package library.src;

import java.util.ArrayList;


public class CadastroUsuario {
	ArrayList<Usuario> usuarios; 
	public CadastroUsuario() {
		usuarios = new ArrayList<Usuario>();
	}
	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	// login deve ser único
	public void insertUser(Usuario user) throws Throwable{
		for (Usuario u: usuarios){
			if (u.getLogin().equals(user.getLogin())){
				throw new Throwable("Usuário já existe!!!");
			}
		}
		usuarios.add(user);
	}
	public boolean removeUser(Usuario user){
		if (user == null)
			return false;
		for (Usuario u: usuarios){
			if (u.getLogin().equals(user.getLogin())){
				usuarios.remove(u);
				return true;
			}
		}
		return false;
	}
	
	public Usuario buscaUsuarioPorNome(String name){
		for (Usuario user : usuarios){
			if (user.getNome().contains(name))
				return user;
		}
		return null;
	}

	public Usuario buscaUsuarioPorLogin(String val){
		for (Usuario user : usuarios){
			if (user.getLogin().equals(val))
				return user;
		}
		return null;
	}
	
	public Boolean validPassword(String login, String pass){
		for (Usuario user : usuarios){
			if (user.getLogin().equals(login))
				return (user.validateLogin(pass));
		}
		return false;
	}
	public int getNumUsers() {
		return usuarios.size();
	}	
}
