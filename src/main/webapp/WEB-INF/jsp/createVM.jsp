<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>VM Details</title>

<!-- core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<link href="assets/css/prettyPhoto.css" rel="stylesheet">
<link href="assets/animate.min.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">

<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/images/ico/apple-touch-icon-57-precomposed.png">
</head>
<!--/head-->

<body>

	<header id="header">
		<nav class="navbar navbar-inverse" role="banner">
			<div class="container" style="height: 123px;">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<img src="assets/images/c1.png"
						style="width: 140px; height: 108px; margin-left: 60%;" />
					<h2
						style="color: white; font-size: 33px; font-family: calibri; margin-left: 110%; width: 100%; margin-top: -20%;">
						Access your Private Cloud</h2>
					<img src="assets/images/c1.png"
						style="width: 140px; height: 108px; margin-left: 219%; margin-top: -23%;" />
				</div>
			</div>
			<!--/.container-->
		</nav>
		<!--/nav-->
	</header>
	<!--/header-->


	<section id="contact-page">
		<div class="container">
			<div class="center">
				<h2 style="margin-top: 3%;">VM Details</h2>
				<form action="/VirtualPrivateCloud/logout" method="GET">
					<button type="submit" class="btn btn-primary"
						style="margin-left: 55%; margin-top: -9%;">Logout</button>
				</form>
				<br>
				<br>
				<form id="homeId" name="homeForm" action="/VirtualPrivateCloud/back"
					method="GET">
					<button type="submit" id="btn1" class="btn btn-primary"
						style="margin-left: -59%; margin-top: -20%;">Home</button>
				</form>
				<p class="lead" style="margin-top: -4%;">Provide appropriate details for creating new
					Virtual Machine</p>
			</div>
			<div class="row contact-wrap">
				<div class="status alert alert-success" style="display: none"></div>
				<form:form class="contact-form" method="post"
					action="/VirtualPrivateCloud/createvmsubmit" commandName="VM">
					<div class="col-sm-5 col-sm-offset-1">
						<div class="form-group">
							<label>Name</label>
							<form:input type="text" path="vmName" class="form-control"
								required="required" />
						</div>
						<div class="form-group">
							<label>Memory Limit</label>
							<form:input type="text" path="memoryLimit" class="form-control"
								required="required" />
						</div>
					</div>
					<div class="col-sm-5">
						<div class="form-group">
							<label>No Of CPUs</label>
							<form:input type="text" path="noOfCpu" class="form-control"
								required="required" />
						</div>
						<div class="form-group">
							<label>Type</label>
							<form:input type="text" path="vmType" class="form-control"
								required="required" />
						</div>

						<button type="submit" name="submit" class="btn btn-primary"
							required="required" style="margin-left: -14%; margin-top: 2%;">Create</button>

					</div>

				</form:form>


			</div>
		</div>

		</div>
		<!--/.row-->
		</div>
		<!--/.container-->
	</section>
	<!--/#contact-page-->

	<footer id="footer" class="midnight-blue">
		<div class="container">
			<div class="row"></div>
		</div>
	</footer>
	<!--/#footer-->

	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
	<script src="assets/js/jquery.isotope.min.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/wow.min.js"></script>
</body>
</html>