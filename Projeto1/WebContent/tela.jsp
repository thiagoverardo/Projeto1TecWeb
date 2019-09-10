<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:import url="tela.jsp" />

<body>
<%@ page import="java.util.*,meuPacote.*" %>
<table border='1'>
<% DAO dao = new DAO();
	List<Jogos> pessoas = dao.getJogos();
	for (Jogos pessoa : pessoas ) { %>
 	 <tr>
 	 <td><%=pessoa.getId()%></td>
 	 <td><%=pessoa.getUser()%></td>
 	 <td><%=pessoa.getGenero()%></td>
 	 <td><%=pessoa.getJogo()%></td>
 	 <td><%=pessoa.getPreco()%></td>
 	 <td><%=pessoa.getData()%></td>
 	</tr>
<% } %>
</table>
</body>