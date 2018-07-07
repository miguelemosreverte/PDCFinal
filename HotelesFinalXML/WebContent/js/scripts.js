jQuery(document).ready(function() {
	console.log("listo JQuery")
	if($.trim($("[name=provincias]").val())!==""){
		JHoteles.getLocalidades();
	}
});

var JHoteles = {
		getLocalidades: function() {
            $("#result-localidades").html("<p>Procesando...</p>");
			$.ajax({
				url: "./getLocalidades.jsp",
				type: "get",
				data: $.param($("[name=provincias]")),  
				/*{"codigoProvincia":$("[name=provincias]").val()},*/
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					$("#result-localidades").empty();
				},
				success: function(html) {
					$("#result-localidades").html(html);
				}
			});
		},
		buscar : function() {
			if($("[name=provincias]").val()===""){
				alert("Debe elegir una provincia :D");
				return;
			} 
			if($.trim($("[name=localidades]").val())===""){
				alert("Debe elegir una localidad :D");
				return;
			}
			if($.trim($("[name=fecha-desde]").val())===""){
				alert("Debe elegir una fecha-desde :D");
				return;
			}
			if($("[name=fecha-hasta]").val()===""){
				alert("Debe elegir una fecha-hasta :D");
				return;
			}
			if($("[name=tipos-habitaciones]").val()===""){
				alert("Debe elegir un tipos de habitación :D");
				return;
			}
			
			$("#result-fecha-hoteles").html("<p>Procesando...</p>");
			$.ajax({
				url:  "./getFechasHoteles.jsp",
				type: "get",
				data: $("#idForm").serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					$("#result-fecha-hoteles").empty();
				},
				success: function(html) {
					$("#result-fecha-hoteles").html(html);
				}
			});

		 
		}
}
