<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="complexityList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/complexityList.js"></script>
</head>
<body>
	<form id="complexityForm" name="complexityForm" action="./listComplexity.do" method="POST">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<%@ include file="./includes/standarHiddenFieldsForm.jsp"%>
		
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="complexityList"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<table id="complexityTable">
						<thead>
							<tr>
								<th><fmt:message key="complexity.complexityId"/></th>
								<th><fmt:message key="complexity.description"/></th>
								<th><fmt:message key="complexity.baseHour"/></th>
								<th><fmt:message key="complexity.multiplexor"/></th>
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
					Id:&nbsp;<input id="complexityId" name="complexityId" value="" readonly/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Description:&nbsp; <input id="description" name="description" value="${command.description}"/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Base Hour:&nbsp; <input id="baseHour" name="baseHour" value="${command.baseHour}"/>
					<br><br>
					</div>
					<div class="col-md-10 offset-md-1">
					Multiplexor:&nbsp; <input id="multiplexor" name="multiplexor" value="${command.multiplexor}"/>
					<br><br>
					</div>
					<div id="create-button" class="col-md-10 offset-md-1">
						<button class="sucess" type="button" onclick="javascript:createComplexitySubmit()">Create</button>
						<button class="warning" type="button" onclick="javascript:cancelCreateLoad()">Cancel</button>					
					</div>
					<div id="update-button" class="col-md-10 offset-md-1 hidden">
						<button class="sucess" type="button" onclick="javascript:updateComplexitySubmit()">Update</button>
						<button class="warning" type="button" onclick="javascript:cancelUpdateLoad()">Cancel</button>
					</div>			
				
				</div>
			</div>
		</fieldset>
	</form>		
</body>
</html>
