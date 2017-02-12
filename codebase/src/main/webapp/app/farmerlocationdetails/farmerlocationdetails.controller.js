/* global autocomplete */

(function () {
    'use strict';
    angular
            .module('farmerApp')
            .controller('FarmerLocationDetailsController', FarmerLocationDetailsController);
    FarmerLocationDetailsController.$inject = ['$scope', 'Principal', 'FarmerLocationService'];
    function FarmerLocationDetailsController($scope, Principal, FarmerLocationService) {
        var vm = this;
        vm.account = null;
        vm.locationdetail=[];
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
            FarmerLocationService.getLocationDetails(vm.account.login).then(getdatafromresponse).catch(function (err) {
                vm.errornotfound = true;
            });

        });
        function getdatafromresponse(farmerdto) {
            vm.locationdetail = farmerdto.data;
        }
        Principal.identity().then(function (account) {
            vm.account = account;
           
            vm.isAuthenticated = Principal.isAuthenticated;
            vm.updatelocationdetails = function () {
             
                
                FarmerLocationService.updateLocationDetails(vm.locationdetail).then(getdatafromupdateresponse).catch(function (err) {
                    vm.errornotfound = true;
                });
                
            };
        });

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




