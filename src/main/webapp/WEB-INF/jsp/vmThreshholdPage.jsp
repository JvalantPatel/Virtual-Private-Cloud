<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>Create VM and List of Vms</title>

<!-- core CSS -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/font-awesome.min.css" rel="stylesheet">
<link href="assets/css/prettyPhoto.css" rel="stylesheet">
<link href="assets/css/animate.min.css" rel="stylesheet">
<link href="assets/css/main.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">
<link href="assets/css/responsive.css" rel="stylesheet">


<link rel="shortcut icon" href="assets/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/images/ico/apple-touch-icon-57-precomposed.png">

<!-- Bootstrap -->
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
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

<center>
	<h2><b>Select a VM to set threshold:</b></h2>

<form action="/VirtualPrivateCloud/logout" method="GET">
			<button type="submit" class="btn btn-primary" style="margin-left: 46%;margin-top: -2%;">
				Logout</button>
		</form>
		<br><br>
		<form id="homeId" name="homeForm" action="/VirtualPrivateCloud/back"
			method="GET">
			<button type="submit" id="btn1" class="btn btn-primary"
				style="margin-left: -42%;margin-top: -11%;">Home</button>
		</form>

	<select id="vmName" class="form-control" style="width: 11%;margin-top: -2%;">
		<option selected="selected"><b>Choose VM</b></option>
		<c:forEach var="vm" items="${vmList}">
			<option>${vm}</option>
		</c:forEach>
	</select>
	<br/>
	<form:form method="POST"
		action="/VirtualPrivateCloud/setVMAlarmThreshold"
		id="threshHoldFormId" role="form" commandName="vmAlarm">
		
		 <form:input id="vm" path="VMName" type="hidden" class="form-control" style="width:10%"></form:input>
		
		<b>CPU: (%*100)</b> <form:input id="cpu" path="cpuThreshold" type="text" class="form-control" style="width:10%"></form:input>
		<br> 
		<b>Memory: (kb)</b> <form:input id="memory" path="memoryThreshold"
			type="text" class="form-control" style="width:10%"></form:input>
		<br> 
		<b>Disk Read: (kb)</b><form:input id="diskRead" path="netThreshold"
			type="text" class="form-control" style="width:10%"></form:input>
		<br>
		<b>Disk Write: (kb)</b> <form:input id="diskWrite"
			path="diskReadThreshold" type="text" class="form-control" style="width:10%"></form:input>
		<br> 
		<b>Net: (kb)</b> <form:input id="net" path="diskWriteThreshold"
			type="text" class="form-control" style="width:10%"></form:input>
		<button type="submit" value="Save" class="btn btn-primary" style="margin-left: -11%;">Save</button>
	</form:form>
		<br>
	<form:form method="POST"
		action="/VirtualPrivateCloud/getVmStatisticsPage"
		commandName="vmAlarm" id="perform">
		<form:input id="vmPerf" path="VMName" type="hidden"></form:input>
		<input type="submit" value="View Performance Statistics" class="btn btn-primary" style="margin-top: -7%;margin-left: 23%;"/>
	</form:form>
	</center>
	<footer id="footer" class="midnight-blue">
		<div class="container">
			<div class="row"></div>
		</div>
	</footer> 
	<!--/#footer-->

	<script src="assets/js/jquery.js"></script>
	<script type="text/javascript">
		 $("#perform").submit(function(event) {
			 
			var vmName = document.getElementById("vmName").value;
			//alert(vmName);
			var url = "/VirtualPrivateCloud/getVmStatisticsPage";
			$("#id").attr('value',vmName);
			$("#perfForm").attr('action', url);
		}); 
		$("#vmName")
				.change(
						function() {
							var vmName = document.getElementById("vmName").value;
							//alert(vmName);
							document.getElementById("vm").value = vmName;
							document.getElementById("vmPerf").value = vmName;
							$
									.ajax({

										url : "${pageContext.request.contextPath}/getVMAlarmThreshold/"
												+ vmName,
										type : "GET",
										beforeSend : function(xhr) {
											xhr.setRequestHeader("Accept",
													"application/json");
											xhr.setRequestHeader(
													"Content-Type",
													"application/json");
										},
										success : function(response) {
											//alert("inside succcess");

											response.cpuThreshold == -1 ? document
													.getElementById("cpu").value = 0
													: document
															.getElementById("cpu").value = response.cpuThreshold;

											response.memoryThreshold == -1 ? document
													.getElementById("memory").value = 0
													: document
															.getElementById("memory").value = response.memoryThreshold;

											response.netThreshold == -1 ? document
													.getElementById("net").value = 0
													: document
															.getElementById("net").value = response.netThreshold;

											response.diskReadThreshold == -1 ? document
													.getElementById("diskRead").value = 0
													: document
															.getElementById("diskRead").value = response.diskReadThreshold;

											response.diskWriteThreshold == -1 ? document
													.getElementById("diskWrite").value = 0
													: document
															.getElementById("diskWrite").value = response.diskWriteThreshold;

										}
									});

						});
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
	<script src="assets/js/jquery.isotope.min.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/wow.min.js"></script>

</body>
</html>