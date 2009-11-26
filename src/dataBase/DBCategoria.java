package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Categoria;

public class DBCategoria {
	private static DBCategoria self = null;
	Statement s;
	Connection c;
	public DBCategoria() throws ClassNotFoundException, Throwable {
		c = Connect.New().getC();
		s = c.createStatement();
	}
	
	public static DBCategoria New() throws Throwable{
		if(DBCategoria.self == null)
			DBCategoria.self = new DBCategoria();
		return DBCategoria.self;
	}
	
	public boolean insertCategoria(Categoria e) throws SQLException{
		PreparedStatement p = c.prepareStatement("INSERT INTO categoria(descricao) " +
				"VALUES (?);");
	
		p.setString(1, e.getDescricao());
		return p.execute();
	}
	
	public int buscaIdCategoria(String descricao) throws SQLException{
		ResultSet rs = s.executeQuery("SELECT idcategoria FROM categoria WHERE " +
				"descricao = '" + descricao + "';");
		while(rs.next())
			return rs.getInt(1);
		
		return 0;
	}
	
	public Categoria buscaCategoriaPorID(int id) throws SQLException{
		Categoria c = new Categoria();
		ResultSet rs = s.executeQuery("SELECT idcategoria FROM categoria WHERE " +
				"idcategoria = '" + id + "';");
		while(rs.next()){
			c.setDescricao(rs.getString("descricao"));
			return c;
		}
		return null;
	}
	
	public ArrayList<Categoria> listaCategoria() throws SQLException{
		ArrayList<Categoria> autores = new ArrayList<Categoria>();
		
		ResultSet rs = s.executeQuery("SELECT descricao FROM categoria;");
		while(rs.next()){
			Categoria e = new Categoria();
			e.setDescricao(rs.getString("descricao"));
			autores.add(e);
		}		
		return autores;		
	}
}
