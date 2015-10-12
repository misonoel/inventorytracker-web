'use strict';
angular.module('main', [
  'ui.router'
])
.config(function ($stateProvider, $urlRouterProvider) {

  console.log('Allo! Allo from your module: ' + 'main');

  // ROUTING with ui.router
  $urlRouterProvider.otherwise('/home');
  $stateProvider
    .state('login', {
    	url: '/login',
    	templateUrl: 'app/main/templates/login.html',
    	controller: 'LoginCtrl as ctrl'
    })
    .state('main', {
    	url: '/main',
    	cache: false,
    	abstract: true,
    	templateUrl: 'app/main/templates/menu.html',
    	controller: 'MenuCtrl as ctrl'
    })
    .state('main.home', {
    	url: '^/home',
    	views: {
    		'pageContent': {
    			templateUrl: 'app/main/templates/home.html',
//    			controller: 'HomeCtrl as ctrl'
    		}
    	}
    })
    .state('main.users', {
    	url: '^/users',
    	views: {
    		'pageContent': {
    			templateUrl: 'app/main/templates/users.html',
    			controller: 'UserCtrl as ctrl'
    		}
    	}
    })
    .state('main.items', {
    	url: '^/items',
    	views: {
    		'pageContent': {
    			templateUrl: 'app/main/templates/items.html',
    			controller: 'ItemsCtrl as ctrl'
    		}
    	}
    })
    .state('main.itemDetails', {
    	url: '^/items/details',
    	views: {
    		'pageContent': {
    			templateUrl: 'app/main/templates/item-details.html',
    			controller: 'ItemDetailCtrl as ctrl'
    		}
    	}
    })
    .state('main.userDetails', {
    	url: '^/users/details',
    	views: {
    		'pageContent': {
    			templateUrl: 'app/main/templates/user-details.html',
    			controller: 'UserDetailCtrl as ctrl'
    		}
    	}
    });
});
