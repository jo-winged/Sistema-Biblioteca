package library.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;
import library.interfaces.CheckPassword;

import org.junit.Test;

import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QDialog;


public class Test_CheckLogin {

	@Test
	public void testSetRenews() {
		String[] str = {"", ""};
		@SuppressWarnings("unused")
		QApplication app = new QApplication(str);
		CheckPassword p = new CheckPassword();
		p.setUser("My user");
		if (p.exec() == 1){
			String pass = p.getPass();
			assertEquals("pass", pass);
		}else
			fail("Cancel clicked");
	}
}
