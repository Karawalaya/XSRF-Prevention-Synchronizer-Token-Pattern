<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!--
This is the legitimate user's Money Transfering page.

Here contains the form which is presented by the bank itself to the legitimate user, which can be used by him/her
to transfer money to another account.

Here, the XSRF mitigation is in place, where there is a Javascript code, which using an Ajax call requests and 
retrieves the client's synchronizer token from the server (SyncTokenProvider Controller) and binds it to the form as a
 hidden field which then is sent to the server.
-->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<link rel="stylesheet" href="/xsrfsynctokenptrn/resrcs/css&js/customized02.css">
		<link rel="stylesheet" href="/xsrfsynctokenptrn/resrcs/css&js/bootstrap/css/bootstrap.css">
		
		<title>Legitimate Form</title>
	</head>
	
	<body>
		<%
			response.setHeader("Cache-Control","no-cache, must-revalidate"); //Forces caches to obtain a new copy of the page from the origin server
			response.setHeader("Cache-Control","no-store"); //Directs caches not to store the page under any circumstance
			response.setDateHeader("Expires", 0); //Causes the proxy cache to see the page as "stale"
			response.setHeader("Pragma","no-cache"); //HTTP 1.0 backward compatibility
			
			if(session.getAttribute("sessionUserName") == null)
				response.sendRedirect("/xsrfsynctokenptrn/views/login.jsp");
		%>
		<h2 align="center">Fill and Submit the below Money Transference Form!</h2>
		<!-- 
			FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM FORM
	 	-->
   		<form style="align:center" name="moneyTransferenceForm" action="/xsrfsynctokenptrn/TransferenceController" method="post">
			<div class="container">
				<label for="username"><b>Receiver Account</b></label>
				<input type="text" placeholder="Receiver's Account Number" name="accountNo" required>

				<label for="password"><b>Transfer Amount</b></label>
				<input type="text" placeholder="Amount to Transerf (in LKR)" name="transferAmount" required>

				<button type="submit" style="border-radius: 25px">Transfer</button>
			</div>
		</form>
		<form action="/xsrfsynctokenptrn/LogoutController">
			<button type="submit" style="border-radius: 25px; background-color: #0000a0">Logout</button>
		</form>

		<script type="text/javascript">
			function callAjax(url, callback){
			    var xmlhttp;
			    xmlhttp = new XMLHttpRequest();
			    xmlhttp.responseType = 'json';
			    xmlhttp.onreadystatechange = function(){
			        if (xmlhttp.readyState == 4 && xmlhttp.status == 200){
			            callback(xmlhttp.response);
			        }
			    }
			    xmlhttp.open("GET", url, true);
			    xmlhttp.send();
			}
			
			function myCallback(resp){
				var form = document.forms['moneyTransferenceForm'];
				   var element = document.createElement("INPUT");
				   element.type = "hidden";
				   element.name = "hiddenTokenField";
				   element.value = resp["syncToken"];
				   form.appendChild(element);
			}

			callAjax("../SyncTokenProvider", myCallback);
		</script>
	</body>
</html>