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

$(document).ready(function() {
});

var repotype=$("#x").text();
var language=$("#chooselan").text();

console.log(repotype);

$(".othertype").each(function() {
	var text = $(this).text();

	$(this).click(function() {
		repotype = text;
		console.log(repotype);
		window.location.href = "#";
	});
});

$(".otherlan").each(function() {
	var text = $(this).text();

	$(this).click(function() {
		language = text;
		console.log(language);
		window.location.href = "#";
	});
});


var app = angular.module('test', ['tm.pagination']);



app.controller('testCtrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	var GetAllEmployee = function () {
		console.log("now get new repos");
		var postData = {
			pageIndex: $scope.paginationConf.currentPage,
			pageSize: $scope.paginationConf.itemsPerPage
		}
		// BusinessService.list(postData).success(function (response) {

		$scope.paginationConf.totalItems = 16;
		// window.alert(response);
		$scope.repos = BusinessService.list(postData);
		// });
	}
    //配置分页基本参数
    $scope.paginationConf = {
    	currentPage: 1,
    	itemsPerPage: 5
    };

    $scope.isActive={
    	isGen:true,
    	isStar:false,
    	isFork:false,
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
	var list = function (postData) {
		console.log("now change business");
    	// return $http.post('/Employee/GetAllEmployee', postData);
    	if(postData.pageIndex%2==0)	return repositories2;
    	else	return repositories1;
    }
    return {
    	list: function (postData) {
    		return list(postData);
    	}
    }
}]);