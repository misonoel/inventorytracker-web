'use strict';
(function () {
  var ItemsService = function ($q, $http) {
    var self = this;
    self.$q = $q;
    self.$http = $http;
    self.item = {};
  };

  ItemsService.$inject = ['$q', '$http'];
  ItemsService.prototype.listItems = function () {
    var self = this;
    var deferred = self.$q.defer();
    var promise = deferred.promise;

     self.$http.get('/inventorytracker/items')
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
  
  ItemsService.prototype.addItem = function (itemname, description, owner) {
	  var self = this;
	  var deferred = self.$q.defer();
	  var promise = deferred.promise;
	  
	  self.$http.post('/inventorytracker/items', {'itemname': itemname, 'description': description, 'owner': owner})
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
  }

  ItemsService.prototype.setItem = function (item) {
    var self = this;
    self.item = item;
  };

  ItemsService.prototype.getItem = function () {
    var self = this;
    return self.item;
  };

  var app = angular.module('main');
  app.service('ItemsService', ItemsService);
})();
