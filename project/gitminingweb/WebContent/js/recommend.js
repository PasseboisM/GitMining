angular.module('main_app').factory('TopService', ['$http', function ($http) {
	var url = "/GitMiningServer/recommend";
	var list = function (getAttribute) {
		console.log(getAttribute);
		return $http({
			method:'GET',
		 	url:url,
			params:getAttribute
			});
    }

    return {
    	getTop25: function (getAttribute) {
    		return list(getAttribute);
    	}
    }
}]);