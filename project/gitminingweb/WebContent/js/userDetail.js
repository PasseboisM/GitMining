function GetQueryString(name) { 
var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i"); 
var r = window.location.search.substr(1).match(reg); 
if (r!=null) return (r[2]); return null; 
}

var login=GetQueryString("login"); 
var recommend_request_list=["OwnedRepositories","StarredRepositories","SubscrippedRepositories","Followers","Followings"]

var app = angular.module('main_app', []);
app.controller('detail_controller',['$scope','$http','LoginService','TopService',function($scope, $http,LoginService,TopService) {
	$scope.email = LoginService.get_cookie("email");
    $(document).ready(function(){
    	if($scope.email.length>0){
    		$("#logout_div").show();
    		$("#login_div").hide();
    		$("#wrong_msg").hide();
    	}else{
    		$("#login_div").show();
    		$("#logout_div").hide();
    		$("#wrong_msg").hide();
    	}
    });
    $scope.password = "";
	var url = '/GitMiningServer/user';
	data = {
		type : "data",
		method: "spec",
		param: login
	};

	RecommendAttribute={
		type:"related",
		param:recommend_request_list[0],
		login:login
	}
	TopService.getTop25(RecommendAttribute).success(
		function(response) {
			$scope.own_repos=response.slice(0,5);
		});

	RecommendAttribute={
		type:"related",
		param:recommend_request_list[1],
		login:login
	}
	TopService.getTop25(RecommendAttribute).success(
		function(response) {
			$scope.star_repos=response.slice(0,5);
		});

	RecommendAttribute={
		type:"related",
		param:recommend_request_list[2],
		login:login
	}
	TopService.getTop25(RecommendAttribute).success(
		function(response) {
			$scope.sub_repos=response.slice(0,5);
		});

	RecommendAttribute={
		type:"related",
		param:recommend_request_list[3],
		login:login
	}
	TopService.getTop25(RecommendAttribute).success(
		function(response) {
			$scope.followers=response.slice(0,5);
		});
	
	RecommendAttribute={
		type:"related",
		param:recommend_request_list[4],
		login:login
	}
	TopService.getTop25(RecommendAttribute).success(
		function(response) {
			$scope.followings=response.slice(0,5);
		});
	
	
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
					$("#wrong_msg").hide();
					//show user info
				}else{
					//show alert info
					console.log("hey sth. wrong!");
					$("#wrong_msg").show();
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
		$("#wrong_msg").hide();
		$("#login_div").show();
	}
	var key = LoginService.get_cookie("key");
	if(key.length>0)
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

}]);