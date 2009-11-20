package library.src;

import java.util.ArrayList;
import java.util.HashMap;

public class CadastroLivros {
	private static CadastroLivros self = null;
	private ArrayList<Livro> livros ;
	private HashMap<String, Integer> mapa;
	
	private CadastroLivros() {
		livros = new ArrayList<Livro>();
		mapa = new HashMap<String, Integer>();
	}

	public void addBook(Livro book){
		if (this.searchBookISBN(book.getISBN()) == null){
			livros.add(book);
			mapa.put(book.getISBN(), 0);
		}
	}
	
	public void removeBook(String isbn){
		int index = livros.indexOf(this.searchBookISBN(isbn));
		this.livros.remove(index);
		this.mapa.put(isbn, 0);
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
	
	public ArrayList<Livro> searchBookTitle(String title){
		ArrayList<Livro> list = new ArrayList<Livro>();
		for(Livro book:livros){
			if(book.getTitulo().contains(title))
				list.add(book);
		}
		return list;
	}

	public int getExemplares(Livro book) {
		
		return mapa.get(book.getISBN());
	}

	public void setExemplares(Livro book, int exemplares) {
		mapa.put(book.getISBN(), exemplares);
	}

	public int getAvaliables(Livro book ) {
		return getExemplares(book) - ReserveControl.New().numReserves(book) - EmprestimoControl.New().howManyBorrows(book);
	}

	public int getNumBooks() {
		return livros.size();
	}

	public static CadastroLivros New() {
		if (CadastroLivros.self == null)
			CadastroLivros.self  = new CadastroLivros();
		return CadastroLivros.self;
	}

}
