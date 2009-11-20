package library.src;

import java.util.ArrayList;

public class CadastroAutores {

	private ArrayList <Pessoa> autores;
	
	public CadastroAutores() {
		autores = new ArrayList<Pessoa>();
	}
	
	public int getNumAuthors() {
		return autores.size();
	}

	public void addAuthor(Pessoa author){
		autores.add(author);
	}
	
	public Pessoa searchAuthor(String name){
		for (Pessoa p: autores){
			if (p.getNome().equals(name)){
				return p;
			}
		}
		return null;
	}
	
	public boolean removeAuthor(Pessoa p){
		return autores.remove(p);
	}
	
}
