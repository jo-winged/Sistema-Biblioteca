package library.src;

import java.util.Calendar;

public class Emprestimo {
	private Usuario user;
	private Livro book;
	Calendar date;
	int renews;


	public Emprestimo() {
		user = new Usuario();
		book = new Livro();
		date = Calendar.getInstance();
		renews = 0;
	}
	public Usuario getUser() {
		return user;
	}
	public void setUser(Usuario user) {
		this.user = user;
	}
	public Livro getBook() {
		return book;
	}
	public void setBook(Livro book) {
		this.book = book;
	}

	public boolean addBook(Livro book){//Armazenar data de emprestimo...
		if(user.howManyFines() == 0){
			this.book = book;
			return true;
		}		
		return false;//tem multas naum pagas;
	}

	public void removeBook(Livro book){//verificar se esta devolvendo no prazo
		this.book = null;

	}
	
	@Override
	public boolean equals(Object obj) {
		Emprestimo e = (Emprestimo) obj;
		return this.user.equals(e.getUser());
	}

}
