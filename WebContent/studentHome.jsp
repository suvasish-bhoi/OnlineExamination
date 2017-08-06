<%@ page errorPage="index.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.oec.vo.MainLoginVO"
    %>

<%@page import="com.oec.vo.ExamInstituteStudentVO"%>
<%@page import="com.oec.vo.StudentVO"%>
<%@page import="com.oec.vo.ExamInstituteVO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
StudentVO studVo = (StudentVO)request.getSession().getAttribute("studentVO");
MainLoginVO mainVo = (MainLoginVO)request.getSession().getAttribute("mainLoginVO");
List<ExamInstituteVO> eivoList = (List<ExamInstituteVO>)request.getAttribute("commingExamList");
List<ExamInstituteStudentVO> eisVoList = (List<ExamInstituteStudentVO>)request.getAttribute("today'sExamList");
%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Welcome&nbsp; <%=studVo.getName() %></title>
    
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
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

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
                <a class="navbar-brand topnav" href="#"><b><%=studVo.getName()%></b></a>
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
							  <li><a href="ExamNoticeController">Upcoming Exams</a></li>
							  <li><a href="GetAttemptExamController">Result</a></li>
							</ul>
						  </li>
						  <li><a href="#">Account</a>
							<ul>
							  <li><a href="ViewStudentProfileController">Profile</a></li>
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
    <a name="about"></a>
    <div class="intro-header">
        <div class="container">
		
		



            <div class="row">
                <div class="col-lg-12">
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
          <div class="row">
          <div style="position:absolute;left:50px;top:150px;width:45%;height:300px;border-radius: 25px;
    border: 2px solid #73AD21;border-width: 10px">
<marquee direction="up" scrolldelay="200" height="280" behavior="scroll" onMouseOver="this.stop();" onMouseOut="this.start();">
<center style="color:white;">
<u>Upcoming Exam Details</u><br><br><br>
	<% 
	for(ExamInstituteVO eivo : eivoList){
		out.print("-->   "+"'"+eivo.getTopic()+"' exam by "+eivo.getName()+" is on date : "+eivo.getDateOfExam()+", required "+eivo.getDescription());%><br><br>
		<% 
		}
	%>
</center></marquee>
</div>
<%if(!eisVoList.isEmpty()){%>
<div style="position:absolute;right:50px;top:150px;width:40%;height:300px;border-radius:25px;
    border: 2px solid #73AD21;border-width: 10px;">
   <center> <img alt="" src="images/todaysexam.gif" height="80px" width="180px" style="border-radius:10px;"/></center>
<marquee direction="up" scrolldelay="200" height="200" behavior="scroll" onMouseOver="this.stop();" onMouseOut="this.start();" >
<center style="color: white;">
<% 
	for(ExamInstituteStudentVO eisVo : eisVoList){
		out.print("   "+"'"+eisVo.getTopic()+"' exam by "+eisVo.getClient_name());%>&nbsp;&nbsp;&nbsp;<a href="examInformation.jsp?exam_id=<%=eisVo.getExam_id()%>&student_id=<%=eisVo.getStudent_id()%>" style="color: yellow;">Start Exam Now<br><br>
		<% 
		}
	%>
</center></marquee>
</div>	
	
<%
}
%></div>
        </div>
        </div>
    </body>

</html>
