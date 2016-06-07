var strCookie=document.cookie;
console.log(strCookie);

var app = angular.module('main_app', []);
app.controller('login_controller', function($scope, $http) {

	$scope.email="";
	$scope.password="";
	$scope.login = function(){
		console.log(emial);
		console.log(password);
	}
	var url = '/GitMiningServer/user';
	data = {
		type : "data",
		method: "spec",
		param: login
	};

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