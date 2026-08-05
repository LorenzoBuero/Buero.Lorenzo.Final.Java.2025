/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.AtaqueVida;
import com.mycompany.diseniointeligente.Modelos.CosteDeCrecimiento;
import com.mycompany.diseniointeligente.Modelos.EDieta;
import com.mycompany.diseniointeligente.Modelos.Estadisticas;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author pirulo
 */
public abstract class ControladorPopupDeLista extends ControladorPopup{
    
    
    @FXML
    public Button btn_eliminarSeleccionado;
    @FXML 
    public ListView<String> listaVisible;
    
    private Integer idSeleccionado = null;
    public Integer getIdSeleccionado(){
        return this.idSeleccionado;
    }
    
    public void initialize(){
        this.listaVisible.getSelectionModel().selectedItemProperty().addListener((
                observable, anteriorSeleccionado, seleccionadoActualmente) -> {
            //cartaSeleccionadaActualmente.
            if(seleccionadoActualmente != null){
                this.idSeleccionado = this.listaVisible.getFocusModel().getFocusedIndex();
            }
            
        });
    }
    
    
    public abstract void agregarALista(ActionEvent evento);
    
    public abstract void eliminarSeleccionado(ActionEvent evento);
    
    public abstract void actualizarListaVisible();

    
}
