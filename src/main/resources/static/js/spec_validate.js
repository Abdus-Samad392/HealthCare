$(document).ready(function(){
    $("#specCodeError").hide();
    $("#specNameError").hide();
    $("#specNoteError").hide();

    var specCodeError=false;
    var specNameError=false;
    var specNoteError=false;

    function validate_specCode(){
        var specCodeLength=$("#specCode").val();
        if(specCodeLength==''){
            $("#specCodeError").show();
            $("#specCode").css("border","2px solid red");
            //$("#specCode").addClass('errorClass');
            $("#specCodeError").html("<h5><strong>* Specialization Code is Required</strong></h5>");
            $("#specCodeError").css("color","red");
            specCodeError=false;
        }else{
			$("#specCode").css("border","");
            $("#specCodeError").hide();
            specCodeError=true;
        }
    }//validate_specCode()

    function validate_specName(){
        var specNameLength=$("#specName").val();
        if(specNameLength==''){
            $("#specNameError").show();
            $("#specName").css("border","2px solid red");
            $("#specNameError").html("<h5><strong>* Specialization Name is Required</strong></h5>");
            $("#specNameError").css("color","red");
            specNameError=false;
        }else{
            $("#specNameError").hide();
            $("#specName").css("border","");
            specNameError=true;
        }
    }//validate_specName()

    function validate_specNote(){
        var specNoteLength=$("#specNote").val();
        if(specNoteLength==''){
            $("#specNoteError").show();
            $("#specNote").css("border","2px solid red");
            $("#specNoteError").html("<h5><strong>* Specialization Note is Required</strong></h5>")
            $("#specNoteError").css("color","red");
            specNoteError=false;
        }else{
            $("#specNoteError").hide();
            $("#specNote").css("border","");
            specNoteError=true;
        }
    }//validate_specNote()

    $("#specCode").keyup(function(){
        validate_specCode();
    })

    $("#specName").keyup(function(){
        validate_specName();
    })

    $("#specNote").keyup(function(){
        validate_specNote();
    })

    $("#specForm").submit(function(){
        validate_specCode();
        validate_specName();
        validate_specNote();
        
        if(specCodeError && specNameError && specNoteError){
            return true;
        }else{
            return false;
        }

    })

})