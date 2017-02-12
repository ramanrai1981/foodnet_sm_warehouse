(function () {
    'use strict';
    angular
        .module('farmerApp')
        .factory('QualityService', QualityService);

    QualityService.$inject = ['$http'];

    function QualityService ($http) {
    
    	var service = {
    			getCommodityQuality: getCommodityQuality
        };
    	return service;
    	
    	
    	
    	/* 
    	 * Call API to get Quality by Commodity ID
    	 */
    	function getCommodityQuality(commodity_id) {    		
    		return $http.get('api/commodityQuality/getQualityByCommodityId/'+commodity_id, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
        
    }
})();

