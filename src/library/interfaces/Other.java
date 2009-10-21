package library.interfaces;

import library.src.Biblioteca;
import library.src.CadastroAutores;

import com.trolltech.qt.core.QObject;
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
        connectSlots();
        
        refreshData();
    }
    
    private void connectSlots() {
//		ui.seekLoginTrans.
		
	}

	private void refreshData() {
		
    	//ToDO Enter how refresh data...
		
	}

	public void cadastrarLivro(){
    	
    }
    
    public void cadastrarUsuario(){
    	
    }
 
}
