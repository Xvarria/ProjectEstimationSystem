$(document).ready(function(){
	initDatatableOnList();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
});

function refreshSubtaskCategoryId(){
	var selectedValue = $("#select-subtaskCategoryId").val();
	loadSubtaskTypeSelect(selectedValue);
	$("#subtaskTypeId").val(0);
}

function refreshSubtaskTypeId(){
	var selectedValue = $("#select-subtaskTypeId").val();
	$("#subtaskTypeId").val(selectedValue);
}

function refreshAutoCalculation(){
	$('#autoCalculation').val($("#chk-autoCalculation").prop("checked"));
}

function loadSubtaskTypeSelect(category){
	$.ajax('../ajax/getSubtaskTypeByCategoryId.do?subtaskTypeCategoryId='+category,{
		async: false,
		success: function(data) {
			var object = JSON.parse(data);
			$("#select-subtaskTypeId option").each(function(index, element){$(this).remove();});
			$("#select-subtaskTypeId").append("<option value='0' label='--- Select ---'>--- Select ---</option>")
			$.each(object,function(index, element){
				$("#select-subtaskTypeId").append("<option value='"+element.subtaskTypeId+"' label='"+element.description+"'>"+element.description+"</option>")
			});
		},
		error: function(){
			alert("Error loading select");
		}
	});	
}

function initDatatableOnList(){
	subtaskTableInit=false;
	cTable=null;
	$.ajax('../ajax/getSubtaskList.do',{
		success: function(data) {
			refreshSubtaskTable(data)
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

function refreshSubtaskTable(data) {
	console.log("START refreshSubtaskTable");
	var object = JSON.parse(data);
	if (subtaskTableInit == false) {
		subtaskTableInit = true;
		cTable = $('#subtaskTable').DataTable({
			data : object,
			columns : [{
				data : 'subtaskId'
			},{
				data : 'subtaskType.subtaskTypeCategory.description',
			},{
				data : 'subtaskType.description'
			},{
				data : 'description'
			},{
				data : 'autoCalculation'
			},{
				data : 'referenceMode'
			},{
				data : 'time'
			},{
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 7,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var deleteLink = '<a href="javascript:deleteSubtaskSubmit('+ full.subtaskId + ')" title="Delete Subtask"><img border="0" alt="Delete" src="../images/cross.png" width="20" height="20"> </a>'; 
					var modifyLink = '<a href="javascript:updateSubtaskLoad('+ full.subtaskId + ')" title="Modify Subtask"><img border="0" alt="Modify" src="../images/page_edit.png" width="20" height="20"> </a>';
					return deleteLink + "&nbsp;" + modifyLink;
				}
			}],
			language: {emptyTable: 'No data found'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#SubtaskTable').show();
		console.log("INIT refreshSubtaskTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshSubtaskTable");
	}
	console.log("FINISH refreshSubtaskTable");
}

function createSubtaskSubmit(){
	$('#action').val('CREATE');
	$("form").submit();
}

function updateSubtaskLoad(subtaskId){
	$.ajax('../ajax/getSubtaskById.do?subtaskIdStr='+subtaskId,{
		success: function(data) {
			var object = JSON.parse(data)
			if (object.responseStatus != 'ERROR'){
				var subtask = object.subtask;
				
				$('#subtaskId').val(subtask.subtaskId);			
				$('#select-subtaskCategoryId').val(subtask.subtaskType.subtaskTypeCategory.subtaskTypeCategoryId);
				refreshSubtaskCategoryId();
				
				$('#select-subtaskTypeId').val(subtask.subtaskType.subtaskTypeId);
				refreshSubtaskTypeId()
									
				$('#description').val(subtask.description);		
				
				$('#autoCalculation').val(subtask.autoCalculation);		
				$("#chk-autoCalculation").prop("checked", $("#autoCalculation").val() == 'true' || $("#autoCalculation").val() == 'TRUE');
				
				$('#referenceMode').val(subtask.referenceMode);		
				$('#time').val(subtask.time);		
				$('#create-button').addClass("hidden");
				$('#update-button').removeClass("hidden");
			}else{
				alert(object.errorMessage);
			}
		},
		error: function(){
			alert("Error loading the values for the Subtask to be updated");
		}
	});
}

function updateSubtaskSubmit(){
	$('#action').val('UPDATE');
	$("form").submit();
}

function deleteSubtaskSubmit(subtaskId){
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
				$('#subtaskId').val(subtaskId);
				
				$('#action').val('DELETE');
				$("form").submit();
			}
    	}
	});
}

function cancelUpdateLoad(){
 	cleanFields();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
}

function cancelCreateLoad(){
	 cleanFields();
}

function cleanFields(){
	$('#subtaskId').val("");		
	$('#subtaskTypeId').val("");		
	$('#description').val("");		
	$('#autoCalculation').val("");		
	$('#referenceMode').val("");		
	$('#time').val("");		
	$('#subtaskType').val("");	
	$('#select-subtaskCategoryId').val(0);	
	refreshSubtaskCategoryId();
}

