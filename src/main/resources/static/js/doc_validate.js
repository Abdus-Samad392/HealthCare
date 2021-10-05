$(document).ready(function(){
    $("#docNameError").hide();
    $("#docEmailIdError").hide();
    $("#docSpecializationError").hide();
    $("#docAddressError").hide();
    $("#docMobileNoError").hide();
    $("#docGenderError").hide();
    $("#docNoteError").hide();
    $("#docPhotoLocError").hide();

    var docNameError=false;
    var docEmailIdError=false;
    var docSpecializationError=false;
    var docAddressError=false;
    var docMobileNoError=false;
    var docGenderError=false;
    var docNoteError=false;
    var docPhotoLocError=false;

    function vaildate_docName(){
        var docName=$("#docName").val();
        var docNamePattern=/^[A-Za-z\s]{5,30}$/;
        if(docName==''){
            $("#docNameError").show();
            $("#docNameError").html("<h5><strong>* Doctor Name is Required</strong></h5>");
            $("#docNameError").css("color","red");
            $("#docName").css("border","2px solid red");
            docNameError=false;
        }else if(!docNamePattern.test(docName)){
            $("#docNameError").show();
            $("#docNameError").html("<h5><strong>* Doctor Name can contain alphabetical letters only from 5 to 30 letters<strong></h5>");
            $("#docNameError").css("color","red");
            $("#docName").css("border","2px solid red");
            docNameError=false;
        }else{
            $("#docNameError").hide();
            $("#docName").css("border","");
            docNameError=true;
        }
    }//vaildate_docName

    function validate_docEmailId(){
        var docEmailId=$("#docEmailId").val();
        var docEmailIdPattern=/^[a-z0-9._-]+\@gmail\.com$/;
        if(docEmailId==''){
            $("#docEmailIdError").show();
            $("#docEmailIdError").html("<h5><strong>* EmailId is Required</strong></h5>");
            $("#docEmailIdError").css("color","red");
            $("#docEmailId").css("border","2px solid red");
            docEmailIdError=false;
        }else if(!docEmailIdPattern.test(docEmailId)){
            $("#docEmailIdError").show();
            $("#docEmailIdError").html("<h5><strong>* EmailId is not Valid</strong></h5>");
            $("#docEmailIdError").css("color","red");
            $("#docEmailId").css("border","2px solid red");
            docEmailIdError=false;
        }else{
			var id=0;
			if($("#id").val()!=undefined){
				id=$("#id").val();
			}
            $.ajax({
                url:'checkDocEmailId',
                data:{"docEmailId":docEmailId,"id":id},
                success:function(res){
                    if(res!=''){
                        $("#docEmailIdError").show();
                        $("#docEmailIdError").html("<h5><strong>* "+res+"</strong></h5>");
                        $("#docEmailIdError").css("color","red");
                        $("#docEmailId").css("border","2px solid red");
                        docEmailIdError=false; 
                    }else{
                        $("#docEmailIdError").hide();
                        $("#docEmailId").css("border","");
                        docEmailIdError=true;
                    }
                }
            })

        }
    }

    function validate_docSpecialization(){
        var docSpecialization=$("#docSpecialization").val();
        if(docSpecialization==''){
            $("#docSpecializationError").show();
            $("#docSpecializationError").html("<h5><strong>* Choose One Specialization</strong></h5>");
            $("#docSpecializationError").css("color","red");
            $("#docSpecialization").css("border","2px solid red");
            docSpecializationError=false;
        }else{
            $("#docSpecializationError").hide();
            $("#docSpecialization").css("border","");
            docSpecializationError=true;
        }
    }

    function validate_docAddress(){
        var docAddress=$("#docAddress").val();
        if(docAddress==''){
            $("#docAddressError").show();
            $("#docAddressError").html("<h5><strong>* Address is Required</strong></h5>");
            $("#docAddressError").css("color","red");
            $("#docAddress").css("border","2px solid red");
            docAddressError=false;
        }else{
            $("#docAddressError").hide();
            $("#docAddress").css("border","");
            docAddressError=true;
        }
    }

    function validate_docMobileNo(){
        var docMobileNo=$("#docMobileNo").val();
        var docMobileNoPattern=/^[0-9]{10}$/;
        if(docMobileNo==''){
            $("#docMobileNoError").show();
            $("#docMobileNoError").html("<h5><strong>* Mobile No is Required</strong></h5>");
            $("#docMobileNoError").css("color","red");
            $("#docMobileNo").css("border","2px solid red");
            docMobileNoError=false;
        }else if(!docMobileNoPattern.test(docMobileNo)){
            $("#docMobileNoError").show();
            $("#docMobileNoError").html("<h5><strong>* Mobile No is Not Valid</strong></h5>");
            $("#docMobileNoError").css("color","red");
            $("#docMobileNo").css("border","2px solid red");
            docMobileNoError=false;
        }else{
			var id=0;
			if($("#id").val()!=undefined){
				id=$("#id").val();
			}
			$.ajax({
				url:'checkDocMobileNo',
				data:{"docMobileNo":docMobileNo,"id":id},
				success:function(res){
					if(res!=''){
						$("#docMobileNoError").show();
			            $("#docMobileNoError").html("<h5><strong>* "+res+"</strong></h5>");
			            $("#docMobileNoError").css("color","red");
			            $("#docMobileNo").css("border","2px solid red");
			            docMobileNoError=false;
					}else{
						
			            $("#docMobileNoError").hide();
			            $("#docMobileNo").css("border","");
			            docMobileNoError=true;
					}
				}
			})
			
        }
    }

    function validate_docGender(){
        var docGender=$("[name='docGender']:checked").length;
        if(docGender==''){
            $("#docGenderError").show();
            $("#docGenderError").html("<h5><strong>* Select your Gender</strong></h5>");
            $("#docGenderError").css("color","red");
            $("[name='docGender']").css("border","2px solid red");
            docGenderError=false;
        }else{
            $("#docGenderError").hide();
            $("[name='docGender']").css("border","");
            docGenderError=true; 
        }
    }

    function validate_docNote(){
        var docNote=$("#docNote").val();
        if(docNote==''){
            $("#docNoteError").show();
            $("#docNoteError").html("<h5><strong>* Write Some Note</strong></h5>");
            $("#docNoteError").css("color","red");
            $("[name='docNote']").css("border","2px solid red");
            docNoteError=false;
        }else{
            $("#docNoteError").hide();
            docNoteError=true;
            $("[name='docNote']").css("border","");
        }
    }

    function validate_docPhotoLoc(){
        var docPhotoLoc=$("#fileOb").val();
        if(docPhotoLoc==''){
            $("#docPhotoLocError").show();
            $("#docPhotoLocError").html("<h5><strong>* Choose One Photo</strong></h5>");
            $("#docPhotoLocError").css("color","red");
            $("#fileOb").css("border","2px solid red");
            docPhotoLocError=false;
        }else{
            $("#docPhotoLocError").hide();
            $("#fileOb").css("border","");
            docPhotoLocError=true;
        }
    }
    
    $("#docName").keyup(function(){
        vaildate_docName();
    })

    $("#docEmailId").keyup(function(){
        validate_docEmailId();
    })

    $("#docSpecialization").change(function(){
        validate_docSpecialization();
    })

    $("#docAddress").keyup(function(){
        validate_docAddress();
    })

    $("#docMobileNo").keyup(function(){
        validate_docMobileNo();
    })

    $("[name='docGender']").click(function(){
        validate_docGender();
    })

    $("[name='docNote']").keyup(function(){
        validate_docNote();
    })

    $("#fileOb").change(function(){
        validate_docPhotoLoc();
         if(docPhotoLocError){
           upload_doc_image();
      }
    })
    
    function upload_doc_image(){
		 var docPhoto=document.getElementById("fileOb");
            alert(docPhoto);
            var form=new FormData();
            form.append("image",docPhoto.files[0]);
            var inputs={
                url:"https://api.imgbb.com/1/upload?key=7ae305e35235aea16016baa9b041d943",
                method:"POST",
                timeout:0,
                processData:false,
                mimeType:"multipart/form-data",
                contentType:false,
                data:form
            };
            $.ajax(inputs).done(function(res){
                var photo=JSON.parse(res);
                $("#docPhotoLoc").val(photo.data.url);
            })
	}

    $("#docForm").submit(function(){
        vaildate_docName();
        validate_docEmailId();
        validate_docSpecialization();
        validate_docAddress();
        validate_docMobileNo();
        validate_docGender();
        validate_docNote();
        validate_docPhotoLoc();
        if(docNameError &&
            docEmailIdError &&
            docSpecializationError &&
            docAddressError &&
            docMobileNoError &&
            docGenderError &&
            docNoteError &&
            docPhotoLocError){
                return true;
            }else{
                return false;
            }
    })
})