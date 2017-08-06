<%@ page errorPage="index.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='http://fonts.googleapis.com/css?family=Bitter' rel='stylesheet' type='text/css'>
<link rel="stylesheet" type="text/css" href="CSS/form10.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Student Page</title>
</head>
<body>
<%
String value = request.getParameter("result");
%>


<div class="form-style-10">
<h1>Sign Up Now!<span>Sign up and Explore your skills</span></h1>
<form action="RegisterStudentController" method="post">
    <div class="section"><span>1</span>First Name &amp; Address</div>
    <div class="inner-wrap">
        <label>Your Full Name <input type="text" name="name" required/></label>
        <label>Address <textarea name="address" required></textarea></label>
    </div>
	<div class="section"><span>2</span>Skills</div>
    <div class="inner-wrap">
        <label>Skills <textarea name="skill" required></textarea></label>
    </div>
	
    <div class="section"><span>3</span>Email &amp; Phone</div>
    <div class="inner-wrap">
        <label>Email Address <input type="email" name="email" required/></label>
        <label>Phone Number <input type="text" name="mobile" required/></label>
    </div>

    <div class="section"><span>4</span>Username & Password</div>
        <div class="inner-wrap">
        <label>Username <input type="text" name="userName" required/></label>
        <label>Password <input type="password" name="password" required/></label>
    </div>
    <div class="button-section">
     <input type="submit" name="Sign Up" />
    </div>
</form>
</div>

<%
	if(value!= null){
	out.print(value);
	}
	%>	
</center>
</body>
</html>