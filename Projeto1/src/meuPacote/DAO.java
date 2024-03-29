package meuPacote;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DAO {
	private Connection connection = null;

	public DAO() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection("jdbc:mysql://localhost/teste", "root", "Padrinhos123");
	}

	public boolean verifica(Cadastro pessoa) throws SQLException {
		boolean st = false;
		String sql = "SELECT * FROM cadastro WHERE user=? and password=?" ;
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());
		ResultSet rs = stmt.executeQuery();
		st = rs.next();
		stmt.execute();
		stmt.close();
		return st;
	}

	public void adiciona(Cadastro pessoa) throws SQLException {
		String sql = "INSERT INTO cadastro" + "(user, password) values (?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getPassword());
		stmt.execute();
		stmt.close();
	}

	public void close() throws SQLException {
		connection.close();
	}
	
	public List<Cadastro> getLista() throws SQLException {
		
		List<Cadastro> pessoas = new ArrayList<Cadastro>();

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM cadastro");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Cadastro pessoa = new Cadastro();

			pessoa.setId(rs.getInt("id"));
			pessoa.setUser(rs.getString("user"));
			pessoa.setPassword(rs.getString("password"));
			pessoas.add(pessoa);
		}

		rs.close();
		stmt.close();
		return pessoas;
	}
	
	public List<Jogos> getJogos() throws SQLException {
		
		List<Jogos> jogos = new ArrayList<Jogos>();

		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM jogos");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {

			Jogos jogo = new Jogos();

			jogo.setId(rs.getInt("id"));
			jogo.setUser(rs.getString("user"));
			jogo.setJogo(rs.getString("jogo"));
			jogo.setGenero(rs.getString("genero"));
			jogo.setPreco(rs.getFloat("preco"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("compra"));
			jogo.setData(data);
			jogos.add(jogo);
		}

		rs.close();
		stmt.close();
		return jogos;
	}

	public void adicionaJogo(Jogos pessoa) throws SQLException {
		String sql = "INSERT INTO jogos " + "(user, jogo, genero, preco, compra) values (?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, pessoa.getUser());
		stmt.setString(2, pessoa.getJogo());
		stmt.setString(3, pessoa.getGenero());
		stmt.setFloat(4, pessoa.getPreco());
		stmt.setDate(5, new java.sql.Date(pessoa.getData().getTimeInMillis()));
		stmt.execute();
		stmt.close();
	}
	
	public void removeJogo(Integer id) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM jogos WHERE id=?");
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
		
		if (id == 0) {
			PreparedStatement reset = connection.prepareStatement("ALTER TABLE jogos AUTO_INCREMENT = 1");
			reset.execute();
			reset.close();
		}
	}
	public void atualizaJogo(Jogos jogo) throws SQLException {
		String sql = "UPDATE jogos SET user=?, jogo=?, genero=?, preco=?, compra=? WHERE id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, jogo.getUser());
		stmt.setString(2, jogo.getJogo());
		stmt.setString(3, jogo.getGenero());
		stmt.setFloat(4, jogo.getPreco());
		stmt.setDate(5, new java.sql.Date(jogo.getData().getTimeInMillis()));
		stmt.setInt(6,  jogo.getId());
		stmt.execute();
		stmt.close();
	 }
	public List<Jogos> getOrdem(String obj) throws SQLException {
		List<Jogos> jogos = new ArrayList<Jogos>();
		PreparedStatement stmt;
		if (obj.contentEquals("user")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY user");
		} else if (obj.contentEquals("jogo")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY jogo");
		} else if (obj.contentEquals("genero")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY genero");
		} else if (obj.contentEquals("preco")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY preco");
		} else if (obj.contentEquals("date")) {
			stmt = connection.prepareStatement("SELECT * FROM jogos ORDER BY compra");
		} else {
			stmt = connection.prepareStatement("SELECT * FROM jogos");
		}
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {

			Jogos jogo = new Jogos();

			jogo.setId(rs.getInt("id"));
			jogo.setUser(rs.getString("user"));
			jogo.setJogo(rs.getString("jogo"));
			jogo.setGenero(rs.getString("genero"));
			jogo.setPreco(rs.getFloat("preco"));
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("compra"));
			jogo.setData(data);
			jogos.add(jogo);
		}
		rs.close();
		stmt.close();
		return jogos;
	}

}