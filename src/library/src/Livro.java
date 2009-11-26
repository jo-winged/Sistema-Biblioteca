package library.src;

import java.util.ArrayList;

//autores, edição, editora, nome, ano
public class Livro {
	private String titulo, ISBN, idioma;
	private int ano, edition, numExemplares;
	Categoria cat;
	Editora editora;
	
	private ArrayList<Pessoa> autores;
	public Livro() {
		editora = new Editora();
		cat = new Categoria();
		titulo = "";
		idioma = "";
		ano = 0;
		edition = 0;
		ISBN = "";
		autores = new ArrayList<Pessoa>();

	}
		
	public int getNumExemplares() {
		return numExemplares;
	}

	public void setNumExemplares(int numExemplares) {
		this.numExemplares = numExemplares;
	}

	public void setAutores(ArrayList<Pessoa> autores) {
		this.autores = autores;
	}
	public ArrayList<Pessoa> getAutores() {
		return autores;
	}
	
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public Categoria getCat() {
		return cat;
	}
	public void setCat(Categoria cat) {
		this.cat = cat;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getEdition() {
		return edition;
	}
	public void setEdition(int edição) {
		this.edition = edição;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	@Override
	public boolean equals(Object obj) {
		Livro l = (Livro) obj;
		return l.getISBN().equals(this.getISBN());
	}
}
