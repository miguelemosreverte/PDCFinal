jQuery(document).ready(function() {
console.log("listo JQuery")


$("[name=conoces]").on("click change",function(){ 

	if($("[name=conoces]:checked").val()=="N"){
		
		$("[name=chasis]").attr("disabled",true);
		$("[name=rowdelchasis]").hide();
		$("[name=petente]").attr("disabled",true);
		$("[name=rowdelapatente]").hide();
		$("[name=km]").attr("disabled",true);
		$("[name=rowdelkm]").hide();
	}else{
		$("[name=chasis]").attr("disabled",false);
		$("[name=rowdelchasis]").show();
		$("[name=petente]").attr("disabled",false);
		$("[name=rowdelapatente]").show();
		$("[name=km]").attr("disabled",false);
		$("[name=rowdelkm]").show();
	}

});

	$("[name=chasis]").on("keydown",function(event){ 
		
		if (event.keyCode === 13) {
            $("#resultchasis").html("<p>Procesando...</p>");
			$.ajax({
				url: "./Validar.jsp",
				type: "get",
				data: {
					chasis : $("[name=chasis]").val(),
				},
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					$("#resultchasis").empty();
					$("#resultpatente").empty();
				},
				success: function(html) {
					$("#resultchasis").html(html);
				}
			})   
			
		}
	

	});
	
	$("[name=patente]").on("keydown",function(event){ 
		
		if (event.keyCode === 13) {
            $("#resultpatente").html("<p>Procesando...</p>");
            $("#resultchasis").html("<p>Procesando...</p>");
			$.ajax({
				url: "./Validar.jsp",
				type: "get",
				data: {
					chasis :     $("[name=chasis]").val(),
					patente:     $("[name=patente]").val(),
				},
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					$("#resultpatente").empty();
					$("#resultchasis").empty();
				},
				success: function(html) {
					$("#resultpatente").empty();
					$("#resultchasis").empty();
					$("#resultpatente").html(html);
					$("#resultchasis").html(html);
				}
			})   
			
		}

	});


});

var JReclamos= {
		registrar: function(event) { 
			event.preventDefault();
			console.log("clickeado");

			$.ajax({
				url: "./Registrar.jsp",
				type: "post",
				data: $("#form").serialize(),
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
					
				},
				success: function(html) {
					jUtils.hiding("main",false);
					$("#finalizar").html(html);
					jUtils.showing("finalizar");
				}
			
		    });
		 
      },
      volver: function() {
			jUtils.hiding("finalizar");
			jUtils.showing("main");
			window.location.reload();
		}
}
