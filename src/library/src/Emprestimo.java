package library.src;

import com.trolltech.qt.core.QDate;

public class Emprestimo {
	private Usuario user;
	private Livro book;
	QDate date;
	int renews;


	public Emprestimo() {
		user = new Usuario();
		book = new Livro();
		date = new QDate();
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
	
	public QDate getDate() {
		return date;
	}
	public void setDate(QDate date) {
		this.date = date;
	}
	public int getRenews() {
		return renews;
	}
	protected void setRenews(int renews) {
		this.renews = renews;
	}
	
	public void renew(){
		this.setDate(QDate.currentDate().addDays(7));
		this.setRenews(this.getRenews()+1);
	}
	
	@Override
	public boolean equals(Object obj) {
		Emprestimo e = (Emprestimo) obj;
		return (this.user.equals(e.getUser()) && this.book.equals(e.getBook()));
	}

}
