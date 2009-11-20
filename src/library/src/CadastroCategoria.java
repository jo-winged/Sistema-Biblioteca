package library.src;

import java.util.ArrayList;

public class CadastroCategoria {
	private ArrayList <Categoria> categorias;

	public CadastroCategoria() {
		categorias = new ArrayList<Categoria>();
	}

	public int getNumCat() {
		// TODO Auto-generated method stub
		return categorias.size();
	}

	public void addCategoria(Categoria cat){
		categorias.add(cat);
	}
	
	public boolean removeCat(Categoria c){
		return categorias.remove(c);
	}
	
}