package library.src;

import java.util.ArrayList;

public class CadastroEditoras {
	ArrayList<Editora> editoras;
	public CadastroEditoras() {
		editoras = new ArrayList<Editora>();
	}

	public int getNumEditoras() {
		// TODO Auto-generated method stub
		return editoras.size();
	}

	public void addEditora(Editora e){
		editoras.add(e);
	}
	
	public boolean removeEditora(Editora e){
		return editoras.remove(e);
	}
}
