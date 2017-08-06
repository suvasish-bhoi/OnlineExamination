function saveData() {
	alert('hi');
	var esqVO = new Object();
	esqVO.exam_id = $('#exam_id').val();
	esqVO.student_id = $('#student_id').val();
	esqVO.question_id = $('#question_id').val();
	esqVO.question_idList = $('#question_list').val().split(",");
	esqVO.question_no = $('#question_no').text();
	esqVO.attempt = $('#answer').val();
	esqVO.min = $('#hms_timer').text().split(':')[1];
	esqVO sec = $('#hms_timer').text().split(':')[2];
	esqVO.question= "" ;
	esqVO.a = "" ;
	esqVO.b = "" ;
	esqVO.c = "" ;
	esqVO.d = "" ;
	
	$.ajax({
		url: "ProcessExamController",
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(esqVO),
		contentType: 'application/json',
		mimeType: 'application/json',
		
		success: function (data) {
			$('#question_list').val(data.question_idList);
			$('#question_id').val(data.question_id);
			$('#question_no').text(data.question_no);
			$('#question').text(data.question);
			$('#radio1').text(data.a);
			$('#radio2').text(data.b);
			$('#radio3').text(data.c);
			$('#radio4').text(data.d);
        },
		error:function(data,status,er) {
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
	esqVO.min="";
	esqVO.sec="";
	esqVO.question= "" ;
	esqVO.a = "" ;
	esqVO.b = "" ;
	esqVO.c = "" ;
	esqVO.d = "" ;
	
	$.ajax({
		url: "EndExamByTimeOffController",
		type: 'POST',
		dataType: 'json',
		data: JSON.stringify(esqVO),
		contentType: 'application/json',
		mimeType: 'application/json',
		success: function (data) {
	    },
		error:function(data,status,er) {
			window.location.href = "/OnlineExaminationSystem";
		}
	});
}

function disqualify(){
	
var esqVO = new Object();
esqVO.exam_id = $('#exam_id').val();
esqVO.student_id = $('#student_id').val();
esqVO.question_id = "";
esqVO.question_idList = "".split(",");
esqVO.question_no = "";
esqVO.attempt = "";
esqVO.min="";
esqVO.sec="";
esqVO.question= "" ;
esqVO.a = "" ;
esqVO.b = "" ;
esqVO.c = "" ;
esqVO.d = "" ;

$.ajax({
	url: "DisqualifyCandidateController",
	type: 'POST',
	dataType: 'json',
	data: JSON.stringify(esqVO),
	contentType: 'application/json',
	mimeType: 'application/json',
	success: function (data) {
    },
	error:function(data,status,er) {
		window.location.href = "/OnlineExaminationSystem";
	}
});
}
