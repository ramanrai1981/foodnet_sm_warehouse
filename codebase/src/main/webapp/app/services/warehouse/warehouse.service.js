(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseService', WarehouseService);
    WarehouseService.$inject = ['$http'];
    function WarehouseService($http) {

        var service = {
            getWarehouseDetail: getWarehouseDetail,
            saveWarehouse: saveWarehouse,
            getActiveWarehouses: getActiveWarehouses
        };

        return service;

        function getWarehouseDetail(id) {

            return $http.get("/api/getwarehouse/" + id, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }
        function getActiveWarehouses() {

            return $http.get("/api/getactivewarehouses", {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }

        function saveWarehouse(data) {

            return $http.put("/api/savewarehouse/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }




    }
})();
