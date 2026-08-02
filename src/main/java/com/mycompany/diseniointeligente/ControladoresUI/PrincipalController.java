package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.GestionDeDatos.Gestor;
import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaCriatura;
import com.mycompany.diseniointeligente.Modelos.CartaEvento;
import com.mycompany.diseniointeligente.Modelos.CartaHabilidadExtra;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PrincipalController {

    
    private Carta cartaSeleccionada = null;
    
    @FXML
    private Button btn_guardarCartas;
    @FXML
    private Button btn_editar;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_verSacrificios;
    @FXML
    private Button btn_verImagen;
    @FXML
    private Label descripcionCarta;
    @FXML
    private ListView<Carta> listaCartas;
    
    private Gestor<Carta> gestorCartas = new Gestor<>(Carta.class);
    
    
    public void initialize(){
        this.actualizarControladores();
        
        this.listaCartas.setCellFactory(lv -> new ListCell<>(){
        
            @Override
            protected void updateItem(Carta carta, boolean vacio){
                super.updateItem(carta, vacio);
                
                if(vacio || carta == null){
                    setText(null);
                    setStyle("");
                }
                else{
                    setText(carta.getNombre());
                    switch (carta){
                        case CartaHabilidadExtra _-> setStyle("-fx-background-color: yellow;");
                        case CartaCriatura _-> setStyle("-fx-background-color: green;");
                        case CartaEvento _-> setStyle("-fx-background-color: blue;");
                    }
                }
            }
        });
    
        
        this.listaCartas.getSelectionModel().selectedItemProperty().addListener((observable, anteriorCartaSelecciondad, cartaSeleccionadaActualmente) -> {
            
            if(cartaSeleccionadaActualmente != null){
                this.cartaSeleccionada = cartaSeleccionadaActualmente;
            }
            this.actualizarControladores();
        
        }); 
    }
    
    public void mostrarSelectorFiltros(ActionEvent evento){}
    
    public void verImagen(ActionEvent evento){
        try{
            String URL = "";
            if(this.cartaSeleccionada.getUrlImagen() != null){
                URL = this.cartaSeleccionada.getUrlImagen();
            } 
            
            FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/PopupVerImagen.fxml"));
            Parent objetivo = cargador.load();

            PopupVerImagenController controlador = cargador.getController();
            controlador.setImagen(URL);

            Stage escenario = new Stage();

            Scene escena = new Scene(objetivo);

            escenario.setScene(escena);
            escenario.show();
            
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public void verSacrificiosValidos(ActionEvent evento){
        if(this.cartaSeleccionada instanceof CartaEvento CE){
            try{
                FXMLLoader cargador = new FXMLLoader(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/VisorSacrificiosValidos.fxml"));
                Parent objetivo = cargador.load();

                VisorSacrificiosValidosController controlador = cargador.getController();
                controlador.ingresarDatos(this.gestorCartas.leer(), CE.getSacrificio());

                Stage escenario = new Stage();

                Scene escena = new Scene(objetivo);

                escenario.setScene(escena);
                escenario.show();
            } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        }
    
    
    }
    
    public void guardarCartas(ActionEvent evento){
        String carpeta = "CartasAlmacenadas";
        String nombreArchivo = "cartasGuardadas";
        this.gestorCartas.guardarDatos(carpeta, nombreArchivo);
    }
    
    public void cargarCartas(ActionEvent evento){
        String carpeta = "CartasAlmacenadas";
        String nombreArchivo = "cartasGuardadas";
        
        this.gestorCartas.cargarDatos(carpeta, nombreArchivo);
        this.actualizarListViewCartas();
        this.actualizarControladores(); 
    }
    
    
    public void procesoCrearCarta(ActionEvent evento){
        try{
            ETipoCarta tipoACrear = this.preguntarTipoCartaACrear();
            
            Carta cartaCreada = mostrarEditorDeCartas(tipoACrear);
            
            
            if(cartaCreada != null){
                System.out.println(cartaCreada.getNombre());
                this.gestorCartas.crear(cartaCreada);
                this.actualizarListViewCartas();
                this.actualizarControladores();
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

    
    
    private void actualizarListViewCartas(){
        this.listaCartas.getItems().setAll(this.gestorCartas.leer());
    }
    
    private void actualizarControladores(){

        boolean sinCartaSeleccionada = this.cartaSeleccionada == null;
        
        this.btn_verImagen.setDisable(sinCartaSeleccionada);
        this.btn_editar.setDisable(sinCartaSeleccionada);
        this.btn_eliminar.setDisable(sinCartaSeleccionada);
        
        if(!sinCartaSeleccionada){  
            switch (this.cartaSeleccionada){
                    case CartaHabilidadExtra c -> this.descripcionCarta.setText(c.aTextoDescriptivo());                         
                    case CartaCriatura c ->  this.descripcionCarta.setText(c.aTextoDescriptivo());                        
                    case CartaEvento c -> this.descripcionCarta.setText(c.aTextoDescriptivo());
                    default -> this.descripcionCarta.setText(this.cartaSeleccionada.aTextoDescriptivo());
                }
        }
        else{ this.descripcionCarta.setText("No hay ninguna carta seleccionada"); }
        
        btn_verSacrificios.setDisable(true);
        if(cartaSeleccionada instanceof CartaEvento CE){
            if(CE.getSacrificio() != null){
                btn_verSacrificios.setDisable(false);
            }
        }
        
        boolean noHayCartas = this.gestorCartas.leer().isEmpty();
        this.btn_guardarCartas.setDisable(noHayCartas);
    }
}
