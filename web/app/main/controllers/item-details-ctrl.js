'use strict';
(function () {
	var ItemDetailCtrl = function (ItemsService, $stateParams) {
		console.log('ItemDetailCtrl activated!');
		var self = this;
		self.$stateParams = $stateParams;
		self.ItemsService = ItemsService;
		self.itemname = self.ItemsService.getItem().itemname || 'No Item Name Specified';
		self.description = self.ItemsService.getItem().description || 'No Description Specified';
		self.owner = self.ItemsService.getItem().owner || 'No Owner Specified';
	};
	
	var app = angular.module('main');
	app.controller('ItemDetailCtrl', ItemDetailCtrl);
})();
