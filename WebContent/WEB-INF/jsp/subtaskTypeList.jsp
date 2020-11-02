<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="complexityList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/subtaskTypeList.js"></script>
</head>
<body>
	<form id="subtaskTypeForm" name="subtaskTypeForm" action="./listSubtaskType.do" method="POST">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<%@ include file="./includes/standarHiddenFieldsForm.jsp"%>
		
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="subtaskTypeList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="subtaskTypeTable">
						<thead>
							<tr>
								<th><fmt:message key="subtaskType.subtaskTypeId"/></th>
								<th><fmt:message key="subtaskType.category"/></th>
								<th><fmt:message key="subtaskType.description"/></th>
								<th><fmt:message key="subtaskType.calculation"/></th>
								<th><fmt:message key="subtaskType.reference"/></th>
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
					Id:&nbsp;<input id="subtaskTypeId" name="subtaskTypeId" value="" readonly/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Category:&nbsp;<select id="select-subtaskCategoryId" name="select-subtaskCategoryId" onchange="javascript:refreshSubtaskCategoryId()">
						<c:forEach var="category" items="${subtaskypeCategoryList}">
							<c:set var="selected" value=""/>
							<c:if test="${category.subtaskTypeCategoryId eq command.subtaskCategoryTypeId}">
								<c:set var="selected" value="selected"/>
							</c:if>
							<option value="${category.subtaskTypeCategoryId}" label="${category.description}" ${selected}>${category.description}</option>
						</c:forEach>
					</select> 
					<input id="subtaskCategoryTypeId" name="subtaskCategoryTypeId" value="${command.subtaskCategoryTypeId}"/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Description:&nbsp; <input id="description" name="description" value="${command.description}"/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Calculation:&nbsp; <input id="calculation" name="calculation" value="${command.calculation}"/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Reference:&nbsp; <input id="reference" name="reference" value="${command.reference}"/>
					<br><br>
					</div>					
					<div id="create-button" class="col-md-10 offset-md-1">
						<button class="sucess" type="button" onclick="javascript:createSubtaskTypeSubmit()">Create</button>
						<button class="warning" type="button" onclick="javascript:cancelCreateLoad()">Cancel</button>					
					</div>
					<div id="update-button" class="col-md-10 offset-md-1 hidden">
						<button class="sucess" type="button" onclick="javascript:updateSubtaskTypeSubmit()">Update</button>
						<button class="warning" type="button" onclick="javascript:cancelUpdateLoad()">Cancel</button>
					</div>			
				
				</div>
			</div>
		</fieldset>
	</form>		
</body>
</html>

