(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('FarmerLocationService', FarmerLocationService);
    FarmerLocationService.$inject = ['$http'];
    function FarmerLocationService($http) {

        var service = {
            getLocationDetails: getLocationDetails,
            updateLocationDetails: updateLocationDetails
        };

        return service;

        function getLocationDetails(login) {
            return $http.get("/farmer/locationdetails/" + login, {
            }).success(function (response) {
                return response;
            });
        }

        function updateLocationDetails(data) {
            return $http.put("/farmer/updatelocationdetails/", data, {
            }).success(function (response) {
                return response;
            });
        }




    }
})();
