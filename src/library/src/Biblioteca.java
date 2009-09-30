package library.src;

import java.util.ArrayList;


public class Biblioteca {
	private CadastroLivros livros;
	private CadastroUsuario usuarios;
	private ArrayList<Reservas> reserva;
	
	public Biblioteca() {
		usuarios = new CadastroUsuario();
		livros = new CadastroLivros();
		reserva = new ArrayList<Reservas>();
	}
		
	public boolean setReservas(int ISBN, String login){
		Reservas r = new Reservas();
		
		Livro l = new Livro();
		l = livros.searchBookISBN(ISBN);
		if(l != null)
			r.setBook(l);
		else
			return false;
		Usuario u = new Usuario();
		u = usuarios.buscaUsuarioPorLogin(login);
		if(u != null)
			r.setUser(u);
		else
			return false;
		
		reserva.add(r);
		return true;
	}	
	
	public boolean cancelaReserva(int ISBN, String login){
		for(Reservas r:reserva){
			if((r.getBook().getISBN() == ISBN) && (r.getUser().getLogin() == login)){
				reserva.remove(r);
				return true;
			}
		}
		return false;
	}
}
