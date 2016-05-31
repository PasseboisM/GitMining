/*var repositories1 = [{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2},
{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:58,forks:79,contributors:18},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:3,forks:12,contributors:2}];

var repositories2 = [{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:0,forks:0,contributors:0},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0},{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:0,forks:0,contributors:0},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0},{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:0,forks:0,contributors:0},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0},{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:0,forks:0,contributors:0},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0},{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:0,forks:0,contributors:0},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0},{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
lastUpdate:"2014-09-08",stars:0,forks:0,contributors:0},
{fullName:"hdddd/soiusdf",describe:"ooowowoow",
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0}];*/

var lang = ["All","Java","Ruby","Python","C","JavaScript","Perl","PHP","C++","html","shell","Objective-C","VIML","C#","EmacsList","Erlang","Lua","Clojure","css","Haskell","Scala","CommonLisp","R","Others"];
var cata = ["All","ActiveRecord","API","app","CMS","Django","Emacs","framework","interface","IRC","JSON","library","Linux","Mac","management","OS","plugin","Rails","Redis","server","source","template","TextMate","tool","Web","website","Others"];

function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var repotype=GetQueryString("type"); 
if(repotype!=null)	repotype = decodeURIComponent(repotype); 
else				repotype = "All"
var language=GetQueryString("lang"); 
if(language!=null)	language = decodeURIComponent(language); 
else				language = "All"

// $(document).ready(function() {

// });
var app = angular.module('test', ['tm.pagination']);




app.controller('testCtrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	//配置分页基本参数
	$scope.type = "no";
	$scope.paginationConf = {
	    	currentPage: 1,
	    	itemsPerPage: 15
	    };
	var GetAllEmployee = function () {
		
		console.log("now get new repos");
		var postData = {
			type:"data",
			method:"paged",
			page:$scope.paginationConf.currentPage,
			numPerPage:$scope.paginationConf.itemsPerPage,
			sort:$scope.type
		}
		
		
		BusinessService.initial().success(
			function(response) {
				$scope.paginationConf.totalItems = response.numOfRepo;
			});
		BusinessService.list(postData).success(
			function(response) {
				$scope.repos=response;
			});
		
	}
    
	$scope.langs = lang;
	$scope.catas = cata;
    $scope.language = language;
    $scope.repotype = repotype;

    $scope.search = "";

    $scope.getRepo = function(text) {
		console.log(text);
	};

    $scope.isActive={
    	isGen:true,
    	isStar:false,
    	isFork:false,
    };


	$scope.changetype = function(text) {
		repotype = text;
		console.log(language);
		window.location.href = "test.html?type="+repotype+"&lang="+language;
	};
	$scope.formattime = function(text) {
		return text.replace("T"," ").replace("Z"," ");
	};
    $scope.changelang = function(text) {
		language = text;
		console.log(language);
		window.location.href = "test.html?type="+repotype+"&lang="+language;
	};


    $scope.changerepo = function(retype) {
    	$scope.type = retype;
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
    $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage + type', GetAllEmployee);}]);
//业务类
app.factory('BusinessService', ['$http', function ($http) {
	var url = "/GitMiningServer/repo";
	var list = function (postData) {
		console.log("now change business");
		console.log(postData);
		return $http({
			 method:'GET',
			 url:url,
			 params:postData
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

    return {
    	list: function (postData) {
    		return list(postData);
    	},
    	initial:function(){
    		return getTotal();
    	}
    }
}]);






