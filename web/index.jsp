<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Inventory Tracker</title>
	<meta name="description" content="">
	<meta name="viewport" content="width=device-width">
	
	<link href='app/main/styles/main.css' rel='stylesheet'>
	<link href='app/main/styles/bootstrap.min.css' rel='stylesheet'>
	<link href='app/main/styles/bootstrap-dialog.min.css' rel='stylesheet'>
</head>
<body ng-app="MyApp">

	<div ui-view></div>
	
	<script src="app/scripts/jquery-2.1.4.min.js"></script>
	<script src="app/scripts/bootstrap.min.js"></script>
	<script src="app/scripts/bootstrap-dialog.min.js"></script>
	<script src="app/scripts/angular.min.js"></script>
	<script src="app/scripts/angular-animate.min.js"></script>
	<script src="app/scripts/angular-cookies.min.js"></script>
	<script src="app/scripts/angular-ui-router.min.js"></script>
	<script src="app/scripts/ui-bootstrap-tpls-0.14.0.min.js"></script>
	<!-- <script src="app/scripts/smart-table.min.js"></script> -->
	
	<script src="app/main/main.js"></script>
	<script src="app/main/controllers/menu-ctrl.js"></script>
	<script src="app/main/controllers/login-ctrl.js"></script>
	<script src="app/main/controllers/users-ctrl.js"></script>
	<script src="app/main/controllers/items-ctrl.js"></script>
	<script src="app/main/controllers/user-details-ctrl.js"></script>
	<script src="app/main/controllers/item-details-ctrl.js"></script>
	<script src="app/main/controllers/modal-users-ctrl.js"></script>
	<script src="app/main/services/menu-serv.js"></script>
	<script src="app/main/services/login-serv.js"></script>
	<script src="app/main/services/users-serv.js"></script>
	<script src="app/main/services/items-serv.js"></script>
	<script src="app/app.js"></script>
</body>
</html>