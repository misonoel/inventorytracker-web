'use strict';
(function () {
	var ItemsCtrl = function (ItemsService, $state) {
		console.log('ItemsCtrl activated!');
		var self = this;
		self.ItemsService = ItemsService;
		self.$state = $state;
		self.items = [];
		self.data = {};
		self.listItems();
	};
	
	ItemsCtrl.$inject = ['ItemsService', '$state'];
	ItemsCtrl.prototype.listItems = function () {
		var self = this;
		self.ItemsService.listItems()
		.success(function (data) {
			self.items = data;
		});
	};
	
	ItemsCtrl.prototype.viewItem = function (item) {
		var self = this;
		self.ItemsService.setItem(item);
		self.$state.go('main.itemDetails');
		// self.$state.go('main.listDetail', {title: item.itemname, des: item.description});
	};
	
	ItemsCtrl.prototype.addItem = function () {
		var self = this;
		self.ItemsService.addItem(self.data.itemname, self.data.description, self.data.owner);
		console.log(self.data.itemname, self.data.description, self.data.owner);
		self.data = {};
	};
	
	ItemsCtrl.prototype.clearData = function () {
		var self = this;
		self.data = {};
	};
	
	var app = angular.module('main');
	app.controller('ItemsCtrl', ItemsCtrl);
})();
