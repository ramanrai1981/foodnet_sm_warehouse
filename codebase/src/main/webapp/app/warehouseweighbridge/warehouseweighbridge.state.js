(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehouseweighbridge', {
            parent: 'app',
            url: '/warehouseweighbridge',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehouseweighbridge/warehouseweighbridge.html',
                    controller: 'WarehouseWeighbridgeController',
                    controllerAs: 'warehouseweighbridgevm'
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
