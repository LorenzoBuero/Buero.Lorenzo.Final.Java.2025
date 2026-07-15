package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaHabilidadExtra;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class EditorHabilidadExtraController extends EditorCartaController implements IEditorDeCartas {
    
    @FXML
    public TextArea efecto;
    @FXML
    public TextArea descripcionObjetivos;
    
    
    private Carta cartaCreada = null;
    

    @Override
    public void intentarCrearCarta(){
        CartaHabilidadExtra cartaNueva = new CartaHabilidadExtra();
        
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
        if(this.descripcionObjetivosFueIngresado()){
            String descripcionIngresado = this.efecto.getText();
            cartaNueva.setDescripcionObjetivos(descripcionIngresado);
        }
        
        //IMAGEN
        if(this.imagenFueIngresada()){
            
            this.guardarImagenDeCarta(cartaNueva);
        }
        
        this.cartaCreada = cartaNueva;
    }

    @Override
    public Carta getCartaCreada() {
        return this.cartaCreada;    
    }

    @Override
    public ETipoCarta obtenerTipoDeCarta() {
        return ETipoCarta.HABILIDAD_EXTRA;
    }
    
    @Override 
    public void continuar(ActionEvent evento){
        this.intentarCrearCarta();
        super.continuar(evento);
    }
    @Override
    public void mostrarAvisoCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios){
    
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
    private boolean descripcionObjetivosFueIngresado(){
        Boolean retorno = true;
    
        if(this.descripcionObjetivos.getText() == null){
            retorno = false;
        }
        
        return retorno;
    }
}
