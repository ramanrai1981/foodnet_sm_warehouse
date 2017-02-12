(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('WarehouseWeighbridgeController', WarehouseWeighbridgeController);
    WarehouseWeighbridgeController.$inject = ['$scope', '$state', 'Principal', 'WarehouseService', 'WarehouseWeighbridgeService', 'WarehouseTokenService', 'WarehouseCommodityRecivedService', 'MarketPlaceService'];
    function WarehouseWeighbridgeController($scope, $state, Principal, WarehouseService, WarehouseWeighbridgeService, WarehouseTokenService, WarehouseCommodityRecivedService, MarketPlaceService) {
        var warehouseweighbridgevm = this;
        warehouseweighbridgevm.account = null;
        warehouseweighbridgevm.isAuthenticated = null;
        warehouseweighbridgevm.weighbridge = {};
        warehouseweighbridgevm.commodities = [
            {'commodityid': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commodityid': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commodityid': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commodityid': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];
        warehouseweighbridgevm.procurementagency = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'},
            {'depositorcode': '72cec150-4430-11e6-beb8-9e71128cae77', 'depositorname': 'Other'}
        ];
        warehouseweighbridgevm.mills = [
            {'millcode': '72cec45c-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 1'},
            {'millcode': '72cec556-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 2'}
        ];
        warehouseweighbridgevm.warehouse = {};
        warehouseweighbridgevm.tokenobject = {};


        getAccount();
        warehouseweighbridgevm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehouseweighbridgevm.account = account;
                warehouseweighbridgevm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponse).catch(function (err) {
            alert(err);
        });

        function warehouseServiceResponse(warehouseDetail) {
            warehouseweighbridgevm.warehouse.warehouselicenseno = warehouseDetail.data.warehouselicenseno;
            warehouseweighbridgevm.warehouse.warehousename = warehouseDetail.data.warehousename;
        }

        warehouseweighbridgevm.getTokenInformation = function () {
            warehouseweighbridgevm.tokenobject.warehouselicenseno = warehouseweighbridgevm.warehouse.warehouselicenseno;
            warehouseweighbridgevm.tokenobject.wtoken = warehouseweighbridgevm.token;

            if (warehouseweighbridgevm.tokenobject.wtoken !== null) {
                WarehouseTokenService.getToken(warehouseweighbridgevm.tokenobject).then(tokenresponse).catch(function (err) {
                });
            }
        };
        function tokenresponse(response) {
            warehouseweighbridgevm.tokenobject = response.data;
            warehouseweighbridgevm.weighbridge.lotid = warehouseweighbridgevm.tokenobject.lotid;
            if (warehouseweighbridgevm.weighbridge.lotid === null) {
                toastr.error("Token does not exists");

            }
            if (warehouseweighbridgevm.tokenobject.lotid !== null) {
                toastr.success("Token Exists !! Getting Details");
                WarehouseWeighbridgeService.getWeighment(warehouseweighbridgevm.tokenobject.lotid).then(weighmentresponse).catch(function (err) {
                });
                WarehouseCommodityRecivedService.getCommodityRecieved(warehouseweighbridgevm.tokenobject.lotid).then(commodityrecievedresponse).catch(function (err) {
                });
            }
        }


        function weighmentresponse(response) {
            warehouseweighbridgevm.weighbridge = response.data;
        }

        function commodityrecievedresponse(response) {

            warehouseweighbridgevm.commodityrecieved = response.data;
            for (var i = 0; i < warehouseweighbridgevm.commodities.length; i++) {
                if (warehouseweighbridgevm.commodityrecieved.commoditycode === warehouseweighbridgevm.commodities[i].commodityid) {
                    warehouseweighbridgevm.commodityrecieved.commoditycode = warehouseweighbridgevm.commodities[i].commodityname;
                }
            }
            for (var i = 0; i < warehouseweighbridgevm.procurementagency.length; i++) {
                if (warehouseweighbridgevm.commodityrecieved.depositor === warehouseweighbridgevm.procurementagency[i].depositorcode) {
                    warehouseweighbridgevm.commodityrecieved.depositor = warehouseweighbridgevm.procurementagency[i].depositorname;
                }
            }
            if (warehouseweighbridgevm.commodityrecieved.sourcemandi !== null) {
                MarketPlaceService.getMarketCommitteeList().then(marketCommitteeresponse).catch(function (err) {
                });
            } else if (warehouseweighbridgevm.commodityrecieved.sourcemill !== null) {
                for (var i = 0; i < warehouseweighbridgevm.mills.length; i++) {
                    if (warehouseweighbridgevm.commodityrecieved.sourcemill === warehouseweighbridgevm.mills[i].millcode) {
                        warehouseweighbridgevm.commodityrecieved.sourcemillname = warehouseweighbridgevm.mills[i].millname;
                    }
                }
            } else if (warehouseweighbridgevm.commodityrecieved.sourcewarehouse !== null) {
                WarehouseService.getActiveWarehouses().then(allActiveWarehouses).catch(function (err) {
                    alert(err);
                });
            }
        }
        function allActiveWarehouses(response) {
            warehouseweighbridgevm.warehouses = response.data;
            for (var i = 0; i < warehouseweighbridgevm.warehouses.length; i++) {
                if (warehouseweighbridgevm.commodityrecieved.sourcewarehouse === warehouseweighbridgevm.warehouses[i].warehouselicenseno) {
                    warehouseweighbridgevm.commodityrecieved.sourcewarehousename = warehouseweighbridgevm.warehouses[i].warehousename;
                }
            }
        }


        function marketCommitteeresponse(response) {
            for (var i = 0; i < response.length; i++) {

                if (warehouseweighbridgevm.commodityrecieved.sourcemandi === response[i].id) {
                    warehouseweighbridgevm.commodityrecieved.sourcemandiname = response[i].market_committee_name;
                }
            }
        }

        warehouseweighbridgevm.save = function () {
            warehouseweighbridgevm.weighbridge.lotid = warehouseweighbridgevm.tokenobject.lotid;
            warehouseweighbridgevm.weighbridge.byuser = warehouseweighbridgevm.account.login;
            if (warehouseweighbridgevm.weighbridge.lotid !== null) {
                WarehouseWeighbridgeService.save(warehouseweighbridgevm.weighbridge).then(serviceresponse).catch(function (err) {

                });
            } else {
                toastr.warning("Invalid Token Number");
                warehouseweighbridgevm.weighbridge.token = "";
            }


        };


        function serviceresponse(response) {
            if (response.data === false) {
                toastr.error("Request Failed");
            } else {
                toastr.success("Information Updated");

            }

            $state.reload();
        }

        warehouseweighbridgevm.calculateNetWeight = function () {
            warehouseweighbridgevm.weighbridge.netweight = parseInt(warehouseweighbridgevm.weighbridge.grossweight) - parseInt(warehouseweighbridgevm.weighbridge.tareweight);
        }




    }
})();




