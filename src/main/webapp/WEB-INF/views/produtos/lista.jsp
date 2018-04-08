<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lista de Livros</title>
</head>
<body>

	<div>${sucesso}</div>
	<div>${falhou}</div>
	<br/>

	<h3>Lista de Prodtuos</h3>
	<br/>

	<table>
		<tr>
			<td>Titulo</td>
			<td>Descrição</td>	
			<td>Páginas</td>
			<td>Data Lançamento</td>			
		</tr>
		
		<c:forEach items="${produtos}" var="produto">
			<tr>
				<td><a href="${s:mvcUrl('PC#detalhe').arg(0,produto.id).build()}"> ${produto.titulo} </a></td>
				<td>${produto.descricao}</td>
				<td>${produto.paginas}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataLancamento.time}"></fmt:formatDate></td>
			</tr>
		</c:forEach>	
	</table>

</body>


</html>