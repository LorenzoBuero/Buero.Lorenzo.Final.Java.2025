module com.mycompany.diseniointeligente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.desktop;
    requires javafx.swing;
    requires com.fasterxml.jackson.databind;

    opens com.mycompany.diseniointeligente to javafx.fxml;
    opens com.mycompany.diseniointeligente.ControladoresUI to javafx.fxml;
    opens com.mycompany.diseniointeligente.Modelos to com.fasterxml.jackson.databind;
    
    exports com.mycompany.diseniointeligente;
    exports com.mycompany.diseniointeligente.Modelos;
    exports com.mycompany.diseniointeligente.Excepciones;
    
    
}
