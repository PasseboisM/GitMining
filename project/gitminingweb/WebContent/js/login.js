function setCookie(name,value,expires){
document.cookie = name + "=" + value + ((expires==null) ? "" : ";expires=" + expires.toGMTString())
}

var expirydate=new Date();
expirydate.setTime(expirydate.getTime()+(100*60*60*24*100))
setCookie('cookiename','cookiedata',expirydate)
// expirydate being a variable with the expiry date in it
// the one i have set for your convenience expires in 10 days

function getCookie(name) {
var cookieName = name + "="
var docCookie = document.cookie
var cookieStart
var end
if (docCookie.length>0) {
cookieStart = docCookie.indexOf(cookieName)
if (cookieStart != -1) {
cookieStart = cookieStart + cookieName.length
end = docCookie.indexOf(";",cookieStart)
if (end == -1) {
end = docCookie.length}
return unescape(docCookie.substring(cookieStart,end))
}
}
return false
}

console.log(getCookie('cookiename'))

var app = angular.module('main_app', []);
app.controller('login_controller', function($scope, $http) {

	$scope.email="";
	$scope.password="";
	$scope.login = function(){
		console.log(emial);
		console.log(password);
	}
	/*var url = '/GitMiningServer/user';
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
	});*/

});