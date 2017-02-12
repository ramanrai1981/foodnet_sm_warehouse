(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseWeighbridgeService', WarehouseWeighbridgeService);
    WarehouseWeighbridgeService.$inject = ['$http'];
    function WarehouseWeighbridgeService($http) {

        var service = {
            save: saveweighbridgedetail,
            getWeighment: getWeighment
        };

        return service;

        function saveweighbridgedetail(data) {

            return $http.put("/api/saveweighment/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }

        function getWeighment(lotid) {

            return $http.get("/api/getweighment/" + lotid, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }



    }
})();
