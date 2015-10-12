'use strict';
(function () {
	var MenuService = function ($q, $http) {
		var self = this;
		self.$q = $q;
		self.$http = $http;
	};
	
	MenuService.$inject = ['$q', '$http'];
	MenuService.prototype.createTable = function () {
		var self = this;
		var deferred = self.$q.defer();
		var promise = deferred.promise;
		
		self.$http.post('/inventorytracker/main')
		.success(function (data) {
			deferred.resolve(data);
		})
		.error(function (data) {
			deferred.reject(data);
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
	
	var app = angular.module('main');
	app.service('MenuService', MenuService);
})();