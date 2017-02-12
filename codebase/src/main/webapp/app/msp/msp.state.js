(function() {
    'use strict';

    angular
        .module('farmerApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('msp', {
            parent: 'app',
            url: '/msp',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/msp/msp.html',
                    controller: 'MspController',
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
