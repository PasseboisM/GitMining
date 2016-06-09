function GetQueryString(name) {
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
var r = window.location.search.substr(1).match(reg);
if (r!=null) return (r[2]); return null;
}

var repoName=GetQueryString("fn");
var app = angular.module('repo_app', []);


app.controller('repo_ctrl', function($scope, $http) {

	$scope.email = "";
    $scope.password = "";
  	var url = '/GitMiningServer/repo';
	data = {
		type : "data",
		method: "spec",
		param: repoName
	};

	

	$http({
		method:'GET',
		url:url,
		params:data
	}).success(function(data) {
		$scope.repo = data;
		console.log(data);
		$scope.formattime = function(text) {
			return text.replace("T"," ").replace("Z"," ");
		};
	});


});
