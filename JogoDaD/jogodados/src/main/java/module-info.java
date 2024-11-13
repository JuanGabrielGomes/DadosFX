module tecinfo.poo {
    requires java.sql;
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.base;
    requires javafx.fxml;
    
    opens tecinfo.poo.controller to javafx.fxml;
    opens tecinfo.poo.model to javafx.base;
    exports tecinfo.poo; // substitua pelo seu pacote principal
    exports tecinfo.poo.model;
}
