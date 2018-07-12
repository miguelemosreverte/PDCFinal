jQuery(document).ready(function() {
console.log("listo JQuery")
 $("#main_container").on("click change","[name=elem_tag]",function(event){
	 const thisElement  = event.target;
	 cod_area = $(thisElement).val();
	 $.ajax({
			url: "./grupos_servlet.jsp",
			type: "get",
			data: {"cod_area":cod_area},  
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(html) {
				console.log(html)
				$("#result").html(html);
			}
		});
	 
 });
$("#main_container").on("click","[name=boton_guardar]",function(event){
	event.preventDefault();
	const thisButtonElement  = event.target;		
	const myRow =  $(thisButtonElement).closest("tr");	
	const cod_area =  $("[name=elem_tag]").val();
	const nro_grupo = myRow.find("[name=nro_grupo]").val().trim();
	const nom_grupo = myRow.find("[name=nom_grupo]").val().trim();

	if(nom_grupo ==""){
		alert("porfavor, ingrese un nombre");
		return;
	}
	const exclusivo = myRow.find("[name=exclusivo]").prop("checked");
	const vigente   =  myRow.find("[name=vigente]").prop("checked");
	const serialized = {cod_area, nro_grupo, nom_grupo,exclusivo,vigente}
	//console.log(serialized);
	 $.ajax({
			url: "./grupos_servlet.jsp",
			type: "post",
			data: serialized,  
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
			},
			success: function(html) {
				 if(nro_grupo===""){
						 $.ajax({
								url: "./nueva_row.jsp",
								type: "get",
								dataType: "html",
								error: function(hr) {
									$("#message").html(hr.responseText);
								},
								success: function(html) {
									console.log("por aca");
									$("#tabla > tbody:last").append(html);
								}
							});
				 }
			}
			
	});
});
$("#main_container").on("click","[name=boton_cancelar]",function(event){
	event.preventDefault();
	const cod_area =  $("[name=elem_tag]").val();
	const thisElement  = event.target;		
	const myRow =  $(thisElement).closest("tr");	

	const vigente = myRow.find("[name=vigente]")
	const exclusivo = myRow.find("[name=exclusivo]")
	const nom_grupo = myRow.find("[name=nom_grupo]")
	
	const vigente_historical = myRow.find("[name=vigente_historical]")
	const exclusivo_historical = myRow.find("[name=exclusivo_historical]")
	const nom_grupo_historical = myRow.find("[name=nom_grupo_historical]")
   
	const nro_grupo = myRow.find("[name=nro_grupo]").val().trim();
    			
	if(nro_grupo === ""){
		vigente.attr("checked", false);
	    exclusivo.attr("checked", false);
	    nom_grupo.val("");
	}
	else{
	    vigente.prop("checked", vigente_historical.prop("checked"));
	    exclusivo.prop("checked", exclusivo_historical.prop("checked"));
	    nom_grupo.val(nom_grupo_historical.val().trim());
	}
			
	});



});



var JGrupos = {
		
}
