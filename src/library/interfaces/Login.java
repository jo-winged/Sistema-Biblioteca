package library.interfaces;

import com.trolltech.qt.gui.*;

public class Login extends QWidget {

    Ui_Login ui = new Ui_Login();

    public static void main(String[] args) {
        QApplication.initialize(args);

        Login testLogin = new Login();
        testLogin.show();

        QApplication.exec();
    }

    public Login() {
        ui.setupUi(this);
    }

    public Login(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
    
}
