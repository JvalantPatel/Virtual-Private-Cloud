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
<style>
#divCPUNot,#divMemNot,#divNetNot,#divdiskReadNot,#divdiskWriteNot{
color:Blue;
font-weight:bold;
}

 #divCPUHigh,#divMemHigh,#divNetHigh,#divdiskReadHigh,#divdiskWriteHigh{
color:Red;
font-weight:bold;
image:url(assets/images/alarm-icon.png);
}

 #divCPUNormal,#divMemNormal,#divNetNormal,#divdiskReadNormal,#divdiskWriteNormal{
color:Green;
font-weight:bold;
}


</style>

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

<center style="margin-left: -9%">
	<h2>VM Graphs for VM:<div id="vmNameId" style="margin-top: -22px;margin-left: 22%;">${vmName}</div></h2>
	<div id="kibanaLink" style="margin-left: 8%;">
	<h3>	<a href id="kibanaHref" target="_blank">Click here for more Info on VM</a></h3>
	</div>
	</center>
	<br>
	<iframe id="iframeId" height="450px" width="100%" frameborder="0"
		scrolling="no"></iframe>
<center>

	<section>
	
	<h3><b>Virtual Machine Usage</b></h3>
		<c:choose>
			<c:when test="${vmAlarmStatus.cpuAlarm==-1}">
				<input type="hidden" id="cpu" value=-1>
				<div id="divCPUNot">CPU Usage: Threshold Not set</div>
			</c:when>
			<c:when test="${vmAlarmStatus.cpuAlarm==1}">
				<input type="hidden" id="cpu" value=1>
				<div id="divCPUHigh">CPU Usage: High
				<image src="assets/images/alarm-icon.png" style="width: 20px;"/>
				</div>
			</c:when>
			<c:when test="${vmAlarmStatus.cpuAlarm==0}">
				<input type="hidden" id="cpu" value=0>
				<div id="divCPUNormal">CPU Usage: Normal</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="cpu" value="">
				<div id="divCPUNoraml">CPU:</div>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${vmAlarmStatus.memoryAlarm==-1}">
				<input type="hidden" id="mem" value=-1>
				<div id="divMemNot">Memory Usage: Threshold not set</div>
			</c:when>
			<c:when test="${vmAlarmStatus.memoryAlarm==1}">
				<input type="hidden" id="mem" value=-1>
				<div id="divMemHigh">Memory usage: High
				<image src="assets/images/alarm-icon.png" style="width: 20px;"/>
				</div>
			</c:when>
			<c:when test="${vmAlarmStatus.memoryAlarm==0}">
				<input type="hidden" id="mem" value=-1>
				<div id="divMemNormal">Memory Usage: Normal"</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="mem" value="">
				<div id="divMemNormal">Memory Usage:</div>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${vmAlarmStatus.netAlarm==-1}">
				<input type="hidden" id="net" value=-1>
				<div id="divNetNot">Net usage: "Threshold Not set"</div>
			</c:when>
			<c:when test="${vmAlarmStatus.netAlarm==1}">
				<input type="hidden" id="net" value=-1>
				<div id="divNetHigh">Net Usage: High
				<image src="assets/images/alarm-icon.png" style="width: 20px;"/>
				</div>
			</c:when>
			<c:when test="${vmAlarmStatus.netAlarm==0}">
				<input type="hidden" id="net" value=0>
				<div id="divNetNormal">Net Usage: Normal</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="net" value="">
				<div id="divNetNormal">Net Usage:</div>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${vmAlarmStatus.diskReadAlarm==-1}">
				<input type="hidden" id="diskRead" value=-1>
				<div id="divdiskReadNot">Disk Read Usage: Threshold Not set</div>
			</c:when>
			<c:when test="${vmAlarmStatus.diskReadAlarm==1}">
				<input type="hidden" id="diskRead" value=1>
				<div id="divdiskReadHigh">Disk Read Usage: High
				<image src="assets/images/alarm-icon.png" style="width: 20px;"/>
				</div>
			</c:when>
			<c:when test="${vmAlarmStatus.diskReadAlarm==0}">
				<input type="hidden" id="diskRead" value=0>
				<div id="divdiskReadNormal">Disk Read Usage: Normal</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="diskRead" value="">
				<div id="divdiskReadNormal">Disk Read Usage:</div>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${vmAlarmStatus.diskWriteAlarm==-1}">
				<input type="hidden" id="diskWrite" value=-1>
				<div id="divdiskWriteNot">Disk Write Usage: Threshold Not Set</div>
			</c:when>
			<c:when test="${vmAlarmStatus.diskWriteAlarm==1}">
				<input type="hidden" id="diskWrite" value=1>
				<div id="divdiskWriteHigh">Disk Write Usage: High
				<image src="assets/images/alarm-icon.png" style="width: 20px;"/>
				</div>
			</c:when>
			<c:when test="${vmAlarmStatus.diskWriteAlarm==0}">
				<input type="hidden" id="diskWrite" value=0>
				<div id="divdiskWriteNormal">Disk Write Usage: Normal</div>
			</c:when>
			<c:otherwise>
				<input type="hidden" id="diskWrite" value="">
				<div id="divdiskWriteNormal">Disk Write Usage:</div>
			</c:otherwise>
		</c:choose>


	</section>
	</center>

	<!-- <footer id="footer" class="midnight-blue">
		<div class="container">
			<div class="row"></div>
		</div>
	</footer> -->
	<!--/#footer-->

	<script src="assets/js/jquery.js"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							var iframeTag = $("#iframeId");
							var test = $("#vmNameId").text();
							var vmName = "vmhost:%22" + test + "%22";
							/* alert("For IFrame: "+vmName); */
							 /* 'http://54.183.240.185:5601/#/dashboard/VM-Statistics?embed&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'
									+ vmName
									+ ')),title:\'VM%20Statistics\')&_g=(refreshInterval:(display:\'10%20seconds\',section:1,value:10000),time:(from:now-15m,mode:quick,to:now))"'; */
//var url = 'http://54.183.240.185:5601/#/dashboard/VM-Statistics?embed&_g=(refreshInterval:(display:\'5%20seconds\',section:1,value:5000),time:(from:now-30m,mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'+vmName+')),title:\'VM%20Statistics')";
//var url = 'http://54.183.240.185:5601/#/dashboard/VM-Statistics?embed&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'+ vmName + ')),title:\'VM%20Statistics\')&_g=(refreshInterval:(display:\'5%20seconds\',section:1,value:5000),time:(from:now-30m,mode:quick,to:now))"';
//var url = "http://54.183.240.185:5601/#/dashboard/VM-Statistics?embed&_g=(refreshInterval:(display:'5%20seconds',section:1,value:5000),time:(from:now-30m,mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'vmhost:%22"+ vmName  +"%22')),title:'VM%20Statistics')"
var url = "http://54.183.240.185:5601/#/dashboard/VM-Statistics?embed&_g=(refreshInterval:(display:'5%20seconds',section:1,value:5000),time:(from:now-30m,mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'vmhost:%22vmhost:%22"+ vmName  +"%22%22')),title:'VM%20Statistics')"
var kibanaUrl = "http://54.183.240.185:5601/#/dashboard/VM-Statistics?_g=(refreshInterval:(display:%275%20seconds%27,section:1,value:5000),time:(from:now-30m,mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:%27vmhost:%22"+ vmName  +"%22%27)),title:%27VM%20Statistics%27)";
							iframeTag.attr('src', url);
							
							//var kibanaURL = "http://54.183.240.185:5601/#/dashboard/VM-Statistics?embed&_g=(refreshInterval:(display:\'5%20seconds\',section:1,value:5000),time:(from:now-30m,mode:quick,to:now))&_a=(filters:!(),panels:!((col:1,id:CPU-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:1,id:Memory-Usage,row:3,size_x:6,size_y:2,type:visualization),(col:7,id:Network-Usage,row:1,size_x:6,size_y:2,type:visualization),(col:7,id:Disk-Usage,row:3,size_x:6,size_y:2,type:visualization)),query:(query_string:(analyze_wildcard:!t,query:'+vmName+')),title:\'VM%20Statistics')";
							
							var kinbanaLink = $("#kibanaHref").attr("href",kibanaUrl);
						});

		setInterval(
				function() {
					var test = $("#vmNameId").text();
					/* alert("VMName: " + test); */
					$
							.ajax({
								url : "${pageContext.request.contextPath}/getVmAlarmStatus/"
										+ test,
								type : "GET",
								beforeSend : function(xhr) {
									xhr.setRequestHeader("Accept",
											"application/json");
									xhr.setRequestHeader("Content-Type",
											"application/json");
								},
								success : function(response) {

									cpu(response);
									/* if(response.cpuAlarm==1){
										document.getElementById("cpu").value=response.cpuAlarm;
										$("#divCPU").text("CPU: Alarm Triggered");
									}else if(response.cpuAlarm==0){
										$("#divCPU").text("CPU: Alarm Not Triggered");
									}else{
										$("#divCPU").text("CPU: Alarm Not set");
									} */

									if (response.memoryAlarm == 1) {
										document.getElementById("mem").value = response.memoryAlarm;
										$("#divMem").text(
												"Memory usage: High");
									} else if (response.memoryAlarm == 0) {
										$("#divMem").text(
												"Memory Usage: Normal");
									} else {
										$("#divMem").text(
												"Memory Usage: Threshold not set");
									}

									if (response.netAlarm == 1) {
										document.getElementById("net").value = response.netAlarm;
										$("#divNet").text(
												"Net Usage: High");
									} else if (response.netAlarm == 0) {
										$("#divNet").text(
												"Net Usage: Normal");
									} else {
										$("#divNet").text("Net Usage: Threshold Not set");
									}

									if (response.diskReadAlarm == 1) {
										document.getElementById("divdiskRead").value = response.diskReadAlarm;
										$("#divdiskRead").text(
												"Disk Read usage: High");
									} else if (response.diskReadAlarm == 0) {
										$("#divdiskRead")
												.text(
														"Disk Read Usage: Normal");
									} else {
										$("#divdiskRead").text(
												"Disk Read Usage: Threshold not set");
									}

									if (response.diskWriteAlarm == 1) {
										document.getElementById("divdiskWrite").value = response.diskWriteAlarm;
										$("#divdiskWrite").text(
												"Disk Write Usage: High");
									} else if (response.diskWriteAlarm == 0) {
										$("#divdiskWrite")
												.text(
														"Disk Write Usage: Normal");
									} else {
										$("#divdiskWrite").text(
												"Disk Write Usage: Threshold not set");
									}
								}
							});
				}, 20000);

		function cpu(response) {

			if (response.cpuAlarm == 1) {
				document.getElementById("cpu").value = response.cpuAlarm;
				$("#divCPU").text("CPU Usage: High");
			} else if (response.cpuAlarm == 0) {
				$("#divCPU").text("CPU Usage: Normal");
			} else {
				$("#divCPU").text("CPU Usage: Theshold not set");
			}

		}
	</script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.prettyPhoto.js"></script>
	<script src="assets/js/jquery.isotope.min.js"></script>
	<script src="assets/js/main.js"></script>
	<script src="assets/js/wow.min.js"></script>

</body>
</html>