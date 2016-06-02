user1=[{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27},
{login:"haha",location:"LA",name:"HaHa",follower:25,following:1,repos:2},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:2,following:185,repos:27}];
user2=[{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0},
{login:"haha",location:"LA",name:"HaHa",follower:0,following:0,repos:0},
{login:"excuse miao",location:"YKHM",name:"Neko",follower:0,following:0,repos:0}];
var isInitialStatus = true;
var app = angular.module('test', ['tm.pagination']);
app.controller('testCtrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	$scope.paginationConf = {
	    	currentPage: 1,
	    	itemsPerPage: 15
	    };
	$scope.paginationConf.totalItems = 16;
	var GetAllEmployee = function () {
		
		if(isInitialStatus){
			console.log("now get new repos");
			var getAttribute = {
				type:"data",
				method:"paged",
				page:$scope.paginationConf.currentPage,
				numPerPage:$scope.paginationConf.itemsPerPage,
				sort:"no"
			}
					
			// if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search==""){
			/*BusinessService.initial().success(
				function(response) {
					$scope.paginationConf.totalItems = response.numOfRepo;
				});*/
			$scope.users = BusinessService.list(getAttribute)
			/*.success(
				function(response) {
					$scope.repos=response;
				});*/
		}else{
			console.log("now get new repos in s type");
			var searchAttribute = {
				cata:$scope.catagory,
				lang:$scope.language,
				keyword:$scope.search
			};
			BusinessService.search(searchAttribute);
			getReposInSpecialType();
		}
	}
	$scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', GetAllEmployee);

}]);//业务类
app.factory('BusinessService', ['$http', function ($http) {
	var url = "/GitMiningServer/user";
	var list = function (getAttribute) {
		/*console.log("now change business");
		console.log(getAttribute);
		return $http({
			 method:'GET',
			 url:url,
			 params:getAttribute
			 });*/
		if(getAttribute.page%2==0)	return user2;
		else								return user1;
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






