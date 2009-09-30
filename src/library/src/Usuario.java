
package library.src;

public class Usuario extends Pessoa{
	private String login, senha;
	private boolean professor; 
	public Usuario() {
		login = "";
		senha = "";
		setProfessor(false);
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean validateLogin(String pass){
		return (pass == senha);
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setProfessor(boolean professor) {
		this.professor = professor;
	}
	public boolean isProfessor() {
		return professor;
	}
	
}
