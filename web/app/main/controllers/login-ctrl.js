'use strict';
(function () {
	var LoginCtrl = function (LoginService, $state) {
		console.log('LoginCtrl activated!');
		var self = this;
		self.LoginService = LoginService;
		self.$state = $state;
		self.data = {};
		self.isLogin = false;
	};
	
	LoginCtrl.$inject = ['LoginService', '$state'];
	LoginCtrl.prototype.loggingIn = function () {
		var self = this;
		self.LoginService.loginUser(self.data.username, self.data.password)
		.success(function () {
			self.isLogin = true;
			self.data = {};
			self.$state.go('main.home');
		})
		.error(function () {
			
		});
	};
	
	var app = angular.module('main');
	app.controller('LoginCtrl', LoginCtrl);
})();
