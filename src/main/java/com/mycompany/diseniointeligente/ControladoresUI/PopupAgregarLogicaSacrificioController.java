package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EAtributoCarta;
import com.mycompany.diseniointeligente.Modelos.EAtributoCriatura;
import com.mycompany.diseniointeligente.Modelos.EAtributoEvento;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.Sacrificio;
import com.mycompany.diseniointeligente.Modelos.EAtributoEstadisticas;
import com.mycompany.diseniointeligente.Modelos.EOperacionSacrificio;
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
    @FXML
    public ChoiceBox<EOperacionSacrificio> cb_comparacion;
    @FXML
    public ChoiceBox<String> cb_estadisticasAtributos;
    
    
    private Sacrificio sacrificio;
    
    
    
    public void initialize(){
    
        ArrayList<String> valoresCBAtrs = new ArrayList<>();
        EAtributoCarta[] valoresEACarta = EAtributoCarta.values();
        for(EAtributoCarta atributo : valoresEACarta){
            valoresCBAtrs.add(atributo.obtenerComoString());
        }
        
        EAtributoCriatura[] valoresEACriatura = EAtributoCriatura.values();
        for(EAtributoCriatura atributo : valoresEACriatura){
            valoresCBAtrs.add(atributo.obtenerComoString());
        }
        
        EAtributoEvento[] valoresEAEvento = EAtributoEvento.values();
        for(EAtributoEvento atributo : valoresEAEvento){
            valoresCBAtrs.add(atributo.obtenerComoString());
        }
        
        this.cb_atributoSeleccionado.getItems().addAll(valoresCBAtrs);
        
        this.cb_atributoSeleccionado.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, valorAnterior, valorNuevo) -> {
                    
                    String seleccionado = valorNuevo;
        
                    IAtributo atributoSeleccionado = EAtributoCarta.obtenerComoAtributo(seleccionado);
        
                    if(atributoSeleccionado == null){
                        atributoSeleccionado = EAtributoCriatura.obtenerComoAtributo(seleccionado);

                        if(atributoSeleccionado == null){
                            atributoSeleccionado = EAtributoEvento.obtenerComoAtributo(seleccionado);
                        }
                    }
                    
                    this.actualizarElementosDeUI(atributoSeleccionado);
                
                });
        
        ArrayList<String> valoresCBStats = new ArrayList<>();
        
        EAtributoEstadisticas[] valoresEAEstats = EAtributoEstadisticas.values();
        for(EAtributoEstadisticas atributo : valoresEAEstats){
            valoresCBStats.add(atributo.obtenerComoString());
        }
        
        this.cb_estadisticasAtributos.getItems().addAll(valoresCBStats);
        
        this.cb_comparacion.getItems().addAll(EOperacionSacrificio.values());
    }
    
    
    public Sacrificio getSacrificio(){
        return this.sacrificio;
    }
    
    public void setSacrificio(Sacrificio sacrificio){
        this.sacrificio = sacrificio;
      
        if(sacrificio == null){
            this.actualizarElementosDeUI(null);
        } else {
            this.valorBuscado.setText(sacrificio.getValorBuscado().toString());
            this.actualizarElementosDeUI(sacrificio.getAtributoDeCarta());
        }
    }
    
    private void actualizarElementosDeUI(IAtributo atributoIngresado){
        this.cb_comparacion.setValue(EOperacionSacrificio.IGUALDAD);
        
        this.cb_estadisticasAtributos.setDisable(true);
        this.cb_comparacion.setDisable(true);
        
        if(atributoIngresado == null){
            this.cb_atributoSeleccionado.setValue(null);
            
        } else if(atributoIngresado instanceof EAtributoEstadisticas atributo){
            
            this.cb_atributoSeleccionado.setValue(EAtributoCriatura.ESTADISTICAS.obtenerComoString());
            this.cb_estadisticasAtributos.setValue(atributo.obtenerComoString());
            
            this.cb_estadisticasAtributos.setDisable(false);
            this.cb_comparacion.setDisable(false);
            
        } else{
            this.cb_atributoSeleccionado.setValue(atributoIngresado.obtenerComoString()); 
            
            if(atributoIngresado instanceof EAtributoCriatura atributo && atributo == EAtributoCriatura.ESTADISTICAS){
                this.cb_estadisticasAtributos.setDisable(false);
                this.cb_comparacion.setDisable(false);
            }   
        }
    }
    
    
    private IAtributo castearIAtributoSeleccionado(){
        IAtributo retorno;
        String seleccionado = this.cb_atributoSeleccionado.getValue();
        
        
        if(seleccionado.equals(EAtributoCriatura.ESTADISTICAS.obtenerComoString())){
            seleccionado = this.cb_estadisticasAtributos.getValue();
            retorno = EAtributoEstadisticas.obtenerComoAtributo(seleccionado);

        } else if(EAtributoCarta.obtenerComoAtributo(seleccionado) != null){
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
    
    private boolean calcularSacrificio(){
        boolean retorno = false;
        if(this.datosValidos()){
            IAtributo atributo = this.castearIAtributoSeleccionado();
            
            String valorIngresado = this.valorBuscado.getText();
            
            Object valorAlmacenado = null;
            
            if(atributo == EAtributoCriatura.ESTADISTICAS){
                try{
                    valorAlmacenado = Integer.valueOf(valorIngresado);
                    retorno = true;
                    
                } catch(NumberFormatException _){
                    retorno = false;
                }
            } else {
                retorno = true;
                valorAlmacenado = valorIngresado;
            }
            
            
            EOperacionSacrificio operacion = this.cb_comparacion.getValue();
            Sacrificio sacrificioIngresado = new Sacrificio(operacion, atributo, valorAlmacenado);
            
            this.setSacrificio(sacrificioIngresado);
            
            
        }
        return retorno;
    }
    
    
    private Boolean datosValidos(){
        Boolean retorno = false;
        if(datosRellenados()){
            
            IAtributo atributoSeleccionado = this.castearIAtributoSeleccionado();
            String valorIngresado = this.valorBuscado.getText();

            if(atributoSeleccionado == EAtributoCriatura.ESTADISTICAS){
                try{
                    Integer.valueOf(valorIngresado);
                    retorno = true;
                } catch(NumberFormatException _){
                    retorno = false;
                }
            }
            else{
                retorno = !(atributoSeleccionado == null || valorIngresado.isBlank());
            }
        }
        
        
        return retorno;
    }
    
    
    private boolean datosVacios(){
        boolean retorno = true;
        
        if(this.cb_atributoSeleccionado.getValue() != null){
            retorno = false;
        }
        if(this.cb_comparacion.getValue() != null){
            retorno = false;
        }
        if(this.cb_estadisticasAtributos.getValue() != null){
            retorno = false;
        }
        if(!this.valorBuscado.getText().isBlank()){
            retorno = false;
        }
        
        return retorno;
    }
    
    private boolean datosRellenados(){
        boolean retorno = true;
        
        if(this.cb_atributoSeleccionado.getValue() == null){
            retorno = false;
        }else if( this.cb_atributoSeleccionado.getValue().equals(EAtributoCriatura.ESTADISTICAS.obtenerComoString()) 
                && this.cb_estadisticasAtributos.getValue() == null){
            retorno = false;
        }
        if(this.cb_comparacion.getValue() == null){
            retorno = false;
        } 
        if(this.valorBuscado.getText().isBlank()){
            retorno = false;
        }
        
        return retorno;
    }
    
    
    public void vaciarDatos(ActionEvent evento){
        this.cb_atributoSeleccionado.setValue(null);
        this.cb_comparacion.setValue(null);
        this.cb_estadisticasAtributos.setValue(null);
        this.cb_estadisticasAtributos.setDisable(true);
        this.valorBuscado.setText("");
        this.sacrificio = null;
    }
    
    @Override
    public void confirmar(ActionEvent evento){
        if(calcularSacrificio()){
            super.confirmar(evento);
        } else if(datosVacios()){
            super.confirmar(evento);
        }
        
    }
}
