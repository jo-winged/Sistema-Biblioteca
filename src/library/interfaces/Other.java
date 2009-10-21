package library.interfaces;

import library.src.Biblioteca;

import com.trolltech.qt.gui.*;

public class Other extends QMainWindow {

    Ui_Other ui = new Ui_Other();
    Biblioteca bib = new Biblioteca();

    public static void main(String[] args) {
        QApplication.initialize(args);

        Other testOther = new Other();
        testOther.show();

        QApplication.exec();
    }

    public Other() {
        ui.setupUi(this);
    }

    public Other(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
    
    public void cadastrarLivro(){
    	
    }
    
    public void cadastrarUsuario(){
    	
    }
 
}
