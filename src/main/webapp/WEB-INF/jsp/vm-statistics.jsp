<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>VM Statistics</title>
    
    <!-- core CSS -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/css/prettyPhoto.css" rel="stylesheet">
    <link href="assets/css/animate.min.css" rel="stylesheet">
    <link href="assets/css/main.css" rel="stylesheet">
    <link href="assets/css/responsive.css" rel="stylesheet">
    
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>

     <header id="header">
       <nav class="navbar navbar-inverse" role="banner">
            <div class="container" style="height: 123px;">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                              <img src="assets/images/c1.png" style="width: 140px;height: 108px;margin-left: 44%;"/>
                            <h2 style="color: white;font-size: 33px;font-family: calibri;margin-left: 92%;width:100%;margin-top: -20%;">
                                Access your Private Cloud
                            </h2>
                            <img src="assets/images/c1.png" style="width: 140px;height: 108px;margin-left: 199%;margin-top: -23%;"/>
                </div>
            </div><!--/.container-->
        </nav><!--/nav-->
    </header><!--/header-->

    <section class="pricing-page">
        <div class="container">
            <div class="center">  
                <h2>VM STATISTICS</h2>
            </div>  
            <div class="pricing-area text-center">
                <div class="row">
                    <c:forEach items="${vmList}" var="vm">
                    
                     <c:if test="${vm.state == 'notRunnning'}">
                    <div class="col-sm-4 plan price-one wow fadeInDown">
                        <ul>
                            <li class="heading-one">
                           
                            <h1>${vm.name}</h1>
                            </li>
                            <li>Guest OS: ${vm.guestOS}</li>
                        	<li>IP Address: ${vm.iPAddress}</li>
                            <li>State: ${vm.state}</li>
                            <li>Number of CPUs: ${vm.numCpu}</li>
                            <li>CPU Allocated: ${vm.cpuAllocated}</li>
                            <li>CPU Usage: ${vm.cpuUsage}</li>
                            <li>Memory Allocated: ${vm.memoryAllocated}</li>
                            <li>Consumed Memory: ${vm.consumedMemory}</li>
                           
                        </ul>
                    </div>
                     </c:if>
                    </c:forEach>
                </div>
            </div><!--/pricing-area-->
        </div><!--/container-->

    <center>
        <form id="homeId" name="homeForm" action="/VirtualPrivateCloud/back" method="GET">
        <button type="submit" id = "btn1" class="btn btn-primary">Home</button>
        </form>
        <form id="listId" name="listForm" action="/VirtualPrivateCloud/home" method="GET">
        <button type="button" class="btn btn-primary" style="margin-left: 15%;">&nbsp;Back&nbsp;</button>
    </form>
    </center>
    </section><!--/pricing-page-->

           
   
    <footer id="footer" class="midnight-blue">
        <div class="container">
            <div class="row">
               
            </div>
        </div>
    </footer><!--/#footer-->

    <script src="assets/js/jquery.js"></script>
    <script type="text/javascript">
        $('.carousel').carousel()
    </script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.prettyPhoto.js"></script>
    <script src="assets/js/jquery.isotope.min.js"></script>
    <script src="assets/js/main.js"></script>
    <script src="assets/js/wow.min.js"></script>
</body>
</html>