package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EAtributoCarta;
import com.mycompany.diseniointeligente.Modelos.EAtributoCriatura;
import com.mycompany.diseniointeligente.Modelos.EAtributoEvento;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.Sacrificio;
import com.mycompany.diseniointeligente.Modelos.SacrificioStrings;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

  //@author Lorenzo Buero
 
public class PopupAgregarLogicaSacrificioController extends ControladorPopup{
    
    @FXML
    public ChoiceBox<String> cb_atributoSeleccionado;
    @FXML
    public TextField valorBuscado;
    
    private SacrificioStrings sacrificio;
    
    public void initialize(){
    
        ArrayList<String> valoresCB = new ArrayList<>();
        EAtributoCarta[] valoresEACarta = EAtributoCarta.values();
        for(EAtributoCarta atributo : valoresEACarta){
            valoresCB.add(EAtributoCarta.obtenerComoString(atributo));
        }
        
        EAtributoCriatura[] valoresEACriatura = EAtributoCriatura.values();
        for(EAtributoCriatura atributo : valoresEACriatura){
            valoresCB.add(EAtributoCriatura.obtenerComoString(atributo));
        }
        
        EAtributoEvento[] valoresEAEvento = EAtributoEvento.values();
        for(EAtributoEvento atributo : valoresEAEvento){
            valoresCB.add(EAtributoEvento.obtenerComoString(atributo));
        }
        
        this.cb_atributoSeleccionado.getItems().addAll(valoresCB);
    }
    
    
    public SacrificioStrings getSacrificio(){
        return this.sacrificio;
    }
    
    public IAtributo castearSeleccionado(){
        IAtributo retorno;
        String seleccionado = this.cb_atributoSeleccionado.getValue();
        
        if(EAtributoCarta.obtenerComoAtributo(seleccionado) != null){
            retorno = EAtributoCarta.obtenerComoAtributo(seleccionado);
            
        } else if(EAtributoCriatura.obtenerComoAtributo(seleccionado) != null){
            retorno = EAtributoCriatura.obtenerComoAtributo(seleccionado);
            
        } else if(EAtributoEvento.obtenerComoAtributo(seleccionado) != null){
            retorno = EAtributoEvento.obtenerComoAtributo(seleccionado);
        } else {
            retorno = null;
        }
        
        return retorno;
    }
    
    private void calcularSacrificio(){
        if(this.datosValidos()){
            IAtributo atributo = this.castearSeleccionado();
            String valorIngresado = this.valorBuscado.getText();
            
            SacrificioStrings sacrificioIngresado = new SacrificioStrings(atributo, valorIngresado);
            this.sacrificio = sacrificioIngresado;
        }
    }
    
    
    private Boolean datosValidos(){
        IAtributo atributoSeleccionado = this.castearSeleccionado();
        String valorIngresado = this.valorBuscado.getText();
        
        Boolean retorno = !(atributoSeleccionado == null || valorIngresado.isBlank());
        
        return retorno;
    }
    
    
    @Override
    public void confirmar(ActionEvent evento){
        
        
    }
}
