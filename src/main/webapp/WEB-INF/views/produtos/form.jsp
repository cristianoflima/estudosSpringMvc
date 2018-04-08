<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Formulario de Cadastro</title>
</head>
<body>

	<form:form action="/casadocodigo/produtos" method="POST" commandName="produto" enctype="multipart/form-data">
		<div>
			<label>Titulo</label>
			<form:input path="titulo"/>
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea rows="10" cols="20" path="descricao"></form:textarea>
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Paginas</label>
			<form:input path="paginas"/>
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de Lançamento</label>
			<form:input path="dataLancamento"/>
			<form:errors path="dataLancamento" />
		</div>
		
		
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor"/>
				<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}"/>
			</div>
		</c:forEach>
		
		<div>
		    <label>Sumário</label>
		    <input name="sumario" type="file" />
		</div>
		
		<button type="submit">Cadastrar</button>
	</form:form>


</body>
</html>