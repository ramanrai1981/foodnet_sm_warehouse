(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehousereceiptbypa', {
            parent: 'app',
            url: '/warehousereceiptbypa',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehousereceiptbypa/warehousereceiptbypa.html',
                    controller: 'WarehouseReceiptByPAController',
                    controllerAs: 'warehousereceiptbypavm'
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
