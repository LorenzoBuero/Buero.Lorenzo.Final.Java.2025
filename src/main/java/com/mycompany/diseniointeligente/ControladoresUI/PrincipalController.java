package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PrincipalController {

    private ETipoCarta cartaSeleccionada = ETipoCarta.CRIATURA;
    
  
    
    
    public void irAEditorCartas(ActionEvent evento){
        try{
            FXMLLoader cargador;
            String nombreEditorCarta = "";
            switch (cartaSeleccionada){
                case CRIATURA -> nombreEditorCarta = "EditorCriatura";
                case HABILIDAD_EXTRA -> nombreEditorCarta = "EditorEvento";
                case EVENTO -> nombreEditorCarta = "EditorHabilidadExtra";
            }
            cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/" + nombreEditorCarta + ".fxml"));
           
            Parent objetivo = cargador.load();
            
            Stage escenario = (Stage)((Node)evento.getSource()).getScene().getWindow();
            
            Scene escena = new Scene(objetivo);
            
            escenario.setScene(escena);
            escenario.show();
            
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
    
    
    
    
    
    
    

