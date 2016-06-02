var languages = ["All","Java","Ruby","Python","C","JavaScript","Perl","PHP","C++","html","shell","Objective-C","VIML","C#","EmacsList","Erlang","Lua","Clojure","css","Haskell","Scala","CommonLisp","R","Others"];
var catagories = ["All","ActiveRecord","API","app","CMS","Django","Emacs","framework","interface","IRC","JSON","library","Linux","Mac","management","OS","plugin","Rails","Redis","server","source","template","TextMate","tool","Web","website","Others"];

function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var searchRepos;

/*var repotype=GetQueryString("type"); 
if(repotype!=null)	repotype = decodeURIComponent(repotype); 
else				repotype = "All"
var language=GetQueryString("lang"); 
if(language!=null)	language = decodeURIComponent(language); 
else				language = "All"*/

// $(document).ready(function() {

// });
var app = angular.module('main_app', ['tm.pagination']);
var isInitialStatus = true;
var searchRepos=[];


app.controller('main_ctrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	$scope.sorttype = "no";
	$scope.languages = languages;
	$scope.catagories = catagories;
    $scope.language = "All";
    $scope.catagory = "All";

    $scope.search = "";

	$scope.paginationConf = {
	    	currentPage: 1,
	    	itemsPerPage: 15
	    };
	function getReposInSpecialType(){
		console.log(searchRepos);
		$scope.paginationConf.totalItems = searchRepos.length;
		var start = ($scope.paginationConf.currentPage-1)*$scope.paginationConf.itemsPerPage;
		var end = $scope.paginationConf.currentPage*$scope.paginationConf.itemsPerPage;
		$scope.repos = searchRepos.slice(start,end);
	}
	var GetAllEmployee = function () {
		
		if(isInitialStatus){
			console.log("now get new repos");
			var getAttribute = {
				type:"data",
				method:"paged",
				page:$scope.paginationConf.currentPage,
				numPerPage:$scope.paginationConf.itemsPerPage,
				sort:$scope.sorttype
			}
					
			// if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search==""){
			BusinessService.initial().success(
				function(response) {
					$scope.paginationConf.totalItems = response.numOfRepo;
				});
			BusinessService.list(getAttribute).success(
				function(response) {
					$scope.repos=response;
				});
		}else{
			console.log("now get new repos in s type");
			var searchAttribute = {
				type:"data",
				method:"search",
				cates:[$scope.catagory],
				langs:[$scope.language],
				keywords:$scope.search.split(" "),
				sortStandard:$scope.sorttype
			};
			BusinessService.search(searchAttribute).success(
				function(response) {
					searchRepos=response;
				});
			getReposInSpecialType();
		}
	}
    
	

    $scope.getRepo = function(text) {
		console.log(text);
	};

    $scope.isActive={
    	isGen:true,
    	isStar:false,
    	isFork:false,
    };

    $scope.formattime = function(text) {
		return text.replace("T"," ").replace("Z"," ");
	};

	$scope.changecata = function(text) {
		console.log(text);
		$scope.catagory = text;
		$scope.paginationConf.currentPage = 1;
		if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search=="")
			isInitialStatus = true;
		else
			isInitialStatus = false;
		console.log(text);
	};
	
    $scope.changelang = function(text) {
    	console.log(text);
		$scope.language = text;
		$scope.paginationConf.currentPage = 1;
		if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search=="")
			isInitialStatus = true;
		else
			isInitialStatus = false;
		console.log(text);
	};


    $scope.changerepo = function(retype) {
    	$scope.sorttype = retype;
    	$scope.paginationConf.currentPage = 1
    	if(retype=="no"){
    		$scope.isActive.isGen=true;
    		$scope.isActive.isStar=false;
    		$scope.isActive.isFork=false;
    	}else if(retype=="stars"){
    		$scope.isActive.isStar=true;
    		$scope.isActive.isGen=false;
    		$scope.isActive.isFork=false;
    	}else if(retype=="forks"){
    		$scope.isActive.isFork=true;
    		$scope.isActive.isStar=false;
    		$scope.isActive.isGen=false;
    	}
    	console.log("now type has changed to "+retype);
    };
    /***************************************************************
    当页码和页面记录数发生变化时监控后台查询
    如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。 
    ***************************************************************/
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage + sorttype + language + catagory', GetAllEmployee);}]);
//业务类
app.factory('BusinessService', ['$http', function ($http) {
	var url = "/GitMiningServer/repo";
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






