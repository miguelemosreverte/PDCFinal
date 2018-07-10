jQuery(document).ready(function() {
	console.log("listo JQuery")
	if($.trim($("[name=provincias]").val())!==""){
		JHoteles.getLocalidades();
	}
	
	
	$("[name=tablaContexto]").on("change","[name=provincias]",function(){
		JHoteles.getLocalidades();
	});
	
});

const yourStandardMarielaAjaxCall = (parameters) => {
	$(parameters.id).html("<p>Procesando...</p>");
	$.ajax({
		url: parameters.url,
		type: "get",
		data: parameters.data,  
		dataType: "html",
		error: function(hr) {
			$("#message").html(hr.responseText);
			$(parameters.id).empty();
		},
		success: function(html) {
			$(parameters.id).html(html);
		}
	});
}
var JHoteles = {
		getLocalidades: function() {
			yourStandardMarielaAjaxCall({
					  id : "#result_localidades"
					, data : $.param($("[name=provincias]"))
					, url : "./getLocalidades.jsp"
					});
		},
		guardarReserva: function(event) {
			const thisButtonElement  = event.target;		
			const myRow =  $(thisButtonElement).closest("tr");	
			
			const fecha =  myRow.find("[name=fecha]").text().trim();
			const tipo_habitacion  =  myRow.find("[name=tipo_habitacion]").text().trim();
			const habitaciones_disponibles   =  myRow.find("[name=habitaciones_disponibles]").text().trim();
			const cantidad  =  myRow.find("[name=cantidad_reservada]").val().trim();
			const nro_hotel = myRow.find("[name=nro_hotel]").val().trim();
			

			//const myTableContainer =  $(thisButtonElement).closest("[name=HotelNameContainer]");	
			//const hotelName = myTableContainer.find("[name=HotelName]").text();
			
			const serialized = {nro_hotel, fecha, tipo_habitacion, habitaciones_disponibles, cantidad}
			
			const cantidadEsValida = Number.isInteger(Number(cantidad)) && cantidad > 0
			if (!cantidadEsValida){
				alert("La cantidad no es válida " + cantidad);
				return;
			}
			
			$.ajax({
				url: "./reservar.jsp",
				type: "post",
				data: serialized,  
				dataType: "html",
				error: function(hr) {
					$("#message").html(hr.responseText);
				},
				success: function(html) {
					alert(html)
					$("#result_fecha_hoteles").html(html);
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
			if($.trim($("[name=fecha_desde]").val())===""){
				alert("Debe elegir una fecha-desde :D");
				return;
			}
			if($("[name=fecha_hasta]").val()===""){
				alert("Debe elegir una fecha-hasta :D");
				return;
			}
			if($("[name=tipos_habitaciones]").val()===""){
				alert("Debe elegir un tipos de habitación :D");
				return;
			}
			
			yourStandardMarielaAjaxCall({
				  id : "#result_fecha_hoteles"
				, data : $("#idForm").serialize()
				, url : "./getFechasHoteles.jsp"
				});	
			alert($("#idForm").serialize());
		 
		}
}
