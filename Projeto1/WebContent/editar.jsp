<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editar Jogo</title>
</head>
<body>
	 <jsp:useBean id="dao" class="meuPacote.DAO"/>
	 	 
	 <%!String user;
	 	String user1;
		String data;
		String jogo;
		String genero;
		Float preco;
		String id;%>

	<%user = (String) request.getAttribute("user");
	user1 = (String) request.getAttribute("user1");
	data = (String) request.getAttribute("data");
	jogo = (String) request.getAttribute("jogo");
	genero = (String) request.getAttribute("genero");
    preco = Float.valueOf(request.getParameter("preco"));
    id = (String) request.getAttribute("id");%>
    
	  <br/>
	  	Edite o Jogo da sua biblioteca:
	  <br><br/>
	  <form action='teste_2.jsp'> 	
	 	Bem vindo, <%=user%>.
	 	<br><br/>
	 	Editar o jogo: <%=id%>/<%=user1%>/<%=jogo%>/<%=genero%>/<%=preco%>/<%=data%>
	  </form>
	  <br><br/>
	  <form action='edita' method='post' autocomplete="off">
	  	<input type="hidden" name="user" value="<%=user%>" readonly>
	    <input type="hidden" name="id" value="<%=id%>" readonly>
	  	Nome do Jogo: <input type='text' name='jogo' value="<%=jogo%>" required='required'><br/>
	  	Gênero do Jogo: <input type='text' name='genero' value="<%=genero%>" required='required'><br/>
	 	Preço do Jogo: <input type='text' name='preco' value="<%=preco%>" required='required'><br/>
	  	Data da Compra: <input type='date' name='compra' value="<%=data%>" required='required'><br/>
	  
	  <input type='submit' value='Editar'>
	  </form>
	<br/>
</body>
</html>