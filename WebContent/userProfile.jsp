<!DOCTYPE html>

<html lang ="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <title>BeEcological - Profile</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/userProfile.css">
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
        <a class="nav-link" href="ShopServlet?param=<%=user.getUsbUsername()%>">SHOP</a>
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

<div class="container">
  <h1 style = "color:#589442;text-align: center"><%=user.getUsbUsername()%></h1>
  <p class = "lead" style = "color:#589442;text-align: center">Amount of ecoPoints: <%=user.getEcopoints()%></p>
  <h1 style = "color: #589442;margin-top: 1 0px">Personal Data</h1>
  <hr></hr>
  <div class="row">
    <div class="col-sm">
      <p></p>
      <div class= "row"><h4 style = "color:#589442;margin-left: 15px"> Name: </h4><p class= "lead" style = "margin-left:125px"><%=user.getUsbName()%></p></div>
      <div class= "row"><h4 style = "color:#589442;margin-left: 15px"> Surname: </h4><p class= "lead" style = "margin-left:94px"><%=user.getUsbSurname()%></p></div>
      <div class= "row"><h4 style = "color:#589442;margin-left: 15px"> Email: </h4><p class= "lead" style = "margin-left:132px;"><%=user.getUsbEmail()%></p></div>
      <div class= "row"><h4 style = "color:#589442;margin-left: 15px"> Cell Number: </h4><p class= "lead" style = "margin-left:54px"><%=user.getUsbPhone()%></p></div>
    </div>
    <div class="col-sm" style = "margin-left:200px;margin-right: -100px">
      <p></p>
      <button class = "btn btn-primary" type="submit"  style="height: 80px;">EDIT PERSONAL DATA</button>
      <form action="UserBookingListServlet" method="post">
      	<button class = "btn btn-primary" type="submit"  style="height: 80px; margin-top: 20px">SEE BOOKING REQUEST</button>
      	<input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
    </form>
    </div>
    <div class="col-sm">
      <p></p>
      <button class = "btn btn-primary" type="submit"  style="height: 80px;">CHANGE LOGIN CREDENTIALS</button>
      <form action="DeleteUserServlet" method="post">
        <button class = "btn delete-account" type="submit" style="height: 80px; margin-top: 20px">DELETE ACCOUNT</button>
        <input type="hidden" name="username" value="<%=user.getUsbUsername()%>" />
      </form>
    </div>
  </div>
  <h1 style = "color: #589442;margin-top: 30px">Unload History</h1>
  <hr></hr>
</div>

  <table class="table">
  <thead style= "background-color: #589442; color:white;">
    <tr>
      <th scope="col">Center</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
      <th scope="col">Waste</th>
      <th scope="col">WasteQuantity</th>
      <th scope="col">ecoPoints</th>
    </tr>
  </thead>
 <tbody>
 	<c:forEach items="${listUnloadUser}" var="list">
    <tr class = "table-row">
        <td>${list.wbCenter}</td>
        <td>${list.wbDate}</td>
        <td>${list.wbTime}</td>
        <td>${list.wbWaste}</td>
        <td>${list.wbWasteQuantity}</td>
        <td>${list.wbEcoPoints}</td>
    </tr>
</c:forEach>
  </tbody>
</table>





<hr></hr>
















  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
    <script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>