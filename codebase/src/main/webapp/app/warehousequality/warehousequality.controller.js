(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('WarehouseQualityController', WarehouseQualityController);
    WarehouseQualityController.$inject = ['$scope', 'Principal', 'QualityService', 'WarehouseTokenService', 'WarehouseCommodityRecivedService', 'WarehouseCommodityRecievedQualityService', 'WarehouseService', 'MarketPlaceService', 'WarehouseWeighbridgeService'];
    function WarehouseQualityController($scope, Principal, QualityService, WarehouseTokenService, WarehouseCommodityRecivedService, WarehouseCommodityRecievedQualityService, WarehouseService, MarketPlaceService, WarehouseWeighbridgeService) {
        var warehousequalityvm = this;
        warehousequalityvm.account = null;
        warehousequalityvm.isAuthenticated = null;
        warehousequalityvm.quality = [];
        warehousequalityvm.qualityparam = null;
        warehousequalityvm.qualitystatus = "Accepted";
        warehousequalityvm.commodityrecieved = null;
        warehousequalityvm.tokenobject = {};
        warehousequalityvm.warehouse = {};
        warehousequalityvm.commodities = [
            {'commodityid': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commodityid': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commodityid': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commodityid': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];
        warehousequalityvm.procurementagency = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'},
            {'depositorcode': '72cec150-4430-11e6-beb8-9e71128cae77', 'depositorname': 'Other'}
        ];
        warehousequalityvm.mills = [
            {'millcode': '72cec45c-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 1'},
            {'millcode': '72cec556-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 2'}
        ];
        getAccount();
        warehousequalityvm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehousequalityvm.account = account;
                warehousequalityvm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });
        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponse).catch(function (err) {
            alert(err);
        });

        function warehouseServiceResponse(warehouseDetail) {
            warehousequalityvm.warehouse.warehouselicenseno = warehouseDetail.data.warehouselicenseno;
            warehousequalityvm.warehouse.warehousename = warehouseDetail.data.warehousename;
        }
        warehousequalityvm.getQualityParameter = function () {
            warehousequalityvm.tokenobject.wtoken = warehousequalityvm.token;
            warehousequalityvm.tokenobject.warehouselicenseno = warehousequalityvm.warehouse.warehouselicenseno;
            if (warehousequalityvm.token !== null) {
                WarehouseTokenService.getToken(warehousequalityvm.tokenobject).then(tokenresponse).catch(function (err) {
                });
            }
        };
        function tokenresponse(response) {
            warehousequalityvm.tokenobject = response.data;
            if (warehousequalityvm.tokenobject.lotid !== null) {
                WarehouseCommodityRecievedQualityService.getLotQuality(warehousequalityvm.tokenobject.lotid).then(commodityqualityresponse).catch(function (err) {
                });


            } else {
                toastr.warning("Invalid Token Number");
                warehousequalityvm.token = "";
            }
        }

        function commodityqualityresponse(response) {
            warehousequalityvm.qualityparam = response.data;

            WarehouseCommodityRecivedService.getCommodityRecieved(warehousequalityvm.tokenobject.lotid).then(commodityrecievedresponse).catch(function (err) {
            });
            WarehouseWeighbridgeService.getWeighment(warehousequalityvm.tokenobject.lotid).then(weighmentresponse).catch(function (err) {
            });
        }

        function weighmentresponse(response) {
            warehousequalityvm.weighment = response.data;
        }
        function commodityrecievedresponse(response) {
            warehousequalityvm.commodityrecieved = response.data;


            for (var i = 0; i < warehousequalityvm.commodities.length; i++) {
                if (warehousequalityvm.commodityrecieved.commoditycode === warehousequalityvm.commodities[i].commodityid) {
                    warehousequalityvm.commodityrecieved.commodityname = warehousequalityvm.commodities[i].commodityname;
                }
            }
            for (var i = 0; i < warehousequalityvm.procurementagency.length; i++) {
                if (warehousequalityvm.commodityrecieved.depositor === warehousequalityvm.procurementagency[i].depositorcode) {
                    warehousequalityvm.commodityrecieved.depositorname = warehousequalityvm.procurementagency[i].depositorname;
                }
            }

            if (warehousequalityvm.commodityrecieved.sourcemandi !== null) {
                MarketPlaceService.getMarketCommitteeList().then(marketCommitteeresponse).catch(function (err) {
                });
            } else if (warehousequalityvm.commodityrecieved.sourcemill !== null) {
                for (var i = 0; i < warehousequalityvm.mills.length; i++) {
                    if (warehousequalityvm.commodityrecieved.sourcemill === warehousequalityvm.mills[i].millcode) {
                        warehousequalityvm.commodityrecieved.sourcemillname = warehousequalityvm.mills[i].millname;
                    }
                }
            } else if (warehousequalityvm.commodityrecieved.sourcewarehouse !== null) {
                WarehouseService.getActiveWarehouses().then(allActiveWarehouses).catch(function (err) {
                    alert(err);
                });
            }

            if (warehousequalityvm.qualityparam.length === 0) {
                QualityService.getCommodityQuality(response.data.commoditycode).then(qualityserviceresponse).catch(function (err) {
                });
            }
        }

        function allActiveWarehouses(response) {
            warehousequalityvm.warehouses = response.data;
            for (var i = 0; i < warehousequalityvm.warehouses.length; i++) {
                if (warehousequalityvm.commodityrecieved.sourcewarehouse === warehousequalityvm.warehouses[i].warehouselicenseno) {
                    warehousequalityvm.commodityrecieved.sourcewarehousename = warehousequalityvm.warehouses[i].warehousename;
                }
            }
        }

        function marketCommitteeresponse(response) {
            for (var i = 0; i < response.length; i++) {

                if (warehousequalityvm.commodityrecieved.sourcemandi === response[i].id) {
                    warehousequalityvm.commodityrecieved.sourcemandiname = response[i].market_committee_name;
                }
            }

        }

        function qualityserviceresponse(response) {
            warehousequalityvm.qualityparam = response.data;
        }

        warehousequalityvm.save = function () {

            for (var i = 1; i < warehousequalityvm.quality.length; i++) {
                warehousequalityvm.quality[i].lotid = warehousequalityvm.commodityrecieved.lotid;
                warehousequalityvm.quality[i].byuser = warehousequalityvm.account.login;
            }

            WarehouseCommodityRecievedQualityService.savecommodityquality(warehousequalityvm.quality).then(savecommodityqualityresponse).catch(function (err) {
            });

        };
        function savecommodityqualityresponse(response) {

            warehousequalityvm.commodityrecieved.status = warehousequalityvm.qualitystatus;
            if (warehousequalityvm.commodityrecieved.status !== null) {
                WarehouseCommodityRecivedService.updateCommodityRecievedStatus(warehousequalityvm.commodityrecieved).then(updateLotStatusResponse).catch(function (err) {
                });
            }
        }

        function updateLotStatusResponse(response) {
            toastr.success("Quality Parameters Saved");
        }

    }
})();




