package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EColecciones;
import com.mycompany.diseniointeligente.Excepciones.ParametroObligatorioEsNullException;
import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaHabilidadExtra;
import com.mycompany.diseniointeligente.Modelos.EAtributoCarta;
import com.mycompany.diseniointeligente.Modelos.EAtributoHabilidadExtra;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javax.imageio.ImageIO;


/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class EditorHabilidadExtraController extends EditorCartaController implements IEditorDeCartas {
    
    @FXML
    TextArea efecto;
    @FXML
    TextArea descripcionObjetivos;
    @FXML
    Label lbl_errorNombre;
    @FXML
    Label lbl_errorColeccion;
    @FXML
    Label lbl_errorEfecto;
    
    private Carta cartaAGuardar = null;
    
    @Override
    public ETipoCarta obtenerTipoDeCarta() {
        return ETipoCarta.HABILIDAD_EXTRA;
    }
    
    @Override
    public Carta getCartaCreada() {
        return this.cartaAGuardar;    
    }
    @Override
    public void ingresarCartaAEditar(Carta carta) {
       if(carta instanceof CartaHabilidadExtra CHE){
            this.nombreCarta.setText(CHE.getNombre());
            this.cb_idColeccion.setValue(EColecciones.valueOf(CHE.getNumId().getNumeroColeccion()));
            this.efecto.setText(CHE.getEfecto());
            
            this.descripcionObjetivos.setText(CHE.getDescripcionObjetivos());
            this.setURLImagen(CHE.getUrlImagen());
            this.reasignarImagen();
            
        } else {
            throw new IllegalArgumentException("La carta debe ser una CartaHabilidadExtra, no una de otro tipo.");
        }
    }
    
    private void guardarCarta(CartaHabilidadExtra cartaHabilidadExtra){
        this.cartaAGuardar = cartaHabilidadExtra;
    }

    @Override
    public void intentarCrearCarta(){
        CartaHabilidadExtra cartaNueva = null;
        
        try { 
            String nombreIngresado = this.nombreCarta.getText().trim();
            
            
            NumeroIdentificador numId = this.formarNumId();
            
            cartaNueva = new CartaHabilidadExtra(
                    nombreIngresado, numId, this.efecto.getText().trim());
        
        } catch (ParametroObligatorioEsNullException ex) {
            
            ArrayList<IAtributo>camposFaltantes = ex.getCamposRequeridos();
            this.mostrarAvisosCamposObligatoriosVacios(camposFaltantes);
            
        } catch(Exception ex){

            System.out.println(ex.getMessage());
        }
        
        if(cartaNueva != null){

            //IMAGEN
            if(this.getImagenObtenida() != null){
                this.guardarImagenDeCarta(cartaNueva);
            }            
            if(this.getURLImagen() != null && !(this.getURLImagen().isBlank())){
                cartaNueva.setUrlImagen(this.getURLImagen());
            }
            
            //DESCRIPCION DE OBJETIVOS VALIDOS
            String descripcion = this.descripcionObjetivos.getText();
            if(descripcion != null && !descripcion.isEmpty()){  
                cartaNueva.setDescripcionObjetivos(descripcion);
            }

            this.guardarCarta(cartaNueva);
        }
        
        
    }

    
    
    @Override 
    public void continuar(ActionEvent evento){
        this.intentarCrearCarta();
        super.continuar(evento);
    }
    @Override
    public void mostrarAvisosCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios){
        for(IAtributo faltante : atributosObligatorios){
                switch(faltante){
                    case EAtributoCarta.NOMBRE -> this.lbl_errorNombre.setVisible(true);
                    case EAtributoCarta.NUM_IDENT -> this.lbl_errorColeccion.setVisible(true);
                    case EAtributoHabilidadExtra.EFECTO -> this.lbl_errorEfecto.setVisible(true);
                    default -> System.out.println(faltante);
                }
            }
    }

    @Override
    public void ocultarAvisosCamposObligatoriosVacios() {
        this.lbl_errorNombre.setVisible(false);
        this.lbl_errorColeccion.setVisible(false);
        this.lbl_errorEfecto.setVisible(false);    
    }
}
