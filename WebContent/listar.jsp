<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<table border="2">
	<tr>
		<th>Nome</th>
		<th>Descrição</th>
	</tr>
	<c:forEach var="produtos" items="${listaDeProdutos}">
		<tr>
			<td>${produtos.getName()}</td>
			<td>${produtos.getDescription()}</td>
		</tr>
	</c:forEach>
</table>
<br>