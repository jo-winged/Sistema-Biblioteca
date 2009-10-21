package library.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

public class Library {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for library.tests");
		//$JUnit-BEGIN$
		suite.addTestSuite(Test_CadastroUsuario.class);
		suite.addTestSuite(Test_Fines.class);
		suite.addTestSuite(Test_FinesControl.class);
		//$JUnit-END$
		return suite;
	}

}
