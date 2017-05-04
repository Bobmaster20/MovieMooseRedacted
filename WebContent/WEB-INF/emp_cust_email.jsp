<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Employee</title>
<link href="css/app.css" rel="stylesheet" type="text/css">
<link href="css/foundation.css" rel="stylesheet" type="text/css">
<link href="css/foundation.min.css" rel="stylesheet" type="text/css">
<link href="js/Employee.js" rel="stylesheet" type="text/css">
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
		            <div class="top-bar-right">
		                <ul class="menu">
		                    <li><a href="#" class="button">Logout</a></li>           
		                </ul>                
		            </div>
		        </div>
            </div>
        </div>
        <div class="top-bar" style="background-color:#ffffff; border-color:#1F1F1F; border-width:medium; border-bottom-style:solid">
        	<div class="row">
				<div class="medium-12 columns ">
		            <div class="top-bar-left">
		                <ul class="menu">
		                	<li style="background-color:#ffffff"><h6 id="first_name" style="color:#1F1F1F"><b>${person.firstName}</b></h6></li>
		                    <li style="background-color:#ffffff"><h6>&nbsp;</h6></li>
		                    <li style="background-color:#ffffff"><h6 id="last_name" style="color:#1F1F1F"><b>${person.lastName}</b></h6></li>
                            <li style="background-color:#ffffff"><h6>&nbsp;</h6></li>
                            <li style="background-color:#ffffff"><h6>&nbsp;</h6></li>
                            <li style="background-color:#ffffff"><h6>&nbsp;</h6></li>
                            <li style="background-color:#ffffff"><h6>&nbsp;</h6></li>
                            <li><a href="#" class="button">Edit Account</a></li>  
		                </ul>
		            </div>
                    <div class="top-bar-right">
		                <form method="POST" action="Employee?action=10">
    						<div class="medium-12 medium columns">
    							<div class="row">
						    		<div class="medium-7 columns ">
						      			<input type="text" name="search_text" placeholder="Enter Customer ID" />
						    		</div>
                                    <div class="medium-5 columns " >
						      			<ul class="menu" style="background-color:#ffffff">
                    						<li id="search_btn"><input class="button" type="submit" value= "Recommendations" /></li>
                						</ul>
						    		</div>
						  		</div>
						    </div>
					    </form>            
		            </div>
                </div>
            </div>
        </div>
</div>
<!--Main body-->
<div class="row">
<div class="medium-12 medium-centered columns" style="background: #FFFFFF; box-shadow: 1px 2px 4px rgba(0, 0, 0, .5);" >
<!--Side menu-->
<div class="row">
	<div class="medium-3 columns ">
		<div class="top-bar-left">
			<ul class="vertical menu" >
	            <li><h6>&nbsp;</h6></li>
				<li id="place_order_btn"><a href="/MovieMoose/Employee?action=1" class="link"><b>Place Order</b></a></li>
                <li id="cust_email_btn"><a href="/MovieMoose/Employee?action=2" class="link"><b>Customer Email</b></a></li>
                <li id="cust_email_btn"><a href="/MovieMoose/Employee?action=4" class="link"><b>Add Customer</b></a></li>
                <li id="cust_email_btn"><a href="/MovieMoose/Employee?action=6" class="link"><b>Edit Customer</b></a></li>
                <li id="cust_email_btn"><a href="/MovieMoose/Employee?action=8" class="link"><b>Delete Customer</b></a></li>
		    </ul>
		</div>
    </div>
    <div class="medium-1 columns" >
    	<h6>&nbsp;</h6>
    	<ul class="vertical menu" style="border-left: thin solid #1F1F1F;">
	            <li><h6>&nbsp;</h6></li>
                <li><h6>&nbsp;</h6></li>
                <li><h6>&nbsp;</h6></li>
                <li><h6>&nbsp;</h6></li>
                <li><h6>&nbsp;</h6></li>
        </ul>
        <!--Side Menu End-->
    </div>
    <div class="medium-8 columns ">
    
   		<!--Content-->
        <!--Title-->
        
        <div class="medium-12 columns" style="border-bottom:thin solid #1F1F1F;" ><h3 id="title" style="text-align:center">Customer Email</h3></div>
    	<table style="width:100%">
		<tr>
			<th>SSN</th>
			<th>First Name</th>
			<th>Last Name</th> 
			<th>Address</th>
			<th>Email</th>
		</tr>
		<c:forEach items="${mailingList}" var="mailing" >
          <tr>
             <td style="text-align:center">${mailing.SSN}</td>
             <td style="text-align:center">${mailing.firstName}</td>
             <td style="text-align:center">${mailing.lastName}</td>
             <td style="text-align:center">${mailing.address}</td>
             <td style="text-align:center">${mailing.email}</td>
          </tr>
       </c:forEach>
		</table>
        	
                		
		<!--Content End-->
    </div>
</div>
<div>


</div>
</div>
<!--Main Body End-->



	


</body>
</html>
