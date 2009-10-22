package library.src;

import com.trolltech.qt.core.QDate;

public class Reservas {
	private Usuario user;
	private Livro book;
	private QDate reservedDate;
	
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
		if (r.getBook().equals(this.getBook()) &&
			r.getUser().equals(this.getUser()) &&
			r.getReservedDate().equals(this.getReservedDate())){
			return true;
		}else{
			return false;
		}
	}
	public void setReservedDate(QDate reservedDate) {
		this.reservedDate = reservedDate;
	}
	public QDate getReservedDate() {
		return reservedDate;
	}
}
