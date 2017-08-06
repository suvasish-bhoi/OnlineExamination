
<%@page import="com.oec.dao.StudentDAO"%>
<%@ page errorPage="index.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.oec.vo.ExamVO"
    import="com.oec.vo.MainLoginVO"
    import="java.util.List"
    %>
<%@page import="com.oec.vo.StudentVO"%>
<%@page import="com.oec.dao.StudentExamMapDAO"%>
<%@page import="com.oec.vo.ExamInstituteVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%
MainLoginVO mainVo = (MainLoginVO)request.getSession().getAttribute("mainLoginVO");
String studentName = StudentDAO.getStudentNameByUsername(mainVo.getUsername());%>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome&nbsp; <%=studentName%></title>
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
<link rel="stylesheet" type="text/css" href="CSS/tableCss.css">

</head>

<body>
<%
String value = request.getParameter("result");
List<ExamInstituteVO> examList = (List) request.getAttribute("examList");
int i=0;
StudentVO svo = (StudentVO)session.getAttribute("studentVO");
%>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="#"><b><%=studentName%></b></a>
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
        <div class="container"><br><br><br>
<section>
<div  class="tbl-header">
<table cellpadding="0" cellspacing="0" border="0">
  <thead>
    <tr>
      <th>Sr No.</th>
		<th>Topic</th>
		<th>Institute</th>
		<th>Date</th>
		<th></th>
    </tr>
  </thead>
</table>
</div>
	<%
	if(examList.isEmpty()){
		%>No Data To Display<%
	}else{ %>
	<div  class="tbl-content">
		<table cellpadding="0" cellspacing="0" border="0">
  			<tbody>
	<%
	for(ExamInstituteVO eivo: examList){
		%>
	
    			<tr>
					<td><%=++i %></td>
					<td><%=eivo.getTopic()%></td>
					<td><%=eivo.getName()%></td>
					<td><%=eivo.getDateOfExam()%></td>
					<td><a href="CheckStudentResultController?exam_id=<%=eivo.getExam_id() %>" style="color:black;">View Result</a></td>
				</tr>
	<%
	}
	%>
			</tbody>
		</table>
	</div>
</section>
<%	
}
%>
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
          <div class="row"></div>
        </div>
        </div>
    </body>

</html>
