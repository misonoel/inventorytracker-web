'use strict';
(function () {
  var UserService = function ($q, $http) {
    var self = this;
    self.$q = $q;
    self.$http = $http;
    self.user = {};
  };

  UserService.$inject = ['$q', '$http'];
  UserService.prototype.listUsers = function () {
    var self = this;
    var deferred = self.$q.defer();
    var promise = deferred.promise;

     self.$http.get('/inventorytracker/users')
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
  
  UserService.prototype.addUser = function (firstname, lastname, username, email, password, rights) {
	  var self = this;
	  var deferred = self.$q.defer();
	  var promise = deferred.promise;
	  
	  self.$http.post('/inventorytracker/users', {'firstname': firstname, 'lastname': lastname, 'username': username, 'email': email, 'password': password, 'rights': rights})
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
  };

  UserService.prototype.setUser = function (user) {
    var self = this;
    self.user = user;
  };

  UserService.prototype.getUser = function () {
    var self = this;
    return self.user;
  };

  var app = angular.module('main');
  app.service('UserService', UserService);
})();
