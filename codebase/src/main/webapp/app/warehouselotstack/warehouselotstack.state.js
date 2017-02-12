(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehouselotstack', {
            parent: 'app',
            url: '/warehouselotstack',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehouselotstack/warehouselotstack.html',
                    controller: 'WarehouseLotStackController',
                    controllerAs: 'warehouselotstackvm'
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
