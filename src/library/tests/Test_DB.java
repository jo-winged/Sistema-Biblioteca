package library.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import library.src.Categoria;
import library.src.Editora;
import library.src.Emprestimo;
import library.src.Livro;
import library.src.Pessoa;
import library.src.Usuario;

import org.junit.Test;

import com.trolltech.qt.core.QDate;

import dataBase.DBAutor;
import dataBase.DBCategoria;
import dataBase.DBEditora;
import dataBase.DBEmprestimo;
import dataBase.DBLivro;
import dataBase.DBUsuario;

public class Test_DB {
	
	@Test
	public void testAddUser() throws SQLException, ClassNotFoundException{
		Usuario user = new Usuario();
		user.setLogin("blaxa");
		user.setNome("MariaLucia");
		user.setSenha("1234");
		user.setProfessor(false);
		
		DBUsuario dbU = new DBUsuario();
		
		assertTrue(dbU.insertUsuario(user) == false);
	}
	
	@Test
	public void testAddCategoria() throws SQLException, Throwable{
		Categoria cat = new Categoria();
		cat.setDescricao("informatica");
		
		assertTrue(DBCategoria.New().insertCategoria(cat) == false);
		
	}
	
	@Test
	public void testAddEditora() throws SQLException, Throwable{
		Editora ed = new Editora();
		ed.setNome("Edit");
		
		assertTrue(DBEditora.New().insertEditora(ed) == false);
	}

	@Test
	public void testAddAutor() throws SQLException, Throwable{
		Pessoa a = new Pessoa();
		a.setNome("Joao");
		
		assertTrue(DBAutor.New().insertAutor(a) == false);
	}
	
	@Test
	public void testAddBook() throws SQLException, ClassNotFoundException, Throwable{
		Pessoa a = new Pessoa();
		a.setNome("Joao");
		Editora ed = new Editora();
		ed.setNome("Edit");
		Categoria cat = new Categoria();
		cat.setDescricao("informatica");
		
		Livro l = new Livro();
		l.setAno(2009);
		l.setCat(cat);
		l.setTitulo("Arquitetura");
		l.setISBN("abcvcdgg");
		l.setIdioma("Espanhol");
		l.setEdition(2);
		l.setEditora(ed);
		ArrayList<Pessoa> p = new ArrayList<Pessoa>();
		p.add(a);
		l.setAutores(p);
		assertTrue(DBLivro.New().insertBook(l) == false);
	}
	
	@Test
	public void searchIdEditora() throws SQLException, Throwable{
		int idEd = DBEditora.New().buscaIdEditora("Edit");
		System.out.println("IDED "+idEd);
		assertTrue(idEd == 3);
	}
	
	@Test
	public void searchIdCategoria() throws SQLException, Throwable{
		assertTrue(DBCategoria.New().buscaIdCategoria("informatica") == 4);
	}
	
	@Test
	public void searchIdAutor() throws SQLException, Throwable{
		assertTrue(DBAutor.New().buscaIdAutor("Joao") == 2);
	}
	
	@Test
	public void searchIdLivro() throws SQLException, Throwable{
		assertTrue(DBLivro.New().buscaIdLivro("abcvcdgg") == 12);
	}
	
	@Test
	public void searchIdUser() throws SQLException, Throwable{
		assertTrue(DBUsuario.New().buscaIdUsuario("blaxa") == 22);
	}
	
	@Test
	public void testAddEmprestimo() throws SQLException, ClassNotFoundException{
		Pessoa a = new Pessoa();
		a.setNome("Joao");
		Editora ed = new Editora();
		ed.setNome("Edit");
		Categoria cat = new Categoria();
		cat.setDescricao("informatica");
		
		Livro l = new Livro();
		l.setAno(2009);
		l.setCat(cat);
		l.setTitulo("Arquitetura");
		l.setISBN("abcvcdgg");
		l.setIdioma("Espanhol");
		l.setEdition(2);
		l.setEditora(ed);
		ArrayList<Pessoa> p = new ArrayList<Pessoa>();
		p.add(a);
		l.setAutores(p);
		
		Usuario user = new Usuario();
		user.setLogin("blaxa");
		user.setNome("Maria Lucia");
		user.setSenha("1234");
		user.setProfessor(false);
		
		Emprestimo emp = new Emprestimo();
		emp.setBook(l);
		emp.setUser(user);
		emp.setDate(QDate.currentDate());
		
		assertTrue(DBEmprestimo.New().insertEmprestimo(emp) == false);
		
	}
	
	@Test
	public void testRenew() throws Exception{
		Pessoa a = new Pessoa();
		a.setNome("Joao");
		Editora ed = new Editora();
		ed.setNome("Edit");
		Categoria cat = new Categoria();
		cat.setDescricao("informatica");
		
		Livro l = new Livro();
		l.setAno(2009);
		l.setCat(cat);
		l.setTitulo("Arquitetura");
		l.setISBN("abcvcdgg");
		l.setIdioma("Espanhol");
		l.setEdition(2);
		l.setEditora(ed);
		ArrayList<Pessoa> p = new ArrayList<Pessoa>();
		p.add(a);
		l.setAutores(p);
		
		Usuario user = new Usuario();
		user.setLogin("blaxa");
		user.setNome("Maria Lucia");
		user.setSenha("1234");
		user.setProfessor(false);
		
		//Emprestimo emp = DBEmprestimo.New().getEmprestimo(user, l);		
		//assertNotNull(emp);
		
		/*assertTrue(DBEmprestimo.New().renewable(emp) == true);
		assertTrue(DBEmprestimo.New().renew(emp) == false);
		assertTrue(DBEmprestimo.New().renew(emp) == false);
		assertTrue(DBEmprestimo.New().renew(emp) == false);
		assertTrue(DBEmprestimo.New().renew(emp) == true);*/
		
		//assertTrue(DBEmprestimo.New().devolucao(emp) == false);
		//assertTrue(DBEmprestimo.New().devolucao(emp) == true);
	}
	
	@Test
	public void testBuscaUsuarioLogin() throws SQLException, ClassNotFoundException{
		ArrayList<Usuario> users = DBUsuario.New().buscaUsuarioLogin("bla"); 
		
		assertTrue(users.size() == 2);
	}
	
	@Test
	public void testBuscaUsuarioNome() throws SQLException, ClassNotFoundException{
		ArrayList<Usuario> users = DBUsuario.New().buscaUsuarioNome("Maria"); 
		
		assertTrue(users.size() == 2);
	}
	
	@Test 
	public void buscaLivroISBN() throws SQLException, ClassNotFoundException{
		ArrayList<Livro> books = DBLivro.New().buscaLivroISBN("abcv");
		
		assertTrue(books.size() == 5);
	}

	@Test
	public void buscaLivroTitulo() throws SQLException, ClassNotFoundException{
		ArrayList<Livro> books = DBLivro.New().buscaLivroTitulo("POO");
		
		assertTrue(books.size() == 4);		
	}
	
	@Test
	public void buscaLivroEditora() throws SQLException, ClassNotFoundException{
		ArrayList<Livro> books = DBLivro.New().buscaLivroISBN("Edit");
		
		assertTrue(books.size() == 5);
	}
	
}
