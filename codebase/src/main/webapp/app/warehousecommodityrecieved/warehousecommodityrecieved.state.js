(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('warehousecommodityrecieved', {
            parent: 'app',
            url: '/warehousecommodityrecieved',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/warehousecommodityrecieved/warehousecommodityrecieved.html',
                    controller: 'WarehouseCommodityRecievedController',
                    controllerAs: 'warehouseinwardlotvm'
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
