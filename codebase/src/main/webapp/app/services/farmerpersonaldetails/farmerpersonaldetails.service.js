(function () {
    'use strict';
    angular
            .module('farmerApp')
            .factory('FarmerPersonalDetailService', FarmerPersonalDetailService);
    FarmerPersonalDetailService.$inject = ['$http'];
    function FarmerPersonalDetailService($http) {

        var service = {
            getFarmerDetails: getFarmerDetails,
            updateFarmerDetails: updateFarmerDetails
        };

        return service;

        function getFarmerDetails(login) {
            return $http.get("/farmer/personaldetails/" + login, {
            }).success(function (response) {
                return response;
            });
        }

        function updateFarmerDetails(data) {
            return $http.put("/farmer/updatepersonaldetails/", data, {
            }).success(function (response) {
                return response;
            });
        }




    }
})();
