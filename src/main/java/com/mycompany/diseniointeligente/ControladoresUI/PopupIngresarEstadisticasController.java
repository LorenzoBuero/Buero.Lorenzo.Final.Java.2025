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
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PopupIngresarEstadisticasController extends ControladorPopupDeLista{

    @FXML
    public TextField comida;
    @FXML
    public TextField ataque;
    @FXML
    public TextField vida;

    
    private Estadisticas estadisticas;
    private Boolean esPrimerEstadistica = true;

    public void initialize(){
        this.comida.setDisable(true);
    }
    
    
    public void setEstadisticas(Estadisticas stats){
        if(stats != null){
            this.estadisticas = stats;
            this.actualizarDatosAlAgregarEstadisticas();
        }
    }
    
    public Estadisticas getEstadisticas(){
        if(this.getGuardarEstosDatos()){
            return this.estadisticas;
        } else {
            return null;
        }
    }
    
    
    
    @Override
    public void agregarALista(ActionEvent evento){
        
        if(!this.verificarDatosValidos()){return;}
        
        int atk = Integer.parseInt(this.ataque.getText());
        int hp = Integer.parseInt(this.vida.getText());
        AtaqueVida ataqueVida = new AtaqueVida(atk, hp);
        
        if(!this.esPrimerEstadistica){
           int cantidadComida = Integer.parseInt(this.comida.getText());
           
           CosteDeCrecimiento coste = new CosteDeCrecimiento(cantidadComida);
           
           this.estadisticas.agregarFase(ataqueVida, coste);
        
        } else {
            this.estadisticas = new Estadisticas(ataqueVida);
        }
        
        this.actualizarDatosAlAgregarEstadisticas();
        this.limpiarTextBoxes();
    }
    
    @Override
    public void eliminarSeleccionado(ActionEvent evento){}
    
    
    private void actualizarDatosAlAgregarEstadisticas(){
        this.esPrimerEstadistica = false;
        this.comida.setDisable(false);
        this.actualizarListaVisible();
    }
    
    @Override
    public void actualizarListaVisible(){
        ArrayList<String> listaStats = new ArrayList<>();

        for(int i = 0; i < this.estadisticas.getCantidadEstadisticas(); i++){
        
            listaStats.add(estadisticas.unaEstadisticaToString(i));
        }
        this.listaVisible.getItems().setAll(listaStats);
    }
    
    
    private void limpiarTextBoxes(){
        this.ataque.setText("");
        this.vida.setText("");
        this.comida.setText("");
    }
    
    private Boolean verificarDatosValidos(){
        Boolean retorno = true;
        
        
        if(!this.esPrimerEstadistica && !this.esEnteroValido(comida.getText())){
            retorno = false;
            System.out.println("A");
        }
        if(!this.esEnteroValido(this.ataque.getText())){
            retorno = false;
            System.out.println("B");
        }
        if(!this.esEnteroValido(this.vida.getText())){
            retorno = false;
            System.out.println("C");
        }

        return retorno;
    }
    
    private Boolean esEnteroValido(String texto){
        Boolean retorno = !texto.isBlank();
        
        try{
            int _ = Integer.parseInt(texto);

        } catch(NumberFormatException ex){
            retorno = false;
        }
        return retorno;
    } 
    
}
