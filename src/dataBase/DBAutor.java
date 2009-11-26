package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Pessoa;

public class DBAutor {
	private static DBAutor self = null;
	Statement s;
	Connection c;
	public DBAutor() throws ClassNotFoundException, SQLException {
		c = Connect.New().getC();
		s = c.createStatement();
	}
	
	public static DBAutor New() throws Throwable{
		if(DBAutor.self == null)
			DBAutor.self = new DBAutor();
		return DBAutor.self;
	}
	
	public boolean insertAutor(Pessoa e) throws SQLException{
		PreparedStatement p = c.prepareStatement("INSERT INTO autor(nome) VALUES (?);");
	
		p.setString(1, e.getNome());
		return p.execute();
	}
	
	public int buscaIdAutor(String nome) throws SQLException{
		ResultSet rs = s.executeQuery("SELECT idautor FROM autor WHERE " +
				"nome = '" + nome + "';");
		while(rs.next())
			return rs.getInt("idautor");
		
		return 0;
	}
	
	public Pessoa buscaAutorPorID(int id) throws SQLException{
		Pessoa p = new Pessoa();
		ResultSet rs = s.executeQuery("SELECT idautor FROM autor WHERE " +
				"idautor = '" + id + "';");
		while(rs.next()){
			p.setNome("nome");
			return p;
		}
		return null;
	}
	
	public ArrayList<Pessoa> listaAutor() throws SQLException{
		ArrayList<Pessoa> autores = new ArrayList<Pessoa>();
		
		ResultSet rs = s.executeQuery("SELECT nome FROM autor;");
		while(rs.next()){
			Pessoa e = new Pessoa();
			e.setNome(rs.getString("nome"));
			autores.add(e);
		}		
		return autores;		
	}


}
