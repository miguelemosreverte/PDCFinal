jQuery(document).ready(function() {
	console.log("listo JQuery")
	console.log($("#idProvinciasPepe"))
});

var JHoteles = {
		getLocalidades: function(sel) {
		
            $("#result").html("<p>Procesando...</p>");
			$.ajax({
				url: "./getLocalidades.jsp",
				type: "get",
				data: {"codigoProvincia":sel.value},
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					$("#result").empty();
				},
				success: function(html) {
					$("#result").html(html);
				}
			})
		},
		buscar : function() {
			console.log($("window #idProvincias"))
			
		}
		
}
