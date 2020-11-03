<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="complexityList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/projectList.js"></script>
</head>
<body>
	<form id="projectForm" name="projectForm" action="./listProject.do" method="POST">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<%@ include file="./includes/standarHiddenFieldsForm.jsp"%>
		
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="projectList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="projectTable">
						<thead>
							<tr>
								<th><fmt:message key="project.projectId"/></th>
								<th><fmt:message key="project.projectNumber"/></th>
								<th><fmt:message key="project.name"/></th>
								<th><fmt:message key="project.taskList"/></th>
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
						projectId:&nbsp;<input id="projectId" name="projectId"
							value="${command.projectId}" readonly/> <br>
						<br>
					</div>
					<div class="col-md-10 offset-md-1">
						projectNumber:&nbsp;<input id="projectNumber" name="projectNumber"
							value="${command.projectNumber}" /> <br>
						<br>
					</div>
					<div class="col-md-10 offset-md-1">
						name:&nbsp;<input id="name" name="name" value="${command.name}" />
						<br>
						<br>
					</div>
				</div>
				<div id="create-button" class="col-md-10 offset-md-1">
					<button class="sucess" type="button"
						onclick="javascript:createProjectSubmit()">Create</button>
					<button class="warning" type="button"
						onclick="javascript:cancelCreateLoad()">Cancel</button>
				</div>
				<div id="update-button" class="col-md-10 offset-md-1 hidden">
					<button class="sucess" type="button"
						onclick="javascript:updateProjectSubmit()">Update</button>
					<button class="warning" type="button"
						onclick="javascript:cancelUpdateLoad()">Cancel</button>
				</div>

			</div>

		</fieldset>
	</form>		
</body>
</html>

