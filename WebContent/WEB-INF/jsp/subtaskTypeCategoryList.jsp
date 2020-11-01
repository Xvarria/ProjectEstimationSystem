<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="subtaskTypeCategoryList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/subtaskTypeCategoryList.js"></script>
</head>
<body>
	<form id="subtaskTypeCategoryForm" name="subtaskTypeCategoryForm" action="./listSubtaskTypeCategory.do" method="POST">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<%@ include file="./includes/standarHiddenFieldsForm.jsp"%>
		
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="subtaskTypeCategoryList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="subtaskTypeCategoryTable">
						<thead>
							<tr>
								<th><fmt:message key="subtaskTypeCategory.subtaskTypeCategoryId"/></th>
								<th><fmt:message key="subtaskTypeCategory.description"/></th>
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
					Id:&nbsp;<input id="subtaskTypeCategoryId" name="subtaskTypeCategoryId" value="" readonly/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Description:&nbsp; <input id="description" name="description" value="${command.description}"/>
					<br><br>
					</div>
					<div id="create-button" class="col-md-10 offset-md-1">
						<button class="sucess" type="button" onclick="javascript:createSubtaskTypeCategorySubmit()">Create</button>
						<button class="warning" type="button" onclick="javascript:cancelCreateLoad()">Cancel</button>					
					</div>
					<div id="update-button" class="col-md-10 offset-md-1 hidden">
						<button class="sucess" type="button" onclick="javascript:updateSubtaskTypeCategorySubmit()">Update</button>
						<button class="warning" type="button" onclick="javascript:cancelUpdateLoad()">Cancel</button>
					</div>			
				</div>
			</div>
		</fieldset>
	</form>		
</body>
</html>
