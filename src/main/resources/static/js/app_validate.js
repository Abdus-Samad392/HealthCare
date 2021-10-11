$(document).ready(function(){
    $("#appointmentWithdoctorError").hide();
    $("#appointmentDateError").hide();
    $("#noOfSlotsError").hide();
    $("#appointmentDetailsError").hide();
    $("#consultationFeeError").hide();

    var appointmentWithdoctorError=false;
    var appointmentDateError=false;
    var noOfSlotsError=false;
    var appointmentDetailsError=false;
    var consultationFeeError=false;

    function validate_appointmentWithdoctorError(){
        var appointmentWithdoctorErrorValue=$("#appointmentWithdoctor").val();
        if(appointmentWithdoctorErrorValue==''){
            $("#appointmentWithdoctorError").show();
            $("#appointmentWithdoctorError").html("<h3><strong>* Please Select One Doctor</strong></h3>");
            $("#appointmentWithdoctorError").css("color","red");
            $("#appointmentWithdoctor").css("border","2px solid red");
            appointmentWithdoctorError=false;
        }else{
            $("#appointmentWithdoctorError").hide();
            $("#appointmentWithdoctor").css("border","");
            appointmentWithdoctorError=true;
        }
    }//validate_appointmentWithdoctorError()

    function validate_appointmentDate(){
        var appointmentDateValue=$("#appointmentDate").val();
        if(appointmentDateValue==''){
            $("#appointmentDateError").show();
            $("#appointmentDateError").html("<h3><strong>* Please Select Appointment Date</strong></h3>");
            $("#appointmentDateError").css("color","red");
            $("#appointmentDate").css("border","2px solid red");
            appointmentDateError=false;
        }else{
            $("#appointmentDateError").hide();
            $("#appointmentDate").css("border","");
            appointmentDateError=true;
        }
    }//validate_appointmentDate()

    function validate_noOfSlots(){
        var noOfSlotsValue=$("#noOfSlots").val();
        if(noOfSlotsValue==''){
            $("#noOfSlotsError").show();
            $("#noOfSlotsError").html("<h3><strong>* No Of Slots Is Required</strong></h3>");
            $("#noOfSlotsError").css("color","red");
            $("#noOfSlots").css("border","2px solid red");
            noOfSlotsError=false;
        }else{
            $("#noOfSlotsError").hide();
            $("#noOfSlots").css("border","");
            noOfSlotsError=true;
        }
    }//validate_noOfSlots()

    function validate_appointmentDetails(){
        var appointmentDetailsValue=$("#appointmentDetails").val();
        if(appointmentDetailsValue==''){
            $("#appointmentDetailsError").show();
            $("#appointmentDetailsError").html("<h3><strong>* Appointment Details is Required</strong></h3>");
            $("#appointmentDetailsError").css("color","red");
            $("#appointmentDetails").css("border","2px solid red");
            appointmentDetailsError=false;
        }else{
            $("#appointmentDetailsError").hide();
            $("#appointmentDetails").css("border","");
            appointmentDetailsError=true;
        }

    }//validate_appointmentDetails()

    function validate_consultationFee(){
        var consultationFeeValue=$("#consultationFee").val();
        if(consultationFeeValue==''){
            $("#consultationFeeError").show();
            $("#consultationFeeError").html("<h3><strong>* Consultation Fee is Required</strong></h3>");
            $("#consultationFeeError").css("color","red");
            $("#consultationFee").css("border","2px solid red");
            consultationFeeError=false;
        }else{
            $("#consultationFeeError").hide();
            $("#consultationFee").css("border","");
            consultationFeeError=true;
        }
    }//validate_consultationFee()

    $("#appointmentWithdoctor").change(function(){
        validate_appointmentWithdoctorError();
    })

    $("#appointmentDate").blur(function(){
        validate_appointmentDate();
    })

    $("#noOfSlots").keyup(function(){
        validate_noOfSlots();
    })

    $("#appointmentDetails").keyup(function(){
        validate_appointmentDetails();
    })

    $("#consultationFee").keyup(function(){
        validate_consultationFee();
    })

    $("#appForm").submit(function(){
        validate_appointmentWithdoctorError();
        validate_appointmentDate();
        validate_noOfSlots();
        validate_appointmentDetails();
        validate_consultationFee();
        if(appointmentWithdoctorError && appointmentDateError && noOfSlotsError && appointmentDetailsError &&
            consultationFeeError){
                return true;
            }else{
                return false;
            }
    })
})//ready(function())