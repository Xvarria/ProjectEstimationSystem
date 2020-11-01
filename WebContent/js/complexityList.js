$(document).ready(function(){
	initDatatableOnList();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
});

function initDatatableOnList(){
	complexityTableInit=false;
	cTable=null;
	$.ajax('../ajax/getComplexityList.do',{
		success: function(data) {
			refreshComplexityTable(data)
		},
		error: function(){
			exceptionHandler
		}
	});

	//mostrarMensajes();
	var message = $('#message').val();
	if (message != ''){
		alert(message);
	}
}

function exceptionHandler(){
	refreshFamilyPageTable([]);
	alert('Error')
}

function refreshComplexityTable(data) {
	console.log("START refreshComplexityTable");
	var object = JSON.parse(data);
	if (complexityTableInit == false) {
		complexityTableInit = true;
		cTable = $('#complexityTable').DataTable({
			data : object,
			columns : [ {
				data : 'complexityId'
			}, {
				data : 'description'
			}, {
				data : 'baseHour'
			}, {
				data : 'multiplexor'
			}, {
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 4,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var deleteLink = '<a href="javascript:deleteComplexitySubmit('+ full.complexityId + ')" title="Delete Complexity"><img border="0" alt="Delete" src="../images/cross.png" width="20" height="20"> </a>'; 
					var modifyLink = '<a href="javascript:updateComplexityLoad('+ full.complexityId + ')" title="Modify Complexity"><img border="0" alt="Modify" src="../images/page_edit.png" width="20" height="20"> </a>';
					return deleteLink + "&nbsp;" + modifyLink;
				}
			}],
			language: {emptyTable: 'No data found'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#ComplexityTable').show();
		console.log("INIT refreshComplexityTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshComplexityTable");
	}
	console.log("FINISH refreshComplexityTable");
}

function createComplexitySubmit(){
	$('#action').val('CREATE');
	$("form").submit();
}

function updateComplexityLoad(complexityId){
	$.ajax('../ajax/getComplexityById.do?complexityIdStr='+complexityId,{
		success: function(data) {
			var object = JSON.parse(data)
			if (object.responseStatus != 'ERROR'){
				var complexity = object.complexity;
				$('#complexityId').val(complexity.complexityId);
				$('#description').val(complexity.description);
				$('#baseHour').val(complexity.baseHour);
				$('#multiplexor').val(complexity.multiplexor);
				$('#create-button').addClass("hidden");
				$('#update-button').removeClass("hidden");
			}else{
				alert(data.errorMessage);
			}
		},
		error: function(){
			alert("Error loading the values for the Complexity to be updated");
		}
	});
}

function updateComplexitySubmit(){
	$('#action').val('UPDATE');
	$("form").submit();
}

function deleteComplexitySubmit(complexityId){
	bootbox.confirm({
	    message: "Are you sure you want to delete?",
	    buttons: {
	        confirm: {
	            label: 'Yes',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: 'No',
	            className: 'btn-danger'
	        }
	    },
	    callback: function (result) {
	        console.log('This was logged in the callback: ' + result);
			if (result){
				$('#complexityId').val(complexityId);
				
				$('#action').val('DELETE');
				$("form").submit();
			}
    	}
	});
}

function cancelUpdateLoad(){
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");	
	$('#complexityId').val("");
	$('#description').val("");
	$('#baseHour').val("");
	$('#multiplexor').val("");	
}

function cancelCreateLoad(){
	$('#complexityId').val("");
	$('#description').val("");
	$('#baseHour').val("");
	$('#multiplexor').val("");	
}
