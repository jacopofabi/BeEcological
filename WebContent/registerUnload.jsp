<!doctype html>

<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="logic.bean.CenterOwnerBean,logic.bean.CenterBean" %>
<jsp:useBean id="owner" class="logic.bean.CenterOwnerBean" scope="session" />
<jsp:useBean id="center" class="logic.bean.CenterBean" scope="session" />
<%owner = (CenterOwnerBean)session.getAttribute("loggedOwner");
center = (CenterBean)session.getAttribute("centerInfo"); %>
<head>
  <meta charset="utf-8">
  <title>BeEcological - Register Unload</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/registerUnload.css">
</head>
<body>
<nav class="navbar navbar-expand-lg fixed-top navbar-dark" style = "background-color:#0B526D">
<!-- logo sulla navbar -->
  <a class="navbar-brand" href="HomeOwnerServlet?username=<%=owner.getCobUsername()%>&ownerphone=<%=owner.getCobPhone()%>
  &mail=<%=owner.getCobEmail()%>&centername=<%=center.getCbName()%>&address=<%=center.getCbAddress()%>&centerphone=<%=center.getCbPhone()%>">
    <img src="img/logo-owner.png" width="320" height=45 class="d-inline-block align-top" alt="">
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto"> <!-- ml mette i pulsanti della navbar a sinistra -->
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <%=owner.getCobUsername()%></a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="OwnerProfileServlet?username=<%=owner.getCobUsername()%>">Your Profile</a>
          <a class="dropdown-item" href="homepage.jsp" onclick=<%session.setAttribute("loggedOwner", null);%> >Logout</a>
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
<h1 style = "color:#0B526D;text-align: center">Register an Unload</h1>
<hr></hr>
<h2 style = "color:#0B526D">Booking Request</h2>

<hr></hr>
<p></p>
<table class="table">
  <thead style= "background-color: #0B526D; color:white;">
    <tr>
      <th scope="col">ID</th>
      <th scope="col">User</th>
      <th scope="col">Date</th>
      <th scope="col">Time</th>
    </tr>
  </thead>
  <tbody>
 	<c:forEach items="${bookAccept}" var="list">
    <tr class = "table-row">
    	<td>${list.ID}</td>
        <td>${list.user}</td>
        <td>${list.date}</td>
        <td>${list.time}</td>
    </tr>
</c:forEach>
  </tbody>
</table>  	




<form>
	<div class = "row" style = "margin-left: 287px">
		<p class = "lead" style = "margin-top: 12px;margin-right: 20px">Unloader username</p> <input class="form-control" required style = "width: 300px;margin-bottom:20px">
	</div>
	<div class = "row" style = "margin-left: 287px">
		<p class = "lead" style = "margin-top: 12px;margin-right: 80px">Unload date</p> <input type="date" class = "eco-datepicker" id="datePicker" required>
	</div>
	<div class = "row" style = "margin-left: 287px">
		<p class = "lead" style = "margin-top: 30px;margin-right: 80px">Unload time</p> <input class="form-control" required style = "width: 300px;margin-top:20px">
	</div>
</form>
<hr></hr>
<h2 style = "color:#0B526D">Type & Quantity</h2>
<p></p>
<div class="container">
  <div class="row">
    <div class="col-sm">

  <div class="input-group-prepend">
    <div class="input-group-text">Bulky Waste
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field1').disabled=!this.checked; document.getElementById('field1').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field1" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Glass
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field2').disabled=!this.checked; document.getElementById('field2').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field2" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Mixed material packaging
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field3').disabled=!this.checked; document.getElementById('field3').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field3" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Plastic packaging
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field4').disabled=!this.checked; document.getElementById('field4').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field4" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Metal packaging
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field5').disabled=!this.checked; document.getElementById('field5').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field5" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Toner and cartridges
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field6').disabled=!this.checked; document.getElementById('field6').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field6" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Battery
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field7').disabled=!this.checked; document.getElementById('field7').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field7" style= "height: 40px;width: 100px">
</div>
	
	</div>
    <div class="col-sm">
  
  <div class="input-group-prepend">
    <div class="input-group-text">Organic Waste
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field8').disabled=!this.checked; document.getElementById('field1').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field8" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Electronic equipment
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field9').disabled=!this.checked; document.getElementById('field2').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field9" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Cold and climate
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field10').disabled=!this.checked; document.getElementById('field3').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field10" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Wood waste
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field11').disabled=!this.checked; document.getElementById('field4').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field11" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Mercury
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field12').disabled=!this.checked; document.getElementById('field5').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field12" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Textile
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field13').disabled=!this.checked; document.getElementById('field6').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field13" style= "height: 40px;width: 100px">
</div>

  <div class="input-group-prepend">
    <div class="input-group-text">Tires
      <input style = "margin-left: 10px" type="checkbox" onchange="document.getElementById('field14').disabled=!this.checked; document.getElementById('field7').value = '' ">
    </div>
  <input disabled type="text" placeholder = "kg" class="form-control" id = "field14" style= "height: 40px;width: 100px">
</div>
    </div>
  </div>
</div>

<hr></hr>
</div>
<div style = "text-align: center">
  		<button class = "btn btn-primary" type="submit"  style="height: 70px; margin-top: 20px">CONFIRM BOOKING</button>
</div>
<p></p>









  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
  <script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>