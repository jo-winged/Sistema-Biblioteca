package dataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Usuario;
//Ainda falta os 'Alter table'...
public class DBUsuario {
	private static DBUsuario self = null;
	Statement s;
	Connection c;
	
	public DBUsuario() throws ClassNotFoundException, SQLException {
		Connect con = Connect.New();
		System.out.println("criou statement...\n");

		c = con.getC();
		this.s = c.createStatement();
	}

	public static DBUsuario New() throws SQLException, ClassNotFoundException{
		if(DBUsuario.self == null)
			DBUsuario.self = new DBUsuario();
		return DBUsuario.self;
	}
	
	public boolean insertUsuario(Usuario user) throws SQLException{
		PreparedStatement p = this.c.prepareStatement("INSERT INTO usuario(" +
				"nome, login, senha, tipo) VALUES (?, ?, ?, ?);");
		
		p.setString(1, user.getNome());
		p.setString(2, user.getLogin());
		p.setString(3, user.getSenha());
		if(user.isProfessor())
			p.setBoolean(4, true);
		else
			p.setBoolean(4, false);
		
		return p.execute();
	}
	
	public ArrayList<Usuario> buscaUsuarioNome(String nome) throws SQLException{
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		ResultSet rs = this.s.executeQuery("SELECT * FROM usuario WHERE nome LIKE '%" + nome + "%'");
		while(rs.next()){
			Usuario u = new Usuario();
			u.setNome(rs.getString("nome"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setProfessor(rs.getBoolean("tipo"));
			
			users.add(u);
		}
		return users;
	}
	
	public ArrayList<Usuario> buscaUsuarioLogin(String login) throws SQLException{
		ArrayList<Usuario> users = new ArrayList<Usuario>();
		ResultSet rs = this.s.executeQuery("SELECT * FROM usuario WHERE login LIKE '%" + login + "%'");
		while(rs.next()){
			Usuario u = new Usuario();
			u.setNome(rs.getString("nome"));
			u.setLogin(rs.getString("login"));
			u.setSenha(rs.getString("senha"));
			u.setProfessor(rs.getBoolean("tipo"));
			
			users.add(u);
		}
		return users;
	}
	
	public int buscaIdUsuario(String login) throws SQLException{
		ResultSet rs = s.executeQuery("SELECT idusuario FROM usuario WHERE " +
				"login = '" + login + "';");
		while(rs.next())
			return rs.getInt("idusuario");
		
		return 0;
	}
	
	public Usuario buscaUsuarioPorID(int id) throws SQLException{
		Usuario user = new Usuario();
		ResultSet rs = s.executeQuery("SELECT idusuario FROM usuario WHERE " +
				"idusuario = '" + id + "';");
		while(rs.next()){
			user.setLogin(rs.getString("login"));
			user.setNome(rs.getString("nome"));
			user.setSenha(rs.getString("senha"));
			user.setProfessor(rs.getBoolean("professor"));
			
			return user;
		}
		return null;
	}
	
	public boolean validarSenha(String login, String senha) throws SQLException{
		ResultSet rs = this.s.executeQuery("SELECT idusuario FROM usuario WHERE" +
				"login = '" + login +"' AND senha = '" + senha + "';");
		if(rs.first())
			return true;
		
		return false;
	}
	
	public boolean trocarSenha(String login, String oldPass, String newPass) throws SQLException{
		PreparedStatement p = this.c.prepareStatement("UPDATE usuario SET senha = ?" + 
				"' WHERE login = '" + login + "' AND senha = '" + oldPass + "'");
		p.setString(1, newPass);
		
		return p.execute(); 
	}
	
	public int removerUsuario(String login) throws SQLException{
		 return s.executeUpdate("DELETE FROM usuario WHERE login = ?");
	}
}
