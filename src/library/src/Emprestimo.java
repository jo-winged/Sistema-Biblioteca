package library.src;

import java.util.ArrayList;

public class Emprestimo {
	private Usuario user;
	private ArrayList<Livro> book;
	public Emprestimo() {
		user = new Usuario();
		book = new ArrayList<Livro>();
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public ArrayList<Livro> getBook() {
		return book;
	}
	public void setBook(ArrayList<Livro> book) {
		this.book = book;
	}
	
	public boolean addBook(Livro book){//Verificar, também, se usuario naum tem multas...
		if(this.user.isProfessor() && (this.book.size() < 5)){
			this.book.add(book);
			return true;
		}else
			if(!this.user.isProfessor() && (this.book.size() < 3)){
				this.book.add(book);
				return true;
			}		
		return false;
	}
	
	public boolean removeBook(Livro book){//verificar se esta devolvendo no prazo
		return (this.book.remove(book));
	
	}
}
