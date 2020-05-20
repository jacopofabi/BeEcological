<!doctype html>

<html lang="en">
<%@ page import="logic.bean.CenterOwnerBean,logic.bean.CenterBean" %>
<jsp:useBean id="owner" class="logic.bean.CenterOwnerBean" scope="session" />
<jsp:useBean id="center" class="logic.bean.CenterBean" scope="session" />
<%owner = (CenterOwnerBean)session.getAttribute("loggedOwner");
center = (CenterBean)session.getAttribute("centerInfo"); %>
<head>
  <meta charset="utf-8">
  <title>BeEcological for Managers - Profile</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/ownerProfile.css">
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

<div class="container">
  <h1 style = "color:#0B526D;text-align: center"><%=owner.getCobUsername()%></h1>
  <h1 style = "color: #0B526D;margin-top: 1 0px">Personal Data</h1>
  <hr></hr>
  <div class="row">
    <div class="col-sm">
      <p></p>
      <div class= "row"><h4 style = "color:#0B526D;margin-left: 15px"> Name: </h4><p class= "lead" style = "margin-left:125px"><%=owner.getCobName()%></p></div>
      <div class= "row"><h4 style = "color:#0B526D;margin-left: 15px"> Surname: </h4><p class= "lead" style = "margin-left:94px"><%=owner.getCobSurname()%></p></div>
      <div class= "row"><h4 style = "color:#0B526D;margin-left: 15px"> Email: </h4><p class= "lead" style = "margin-left:132px;"><%=owner.getCobEmail()%></p></div>
      <div class= "row"><h4 style = "color:#0B526D;margin-left: 15px"> Cell Number: </h4><p class= "lead" style = "margin-left:54px"><%=owner.getCobPhone()%></p></div>
    </div>
    <div class="col-sm" style = "margin-left:200px;margin-right: -100px">
      <p style = "margin-top: 60px"></p>
      <button class = "btn btn-primary" type="submit" onclick="myFunction()" style="height: 80px;">EDIT PERSONAL DATA</button>
    </div>
    <div class="col-sm">
      <p style = "margin-top: 60px"></p>
      <button class = "btn btn-primary" type="submit" onclick="myFunction()" style="height: 80px;">CHANGE LOGIN CREDENTIALS</button>
    </div>
  </div>
  <hr></hr>
<form action="DeleteOwnerServlet" method="post">
  <div style = "text-align:center">
      <button class = "btn delete-account" type="submit"  style="height: 80px; margin-top: 20px">DELETE ACCOUNT</button>
      <input type="hidden" name="username" value="<%=owner.getCobUsername()%>" />
  </div>
</form>
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