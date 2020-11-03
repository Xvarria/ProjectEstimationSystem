$(document).ready(function(){
	initDatatableOnList();
	$('#create-button').removeClass("hidden");
	$('#update-button').addClass("hidden");
});

function initDatatableOnList(){
	projectTableInit=false;
	cTable=null;
	$.ajax('../ajax/getProjectList.do',{
		success: function(data) {
			refreshProjectTable(data)
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

function refreshProjectTable(data) {
	console.log("START refreshProjectTable");
	var object = JSON.parse(data);
	if (projectTableInit == false) {
		projectTableInit = true;
		cTable = $('#projectTable').DataTable({
			data : object,
			columns : [{
				data : 'projectId'
			},{
				data : 'projectNumber'
			},{
				data : 'name'
			},{
				data : null,
				orderable : false
			},{
				data : null,
				orderable : false
			}],
			columnDefs : [ {
				targets : 3,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var viewLink = '<a href="./listTask.do?projectId='+full.projectId+'" title="View Task Project"><img border="0" alt="View" src="../images/magnifier.png" width="20" height="20"> </a>'; 
					return viewLink;
				}
			},{
				targets : 4,
				className : 'center',
				orderable : 'false',
				render : function(data, type, full, meta) {
					var deleteLink = '<a href="javascript:deleteProjectSubmit('+ full.projectId + ')" title="Delete Project"><img border="0" alt="Delete" src="../images/cross.png" width="20" height="20"> </a>'; 
					var modifyLink = '<a href="javascript:updateProjectLoad('+ full.projectId + ')" title="Modify Project"><img border="0" alt="Modify" src="../images/page_edit.png" width="20" height="20"> </a>';
					return deleteLink + "&nbsp;" + modifyLink;
				}				
			}],
			language: {emptyTable: 'No data found'},
			order : [ [ 0, 'asc' ] ],
			lengthMenu : [ [ 25, 50,  100, -1 ], [ 25, 50, 100, "All" ] ],
			iDisplayLength : 25
		});
		$('#ProjectTable').show();
		console.log("INIT refreshProjectTable");
	} else {
		cTable.clear().draw();
		for (var i = 0; i < object.length; ++i) {
			cTable.row.add(object[i]).draw();
		}
		console.log("REFRESH refreshProjectTable");
	}
	console.log("FINISH refreshProjectTable");
}

function createProjectSubmit(){
	$('#action').val('CREATE');
	$("form").submit();
}

function updateProjectLoad(projectId){
	$.ajax('../ajax/getProjectById.do?projectIdStr='+projectId,{
		success: function(data) {
			var object = JSON.parse(data)
			if (object.responseStatus != 'ERROR'){
				var project = object.project;
				$('#projectId').val(project.projectId);		
				$('#projectNumber').val(project.projectNumber);		
				$('#name').val(project.name);		
				$('#taskList').val(project.taskList);		
								$('#create-button').addClass("hidden");
				$('#update-button').removeClass("hidden");
			}else{
				alert(object.errorMessage);
			}
		},
		error: function(){
			alert("Error loading the values for the Project to be updated");
		}
	});
}

function updateProjectSubmit(){
	$('#action').val('UPDATE');
	$("form").submit();
}

function deleteProjectSubmit(projectId){
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
				$('#projectId').val(projectId);
				
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
	$('#projectId').val("");		
	$('#projectNumber').val("");		
	$('#name').val("");		
	$('#taskList').val("");		
	}

