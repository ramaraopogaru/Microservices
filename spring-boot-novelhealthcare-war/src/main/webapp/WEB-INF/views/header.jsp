<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="core"%>
<html lang="en">
<head>
  
  <title>Animated Vertical Carousel</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href='<core:url value="/resources/css/bootstrap.min.css"/>'>
    <!--<link href="https://fonts.googleapis.com/css?family=Sansita" rel="stylesheet">
     <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css"> -->
<link href='<core:url value="/resources/css/landingpage.css"/>' rel="stylesheet">
<link href='<core:url value="/resources/css/animate.css"/>' rel="stylesheet">
<script src='<core:url value="/resources/js/jquery.js"/>'></script>
<script src='<core:url value="/resources/js/bootstrap.min.js"/>'></script>

<%-- <script src="<core:url value="/resources/css/bootstrap.min.css"/>"></script> --%>
<script src='<core:url value="/resources/js/landingpage.js"/>'></script>

    </head>
<body>
<!-- Fixed navbar -->
        <nav id="header" class="navbar navbar-fixed-top">
            <div id="header-container" class="container navbar-container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a id="brand" class="navbar-brand" href="#"><img src="resources/images/hospital-logo.jpg" alt="logo" class="img img-responsive" /></a>
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
                </div><!-- /.nav-collapse -->
            </div><!-- /.container -->
        </nav><!-- /.navbar -->
</body>
</html>