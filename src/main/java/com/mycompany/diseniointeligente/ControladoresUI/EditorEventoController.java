package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaEvento;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javax.imageio.ImageIO;

 
 // @author Lorenzo Buero
 
public class EditorEventoController extends EditorCartaController implements IEditorDeCartas {

    private CartaEvento cartaCreada = null;
    
    public TextArea efecto;
    public TextArea descripcionSacrificio;
    
    
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
    
}
