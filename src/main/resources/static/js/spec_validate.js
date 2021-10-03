$(document).ready(function() {
	$("#specCodeError").hide();
	$("#specNameError").hide();
	$("#specNoteError").hide();

	var specCodeError = false;
	var specNameError = false;
	var specNoteError = false;

	function validate_specCode() {
		var specCodeLength = $("#specCode").val();
		var specCodeExpression = /^[A-Z]{3,10}$/;
		if (specCodeLength == '') {
			$("#specCodeError").show();
			$("#specCode").css("border", "2px solid red");
			//$("#specCode").addClass('errorClass');
			$("#specCodeError").html("<h5><strong>* Specialization Code is Required</strong></h5>");
			$("#specCodeError").css("color", "red");
			specCodeError = false;
		} else if (!specCodeExpression.test(specCodeLength)) {
			$("#specCodeError").show();
			$("#specCodeError").html("<h5><strong>* Letters from A to Z(capital) with length 3 to 6 allowed </strong></h5>");
			$("#specCodeError").css("color", "red");
			$("#specCode").css("border", "2px solid red");
			specCodeError = false;
		}
		else {
			var id=0;
			if($("#specId").val()!=undefined){
				id=$("#specId").val();
			}
			$.ajax({
				url:'checkSpecCode',
				data:{"specCode":specCodeLength,"id":id},
				success:function(res){
					if(res!=''){
						$("#specCodeError").show();
						$("#specCodeError").html("<h5><strong>* "+res+"</strong></h5>");
						$("#specCodeError").css("color","red");
						$("#specCode").css("border","2px solid red");
						specCodeError=false;
					}else{
						$("#specCode").css("border", "");
						$("#specCodeError").hide();
						specCodeError = true;
					}
				}
				
			})//ajax call
			
		}//else
	}//validate_specCode()

	function validate_specName() {
		var specNameLength = $("#specName").val();
		var specNameExpression = /^[A-Za-z\ ]{5,60}$/;
		if(specNameLength == '') {
			$("#specNameError").show();
			$("#specName").css("border", "2px solid red");
			$("#specNameError").html("<h5><strong>* Specialization Name is Required</strong></h5>");
			$("#specNameError").css("color", "red");
			specNameError = false;
		} else if (!specNameExpression.test(specNameLength))  {
			$("#specNameError").show();
			$("#specNameError").html("<h5><strong>* Specialization Name should be in between 5 to 20 chars</strong></h5>");
			$("#specNameError").css("color", "red");
			$("#specName").css("border", "2px solid red");
			specNameError = false;
		}

		else {
			var id=0;
			if($("#specId").val()!=undefined){
				id=$("#specId").val();
			}
			
			$.ajax({
				url:'checkSpecName',
				data:{"specName":specNameLength,"id":id},
				success:function(res){
					if(res!=''){
						$("#specNameError").show();
						$("#specNameError").html("<h5><strong>* "+res+"</strong></h5>");
						$("#specNameError").css("color", "red");
						$("#specName").css("border", "2px solid red");
						specNameError = false;
					}else{
						$("#specNameError").hide();
						$("#specName").css("border", "");
						specNameError = true;
					}
				}
			})
			
		}
	}//validate_specName()

function validate_specNote() {
	var specNoteLength = $("#specNote").val();
	 var specNoteExpression=/^[A-Za-z0-9\.\'\,\s\-]*$/;
	if (specNoteLength == '') {
		$("#specNoteError").show();
		$("#specNote").css("border", "2px solid red");
		$("#specNoteError").html("<h5><strong>* Specialization Note is Required</strong></h5>")
		$("#specNoteError").css("color", "red");
		specNoteError = false;
	}else if(!specNoteExpression.test(specNoteLength)){
            $("#specNoteError").show();
            $("#specNoteError").html("<h5><strong>* Specialization Note can contain . ,' only</strong></h5>")
            $("#specNoteError").css("color","red");
            $("#specNote").css("border","2px solid red");
            specNoteError=false;
        }
	
	 else {
		$("#specNoteError").hide();
		$("#specNote").css("border", "");
		specNoteError = true;
	}
}//validate_specNote()

$("#specCode").keyup(function() {
	validate_specCode();
})

$("#specName").keyup(function() {
	validate_specName();
})

$("#specNote").keyup(function() {
	validate_specNote();
})

	$("#specForm").submit(function() {
		validate_specCode();
		validate_specName();
		validate_specNote();

		if (specCodeError && specNameError && specNoteError) {
			return true;
		} else {
			return false;
		}

	})

})