package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Editora;

public class DBEditora {
	private static DBEditora self = null;
	Statement s;
	Connection c;
	public DBEditora() throws SQLException, Throwable {
		c = Connect.New().getC();
		s = c.createStatement();
	}
	
	public static DBEditora New() throws Throwable{
		if(DBEditora.self == null)
			DBEditora.self = new DBEditora();
		return DBEditora.self;
	}
	
	public boolean insertEditora(Editora e) throws SQLException{
		PreparedStatement p = c.prepareStatement("INSERT INTO editora(nome) VALUES (?);");
		
		p.setString(1, e.getNome());
		return p.execute();
	}
	
	public int buscaIdEditora(String nome) throws SQLException{
		ResultSet rs = s.executeQuery("SELECT ideditora FROM editora WHERE " +
				"nome = '" + nome + "';");
		while(rs.next()){
			int idEd = 0;
			try{
				idEd = rs.getInt(1);

			}catch (Exception e) {
				e.getMessage();
			}
			return idEd;
		}
		return 0;
	}
	
	public Editora buscaEditoraPorId(int id) throws SQLException{
		Editora e = new Editora();
		ResultSet rs = s.executeQuery("SELECT ideditora FROM editora WHERE " +
				"ideditora = " + id + ";");
		while(rs.next()){
			e.setNome(rs.getString("nome"));
			return e;
		}
		return null;
	}
	
	
	
	public ArrayList<Editora> listaEditora() throws SQLException{
		ArrayList<Editora> editoras = new ArrayList<Editora>();
		
		ResultSet rs = s.executeQuery("SELECT nome FROM editora;");
		while(rs.next()){
			Editora e = new Editora();
			e.setNome(rs.getString("nome"));
			editoras.add(e);
		}		
		return editoras;		
	}
}
