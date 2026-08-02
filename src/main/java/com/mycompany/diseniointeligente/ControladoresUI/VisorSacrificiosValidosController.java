package com.mycompany.diseniointeligente.ControladoresUI;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.diseniointeligente.Modelos.ISacrificable;
import com.mycompany.diseniointeligente.Modelos.Sacrificio;
import com.mycompany.diseniointeligente.Modelos.Carta;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class VisorSacrificiosValidosController {   
    
    @FXML
    Label  lbl_cartasValidas;
    
    
    ArrayList<ISacrificable> sacrificablesValidas;
    Sacrificio condicionesSacrificio;
    
    public void ingresarDatos(List<Carta> cartas, Sacrificio condiciones){
        this.condicionesSacrificio = condiciones;
        this.sacrificablesValidas = this.buscarSacrificablesValidos(cartas);
        this.lbl_cartasValidas.setText(this.obtenerSacrificablesComoTexto());
    }
    
    private ArrayList<ISacrificable> buscarSacrificablesValidos(List<Carta> cartas){
        
        if(this.condicionesSacrificio == null){return null;}
        
        ArrayList<ISacrificable> sacrificables = new ArrayList<>();
        
        for(Carta carta : cartas){
            if(carta instanceof ISacrificable sacrificable){
                sacrificables.add(sacrificable);
            }  
        }
        
        ArrayList<ISacrificable> sacrificablesRetornadas;
        sacrificablesRetornadas = this.condicionesSacrificio.obtenerSacrificiosValidos(sacrificables);

        
        return sacrificablesRetornadas;
    }
    
    private String obtenerSacrificablesComoTexto(){
        if(this.sacrificablesValidas == null || this.sacrificablesValidas.isEmpty()){
            return "No hay objetivos validos";
        }
        
        String retorno = "";
        
        for(ISacrificable sacrificable : this.sacrificablesValidas){
            if(sacrificable instanceof Carta carta){
                retorno += "Nombre: " + carta.getNombre() + "  |  ";
                retorno += "ID: " + carta.getNumId().getNumeroIdentificador();
                retorno += "\n";
            }
        }
            
        
        return retorno;
    }
    
    public void cerrar(ActionEvent evento){
        Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
        escenario.close();
    }
    
}
