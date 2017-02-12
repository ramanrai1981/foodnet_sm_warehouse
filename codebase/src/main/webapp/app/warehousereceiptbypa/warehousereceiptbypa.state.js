(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehousereceipt', {
            parent: 'app',
            url: '/warehousereceipt',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehousereceipt/warehousereceipt.html',
                    controller: 'WarehouseReceiptController',
                    controllerAs: 'warehousereceiptvm'
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
