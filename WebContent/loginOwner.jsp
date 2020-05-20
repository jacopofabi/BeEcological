<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8">
  <title>BeEcological - Login</title>
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="styles/loginOwner.css">
  
        <%String message = (String)request.getAttribute("alertMsg");
    if(message != null){%>

        <script type="text/javascript">
            var msg = "<%=message%>";
            alert(msg);
        </script>
   <%} %>
   
</head>

<nav class="navbar navbar-expand-lg fixed-top navbar-dark" style = "background-color:#0B526D">
<!-- logo sulla navbar -->
  <a class="navbar-brand" href="homepage.jsp">
    <img src="img/logo-owner.png" width="320" height=45 class="d-inline-block align-top" alt="">
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
    	<h1 align = "center" style = "color:#0B526D"> Login </h1>
      <hr></hr>
      <form action="LoginOwnerServlet" method="post">
   		<div class="form-group">
  			<label for="usr">Username:</label>
 			<input type="text" class="form-control" id="usr" name="username">
		</div>
		<div class="form-group">
  			<label for="pwd">Password:</label>
  			<input type="password" class="form-control" id="pwd" name="password">
		</div>
		<div class = "text-center">
			<button class = "btn btn-primary btn-lg" type="submit"  style="background-color: #0B526D">LOG IN</button>
		</div>
	  </form>
    <hr></hr>
    <h3 align = "center" style = "color:#589442; margin-top: 50px; margin-bottom: 30px"> BeEcological for Users </h3>
    <div class = "text-center">
      <a href="loginUser.jsp" class="btn btn-primary " role="button"   style="background-color: #589442" aria-pressed="true">CONTINUE</a>
    </div>
    <hr></hr>
    </div>
    <div class="col-sm"> <!-- Colonna Destra -->
    	<h1 align = "center" style = "color:#0B526D"> Register </h1>
      <hr></hr>
    	<form>
      <h4> Personal Data</h4>
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
      <input type="text" class="form-control" id="validationDefault03" required>
    </div>
    <div class="col-md-6 mb-3">
      <label for="validationDefault03">Phone Number</label>
      <input type="text" class="form-control" id="validationDefault03" required>
    </div>
    <div class="col-md-6 mb-3">
        <label for="pwd">Password:</label>
        <input type="password" class="form-control" id="pwd" required>
    </div>
    <div class="col-md-6 mb-3">
        <label for="pwd">Confirm Password:</label>
        <input type="password" class="form-control" id="pwd" required>
    </div>
  </div>
  <hr></hr>
  <h4 style = "margin-top: 50px"> Center Information</h4>
    <div class="form-row">
    <div class="col-md-6 mb-3">
      <label for="validationDefault03">Ecological Island Name</label>
      <input type="text" class="form-control" id="validationDefault03" required>
    </div>
     <div class="col-md-6 mb-3">
      <label for="validationDefault03">City</label>
      <input type="text" class="form-control" id="validationDefault03" required>
    </div>
    <div class="col-md-6 mb-3">
      <label for="validationDefault03">Address</label>
      <input type="text" class="form-control" id="validationDefault03" required>
    </div>
    <div class="col-md-3 mb-3">
        <label for="validationDefault03">n°</label>
        <input type="text" class="form-control" id="validationDefault03" required>
    </div>
    <div class="col-md-3 mb-3">
        <label for="validationDefault03">CAP</label>
        <input type="text" class="form-control" id="validationDefault03" required>
    </div>
  </div>


  	<div class = "text-center">
  		<button class="btn btn-primary btn-lg" type="button" style="background-color: #0B526D">SIGN UP</button>
      <hr></hr>
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