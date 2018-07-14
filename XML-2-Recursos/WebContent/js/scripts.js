jQuery(document).ready(function() {
	console.log("listo JQuery");
			$.ajax({
				url: "./getRecursos.jsp",
				type: "get",
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
				},
				success: function(html) {
					$("#recursos").html(html)
				}
			});
	
	$("#main").on("change","[name^=tipo_propietario]:checked",function(e){
		 const thisElement= $(e.target);	
		 const thisElementValue = $(e.target).val();
		 $(thisElement).closest("td").find("[name=tipo_propietario]").val(thisElementValue);
		 $.ajax({
				url: "./changeCustom.jsp",
				type: "get",
				data:  $(thisElement).closest("td").find("[name=tipo_propietario]").serialize(),
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
	
	$("#main").on("click","#boton_agregar",function(e){
		 /*console.log($('input:not([value!=""])'))*/
		 // $("select")
		 e.preventDefault();
		 const thisElement = $(e.target).text();
		 console.log(thisElement);
	 });
    
	 $("#main").on("change","[name^=tipo_recurso]:checked",function(e){
		 const thisElement= $(e.target);	
		 const thisElementValue = $(e.target).val();
		 $(thisElement).closest("td").find("[name=tipo_recurso]").val(thisElementValue);
	 });
	
	
});


var jX = {
		a: function() {
			
		},
		
}
