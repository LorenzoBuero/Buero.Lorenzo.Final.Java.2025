module com.mycompany.diseniointeligente {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.diseniointeligente to javafx.fxml;
    exports com.mycompany.diseniointeligente;
}
