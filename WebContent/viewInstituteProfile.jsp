<%@page import="com.oec.dao.InstituteDAO"%>
<%@ page errorPage="index.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.oec.vo.InstituteVO"
    import="com.oec.vo.MainLoginVO"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%InstituteVO instVo = (InstituteVO)request.getAttribute("instituteVo");
MainLoginVO mainVo = (MainLoginVO)request.getSession().getAttribute("mainLoginVO");
String instituteName = InstituteDAO.getInstituteNameByClientId(mainVo.getClient_id()); %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome&nbsp; <%=instituteName%></title>
	<style type="text/css">
  ul {list-style: none;padding: 0px;margin: 0px;}
  ul li {display: block;position: relative;float: left;border:1px}
  li ul {display: none;}
  ul li a {display: block;padding: 5px 10px 5px 10px;text-decoration: none;
           white-space: nowrap;color:#000000;}
  ul li a:hover {background:#000000}
  li:hover ul {display: block; position: absolute;}
  li:hover li {float: none;}
  li:hover a {background:#CCCCCC;}
  li:hover li a:hover {background:#FFFFFF}
  #drop-nav li ul li {border-top: 0px;}
</style>

    <!-- Bootstrap Core CSS -->
    <link href="CSS/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="CSS/landing-page.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,100,300italic,400italic,100italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<meta http-equiv="content-type" content="text/html; charset=utf-8"/>

<link rel="stylesheet" type="text/css" href="CSS/form1.css">
</head>

<body>
<%
String value = request.getParameter("result");

%>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="#"><b><%=instituteName%></b></a>
            </div>
			<center><%
	if(value!= null){
	%>
	<%=value %>
	<%
	}
	%></center>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right" id="drop-nav">
  					<li><a href="LoginController?client_id=<%=mainVo.getClient_id()%>&username=<%=mainVo.getUsername()%>&password=<%=mainVo.getPassword()%>">Home</a>
					  </li>
  					<li><a href="#">Exam</a>
    					<ul>
						  <li><a href="createExam.jsp">New Exam</a></li>
						  <li><a href="ExamHistoryController">History</a></li>
						  <li><a href="GetConductedExamController">Result</a></li>
						</ul>
					  </li>
					  <li><a href="#">Student Management</a>
						<ul>
						  <li><a href="ViewStudentExamListController">View Student</a></li>
						  <li><a href="StudentAppliedController">Student Applied</a></li>
						</ul>
					  </li>
					  <li><a href="#">Account</a>
						<ul>
						  <li><a href="ViewProfileController">Profile</a></li>
						</ul>
					  </li>
					  <li><a href="LogoutController">Logout</a>
					  </li>
					</ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Header -->
    <div class="intro-header">
        <div class="container">	
        <div style="width: 100px;position: relative;left:400px;">		
 <p style="position:relative;left:150px;color: white;font-size: 30px;font-stretch: wider;"><u>Profile</u></p>
<form action="UpdateProfileController" method="post">
<table>
	<tr>
		<td><label style="position:relative;right: 10px;color: red;">Client Id <span class="required">*</span></label><br>
	<input type="text" name="client_id" value="<%=instVo.getClient_id()%>" style="color: black;width: 200px;border-radius:10px;" readonly="readonly"><br></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><label style="position:relative;right: 10px;color: red;">Name <span class="required">*</span></label><input type="text" name="name" style="border-radius:10px;color: black;width: 200px" class="field-divided" value="<%=instVo.getName()%>" required/><br></td>
		
	</tr>
	<tr>
		<td><label style="position:relative;right: 10px;color: red;">Username <span class="required">*</span></label><input type="text" name="userName" style="border-radius:10px;color: black;width: 200px" class="field-divided" value="<%=instVo.getUsername()%>" ><br></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><label style="position:relative;right: 10px;color: red;">Password <span class="required">*</span></label><input type="text" name="password" class="field-divided" style="border-radius:10px;color: black;width: 200px" value="<%=instVo.getPassword()%>" /><br></td>
		
	</tr>
	<tr>
		<td><label style="position:relative;right: 10px;color: red;">Email <span class="required">*</span></label><br><input type="email" name="email" class="field-long" style="color: black;width: 200px;border-radius:10px;" value="<%=instVo.getEmail()%>"/><br></td>
		<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<td><label style="position:relative;right: 10px;color: red;">Mobile <span class="required">*</span></label><br><input type="text" name="mobile" style="color: black;width: 200px;border-radius:10px;" class="field-long" value="<%=instVo.getMobile()%>" required/><br></td>
	</tr>
	<tr>
		<td><label style="position:relative;right: 10px;color: red;">Address <span class="required">*</span></label><br><textarea name="address" id="field5" class="field-long field-textarea" style="border-radius:10px;color: black;width: 200px" cols="25" rows="5" required><%=instVo.getAddress() %></textarea><br><br></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" value="Update" style="border-radius:10px;color: black;height:40px;width: 80px;"></td>
		<td></td>
	</tr>	
</table>    
        

</form>
</div> 
                  <div class="intro-message">
             
                        <h1>&nbsp;</h1>
                      </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- Page Content -->

	<a  name="services"></a>
    <div class="content-section-a">

      <div class="container">
          <div class="row"></div>
        </div>
        </div>
    </body>

</html>
