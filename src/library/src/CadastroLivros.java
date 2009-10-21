package library.src;

import java.util.ArrayList;

public class CadastroLivros {
	private ArrayList<Livro> livros;
	private ArrayList<Integer> exemplares;
//Humm dah pra usar um hash aqui.... Livros -> num exemplares
	public CadastroLivros() {
		livros = new ArrayList<Livro>();
		exemplares = new ArrayList<Integer>();
	}

	public void addBook(Livro book){
		if (this.searchBookISBN(book.getISBN()) == null){
			livros.add(book);
			exemplares.add(0);
		}

	}
	

	
	public void removeBook(String isbn){
		int index = livros.indexOf(this.searchBookISBN(isbn));
		this.livros.remove(index);
		this.exemplares.remove(index);
		
	}
	
	public ArrayList<Livro> getBooksList(){
		return livros;
	}
	
	public Livro searchBookISBN(String ISBN){
		for(Livro book:livros){
			if(book.getISBN().equals(ISBN))
				return book;
		}
		return null;
	}
	
	public Livro searchBookEditora(String editora){
		for(Livro book:livros){
			if(book.getEditora().equals(editora))
				return book;
		}
		return null;
	}
	
	public Livro searchBookTitle(String title){
		for(Livro book:livros){
			if(book.getTitulo().contains(title))
				return book;
		}
		return null;
	}

	public int getExemplares(Livro book) {
		int index = livros.indexOf(book);
		if (index < 0)
				return -1;
		return exemplares.get(index);
	}

	public void setExemplares(Livro book, int exemplares) {
		int index = livros.indexOf(book);
		if (index == -1)
			return;
		Integer k = this.exemplares.get(index);
		k = exemplares;//ToDo alterar, não inserir

	}

	public int getAvaliables(Livro book ) {
		return getExemplares(book) - ReserveControl.New().numReserves(book) - EmprestimoControl.New().howManyBorrows(book);
	}

	public int getNumBooks() {
		return livros.size();
	}
}
