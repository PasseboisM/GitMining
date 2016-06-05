function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var login=GetQueryString("login"); 
console.log(login);
var formattime = function(text) {
	return text.replace("T"," ").replace("Z"," ");
};
// if(repotype!=null)	repotype = decodeURIComponent(repotype); 
// else				repotype = null;
var app = angular.module('detail_app', []);
app.controller('detail_controller', function($scope, $http) {
	var url = '/GitMiningServer/user', 
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
	});

});