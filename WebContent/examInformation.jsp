<%@ page import="java.util.Enumeration" %>
<%@ page errorPage="index.jsp" %>
<%@page import="com.oec.dao.ConductExamDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
img{
	position: absolute;
    left: 0px;
    top: 0px;
    z-index: -1;
    height: 100%;
    width: 100%;
}
</style>
<script type="text/javascript" language="javascript">
 window.onload=blinkOn;
 
function blinkOn()
{
  document.getElementById("blink").style.color="red"
  setTimeout("blinkOff()",1000)
}
 
function blinkOff()
{
  document.getElementById("blink").style.color="yellow"
  setTimeout("blinkOn()",1000)
}
 
 
 
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Skill Stones Examination</title>
</head>
<body>

<img alt="" src="img/noticeimg.jpg">
<div style="display: list-item;position: relative;left: 450px;">
<u style="color: green;"><b>INSTRUCTIONS TO BE READ</b></u><br><br>
<%int exam_id=Integer.parseInt(request.getParameter("exam_id")); 
int student_id = Integer.parseInt(request.getParameter("student_id"));
int occurance = ConductExamDAO.getOccurance(exam_id, student_id); 
%>

1. The set contains <%=ConductExamDAO.getQuestionCount(exam_id) %>&nbsp;&nbsp; questions.<br><br> 

2. Time Duration  : <%=ConductExamDAO.getDuration(exam_id) %> &nbsp;&nbsp;minutes.<br><br>

3. Don't press the Back or Refresh button during exam.<br><br>

4. There mark may be same or separate for each question according to the Institution.<br><br>

6. No other tab should be opened while giving an exam. On opening another tab,candidate will be disqualify.<br><br>

7. Only two breaks are allowed (Electricity breakdown).<br><br><br><br>
<%if(occurance==-1) {%>
<a href="StartExamController?exam_id=<%=exam_id%>&student_id=<%=student_id %>">Start Exam</a>
<%
}if(occurance>0){%>
<p id="blink" style="font-size:20px;color: red;text-decoration: blink;">You have used your break.</p><br>
<a href="StartExamController?exam_id=<%=exam_id%>&student_id=<%=student_id %>">Start Exam</a>
<%}if(occurance==3){
	ConductExamDAO.removeStudent(student_id, exam_id);
	ConductExamDAO.deactivateStudent(student_id, exam_id);
	response.sendRedirect("index.jsp?result=you have already used your 2 break");
}%><br><br>
<p style="color: red;">
Note : Please use Opera , Chrome or Internet explorer for compatibility.
<%
Enumeration headerNames = request.getHeaderNames();
while (headerNames.hasMoreElements()) {
	String headerName = (String) headerNames.nextElement();
	if(headerName.equals("user-agent")){
		String headerValue = request.getHeader(headerName);
		String s[]= headerValue.split(" ");
		String name = s[s.length-1];
		if(name.startsWith("Firefox")){
			out.print("You are using Firefox which is not compatable. Please use other browser");	
		}else{
			out.print("You are using "+name+" .");
		}
	}
}
%>
</p>
</div>
</body>
</html>