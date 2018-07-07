jQuery(document).ready(function() {
console.log("listo JQuery")

});

var JTickets= {
		buscar: function() {
			$("#result").html("<p>Procesando...</p>");
			
			$.ajax({
				url: "./Tickets.jsp",
				type: "get",
				data: $("[name=crit_busq]").serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					$("#result").empty();
				},
				success: function(html) {
					$("#result").html(html);
				}
			});
			
		},
		registrar: function(evt) {
			evt.preventDefault();
			$.ajax({
				url: "./Tickets.jsp",
				type: "post",
				data: $("[name=regform]").serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					
				},
				success: function(html) {
					window.location.reload();
				}
			});	
					
		
        }
}
