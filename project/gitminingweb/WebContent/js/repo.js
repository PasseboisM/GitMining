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

var app = angular.module('test', ['tm.pagination']);

 

app.controller('testCtrl', ['$scope', 'BusinessService', function ($scope, BusinessService) {
	var GetAllEmployee = function () {
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
        /***************************************************************
        当页码和页面记录数发生变化时监控后台查询
        如果把currentPage和itemsPerPage分开监控的话则会触发两次后台事件。 
        ***************************************************************/
        $scope.$watch('paginationConf.currentPage + paginationConf.itemsPerPage', GetAllEmployee);
    }]);
    //业务类
    app.factory('BusinessService', ['$http', function ($http) {
    	var list = function (postData) {
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