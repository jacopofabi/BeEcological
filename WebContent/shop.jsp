<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<!doctype html>

<html lang="en">
<%@ page import="logic.bean.UserBean" %>
<%UserBean user = new UserBean();
if(session.getAttribute("loggedUser")!=null){
	user=(UserBean)session.getAttribute("loggedUser"); %>
<%
	}else { 
	user.setUsbUsername("");}
%>
<head>
  <meta charset="utf-8">
  <title>BeEcological - Shop</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/shop.css">
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

<div class = "container">
	<h1 style = "color:#589442"> Shop </h1>
	<hr></hr>
	<p class = "lead" style = "color:#589442">Amount of ecoPoints: <%=user.getEcopoints()%></p>
	<div class="container">
  <div class="row">
    <div class="col-sm" style = "text-align: center">
      <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/amazon5.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Amazon 5&#8364</p>
    	<p style = "color:#589442;">100 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="100" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
      </form>
      <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/zalando5.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Zalando 5&#8364</p>
    	<p style = "color:#589442;">200 ecoPoints</p>
    	    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="200" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/apple5.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Apple Store 5&#8364</p>
    	<p style = "color:#589442;">220 ecoPoints</p>
    	    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="220" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/google5.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Play Store 5&#8364</p>
    	<p style = "color:#589442;">100 ecoPoints</p>
    	    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="100" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
	   </form>

    </div>
    <div class="col-sm" style = "text-align: center">
       <form action="BuyServlet" method="post">
   		<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/amazon10.png)"></button>
   		<p style = "margin: 0;padding: 0">Gift Card Amazon 10&#8364</p>
    	<p style = "color:#589442;">200 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="200" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
   	   </form>
   	   <form action="BuyServlet" method="post">
   		<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/zalando10.png)"></button>
     	<p style = "margin: 0;padding: 0">Gift Card Zalando 10&#8364</p>
    	<p style = "color:#589442;">200 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="200" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/netflix10.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Netflix 10&#8364</p>
    	<p style = "color:#589442;">220 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="220" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/google10.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Play Store 10&#8364</p>
    	<p style = "color:#589442;">200 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="200" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
   	   </form>
    </div>
    <div class="col-sm" style = "text-align: center">
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/amazon25.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Amazon 25&#8364</p>
    	<p style = "color:#589442;">450 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="450" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/roma10.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Roma Store 10&#8364</p>
    	<p style = "color:#589442;">180 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="180" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/psn5.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card PlayStation Store 5&#8364</p>
    	<p style = "color:#589442;">200 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="200" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
       </form>
       <form action="BuyServlet" method="post">
    	<button type="submit" class="btn btn-primary" style = "border: 0px;height: 170px; width: 292px;background-image: url(img/cards/microsoft10.png)"></button>
    	<p style = "margin: 0;padding: 0">Gift Card Microsoft Store 10&#8364</p>
    	<p style = "color:#589442;">210 ecoPoints</p>
    	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    	<input type="hidden" name="pointsToDelete" value="210" />
    	<input type="hidden" name="totalPoints" value="<%=user.getEcopoints()%>" />
	   </form>

    </div>
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