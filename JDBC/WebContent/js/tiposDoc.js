jQuery(document).ready(function(){
	$("#nuevo").click(function() {
		jTiposDoc.nuevo();
	});
	
	$("a[name=eliminar]").click(function() {
		jTiposDoc.eliminar($(this));
	});

	$("a[name=editar]").click(function() {
		jTiposDoc.editar($(this).closest("tr").find("input[name=cod_tipo_documento]").val());
	});
}); 

var jTiposDoc = {
		
	nuevo: function() {
		jUtils.hiding("main", false);
		jUtils.executing("response");
		$.ajax({
			url: "./tiposDocNuevo.jsp",
			type: "get",
			datatype: "html",
			error: function(hr) {
				jUtils.showing("response", hr.responseText);
				jUtils.showing("main");
			},
			success: function(html) {
				jUtils.showing("response", html);
				fxTiposDoc.confBotones();
			}
		});
	},
	
	guardar: function() {
        if($.trim($("input[name=cod_tipo_documento]").val()) == "") {
            alert("Debe informar el código del tipo de documento");
            $("input[name=cod_tipo_documento]").focus();
            return false;
        }
        
        if($.trim($("input[name=tipo_documento]").val()) == "") {
            alert("Debe informar el nombre del tipo de documento");
            $("input[name=tipo_documento]").focus();
            return false;
        }
        
        if($.trim($("input[name=mascara]").val()) == "") {
            alert("Debe informar la máscara");
            $("input[name=mascara]").focus();
            return false;
        }
        
        if($.trim($("select[name=tipo_persona]").val()) == "") {
            alert("Debe informar si el documento pertenece a una persona física o jurídica");
            $("select[name=tipo_persona]").focus();
            return false;
        }

        jUtils.hiding("response", false);
		jUtils.executing("aux");
		
		$.ajax({
			url: "./tipoDocGuardar.jsp",
			type: "post",
			data: $.param($("input[type=hidden],input[type=text],input[type=checkbox]:checked,select", $("#form_nuevo"))),
			datatype: "html",		
			error: function(hr) {
				jUtils.showing("response");
				jUtils.showing("aux", hr.responseText);
			},
			success: function(html) {
				$(location).attr("href", "./index.jsp");
			}
		});
	},

	eliminar: function(obj) {
		jUtils.executing("aux");
		jUtils.hiding("main", false);
		
		var codigo = $(obj).closest("tr").find("input[name=cod_tipo_documento]").val();
		
		$.ajax({
			url: "./tiposDocEliminar.jsp",
			type: "post",
			data: $.param({"cod_tipo_documento": codigo}),
			datatype: "html",
			error: function(hr) {
				jUtils.showing("aux", hr.responseText);
				jUtils.showing("main");
			},
			success: function(html) {
				jUtils.hiding("aux");
				jUtils.showing("main");
				$(obj).closest("tr").remove();
			}
		});		
	},
	
	editar: function(codigo) {
		jUtils.hiding("main", false);
		jUtils.executing("response");
		$.ajax({
			url: "./tiposDocEditar.jsp",
			type: "post",
			data: $.param({"cod_tipo_documento": codigo}),
			datatype: "html",
			error: function(hr) {
				jUtils.showing("response", hr.responseText);
				jUtils.showing("main");
			},
			success: function(html) {
				jUtils.showing("response", html);
				fxTiposDoc.confBotones("U");
			}
		});
	},
	
	cancelar: function() {
		jUtils.hiding("response");
		jUtils.showing("main");
	}
}

var fxTiposDoc = {
		
	confBotones: function() {
		$("#cancelar").click(function() {
			jTiposDoc.cancelar();
		});
		
		$("#aceptar").click(function() {
			jTiposDoc.guardar();
		});
	}
		
};