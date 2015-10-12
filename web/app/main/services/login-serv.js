'use strict';
(function () {
	var LoginService = function ($q, $http, $cookies) {
		var self = this;
		self.$q = $q;
		self.$http = $http;
		self.$cookies = $cookies;
		self.user = self.$cookies.get('username');
//		self.user = undefined
	};
	
	LoginService.$inject = ['$q', '$http', '$cookies'];
	LoginService.prototype.loginUser = function (username, password) {
		var self = this;
		var deferred = self.$q.defer();
		var promise = deferred.promise;
		
		self.$http.post('/inventorytracker/login', {'username': username, 'password': password})
		.success(function (data, status) {
			if (data.__MSGS__) {
				self.user = username;
				self.$cookies.put('username', username);
				console.log("cookies: " + self.$cookies.get('username'));
				deferred.resolve(data, status);
			}else {
				self.user = undefined;
				deferred.reject(data, 404);
			}
		})
		.error(function (data, status) {
			deferred.reject(data, status);
		});
		
		promise.success = function (fn) {
			promise.then(fn);
			return promise;
		};
		promise.error = function (fn) {
			promise.then(null, fn);
			return promise;
		};
		
		return promise;
	};
	
	LoginService.prototype.isLogin = function () {
		var self = this;
		if (self.user || self.$cookies.get('username') !== undefined) {
			return true;
		}
		return false;
	};
	
	LoginService.prototype.logout = function () {
		var self = this;
		self.user = undefined;
		self.$cookies.put('username', undefined);
	};
	
	var app = angular.module('main');
	app.service('LoginService', LoginService);
})();
