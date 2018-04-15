<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="core"%>
<html lang="en">
<head>
  
 <%--  <title>Animated Vertical Carousel</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href='<core:url value="/resources/css/bootstrap.min.css"/>'>
    <!--<link href="https://fonts.googleapis.com/css?family=Sansita" rel="stylesheet">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"> -->
<link href='<core:url value="/resources/css/landingpage.css"/>' rel="stylesheet">
<link href='<core:url value="/resources/css/animate.css"/>' rel="stylesheet">
<script src='<core:url value="/resources/js/jquery.js"/>'></script>
<script src='<core:url value="/resources/js/bootstrap.min.js"/>'></script>

<script src="<core:url value="/resources/css/bootstrap.min.css"/>"></script>
<script src='<core:url value="/resources/js/landingpage.js"/>'></script> --%>

    </head>
<body>
<!-- Fixed navbar -->
       <!--  <nav id="header" class="navbar navbar-fixed-top">
            <div id="header-container" class="container navbar-container">
                <div class="navbar-header">
                 
                    <img src="resources/images/hospital-logo.jpg" alt="logo" class="img img-responsive"  width="150" height="150"/></a>
                </div>
                 <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a href="home"><i class="fa fa-home" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Home</span></a></li>
                        <li><a href="getSignUpPage"><i class="fa fa-sign-in" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Signup</span></a></li>
                        <li><a href="getLoginPage"><i class="fa fa-sign-in" aria-hidden="true"></i><span class="hidden-xs hidden-sm">Login</span></a></li>
                        
                        <li><a href="about">About</a></li>
                        <li><a href="contact">Contact</a></li>
                         <li><a href="contact"></a></li>
                    </ul>
                </div>/.nav-collapse
            </div>/.container
        </nav>/.navbar
        <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
          Super Admin<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">Profile</a></li>
            <li><a href="#">Sign out</a></li>
          </ul>
        </li>
      </ul> -->
      <p class="navbar-text navbar-right">
	      <div class="navbar-header">
	      	<img src="./resources/images/hospital-logo.jpg" alt="logo" class="img img-responsive"  width="80" height="80"/>
	      </div>
	      <span class="label label-primary">Novel Health Care</span>
	      <ul class="nav navbar-nav navbar-right">
	        <li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
		          <img src="resources/images/profile.png" alt="logo" class="img img-responsive"  width="50" height="50"/>
		          <span class="glyphicon glyphicon-header"><font color="white">Super Admin</font></span>
		          <span class="caret"></span>
		      </a>
	          <ul class="dropdown-menu">
	            <li><a href="#"><span class="label label-primary">Profile</span></a></li>
	            <li><a href="./logout"><span class="label label-default">Sign out</span></a></li>
	          </ul>
	        </li>
	      </ul>
	</body>
</html>