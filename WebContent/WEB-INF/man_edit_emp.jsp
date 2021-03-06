<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Manager</title>
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
		            <div class="top-bar-right">
		                <ul class="menu">
		                    <li><a href="/MovieMoose" class="button">Logout</a></li>           
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
		                </ul>
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
				<li id="movies"><a href="/MovieMoose/Manager?action=1" class="link"><b>Movies</b></a></li>
				<li id="movies"><a href="/MovieMoose/Manager?action=2" class="link"><b>Add Movie</b></a></li>
				<li id="movies"><a href="/MovieMoose/Manager?action=4" class="link"><b>Edit Movie</b></a></li>
				<li id="movies"><a href="/MovieMoose/Manager?action=6" class="link"><b>Delete Movie</b></a></li>
				<li id="best_emp"><a href="/MovieMoose/Manager?action=8" class="link"><b>Best Employee</b></a></li>
                <li id="movies"><a href="/MovieMoose/Manager?action=9" class="link"><b>Add Employee</b></a></li>
				<li id="movies"><a href="/MovieMoose/Manager?action=11" class="link"><b>Edit Employee</b></a></li>
				<li id="movies"><a href="/MovieMoose/Manager?action=13" class="link"><b>Delete Employee</b></a></li>
				<li id="movies"><a href="/MovieMoose/Manager?action=15" class="link"><b>Sales Report</b></a></li>
                <li id="most_rented_movies"><a href="/MovieMoose/Manager?action=15" class="link"><b>Most Rented Movie</b></a></li>	
                <li id="most_active_customer"><a href="/MovieMoose/Manager?action=15" class="link"><b>Most Active Customer</b></a></li>
                
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
        <div class="medium-12 columns" style="border-bottom:thin solid #1F1F1F;" ><h3 id="title" style="text-align:center">Edit Employee</h3></div>
    	<form method="POST" action="Manager?action=12">
    		<div class="medium-8 medium-centered columns" style="margin-top:15%; margin-bottom:15%;">
    			<div class="row">
		           	<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>SSN</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="ssn_txt" type="text" placeholder="Enter the SSN" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>First Name:</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="first_name_txt" type="text" placeholder="Enter the first name" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>Last Name:</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="last_name_txt" type="text" placeholder="Enter the last name" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>Address:</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="address_txt" type="text" placeholder="Enter the address" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>ZipCode:</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="zipcode_txt" type="text" placeholder="Enter the zipcode" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>Telephone#:</b></label>
    				</div>
    				<div class="medium-8 columns ">
      					<input name="telephone_txt" type="text" placeholder="Enter the telephone #" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>Hourly Wage:</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="ccn_txt" type="text" placeholder="Enter the hourly wage" />
    				</div>
    				<div class="medium-4 columns ">
            			<label style="padding:9px 0px"><b>ID:</b></label>
    				</div>
            		<div class="medium-8 columns ">
      					<input name="id_txt" type="text" placeholder="Enter the ID" />
    				</div>
  				</div>
        		<div class="top-bar-right">
                	<ul class="menu">
                   		<li style="padding-right:15px" id="register_btn"><input class="button" type="submit" value= "Edit Employee" /></li>
                	</ul>
         		</div>
    		</div>
    	</form>	
		
		<!--Content End-->
    </div>
</div>


</div>
</div>
<!--Main Body End-->



	


</body>
</html>
