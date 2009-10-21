package library.interfaces;

import com.trolltech.qt.gui.*;

public class CheckPassword extends QDialog {

    Ui_CheckPassword ui = new Ui_CheckPassword();

    public static void main(String[] args) {
        QApplication.initialize(args);

        CheckPassword testCheckPassword = new CheckPassword();
        testCheckPassword.show();

        QApplication.exec();
    }

    public CheckPassword() {
        ui.setupUi(this);
    }

    public CheckPassword(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
    
    public String getPass(){
    	return ui.password.text();
    }
    
    public void setUser(String user){
    	ui.user.setText(tr("User: ")+ user);
    }
}
