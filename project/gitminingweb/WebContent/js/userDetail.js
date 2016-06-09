function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var login=GetQueryString("login"); 

var app = angular.module('main_app', []);
app.controller('detail_controller','LoginService', function($scope, $http,LoginService) {
	$scope.email = "";
    $scope.password = "";
	var url = '/GitMiningServer/user';
	data = {
		type : "data",
		method: "spec",
		param: login
	};

	if(document.cookie.length>0)
		data.key=LoginService.get_cookie("key");

	$http({
		method:'GET',
		url:url,
		params:data
	}).success(function(data) {
		$scope.user = data;
		console.log(data);
		$scope.formattime = function(text) {
			return text.replace("T"," ").replace("Z"," ");
		};
	});

});