(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('StackManagementController', StackManagementController);
    StackManagementController.$inject = ['$scope', 'Principal', 'WarehouseChamberStackService', 'WarehouseService', 'AlertService'];
    function StackManagementController($scope, Principal, WarehouseChamberStackService, WarehouseService, AlertService) {
        var stackmanagementvm = this;
        stackmanagementvm.account = null;
        stackmanagementvm.isAuthenticated = null;
        stackmanagementvm.stack = [];

        stackmanagementvm.commodities = [
            {'commoditycode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Wheat'},
            {'commoditycode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Rice'},
            {'commoditycode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Sugar'},
            {'commoditycode': '72cec150-4430-11e6-beb8-9e71128cae77', 'commodityname': 'Cotton'}
        ];

        stackmanagementvm.stacktypes = [
            {'stacktype': '12X4X4'},
            {'stacktype': '12X4X8'},
            {'stacktype': '12X8X4'},
            {'stacktype': '10X3X2'}
        ];
        stackmanagementvm.depositors = [
            {'depositorcode': '72ceb764-4430-11e6-beb8-9e71128cae77', 'depositorname': 'FCI'},
            {'depositorcode': '72ceba0c-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HAFED'},
            {'depositorcode': '72cebf48-4430-11e6-beb8-9e71128cae77', 'depositorname': 'HSWC'},
            {'depositorcode': '72cec150-4430-11e6-beb8-9e71128cae77', 'depositorname': 'Other'}
        ];



        getAccount();
        stackmanagementvm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                stackmanagementvm.account = account;
                stackmanagementvm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });
        WarehouseService.getWarehouseDetail("WLN-230").then(warehouseServiceResponseMaster).catch(function (err) {
           toastr.error('Error while getting Warehouse Detail: '+ err);
        });

        function warehouseServiceResponseMaster(warehouseDetail) {

            stackmanagementvm.warehousename = warehouseDetail.data.warehousename;

        }
        WarehouseChamberStackService.getChambersStacks("WLN-230").then(warehouseServiceResponse).catch(function (err) {
             toastr.error('Error : '+ err);
        });


        function warehouseServiceResponse(response) {
            stackmanagementvm.response = response;

        }

        stackmanagementvm.save = function () {
            for (var i = 1; i < stackmanagementvm.stack.length; i++) {
                stackmanagementvm.stack[i].warehouselicenseno = stackmanagementvm.response.data[i - 1].warehouselicenseno;
                stackmanagementvm.stack[i].fromdate = stackmanagementvm.response.data[i - 1].fromdate;
                stackmanagementvm.stack[i].capacityinbags = stackmanagementvm.response.data[i - 1].capacityinbags;
                WarehouseChamberStackService.saveChamberStacks(stackmanagementvm.stack[i]).then(ctackmanagementresponse).catch(function (err) {
                  toastr.error('Error : '+ err);
                });
            }

        };

        function ctackmanagementresponse(response) {
            toastr.success('Records updated');
        }

    }






})();




