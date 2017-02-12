(function () {
    'use strict';
    angular
        .module('farmerApp')
        .factory('CommodityService', CommodityService);

    CommodityService.$inject = ['$http','$q'];

    function CommodityService ($http,$q) {
    	var service = {
    			getCommodityList: getCommodityList,
    			saveRecord:saveRecord
        };
    	return service;
    	
    	/* 
    	 * Call API to get all Commodities
    	 */
    	function getCommodityList(){
    		//console.log("COMMODITY SUCCESS RESPONSE : ");
    		var deferred = $q.defer();
    		$http.get('api/commodities').success(function (response) {
    			console.log("COMMODITY SUCCESS RESPONSE : "+response);
    			deferred.resolve(response);
            }).error(function(){
            	console.log("ERROR");
                deferred.reject(error);
           });
           return deferred.promise;           
        } 
    	
    	/* 
    	 * Call API to add new Mandi
    	 */
    	function saveRecord(rec){
    		var data = rec;
    		console.log(data);
	        return $http.post('api/gatepasses', data, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	        	console.log(response);
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
    	
        
    }
})();

