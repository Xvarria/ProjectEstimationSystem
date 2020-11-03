<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="complexityList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/taskList.js"></script>
</head>
<body>
	<form id="taskForm" name="taskForm" action="./listTask.do" method="POST">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<%@ include file="./includes/standarHiddenFieldsForm.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<a href="./projectList.do">Back</a> &nbsp; <label>Project:&nbsp;<c:out value="${command.projectId}"/> - <c:out value="${command.project.name}"/></label>
					
					<input id="projectId" name="projectId" value="${command.projectId}" type="hidden"/> 
					<input id="project-projectId" name="project.projectId" value="${command.project.projectId}" type="hidden"/>
					<input id="project-projectId" name="project.name" value="${command.project.name}" type="hidden"/>
					<br>
					<br>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="taskList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="taskTable">
						<thead>
							<tr>
								<th><fmt:message key="task.taskId"/></th>
								<th><fmt:message key="task.project"/></th>
								<th><fmt:message key="task.code"/></th>
								<th><fmt:message key="task.sequence"/></th>
								<th><fmt:message key="task.name"/></th>
								<th><fmt:message key="task.subtaskList"/></th>
								<th><fmt:message key="header.detail"/></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<fieldset>
			<div class="container">
				<div class="row">
					<div class="col-md-10 offset-md-1">
						taskId:&nbsp;<input id="taskId" name="taskId"
							value="${command.taskId}" readonly /> <br>
						<br>
					</div>
					<div class="col-md-10 offset-md-1">
						code:&nbsp;<input id="code" name="code" value="${command.code}" />
						<br>
						<br>
					</div>
					<div class="col-md-10 offset-md-1">
						sequence:&nbsp;<input id="sequence" name="sequence"
							value="${command.sequence}" /> <br>
						<br>
					</div>
					<div class="col-md-10 offset-md-1">
						name:&nbsp;<input id="name" name="name" value="${command.name}" />
						<br>
						<br>
					</div>
					
					<div id="create-button" class="col-md-10 offset-md-1">
						<button class="sucess" type="button" onclick="javascript:createTaskSubmit()">Create</button>
						<button class="warning" type="button" onclick="javascript:cancelCreateLoad()">Cancel</button>					
					</div>
					<div id="update-button" class="col-md-10 offset-md-1 hidden">
						<button class="sucess" type="button" onclick="javascript:updateTaskSubmit()">Update</button>
						<button class="warning" type="button" onclick="javascript:cancelUpdateLoad()">Cancel</button>
					</div>			
				
				</div>
			</div>
		</fieldset>
	</form>		
</body>
</html>

