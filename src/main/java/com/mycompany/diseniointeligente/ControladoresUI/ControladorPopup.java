/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseniointeligente.ControladoresUI;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author pirulo
 */
public abstract class ControladorPopup {
    
    
    private Boolean guardarEstosDatos = true;
    
    
    public Boolean getGuardarEstosDatos(){
        return guardarEstosDatos;
    }
    public void setGuardarEstosDatos(Boolean queHacer){
        this.guardarEstosDatos = queHacer;
    }
    
    public void cancelar(ActionEvent evento){
        this.setGuardarEstosDatos(false);
        cerrarVentana(evento); 
    }
    public void confirmar(ActionEvent evento){
        this.setGuardarEstosDatos(true);
        cerrarVentana(evento); 
    }
    private void cerrarVentana(ActionEvent evento){
        Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
        escenario.close();
    }
}
