package library.src;

import java.util.ArrayList;

//autores, edição, editora, nome, ano
public class Livro {
	private String editora, titulo, ISBN;
	private int ano, edition;
	
	private ArrayList<Pessoa> autores;
	public Livro() {
		editora = "";
		titulo = "";
		ano = 0;
		edition = 0;
		ISBN = "";
		autores = new ArrayList<Pessoa>();

	}
	public void setAutores(ArrayList<Pessoa> autores) {
		this.autores = autores;
	}
	public ArrayList<Pessoa> getAutores() {
		return autores;
	}
	
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
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

	@Override
	public boolean equals(Object obj) {
		Livro l = (Livro) obj;
		return l.getISBN().equals(this.getISBN());
	}
}
