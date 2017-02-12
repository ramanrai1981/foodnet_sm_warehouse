(function() {
    'use strict';

    angular
        .module('farmerApp')
        .controller('SideNavbarController', SideNavbarController);

    SideNavbarController.$inject = ['$state', 'Auth', 'Principal', 'ProfileService', 'LoginService'];

    function SideNavbarController ($state, Auth, Principal, ProfileService, LoginService) {
        var vm = this;

        vm.isNavbarCollapsed = true;
        vm.isAuthenticated = Principal.isAuthenticated;

        ProfileService.getProfileInfo().then(function(response) {
            vm.inProduction = response.inProduction;
            vm.swaggerDisabled = response.swaggerDisabled;
        });

        vm.login = login;
        vm.logout = logout;
      
        vm.$state = $state;

        function login() {
            collapseNavbar();
            LoginService.open();
        }

        function logout() {
            collapseNavbar();
            Auth.logout();
            $state.go('home');
        }

       
    }
})();
