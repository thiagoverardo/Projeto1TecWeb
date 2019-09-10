package meuPacote;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/edita")
public class Edita extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		DAO dao;
		try {
			
			dao = new DAO();
			Jogos jogos = new Jogos();

			String user = request.getParameter("user");
			request.setAttribute("user", user);
			
			String user1 = request.getParameter("user1");
			request.setAttribute("user1", user1);
	
			String jogo = request.getParameter("jogo");
			request.setAttribute("jogo", jogo);
	
			String genero = request.getParameter("genero");
			request.setAttribute("genero", genero);
			
			String preco = request.getParameter("preco");
			request.setAttribute("preco", preco);
			
			String date = request.getParameter("compra");
			request.setAttribute("data", date);
			
			String id = request.getParameter("id");
			request.setAttribute("id", id);
			
			jogos.setId(Integer.valueOf(request.getParameter("id")));
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		DAO dao;
		try {
			dao = new DAO();
			Jogos pessoa = new Jogos();
			
			pessoa.setId(Integer.valueOf(request.getParameter("id")));
			
			pessoa.setUser(request.getParameter("user"));
			
			pessoa.setJogo(request.getParameter("jogo"));
			
			pessoa.setGenero(request.getParameter("genero"));
			
			pessoa.setPreco(Float.valueOf(request.getParameter("preco")));
			
			String date = request.getParameter("compra");
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar dataCompra = Calendar.getInstance();
			dataCompra.setTime(data);
			pessoa.setData(dataCompra);
			
			dao.atualizaJogo(pessoa);
			
			String user = request.getParameter("user");
			request.setAttribute("user", user);
			
			RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
			rd.forward(request, response);
			dao.close();

		} catch (ClassNotFoundException | SQLException | ParseException e) {
			// TODO Auto-generated catch block
			RequestDispatcher rd = request.getRequestDispatcher("teste_2.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

}
