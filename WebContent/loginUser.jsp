<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    
<!doctype html>

<html lang="en">

<jsp:useBean id="userBean" scope="session" class="logic.Bean.UserBean"/>

<head>
  <meta charset="utf-8">
  <title>BeEcological - Login</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/loginUser.css">
  
      <%String message = (String)request.getAttribute("alertMsg");
    if(message != null){%>

        <script type="text/javascript">
            var msg = "<%=message%>";
            alert(msg);
        </script>
   <%} %>
   
</head>

<nav class="navbar navbar-expand-lg fixed-top navbar-dark" style = "background-color:#589442">
<!-- logo sulla navbar -->
  <a class="navbar-brand" href="homepage.jsp">
    <img src="img/logo-white.png" width="250" height=45 class="d-inline-block align-top" alt="">
  </a>
  
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
 
  
    <ul class="navbar-nav ml-auto"> <!-- ml mette i pulsanti della navbar a sinistra -->
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
  <div class="row">
    <div class="col-sm"> <!-- Colonna Sinistra -->
    	<h1 align = "center" style = "color:#589442"> Login </h1>
    	<hr></hr>
    	<form action="LoginUserServlet" method="post">
   			<div class="form-group">
  				<label for="usr">Username:</label>
 				<input type="text" class="form-control" id="usr" name="username">
			</div>
			<div class="form-group">
  				<label for="pwd">Password:</label>
  				<input type="password" class="form-control" id="pwd" name="password">
			</div>
			<div class = "text-center">
				<button class = "btn btn-primary" type="submit">LOG IN</button>
			</div>
		</form>
		<hr></hr>
		<h3 align = "center" style = "color:#0B526D;margin-top: 50px;margin-bottom: 30px"> BeEcological for Center Owners </h3>
		<div class = "text-center">
			<a href="loginOwner.jsp" class="btn btn-primary" style = "background-color: #0B526D" role="button" aria-pressed="true">CONTINUE</a>
		</div>
    </div>
    <div class="col-sm"> <!-- Colonna Destra -->
    	<h1 align = "center" style = "color:#589442"> Register </h1>
    	<hr></hr>
    	<form>
  <div class="form-row">
    <div class="col-md-4 mb-3">
      <label for="validationDefault01">First name</label>
      <input type="text" class="form-control" id="validationDefault01" required>
    </div>
    <div class="col-md-4 mb-3">
      <label for="validationDefault02">Last name</label>
      <input type="text" class="form-control" id="validationDefault02" required>
    </div>
    <div class="col-md-4 mb-3">
      <label for="validationDefaultUsername">Username</label>
      <div class="input-group">
        <div class="input-group-prepend">
          <span class="input-group-text" id="inputGroupPrepend2">@</span>
        </div>
        <input type="text" class="form-control" id="validationDefaultUsername"  aria-describedby="inputGroupPrepend2" required>
      </div>
    </div>
  </div>
  <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationDefault03">E-mail</label>
      <input type="text" class="form-control" id="email" required>
    </div>
    <div class="col-md-6 mb-3">
      <label for="validationDefault03">Phone Number</label>
      <input type="text" class="form-control" id="phone" required>
    </div>
    <div class="col-md-6 mb-3">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" id="pass" required>
    </div>
    <div class="col-md-6 mb-3">
        <label for="pwd">Confirm Password:</label>
        <input type="password" class="form-control" id="confirmPass" required>
    </div>
  </div>
  	<div class = "text-center">
  		<button class="btn btn-primary" type="submit">SIGN UP</button>
	</div>

</form>
    </div>
  </div>
</div>






<body>





  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
    <script>
function myFunction() {
  alert("Functionality not implemented.");
}
</script>
</body>
</html>