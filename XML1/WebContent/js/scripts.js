var JGrupos = {
		 cargarTabla: function() {
			 $.ajax({
					url: "./get_grupos.jsp",
					type: "get",
					data: $.param($("[name=cod_area]")),
					dataType: "html",
					error: function(hr) {
						$("#message").html(hr.responseText);
						$("[name=resultTabla]").empty();
					},
					success: function(html) {
						$("#iresultTabla").html(html);
						$("#message").empty();
					}
				  });
		},	
 }
jQuery(document).ready(function() {
	  console.log("listo JQuery")
      $("#imain").on( "change","[name=cod_area]", function() {
	   JGrupos.cargarTabla();
      });

      $("#imain").on( "click","[name=cancelar]", function() {
	   JGrupos.cargarTabla();
	  });
  
      $("#imain").on( "click","[name=guardar]", function() {
    	  if($(this ).closest("tr").find("[name=nom_grupo]").val()!==""){
    	  $.ajax({
			url: "./actualizar_guardar_grupos.jsp",
			type: "post",
			data: { 
					"nom_grupo":$(this ).closest("tr").find("[name=nom_grupo]").val(),
				    "nro_grupo":$(this ).closest("tr").find("[name=nro_grupo]").val(),
				    "exclusivo":$(this ).closest("tr").find("[name=exclusivo]").prop("checked"),
				    "vigente":$(this ).closest("tr").find("[name=vigente]").prop("checked"),
				    "cod_area":$("[name=cod_area]").val()
			},
			dataType: "html",
			error: function(hr) {
				$("#message").html(hr.responseText);
				$("[name=resultTabla]").empty();
			},
			success: function(html) {
				$("#iresultTabla").html(html);
				$("#message").empty();
				JGrupos.cargarTabla();
			}
		   });
    	 }
    	  else{
    		  $(this ).closest("tr").find("[name=nom_grupo]").focus();
    		  confirm("Debe ingresar un nombre de grupo :)");
    		  return;
    		  
    	  }
	  });

});



