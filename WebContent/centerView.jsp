<!doctype html>

<html lang="en">
<%@ page import="logic.bean.UserBean" %>
<%@ page import="logic.bean.CenterBean" %>
<%@ page import="logic.bean.CenterOwnerBean" %>
<%UserBean user = new UserBean();
if(session.getAttribute("loggedUser")!=null){
	user=(UserBean)session.getAttribute("loggedUser"); %>
<%
	}else { 
	user.setUsbUsername("");}
%>
	
<%CenterBean center = (CenterBean)session.getAttribute("centerInfo");
  CenterOwnerBean owner = (CenterOwnerBean)session.getAttribute("ownerInfo");%>
<head>
  <meta charset="utf-8">
  <title>BeEcological - Center Page</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/centerView.css">
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
          <a class="dropdown-item" href="homepage.jsp" onclick=<%session.setAttribute("loggedUser", null);%>>Logout</a>
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
  	<form action="SearchServlet" method="post">
		<div class="md-form mt-0">
	  		<input class="form-control" type="text" placeholder="Search by center name, city, address..." aria-label="Search" 
	  		id="src" name="search">
	  		<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
		</div>
	</form>	

<div class = "container">
	<p></p>
	<h1 style = "color:#589442"> <%=center.getCbName()%> </h1>
	<hr></hr>
</div>

<div class="container">
  <div class="row">
    <div class="col-sm">
      <p></p>
      <div style="width:382px;height:342px;border:1px solid #0B526D;margin-left: 50px">
      	<img src="img/jpeg/<%=center.getCbName()%>.jpg" width="380" height=340 class="d-inline-block align-top" alt="">
      </div>
    </div>
    <div class="col-sm" style="margin-left: 50px">
      <h4 style = "color:#589442;margin-top: 10px">Address</h4>
      <p> <%=center.getCbAddress()%></p>
      <h4 style = "color:#589442; margin-top: 60px">Opening Hours</h4>
      <div style="width:200px;height:200px;border:1px solid #0B526D;">Hours-table</div>
    </div>
    <div class="col-sm" style = "text-align: center">
    	<h3 style="color: #589442;text-decoration: underline;margin-top: 10px;text-align: center"> Book for this center!</h3>
    	<div class="container">
<form action="BookingRequestServlet" method="post">
  <div class="form-group">
    <p> Select a day </p>
    <input type="date" id="datePicker" name="date">
  </div>
  <div class="form-group">
    <p style = "margin-top: 30px"> Select an hour </p>
    <select class="form-control" id="exampleFormControlSelect1" name="time">
      <option>08:30</option>
      <option>09:00</option>
      <option>09:30</option>
      <option>10:00</option>
      <option>10:30</option>
      <option>11:00</option>
      <option>11:30</option>
      <option>12:00</option>
      <option>12:30</option>
      <option>14:00</option>
      <option>14:30</option>
      <option>15:00</option>
      <option>15:30</option>
      <option>16:00</option>
      <option>16:30</option>
      <option>17:00</option>
      <option>17:30</option>
      <option>18:00</option>
      <option>18:30</option>
      <option>19:00</option>
      <option>19:30</option>
    </select>
    <input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    <input type="hidden" name="centername" value="<%=center.getCbName()%>" />
  </div>
  		<button class = "btn btn-primary" type="submit"  style="height: 70px; margin-top: 20px">CONFIRM BOOKING</button>
</form>
</div>
    </div>
  </div>
  <hr></hr>
    <div class = "row">
    <div class="col-sm" style="text-align: center">
      <p></p>
          <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2979.22256141332!2d13.346590014808037!3d41.694130884886874!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x13255a1d5fbd4181%3A0xc9f6683e37eff05f!2sVia%20dei%20tassi%2C%2003011%20Tecchiena%20FR!5e0!3m2!1sit!2sit!4v1581710711981!5m2!1sit!2sit" width="400" height="400" frameborder="0" style="border:0;" allowfullscreen=""></iframe>
    </div>
    <div class="col-sm">
    	<p></p>
    	<h3 style = "color: #589442;">Contact Us</h3>
    	<p style = "margin-top:90px"></p>
      <div class= "row"><h4 style = "color:#589442"> Email: </h4><p class= "lead" style = "margin-left:200px;"><%=owner.getCobEmail()%></p></div>
      <div class= "row"><h4 style = "color:#589442"> Cell Number: </h4><p class= "lead" style = "margin-left:122px"><%=owner.getCobPhone()%></p></div>
      <div class= "row"><h4 style = "color:#589442"> Phone Number: </h4><p class= "lead" style = "margin-left:94px"><%=center.getCbPhone()%></p></div>
    </div>

</div>
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