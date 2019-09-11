package meuPacote;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DAO dao;		
		try {
			dao = new DAO();
			Cadastro cadastro = new Cadastro();
			cadastro.setUser(request.getParameter("user"));
			cadastro.setPassword(request.getParameter("password"));

			if (dao.verifica(cadastro)) {
				String user = request.getParameter("user");
				request.setAttribute("user", user);
				request.setAttribute("ordem", "");
				RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("falha.jsp");
				rd.forward(request, response);
			}
			
			dao.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}

}
