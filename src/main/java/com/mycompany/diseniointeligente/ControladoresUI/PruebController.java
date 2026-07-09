/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.diseniointeligente.ControladoresUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pirulo
 */
public class PruebController {

    @FXML
    private Circle circulo;
    private double x;
    private double y;

    //private Stage stage;
    //private Scene scene;
    //private Parent root

    
    final int velocidad = 10;
    
    public void arriba(ActionEvent e){
        circulo.setCenterY(y-=velocidad);
    }
    
    
    public void abajo(ActionEvent e){
        circulo.setCenterY(y+=velocidad);
    }
    
    
    public void izquierda(ActionEvent e){
        circulo.setCenterX(x-=velocidad);
    }
    
    
    public void derecha(ActionEvent e){
        circulo.setCenterX(x+=velocidad);
    }
    
    public void teletransportar(ActionEvent e){
        try{
        
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/prueb2.fxml"));
            Parent objetivo = cargador.load();
            
            Prueb2Controller p2Cont = cargador.getController();
            
            p2Cont.cargarPosicion(x, y);
            
            
            Stage escenario = (Stage)((Node)e.getSource()).getScene().getWindow();
            
            Scene escena = new Scene(objetivo);
            
            escenario.setScene(escena);
            escenario.show();
        
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
}
