<%@ page errorPage="index.jsp" %>
<%@page import="com.oec.dao.InstituteDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.oec.vo.*"
    import="java.util.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/tableCss.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exam Notice Page</title>
</head>
<body>
<center>
<br><br>
<section>
<div  class="tbl-header">
<table cellpadding="0" cellspacing="0" border="0">
  <thead>
	<tr>
		<th>Sr No.</th>
		<th>Topic</th>
		<th>Date Of Creation</th>
		<th>Exam Date</th>
		<th>Duration</th>
		<th>Details</th>
	</tr>
</thead>
</table>
</div>
	<%List<ExamVO> examList = (List)request.getAttribute("examVo");
	if(examList.isEmpty()){
		%>No Data To Display<%
	}else{ %>
	<div  class="tbl-content">
<table cellpadding="0" cellspacing="0" border="0">
  <tbody>
	<%
	int i=0;
	for(ExamVO eVo: examList){
		%>
		<tr>
			<td><%=++i %></td>
			<td><%=eVo.getTopic()%></td>
			<td><%=eVo.getDateOfCreation()%></td>
			<td><%=eVo.getDateOfExam()%></td>
			</td><%=eVo.getDuration()%></td>
			<td><%=eVo.getDescription() %></td>
			<td><a href="uploadQuestionAnswer.jsp?id=<%=eVo.getExam_id()%>">Upload</a></td>
			<td><a href="DeleteExamController?id=<%=eVo.getExam_id()%>"><input type="button" value="Delete"></a></td>
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
</center>

</body>
</html>