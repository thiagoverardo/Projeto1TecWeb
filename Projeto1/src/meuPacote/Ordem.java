package meuPacote;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/ordem")
public class Ordem extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		DAO dao;
		try {
			dao = new DAO();
			String ordem;

			if (request.getParameter("variable").contentEquals("User")) {
				ordem = "user";
			} else if (request.getParameter("variable").contentEquals("Data")) {
				ordem = "date";
			} else if (request.getParameter("variable").contentEquals("Jogo")){
				ordem = "jogo";
			} else if (request.getParameter("variable").contentEquals("Genero")){
				ordem = "genero";
			} else if (request.getParameter("variable").contentEquals("Preco")){
				ordem = "preco";
			} else {
				ordem = "";
			}

			String resultado = request.getParameter("user");
			request.setAttribute("user", resultado);
			request.setAttribute("ordem", ordem);
			RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
			rd.forward(request, response);

			dao.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}