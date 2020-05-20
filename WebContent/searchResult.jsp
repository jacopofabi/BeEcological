<!doctype html>
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="logic.Bean.UserBean" %>
<%@ page import="logic.Bean.CenterBean" %>
<%UserBean user = new UserBean();
if(session.getAttribute("loggedUser")!=null){
	user=(UserBean)session.getAttribute("loggedUser"); %>
<%
	}else { 
	user.setUsbUsername("");}
%>
<head>
  <meta charset="utf-8">
  <title>BeEcological - Search Result</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/searchResult.css">
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
  	<form action="SearchServlet" method="post">
		<div class="md-form mt-0">
	  		<input class="form-control" type="text" placeholder="Search by center name, city, address..." aria-label="Search" 
	  		id="src" name="search">
	  		<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
		</div>
	</form>	

<div class = "container">
	<p></p>
	<div class= "row"><h1 style = "color:#589442"> Search result for: </h1><p class= "beEco-title-2"><%=session.getAttribute("textSearched")%></p></div> 
<table class="table">
  <thead style= "background-color: #589442; color:white;">
    <tr>
      <th scope="col">Name</th>
      <th scope="col">City</th>
      <th scope="col">CAP</th>
      <th scope="col">Address</th>
      <th scope="col">Phone</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${listOfCenter}" var="list">
    <tr class = "table-row" data-href='CenterPageServlet?username=<%=user.getUsbUsername()%>&centername=${list.cbName}&address=${list.cbAddress}&phone=${list.cbPhone}'>
        <td>${list.cbName}</td>
        <td>${list.cbCity}</td>
        <td>${list.cbCap}</td>
        <td>${list.cbAddress}</td>
        <td>${list.cbPhone}</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</div>
<hr></hr>















  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script>
  $(document).ready(function($) {
	    $(".table-row").click(function() {
	        window.location = $(this).data("href");
	    });
	});
  </script>
    <script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>