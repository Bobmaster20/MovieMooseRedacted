<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Customer</title>
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
		                <form method="POST" action="doSignUp">
    						<div class="medium-12 medium columns">
    							<div class="row">
						    		<div class="medium-7 columns ">
						      			<input type="text" name="ssn_text" placeholder="" />
						    		</div>
                                    <div class="medium-5 columns ">
						      			<ul class="menu" style="background-color:#ffffff">
                    						<li id="search_btn"><input class="button" type="submit" value= "Search" /></li>
                                            <li id="avd_search_btn"><input class="button" type="button" value= "Register" /></li>
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
	<div class="medium-2 columns ">
		<div class="top-bar-left">
			<ul class="vertical menu" >
	            <li><h6>&nbsp;</h6></li>
				<li ><a href="/MovieMoose/Customer?action=1" class="link"><b>Checkouts</b></a></li>
                <li ><a href="/MovieMoose/Customer?action=2" class="link"><b>Wishlist</b></a></li>
                <li ><a href="#" class="link"><b>History</b></a></li>
                <li ><a href="#" class="link"><b>Best-sellers</b></a></li>
                <li ><a href="#" class="link"><b>Recommended</b></a></li>	
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
        </ul>
        <!--Side Menu End-->
    </div>
    <div class="medium-9 columns ">
   		<!--Content-->
        <!--Title-->
		<div id="checkout">
		<script>
			if (${action} == 1){
				document.write("<div class=\"medium-12 columns \" style=\"border-bottom:thin solid #1F1F1F;\" ><h3 id=\"title\" style=\"text-align:center\">Checkouts</h3></div>")
			}
			if (${action} == 2){
				<!--Use this to write out your html output-->
				document.write("<a href=\"#\" class=\"button\">Sign Up</a>");
			}
		</script>
		</div>
		<!--Content End-->
    </div>
</div>


</div>
</div>
<!--Main Body End-->



	


</body>
</html>
