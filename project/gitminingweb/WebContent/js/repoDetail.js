


var app = angular.module('repo_app', []);


app.controller('repo_ctrl', function($scope, $http) {

  var url = '/GitMiningServer/repository';
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
		$scope.repo = data;
		console.log(data);
		$scope.formattime = function(text) {
			return text.replace("T"," ").replace("Z"," ");
		};
	});


});
