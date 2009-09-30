package library.src;

import java.util.ArrayList;

public class CadastroLivros {
	private ArrayList<Livro> livros;
	public CadastroLivros() {
		livros = new ArrayList<Livro>();
	}
	public ArrayList<Livro> getBooksList(){
		return livros;
	}
	public Livro searchBookISBN(int ISBN){
		for(Livro book:livros){
			if(book.getISBN() == ISBN)
				return book;
		}
		return null;
	}
	public Livro searchBookEditora(String editora){
		for(Livro book:livros){
			if(book.getEditora() == editora)
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
}
