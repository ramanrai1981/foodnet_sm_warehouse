(function () {
    'use strict';

    angular
            .module('farmerApp')
            .controller('Sell2Controller', Sell2Controller);

    Sell2Controller.$inject = ['$scope', 'Principal', 'Sell2', 'Sell3', 'SellSave', '$state', '$resource','DateUtils'];

    function Sell2Controller($scope, Principal, Sell2, Sell3, SellSave, $state, $resource,DateUtils) {
        var vm = this;

        vm.account = null;
        vm.isAuthenticated = null;

        vm.register = register;


        vm.sellingcommodity = [];

        vm.savesellform = function () {
            vm.sellingcommodity.farmerid="737efd22-3df0-11e6-ac61-9e71128cae77";
            alert(vm.sellingcommodity.farmerid);
            alert(vm.sellingcommodity.commodityshortcode);
            alert(vm.sellingcommodity.approximateweight);
            alert(vm.sellingcommodity.placeshortcode);
            alert(vm.sellingcommodity.expectedrate);
          
            SellSave.save(vm.sellingcommodity, onSaveSuccess, onSaveError);

        };
        
        function onSaveSuccess(data, headers) {
          
            alert("success");
        }
        function onSaveError(error) {
            alert("error" + error);
        }



        $scope.$on('authenticationSuccess', function () {
            getAccount();

        });

        getAccount();
        Sell2.query(onSuccess, onError);

        function onSuccess(data, headers) {
            vm.data = data;
            alert("in Comm");
        }
        function onError(error) {
            alert("in Comm Error");
        }

        Sell3.query(onSuccess2, onError2);

        function onSuccess2(data, headers) {
            vm.dataplace = data;
            alert("success");
        }
        function onError2(error) {
            alert("error" + error);
        }


        

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




