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
<title>Private Cloud</title>

<!-- core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<link href="assets/css/animate.min.css" rel="stylesheet">
<link href="assets/css/prettyPhoto.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">
<!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
<link rel="shortcut icon" href="assets/images/ico/favicon.ico">
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

<body class="homepage">

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
						style="width: 140px; height: 108px; margin-left: 44%;" />
					<h2
						style="color: white; font-size: 33px; font-family: calibri; margin-left: 92%; width: 100%; margin-top: -20%;">
						Access your Private Cloud</h2>
					<img src="assets/images/c1.png"
						style="width: 140px; height: 108px; margin-left: 199%; margin-top: -23%;" />

				</div>
			</div>
			<!--/.container-->
		</nav>
		<!--/nav-->
	</header>
	<!--/header-->

	<section id="main-slider" class="no-margin">
		<div class="carousel slide">
			<ol class="carousel-indicators">
				<!-- <li data-target="#main-slider" data-slide-to="0" class="active"></li> -->
				<!--  <li data-target="#main-slider" data-slide-to="1"></li>
                <li data-target="#main-slider" data-slide-to="2"></li> -->
			</ol>
			<div class="carousel-inner">

				<div class="item active"
					style="background-image: url(assets/images/slider/bg1.jpg)">
					<div class="container">
						<div class="row slide-margin" style="margin-left: 38%;">
							<div class="col-sm-6">
								<div class="carousel-content">
									<h1 class="animation animated-item-1"
										style="margin-top: -36%; color: Black;">Welcome</h1>
									<!-- <h2 class="animation animated-item-2">Accusantium doloremque laudantium totam rem aperiam, eaque ipsa...</h2> -->
									<div style="margin-left: -23%;">
										<a id="activator" class="btn-slide animation animated-item-3"
											style="width: 160px; height: 56px; margin-left: -16%; font-size: 25px"
											href="#myModal">
											<center style="margin-top: 5%; margin-left: -7%;">
												LOGIN</center>
										</a> <a id="activator1"
											class="btn-slide animation animated-item-3"
											style="width: 160px; height: 56px; font-size: 25px; margin-left: 19%;"
											href="#myModal1">
											<center style="margin-top: 5%; margin-left: -7%;">
												SIGN UP</center>
										</a>
										
<form:errors path="userName" cssClass="error" />
									</div>
								</div>
							</div>

							<!-- Adding Modal Code 1-->

							<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
								aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-dialog">
									<div class="modal-content" style="margin-top: 12%;">
										<div class="modal-header">

											<h2 class="modal-title" id="myModalLabel">Login</h2>
										</div>
										<div class="modal-body">
											<form:form role="form" method="post"
												action="/VirtualPrivateCloud/login" commandName="userForm">
												<div class="form-group">
													<label>Enter UserName</label>
													<form:input type="text" class="inputclass" path="userName"
														placeholder="Enter UserName" style="margin-left: 8%;" />
														
														
														
												</div>
												<div class="form-group">
													<label for="password">Enter Password</label>&nbsp;&nbsp;&nbsp;&nbsp;
													<form:input path="password" type="password" class="inputclass"
														id="pwd" placeholder="Enter Password" />
												</div>
												<div class="modal-footer">
													<button type="submit" id="btn1" class="btn btn-primary">Login</button>
												</div>
											</form:form>

											<form:form action="/VirtualPrivateCloud/cancel" method="GET">
												<button type="submit" class="btn btn-primary">&nbsp;Cancel&nbsp;</button>

											</form:form>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="modal fade" id="myModal1" tabindex="-1" role="dialog"
							aria-labelledby="myModalLabel1" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content" style="margin-top: 12%;">
									<div class="modal-header">

										<h2 class="modal-title" id="myModalLabel1">Sign Up</h2>
									</div>
									<div class="modal-body">
										<form:form role="form" method="post"
											action="/VirtualPrivateCloud/signUp" commandName="userForm1">
											<div class="form-group">
												<label>Enter UserName</label> <form:input type="text"
													class="inputclass" id="uname" path="userName"
													placeholder="Enter UserName" style="margin-left: 8%;" />
											</div>
											<div class="form-group">
												<label>Enter Password</label>&nbsp;&nbsp;&nbsp;&nbsp;
												<form:input path="password" type="password" class="inputclass"
													id="pwd1" placeholder="Enter Password" />
											</div>
											<div class="modal-footer">
												<button type="submit" id="btn1" class="btn btn-primary">Sign
													Up</button>
											</div>
										</form:form>
										<form:form action="/VirtualPrivateCloud/cancel" method="GET">
											<button type="submit" class="btn btn-primary">&nbsp;Cancel&nbsp;</button>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>

					<!--Adding Modal Code-->



				</div>
			</div>
		</div>
		<!--/.item-->

		</div>
		<!--/.carousel-inner-->
		</div>
		<!--/.carousel-->

	</section>
	<!--/#main-slider-->



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
	<script src="assets/js/jquery.leanModal.min.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/wow.min.js"></script>
	<script type="text/javascript">
		/*$('.carousel').carousel()*/

		$("#activator").leanModal({
			top : 200,
			overlay : 0.6
		});
		$("#activator1").leanModal({
			top : 200,
			overlay : 0.6
		});
		$(function() {
			$('#activator').click(function() {

				$('#myModal').animate();

			});

		});

		$(function() {
			$('#activator1').click(function() {

				$('#myModal1').animate();

			});

		});
	</script>
</body>
</html>