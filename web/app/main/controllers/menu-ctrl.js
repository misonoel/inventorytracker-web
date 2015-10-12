'use strict';
(function () {
	var MenuCtrl = function (MenuService, LoginService, $state, $location) {
		console.log('MenuCtrl activated!');
		var self = this;
		self.MenuService = MenuService;
		self.LoginService = LoginService;
		self.$state = $state;
		self.$location = $location
		self.createTable();
		self.checkUser();
//		console.log("checkUser: " + self.checkUser());
	};
	
	MenuCtrl.$inject = ['MenuService', 'LoginService', '$state', '$location'];
	MenuCtrl.prototype.createTable = function () {
		var self = this;
		self.MenuService.createTable();
	};
	
	MenuCtrl.prototype.isLogin = function () {
		var self = this;
		return self.LoginService.isLogin();
	};
	
	MenuCtrl.prototype.logout = function () {
		var self = this;
		self.LoginService.logout();
		self.$state.go('login');
	};
	
	MenuCtrl.prototype.getUser = function () {
		var self = this;
		return self.LoginService.user || 'No username';
	};
	
	MenuCtrl.prototype.checkUser = function () {
		var self = this;
		var isLogin = self.LoginService.isLogin();
		if (!isLogin) {
			self.$state.go('login');
		}
		return isLogin;
	};
	
	MenuCtrl.prototype.isActive = function (path) {
		var self = this;
		var active = (path === self.$location.path());
		return active;
	}
	
	var app = angular.module('main');
	app.controller('MenuCtrl', MenuCtrl);
})();
