package library.src;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Emprestimo {
	private Usuario user;
	private Livro book;
	Date date;
	int renews;


	public Emprestimo() {
		user = new Usuario();
		book = new Livro();
		date = new Date();
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
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getRenews() {
		return renews;
	}
	public void setRenews(int renews) {
		this.renews = renews;
	}
	public boolean addBook(Livro book){//Armazenar data de emprestimo...
		if(user.howManyFines() == 0){
			this.date.setDate(this.date.getDate() + 7);
			this.book = book;
			return true;
		}		
		return false;//tem multas naum pagas;
	}

	public void removeBook(){//verificar se esta devolvendo no prazo //ToDo Refatorar para devolve
		Date dataEntrega = new Date();
		if(this.date.compareTo(dataEntrega) < 0){
			FinesControl fine = FinesControl.New();
			fine.addFine(this.user, (dataEntrega.getDate() - this.date.getDate()));		
		}			
		this.book = null;
	}
	
	@Override
	public boolean equals(Object obj) {
		Emprestimo e = (Emprestimo) obj;
		return (this.user.equals(e.getUser()) && this.book.equals(e.getBook()));
	}

}
