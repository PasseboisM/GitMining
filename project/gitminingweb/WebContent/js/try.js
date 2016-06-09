var app = angular.module('main_app', []);


app.controller('repo_ctrl', function($scope) {
$scope.repo={
   getStargazers_count:1,
   getForks_count:2,
   getSubscribers_count:3,

   getUpdated_at: "1111T-2222Z",
   getCreated_at: "11111T-22222Z",
   getPushed_at:"1111111T-222222222222Z",
   getSize: 10086,
   getMainLanguage: "Brain F",
   getUrl : "WWWWWWWWWWWWW",
   getFull_name: "sdflkgjdg",
   getName:"asdfasf",
   getDescription:"sdlgksfl;skfjlsfkj;fjfj"
};
$scope.formattime = function(text) {
  return text.replace("T"," ").replace("Z"," ");
};


});
