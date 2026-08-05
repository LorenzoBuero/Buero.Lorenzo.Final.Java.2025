package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EColecciones;
import com.mycompany.diseniointeligente.Excepciones.ParametroObligatorioEsNullException;
import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaCriatura;
import com.mycompany.diseniointeligente.Modelos.EAtributoCarta;
import com.mycompany.diseniointeligente.Modelos.EAtributoCriatura;
import com.mycompany.diseniointeligente.Modelos.EDieta;
import com.mycompany.diseniointeligente.Modelos.EHabilidadBasica;
import com.mycompany.diseniointeligente.Modelos.EHabitat;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.Estadisticas;
import com.mycompany.diseniointeligente.Modelos.HabilidadEspecial;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


  //@author Lorenzo Buero

public class EditorCriaturaController extends EditorCartaController implements IEditorDeCartas{

    private CartaCriatura cartaAGuardar = null;
    
    @FXML
    public TextField genero;
    @FXML
    public TextField especie;
    @FXML
    public ChoiceBox<EHabitat> cb_selectorHabitat;
    @FXML
    public ChoiceBox<EDieta> cb_selectorDieta;
    @FXML
    Label lbl_errorGeneroEspecie;
    @FXML
    Label lbl_errorColeccion;
    @FXML
    Label lbl_errorDieta;
    @FXML
    Label lbl_errorHabitat;
    @FXML
    Label lbl_errorEstadisticas;
    
    private ArrayList<EHabilidadBasica> habilidadesBasicasIngresadas;
    private HabilidadEspecial habilidadEspecialIngresada;
    private Estadisticas estadisticasIngresadas;
    
    @Override
    public void initialize(){
        
        EDieta[] tiposEnEnumDieta = EDieta.values();
        cb_selectorDieta.getItems().addAll(tiposEnEnumDieta);
   
        EHabitat[] tiposEnEnumHabitat = EHabitat.values();
        cb_selectorHabitat.getItems().addAll(tiposEnEnumHabitat);

        super.initialize();
    }
    
    public EditorCriaturaController(){
        
        
    }
    
    
    
    @Override
    public CartaCriatura getCartaCreada() {
        return this.cartaAGuardar;
    }
    @Override
    public void ingresarCartaAEditar(Carta carta){
        if(carta instanceof CartaCriatura CC){
            
            
            this.especie.setText(CC.getEspecie());
            this.genero.setText(CC.getGenero());
            this.cb_idColeccion.setValue(EColecciones.valueOf(CC.getNumId().getNumeroColeccion()));
            this.cb_selectorDieta.setValue(CC.getDieta());
            this.cb_selectorHabitat.setValue(CC.getHabitat());
            this.estadisticasIngresadas = CC.getEstadisticas();
            
            this.habilidadEspecialIngresada = CC.getHabilidadEspecial();
            this.habilidadesBasicasIngresadas = CC.getHabilidadesBasicas();
            this.setURLImagen(CC.getUrlImagen());
            
            this.reasignarImagen();
            
        } else {
            throw new IllegalArgumentException("La carta debe ser una CartaCriatura, no una de otro tipo.");
        }
    }
    
    private void guardarCarta(CartaCriatura cartaCriatura){
        this.cartaAGuardar = cartaCriatura;
    }
    

    @Override
    public ETipoCarta obtenerTipoDeCarta() {
        return ETipoCarta.CRIATURA;
    }

    public void obtenerEstadisticas(ActionEvent evento){
        try{

            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/PopupIngresarEstadisticas.fxml"));
            Parent objetivo = cargador.load();

            PopupIngresarEstadisticasController controlador = cargador.getController();
            controlador.setEstadisticas(estadisticasIngresadas);

            Stage escenario = new Stage();
            escenario.initModality(Modality.APPLICATION_MODAL);

            Scene escena = new Scene(objetivo);

            escenario.setScene(escena);
            escenario.showAndWait();

            Estadisticas estadisticas = controlador.getEstadisticas();
            if(estadisticas != null){
                this.estadisticasIngresadas = estadisticas;
            }
            
        } catch (IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    
    }
    
    
    public void obtenerHabilidadesBasicas(ActionEvent evento){
        try{

            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/PopupAgregarHabilidadesBasicas.fxml"));
            Parent objetivo = cargador.load();

            PopupAgregarHabilidadesBasicasController controlador = cargador.getController();
            controlador.setHabilidades(this.habilidadesBasicasIngresadas);

            Stage escenario = new Stage();
            escenario.initModality(Modality.APPLICATION_MODAL);

            Scene escena = new Scene(objetivo);

            escenario.setScene(escena);
            escenario.showAndWait();

            ArrayList<EHabilidadBasica> habilidadesActualizadas = controlador.getHabilidades();
            if(habilidadesActualizadas != null){
                this.habilidadesBasicasIngresadas = habilidadesActualizadas;
            }
            
        } catch (IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
        
    }
    
    public void obtenerHabilidadEspecial(ActionEvent evento){
        try{

            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/PopupAgregarHabilidadEspecial.fxml"));
            Parent objetivo = cargador.load();
            
            
            PopupAgregarHabilidadEspecialController controlador = cargador.getController();
            controlador.setHabilidad(this.habilidadEspecialIngresada);

            Stage escenario = new Stage();
            escenario.initModality(Modality.APPLICATION_MODAL);

            Scene escena = new Scene(objetivo);

            escenario.setScene(escena);
            escenario.showAndWait();

            HabilidadEspecial habilidadActualizada = controlador.getHabilidad();
            if(controlador.getGuardarEstosDatos()){
                this.habilidadEspecialIngresada = habilidadActualizada;
            }
            
        } catch (IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    
    
    @Override
    public void intentarCrearCarta(){
        
        
        CartaCriatura cartaNueva = null;
        try { 
            String generoIngresado = this.genero.getText().trim();
            String especieIngresada = this.especie.getText().trim();
            
            NumeroIdentificador numId = this.formarNumId();
            cartaNueva = new CartaCriatura(
                    generoIngresado, especieIngresada, numId, estadisticasIngresadas,
                    this.cb_selectorHabitat.getValue(), this.cb_selectorDieta.getValue());
        
        } catch (ParametroObligatorioEsNullException ex) {
            
            ArrayList<IAtributo>camposFaltantes = ex.getCamposRequeridos();
            this.mostrarAvisosCamposObligatoriosVacios(camposFaltantes);
            
        } catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        if(cartaNueva != null){
            //HABILIDADES BASICAS
            if(this.habilidadesBasicasIngresadas != null){
                cartaNueva.setHabilidadesBasicas(habilidadesBasicasIngresadas);
            }

            //HABILIDAD ESPECIAL
            if(this.habilidadEspecialIngresada != null){
                cartaNueva.setHabilidadEspecial(habilidadEspecialIngresada);
            }
            
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
    public void mostrarAvisosCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios){
        for(IAtributo faltante : atributosObligatorios){
                switch(faltante){
                    case EAtributoCarta.NOMBRE -> this.lbl_errorGeneroEspecie.setVisible(true);
                    case EAtributoCarta.NUM_IDENT -> this.lbl_errorColeccion.setVisible(true);
                    case EAtributoCriatura.ESTADISTICAS -> this.lbl_errorEstadisticas.setVisible(true);
                    case EAtributoCriatura.DIETA -> this.lbl_errorDieta.setVisible(true);
                    case EAtributoCriatura.HABITAT -> this.lbl_errorHabitat.setVisible(true);
                    default -> System.out.println(faltante);
                }
            }
    }
    
    @Override 
    public void continuar(ActionEvent evento){
        this.intentarCrearCarta();
        super.continuar(evento);
    }

    @Override
    public void ocultarAvisosCamposObligatoriosVacios() {
        this.lbl_errorGeneroEspecie.setVisible(false);
        this.lbl_errorColeccion.setVisible(false);
        this.lbl_errorEstadisticas.setVisible(false);
        this.lbl_errorDieta.setVisible(false);
        this.lbl_errorHabitat.setVisible(false);    
    }
    
    
}
