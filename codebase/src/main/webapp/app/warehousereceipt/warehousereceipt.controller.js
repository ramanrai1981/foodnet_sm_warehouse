(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('WarehouseReceiptController', WarehouseReceiptController);
    WarehouseReceiptController.$inject = ['$scope', 'Principal', 'WarehouseTokenService', 'WarehouseCommodityRecivedService', 'WarehouseCommodityRecievedQualityService', 'WarehouseReceiptService', 'WarehouseService', 'WarehouseWeighbridgeService', 'WarehouseLotStackService', 'MarketPlaceService'];
    function WarehouseReceiptController($scope, Principal, WarehouseTokenService, WarehouseCommodityRecivedService, WarehouseCommodityRecievedQualityService, WarehouseReceiptService, WarehouseService, WarehouseWeighbridgeService, WarehouseLotStackService, MarketPlaceService) {
        var warehousereceiptvm = this;
        warehousereceiptvm.account = null;
        warehousereceiptvm.isAuthenticated = null;
        warehousereceiptvm.warehouselotquality = [];
        warehousereceiptvm.token = null;
        warehousereceiptvm.commodityrecieved = null;
        warehousereceiptvm.tokenobject = {};
        warehousereceiptvm.outwardgatepass = {"whr": "",
                    lotid: [],
                    wtoken: [],
                    ondate: ""};
        warehousereceiptvm.warehouse = {};
        warehousereceiptvm.lotstacks = [];
        warehousereceiptvm.totalbags = 0;
        warehousereceiptvm.warehouseweighment = {};
        warehousereceiptvm.mandis = [
            {'mandicode': '72cec268-4430-11e6-beb8-9e71128cae77', 'mandiname': 'Mandi 1'},
            {'mandicode': '72cec362-4430-11e6-beb8-9e71128cae77', 'mandiname': 'Mandi 2'}
        ];
        warehousereceiptvm.commodities = [
            {'commodityid': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commodityid': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commodityid': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commodityid': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];
        warehousereceiptvm.procurementagency = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'},
            {'depositorcode': '72cec150-4430-11e6-beb8-9e71128cae77', 'depositorname': 'Other'}
        ];
        warehousereceiptvm.mills = [
            {'millcode': '72cec45c-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 1'},
            {'millcode': '72cec556-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 2'}
        ];
        getAccount();
        warehousereceiptvm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehousereceiptvm.account = account;
                warehousereceiptvm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });
        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponse).catch(function (err) {
            toastr.error('Error while getting Warehouse Detail: ' + err);
        });
        function warehouseServiceResponse(warehouseDetail) {
            warehousereceiptvm.warehouse.warehouselicenseno = warehouseDetail.data.warehouselicenseno;
            warehousereceiptvm.warehouse.warehousename = warehouseDetail.data.warehousename;
        }
        warehousereceiptvm.getTokenInformation = function () {
            warehousereceiptvm.tokenobject.warehouselicenseno = warehousereceiptvm.warehouse.warehouselicenseno;
            warehousereceiptvm.tokenobject.wtoken = warehousereceiptvm.token;
            if (warehousereceiptvm.token !== null) {
                WarehouseTokenService.getToken(warehousereceiptvm.tokenobject).then(tokenresponse).catch(function (err) {
                });
            }
        };
        function tokenresponse(response) {
            warehousereceiptvm.tokenobject = response.data;
            if (warehousereceiptvm.tokenobject.lotid !== null) {
                WarehouseCommodityRecivedService.getCommodityRecieved(warehousereceiptvm.tokenobject.lotid).then(commodityrecievedresponse).catch(function (err) {
                });
            } else {
                toastr.error('Invalid Token Number');
                warehousereceiptvm.token = "";
            }
        }

        function commodityrecievedresponse(response) {
            warehousereceiptvm.commodityrecieved = response.data;
            for (var i = 0; i < warehousereceiptvm.commodities.length; i++) {
                if (warehousereceiptvm.commodityrecieved.commoditycode === warehousereceiptvm.commodities[i].commodityid) {
                    warehousereceiptvm.commodityrecieved.commodityname = warehousereceiptvm.commodities[i].commodityname;
                }
            }
            for (var i = 0; i < warehousereceiptvm.procurementagency.length; i++) {
                if (warehousereceiptvm.commodityrecieved.depositor === warehousereceiptvm.procurementagency[i].depositorcode) {
                    warehousereceiptvm.commodityrecieved.depositorname = warehousereceiptvm.procurementagency[i].depositorname;
                }
            }
            if (warehousereceiptvm.commodityrecieved.sourcemandi !== null) {
                MarketPlaceService.getMarketCommitteeList().then(marketCommitteeresponse).catch(function (err) {
                });
            } else if (warehousereceiptvm.commodityrecieved.sourcemill !== null) {
                for (var i = 0; i < warehousereceiptvm.mills.length; i++) {
                    if (warehousereceiptvm.commodityrecieved.sourcemill === warehousereceiptvm.mills[i].millcode) {
                        warehousereceiptvm.commodityrecieved.sourcemillname = warehousereceiptvm.mills[i].millname;
                    }
                }
            } else if (warehousereceiptvm.commodityrecieved.sourcewarehouse !== null) {
                WarehouseService.getActiveWarehouses().then(allActiveWarehouses).catch(function (err) {
                    toastr.error('Error Occured' + err);
                });
            }
            WarehouseWeighbridgeService.getWeighment(response.data.lotid).then(weighmentresponse).catch(function (err) {
            });
            WarehouseLotStackService.getLotStack(response.data.lotid).then(lotstackresponse).catch(function (err) {
            });
            WarehouseCommodityRecievedQualityService.getLotQuality(response.data.lotid).then(qualityserviceresponse).catch(function (err) {
            });
            WarehouseService.getWarehouseDetail(warehousereceiptvm.commodityrecieved.inwarehouse).then(warehouseresponse).catch(function (err) {
            });

        }
        function marketCommitteeresponse(response) {
            for (var i = 0; i < response.length; i++) {

                if (warehousereceiptvm.commodityrecieved.sourcemandi === response[i].id) {
                    warehousereceiptvm.commodityrecieved.sourcemandiname = response[i].market_committee_name;
                }
            }

        }

        function allActiveWarehouses(response) {
            warehousereceiptvm.warehouses = response.data;
            for (var i = 0; i < warehousereceiptvm.warehouses.length; i++) {
                if (warehousereceiptvm.commodityrecieved.sourcewarehouse === warehousereceiptvm.warehouses[i].warehouselicenseno) {
                    warehousereceiptvm.commodityrecieved.sourcewarehousename = warehousereceiptvm.warehouses[i].warehousename;
                }
            }
        }
        function warehouseresponse(response) {
            warehousereceiptvm.commodityrecieved.warehousename = response.data.warehousename;
        }

        function weighmentresponse(weighresponse) {
            warehousereceiptvm.warehouseweighment = weighresponse.data;
        }
        function lotstackresponse(stackresponse) {
            warehousereceiptvm.totalbags = 0;
            warehousereceiptvm.lotstacks = stackresponse.data;
            for (var i = 0; i < warehousereceiptvm.lotstacks.length; i++) {
                warehousereceiptvm.totalbags = warehousereceiptvm.totalbags + warehousereceiptvm.lotstacks[i].bags;
            }
        }

        function qualityserviceresponse(response) {
            warehousereceiptvm.warehouselotquality = response.data;
        }




        warehousereceiptvm.save = function () {
            warehousereceiptvm.outwardgatepass.lotid[0] = warehousereceiptvm.commodityrecieved.lotid;
            warehousereceiptvm.outwardgatepass.wtoken[0] = warehousereceiptvm.token;
            WarehouseReceiptService.saveWarehouseReceipt(warehousereceiptvm.outwardgatepass).then(warehousereceipt).catch(function (err) {
            });
        };
        function warehousereceipt(response) {
            WarehouseTokenService.deleteToken(warehousereceiptvm.tokenobject).then(tokendeleted).catch(function (err) {
            });
        }


        function tokendeleted(response) {
            toastr.success("Information Saved and Token Released");
            warehousereceiptvm.warehouselotquality = [];
            warehousereceiptvm.commodityrecieved = null;
            warehousereceiptvm.wtoken = null;
            warehousereceiptvm.outwardgatepass = null;
            warehousereceiptvm.token = null;
            warehousereceiptvm.totalbags = 0;
            warehousereceiptvm.warehouseweighment = {};
        }




    }
})();




