
var JVideos= {
		buscar: function() {
			$("#resultchasis").html("<p>Procesando...</p>");
			
			$.ajax({
				url: "./getvideos.jsp",
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
			})
				
		},
		volver: function() {
			jUtils.hiding("reproducir");
			jUtils.showing("main");
			window.location.reload();
		},
}

jQuery(document).ready(function() {
	console.log("listo JQuery")
	
	$("#result").on("click",".elemento-video",function(){
		
		$.ajax({
			url: "./reproducir.jsp",
			type: "post",
			data: {
					link :     $( this ).attr("data-video-link"),
					cantante : $( this ).attr("data-video-cantante"),
					titulo :   $( this ).attr("data-video-titulo"),
					numero :   $( this ).attr("data-video-numero")
		    },
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
				$("#reproducir").empty();
				jUtils.showing("main");
			},
			success: function(html) {
				jUtils.hiding("main",false);
				$("#reproducir").html(html);
				jUtils.showing("reproducir");
			}
		});

	 });

});

