package library.src;

public class Reservas {
	Usuario user;
	Livro book;
	public Reservas() {
		user = new Usuario();
		book = new Livro();
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
		this.book.setReserved();
	}
}
