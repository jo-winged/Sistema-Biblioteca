package library.tests;

import static org.junit.Assert.*;
import library.src.Fine;
import library.src.Usuario;

import org.junit.Test;

public class Test_Fines {

	private Fine createFine() {
		Fine fine = new Fine();
		return fine;
	}
	
	
	
	
	@Test
	public void testFineValue() {
		Fine fine = createFine();
		fine.setNumDays(3);
	    assertTrue(3.0 == fine.fineValue());
	}

	@Test
	public void testSetNumDays() {
		Fine fine = createFine();
		fine.setNumDays(3);
		assertTrue(3 == fine.fineValue());
	}

	@Test
	public void testSetUser() {
		Fine fine = createFine();
		Usuario user = new Usuario();
		fine.setUser(user);
		assertTrue(user.howManyFines() == 1);
		assertTrue(!fine.wasPaid());
	}

	@Test
	public void testPayFine() {
		Fine fine = createFine();
		Usuario user = new Usuario();
		fine.setUser(user);
		assertTrue(user.howManyFines() == 1);
		assertTrue(!fine.wasPaid());
		
		fine.payFine();
		
		assertTrue(user.howManyFines() == 0);
		assertTrue(fine.wasPaid());
	}

}
