'use strict';
(function () {
	var UserCtrl = function (UserService, $uibModal, $state) {
		console.log('UserCtrl activated!');
		var self = this;
		self.UserService = UserService;
		self.$uibModal = $uibModal;
		self.$state = $state;
		self.users = [];
		self.data = {};
		self.listUsers();
	};
	
	UserCtrl.$inject = ['UserService', '$uibModal', '$state'];
	UserCtrl.prototype.listUsers = function () {
		var self = this;
		self.UserService.listUsers()
		.success(function (data) {
			self.users = data;
		});
	};
	
	UserCtrl.prototype.setUser = function (user) {
		var self = this;
		self.UserService.setUser(user);
	};
	
	UserCtrl.prototype.getUser = function () {
		var self = this;
		return self.UserService.getUser();
	};
	
	UserCtrl.prototype.viewUser = function (user) {
		var self = this;
		self.setUser(user);
		self.$state.go('main.userDetails');
	};
	
	UserCtrl.prototype.add = function () {
		var self = this;
//		var modalInstance = 
		self.$uibModal.open({
			animation: true,
			templateUrl: 'app/main/templates/modal-user.html',
			controller: 'ModalUserCtrl as ctrl',
			resolve: {
				action: function () {
					return "add";
				},
				user: {}
			}
		});
		
//		modalInstance.result.then(function (user) {
//			self.listUsers();
//			console.log(user);
//		});
	};
	
	UserCtrl.prototype.edit = function (user) {
		var self = this;
//		var modalInstance = 
		self.$uibModal.open({
			animation: true,
			templateUrl: 'app/main/templates/modal-user.html',
			controller: 'ModalUserCtrl as ctrl',
			resolve: {
				action: function () {
					return "edit";
				},
				user: function () {
					self.setUser(user);
					return self.getUser();
				}
			}
		});
		
//		modalInstance.result.then(function (user) {
//			self.listUsers();
//		});
	};
	
	UserCtrl.prototype.deleteUser = function (user, index) {
		var self = this;
		self.setUser(user);
		BootstrapDialog.confirm({
			title: 'Delete user',
			message: 'Are you sure you want to delete user <b><i>' + self.getUser().username + '</i></b>?',
			type: BootstrapDialog.TYPE_DANGER,
			btnOKLabel: 'Delete',
			btnOKClass: 'btn-danger',
			callback: function (result) {
				if(result) {
					console.log('deleted | ' + self.getUser().username + ' | ' + index);
					self.users.splice(index, 1);
				} else {
					console.log('delete - cancel.');
				}
			}
		});
	};
	
	var app = angular.module('main');
	app.controller('UserCtrl', UserCtrl);
})();
