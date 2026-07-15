package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.HabilidadEspecial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PopupAgregarHabilidadEspecialController extends ControladorPopup{

    @FXML
    public TextField nombreHabilidad;
    @FXML
    public TextArea descripcionHabilidad;
    
    HabilidadEspecial habilidadEspecial;
    
    public void setHabilidad(HabilidadEspecial habilidad){
        this.habilidadEspecial = habilidad;
    }
    
    public HabilidadEspecial getHabilidad(){
        return habilidadEspecial;
    }
    
    private Boolean datosValidos(){
        String nombreTB = this.nombreHabilidad.getText();
        String descripcionTB = this.descripcionHabilidad.getText();
        
        Boolean retorno = !(nombreTB.isBlank() || descripcionTB.isBlank());
        
        return retorno;
    }
    
    private void asignarValores(){
        if(datosValidos()){
            String nombre = nombreHabilidad.getText();
            String descripcion = descripcionHabilidad.getText();

            this.habilidadEspecial = new HabilidadEspecial(nombre, descripcion);
        }
    }
    
    @Override
    public void confirmar(ActionEvent evento){
        if(datosValidos()){
            this.asignarValores();
            super.confirmar(evento);
        }
    }
}
