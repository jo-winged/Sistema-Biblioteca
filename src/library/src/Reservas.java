package library.src;

public class Reservas {
	private Usuario user;
	private Livro book;
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
	}

	public boolean equals(Object obj) {
		Reservas r = (Reservas) obj;
		if (r.getBook().equals(this.getBook()) && r.getUser().equals(this.getUser()) )
		    return true;
		return false;
	}
}
