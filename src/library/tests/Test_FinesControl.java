package library.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import library.src.Fine;
import library.src.FinesControl;
import library.src.Usuario;

import org.junit.Test;

public class Test_FinesControl {

	public void cleanUp(){
		FinesControl control = FinesControl.New();
		
	}
	
	@Test
	public void testAddFine() {
		FinesControl control = FinesControl.New();
		Fine fine = new Fine();
		
		assertTrue(control.FinesNotPaid() == 0);
		assertTrue(control.FinesPaid() == 0);
		control.addFine(fine);
		assertTrue(control.FinesNotPaid() == 1);
		assertTrue(control.FinesPaid() == 0);
		
		
	}

	@Test
	public void testPay() {
		FinesControl control = FinesControl.New();
		Fine fine = new Fine();
		
		control.addFine(fine);
		
		assertTrue(control.FinesNotPaid() == 1);
		assertTrue(control.FinesPaid() == 0);
		
		control.pay(fine);
		
		assertTrue(control.FinesNotPaid() == 0);
		assertTrue(control.FinesPaid() == 1);
		
	}

	@Test
	public void testNew() {
		FinesControl control1 = FinesControl.New();
		FinesControl control2 = FinesControl.New();
		
		assertTrue(control1 == control2);
		
	}

	@Test
	public void testAddFineWithUserAndDays(){
		FinesControl control = FinesControl.New();
		Usuario user = new Usuario();
		user.setNome("Polly");
		user.setLogin("parrot");
		control.addFine(user, 3);
		assertTrue(user.howManyFines() == 1);
		ArrayList<Fine> fines = control.getFines(user);
		assertTrue(fines.size() == 1);
		
		Usuario user2 = new Usuario();
		user2.setLogin("Polly");
		control.addFine(user2, 3);
	}
	
	
}
