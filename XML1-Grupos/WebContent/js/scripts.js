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
				$("#result").html(html);
			}
		});
	 
 });
$("#main_container").on("click","[name=boton_guardar]",function(event){
	const thisButtonElement  = event.target;		
	const myRow =  $(thisButtonElement).closest("tr");	
	const cod_area =  $("[name=elem_tag]").val();
	const nro_grupo = myRow.find("[name=nro_grupo]").val().trim();
	const nom_grupo = myRow.find("[name=nom_grupo]").val().trim();
	console.log(nom_grupo);
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
	const cod_area =  $("[name=elem_tag]").val();
    console.log("clicked CANCELAR");
    
	
			
			
	});

$("#main_container").on("click","[name=exclusivo]",function(event){
	JGrupos.visibilizeMenu(event)
});

$("#main_container").on("click","[name=vigente]",function(event){
		JGrupos.visibilizeMenu(event)
});

$("#main_container").on("click","[name=nom_grupo]",function(event){
	JGrupos.visibilizeMenu(event)
});

});



var JGrupos = {
		visibilizeMenu: function(event) {
			  	const thisElement  = event.target;		
				const myRow =  $(thisElement).closest("tr");	

				const changed =  [ 
						myRow.find("[name=vigente]").prop("checked")
				    ,   myRow.find("[name=exclusivo]").prop("checked")
				    ,   myRow.find("[name=nom_grupo]").val().trim()
				    ]

				const historicals =  
					[ 
						myRow.find("[name=vigente_historical]").prop("checked")
				    ,   myRow.find("[name=exclusivo_historical]").prop("checked")
				    ,   myRow.find("[name=nom_grupo_historical]").val().trim()
				    ]
			    
				
			    const hasChanges = historicals
			    						.map((elem, elemIndex) => elem == changed[elemIndex])
			    						.reduce((a,next) => a && next, true)
			    			
				const cancelButton = myRow.find("[name=boton_cancelar]")["0"]	
				const guardarButton = myRow.find("[name=boton_guardar]")["0"]
				
				console.log(cancelButton, cancelButton)		
				
				if (!hasChanges){
					guardarButton.style = "display:true"
					cancelButton.style = "display:true"
				}
				if (hasChanges){
					guardarButton.style = "display:false"
					cancelButton.style = "display:false"
				}
				console.log(changed, historicals, hasChanges)
		},
		
}
