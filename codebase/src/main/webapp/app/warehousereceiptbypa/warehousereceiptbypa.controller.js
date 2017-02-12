(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('WarehouseReceiptByPAController', WarehouseReceiptByPAController);
    WarehouseReceiptByPAController.$inject = ['$scope', '$state', 'Principal', 'WarehouseTokenService', 'WarehouseCommodityRecivedService', 'WarehouseCommodityRecievedQualityService', 'WarehouseReceiptService', 'WarehouseService', 'WarehouseWeighbridgeService', 'WarehouseLotStackService', 'MarketPlaceService'];
    function WarehouseReceiptByPAController($scope, $state, Principal, WarehouseTokenService, WarehouseCommodityRecivedService, WarehouseCommodityRecievedQualityService, WarehouseReceiptService, WarehouseService, WarehouseWeighbridgeService, WarehouseLotStackService, MarketPlaceService) {
        var warehousereceiptbypavm = this;
        warehousereceiptbypavm.account = null;
        warehousereceiptbypavm.isAuthenticated = null;
        warehousereceiptbypavm.receipt = [];
        warehousereceiptbypavm.tokenobject = [];
        warehousereceiptbypavm.checkedLotIDs = [];
        warehousereceiptbypavm.outwardgatepass = {"whr": "",
            lotid: [],
            wtoken: [],
            ondate: ""};
        warehousereceiptbypavm.warehouse = {};
        warehousereceiptbypavm.mandis = [
            {'mandicode': '72cec268-4430-11e6-beb8-9e71128cae77', 'mandiname': 'Mandi 1'},
            {'mandicode': '72cec362-4430-11e6-beb8-9e71128cae77', 'mandiname': 'Mandi 2'}
        ];
        warehousereceiptbypavm.commodities = [
            {'commodityid': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commodityid': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commodityid': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commodityid': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];
        warehousereceiptbypavm.procurementagency = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'},
            {'depositorcode': '72cec150-4430-11e6-beb8-9e71128cae77', 'depositorname': 'Other'}
        ];
        warehousereceiptbypavm.mills = [
            {'millcode': '72cec45c-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 1'},
            {'millcode': '72cec556-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 2'}
        ];
        getAccount();
        warehousereceiptbypavm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehousereceiptbypavm.account = account;
                warehousereceiptbypavm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });
        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponse).catch(function (err) {
            toastr.error('Error while getting Warehouse Detail: ' + err);
        });
        function warehouseServiceResponse(warehouseDetail) {
            warehousereceiptbypavm.warehouse.warehouselicenseno = warehouseDetail.data.warehouselicenseno;
            warehousereceiptbypavm.warehouse.warehousename = warehouseDetail.data.warehousename;
        }

        warehousereceiptbypavm.getAllTokens = function () {
            WarehouseTokenService.getAllTokens(warehousereceiptbypavm.warehouse.warehouselicenseno).then(allTokens).catch(function (err) {
            });
        };
        function allTokens(response) {
            warehousereceiptbypavm.allcommdities = [];
            warehousereceiptbypavm.alltokens = response.data;
            for (var i = 0; i < warehousereceiptbypavm.alltokens.length; i++) {
                WarehouseCommodityRecivedService.getCommodityRecieved(warehousereceiptbypavm.alltokens[i].lotid).then(function (response) {
                    if (response.data.depositor === warehousereceiptbypavm.depositor) {
                        for (var i = 0; i < warehousereceiptbypavm.commodities.length; i++) {
                            if (response.data.commoditycode === warehousereceiptbypavm.commodities[i].commodityid) {
                                response.data.commoditycode = warehousereceiptbypavm.commodities[i].commodityname;
                            }
                        }
                        warehousereceiptbypavm.allcommdities.push(response.data);
                    }
                });
            }
        }

        warehousereceiptbypavm.save = function () {

            for (var i = 0; i < warehousereceiptbypavm.allcommdities.length; i++) {
                for (var j = 0; j < warehousereceiptbypavm.alltokens.length; j++) {
                    if (warehousereceiptbypavm.allcommdities[i].lotid === warehousereceiptbypavm.alltokens[j].lotid) {
                        warehousereceiptbypavm.allcommdities[i].wtoken = warehousereceiptbypavm.alltokens[j].wtoken;
                    }
                }
            }
            var j = 0;
            warehousereceiptbypavm.outwardgatepass.lotid = [];
            warehousereceiptbypavm.outwardgatepass.wtoken = [];
            for (var i = 0; i < warehousereceiptbypavm.allcommdities.length; i++) {
                if (warehousereceiptbypavm.allcommdities[i].checked) {
                    warehousereceiptbypavm.outwardgatepass.lotid[j] = warehousereceiptbypavm.allcommdities[i].lotid;
                    warehousereceiptbypavm.outwardgatepass.wtoken[j++] = warehousereceiptbypavm.allcommdities[i].wtoken;
                }
            }

            WarehouseReceiptService.saveWarehouseReceipt(warehousereceiptbypavm.outwardgatepass).then(warehousereceipt).catch(function (err) {
            });

        };

        function warehousereceipt(response) {
            warehousereceiptbypavm.tokenobject = [];
            for (var i = 0; i < warehousereceiptbypavm.allcommdities.length; i++) {
                if (warehousereceiptbypavm.allcommdities[i].checked) {
                    warehousereceiptbypavm.tokenobject.push({'warehouselicenseno': warehousereceiptbypavm.warehouse.warehouselicenseno, 'wtoken': warehousereceiptbypavm.allcommdities[i].wtoken});
                }

                WarehouseTokenService.deleteToken(warehousereceiptbypavm.tokenobject[i]).then(tokendeleted).catch(function (err) {
                });
            }
        }

        function tokendeleted(response) {
            toastr.success("Information Saved and Token Released");
            $state.reload();
        }
    }
})();




