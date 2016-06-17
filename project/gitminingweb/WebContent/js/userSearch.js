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
	if(searchAttribute.key.length>0)
		http_attributes.key=searchAttribute.key;
	return http_attributes;
}
var isInitialStatus = true;
var hasNewSearchQuest = false;
var app = angular.module('main_app', ['tm.pagination']);
app.controller('testCtrl', ['$scope', 'BusinessService','LoginService', 'TopService',function ($scope, BusinessService,LoginService,TopService) {
	$scope.email = LoginService.get_cookie("email");
    $(document).ready(function(){
    	if($scope.email.length>0){
    		$("#logout_div").show();
    		$("#wrong_msg").hide();
    		$("#login_div").hide();
    	}else{
    		$("#login_div").show();
    		$("#logout_div").hide();
    		$("#wrong_msg").hide();
    	}
    });
    $scope.password = "";
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
	$scope.login = function(){
		param={
			login:$scope.email,
			pass:$scope.password
		}
		LoginService.login(param).success(
			function(response) {
				console.log(response);
				if(response.state){
					LoginService.save_cookie("key",response.key);
					LoginService.save_cookie("email",$scope.email);
					$("#login_div").hide();
					$("#wrong_msg").hide();
					$("#logout_div").show();
					//show user info
				}else{
					//show alert info
					console.log("hey sth. wrong!");
					$("#wrong_msg").show();
					$scope.email="";
					$scope.password="";
				}
			});
	}
	$scope.logout = function(){
		LoginService.del_cookie("key");
		LoginService.del_cookie("email");
		$scope.email="";
		$scope.password="";
		$("#logout_div").hide();
		$("#wrong_msg").hide();
		$("#login_div").show();
	}
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
			var key = LoginService.get_cookie("key");
			if(key.length>0)
				getAttribute.key=LoginService.get_cookie("key");
					
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
				sortStandard:$scope.sort_type,
				key:LoginService.get_cookie("key")
			};
			BusinessService.search(transParams(searchAttribute)).success(
				function(response) {
					searchUsers=response;
					hasNewSearchQuest = false;
					getReposInSpecialType();
				});
			getReposInSpecialType();
		}

		RecommendAttribute={
			type:"user",
			param:"All"
		}
		TopService.getTop25(RecommendAttribute).success(
			function(response) {
				$scope.recommend_users=response;
			});


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






