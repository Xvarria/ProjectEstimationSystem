var medidorTableInit=false;
cTable=null;

function initDatatableOnList(){
	var medidorId = $("#medidorId").val();
	$.ajax('../ajax/getLecturaList.do?medidorId='+medidorId,{
		success: function(data) {
			refreshLecturaTable(data)
		},
		error: function(){
			exceptionHandler
		}
	});

	//mostrarMensajes();
	var mensaje = $('#mensaje').val();
	if (mensaje != ''){
		alert(mensaje);
	}
}

function exceptionHandler(){
	refreshFamilyPageTable([]);
	alert('Error')
}

function refreshLecturaTable(data) {
	console.log("START refreshLecturaTable");
	var object = JSON.parse(data);
	if (medidorTableInit == false) {
		medidorTableInit = true;
		cTable = $('#LecturaTable').DataTable({
			data : object,
			columns : [ {
				data : 'lecturaId'
			}, {
				data : 'temperatura'
			}, {
				data : 'fechaLecturaFmt'
			}, {
				data : 'volumen'
			}, {
				data : 'error'
			}],
			language: {emptyTable: 'Lecturas no encontradas'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#LecturaTable').show();
		console.log("INIT refreshLecturaTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshLecturaTable");
	}
	console.log("FINISH refreshLecturaTable");
}

function refrescarDatos(){
	initDatatableOnList();
}

function mostarOcultarDetalle(){
	let estadoActual = $('#estadoDetalle').val();
	if (estadoActual == 'visible'){
		$('div[name="detalleLectura"]').hide();
		$('#estadoDetalle').val('invisible');
	}else{
		$('div[name="detalleLectura"]').show();
		$('#estadoDetalle').val('visible');		
	}
	initDatatableOnList();
}
