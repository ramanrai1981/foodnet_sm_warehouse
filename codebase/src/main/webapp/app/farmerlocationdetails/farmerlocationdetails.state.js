(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('farmerlocationdetails', {
            parent: 'app',
            url: '/farmerlocationdetails',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/farmerlocationdetails/farmerlocationdetails.html',
                    controller: 'FarmerLocationDetailsController',
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
