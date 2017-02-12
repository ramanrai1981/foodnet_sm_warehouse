(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('sell2', {
            parent: 'app',
            url: '/sell2',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/sell/sell.html',
                    controller: 'Sell2Controller',
                    controllerAs: 'vm'
                }
            }, 
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                        $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        });
    }
})();
