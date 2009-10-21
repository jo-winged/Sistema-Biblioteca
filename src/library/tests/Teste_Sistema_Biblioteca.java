/**
 * 
 */
package library.tests;

import static org.junit.Assert.*;
import library.src.Biblioteca;
import library.src.EmprestimoControl;
import library.src.FinesControl;
import library.src.Livro;
import library.src.ReserveControl;
import library.src.Usuario;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Wiglot
 *
 */
public class Teste_Sistema_Biblioteca {

	private Biblioteca biblioteca;
	/**
	 * @throws java.lang.Exception
	 * Inicializa classe da biblioteca
	 */
	@Before
	public void setUp() throws Exception {
	    biblioteca = new Biblioteca();
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
		assertEquals(1, biblioteca.getCadastroLivros().getNumBooks());
	}

	


}
