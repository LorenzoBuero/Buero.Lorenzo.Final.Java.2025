package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EColecciones;
import com.mycompany.diseniointeligente.Excepciones.ParametroObligatorioEsNullException;
import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaEvento;
import com.mycompany.diseniointeligente.Modelos.EAtributoCarta;
import com.mycompany.diseniointeligente.Modelos.EAtributoEvento;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import com.mycompany.diseniointeligente.Modelos.Sacrificio;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

 
 // @author Lorenzo Buero
 
public class EditorEventoController extends EditorCartaController implements IEditorDeCartas {

    private CartaEvento cartaAGuardar = null;
    
    @FXML
    TextArea efecto;
    @FXML
    TextArea descripcionSacrificio;
    @FXML
    Label lbl_errorNombre;
    @FXML
    Label lbl_errorColeccion;
    @FXML
    Label lbl_errorEfecto;
    
    
    
    private Sacrificio logicaSacrificio;
    
    @Override
    public ETipoCarta obtenerTipoDeCarta(){
        return ETipoCarta.EVENTO;
    } 
    
    @Override
    public void intentarCrearCarta(){
        
        CartaEvento cartaNueva = null;
        
        try { 
            String nombreIngresado = this.nombreCarta.getText().trim();
            
            NumeroIdentificador numId = this.formarNumId();
            
            cartaNueva = new CartaEvento(
                    nombreIngresado, numId, this.efecto.getText().trim());
        
        } catch (ParametroObligatorioEsNullException ex) {
            
            ArrayList<IAtributo>camposFaltantes = ex.getCamposRequeridos();
            this.mostrarAvisosCamposObligatoriosVacios(camposFaltantes);
            
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        if(cartaNueva != null){

            String descripcion = this.descripcionSacrificio.getText();
            //DESCRIPCION DE OBJETIVOS VALIDOS
            if(descripcion != null){
                String descripcionIngresado = this.efecto.getText();
                cartaNueva.setDescripcionSacrificio(descripcionIngresado);
            }
            
            cartaNueva.setSacrificio(this.logicaSacrificio);

            //IMAGEN
            if(this.getImagenObtenida() != null){
                this.guardarImagenDeCarta(cartaNueva);
            }            
            if(this.getURLImagen() != null && !(this.getURLImagen().isBlank())){
                cartaNueva.setUrlImagen(this.getURLImagen());
            }

            this.guardarCarta(cartaNueva);
        }
    }
    
    @Override
    public Carta getCartaCreada(){
        return this.cartaAGuardar;
    }
    
    @Override
    public void ingresarCartaAEditar(Carta carta) {
       if(carta instanceof CartaEvento CE){
            this.nombreCarta.setText(CE.getNombre());
            this.cb_idColeccion.setValue(EColecciones.valueOf(CE.getNumId().getNumeroColeccion()));
            this.efecto.setText(CE.getEfecto());
            
            this.logicaSacrificio = CE.getSacrificio();
            this.descripcionSacrificio.setText(CE.getDescripcionSacrificio());
            this.setURLImagen(CE.getUrlImagen());
            
            this.reasignarImagen();
            
        } else {
            throw new IllegalArgumentException("La carta debe ser una CartaEvento, no una de otro tipo.");
        }
    }
    
    private void guardarCarta(CartaEvento cartaEvento){
        this.cartaAGuardar = cartaEvento;
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
                case EAtributoEvento.EFECTO -> this.lbl_errorEfecto.setVisible(true);
                default -> System.out.println(faltante);
            }
        }
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
            
            this.logicaSacrificio = sacrificioActualizado;
            
            
        } catch (IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }

    @Override
    public void ocultarAvisosCamposObligatoriosVacios() {
        this.lbl_errorNombre.setVisible(false);
        this.lbl_errorColeccion.setVisible(false);
        this.lbl_errorEfecto.setVisible(false);
    }
}
