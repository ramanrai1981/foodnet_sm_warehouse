(function () {
    'use strict';
    angular
        .module('farmerApp')
        .factory('MarketPlaceService', MarketPlaceService);

    MarketPlaceService.$inject = ['$http','$q'];

    function MarketPlaceService ($http,$q) {
    
    	var service = {
    		getMarketCommitteeList: getMarketCommitteeList,
    		marketPlaceCommodityReceived:addNewCommodityReceived,
    		getMarketPlaceToken:getMarketPlaceToken,
    		getMarketPlaceReceivedCommodityList:getMarketPlaceReceivedCommodityList ,
    		getMarketPlaceReceivedCommodityIdByToken:getMarketPlaceReceivedCommodityIdByToken,
    		marketPlaceCommodityReceivedLot:marketPlaceCommodityReceivedLot,
    		getMarketPlaceReceivedCommodityLotInfo:getMarketPlaceReceivedCommodityLotInfo
        };
    	return service;
    	
    	/* 
    	 * Call API to get all Mandis
    	 */
    	function getMarketCommitteeList(){
    		var deferred = $q.defer();
    		$http.get('api/marketcommittees ').success(function (response) {
    			//console.log("SUCCESS RESPONSE : "+response);
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
    	function addNewCommodityReceived(rec){
    		var data = rec;
    		console.log(data); 
	        return $http.post('api/marketPlaceCommodityReceived', data, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	        	console.log(response);
	        	//console.log(response);
				//console.log('=======================.'+response.id);
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
    	/* 
    	 * Call API to get token by ID
    	 */
    	function getMarketPlaceToken(id){    		
    		return $http.get('api/marketPlaceCommodityReceived/token/'+id, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	        	//console.log(response);
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
    	/* 
    	 * Call API to get received commodity info by ID
    	 */
    	function getMarketPlaceReceivedCommodityList(MID){
    		return $http.get('api/marketPlaceCommodityReceived/'+MID, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	        	//console.log(response);
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
    	
    	/* 
    	 * Call API to get Market place ID by Token
    	 */
    	function getMarketPlaceReceivedCommodityIdByToken(id){    		
    		return $http.get('api/marketPlaceCommodityReceived/getMarketPlaceIdByToken/'+id, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
    	/* 
    	 * Call API to add lots
    	 */
    	function marketPlaceCommodityReceivedLot(rec){
    		var data = rec;
    		console.log(data); 
	        return $http.post('api/lots', data, {
	            headers: {
	                'Content-Type': 'application/JSON'
	            }
	        }).success(function (response) {
	        	console.log(response);
	        	//console.log(response);
				//console.log('=======================.'+response.id);
	            return response;
	        }).error(function(response){
            	console.log("ERROR :: "+response.description);
           });
    	}
    	
    	/* 
    	 * Call API to get lots
    	 */
    	function getMarketPlaceReceivedCommodityLotInfo(id){
    		return $http.get('api/lots/'+id, {
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

