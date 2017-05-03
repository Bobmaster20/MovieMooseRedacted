<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<link href="css/app.css" rel="stylesheet" type="text/css">
<link href="css/foundation.css" rel="stylesheet" type="text/css">
<link href="css/foundation.min.css" rel="stylesheet" type="text/css">
</head>

<body>

<!-- TOP BAR -->
<div class="top-bar-container" data-sticky-container>
    <div class="top-bar" style="background-color:#1F1F1F;">
       	<div class="row">
			<div class="medium-12 columns ">
	            <div class="top-bar-left">
	                <ul class="menu" style="background-color:#1F1F1F;">
	                    <li><img src="img/Moose.png" alt="JTE Image" height="64" width="64" ></li>
	                    <li><a href="#" class="logo-name" style="color:#1779BA; background-color:#1F1F1F;">Movie Moose</a></li>
	                </ul>
	            </div>	            
	        </div>
        </div>
    </div>
</div>

<div style="margin-top:10%">
	<form method="POST" action="Customer">
    <div class="medium-6 medium-centered columns">
    	<div class="row">
        	<div class="medium-1 columns ">
            	<label style="padding:9px 0px"><b>SSN:</b>
                </label>
    		</div>
    		<div class="medium-11 columns ">
      			<input type="text" name="ssn_text" placeholder="Enter your SSN" />
    		</div>
  		</div>
        <div class="top-bar-right">
                <ul class="menu">
                    <li style="padding-right:15px" id="sign_up_btn"><a href="#" class="button">Sign Up</a></li>
                    <li id="login_btn"><input class="button" type="submit" value= "Login" /></li>
                </ul>	
         </div>
    </div>
    </form>
    <form method="POST" action="Employee">
    <div class="medium-6 medium-centered columns">
    	<div class="row">
        	<div class="medium-1 columns ">
            	<label style="padding:9px 0px"><b>SSN:</b>
                </label>
    		</div>
    		<div class="medium-11 columns ">
      			<input type="text" name="ssn_text" placeholder="Enter your SSN" />
    		</div>
  		</div>
        <div class="top-bar-right">
                <ul class="menu">
                    <li id="login_btn"><input class="button" type="submit" value= "Login as Employee" /></li>
                </ul>	
         </div>
    </div>
    </form>
</div>

</body>
</html>