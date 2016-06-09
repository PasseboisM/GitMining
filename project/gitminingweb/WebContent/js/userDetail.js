function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var login=GetQueryString("login"); 

var app = angular.module('main_app', []);
app.controller('detail_controller','LoginService', function($scope, $http,LoginService) {
	$scope.email = LoginService.get_cookie("email");
    $(document).ready(function(){
    	if($scope.email.length>0){
    		$("#logout_div").show();
    		$("#login_div").hide();
    	}else{
    		$("#login_div").show();
    		$("#logout_div").hide();
    	}
    });
    $scope.password = "";
	var url = '/GitMiningServer/user';
	data = {
		type : "data",
		method: "spec",
		param: login
	};
	$scope.login = function(){
		param={
			login:$scope.email,
			pass:$scope.password
		}
		LoginService.login(param).success(
			function(response) {
				console.log(response);
				if(response.state){
					LoginService.save_cookie("key",response.key);
					LoginService.save_cookie("email",$scope.email);
					$("#login_div").hide();
					$("#logout_div").show();
					//show user info
				}else{
					//show alert info
					console.log("hey sth. wrong!");
					$scope.email="";
					$scope.password="";
				}
			});
	}
	$scope.logout = function(){
		LoginService.del_cookie("key");
		LoginService.del_cookie("email");
		$scope.email="";
		$scope.password="";
		$("#logout_div").hide();
		$("#login_div").show();
	}
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