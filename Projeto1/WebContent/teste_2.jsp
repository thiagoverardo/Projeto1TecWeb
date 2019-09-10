<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Biblioteca de Jogos</title>
</head>
<body>
	 <jsp:useBean id="dao" class="meuPacote.DAO"/>
	 
	 <% String user = (String) request.getAttribute("user"); %>
	 <form action='login.jsp'>
	 	Bem vindo, <%=user%>.
		<input type='submit' value='Sair'>
	 </form>
	  <br/>
	  	Adicione um Jogo à sua biblioteca:
	  <br><br/>
	  <form method='post' action='biblioteca'>
	  	<input type="hidden" name="user" value="<%=user%>" readonly>
	  	Nome do Jogo: <input type='text' name='jogo' required='required'><br/>
	  	Gênero do Jogo: <input type='text' name='genero' required='required'><br/>
	 	Preço do Jogo: <input type='text' name='preco' required='required'><br/>
	  	Data da Compra: <input type='date' name='compra' required='required'><br/>
	  
	  <input type='submit' value='Adicionar'>
	  </form>
	<br/>
	<table border="1">
	 <tr>
	 <td><b>Id</b></td>
	 <td><b>User</b></td>
	 <td><b>Jogo</b></td>
	 <td><b>Gênero</b></td>
	 <td><b>Preço</b></td>
	 <td><b>Data da Compra</b></td>
	 </tr>
	 <c:forEach var="pessoa" items="<%=dao.getJogos()%>" varStatus="id">
	 	<tr bgcolor="#${id.count%2 == 0 ? 'bbffcc' : 'ffffbb' }" >
	 	<td>${pessoa.id}</td>
	 	<td>${pessoa.user}</td>
	 	<td>${pessoa.jogo}</td>
	 	<td>${pessoa.genero}</td>
	 	<td>${pessoa.preco}</td>
	 	<td><fmt:formatDate value="${pessoa.data.time}" pattern="dd/MM/yyyy"/></td>
	 	<td><form action='edita' method= 'get'>
	 			<input type="hidden" name="id" value= "${pessoa.id}" readonly>
	 			<input type="hidden" name="user" value="<%=user%>" readonly>
	 			<input type="hidden" name="user1" value="${pessoa.user}" readonly>
	 			<input type="hidden" name="jogo" value= "${pessoa.jogo}" readonly>
	 			<input type="hidden" name="genero" value="${pessoa.genero}" readonly>
	 			<input type="hidden" name="preco" value="${pessoa.preco}" readonly>
	 			<input type="hidden" name="compra" value=<fmt:formatDate value="${pessoa.data.time}" pattern="yyyy-MM-dd"/> readonly>
				<input type='submit' value='Editar'>
		</form></td>
	 	<td><form method='post' action='remove'>
	 			<input type="hidden" name="id" value= "${pessoa.id}" readonly>
	 			<input type="hidden" name="user" value="<%=user%>" readonly>
				<input type='submit' value='Excluir'>
		</form></td>
	 	</tr>
	 </c:forEach>
	</table>
	<br/>
</body>
</html>