<!DOCTYPE html>

<html lang ="en">
<%@ page import="logic.bean.UserBean" %>
<%UserBean user = new UserBean();
if(session.getAttribute("loggedUser")!=null){
	user=(UserBean)session.getAttribute("loggedUser"); %>
<%
	}else { 
	user.setUsbUsername("");}
%>
<head>
	<meta charset= "UTF-8">
	<title>BeEcological - Homepage</title>
	<link rel="stylesheet" href="styles/homepage.css">
	<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-lg fixed-top navbar-dark" style = "background-color:#589442">
<!-- logo sulla navbar -->
  <a class="navbar-brand" href="HomeUserServlet?username=<%=user.getUsbUsername()%>">
    <img src="img/logo-white.png" width="250" height=45 class="d-inline-block align-top" alt="">
  </a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
 
    <ul class="navbar-nav ml-auto"> <!-- ml mette i pulsanti della navbar a sinistra -->
      <%
      	if(user.getUsbUsername()==""){
      %>
      <li class="nav-item">
        <a class="nav-link" href="loginUser.jsp">LOGIN</a>
      </li>
      <%
      	}else {
      %>
      <li class="nav-item">
        <a class="nav-link" href="ShopServlet?param=<%=user.getUsbUsername()%>" >SHOP</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
           <%=user.getUsbUsername()%></a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="UserProfileServlet?username=<%=user.getUsbUsername()%>">Your Profile</a>
          <a class="dropdown-item" href="homepage.jsp" onclick="<%session.setAttribute("loggedUser",null);%>">Logout</a>
        </div>
      </li>
      <%
      	}
      %>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          HELP</a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#" onclick="myFunction()">Read FAQs</a>
          <a class="dropdown-item" href="#" onclick="myFunction()">Contact Us</a>
          <a class="dropdown-item" href="#" onclick="myFunction()">About Us</a>
        </div>
      </li>
    </ul>
  </div>
</nav>



<div class="container">
	<h2 align="right">Keep the world cleaner</h2>
	<h3 align="right" style = "margin-bottom: 50px">More rubbish you dump, more rewards you get</h3>
  	<form action="SearchServlet" method="post">
		<div class="md-form mt-0">
	  		<input class="form-control" type="text" placeholder="Search by center name, city, address..." aria-label="Search" 
	  		id="src" name="search">
	  		<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
		</div>
	</form>	
		<h4 align = "center" style = "margin-top: 100px;">How it works?</h4>

<!-- CAROUSEL -->

  <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
      <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
      <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="img/slide.png" class="d-block w-75" style = "margin:auto" alt="">
      </div>
      <div class="carousel-item">
        <img src="img/slide2.png" class="d-block w-75" style = "margin:auto" alt="">
      </div>
      <div class="carousel-item">
        <img src="img/slide3.png" class="d-block w-75" style = "margin:auto" alt="">
      </div>
    </div>
    <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>

<%
	if(user.getUsbUsername()==""){
%>
	<h4 align = "center" style = " text-decoration: underline; margin-top: 50px;">Are you an Ecological Island owner?</h4>	
	<div class="text-center">
    	<img src="img/owner.png" class="rounded" style = "height:200px" alt="">
    	<button class="btn" onclick="window.location.href = 'loginOwner.jsp';"></button>
	</div>
		<p align = "center" style ="margin-bottom:0px">Join BeEcological to increase your online visibility,</p>
		<p align = "center">manage booking schedule and attract new customers</p>
      
      <%
            	}else {
            %>
	<h4 align = "center" style = " text-decoration: underline; margin-top: 50px;">Welcome Back!</h4>	
	<form action="UserBookingListServlet" method="post">
	<div class="text-center">
    	<img src="img/user.png" class="rounded" style = "height:200px" alt="">
    	<button class="btn" type="submit"></button>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
	</div>
	</form>
		<p align = "center" style ="margin-bottom:0px">See your booking request, and your</p>
		<p align = "center">registered unloads!</p>
      <%}%>
</div>

















  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
	  <script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>