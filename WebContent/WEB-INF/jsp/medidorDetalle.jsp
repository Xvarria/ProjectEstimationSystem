<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="medidorList"/></title>
<%@ include file="./includes/basic.jsp"%>
<script type="text/javascript" src="../js/medidorDetalle.js"></script>
</head>
<body>
	<form id="medidorDetalle" name="medidorDetalle">
		<%@ include file="./includes/standardHiddenFields.jsp"%>
		<div class="hidden">
			<input id="medidorId" name="medidor.medidorId" value="${command.medidor.medidorId}"/>
		</div>
		<div class="container">
			<div class="row upperSpace">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="medidorDetalle"/>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-2 offset-md-1">
					<fmt:message key="medidor.medidorId"/>	
				</div>
				<div class="col-md-5">
  					<c:out value = "${command.medidor.medidorId}"/>					
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 offset-md-1">
					<fmt:message key="medidor.fechaInclusion"/>	
				</div>
				<div class="col-md-5">
					<c:out value = "${command.medidor.fechaInclusionFmt}"/>	
				</div>
			</div>
			<div class="row">				
				<div class="col-md-2 offset-md-1">
					<fmt:message key="medidor.fechaUltimaLectura"/>
				</div>
				<div class="col-md-5">
					<c:out value = "${command.medidor.fechaUltimaLecturaFmt}"/>
				</div>
			</div>
			<div class="row">				
				<div class="col-md-2 offset-md-1">
					<fmt:message key="medidor.ultimaLectura"/>
				</div>
				<div class="col-md-5">
					<c:out value = "${command.medidor.ultimaLectura}"/>
				</div>
			</div>
		</div>			
		<br>
		<div class="container">	
			<div class="row">
				<div class="col-md-10 offset-md-1">
					<fmt:message key="detalleLectura"/><br>
					<input type="button" class="btn-primary" value="Mostrar/Ocultar" onclick="javascript:mostarOcultarDetalle()"/>
					<input id="estadoDetalle" value="visible" type="hidden" />
				</div>
			</div>					
			<div class="row" name="detalleLectura">
				<div class="col-md-10 offset-md-1">
					<table id="LecturaTable">
						<thead>
							<tr>
								<th><fmt:message key="lectura.lecturaId"/></th>
								<th><fmt:message key="lectura.temperatura"/></th>
								<th><fmt:message key="lectura.fechaLectura"/></th>
								<th><fmt:message key="lectura.volumen"/></th>
								<th><fmt:message key="lectura.error"/></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row upperSpace" name="detalleLectura"temp>
				<div class="col-md-10 offset-md-1">
					<input type="button" class="btn-primary" value="Refrescar" onclick="javascript:refrescarDatos()">
				</div>
			</div>			
		</div>
	</form>
</body>
<script type="text/javascript">
	$(document).ready(function(){
   		initDatatableOnList();
	});
</script>
</html>
