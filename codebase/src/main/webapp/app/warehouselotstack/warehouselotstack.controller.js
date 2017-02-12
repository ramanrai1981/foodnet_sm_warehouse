

(function () {
    'use strict';

    angular
            .module('farmerApp').filter('unique', function () {
        return function (collection, keyname) {
            var output = [],
                    keys = [];

            angular.forEach(collection, function (item) {
                var key = item[keyname];
                if (keys.indexOf(key) === -1) {
                    keys.push(key);
                    output.push(item);
                }
            });

            return output;
        };
    });


    angular
            .module('farmerApp')
            .controller('WarehouseLotStackController', WarehouseLotStackController);
    WarehouseLotStackController.$inject = ['$scope', '$state', 'Principal', 'WarehouseChamberStackService', 'WarehouseTokenService', 'WarehouseCommodityRecivedService', 'WarehouseService', 'WarehouseLotStackService', 'AlertService', 'WarehouseWeighbridgeService', 'WarehouseCommodityRecievedQualityService', 'MarketPlaceService'];
    function WarehouseLotStackController($scope, $state, Principal, WarehouseChamberStackService, WarehouseTokenService, WarehouseCommodityRecivedService, WarehouseService, WarehouseLotStackService, AlertService, WarehouseWeighbridgeService, WarehouseCommodityRecievedQualityService, MarketPlaceService) {
        var warehouselotstackvm = this;
        warehouselotstackvm.account = null;
        warehouselotstackvm.isAuthenticated = null;
        warehouselotstackvm.tokenobject = {};
        warehouselotstackvm.warehouse = {};
        warehouselotstackvm.stacks = [];
        warehouselotstackvm.autostacking = [];
        warehouselotstackvm.savedlotstack = [];
        warehouselotstackvm.totalbagstobeallocated = 0;


        warehouselotstackvm.lotstack = [{
                "lotid": "",
                "chamberid": "",
                "stackid": "",
                "bags": ""
            }];
        warehouselotstackvm.commodities = [
            {'commodityid': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commodityid': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commodityid': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commodityid': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];
        warehouselotstackvm.procurementagency = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'},
            {'depositorcode': '72cec150-4430-11e6-beb8-9e71128cae77', 'depositorname': 'Other'}
        ];
        warehouselotstackvm.mills = [
            {'millcode': '72cec45c-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 1'},
            {'millcode': '72cec556-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 2'}
        ];
        warehouselotstackvm.items = [{"index": 1}];



        getAccount();
        warehouselotstackvm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehouselotstackvm.account = account;
                warehouselotstackvm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });
        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponse).catch(function (err) {
            alert(err);
        });

        function warehouseServiceResponse(warehouseDetail) {
            warehouselotstackvm.warehouse.warehouselicenseno = warehouseDetail.data.warehouselicenseno;
            warehouselotstackvm.warehouse.warehousename = warehouseDetail.data.warehousename;
        }
        warehouselotstackvm.getStacks = function () {
            warehouselotstackvm.autostacking = [];
            warehouselotstackvm.tokenobject.wtoken = warehouselotstackvm.token;
            warehouselotstackvm.tokenobject.warehouselicenseno = warehouselotstackvm.warehouse.warehouselicenseno;
            if (warehouselotstackvm.token !== null) {
                WarehouseTokenService.getToken(warehouselotstackvm.tokenobject).then(tokenresponse).catch(function (err) {
                });
            }

        };
        function tokenresponse(response) {
            warehouselotstackvm.tokenobject = response.data;
            if (warehouselotstackvm.tokenobject.lotid !== null) {
                WarehouseCommodityRecivedService.getCommodityRecieved(warehouselotstackvm.tokenobject.lotid).then(commodityrecievedresponse).catch(function (err) {
                });
            } else {
                toastr.error("Invalid Token No.");
                warehouselotstackvm.token = "";
            }
        }
        ;
        function commodityrecievedresponse(response) {

            warehouselotstackvm.commodityrecieved = response.data;

            for (var i = 0; i < warehouselotstackvm.commodities.length; i++) {
                if (warehouselotstackvm.commodityrecieved.commoditycode === warehouselotstackvm.commodities[i].commodityid) {
                    warehouselotstackvm.commodityrecieved.commodityname = warehouselotstackvm.commodities[i].commodityname;
                }
            }
            for (var i = 0; i < warehouselotstackvm.procurementagency.length; i++) {
                if (warehouselotstackvm.commodityrecieved.depositor === warehouselotstackvm.procurementagency[i].depositorcode) {
                    warehouselotstackvm.commodityrecieved.depositorname = warehouselotstackvm.procurementagency[i].depositorname;
                }
            }

            if (warehouselotstackvm.commodityrecieved.sourcemandi !== null) {
                MarketPlaceService.getMarketCommitteeList().then(marketCommitteeresponse).catch(function (err) {
                });
            } else if (warehouselotstackvm.commodityrecieved.sourcemill !== null) {
                for (var i = 0; i < warehouselotstackvm.mills.length; i++) {
                    if (warehouselotstackvm.commodityrecieved.sourcemill === warehouselotstackvm.mills[i].millcode) {
                        warehouselotstackvm.commodityrecieved.sourcemillname = warehouselotstackvm.mills[i].millname;
                    }
                }
            } else if (warehouselotstackvm.commodityrecieved.sourcewarehouse !== null) {
                WarehouseService.getActiveWarehouses().then(allActiveWarehouses).catch(function (err) {
                    alert(err);
                });
            }

            WarehouseWeighbridgeService.getWeighment(warehouselotstackvm.commodityrecieved.lotid).then(weighmentresponse).catch(function (err) {
            });
            WarehouseCommodityRecievedQualityService.getLotQuality(response.data.lotid).then(qualityserviceresponse).catch(function (err) {
            });
            WarehouseLotStackService.getLotStack(warehouselotstackvm.commodityrecieved.lotid).then(warehouseLotStackResponse).catch(function (err) {
            });

        }
        function allActiveWarehouses(response) {
            warehouselotstackvm.warehouses = response.data;
            for (var i = 0; i < warehouselotstackvm.warehouses.length; i++) {
                if (warehouselotstackvm.commodityrecieved.sourcewarehouse === warehouselotstackvm.warehouses[i].warehouselicenseno) {
                    warehouselotstackvm.commodityrecieved.sourcewarehousename = warehouselotstackvm.warehouses[i].warehousename;
                }
            }
        }

        function marketCommitteeresponse(response) {
            for (var i = 0; i < response.length; i++) {

                if (warehouselotstackvm.commodityrecieved.sourcemandi === response[i].id) {
                    warehouselotstackvm.commodityrecieved.sourcemandiname = response[i].market_committee_name;
                }
            }

        }


        function weighmentresponse(response) {
            warehouselotstackvm.weighment = response.data;
        }
        function qualityserviceresponse(response) {
            warehouselotstackvm.quality = response.data;
        }

        function warehouseLotStackResponse(response) {
            if (response.data.length === 0) {
                WarehouseChamberStackService.getChambersStacks(warehouselotstackvm.warehouse.warehouselicenseno).then(warehouseChamberStackServiceResponse).catch(function (err) {
                });
            } else {
                warehouselotstackvm.savedlotstack = response.data;
                warehouselotstackvm.savedlotstack.sort(sortByProperty('stackname'));
            }
        }



        var sortByProperty = function (property) {
            return function (x, y) {
                return ((x[property] === y[property]) ? 0 : ((x[property] > y[property]) ? 1 : -1));
            };
        };
        function warehouseChamberStackServiceResponse(response) {
            warehouselotstackvm.stacks = response.data;
            warehouselotstackvm.stacks.sort(sortByProperty('stackname'));
            warehouselotstackvm.stackstemp = [];
            for (var i = 0; i < warehouselotstackvm.stacks.length; i++) {
                if (warehouselotstackvm.stacks[i].commoditycode === warehouselotstackvm.commodityrecieved.commoditycode && warehouselotstackvm.stacks[i].depositor === warehouselotstackvm.commodityrecieved.depositor && (parseInt(warehouselotstackvm.stacks[i].capacityinbags) - parseInt(warehouselotstackvm.stacks[i].stock)) !== 0) {
                    warehouselotstackvm.stackstemp.push(warehouselotstackvm.stacks[i]);
                }
                if (warehouselotstackvm.stacks.length === 0) {
                    toastr.warning("Ask Warehouse Manager to Map Stacks for Commodity and Procurement Agency");
                    return;
                }
            }
            warehouselotstackvm.stacks = warehouselotstackvm.stackstemp;
            warehouselotstackvm.totalbagstobeallocated = warehouselotstackvm.commodityrecieved.liftedbags;
            for (var i = 0; i < warehouselotstackvm.stacks.length; i++) {
                warehouselotstackvm.autostack = {
                    "stackid": "",
                    "stackname": "",
                    "bags": "",
                    "vacancy": ""
                };

                if (warehouselotstackvm.totalbagstobeallocated !== 0) {

                    var maxcapacityofstack = warehouselotstackvm.stacks[i].capacityinbags;
                    var stock = warehouselotstackvm.stacks[i].stock;

                    warehouselotstackvm.stacks[i].vacancy = parseInt(maxcapacityofstack) - parseInt(stock);
                    warehouselotstackvm.autostack.vacancy = parseInt(maxcapacityofstack) - parseInt(stock);
                    if (parseInt(stock) < parseInt(maxcapacityofstack)) {
                        if (parseInt(warehouselotstackvm.totalbagstobeallocated) <= parseInt(maxcapacityofstack) - parseInt(stock)) {
                            warehouselotstackvm.autostack.stackid = warehouselotstackvm.stacks[i].stackid;
                            warehouselotstackvm.autostack.stackname = warehouselotstackvm.stacks[i].stackname;
                            warehouselotstackvm.autostack.bags = warehouselotstackvm.totalbagstobeallocated;
                            warehouselotstackvm.totalbagstobeallocated = 0;
                        } else {
                            warehouselotstackvm.autostack.stackid = warehouselotstackvm.stacks[i].stackid;
                            warehouselotstackvm.autostack.stackname = warehouselotstackvm.stacks[i].stackname;
                            warehouselotstackvm.autostack.bags = (parseInt(maxcapacityofstack) - parseInt(stock));
                            warehouselotstackvm.totalbagstobeallocated = parseInt(warehouselotstackvm.totalbagstobeallocated) - parseInt(warehouselotstackvm.autostack.bags);

                        }
                        warehouselotstackvm.autostacking.push(warehouselotstackvm.autostack);
                    }
                }

            }
        }


        warehouselotstackvm.newRow = function () {
            if (warehouselotstackvm.autostacking.length === 0) {
                return;
            } else {
                warehouselotstackvm.autostack = {
                    "stackid": "",
                    "stackname": "",
                    "bags": "",
                    "vacancy": ""
                };

                warehouselotstackvm.autostacking.push(warehouselotstackvm.autostack);
            }

        };


        warehouselotstackvm.setVacancy = function (index) {
            for (var i = 0; i < warehouselotstackvm.stacks.length; i++) {
                if (warehouselotstackvm.stacks[parseInt(i)].stackid === warehouselotstackvm.lotstack[parseInt(index)].stackid) {
                    warehouselotstackvm.lotstack[parseInt(index)].vacancy = parseInt(warehouselotstackvm.stacks[parseInt(i)].capacityinbags) - parseInt(warehouselotstackvm.stacks[parseInt(i)].stock);
                }
            }

        };





        warehouselotstackvm.save = function () {
            var countofbags = 0;
            for (var i = 1; i < warehouselotstackvm.lotstack.length; i++) {
                countofbags = parseInt(countofbags) + parseInt(warehouselotstackvm.lotstack[parseInt(i)].bags);
            }
            for (var i = 1; i < warehouselotstackvm.lotstack.length; i++) {
                if (parseInt(warehouselotstackvm.lotstack[i].bags) > parseInt(warehouselotstackvm.lotstack[i].vacancy)) {
                    toastr.error("Allocated Bags is more than Vacancy");
                    return;
                }
            }
            if (warehouselotstackvm.lotstack.length === 1) {
                toastr.warning("Already Saved");
                return;
            }
            if (parseInt(warehouselotstackvm.commodityrecieved.liftedbags) > parseInt(countofbags)) {
                toastr.error("No. of Bags allocated is less than the Lifted Bags");
                return;
            }
            if (parseInt(warehouselotstackvm.commodityrecieved.liftedbags) < parseInt(countofbags)) {
                toastr.error("No. of Bags Exceeded Lifted Bags");
                return;
            }
            if (parseInt(warehouselotstackvm.totalbagstobeallocated) > 0) {
                toastr.error("Some Bags are remaining. Please Ask Warehouse Manager to Allocate Stack for this commodity and depositor");
                return;
            }
            for (var i = 1; i < warehouselotstackvm.lotstack.length; i++) {
                warehouselotstackvm.lotstack[i].lotid = warehouselotstackvm.tokenobject.lotid;
                warehouselotstackvm.lotstack[i].byuser = warehouselotstackvm.account.login;
                if (warehouselotstackvm.lotstack[i].stackid !== '' && warehouselotstackvm.lotstack[i].bags !== '') {
                    WarehouseLotStackService.saveLotStack(warehouselotstackvm.lotstack[i]).then(saveLotStackResponse).catch(function (err) {
                    });
                }

            }
        };
        function saveLotStackResponse(response) {
            toastr.success("Information Saved Successfully");
            $state.reload();
        }


    }
})();




