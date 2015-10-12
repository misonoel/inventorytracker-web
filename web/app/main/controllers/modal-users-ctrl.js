'use strict';
(function () {
	var ModalUserCtrl = function (UserService, $modalInstance, action, user) {
//		console.log("ModalUserCtrl activated!");
		var self = this;
		self.UserService = UserService;
		self.$modalInstance = $modalInstance;
		self.action = action || 'add';
		self.user = user || {};
	};
	
	ModalUserCtrl.$inject = ['UserService', '$modalInstance', 'action', 'user'];
	ModalUserCtrl.prototype.ok = function () {
		var self = this;
		self.$modalInstance.close(self.user);
	};
	
	ModalUserCtrl.prototype.cancel = function () {
		var self = this;
		self.$modalInstance.dismiss("Cancelled");
	};
	
	ModalUserCtrl.prototype.addUser = function () {
		var self = this;
		self.UserService.addUser(self.data.firstname, self.data.lastname, self.data.username, self.data.email, self.data.password, self.data.rights);
		console.log(self.data.firstname, self.data.lastname, self.data.username, self.data.email, self.data.password, self.data.rights);
		self.data = {};
	};
	
	var app = angular.module('main');
	app.controller('ModalUserCtrl', ModalUserCtrl);
})();
