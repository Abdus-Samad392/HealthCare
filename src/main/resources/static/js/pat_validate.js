$(document).ready(function() {
	$("#patientNameError").hide();
	$("#patientEmailIdError").hide();
	$("#patientGenderError").hide();
	$("#patientMobileNoError").hide();
	$("#patientDOBError").hide();
	$("#patientMaritalStatusError").hide();
	$("#patientPresentAddressError").hide();
	$("#patientPermanentAddressError").hide();
	$("#patientPastMedicalHistoryError").hide();
	$("#patientPastMedicalHistory7").hide();

	var patientNameError = false;
	var patientEmailIdError = false;
	var patientGenderError = false;
	var patientMobileNoError = false;
	var patientDOBError = false;
	var patientMaritalStatusError = false;
	var patientPresentAddressError = false;
	var patientPermanentAddressError = false;
	//var patientPastMedicalHistoryError=false;

	function validate_patientName() {
		var patientName = $("#patientName").val();
		var patientNamePattern = /^[A-Za-z\s]{4,15}$/;
		if (patientName == '') {
			$("#patientNameError").show();
			$("#patientNameError").html("<h5><strong>* Patient Name is Required</strong></h5>");
			$("#patientNameError").css("color", "red");
			$("#patientName").css("border", "2px solid red");
			patientNameError = false;
		} else if (!patientNamePattern.test(patientName)) {
			$("#patientNameError").show();
			$("#patientNameError").html("<h5><strong>* Patient Name can contain alphabetical letters from 4 to 20 letters</strong></h5>");
			$("#patientNameError").css("color", "red");
			$("#patientName").css("border", "2px solid red");
			patientNameError = false;
		} else {
			$("#patientNameError").hide();
			$("#patientName").css("border", "");
			patientNameError = true;
		}
	}

	function validate_patientEmailId() {
		var patientEmailId = $("#patientEmailId").val();
		var patientEmailIdPattern = /^[a-z0-9-_.]*[@]gmail.com$/;
		if (patientEmailId == '') {
			$("#patientEmailIdError").show();
			$("#patientEmailIdError").html("<h5><strong>* Patient EmailId is Required</strong></h5>");
			$("#patientEmailIdError").css("color", "red");
			$("#patientEmailId").css("border", "2px solid red");
			patientEmailIdError = false;
		} else if (!patientEmailIdPattern.test(patientEmailId)) {
			$("#patientEmailIdError").show();
			$("#patientEmailIdError").html("<h5><strong>* Patient EmailId Not Valid</strong></h5>");
			$("#patientEmailIdError").css("color", "red");
			$("#patientEmailId").css("border", "2px solid red");
			patientEmailIdError = false;
		} else {
			$("#patientEmailIdError").hide();
			$("#patientEmailId").css("border", "");
			patientEmailIdError = true;
		}
	}

	function validate_patientGender() {
		var patientGender = $("[name='patientGender']:checked").length;
		if (patientGender == '') {
			$("#patientGenderError").show();
			$("#patientGenderError").html("<h5><strong>* Patient Gender is Required</strong></h5>");
			$("#patientGenderError").css("color", "red");
			//$("[name='patientGender']").css("border-color","red");
			patientGenderError = false;
		} else {
			$("#patientGenderError").hide();
			//$("#patientGender").css("border","");
			patientGenderError = true;
		}
	}

	function validate_patientMobileNo() {
		var patientMobileNo = $("#patientMobileNo").val();
		var patientMobileNoPattern = /^[\d]{10}$/;
		if (patientMobileNo == '') {
			$("#patientMobileNoError").show();
			$("#patientMobileNoError").html("<h5><strong>* Patient Mobile No is Required</strong></h5>");
			$("#patientMobileNoError").css("color", "red");
			$("#patientMobileNo").css("border", "2px solid red");
			patientMobileNoError = false;
		} else if (!patientMobileNoPattern.test(patientMobileNo)) {
			$("#patientMobileNoError").show();
			$("#patientMobileNoError").html("<h5><strong>* Patient Mobile No is Not Valid</strong></h5>");
			$("#patientMobileNoError").css("color", "red");
			$("#patientMobileNo").css("border", "2px solid red");
			patientMobileNoError = false;
		} else {
			var id=0;
			if($("#patientId").val()!=undefined){
				id=$("#patientId").val();
			}
			$.ajax({
				url: 'checkPatientMobileNo',
				data: { "patientMobileNo": patientMobileNo,"id":id},
				success: function(res) {
					if (res != '') {
						$("#patientMobileNoError").show();
						$("#patientMobileNoError").html("<h5><strong>* "+res+"</strong></h5>");
						$("#patientMobileNoError").css("color", "red");
						$("#patientMobileNo").css("border", "2px solid red");
						patientMobileNoError = false;
					} else {
						$("#patientMobileNoError").hide();
						$("#patientMobileNo").css("border", "");
						patientMobileNoError = true;
					}
				}
			})

		}
	}

	function validate_patientDOB() {
		var patientDOB = $("#patientDOB").val();
		if (patientDOB == '') {
			$("#patientDOBError").show();
			$("#patientDOBError").html("<h5><strong>* Patient Date Of Birth is Required</strong></h5>");
			$("#patientDOBError").css("color", "red");
			$("#patientDOB").css("border", "2px solid red");
			patientDOBError = false;
		} else {
			$("#patientDOBError").hide();
			$("#patientDOB").css("border", "");
			patientDOBError = true;
		}
	}

	function validate_patientMaritalStatus() {
		var patientMaritalStatus = $("[name='patientMaritalStatus']:checked").length;
		alert(patientMaritalStatus);
		if (patientMaritalStatus == 0) {
			$("#patientMaritalStatusError").show();
			$("#patientMaritalStatusError").html("<h5><strong>* Patient Marital Status is Required</strong></h5>");
			$("#patientMaritalStatusError").css("color", "red");
			//$("#patientMaritalStatus").css("border","2px solid red");
			patientMaritalStatusError = false;
		} else {
			$("#patientMaritalStatusError").hide();
			patientMaritalStatusError = true;
		}
	}

	function validate_patientPresentAddress() {
		var patientPresentAddress = $("#patientPresentAddress").val();
		//alert("before pattern"+patientPresentAddress);
		var patientPresentAddressPattern = /^[\w\s_\-.,]*$/;
		//alert("after pattern"+patientPresentAddress);
		if (patientPresentAddress == '') {
			//alert("inside if");
			$("#patientPresentAddressError").show();
			$("#patientPresentAddressError").html("<h5><strong>* Patient Present Address is Required</strong></h5>");
			$("#patientPresentAddressError").css("color", "red");
			$("#patientPresentAddress").css("border", "2px solid red");
			patientPresentAddressError = false;
		} else if (!patientPresentAddressPattern.test(patientPresentAddress)) {
			$("#patientPresentAddressError").show();
			$("#patientPresentAddressError").html("<h5><strong>* Patient Present Address is Not Valid</strong></h5>");
			$("#patientPresentAddressError").css("color", "red");
			$("#patientPresentAddress").css("border", "2px solid red");
			patientPresentAddressError = false;
		} else {
			$("#patientPresentAddressError").hide();
			$("#patientPresentAddress").css("border", "");
			patientPresentAddressError = true;
		}
	}

	function validate_patientPermanentAddress() {
		var patientPermanentAddress = $("#patientPermanentAddress").val();
		var patientPermanentAddressPattern = /^[\w\s_\-.,]*$/;
		if (patientPermanentAddress == '') {
			$("#patientPermanentAddressError").show();
			$("#patientPermanentAddressError").html("<h5><strong>* Patient Permanent Address is Required</strong></h5>");
			$("#patientPermanentAddressError").css("color", "red");
			$("#patientPermanentAddress").css("border", "2px solid red");
			patientPermanentAddressError = false;
		} else if (!patientPermanentAddress.match(patientPermanentAddressPattern)) {
			$("#patientPermanentAddressError").show();
			$("#patientPermanentAddressError").html("<h5><strong>* Patient Permanent Address is Not Valid</strong></h5>");
			$("#patientPermanentAddressError").css("color", "red");
			$("#patientPermanentAddress").css("border", "2px solid red");
			patientPermanentAddressError = false;
		} else {
			$("#patientPermanentAddressError").hide();
			$("#patientPermanentAddress").css("border", "");
			patientPermanentAddressError = true;
		}
	}

	//connecting all errors with action event
	$("#patientName").keyup(function() {
		validate_patientName();
	})

	$("#patientEmailId").keyup(function() {
		validate_patientEmailId();
	})

	$("[name='patientGender']").click(function() {
		validate_patientGender();
	})

	$("#patientMobileNo").keyup(function() {
		validate_patientMobileNo();
	})

	$("#patientDOB").blur(function() {
		validate_patientDOB();
	})

	$("[name='patientMaritalStatus']").click(function() {
		validate_patientMaritalStatus();
	})

	$("#patientPresentAddress").keyup(function() {
		//alert("inside present address");
		validate_patientPresentAddress();
	})

	$("#patientPermanentAddress").keyup(function() {
		validate_patientPermanentAddress();
	})

	$("[name='other']").click(function() {
		$("#patientPastMedicalHistory7").toggle();
	})



	$("#patForm").submit(function() {
		validate_patientName();
		validate_patientEmailId();
		validate_patientGender();
		validate_patientMaritalStatus();
		validate_patientPermanentAddress();
		validate_patientMobileNo();
		validate_patientPresentAddress();
		validate_patientDOB();
		if (patientGenderError && patientPermanentAddressError && patientPresentAddressError
			&& patientDOBError && patientEmailIdError && patientMobileNoError &&
			patientNameError && patientMaritalStatusError) {
			return true;
		} else {
			return false;
		}
	})
})

