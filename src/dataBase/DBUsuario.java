package dataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.src.Usuario;

public class DBUsuario {
	private static DBUsuario self = null;
	Statement s;
	Connect c;
	
	public DBUsuario() throws ClassNotFoundException, SQLException {
		c = Connect.New();	
		this.s = c.getC().createStatement();
	}

	public DBUsuario New() throws SQLException, ClassNotFoundException{
		if(DBUsuario.self == null)
			DBUsuario.self = new DBUsuario();
		return DBUsuario.self;
	}
	
	public boolean insertUsuario(Usuario user) throws SQLException{
		PreparedStatement p = this.c.getC().prepareStatement("INSERT INTO usuario(" +
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
		ResultSet rs = this.s.executeQuery("SELECT * FROM usuario WHERE nome LIKE %" + nome + "%");
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
		ResultSet rs = this.s.executeQuery("SELECT * FROM usuario WHERE login LIKE %" + login + "%");
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
	
	public boolean validarSenha(String login, String senha) throws SQLException{
		ResultSet rs = this.s.executeQuery("SELECT idusuario FROM usuario WHERE" +
				"login = '" + login +"' AND senha = '" + senha + "';");
		if(rs.first())
			return true;
		
		return false;
	}
	
	public boolean trocarSenha(String login, String oldPass, String newPass) throws SQLException{
		PreparedStatement p = this.c.getC().prepareStatement("UPDATE usuario SET senha = ?" + 
				"' WHERE login = '" + login + "' AND senha = '" + oldPass + "'");
		p.setString(1, newPass);
		
		return p.execute(); 
	}
	
	public int removerUsuario(String login) throws SQLException{
		 return s.executeUpdate("DELETE FROM usuario WHERE login = ?");
	}
}
