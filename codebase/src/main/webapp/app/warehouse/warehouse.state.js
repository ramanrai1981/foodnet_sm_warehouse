(function () {
    'use strict';

    angular
            .module('farmerApp')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehouse', {
            parent: 'app',
            url: '/warehouse',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehouse/warehouse.html',
                    controller: 'WarehouseController',
                    controllerAs: 'warehousevm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('warehouse');
                        return $translate.refresh();
                    }]
            }
        });
    }
})();
