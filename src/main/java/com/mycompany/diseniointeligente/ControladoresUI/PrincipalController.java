package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PrincipalController {

    
    private ETipoCarta cartaSeleccionada = ETipoCarta.CRIATURA;
    
    @FXML
    private Label descripcionCarta;
    @FXML
    private ListView<Carta> scrollerCartas;
    
    private Carta cartita;
    
    
    public void mostrarSelectorFiltros(ActionEvent evento){}
    
    
    public void irAEditorCartas(ActionEvent evento){
        try{
            ETipoCarta tipoACrear = this.preguntarTipoCartaACrear();
            
            Carta cartaCreada = mostrarEditorDeCartas(tipoACrear);
            if(cartaCreada != null){
                System.out.println(cartaCreada.getNombre());
            }
            
            
        } catch (Exception ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    private ETipoCarta preguntarTipoCartaACrear() throws IOException{
    
        ETipoCarta tipoCarta = null;

        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/PopupTipoDeCartaACrear.fxml"));
        Parent objetivo = cargador.load();

        PopupTipoDeCartaACrearController controlador = cargador.getController();

        Stage escenario = new Stage();

        Scene escena = new Scene(objetivo);

        escenario.setScene(escena);
        escenario.showAndWait();

        tipoCarta = controlador.getTipoSeleccionado();
        
        return tipoCarta;

    }
    
    private Carta mostrarEditorDeCartas(ETipoCarta tipoDeCarta) throws IOException{
        
        String nombreEditor;
        
        switch(tipoDeCarta){
            case CRIATURA: nombreEditor = "EditorCriatura"; break;
            case EVENTO: nombreEditor = "EditorEvento"; break;
            case HABILIDAD_EXTRA: nombreEditor = "EditorHabilidadExtra"; break;
            case null: return null;
            default: return null;
        }
        

        FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/" + nombreEditor + ".fxml"));
        Parent objetivo = cargador.load();

        IEditorDeCartas controlador = cargador.getController();

        Stage escenario = new Stage();

        Scene escena = new Scene(objetivo);

        escenario.setScene(escena);
        escenario.showAndWait();

        Carta cartaRetornada = controlador.getCartaCreada();
        
        
        return cartaRetornada;
    }

    
}
