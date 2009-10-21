/**
 * 
 */
package library.tests;

import static org.junit.Assert.*;
import library.src.Biblioteca;
import library.src.Emprestimo;
import library.src.EmprestimoControl;
import library.src.FinesControl;
import library.src.Livro;
import library.src.ReserveControl;
import library.src.Usuario;

import org.junit.Test;
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
		Emprestimo emp = new Emprestimo();
		emp.setUser(user);
		emp.setBook(book);
		EmprestimoControl.New().addEmprestimo(emp);
		assertEquals(book, emp.getBook());
		assertEquals(user, emp.getUser());
		assertEquals(1,EmprestimoControl.New().getNumEmprestimos());
		assertEquals(2, biblioteca.getCadastroLivros().getAvaliables(book));
		
		
	}


}
