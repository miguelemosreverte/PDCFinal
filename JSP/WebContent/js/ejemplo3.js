 jQuery(document).ready(function() {
	 $("input:visible:enabled:first").focus();
 });

 var jNumber = {
	
    allowOnlyNumbers: function(e) {
    	if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 173, 190]) !== -1 || (e.keyCode == 65 && e.ctrlKey === true) || (e.keyCode >= 35 && e.keyCode <= 39)) {
	        return;
    	}
    	else if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
           e.preventDefault();
    	}
    },
    
    validForm: function() {
    	if($.trim($("input[name=variable1]").val()) == "") {
    		alert("Debe informar la variable 1");
    		$("input[name=variable1]").focus();
    		return false;
    	}
    	
    	if($.trim($("input[name=variable2]").val()) == "") {
    		alert("Debe informar la variable 2");
    		$("input[name=variable2]").focus();
    		return false;
    	}
    	
    	$("#form").submit();
    }		 
		 
 };