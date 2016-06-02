var app = angular.module('test', ['tm.pagination']);
app.controller('testCtrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	$scope.paginationConf = {
	    	currentPage: 1,
	    	itemsPerPage: 15
	    };
	$scope.paginationConf.totalItems = 16;

}]);//业务类
app.factory('BusinessService', ['$http', function ($http) {
	var url = "/GitMiningServer/user";
	var list = function (getAttribute) {
		console.log("now change business");
		console.log(getAttribute);
		return $http({
			 method:'GET',
			 url:url,
			 params:getAttribute
			 });
    }
	var getTotal = function () {
		console.log("getTotal");
		return $http({
			 method:'GET',
			 url:url,
			 params:{type:"stat"}
			 });
    }
    var search = function (searchAttribute) {
    	console.log(searchAttribute);
    }

    return {
    	list: function (getAttribute) {
    		return list(getAttribute);
    	},
    	initial:function(){
    		return getTotal();
    	},
    	search:function(searchAttribute){
    		return search(searchAttribute);
    	}
    }
}]);






