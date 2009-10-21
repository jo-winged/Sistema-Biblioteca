package library.interfaces;

import com.trolltech.qt.gui.*;

public class UserSearchResult extends QWidget {

    Ui_UserSearchResult ui = new Ui_UserSearchResult();

    public static void main(String[] args) {
        QApplication.initialize(args);

        UserSearchResult testUserSearchResult = new UserSearchResult();
        testUserSearchResult.show();

        QApplication.exec();
    }

    public UserSearchResult() {
        ui.setupUi(this);
    }

    public UserSearchResult(QWidget parent) {
        super(parent);
        ui.setupUi(this);
    }
}
