<!DOCTYPE html>
<%@ page errorPage="index.jsp" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import="com.oec.vo.InstituteVO"
    import="com.oec.vo.MainLoginVO"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Welcome Admin</title>
    
    <%
MainLoginVO mainVo = (MainLoginVO)request.getSession().getAttribute("mainLoginVO");%>
    

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
<center><%
String value = request.getParameter("result");
%></center>
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
                <a class="navbar-brand topnav" href="#">Welcome Admin</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                         <a href="LoginController?client_id=<%=mainVo.getClient_id()%>&username=<%=mainVo.getUsername()%>&password=<%=mainVo.getPassword()%>">Home</a>
                    </li>
                    <li>
                       <a href="instituteRegistration.jsp">Add Institute</a>
                    </li>
                    <li>
                        <a href="ViewAndManageInstituteController">View / Manage</a>
                    </li>
					<li>
                        <a href="LogoutController">Logout</a>
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
			<div>
			<center><b>View Institute Details</b></center>
				<section>
					<div  class="tbl-header">
					<table cellpadding="0" cellspacing="0" border="0">
					  <thead>
						<tr>
							<th>Sr No.</th>
							<th>Name</th>
							<th>Edit</th>
							<th>Status</th>
							<th>Delete</th>
						</tr>
					</thead>
					</table>
					</div>
						<%List<InstituteVO> instituteList = (List)request.getAttribute("instituteDetails");

					if(instituteList.isEmpty()){
						%>No Data To Display<%
					}else{ %>
					<div  class="tbl-content">
					<table cellpadding="0" cellspacing="0" border="0">
					  <tbody>
						<%
						int i=0;
						for(InstituteVO instVo: instituteList){
							%>
							<tr>
								<td><%=++i %></td>
								<td><%=instVo.getName() %></td>
								<td><a href="<%="EditInstituteController?client_id="+instVo.getClient_id() %>"  style="color: yellow;">edit</a></td>
								<td><input type="checkbox" <% if(instVo.getStatus()==1){%>checked<%}%>></td>
								<td><a href="<%="DeleteInstituteController?client_id="+instVo.getClient_id() %>" style="color: yellow;">delete</a></td>
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
			</div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1></h1>
                        <h3></h3>
                        
                        
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

       

    <footer>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                  <center><p class="copyright text-muted small">Copyright &copy; www.SkillStones.com 2016. All Rights Reserved</p></center>
                </div>
            </div>
        </div>
    </footer>

    <!-- jQuery -->
    <script src="js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>

