(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehousequality', {
            parent: 'app',
            url: '/warehousequality',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehousequality/warehousequality.html',
                    controller: 'WarehouseQualityController',
                    controllerAs: 'warehousequalityvm'
                }
            }, 
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('warehouse');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
