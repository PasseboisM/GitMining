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

console.log(getCookie('cookiename'));

angular.module('main_app').factory('LoginService', ['$http', function ($http) {
	var url = "/GitMiningServer/repo";
	var list = function (getAttribute) {
		console.log("now change business");
		console.log(getAttribute);
		return $http({
			 method:'GET',
			 url:url,
			 params:getAttribute
			 });
    }
	var getTotal = function () {
		console.log("getTotal");
		return $http({
			 method:'GET',
			 url:url,
			 params:{type:"stat"}
			 });
    }
    var search = function (searchAttribute) {
    	console.log(searchAttribute);
    	return $http({
			 method:'GET',
			 url:url,
			 params:searchAttribute
			 });
    }

    return {
    	list: function (getAttribute) {
    		return list(getAttribute);
    	},
    	initial:function(){
    		return getTotal();
    	},
    	search:function(searchAttribute){
    		return search(searchAttribute);
    	}
    }
}]);
//app.controller('login_controller', ['$scope','$http',function($scope, $http) {
//
//	$scope.email="";
//	$scope.password="";
//	$scope.login = function(){
//		console.log(emial);
//		console.log(password);
//	}
//	/*var url = '/GitMiningServer/user';
//	data = {
//		type : "data",
//		method: "spec",
//		param: login
//	};
//
//	$http({
//		method:'GET',
//		url:url,
//		params:data
//	}).success(function(data) {
//		$scope.user = data;
//		console.log(data);
//		$scope.formattime = function(text) {
//			return text.replace("T"," ").replace("Z"," ");
//		};
//	});*/
//
//}]);