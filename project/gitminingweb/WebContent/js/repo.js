var repositories1 = [{fullName:"haha/ss",describe:"sldkfjslkdjflskdjflskdjflskdjflskdjf",
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
lastUpdate:"2016-02-07",stars:0,forks:0,contributors:0}];

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
    
	var GetAllEmployee = function () {
		$scope.paginationConf = {
		    	currentPage: 1,
		    	itemsPerPage: 5
		    };
		console.log("now get new repos");
		var postData = {
			type:"data",
			method:"paged",
//			page:1,
//			numPerPage:1,
			page: $scope.paginationConf.currentPage,
			numPerPage: $scope.paginationConf.itemsPerPage,
			sort:"no"
		}
		
		$scope.paginationConf.totalItems = 16;
		BusinessService.list(postData,$scope);
		
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

    $scope.changelang = function(text) {
		language = text;
		console.log(language);
		window.location.href = "test.html?type="+repotype+"&lang="+language;
	};


    $scope.changerepo = function(retype) {
    	$scope.type = retype;
    	$scope.paginationConf.currentPage = 1
    	if(retype==1){
    		$scope.isActive.isGen=true;
    		$scope.isActive.isStar=false;
    		$scope.isActive.isFork=false;
    	}else if(retype==2){
    		$scope.isActive.isStar=true;
    		$scope.isActive.isGen=false;
    		$scope.isActive.isFork=false;
    	}else if(retype==3){
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
	var list = function (postData,$scope) {
		console.log("now change business");
		$http({
			 method:'GET',
			 url:"/GitMiningServer/repo",
			 params:postData
			 }).success(function(response) {$scope.repos = response;console.log($scope.repos);});
		/*$http.get("http://106.75.5.61:8080/GitMiningServer/repo?type=data&method=paged&page=1&numPerPage=2&sort=no")
	    .success(function(response) {console.log(1);});*/
		var repodata;
    	$(document).ready(function() {
    		var url = "/GitMiningServer/repo"
    		$.ajax(url, {
    			type : 'GET',
    			data : postData,
    			success : function(data) {
    				repodata=data;
    				console.log(repodata);
    			}
    		});
		});
    	
    	return repodata;
    }
    return {
    	list: function (postData,$scope) {
    		return list(postData,$scope);
    	}
    }
}]);





