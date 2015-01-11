(function() {
  var app = angular.module('maintainerApp', ['ngRoute', 'achievements', 'maintainer'])
  .config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    when('/maintainer/:team', {
      templateUrl: 'view/maintainer.html',
      controller: 'AchievementsController'
    }).
    otherwise({
      redirectTo: '/'
    });
  }]);
})();
