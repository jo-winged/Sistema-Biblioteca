package library.tests;

import static org.junit.Assert.*;

import library.src.Emprestimo;
import library.src.EmprestimoControl;
import library.src.Fine;
import library.src.FinesControl;
import library.src.Usuario;

import org.junit.Test;

import com.trolltech.qt.core.QDate;

public class Test_FinesControl {

	private void cleanUp(){
		FinesControl control = FinesControl.New();
		control.cleanUp();
		
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
		
		this.cleanUp();
		
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
		this.cleanUp();
	}

	@Test
	public void testNew() {
		FinesControl control1 = FinesControl.New();
		FinesControl control2 = FinesControl.New();
		
		assertTrue(control1 == control2);
		this.cleanUp();
	}

	@Test
	public void testAddFineWithUserAndDays(){
		FinesControl control = FinesControl.New();
		Usuario user = new Usuario();
		user.setNome("Polly");
		user.setLogin("parrot");
		control.addFine(user, 3);
		assertTrue(user.howManyFines() == 1);
		
		
		assertEquals(1, control.FinesNotPaid());
		
		control.pay(control.getFines(user).get(0));
		assertEquals(0, control.FinesNotPaid());
		assertEquals(1, control.FinesPaid());
		
		this.cleanUp();
	}	
}
