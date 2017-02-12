(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseLotStackService', WarehouseLotStackService);
    WarehouseLotStackService.$inject = ['$http'];
    function WarehouseLotStackService($http) {

        var service = {
           saveLotStack : saveLotStack,
           getLotStack : getLotStack,
           getStockInStack : getStockInStack
        };

        return service;

      
        function saveLotStack(data) {

            return $http.put("/api/savelotstack/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
               
            });
        }
        
        function getLotStack(lotid) {

            return $http.get("/api/getlotstack/"+ lotid, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
               
            });
        }

        function getStockInStack(data) {

            return $http.put("/api/getstockinstack/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
               
            });
        }



    }
})();
