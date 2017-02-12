(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseCommodityRecievedQualityService', WarehouseCommodityRecievedQualityService);
    WarehouseCommodityRecievedQualityService.$inject = ['$http'];
    function WarehouseCommodityRecievedQualityService($http) {

        var service = {
            getLotQuality : getLotQuality,
            savecommodityquality: savecommodityquality
        };

        return service;

        function savecommodityquality(data) {
         
            return $http.put("/api/savecommodityquality/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
          
            });
        }
        
         function getLotQuality(lotid) {
         
            return $http.get("/api/getlotquality/"+ lotid, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
          
            });
        }




    }
})();
