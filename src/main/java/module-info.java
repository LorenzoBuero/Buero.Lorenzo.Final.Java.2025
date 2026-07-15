module com.mycompany.diseniointeligente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;
    requires javafx.swing;
    

    opens com.mycompany.diseniointeligente to javafx.fxml;
    opens com.mycompany.diseniointeligente.ControladoresUI to javafx.fxml;
    exports com.mycompany.diseniointeligente;
    requires com.fasterxml.jackson.databind;
}
