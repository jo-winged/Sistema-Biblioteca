package library.src;
import java.util.ArrayList;

public class Biblioteca {
	private CadastroLivros livros;
	private CadastroUsuario usuarios;
	private ArrayList<Reservas> reserva;
	private ArrayList<Emprestimo> emp;

	private CadastroAutores autores;
	private CadastroCategoria categorias;
	private CadastroEditoras editoras;
	
	public Biblioteca() {
		usuarios = new CadastroUsuario();
		livros = CadastroLivros.New();
		reserva = new ArrayList<Reservas>();
		autores = new CadastroAutores();
		categorias  = new CadastroCategoria();
		editoras = new CadastroEditoras();
	}		
	public boolean setReservas(String ISBN, String login){
		Reservas r = new Reservas();

		Livro l = new Livro();
		l = livros.searchBookISBN(ISBN);
		if(l != null)
			r.setBook(l);
		else
			return false;
		Usuario u = new Usuario();
		u = usuarios.buscaUsuarioPorLogin(login);
		if(u != null)
			r.setUser(u);
		else
			return false;

		reserva.add(r);
		return true;
	}	

	public boolean cancelaReserva(int ISBN, String login){
		for(Reservas r:reserva){
			if((r.getBook().getISBN().equals(ISBN)) && (r.getUser().getLogin().equals(login))){
				reserva.remove(r);
				return true;
			}
		}
		return false;
	}

//	public boolean addEmprestimo(Usuario user, Livro book){
//		if (livros.getAvaliables(book) > 1){
//			if((user.isProfessor() && this.numEmp(user) < 5) || (!user.isProfessor() && this.numEmp(user) < 3)){
//				Emprestimo e = new Emprestimo();
//				e.setUser(user);
//				e.setBook(book);
//				if(e.addBook(book)){//se naum tem multas...
//					this.emp.add(e);
//					return true;
//				}	
//			}		
//		}
//		return false;//jah pegou todos os livros que tinha direito;
//	}

//	public boolean devolve(Usuario user, Livro book){
//		for(Emprestimo e: this.emp){
//			if(e.getUser().equals(user) && e.getBook().equals(book)){
//				e.removeBook();
//				return true;
//			}				
//		}			
//			
//		return false;
//	}


	public int numEmp(Usuario user){
		int num = 0;
		for(Emprestimo e : this.emp){
			if(e.getUser().equals(user)){
				num++;
			}
		}
		return num;
	}
	public void setCadUsers(CadastroUsuario cad) {
		usuarios = cad;
	}

	public CadastroUsuario getCadastroUsuarios() {
		return usuarios;
	}
	public CadastroLivros getCadastroLivros() {
		return livros;
	}
	public CadastroAutores getCadastroAutores() {
		return autores;
	}
	public CadastroCategoria getCategorias() {
		return categorias;
	}
	public CadastroEditoras getEditoras() {
		return editoras;
	}
	

}
