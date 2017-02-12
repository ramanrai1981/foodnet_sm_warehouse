
(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('WarehouseCommodityRecievedController', WarehouseCommodityRecievedController);
    WarehouseCommodityRecievedController.$inject = ['$scope', '$state', 'Principal', 'WarehouseCommodityRecivedService', 'WarehouseService', 'WarehouseTokenService', 'MarketPlaceService'];
    function WarehouseCommodityRecievedController($scope, $state, Principal, WarehouseCommodityRecivedService, WarehouseService, WarehouseTokenService, MarketPlaceService) {
        var warehouseinwardlotvm = this;
        warehouseinwardlotvm.account = null;
        warehouseinwardlotvm.isAuthenticated = null;
        warehouseinwardlotvm.saved = false;
        warehouseinwardlotvm.token = {};

        warehouseinwardlotvm.inwardgatepass = {
            "lotid": "",
            "warehousereciept": "",
            "liftedon": "",
            "enteredby": "",
            "commoditycode": "",
            "liftedweight": "",
            "liftedbags": "",
            "liftedgunnyweight": "",
            "liftednetweight": "",
            "depositor": "",
            "vehicleno": "",
            "drivername": "",
            "sourcemandi": "",
            "sourcewarehouse": "",
            "sourcemill": "",
            "inwarehouse": "",
            "inmill": ""
        };
        warehouseinwardlotvm.commodities = [
            {'commodityid': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commodityid': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commodityid': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commodityid': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];
       
        warehouseinwardlotvm.mills = [
            {'millcode': '72cec45c-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 1'},
            {'millcode': '72cec556-4430-11e6-beb8-9e71128cae77', 'millname': 'Mill 2'}
        ];
        warehouseinwardlotvm.procurementagency = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'}
        ];

        getAccount();
        warehouseinwardlotvm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehouseinwardlotvm.account = account;
                warehouseinwardlotvm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });


        WarehouseService.getActiveWarehouses().then(allActiveWarehouses).catch(function (err) {
            alert(err);
        });

        function allActiveWarehouses(response) {
            warehouseinwardlotvm.warehouses = response.data;
        }

        MarketPlaceService.getMarketCommitteeList().then(allMarketCommittee).catch(function (err) {
            alert(err);
        });

        function allMarketCommittee(response) {
            warehouseinwardlotvm.mandis = response;


        }

        warehouseinwardlotvm.getWarehouseRecieptDetails = function () {

            warehouseinwardlotvm.inwardgatepass = warehouseinwardlotvm.frommandi;
            warehouseinwardlotvm.inwardgatepass.liftedon = new Date(warehouseinwardlotvm.frommandi.liftedon);
        };

        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponse).catch(function (err) {
            alert(err);
        });

        function warehouseServiceResponse(warehouse) {
            warehouseinwardlotvm.inwardgatepass.warehouselicenseno = warehouse.data.warehouselicenseno;
            warehouseinwardlotvm.inwardgatepass.warehousename = warehouse.data.warehousename;
        }


        warehouseinwardlotvm.saveLotRecieved = function () {
            if (warehouseinwardlotvm.inwardgatepass.commoditycode === "") {
                toastr.error("Please select Commodity");
                return;
            }
            if (warehouseinwardlotvm.inwardgatepass.depositor === "") {
                toastr.error("Please select Depositor");
                return;
            }
          

            WarehouseCommodityRecivedService.saveCommodityRecieved(warehouseinwardlotvm.inwardgatepass).then(warehouseLotRecivedServiceResponse).catch(function (err) {
                warehouseinwardlotvm.err = err;
            });

        };

        function warehouseLotRecivedServiceResponse(response) {
            warehouseinwardlotvm.token.warehouselicenseno = "WLN-230";
            warehouseinwardlotvm.token.lotid = response.data;
            WarehouseTokenService.generateWarehouseToken(warehouseinwardlotvm.token).then(warehouseTokenServiceResponse).catch(function (err) {
                warehouseinwardlotvm.err = err;
            });
            warehouseinwardlotvm.saved = true;

        }
        function warehouseTokenServiceResponse(response) {
            warehouseinwardlotvm.tokenrc = response.data;
            toastr.success("Details Captured");
            toastr.success("Token No. : " + warehouseinwardlotvm.tokenrc.wtoken, "Token", {timeOut: 10000});
            $state.reload();

        }

        warehouseinwardlotvm.calculateBags = function () {
            warehouseinwardlotvm.inwardgatepass.liftedbags = parseInt(parseInt(warehouseinwardlotvm.inwardgatepass.liftedweight) / 50);
            if (parseInt(warehouseinwardlotvm.inwardgatepass.liftedweight) / 50 > warehouseinwardlotvm.inwardgatepass.liftedbags) {
                warehouseinwardlotvm.inwardgatepass.liftedbags = parseInt(warehouseinwardlotvm.inwardgatepass.liftedbags) + 1;
            }
        };




    }
})();




