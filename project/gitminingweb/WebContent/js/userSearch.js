var sort_type = ["no","follower"];
var http_sort_type = ["NO_SORT","FOLLOWER_DESCENDING"];
var searchUsers=[];
function transParams(searchAttribute){
	var http_attributes = {
		type:"data",
		method:"search",
		param:{}
	};
	http_attributes.param.sortStandard = http_sort_type[sort_type.indexOf(searchAttribute.sortStandard)];
	http_attributes.param.keywords = searchAttribute.keywords;
	return http_attributes;
}
var isInitialStatus = true;
var hasNewSearchQuest = false;
var app = angular.module('test', ['tm.pagination']);
app.controller('testCtrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	$scope.search = "";
	$scope.sort_type = "no";
	$scope.paginationConf = {
	    	currentPage: 1,
	    	itemsPerPage: 15
	    };
	function getReposInSpecialType(){
		$scope.paginationConf.totalItems = searchUsers.length;		
		var start = ($scope.paginationConf.currentPage-1)*$scope.paginationConf.itemsPerPage;
		var end = $scope.paginationConf.currentPage*$scope.paginationConf.itemsPerPage;
		$scope.users = searchUsers.slice(start,end);
	}
	$scope.searchUsers = function() {
    	console.log($scope.search);
    	hasNewSearchQuest = true;
		$scope.paginationConf.currentPage = 1;
		if($scope.search=="")
			isInitialStatus = true;
		else
			isInitialStatus = false;
		GetAllEmployee();
	};
	var GetAllEmployee = function () {
		
		if(isInitialStatus){
			console.log("now get new repos");
			var getAttribute = {
				type:"data",
				method:"paged",
				page:$scope.paginationConf.currentPage,
				numPerPage:$scope.paginationConf.itemsPerPage,
				sort:$scope.sort_type
			}
					
			// if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search==""){
			BusinessService.initial().success(
				function(response) {
					$scope.paginationConf.totalItems = response.numOfUser;
				});
			BusinessService.list(getAttribute).success(
				function(response) {
					$scope.users=response;
				});
		}else{
			if (!hasNewSearchQuest) {
				getReposInSpecialType();
				return;
			}
			console.log("now get new users in search type");
			var searchAttribute = {
				keywords:$scope.search.split(" "),
				sortStandard:$scope.sort_type
			};
			BusinessService.search(transParams(searchAttribute)).success(
				function(response) {
					searchUsers=response;
					hasNewSearchQuest = false;
					getReposInSpecialType();
				});
			getReposInSpecialType();
		}
	}

	$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', GetAllEmployee);

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
    	return $http({
			 method:'GET',
			 url:url,
			 params:searchAttribute
			 });
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






