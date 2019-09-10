package meuPacote;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/remove")
public class Remove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		DAO dao;
		try {
			dao = new DAO();

			if (request.getParameter("id") != null) {
				dao.removeJogo(Integer.valueOf(request.getParameter("id")));
				String user = request.getParameter("user");
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
				rd.forward(request, response);
	
				dao.close();
			} else {
				dao.removeJogo(0);
				String user = request.getParameter("user");
				request.setAttribute("user", user);
				RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
				rd.forward(request, response);
			
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}