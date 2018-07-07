$(document).ready(function() {
	$("input[type=checkbox]").click(function() {
		$("input[type=checkbox]:checked", $(this).closest("tr")).not($(this)).prop("checked", false);
	});	

	$("input[name=resultados-prode]").click(function() {
		if($("input[type=checkbox]:checked").size() != $("tbody tr").size()) {
			alert("Debe indicar todos los resultados");
			return false;
		}
		
		$("#prode").hide();
		$("#resultados").html("<p>Procesando...</p>");
				
		$.ajax({
			url: "./resultados.jsp",
			type: "post",
			data: $.param($("input[type='checkbox']:checked", $("#form"))),
			datatype: "html",
			error: function(hr) {
				$("#resultados").empty();
				$("#message").html(hr.responseText);
				$("#prode").show();
			},
			success: function(html) {
				$("#resultados").html(html);
				$("#prode").show();
				$("#bresultados").hide();
				$("#bvolver").show();
			}			
		});		
	});	

	$("input[name=volver]").click(function() {
		window.location.href="index.jsp";
	});	
});
