function setCookie(name,value,expires){
    document.cookie = name + "=" + value + ((expires==null) ? "" : ";expires=" + expires.toGMTString())
}

// var expirydate=new Date();
// expirydate.setTime(expirydate.getTime()+(100*60*60*24*100))
// setCookie('cookiename','cookiedata',expirydate)
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
        return ""
    }

function deleteCookie(name){ 
    var date=new Date(); 
    date.setTime(date.getTime()-10000); 
    document.cookie=name+"=v; expires="+date.toGMTString(); 
} 


angular.module('main_app').factory('LoginService', ['$http', function ($http) {
	var url = "/GitMiningServer/login";
	var list = function (getAttribute) {
		console.log(getAttribute);
		return $http({
			 method:'GET',
			 url:url,
			 params:getAttribute
			 })
    }


    return {
    	login: function (getAttribute) {
    		return list(getAttribute);
    	},
    	save_cookie : function(name,value){
    		setCookie(name,value);
    	},
    	get_cookie : function(text){
    		return getCookie(text);
    	},
        del_cookie : function(text){
            return deleteCookie(text);
        }
    }
}]);
