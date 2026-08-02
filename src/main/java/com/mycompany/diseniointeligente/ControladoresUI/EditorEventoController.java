package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaEvento;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import com.mycompany.diseniointeligente.Modelos.Sacrificio;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

 
 // @author Lorenzo Buero
 
public class EditorEventoController extends EditorCartaController implements IEditorDeCartas {

    private CartaEvento cartaCreada = null;
    
    public TextArea efecto;
    public TextArea descripcionSacrificio;
    
    private Sacrificio logicaSacrificio;
    
    @Override
    public ETipoCarta obtenerTipoDeCarta(){
        return ETipoCarta.EVENTO;
    } 
    
    @Override
    public void intentarCrearCarta(){
        CartaEvento cartaNueva = new CartaEvento();
        
        //NUMERO DE COLECCION
        if(this.idColeccionSeleccionada()){
            int numColeccion = this.cb_idColeccion.getValue().numero;
            NumeroIdentificador numIdent = new NumeroIdentificador(cartaNueva.getCaracterRepresentativo(), numColeccion);
            cartaNueva.setNumId(numIdent);
        }
        
        //NOMBRE
        if(this.nombreCartaFueIngresado()){
                        
            String nombre = this.nombreCarta.getText();
            
            cartaNueva.setNombre(nombre);
        } 
        
        //EFECTO
        if(this.efectoFueIngresado()){
            String efectoIngresado = this.efecto.getText();
            cartaNueva.setEfecto(efectoIngresado);
        }
        
        //DESCRIPCION DE OBJETIVOS VALIDOS
        if(this.descripcionSacrificioFueIngresado()){
            String descripcionIngresado = this.efecto.getText();
            cartaNueva.setDescripcionSacrificio(descripcionIngresado);
        }
        
        if(this.sacrificioFueIngresado()){
            cartaNueva.setSacrificio(this.logicaSacrificio);
        }
        
        //IMAGEN
        if(this.imagenFueIngresada()){
            
            this.guardarImagenDeCarta(cartaNueva);
        }
        
        this.cartaCreada = cartaNueva;
    }
    
    @Override
    public Carta getCartaCreada(){
        return this.cartaCreada;
    }
    
    @Override 
    public void continuar(ActionEvent evento){
        this.intentarCrearCarta();
        super.continuar(evento);
    }
    @Override
    public void mostrarAvisoCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios){
    
    }
    
    public void abrirVentanaCondiciones(ActionEvent evento){
        try{

            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/PopupAgregarLogicaSacrificio.fxml"));
            Parent objetivo = cargador.load();

            PopupAgregarLogicaSacrificioController controlador = cargador.getController();
            //if(this.logicaSacrificio != null){
                controlador.setSacrificio(this.logicaSacrificio);
            //}
            
            Stage escenario = new Stage();
            escenario.initModality(Modality.APPLICATION_MODAL);

            Scene escena = new Scene(objetivo);

            escenario.setScene(escena);
            escenario.showAndWait();

            Sacrificio sacrificioActualizado = controlador.getSacrificio();
            if(sacrificioActualizado != null){
                this.logicaSacrificio = sacrificioActualizado;
            }
            
        } catch (IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    
    
    
    private boolean idColeccionSeleccionada(){
        Boolean retorno = true;
        
        if(this.cb_idColeccion.getValue() == null){
            retorno = false;
        }
        return retorno;
    }
    private boolean nombreCartaFueIngresado(){
        Boolean retorno = true;
        
        if(this.nombreCarta.getText() == null || this.nombreCarta.getText().isBlank()){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean imagenFueIngresada(){
        Boolean retorno = true;
    
        if(this.getImagenObtenida() == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean efectoFueIngresado(){
        Boolean retorno = true;
    
        if(this.efecto.getText() == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean descripcionSacrificioFueIngresado(){
        Boolean retorno = true;
    
        if(this.descripcionSacrificio.getText() == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean sacrificioFueIngresado(){
        Boolean retorno = true;
    
        if(this.logicaSacrificio == null){
            retorno = false;
        }
        
        return retorno;
    }
    
}
