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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author pirulo
 */
public class Prueb2Controller {

    
    
    @FXML
    private Rectangle rectangulo;
    private double x;
    private double y;
    
    final int velocidad = 10;
    
    public void arriba(ActionEvent e){
        rectangulo.setY(y-=velocidad);
    }
    
    
    public void abajo(ActionEvent e){
        rectangulo.setY(y+=velocidad);
    }
    
    
    public void izquierda(ActionEvent e){
        rectangulo.setX(x-=velocidad);
    }
    
    
    public void derecha(ActionEvent e){
        rectangulo.setX(x+=velocidad);
    }
    
    public void teletransportar(ActionEvent e){
        try{
        
            Parent objetivo = FXMLLoader.load(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/prueb.fxml"));
            Stage escenario = (Stage)((Node)e.getSource()).getScene().getWindow();
            
            Scene escena = new Scene(objetivo);
            
            escenario.setScene(escena);
            escenario.show();
        
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    public void cargarPosicion(double x, double y){
        this.x = x;
        this.y = y;
        this.rectangulo.setX(x);
        this.rectangulo.setY(y);
    }        
}
