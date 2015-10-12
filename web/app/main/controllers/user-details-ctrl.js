'use strict';
(function () {
	var UserDetailCtrl = function (UserService, $stateParams) {
		console.log('UserDetailCtrl activated!');
		var self = this;
		self.$stateParams = $stateParams;
		self.UserService = UserService;
		self.firstname = self.UserService.getUser().firstname || 'No First Name Specified';
		self.lastname = self.UserService.getUser().lastname || 'No Last Name Specified';
		self.username = self.UserService.getUser().username || 'No Username Specified';
		self.email = self.UserService.getUser().email || 'No Email Address Specified';
	};
	
	var app = angular.module('main');
	app.controller('UserDetailCtrl', UserDetailCtrl);
})();
