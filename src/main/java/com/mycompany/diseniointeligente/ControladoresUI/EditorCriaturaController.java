package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaCriatura;
import com.mycompany.diseniointeligente.Modelos.EDieta;
import com.mycompany.diseniointeligente.Modelos.EHabilidadBasica;
import com.mycompany.diseniointeligente.Modelos.EHabitat;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.Estadisticas;
import com.mycompany.diseniointeligente.Modelos.HabilidadEspecial;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


  //@author Lorenzo Buero

public class EditorCriaturaController extends EditorCartaController implements IEditorDeCartas{

    private CartaCriatura cartaCreada = null;
    
    @FXML
    public TextField genero;
    @FXML
    public TextField especie;
    @FXML
    public ChoiceBox<EHabitat> cb_selectorHabitat;
    @FXML
    public ChoiceBox<EDieta> cb_selectorDieta;
    @FXML
    public Button agregarHabsDef;
    @FXML
    public TextArea habPers;
    
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
    
    
    @Override
    public CartaCriatura getCartaCreada() {
        return this.cartaCreada;
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
            if(habilidadActualizada != null){
                this.habilidadEspecialIngresada = habilidadActualizada;
            }
            
        } catch (IOException ex){
            System.out.println(ex.getLocalizedMessage());
        }
    }
    
    
    
    @Override
    public void intentarCrearCarta(){
        CartaCriatura cartaNueva = new CartaCriatura();
        
        //NUMERO DE COLECCION
        if(this.idColeccionSeleccionada()){
            int numColeccion = this.cb_idColeccion.getValue().numero;
            NumeroIdentificador numIdent = new NumeroIdentificador(cartaNueva.getCaracterRepresentativo(), numColeccion);
            cartaNueva.setNumId(numIdent);
        }
        
        //NOMBRE    GENERO    ESPECIE
        if(this.camposNombreFueronLlenados()){
            String especieIngresada = this.especie.getText().trim();
            String generoIngresado = this.genero.getText().trim();
            
            cartaNueva.setGenero(especieIngresada);
            cartaNueva.setEspecie(generoIngresado);
            
            String nombre = generoIngresado + " " + especieIngresada;
            
            cartaNueva.setNombre(nombre);
        } 
        
        //DIETA
        if(this.dietaFueSeleccionada()){
            cartaNueva.setDieta(this.cb_selectorDieta.getValue());
        }
        
        //HABITAT
        if(this.habitatFueSeleccionado()){
            cartaNueva.setHabitat(this.cb_selectorHabitat.getValue());
        }

        //ESTADISTICAS
        if(this.estadisticasFueronLlenadas()){
            cartaNueva.setEstadisticas(estadisticasIngresadas);
        }
        
        //HABILIDADES BASICAS
        if(this.habilidadesBasicasFueronSeleccionadas()){
            cartaNueva.setHabilidadesBasicas(habilidadesBasicasIngresadas);
        }
        
        //HABILIDAD ESPECIAL
        if(this.habilidadEspecialFueLlenada()){
            cartaNueva.setHabilidadEspecial(habilidadEspecialIngresada);
        }
        
        //IMAGEN
        if(this.imagenFueIngresada()){
            
            this.guardarImagenDeCarta(cartaCreada);
        }
        
        this.cartaCreada = cartaNueva;
    }
    
    @Override
    public void mostrarAvisoCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios){
    
    }
    
    @Override 
    public void continuar(ActionEvent evento){
        this.intentarCrearCarta();
        super.continuar(evento);
    }
    
    
    
    private boolean camposNombreFueronLlenados(){
        String especieIngresada = this.especie.getText();
        String generoIngresado = this.genero.getText();
        
        Boolean retorno = true;
        
        if(especieIngresada.isBlank() || generoIngresado.isBlank()){
            retorno = false;
        }
        
        return retorno;
    }
    
    private boolean habitatFueSeleccionado(){
        Boolean retorno = true;
        
        if(this.cb_selectorHabitat.getValue() == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean dietaFueSeleccionada(){
        Boolean retorno = true;
        
        if(this.cb_selectorDieta.getValue() == null){
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
    private boolean estadisticasFueronLlenadas(){
        Boolean retorno = true;
        
        if(this.estadisticasIngresadas == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean habilidadesBasicasFueronSeleccionadas(){
        Boolean retorno = true;
        
        if(this.habilidadesBasicasIngresadas == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean habilidadEspecialFueLlenada(){
        Boolean retorno = true;
        
        if(this.habilidadEspecialIngresada == null){
            retorno = false;
        }
        
        return retorno;
    }
    private boolean idColeccionSeleccionada(){
        Boolean retorno = true;
        
        if(this.cb_idColeccion.getValue() == null){
            retorno = false;
        }
        return retorno;
    }
}
