(function () {
    'use strict';

    angular
            .module('farmerApp')
            .controller('MspController', MspController);

    MspController.$inject = ['$scope', 'Principal', '$state', 'MspService'];

    function MspController($scope, Principal, $state, MspService) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;
        vm.data = "";
        vm.register = register;

        vm.colorcode = function (commodity) {
            if (commodity === 'Wheat') {
                return  "widget-content padding-30 bg-blue-600";
            } else if (commodity === 'Rice') {
                return  "widget-content padding-30 bg-red-600";
            } else if (commodity === 'Cotton') {
                return  "widget-content padding-30 bg-purple-600";
            } else {
                return  "widget-content padding-30 bg-green-600";
            }
        };

        MspService.getMspCurrentYear().then(getdatafromresponse).catch(function (err) {
            vm.errornotfound = true;
        });

        function getdatafromresponse(mspcurentyeardto) {
            vm.mspcurrentyear = mspcurentyeardto.data;
        }



        $scope.$on('authenticationSuccess', function () {
            getAccount();

        });

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
        function register() {
            $state.go('register');
        }
    }
})();




