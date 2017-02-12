(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('MspService', MspService);
    MspService.$inject = ['$http'];
    function MspService($http) {

        var service = {
            getMspCurrentYear: getMspCurrentYear
           
        };

        return service;

        function getMspCurrentYear() {
            return $http.get("/farmer/mspcurrentyear/", {
            }).success(function (response) {
                return response;
            });
        }

       




    }
})();
