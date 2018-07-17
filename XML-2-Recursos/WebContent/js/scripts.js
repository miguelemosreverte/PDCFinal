jQuery(document).ready(function() {
	console.log("listo JQuery");
			
	JRecursos.getRecursos();
	
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
	
	$("#main").on("change","[name^=tipo_recurso]:checked",function(e){
		 const thisElement= $(e.target);	
		 const thisElementValue = $(e.target).val();
		 $(thisElement).closest("td").find("[name=tipo_recurso]").val(thisElementValue);
	 });
	
	$("#main").on("change","[name^=vigente]",function(e){
		 const thisElement= $(e.target);
		 console.log(thisElement.prop("checked"));
		 if(thisElement.prop("checked")){
			 $(thisElement).closest("td").find("[name=vigente]").val("S");
			// $(thisElement).closest("tr").find(":radio,:input").prop("disabled",false)
			 
		 }else{
			 $(thisElement).closest("td").find("[name=vigente]").val("N");
			// $(thisElement).closest("tr").find(":radio,:input").prop("disabled",true);
			 //$(thisElement).prop("disabled",false);
		 }
		 
	 });
	
	
});
var JRecursos = {
		sendForm: function(e) {
			 e.preventDefault();
			 console.log($("form").serialize())
			$.ajax({
					url: "./updateRecursos.jsp",
					type: "post",
					data:  $("form").serialize(),
					dataType: "html",
					error: function(hr) {
						$("#message").html(hr.responseText);
					},
					success: function(html) {
						JRecursos.getRecursos();		
					}
				});
		},
		getRecursos: function(e){
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
		},
		guardarTemporalmente:function(){
			console.log("temp");
			$.ajax({
				url: "./saveSession.jsp",
				type: "post",
				data:  $("form").serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
				},
				success: function(html) {
					//JRecursos.getRecursos();
					$("#recursos").html(html)
				}
			});
		},
}
