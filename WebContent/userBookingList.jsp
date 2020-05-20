<!DOCTYPE html>

<html lang ="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="logic.Bean.UserBean" %>
<%UserBean user = new UserBean();
if(session.getAttribute("loggedUser")!=null){
	user=(UserBean)session.getAttribute("loggedUser"); %>
<%
	}else { 
	user.setUsbUsername("");}
%>
<head>
  <meta charset="utf-8">
  <title>BeEcological - User Booking List</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/userBookingList.css">
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


<ul class="nav nav-pills" id="myTab" role="tablist">
  <li class="nav-item"><a class="nav-link active" id="tab1-tab" data-toggle="tab" href="#tab1" role="tab" aria-controls="tab1" aria-selected="true">Waiting booking request </a></li>
  <li class="nav-item"><a class="nav-link" id="tab2-tab" data-toggle="tab" href="#tab2" role="tab" aria-controls="tab2" aria-selected="false">Accepted booking request</a></li>
  <li class="nav-item"><a class="nav-link" id="tab3-tab" data-toggle="tab" href="#tab3" role="tab" aria-controls="tab3" aria-selected="false">Refused booking request</a></li>
</ul>




<div class="tab-content" id="myTabContent">
  <div class="tab-pane p-4 fade show active" id="tab1" role="tabpanel" aria-labelledby="tab1-tab">
  	<table class="table">
  <thead style= "background-color: #589442; color:white;">
    <tr>
      <th scope="col">Center</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${bookWait}" var="list">
    <tr class = "table-row">
        <td>${list.bbCenter}</td>
        <td>${list.bbDate}</td>
        <td>${list.bbTime}</td>
    </tr>
</c:forEach>
  </tbody>
</table>
  </div>



  <div class="tab-pane p-4 fade" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
  	  	<table class="table">
  <thead style= "background-color: #589442; color:white;">
    <tr>
      <th scope="col">Center</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${bookAccept}" var="list">
    <tr class = "table-row">
        <td>${list.bbCenter}</td>
        <td>${list.bbDate}</td>
        <td>${list.bbTime}</td>
    </tr>
</c:forEach>
  </tbody>
</table>  	
  </div>


  <div class="tab-pane p-4 fade" id="tab3" role="tabpanel" aria-labelledby="tab3-tab">
  	  	<table class="table">
  <thead style= "background-color: #589442; color:white;">
    <tr>
      <th scope="col">Center</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${bookRefuse}" var="list">
    <tr class = "table-row">
        <td>${list.bbCenter}</td>
        <td>${list.bbDate}</td>
        <td>${list.bbTime}</td>
    </tr>
</c:forEach>
  </tbody>
</table>



  	
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