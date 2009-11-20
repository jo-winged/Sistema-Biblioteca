
package library.src;

public class Usuario extends Pessoa{
	private String login, senha;
	private boolean professor;
	private int haveFines; 
	
	public Usuario() {
		login = "";
		senha = "";
		setProfessor(false);
		haveFines = 0;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean validateLogin(String pass){
		return (pass.equals(senha));
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSenha(){
		return this.senha;
	}
	
	public void setProfessor(boolean professor) {
		this.professor = professor;
	}
	public boolean isProfessor() {
		return professor;
	}
	
	public void setFine() {
		haveFines += 1;
	}
	
	public int howManyFines(){
		return haveFines;
	} 
	
	public void payFine(){
		
		haveFines -= 1;
	}
	@Override
	public boolean equals(Object obj) {
		Usuario l = (Usuario) obj;
		return l.getLogin().equals(this.getLogin());
	}
	
}
