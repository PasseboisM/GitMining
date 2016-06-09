var languages = ["All","Java","Ruby","Python","C","JavaScript","Perl","PHP","C++","html","shell","Objective-C","VIML","C#","EmacsLisp","Erlang","Lua","Clojure","css","Haskell","Scala","CommonLisp","R","Others"];
var catagories = ["All","ActiveRecord","API","app","CMS","Django","Emacs","framework","interface","IRC","JSON","library","Linux","Mac","management","OS","plugin","Rails","Redis","server","source","template","TextMate","tool","Web","website","Others"];
var sorttypes = ["no","stars","forks"];


var http_languages = ["ALL","JAVA","RUBY","PYTHON","C","JAVA_SCRIPT","PERL","PHP","C_PLUS_PLUS","HTML","SHELL","OBJECTIVE_C","VIML","C_SHARP","EMACS_LISP","ERLANG","LUA","CLOJURE","CSS","HASKELL","SCALA","COMMON_LISP","R","OTHERS"];
var http_catagories = ["ALL","ACTIVE_RECORD","API","APP","CMS","DJANGO","EMACS","FRAMEWORK","INTERFACE","IRC","JSON","LIBRARY","LINUX","MAC","MANAGEMENT","OS","PLUGIN","RAILS","REDIS","SERVER","SOURCE","TEMPLATE","TEXT_MATE","TOOL","WEB","WEBSITE","OTHERS"];
var http_sorttypes = ["NO_SORT","STARS_DESCENDING","FORKS_DESCENDING"];


$(document).ready(function(){
	$("#login_div").show();
	$("#logout_div").hide();
});



// $(document).ready(function() {

// });
var app = angular.module('main_app', ['tm.pagination']);

// angular.module('myApp')
//app.config(['$httpProvider', function($httpProvider) {
//  $httpProvider.defaults.withCredentials = true;
//}])

var isInitialStatus = true;
var hasNewSearchQuest = false;

var searchRepos=[];

function transParams(searchAttribute){
	var http_attributes = {
		type:"data",
		method:"search",
		param:{}
	};
	http_attributes.param.cates = [http_catagories[catagories.indexOf(searchAttribute.cates)]];
	http_attributes.param.langs = [http_languages[languages.indexOf(searchAttribute.langs)]];
	http_attributes.param.sortStandard = http_sorttypes[sorttypes.indexOf(searchAttribute.sortStandard)];
	http_attributes.param.keywords = searchAttribute.keywords;
	if(document.cookie.length>0)
		http_attributes.key=document.cookie;
	return http_attributes;
}

app.controller('main_ctrl', ['$scope', 'BusinessService','LoginService', function ($scope, BusinessService,LoginService) {
	$scope.sorttype = "no";
	$scope.languages = languages;
	$scope.catagories = catagories;
    $scope.language = "All";
    $scope.catagory = "All";
    // $scope.hasLogIn = LoginService.get_cookie("key").length>0;
    $scope.hasLogIn = false;

    $scope.search = "";
    $scope.email = "";
    $scope.password = "";

	$scope.paginationConf = {
	    	currentPage: 1,
	    	itemsPerPage: 15
	    };
	function getReposInSpecialType(){
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

			if(document.cookie.length>0)
				getAttribute.key=document.cookie;
					
			BusinessService.initial().success(
				function(response) {
					$scope.paginationConf.totalItems = response.numOfRepo;
				});
			BusinessService.list(getAttribute).success(
				function(response) {
					$scope.repos=response;
				});
		}else{
			// if($scope.search=="")	return;
			if (!hasNewSearchQuest) {
				getReposInSpecialType();
				return;
			}
			console.log("now get new repos in s type");
			var searchAttribute = {
				type:"data",
				method:"search",
				cates:$scope.catagory,
				langs:$scope.language,
				keywords:$scope.search.split(" "),
				sortStandard:$scope.sorttype
			};

			

			BusinessService.search(transParams(searchAttribute)).success(
				function(response) {
					searchRepos=response;
					hasNewSearchQuest = false;
					getReposInSpecialType();
				});
			
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
		hasNewSearchQuest = true;
		console.log(text);
		$scope.catagory = text;
		$scope.paginationConf.currentPage = 1;
		if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search=="")
			isInitialStatus = true;
		else
			isInitialStatus = false;
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
					LoginService.save_cookie(response.key);
					$("#login_div").hide();
					$("#logout_div").show();
					//show user info
				}else{
					//show alert info
					console.log("hey sth. wrong!");
					$scope.email="";
					$scope.password="";
				}
			});
	}
	
    $scope.changelang = function(text) {
    	hasNewSearchQuest = true;
    	console.log(text);
		$scope.language = text;
		$scope.paginationConf.currentPage = 1;
		if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search=="")
			isInitialStatus = true;
		else
			isInitialStatus = false;
	};

	$scope.searchRepos = function() {
    	console.log($scope.search);
    	hasNewSearchQuest = true;
		$scope.paginationConf.currentPage = 1;
		if($scope.language=="All"&&$scope.catagory=="All"&&$scope.search=="")
			isInitialStatus = true;
		else
			isInitialStatus = false;
		GetAllEmployee();
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
    /*var changeState = function () {
    	hasNewSearchQuest = true;
    }*/
    /***************************************************************
    当页码和页面记录数发生变化时监控后台查询
    如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。 
    ***************************************************************/
    // $scope.$watch('search',changeState);
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage + sorttype + language + catagory', GetAllEmployee);}]);
//业务类
app.factory('BusinessService', ['$http', function ($http) {
	var url = "/GitMiningServer/repo";
	var list = function (getAttribute) {
		console.log("now change business");
		console.log(getAttribute);
		console.log($http.defaults.headers);
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






