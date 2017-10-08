<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style> 
	@font-face {
	    font-family: brezynFont;
	    src: url(../css/Cherry.ttf);
	}
	
	body {
	    font-family: brezynFont;
	}
	
	.font {
	    font-family: brezynFont;
    	border-radius: 10px;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password</title>
</head>
<body>
	<script language="javascript">
		function fncSubmit()
		{
		
		if(document.brezyn.password.value == "")
		{
			alert('Please input password');
			document.brezyn.password.focus();
			return false;
		} 
		
		if(document.brezyn.rePassword.value == "")
		{
			alert('Please input Confirm Password');
			document.brezyn.rePassword.focus(); 
			return false;
		} 
		
		if(document.brezyn.password.value != document.brezyn.rePassword.value)
		{
			alert('Confirm Password Not Match');
			document.brezyn.rePassword.focus(); 
			return false;
		} 
		
		document.brezyn.submit();
	}
	</script>
	<form name="brezyn" method="post" action="../rest/user/submit" onsubmit="return fncSubmit();">
		<center>
			<input type="hidden" name="token" value="<%= request.getParameter("token") %>"/>
			<img alt="" src="../images/brezyn_icons.png" style="height: 100px;">
			<br />
			Password : <input type="password" name="password" />
			<br /><br />
			Confirm Password : <input type="password" name="rePassword" />
			<br /><br />
			<input type="submit" value="Submit" class="font"/>
		</center>
	</form>
</body>
</html>