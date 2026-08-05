package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.GestionDeDatos.ConfigFiltroYOrdenamiento;
import com.mycompany.diseniointeligente.GestionDeDatos.OrdenamientoCartas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PopupFiltrosController extends ControladorPopup{ 
    
    @FXML
    CheckBox chb_habilitarCriaturas;
    @FXML
    CheckBox chb_habilitarEventos;
    @FXML
    CheckBox chb_habilitarHabilidadesExtra;
    @FXML
    CheckBox chb_habilitarSoloSinImagen;
    @FXML
    ChoiceBox<OrdenamientoCartas> cb_ordenarPor;
    @FXML 
    CheckBox chb_ordenarMayorAMenor;
    
    //public ConfigFiltroYOrdenamiento configFiltrosYOrdenado;
    
    
    public void initialize(){
        this.cb_ordenarPor.getItems().addAll(OrdenamientoCartas.values());
    }
    
    private void valoresDefault(){
        this.reiniciarFiltros();
        
        this.cb_ordenarPor.setValue(OrdenamientoCartas.NOMBRE);
        this.chb_ordenarMayorAMenor.setSelected(true);
    }
    
    public void reiniciarFiltros(ActionEvent evento){
        this.reiniciarFiltros();
    }
    
    public void reiniciarFiltros(){
        this.chb_habilitarCriaturas.setSelected(true);
        this.chb_habilitarEventos.setSelected(true);
        this.chb_habilitarHabilidadesExtra.setSelected(true);
        this.chb_habilitarSoloSinImagen.setSelected(false);
    }
    
    public void establecerFiltros(ConfigFiltroYOrdenamiento config){
        if(config == null){
            this.valoresDefault();
        }        
        else{
            this.chb_habilitarCriaturas.setSelected(config.mostrarCriaturas);
            this.chb_habilitarEventos.setSelected(config.mostrarEventos);
            this.chb_habilitarHabilidadesExtra.setSelected(config.mostrarHabilidadesExtra);
            this.chb_habilitarSoloSinImagen.setSelected(config.mostrarSinImagen);

            this.cb_ordenarPor.setValue(config.ordenamiento);
            this.chb_ordenarMayorAMenor.setSelected(config.ordenarMayorAMenor);
        }
    }
    
    
    public ConfigFiltroYOrdenamiento obtenerFiltros(){    
        ConfigFiltroYOrdenamiento retorno;
        
        boolean hC = this.chb_habilitarCriaturas.isSelected();
        boolean hE = this.chb_habilitarEventos.isSelected();
        boolean hH = this.chb_habilitarHabilidadesExtra.isSelected();
        boolean hSImg = this.chb_habilitarSoloSinImagen.isSelected();
        
        OrdenamientoCartas orden = this.cb_ordenarPor.getValue();
        boolean mayorAMenor = this.chb_ordenarMayorAMenor.isSelected();
        
        retorno = new ConfigFiltroYOrdenamiento(hC, hE, hH, hSImg, orden, mayorAMenor);
        
        return retorno;
    }

}
