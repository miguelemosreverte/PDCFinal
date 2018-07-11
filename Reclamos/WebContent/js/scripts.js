jQuery(document).ready(function() {
console.log("listo JQuery")

 $("[name=conoce_chasis]").on("change",function(evt){
	 const chk =  evt.target;
	 const val  = $(chk).val();
	 //console.log(val);
	 if(val==="S"){
		   $("#container_chasis").show();
		   $("#container_patente").show();
		   $("#container_kilometros").show();
		   $("[name=nro_chasis]").prop("disabled",false);
		   $("[name=nro_patente]").prop("disabled",false);
		   $("[name=kilometros]").prop("disabled",false);
	 }
	 else{
		   $("#container_chasis").hide();
		   $("#container_patente").hide();
		   $("#container_kilometros").hide();
		   $("[name=nro_chasis]").prop("disabled",true);
		   $("[name=nro_patente]").prop("disabled",true);
		   $("[name=kilometros]").prop("disabled",true);
	 }
	 
	 
	 
 });

$("[name=nro_chasis]").on("keyup",function(event){ 
	
	if (event.keyCode === 13) {
        $("#nro_chasis_validacion").html("<p>Procesando...</p>");
        const serialized = {nro_chasis : $("[name=nro_chasis]").val()}
        
		$.ajax({
			url: "./validar_nro_chasis.jsp",
			type: "post",
			data: serialized,
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
				$("#nro_chasis_validacion").empty();
				$("#nro_patente_validacion").empty();
			},
			success: function(html) {
				$("#nro_chasis_validacion").html(html)
				$("#nro_patente_validacion").html(html)
			}
		})   
		
	}
});

$("[name=nro_patente]").on("keyup",function(event){ 
	
	if (event.keyCode === 13) {
		
        $("#nro_chasis_validacion").html("<p>Procesando...</p>");
        $("#nro_patente_validacion").html("<p>Procesando...</p>");
        
        const serialized = {
				        		nro_chasis : $("[name=nro_chasis]").val(),
				        		nro_patente : $("[name=nro_patente]").val(),
			        		}
        
		$.ajax({
			url: "./validar_nro_chasis_y_patente.jsp",
			type: "post",
			data: serialized,
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
				$("#nro_chasis_validacion").empty();
				$("#nro_patente_validacion").empty();
			},
			success: function(html) {
				$("#nro_chasis_validacion").html(html)
				$("#nro_patente_validacion").html(html)
			}
		})   
		
	}
});

  $("#boton_registrar").on("click",function(evt){
	  evt.preventDefault()
	  const serialize= $("#reclamosForm").serialize();
	  console.log(serialize)
	  
	  if ($("[name=reclamo]").val().trim() === "") return;
	  
	  $.ajax({
			url: "./registrar.jsp",
			type: "post",
			data: serialize,  
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(html) {
				JReclamos.aftermath(html)
			}
		});
  });
});

var JReclamos = {

		aftermath : (html) => {
			$("#main").hide()
			$("#aftermath").html(html)
		},
		backToHome : () => {
			$("#reclamosForm")["0"].reset()
			$("#main").show()
			$("#aftermath").html("")
		}
		//realizarReclamo = (evt) => {}
		
}
