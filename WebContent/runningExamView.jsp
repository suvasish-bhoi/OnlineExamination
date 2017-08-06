<%@ page errorPage="index.jsp"%>
<%@page import="com.oec.dao.ConductExamDAO"%>
<%@page import="com.oec.vo.ExamStudentQuestionVO"%>
<%@page import="com.oec.util.DateUtil"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Skill Stones Examination</title>
<script language="JavaScript">
	window.location.hash = "no-back-button";
	window.location.hash = "Again-No-back-button";//again because google chrome don't insert first hash into history
	window.onhashchange = function() {
		window.location.hash = "no-back-button";
	}
	var examFlag = 1;

	function onFocus() {
		if (document.addEventListener) {
			document.addEventListener("visibilitychange", visibilityChanged);
			document.addEventListener("webkitvisibilitychange",
					visibilityChanged);
			document.addEventListener("msvisibilitychange", visibilityChanged);
		}
	}
	function isPageHidden() {
		return document.hidden || document.msHidden || document.webkitHidden;
	}

	function visibilityChanged() {
		if (isPageHidden() && examFlag == 1) {
			alert("Disqualified");
			disqualify();
		}

	}
	function saveData() {
		var ans;
		var radios = document.getElementsByName('answer');
		var flag = true;
		for (var i = 0; i < radios.length; i++) {
			if (radios[i].checked) {
				ans = radios[i].value;
				flag = false;
			}
		}
		if (flag) {
			return false;
		}
		var esqVO = new Object();
		esqVO.exam_id = $('#exam_id').val();
		esqVO.student_id = $('#student_id').val();
		esqVO.question_id = $('#question_id').val();
		esqVO.question_idList = $('#question_list').val().split(",");
		esqVO.question_no = $('#question_no').text();
		esqVO.attempt = ans;
		esqVO.time = $('#hms_timer').text();
		esqVO.question = "";
		esqVO.a = "";
		esqVO.b = "";
		esqVO.c = "";
		esqVO.d = "";

		$.ajax({
			url : "ProcessExamController",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(esqVO),
			contentType : 'application/json',
			mimeType : 'application/json',

			success : function(data) {
				$('#question_list').val(data.question_idList);
				$('#question_id').val(data.question_id);
				$('#question_no').text(data.question_no);
				$('#question').text(data.question);
				$('#radio1').text(data.a);
				$('#radio2').text(data.b);
				$('#radio3').text(data.c);
				$('#radio4').text(data.d);
			},
			error : function(data, status, er) {
				examFlag=0;
				alert("Exam Completed");
				window.location.href = "/OnlineExaminationSystem";
			}
		});

	}

	function sendAjaxForEndTime() {

		var esqVO = new Object();
		esqVO.exam_id = $('#exam_id').val();
		esqVO.student_id = $('#student_id').val();
		esqVO.question_id = "";
		esqVO.question_idList = "".split(",");
		esqVO.question_no = "";
		esqVO.attempt = "";
		esqVO.time = "";
		esqVO.question = "";
		esqVO.a = "";
		esqVO.b = "";
		esqVO.c = "";
		esqVO.d = "";

		$
				.ajax({
					url : "EndExamByTimeOffController",
					type : 'POST',
					dataType : 'json',
					data : JSON.stringify(esqVO),
					contentType : 'application/json',
					mimeType : 'application/json',
					success : function(data) {
					},
					error : function(data, status, er) {
						window.location.href = "http://localhost:8080/OnlineExaminationSystem";
					}
				});
	}

	function disqualify() {

		var esqVO = new Object();
		esqVO.exam_id = $('#exam_id').val();
		esqVO.student_id = $('#student_id').val();
		esqVO.question_id = "";
		esqVO.question_idList = "".split(",");
		esqVO.question_no = "";
		esqVO.attempt = "";
		esqVO.time = "";
		esqVO.question = "";
		esqVO.a = "";
		esqVO.b = "";
		esqVO.c = "";
		esqVO.d = "";

		$
				.ajax({
					url : "DisqualifyCandidateController",
					type : 'POST',
					dataType : 'json',
					data : JSON.stringify(esqVO),
					contentType : 'application/json',
					mimeType : 'application/json',
					success : function(data) {
					},
					error : function(data, status, er) {
						window.location.href = "http://localhost:1808/OnlineExaminationSystem";
					}
				});
	}

	function clockUpdate() {

		var esqVO = new Object();
		esqVO.exam_id = $('#exam_id').val();
		esqVO.student_id = $('#student_id').val();
		esqVO.question_id = "";
		esqVO.question_idList = "".split(",");
		esqVO.question_no = "";
		esqVO.attempt = "";
		esqVO.time = $('#hms_timer').text();
		esqVO.question = "";
		esqVO.a = "";
		esqVO.b = "";
		esqVO.c = "";
		esqVO.d = "";

		$.ajax({
			url : "UpdateClockController",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify(esqVO),
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
			},
			error : function(data, status, er) {
			}
		});
	}
</script>
<script type="text/javascript" src="LIB/jquery-2.0.3.js"></script>
<script src="jquery.1.9.1.min.js"></script>
<script type="text/javascript" src="LIB/jquery.countdownTimer.js"></script>
<link rel="stylesheet" type="text/css"
	href="CSS/jquery.countdownTimer.css" />
<style type="text/css">
img {
	position: absolute;
	left: 0px;
	top: 0px;
	z-index: -1;
	height: 100%;
	width: 100%;
}
</style>
</head>
<body onLoad="onFocus()">
	<center>
		<p
			style="font: italic; font-family: cursive; font-size: 18px; font-stretch: 5px; font-weight: 900;">SkillStones
			Software</p>
	</center>
	<img alt="" src="img/examback.jpg">
	<br>
	<br>
	<br>
	<hr>
	<%
		ExamStudentQuestionVO esqVo = (ExamStudentQuestionVO) request
				.getAttribute(request.getParameter("student_id"));
	%>
	<div id="main" style="position: relative; left: 855px;">
		<table style="border: 0px;">
			<tr>
				<td colspan="4"><span id="hms_timer"></span></td>
			</tr>
		</table>
		<script>
			$(function() {
				$('#hms_timer').countdowntimer({
					hours : 0,
					minutes :
		<%=esqVo.getMin()%>
			,
					seconds :
		<%=esqVo.getSec()%>
			,
					size : "lg"
				});
			});
		</script>


		</style>
	</div>

	<input type="hidden" id="question_list" name="question_list"
		value="<%=ConductExamDAO.getCommaSeparatedFromList(esqVo.getQuestion_idList())%>">
	<input type="hidden" id="exam_id" name="exam_id"
		value="<%=esqVo.getExam_id()%>">
	<input type="hidden" id="student_id" name="student_id"
		value="<%=esqVo.getStudent_id()%>">
	<input type="hidden" id="question_id" name="question_id"
		value="<%=esqVo.getQuestion_id()%>">
	<br>
	<br>
	<hr>
	<div
		style="border-width: 5px; border-radius: 10px; border-style: groove; font-size: 18px; font-weight: bold;">
		<table style="position: relative; left: 450px;">
			<tr>
				<td style="width: 0px"><span id="question_no"
					name="question_no"><%=esqVo.getQuestion_no()%></span></td>
				<td colspan="2"><span id="question" name="question">.<%=esqVo.getQuestion()%></span></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>(a)<input type="radio" id="answer" name="answer" value="a"><span
					id="radio1" name="radio1"><%=esqVo.getA()%></span></td>
				<td></td>
				<td>(b)<input type="radio" id="answer" name="answer" value="b"><span
					id="radio2" name="radio2"><%=esqVo.getB()%></span></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>(c)<input type="radio" id="answer" name="answer" value="c"><span
					id="radio3" name="radio3"><%=esqVo.getC()%></span></td>
				<td></td>
				<td>(d)<input type="radio" id="answer" name="answer" value="d"><span
					id="radio4" name="radio4"><%=esqVo.getD()%></span></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td>(e)<input type="radio" id="answer" name="answer" value="n">skip
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>

			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td colspan="3"><button type="button" onClick="saveData()"
						style="width: 130px; height: 60px; border-radius: 10px; position: relative; left: 180px">Save
						& Next</button></td>
			</tr>
		</table>
	</div>
	<hr>
</body>
<footer>
<center>
	<p>Copyright &copy; www.skillstones.com 2016</p>
</center>
</footer>
</html>