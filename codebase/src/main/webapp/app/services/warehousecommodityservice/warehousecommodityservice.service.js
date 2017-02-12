(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseCommodityRecivedService', WarehouseCommodityRecivedService);
    WarehouseCommodityRecivedService.$inject = ['$http'];
    function WarehouseCommodityRecivedService($http) {

        var service = {
            saveCommodityRecieved: saveCommodityRecieved,
            getCommodityRecieved: getCommodityRecieved,
            updateCommodityRecievedStatus: updateCommodityRecievedStatus
        };

        return service;

        function saveCommodityRecieved(data) {
            return $http.put("/api/savewarehousecommodity/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }
        function updateCommodityRecievedStatus(data) {
            return $http.put("/api/updatelotstatus/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }

        function getCommodityRecieved(data) {
            return $http.get("/api/getwarehouselot/" + data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }





    }
})();
