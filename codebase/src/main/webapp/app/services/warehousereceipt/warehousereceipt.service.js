(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseReceiptService', WarehouseReceiptService);
    WarehouseReceiptService.$inject = ['$http'];
    function WarehouseReceiptService($http) {

        var service = {
           saveWarehouseReceipt : saveWarehouseReceipt
        };

        return service;

      
        function saveWarehouseReceipt(data) {

            return $http.put("/api/savewarehousereceipt/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {
               
            });
        }




    }
})();
