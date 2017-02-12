(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('WarehouseChamberStackService', WarehouseChamberStackService);
    WarehouseChamberStackService.$inject = ['$http'];
    function WarehouseChamberStackService($http) {

        var service = {
            getChambersStacks: getChambersStacks,
            saveChamberStacks: saveChamberStacks,
            getStack: getStack

        };

        return service;

        function getChambersStacks(id) {

            return $http.get("/api/getchamberstack/" + id, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }

        function getStack(stackid) {

            return $http.get("/api/getstack/" + stackid, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }

        function saveChamberStacks(data) {

            return $http.put("/api/savechamberstack/", data, {
            }).success(function (response) {
                return response;
            }).error(function (data, status) {

            });
        }






    }
})();
