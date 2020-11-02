$(document).ready(function(){
	initDatatableOnList();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
});

function refreshSubtaskCategoryId(){
	var selectedValue = $("#select-subtaskCategoryId").val();
	$("#subtaskCategoryTypeId").val(selectedValue);
}

function initDatatableOnList(){
	subtaskTypeTableInit=false;
	cTable=null;
	$.ajax('../ajax/getSubtaskTypeList.do',{
		success: function(data) {
			refreshSubtaskTypeTable(data)
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

function refreshSubtaskTypeTable(data) {
	console.log("START refreshSubtaskTypeTable");
	var object = JSON.parse(data);
	if (subtaskTypeTableInit == false) {
		subtaskTypeTableInit = true;
		cTable = $('#subtaskTypeTable').DataTable({
			data : object,
			columns : [ {
				data : 'subtaskTypeId'
			}, {
				data : 'subtaskTypeCategory.description'
			}, {
				data : 'description'
			}, {
				data : 'calculation'
			}, {
				data : 'reference'
			}, {
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 5,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var deleteLink = '<a href="javascript:deleteSubtaskTypeSubmit('+ full.subtaskTypeId + ')" title="Delete SubtaskType"><img border="0" alt="Delete" src="../images/cross.png" width="20" height="20"> </a>'; 
					var modifyLink = '<a href="javascript:updateSubtaskTypeLoad('+ full.subtaskTypeId + ')" title="Modify SubtaskType"><img border="0" alt="Modify" src="../images/page_edit.png" width="20" height="20"> </a>';
					return deleteLink + "&nbsp;" + modifyLink;
				}
			}],
			language: {emptyTable: 'No data found'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#SubtaskTypeTable').show();
		console.log("INIT refreshSubtaskTypeTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshSubtaskTypeTable");
	}
	console.log("FINISH refreshSubtaskTypeTable");
}

function createSubtaskTypeSubmit(){
	$('#action').val('CREATE');
	$("form").submit();
}

function updateSubtaskTypeLoad(subtaskTypeId){
	$.ajax('../ajax/getSubtaskTypeById.do?subtaskTypeIdStr='+subtaskTypeId,{
		success: function(data) {
			var object = JSON.parse(data)
			if (object.responseStatus != 'ERROR'){
				var subtaskType = object.subtaskType;
				$('#subtaskTypeId').val(subtaskType.subtaskTypeId);
				$('#select-subtaskCategoryId').val(subtaskType.subtaskTypeCategoryId);
				refreshSubtaskCategoryId()
				$('#description').val(subtaskType.description);
				$('#calculation').val(subtaskType.calculation);
				$('#reference').val(subtaskType.reference);
				$('#create-button').addClass("hidden");
				$('#update-button').removeClass("hidden");
			}else{
				alert(object.errorMessage);
			}
		},
		error: function(){
			alert("Error loading the values for the SubtaskType to be updated");
		}
	});
}

function updateSubtaskTypeSubmit(){
	$('#action').val('UPDATE');
	$("form").submit();
}

function deleteSubtaskTypeSubmit(subtaskTypeId){
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
				$('#subtaskTypeId').val(subtaskTypeId);
				
				$('#action').val('DELETE');
				$("form").submit();
			}
    	}
	});
}

function cancelUpdateLoad(){
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");	
	cleanFields();
}

function cancelCreateLoad(){
	cleanFields();
}

function cleanFields(){
	$('#subtaskTypeId').val("");
	$('#select-subtaskCategoryId').val(0);
	$('#description').val("");
	$('#calculation').val("");
	$('#reference').val("");
}
