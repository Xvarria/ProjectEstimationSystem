$(document).ready(function(){
	initDatatableOnList();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
});

function initDatatableOnList(){
	subtaskTypeCategoryTableInit=false;
	cTable=null;
	$.ajax('../ajax/getSubtaskTypeCategoryList.do',{
		success: function(data) {
			refreshSubtaskTypeCategoryTable(data)
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

function refreshSubtaskTypeCategoryTable(data) {
	console.log("START refreshSubtaskTypeCategoryTable");
	var object = JSON.parse(data);
	if (subtaskTypeCategoryTableInit == false) {
		subtaskTypeCategoryTableInit = true;
		cTable = $('#subtaskTypeCategoryTable').DataTable({
			data : object,
			columns : [ {
				data : 'subtaskTypeCategoryId'
			}, {
				data : 'description'
			}, {
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 2,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var deleteLink = '<a href="javascript:deleteSubtaskTypeCategorySubmit('+ full.subtaskTypeCategoryId + ')" title="Delete SubtaskTypeCategory"><img border="0" alt="Delete" src="../images/cross.png" width="20" height="20"> </a>'; 
					var modifyLink = '<a href="javascript:updateSubtaskTypeCategoryLoad('+ full.subtaskTypeCategoryId + ')" title="Modify SubtaskTypeCategory"><img border="0" alt="Modify" src="../images/page_edit.png" width="20" height="20"> </a>';
					return deleteLink + "&nbsp;" + modifyLink;
				}
			}],
			language: {emptyTable: 'No data found'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#SubtaskTypeCategoryTable').show();
		console.log("INIT refreshSubtaskTypeCategoryTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshSubtaskTypeCategoryTable");
	}
	console.log("FINISH refreshSubtaskTypeCategoryTable");
}

function createSubtaskTypeCategorySubmit(){
	$('#action').val('CREATE');
	$("form").submit();
}

function updateSubtaskTypeCategoryLoad(subtaskTypeCategoryId){
	$.ajax('../ajax/getSubtaskTypeCategoryById.do?subtaskTypeCategoryIdStr='+subtaskTypeCategoryId,{
		success: function(data) {
			var object = JSON.parse(data)
			if (object.responseStatus != 'ERROR'){
				var subtaskTypeCategory = object.subtaskTypeCategory;
				$('#subtaskTypeCategoryId').val(subtaskTypeCategory.subtaskTypeCategoryId);
				$('#description').val(subtaskTypeCategory.description);
				$('#baseHour').val(subtaskTypeCategory.baseHour);
				$('#multiplexor').val(subtaskTypeCategory.multiplexor);
				$('#create-button').addClass("hidden");
				$('#update-button').removeClass("hidden");
			}else{
				alert(object.errorMessage);
			}
		},
		error: function(){
			alert("Error loading the values for the SubtaskTypeCategory to be updated");
		}
	});
}

function updateSubtaskTypeCategorySubmit(){
	$('#action').val('UPDATE');
	$("form").submit();
}

function deleteSubtaskTypeCategorySubmit(subtaskTypeCategoryId){
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
				$('#subtaskTypeCategoryId').val(subtaskTypeCategoryId);
				
				$('#action').val('DELETE');
				$("form").submit();
			}
    	}
	});
}

function cancelUpdateLoad(){
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");	
	$('#subtaskTypeCategoryId').val("");
	$('#description').val("");
	$('#baseHour').val("");
	$('#multiplexor').val("");	
}

function cancelCreateLoad(){
	$('#subtaskTypeCategoryId').val("");
	$('#description').val("");
	$('#baseHour').val("");
	$('#multiplexor').val("");	
}
