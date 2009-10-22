/**
 * 
 */
package library.tests;

import static org.junit.Assert.*;
import library.src.Biblioteca;
import library.src.CadastroLivros;
import library.src.Emprestimo;
import library.src.EmprestimoControl;
import library.src.FinesControl;
import library.src.Livro;
import library.src.Reservas;
import library.src.ReserveControl;
import library.src.Usuario;

import org.junit.Test;

import com.trolltech.qt.core.QDate;
/**
 * @author Wiglot
 *
 */
public class Test_Sistema_Biblioteca {

	private static Biblioteca biblioteca;
	@Test
	public void setUp() throws Exception {
	    Test_Sistema_Biblioteca.biblioteca = new Biblioteca();
	    assertEquals(biblioteca.getCadastroLivros().getNumBooks(), 0);
	    assertEquals(biblioteca.getCadastroUsuarios().getNumUsers(), 0);
	    assertEquals(biblioteca.getCadastroAutores().getNumAuthors(), 0);
	    assertEquals(FinesControl.New().FinesNotPaid(),0);
	    assertEquals(FinesControl.New().FinesPaid(),0);
	    assertEquals(EmprestimoControl.New().getNumEmprestimos(),0);
	    assertEquals(ReserveControl.New().getNumReservas(),0);

	}

	/**
	 * Test method for {@link library.src.CadastroUsuario}.
	 */
	@Test
	public void testInsertUsers() {
		Usuario user = new Usuario();
		user.setNome("Polly");
		user.setLogin("parrot");
		user.setSenha("dead");
		try {
			biblioteca.getCadastroUsuarios().insertUser(user);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(biblioteca.getCadastroUsuarios().getNumUsers(), 1);
	}

	/**
	 * Test method for {@link library.src.CadastroLivros}.
	 */
	@Test
	public void testInsertBooks() {
		Livro book = new Livro();
		book.setAno(1);
		book.setEdition(1);
		book.setEditora("Edit edit");
		book.setISBN("ISBN");
		book.setTitulo("Lá e devolta, por Bilbo Bolseiro");
		biblioteca.getCadastroLivros().addBook(book);
		biblioteca.getCadastroLivros().setExemplares(book, 3);
		assertEquals(1, biblioteca.getCadastroLivros().getNumBooks());
		assertEquals(3, biblioteca.getCadastroLivros().getExemplares(book));
	}
	
	@Test
	public void testBorrow() {
		Livro book = biblioteca.getCadastroLivros().searchBookISBN("ISBN");
		Usuario user = biblioteca.getCadastroUsuarios().buscaUsuarioPorLogin("parrot");
		QDate date = new QDate(2009,10,22);
		Emprestimo emp = new Emprestimo();
		emp.setUser(user);
		emp.setBook(book);
		emp.setDate(date);
		try {
			EmprestimoControl.New().addEmprestimo(emp);
			assertEquals(book, emp.getBook());
			assertEquals(user, emp.getUser());
			assertEquals(date.addDays(7), emp.getDate());
			assertEquals(1,EmprestimoControl.New().getNumEmprestimos());
			assertEquals(1,EmprestimoControl.New().howManyBorrows(book));
			assertEquals(1,EmprestimoControl.New().howManyBorrows(user));
			assertEquals(2,CadastroLivros.New().getAvaliables(book));
			
		} catch (Throwable e) {
			fail("Shoud borrow!");
			e.printStackTrace();
		}
		
	}
	@Test
	public void testDelivery(){
		Livro book = biblioteca.getCadastroLivros().searchBookISBN("ISBN");
		Usuario user = biblioteca.getCadastroUsuarios().buscaUsuarioPorLogin("parrot");
		QDate date = new QDate(2009,10,22);
		Emprestimo emp = EmprestimoControl.New().getEmprestimo(user, book, date); 
		EmprestimoControl.New().delivery(emp);
		assertEquals(0,EmprestimoControl.New().getNumEmprestimos());
		assertEquals(0,EmprestimoControl.New().howManyBorrows(book));
		assertEquals(0,EmprestimoControl.New().howManyBorrows(user));
		assertEquals(3, biblioteca.getCadastroLivros().getAvaliables(book));
	}

	@Test
	public void testOverBorrow(){
		Emprestimo emp, emp1, emp2;
		
		Livro book = biblioteca.getCadastroLivros().searchBookISBN("ISBN");
		Usuario user = biblioteca.getCadastroUsuarios().buscaUsuarioPorLogin("parrot");
		QDate date = new QDate(2009,10,23);
		emp = new Emprestimo();
		emp.setUser(user);
		emp.setBook(book);
		emp.setDate(date);
		try {
			EmprestimoControl.New().addEmprestimo(emp);
		} catch (Throwable e) {
			fail("Shoud borrow!");
			e.printStackTrace();
		}
		assertEquals(1,EmprestimoControl.New().getNumEmprestimos());
		assertEquals(2, biblioteca.getCadastroLivros().getAvaliables(book));
		emp1 = new Emprestimo();
		emp1.setUser(user);
		emp1.setBook(book);
		emp1.setDate(date.addDays(2));
		try {
			EmprestimoControl.New().addEmprestimo(emp1);
		} catch (Throwable e) {
			fail("Shoud borrow!");
			e.printStackTrace();
		}
		emp2 = new Emprestimo();
		emp2.setUser(user);
		emp2.setBook(book);
		emp2.setDate(date.addDays(3));
		try {
			EmprestimoControl.New().addEmprestimo(emp2);
			fail("Shoud't borow!");
		} catch (Throwable e) {
			assertEquals("No avaliable book!",e.getMessage());
		}
		EmprestimoControl.New().delivery(emp);
		EmprestimoControl.New().delivery(emp1);
		assertEquals(0,EmprestimoControl.New().getNumEmprestimos());
		assertEquals(0,EmprestimoControl.New().getNumEmprestimos());
		
	}
	
	@Test
	public void testReserves(){
		Livro book = biblioteca.getCadastroLivros().searchBookISBN("ISBN");
		Usuario user = biblioteca.getCadastroUsuarios().buscaUsuarioPorLogin("parrot");
		QDate date = new QDate(2009,10,23);
		
		Reservas r = new Reservas();
		r.setBook(book);
		r.setUser(user);
		r.setReservedDate(date);
		
		ReserveControl.New().addReserve(r);
		assertEquals(1,ReserveControl.New().getNumReservas());
		assertEquals(2,CadastroLivros.New().getAvaliables(book));
		
		ReserveControl.New().removeReserve(r);
		assertEquals(0,ReserveControl.New().getNumReservas());
		assertEquals(3,CadastroLivros.New().getAvaliables(book));
	}
	

}
