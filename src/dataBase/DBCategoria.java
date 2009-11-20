package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Categoria;

public class DBCategoria {
	private static DBCategoria self = null;
	Statement s;
	Connect c;
	public DBCategoria() throws ClassNotFoundException, Throwable {
		c = Connect.New();
		s = c.getC().createStatement();
	}
	
	public static DBCategoria New() throws Throwable{
		if(DBCategoria.self == null)
			DBCategoria.self = new DBCategoria();
		return DBCategoria.self;
	}
	
	public boolean insertCategoria(Categoria e) throws SQLException{
		PreparedStatement p = c.getC().prepareStatement("INSERT INTO categoria(descricao) " +
				"VALUES (?);");
	
		p.setString(1, e.getDescricao());
		return p.execute();
	}
	
	public int buscaIdCategoria(String descricao) throws SQLException{
		ResultSet rs = s.executeQuery("SELECT idcategoria FROM categoria WHERE" +
				"descricao = " + descricao + ";");
		while(rs.next())
			return rs.getInt("idcategoria");
		
		return 0;
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
