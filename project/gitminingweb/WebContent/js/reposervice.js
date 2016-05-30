var app = angular.module('test', [tm.pagination]);
app.factory('BusinessService', ['$http', function ($http) {
	var list = function (postData) {
		console.log("now change business");
		console.log(repotype);
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