(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseTokenService', WarehouseTokenService);
    WarehouseTokenService.$inject = ['$http'];
    function WarehouseTokenService($http) {

        var service = {
            getToken: getToken,
            deleteToken: deleteToken,
            generateWarehouseToken: generateWarehouseToken,
            getAllTokens: getAllTokens
        };

        return service;

        function getToken(data) {

            return $http.put("/api/gettoken/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
                alert("Please Enter a Valid Token");
            });
        }

        function deleteToken(data) {

            return $http.put("/api/deletetoken/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
                alert("Please Enter a Valid Token");
            });
        }

        function generateWarehouseToken(data) {

            return $http.put("/api/generatewarehousetoken/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
                alert("Please Enter a Valid Token");
            });
        }
        function getAllTokens(warehouseid) {

            return $http.get("/api/getalltokens/"+ warehouseid, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
                alert("Please Enter a Valid Token");
            });
        }




    }
})();
