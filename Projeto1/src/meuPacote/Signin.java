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

@WebServlet("/signin")
public class Signin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DAO dao;

		try {
			dao = new DAO();
			Cadastro cadastro = new Cadastro();
			String password = request.getParameter("password");
			String check = request.getParameter("password_check");
			if (!password.isEmpty()) {
				if (password.contentEquals(check)) {
					cadastro.setUser(request.getParameter("user"));
					cadastro.setPassword(request.getParameter("password"));
	
					dao.adiciona(cadastro);
					String user = request.getParameter("user");
					request.setAttribute("user", user);
					request.setAttribute("ordem", "");
					RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
					rd.forward(request, response);
					
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("falha_c.jsp");
					rd.forward(request, response);
					//senhas incompat�veis;
				}
			} else {
				dao.close();
			}
			dao.close();

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
