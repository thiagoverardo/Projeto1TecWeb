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

@WebServlet("/biblioteca")
public class Biblioteca extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		DAO dao;

		try {
			dao = new DAO();
			Jogos pessoa = new Jogos();
			
			pessoa.setUser(request.getParameter("user"));
			
			pessoa.setJogo(request.getParameter("jogo"));
			
			pessoa.setGenero(request.getParameter("genero"));
			
			pessoa.setPreco(Float.valueOf(request.getParameter("preco")));
			
			String date = request.getParameter("compra");
			Date data = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			Calendar dataCompra = Calendar.getInstance();
			dataCompra.setTime(data);
			pessoa.setData(dataCompra);
			
			dao.adicionaJogo(pessoa);
			
			String user = request.getParameter("user");
			request.setAttribute("user", user);
			
			request.setAttribute("ordem", "");
			
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
