package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EHabilidadBasica;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PopupAgregarHabilidadesBasicasController extends ControladorPopupDeLista {  
    
    @FXML
    public ChoiceBox<String> cb_habilidadesBasicas;

    private ArrayList<EHabilidadBasica> habilidades;
    
    
    
    public void initialize(){
        this.habilidades = new ArrayList<>();
        
        EHabilidadBasica[] tiposEnEnum = EHabilidadBasica.values();
        String[] tiposEnString = new String[tiposEnEnum.length];
        for(int i = 0; i < tiposEnEnum.length; i++){
            tiposEnString[i] = EHabilidadBasica.aString(tiposEnEnum[i]);
        }
        
        cb_habilidadesBasicas.getItems().addAll(tiposEnString);
        
    }

    public ArrayList<EHabilidadBasica> getHabilidades(){
        if(this.getGuardarEstosDatos()){
            return this.habilidades;
        }
        else{
            return null;
        }
    }
    
    public void setHabilidades(ArrayList<EHabilidadBasica> habilidades){
        if(habilidades != null){
            this.habilidades = habilidades;
            this.actualizarListaVisible();
        }
    }
    
    
    
    private Boolean asignarTipoSeleccionado(){
         
        if(this.cb_habilidadesBasicas.getValue() == null){
            return false;
        } 
        else {
            String valor = this.cb_habilidadesBasicas.getValue();
            
            EHabilidadBasica habilidadSeleccionada = EHabilidadBasica.aEHabilidadBasica(valor);
            this.habilidades.add(habilidadSeleccionada);
            return true;
        } 
    }
    
    @Override
    public void agregarALista(ActionEvent evento){
        if(this.asignarTipoSeleccionado()){
            this.actualizarListaVisible();
        }
    }
    
    @Override
    public void eliminarSeleccionado(ActionEvent evento){}
    
    @Override
    public void actualizarListaVisible(){
        
        ArrayList<String> listaHabilidades = new ArrayList<>();
        
        for(int i = 0; i < this.habilidades.size(); i++){
            listaHabilidades.add(EHabilidadBasica.aString(habilidades.get(i)));
        }
        this.listaVisible.getItems().setAll(listaHabilidades);
    }
}
