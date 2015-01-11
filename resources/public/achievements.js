(function() {
  "use strict"
  var app = angular.module('achievements', []);

  app.controller('AchievementsController', ['$scope', '$routeParams', 'AchievementFactory', function($scope, $routeParams, achievementFactory){
    $scope.team = $routeParams.team;
    console.log('zzz' + $routeParams.team);
    achievementFactory.getAchievements($scope.team).then(function (response) {
      $scope.achievements = response.data
    });
    achievementFactory.getBuildBreakerTop($scope.team).then(function (response) {
      $scope.buildBreakerTop = response.data;
    });

    $scope.isSuccess = function(achievement) {
      return achievement.severity === "success";
    };

    $scope.isFailure = function(achievement) {
      return achievement.severity === "failure";
    };

    $scope.isWarning = function(achievement) {
      return achievement.severity === "warning";
    };
  }]);

  app.directive('oopAchievements', function() {
    return {
      restrict: 'E',
      templateUrl: 'oop-achievements.html'
    };
  });

  app.factory("AchievementFactory", ["$http", function($http) {
    return {
      getAchievements: function(team) {
        return $http.get('rest/teams/' + team + '/achievements');
      },

      getBuildBreakerTop: function(team) {
        return $http.get('rest/teams/' + team + '/achievements/buildBreaker');
      }
    };
  }]);
})();
