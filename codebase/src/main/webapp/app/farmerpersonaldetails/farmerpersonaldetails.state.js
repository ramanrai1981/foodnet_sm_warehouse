(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('farmerpersonaldetails', {
            parent: 'app',
            url: '/farmerpersonaldetails',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/farmerpersonaldetails/farmerpersonaldetails.html',
                    controller: 'FarmerPersonalDetailsController',
                    controllerAs: 'vm'
                }
            }, 
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('farmer');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
