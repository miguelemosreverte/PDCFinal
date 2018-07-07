jQuery(document).ready(function() {
console.log("listo JQuery")


});

var JParcial = {
		getCategorias: function() {
			if($("[name=carreras]").val() != ""){
				$("#result").html("<p>Procesando...</p>");
				$.ajax({
					    url: "./mostrarlistado.jsp",
						type: "post",
						data: { "cod": $("[name=carreras]").val() },
						dataType: "html",
						error: function(hr) {
							$("#message").html(hr.responseText);
							$("#result").empty();
						},
						success: function(html) {
							$("#result").html(html);
						}		
				});
			
			}
		},		
}


