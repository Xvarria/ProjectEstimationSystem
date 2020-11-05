$(document).ready(function(){
	initDatatableOnList();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
});

function initDatatableOnList(){
	taskTableInit=false;
	cTable=null;
	var projectId = $("#projectId").val();
	$.ajax('../ajax/getTaskList.do?projectIdStr='+projectId,{
		success: function(data) {
			refreshTaskTable(data)
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

function refreshTaskTable(data) {
	console.log("START refreshTaskTable");
	var object = JSON.parse(data);
	if (taskTableInit == false) {
		taskTableInit = true;
		cTable = $('#taskTable').DataTable({
			data : object,
			columns : [
			{
				data : 'taskId'
			},			
			{
				data : null,
				visible: false
			},			
			{
				data : 'code'
			},			
			{
				data : 'sequence'
			},			
			{
				data : 'name'
			},					
			{
				data : null
			},			
			{
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 1,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					return full.project.projectNumber +" - "+full.project.name
				}
			},{
				targets : 5,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var viewLink = '<a href="./listSubtask.do?taskId='+full.taskId+'" title="View Subtask List"><img border="0" alt="View" src="../images/magnifier.png" width="20" height="20"> </a>'; 
					return viewLink;
				}
			}, {
				targets : 6,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var deleteLink = '<a href="javascript:deleteTaskSubmit('+ full.taskId + ')" title="Delete Task"><img border="0" alt="Delete" src="../images/cross.png" width="20" height="20"> </a>'; 
					var modifyLink = '<a href="javascript:updateTaskLoad('+ full.taskId + ')" title="Modify Task"><img border="0" alt="Modify" src="../images/page_edit.png" width="20" height="20"> </a>';
					return deleteLink + "&nbsp;" + modifyLink;
				}
			}],
			language: {emptyTable: 'No data found'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#TaskTable').show();
		console.log("INIT refreshTaskTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshTaskTable");
	}
	console.log("FINISH refreshTaskTable");
}

function createTaskSubmit(){
	$('#action').val('CREATE');
	$("form").submit();
}

function updateTaskLoad(taskId){
	$.ajax('../ajax/getTaskById.do?taskIdStr='+taskId,{
		success: function(data) {
			var object = JSON.parse(data)
			if (object.responseStatus != 'ERROR'){
				var task = object.task;
				$('#taskId').val(task.taskId);		
				$('#projectId').val(task.projectId);		
				$('#code').val(task.code);		
				$('#sequence').val(task.sequence);		
				$('#name').val(task.name);		
				$('#project').val(task.project);		
				$('#subtaskList').val(task.subtaskList);		
				$('#create-button').addClass("hidden");
				$('#update-button').removeClass("hidden");
			}else{
				alert(object.errorMessage);
			}
		},
		error: function(){
			alert("Error loading the values for the Task to be updated");
		}
	});
}

function updateTaskSubmit(){
	$('#action').val('UPDATE');
	$("form").submit();
}

function deleteTaskSubmit(taskId){
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
				$('#taskId').val(taskId);
				
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
	$('#taskId').val("");		
	$('#projectId').val("");		
	$('#code').val("");		
	$('#sequence').val("0");		
	$('#name').val("");		
	$('#project').val("");		
	$('#subtaskList').val("");		
	}

