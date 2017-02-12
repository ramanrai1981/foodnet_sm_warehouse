(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('FarmerPersonalDetailsController', FarmerPersonalDetailsController);
    FarmerPersonalDetailsController.$inject = ['$scope', '$http', 'Principal', 'FarmerPersonalDetailService'];
    function FarmerPersonalDetailsController($scope, $http, Principal, FarmerPersonalDetailService) {
        var vm = this;
        vm.account = null;
        vm.isAuthenticated = null;
        vm.response = false;
        vm.error = false;
        vm.errornotfound = false;
        getAccount();
        vm.callAPI = function ($resource) {
            console.log("API Called");
            console.log($resource);
        };
        function getAccount() {
            Principal.identity().then(function (account) {
                vm.account = account;
                vm.isAuthenticated = Principal.isAuthenticated;
            });
        }

        $scope.$on('authenticationSuccess', function () {
            getAccount();
        });
        Principal.identity().then(function (account) {
            vm.account = account;
            vm.isAuthenticated = Principal.isAuthenticated;
            FarmerPersonalDetailService.getFarmerDetails(vm.account.login).then(getdatafromresponse).catch(function (err) {
                vm.errornotfound = true;
            });

        });
        function getdatafromresponse(farmerdto) {
            vm.personaldetails = farmerdto.data;
        }

        vm.updatepersonaldetails = function () {
            FarmerPersonalDetailService.updateFarmerDetails(vm.personaldetails).then(getdatafromupdateresponse).catch(function (err) {
                vm.errornotfound = true;
            });

        };

        function getdatafromupdateresponse(updateresponse) {
            vm.response = updateresponse.data;
            vm.error = false;
            vm.errornotfound = false;
            if (vm.response === false) {
                vm.error = true;
            }
        }
    }
})();




