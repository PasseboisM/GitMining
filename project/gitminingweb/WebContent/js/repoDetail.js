var repoStatistic = ["stars", "forks", "subscribers", "contributors", "collaborator"];
var repoInfo = ["更新日期", "创建时间", "项目语言", "项目大小"];


var app = angular.module('main_app', []);


app.controller('main_ctrl', function($scope) {
  console.log(repoInfo[0]);
  $scope.repoSta=null;
  $scope.repoInf=null;
    for (var i = 0; i < repoStatistic.length; i++) {
        $scope.repoSta[i]=repoStatistic[i];
    }
    for (var j = 0; j < repoInfo.length; j++) {
        $scope.repoInf[j]=repoInfo[j];
    }


});
