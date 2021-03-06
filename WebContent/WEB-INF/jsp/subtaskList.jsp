<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="complexityList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/subtaskList.js"></script>
</head>
<body>
	<form id="subtaskForm" name="subtaskForm" action="./listSubtask.do" method="POST">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<%@ include file="./includes/standarHiddenFieldsForm.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<a href="./listTask.do?projectId=${command.task.projectId}">Back</a> &nbsp; <label>Task:&nbsp;<c:out value="${command.task.taskId}"/> - <c:out value="${command.task.name}"/></label>
					
					<input id="taskId" name="taskId" value="${command.taskId}" type="hidden"/> 
					<input id="task-taskId" name="task.taskId" value="${command.task.taskId}" type="hidden"/>
					<input id="task-name" name="task.name" value="${command.task.name}" type="hidden"/>
					<input id="task-project-projectId" name="task.project.projectId" value="${command.task.project.projectId}" type="hidden"/>
					<br>
					<br>
				</div>
			</div>
		</div>		
		
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="subtaskList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="subtaskTable">
						<thead>
							<tr>
								<th><fmt:message key="subtask.subtaskId"/></th>
								<th><fmt:message key="subtask.task"/></th>
								<th><fmt:message key="subtask.subtaskTypeCategory"/></th>
								<th><fmt:message key="subtask.subtaskType"/></th>
								<th><fmt:message key="subtask.description"/></th>
								<th><fmt:message key="subtask.autoCalculation"/></th>
								<th><fmt:message key="subtask.complexity"/></th>
								<th><fmt:message key="subtask.referenceMode"/></th>
								<th><fmt:message key="subtask.time"/></th>
								<th><fmt:message key="subtask.ctime"/></th>
								<th><fmt:message key="subtask.method"/></th>
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
					<div class="col-md-6 offset-md-1">
						<div class="row">
							<div class="col-md-12">
								Id:&nbsp;<input id="subtaskId" name="subtaskId" readonly
									value="${command.subtaskId}" /> <br> <br>
							</div>
							<div class="col-md-12">
								Category:&nbsp;<select id="select-subtaskCategoryId"
									name="select-subtaskCategoryId"
									onchange="javascript:refreshSubtaskCategoryId()">
									<c:forEach var="category" items="${subtaskypeCategoryList}">
										<option value="${category.subtaskTypeCategoryId}"
											label="${category.description}">${category.description}</option>
									</c:forEach>
								</select> &nbsp; Type:&nbsp;<select id="select-subtaskTypeId"
									name="select-subtaskTypeId"
									onchange="javascript:refreshSubtaskTypeId()">
									<c:forEach var="type" items="${subtaskTypeList}">
										<c:set var="selected" value="" />
										<c:if
											test="${type.subtaskTypeId eq type.subtaskTypeCategoryId}">
											<c:set var="selected" value="selected" />
										</c:if>
										<option value="${type.subtaskTypeId}"
											label="${type.description}" ${selected}>${type.description}</option>
									</c:forEach>
								</select> &nbsp;<input id="subtaskTypeId" name="subtaskTypeId"
									value="${command.subtaskTypeId}" type="hidden" /> <br> <br>
							</div>
							<div class="col-md-12">
								description:&nbsp;<input id="description" name="description"
									value="${command.description}" /> <br> <br>
							</div>
							<div class="col-md-12">
								<c:set var="checeked" value="" />
								<c:if
									test="${command.description eq 'TRUE' || command.description eq 'true'}">
									<c:set var="checeked" value="checked" />
								</c:if>
								Auto Calculation:&nbsp; <input id="chk-autoCalculation"
									name="chk-autoCalculation" type="checkbox" ${checeked}
									onChange="javaScript:refreshAutoCalculation()" /> <input
									id="autoCalculation" name="autoCalculation"
									value="${command.description}" type="hidden" /> <br> <br>
							</div>
							<div class="col-md-12">
								Complexity:&nbsp;<select id="select-complexityId"
									name="select-complexityId"
									onchange="javascript:refreshComplexityId()">
									<c:forEach var="complexity" items="${subtaskComplexityList}">
										<option value="${complexity.complexityId}"
											label="${complexity.description}">${complexity.description}</option>
									</c:forEach></select>
									&nbsp;
									<input id="complexityId" name="complexityId" value="${command.complexityId}" type="hidden" /> <br> <br>
							</div>							
							<div class="col-md-12">
								referenceMode:<br>
								<textArea id="textarea-refenceModel" name="textarea-refenceModel" style="margin-top: 0px;margin-bottom: 0px;height: 116px;width: inherit;" onkeyup="javascript:updateReferenceModel()" onblur="javascript:updateReferenceModel()">${command.referenceMode}</textArea>
								<input id="referenceMode" name="referenceMode" value="${command.referenceMode}" type="hidden"/> <br>
								<br>
							</div>
							<div class="col-md-12">
								time:&nbsp;<input id="time" name="time" value="${command.time}" />
								<br> <br>
							</div>
						</div>
					</div>
					<div  class="col-md-4">
						<div class="row">
							Calculation:<br>
						</div>
						<div class="row">
							<div class="col-md-12">
								<textArea id="calculation-description" style="margin-top: 0px;margin-bottom: 0px;height: 116px;width: inherit;" readonly></textArea>
							</div>						
						</div>						
					</div>
				</div>
				<div class="row">
					<div id="create-button" class="col-md-10 offset-md-1">
						<button class="sucess" type="button"
							onclick="javascript:createSubtaskSubmit()">Create</button>
						<button class="warning" type="button"
							onclick="javascript:cancelCreateLoad()">Cancel</button>
					</div>
					<div id="update-button" class="col-md-10 offset-md-1 hidden">
						<button class="sucess" type="button"
							onclick="javascript:updateSubtaskSubmit()">Update</button>
						<button class="warning" type="button"
							onclick="javascript:cancelUpdateLoad()">Cancel</button>
					</div>
				</div>
			</div>
		</fieldset>
	</form>		
</body>
</html>

