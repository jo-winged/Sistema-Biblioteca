package library.tests;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;
import library.src.CadastroUsuario;
import library.src.Usuario;

public class Test_CadastroUsuario extends TestCase {

	public void testCadastroUsuario() {
		CadastroUsuario cad = new CadastroUsuario();
		assertEquals(0, cad.getNumUsers());
		
	}

	public void testGetUsuarios() {
		CadastroUsuario cadUser = new CadastroUsuario();
		ArrayList<Usuario>list = new ArrayList<Usuario>();
		Usuario u = new Usuario();
		u.setLogin("login");
		list.add(u);
		cadUser.setUsuarios(list);
		
		assertEquals("login", cadUser.getUsuarios().get(0).getLogin());
		
	}

	public void testSetUsuarios() {
		CadastroUsuario cadUser = new CadastroUsuario();
		ArrayList<Usuario>list = new ArrayList<Usuario>();
		Usuario u = new Usuario();
		u.setLogin("login");
		list.add(u);
		cadUser.setUsuarios(list);
		
		assertEquals(true, cadUser.removeUser(u));
		
	}

	public void testInsertUser() {
		Usuario user = new Usuario();
		user.setLogin("xxx");
		CadastroUsuario cad = new CadastroUsuario();
		try{
			cad.insertUser(user);
			Assert.assertEquals(true, true);
		}catch (Throwable e) {
			fail("Fail");
		}
		try{
			cad.insertUser(user);
			fail("Fail");
		
		}catch (Throwable e) {
			Assert.assertEquals(true, true);	
		}
		
	}

	public void testRemoveUser() {
		Usuario user = new Usuario();
		user.setLogin("xxx");
		
		CadastroUsuario cad = new CadastroUsuario();
		Assert.assertEquals(false, cad.removeUser(user));
		
		try {
			cad.insertUser(user);
			Assert.assertEquals(true, cad.removeUser(user));
			
		} catch (Throwable e) {
			fail("Não deveria lançar isso....");
			e.printStackTrace();
		}
		
		try {
			cad.insertUser(user);
		} catch (Throwable e1) {
			fail("Error reinserting the user after remove it");
			e1.printStackTrace();
		}
		
		Usuario user_2 = cad.buscaUsuarioPorLogin("xxx");
		Assert.assertEquals(true, cad.removeUser(user_2));
		
		try {
			cad.insertUser(user);
			
		} catch (Throwable e) {
			fail("Error reinserting the user after remove it");
			e.printStackTrace();
		}
		Usuario user_3 = cad.buscaUsuarioPorLogin("not_found");
		Assert.assertEquals(false, cad.removeUser(user_3));
		
	}

	public void testBuscaUsuarioPorNome() {
		CadastroUsuario cad = new CadastroUsuario();
		Assert.assertEquals(null, cad.buscaUsuarioPorNome("Joazinho"));
	}

	public void testBuscaUsuarioPorLogin() {
		CadastroUsuario cad = new CadastroUsuario();
		Assert.assertEquals(null, cad.buscaUsuarioPorLogin("Joazinho"));
	}

	public void testValidPassword() {
		Usuario user = new Usuario();
		user.setLogin("xxx");
		user.setSenha("xxx");
		CadastroUsuario cad = new CadastroUsuario();
		try{
			cad.insertUser(user);
			assertTrue(cad.validPassword("xxx", "xxx"));
		}catch (Throwable e) {
			fail("Fail");
		}
	}

}
