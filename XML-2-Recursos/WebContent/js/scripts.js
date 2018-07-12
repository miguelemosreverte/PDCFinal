jQuery(document).ready(function() {
	console.log("listo JQuery");
	
	
	 $.ajax({
			url: "./getRecursos.jsp",
			type: "get",
			data: {"tipo_recurso": "I"},  
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(html) {
				 $("#recursos").html(html)
			}
	 });


});

var jX = {
		a: function() {
			
		},
		
}
