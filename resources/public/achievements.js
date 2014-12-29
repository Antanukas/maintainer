(function() {
    "use strict"
    var app = angular.module('achievements', []);

    app.controller('AchievementsController', ['$scope','AchievementService', function($scope, achievementService){
        achievementService.getAchievements(function (data) {
          $scope.achievements = data
        });
        achievementService.getBuildBreakerTop(function (data) {
          $scope.buildBreakerTop = data;
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

  app.factory("AchievementService", ["$http", function($http) {
    return {
      getAchievements: function(successCallback) {
        return $http.get('achievements').success(successCallback);
      },

      getBuildBreakerTop: function(successCallback) {
        return $http.get('achievements/buildBreaker').success(successCallback);
      }
    };
  }]);
})();
