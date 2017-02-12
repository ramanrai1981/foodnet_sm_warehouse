(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('WarehouseController', WarehouseController);
    WarehouseController.$inject = ['$scope', 'Principal', 'WarehouseService', 'WarehouseChamberStackService'];
    function WarehouseController($scope, Principal, WarehouseService, WarehouseChamberStackService) {
        var warehousevm = this;
        warehousevm.account = null;
        warehousevm.isAuthenticated = null;
        warehousevm.chamberstacks = [];
        warehousevm.stacktypes = [
            {'stacktype': '12X4X4'},
            {'stacktype': '12X4X8'},
            {'stacktype': '12X8X4'},
            {'stacktype': '10X3X2'}
        ];


        getAccount();
        warehousevm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                warehousevm.account = account;
                warehousevm.isAuthenticated = Principal.isAuthenticated;
            });
        }
        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });

        warehousevm.getWarehouse = function () {

            warehousevm.chamberstacks = [];
            WarehouseService.getWarehouseDetail(warehousevm.warehouse.warehouselicenseno).then(warehouseDetailResponse).catch(function (err) {
                toastr.error("Error : " + err);
            });
        };

        function warehouseDetailResponse(response) {

            warehousevm.warehouse = response.data;

            if (warehousevm.warehouse.warehousename !== null) {
                toastr.success("Getting Details of the Warehouse : " + warehousevm.warehouse.warehousename);
                WarehouseChamberStackService.getChambersStacks(warehousevm.warehouse.warehouselicenseno).then(warehuoseChamberStackServiceResponse).catch(function (err) {
                    toastr.error("Some Error Occured");
                });
            }


        }

        warehousevm.save = function () {

            WarehouseService.saveWarehouse(warehousevm.warehouse).then(warehouseServiceResponse).catch(function (err) {
                alert(err);
            });
        };
        function warehouseServiceResponse(response) {
            toastr.success("Warehouse Created / Updated Succesfully");

        }

        function warehuoseChamberStackServiceResponse(response) {
            warehousevm.chamberstacks = response.data;
            warehousevm.chamberstacks.sort(sortByProperty('stackname'));
        }

        var sortByProperty = function (property) {
            return function (x, y) {
                return ((x[property] === y[property]) ? 0 : ((x[property] > y[property]) ? 1 : -1));
            };
        };


    }
})();




