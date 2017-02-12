(function () {
    'use strict';

    angular
            .module('farmerApp')
            .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('stackmanagement', {
            parent: 'app',
            url: '/stackmanagement',
            data: {
                authorities: []
            },
            views: {
                'content@': {
                    templateUrl: 'app/stackmanagement/stackmanagement.html',
                    controller: 'StackManagementController',
                    controllerAs: 'stackmanagementvm'
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
