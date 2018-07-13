jQuery(document).ready(function() {
	console.log("listo JQuery");
	$("#main" ).on("change","[name=tipo_recurso]",function(e){
		const thisElement = $(e.target);
		//console.log(thisElement.serialize());
			$.ajax({
				url: "./getRecursos.jsp",
				type: "get",
				data: thisElement.serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
				},
				success: function(html) {
					$("#recursos").html(html)
				}
			});
		
    });
	
	
	$("#main").on("change","[name^=tipo_propietario]:checked",function(e){
		const thisElement = $(e.target);
		//console.log(thisElement.serialize());
			$.ajax({
				url: "./changeCustom.jsp",
				type: "get",
				data: thisElement.serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
				},
				success: function(html) {
					thisElement.closest("tr").find("[name=custom_div]").empty();
					thisElement.closest("tr").find("[name=custom_div]").html(html);
					
				}
			});
		
    });
	
});




var jX = {
		a: function() {
			
		},
		
}
