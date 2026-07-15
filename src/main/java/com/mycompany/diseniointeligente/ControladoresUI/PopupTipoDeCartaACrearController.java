package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PopupTipoDeCartaACrearController{

    @FXML
    private ChoiceBox<String> selectorDeTipo;
    private ETipoCarta tipoSeleccionado = null;
    
    public void initialize(){
        
        
        ETipoCarta[] tiposEnEnum = ETipoCarta.values();
        String[] tiposEnString = new String[tiposEnEnum.length];
        for(int i = 0; i < tiposEnEnum.length; i++){
            tiposEnString[i] = ETipoCarta.aString(tiposEnEnum[i]);
        }
        
        selectorDeTipo.getItems().addAll(tiposEnString);
    }
    
    public ETipoCarta getTipoSeleccionado(){
        return tipoSeleccionado;
    }
    
    private Boolean asignarTipoSeleccionado(){
         
        if(this.selectorDeTipo.getValue() == null){
            return false;
        } 
        else {
            String valor = this.selectorDeTipo.getValue();
            this.tipoSeleccionado = ETipoCarta.aETipoCarta(valor);
            return true;
        } 
    }
        
        
    
    public void continuar(ActionEvent evento){
        
        if(asignarTipoSeleccionado()){
            Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
            escenario.close();
        }
    }
    
    public void cancelar(ActionEvent evento){
        tipoSeleccionado = null;
        Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
        escenario.close();
    }
   
   
   
}
