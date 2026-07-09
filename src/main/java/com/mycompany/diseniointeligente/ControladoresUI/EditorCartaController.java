package com.mycompany.diseniointeligente.ControladoresUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lorenzo Buero
 */
public abstract class EditorCartaController {
    
    public final void volver(ActionEvent evento){
       
        try{
            FXMLLoader cargador;
            
            cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/Principal.fxml"));
           
           
            Parent objetivo = cargador.load();
            
            
            Stage escenario = (Stage)((Node)evento.getSource()).getScene().getWindow();
            
            Scene escena = new Scene(objetivo);
            
            escenario.setScene(escena);
            escenario.show();
        
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    public final void continuar(ActionEvent evento){
        crearCarta();
        volver(evento);
    }
    
    public abstract void crearCarta();
}
