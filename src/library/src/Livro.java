package library.src;

import java.util.ArrayList;

//autores, edição, editora, nome, ano
public class Livro {
	private String editora, titulo;
	private int ano, edition, ISBN;
	private int exemplares;
	private int avaliables, reserved;
	
	private ArrayList<Pessoa> autores;
	public Livro() {
		editora = "";
		titulo = "";
		ano = 0;
		edition = 0;
		ISBN = 0;
		autores = new ArrayList<Pessoa>();
		exemplares = 0;
		reserved = 0;		
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
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public int getExemplares() {
		return exemplares;
	}
	public void setExemplares(int exemplares) {
		this.exemplares = exemplares;
		this.avaliables = this.exemplares;
	}
	public int getAvaliables() {
		return avaliables;
	}
	public boolean borrow() {
		if (this.avaliables > (1 + this.reserved)){
		   avaliables--;
		   return true;
		}
		return false;
	}
	public void setReserved(){
		this.reserved++;
	}
	public int getReserved() {
		return reserved;
	}
}
