package library.interfaces;

import com.trolltech.qt.gui.*;

public class BookSearchResult extends QWidget {

    Ui_BookSearchResult ui = new Ui_BookSearchResult();

    public static void main(String[] args) {
        QApplication.initialize(args);

        BookSearchResult testBookSearchResult = new BookSearchResult();
        testBookSearchResult.show();

        QApplication.exec();
    }

    public BookSearchResult() {
        ui.setupUi(this);
    }

    public BookSearchResult(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
