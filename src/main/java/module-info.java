module no.ntnu.idatt2003.chaosgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens no.ntnu.idatt2003.chaosgame to javafx.fxml;
    exports no.ntnu.idatt2003.chaosgame;
    exports no.ntnu.idatt2003.chaosgame.controller;
    opens no.ntnu.idatt2003.chaosgame.controller to javafx.fxml;
}