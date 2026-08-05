package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.EColecciones;
import com.mycompany.diseniointeligente.GestionDeDatos.EscritorDeArchivos;
import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaCriatura;
import com.mycompany.diseniointeligente.Modelos.CartaEvento;
import com.mycompany.diseniointeligente.Modelos.CartaHabilidadExtra;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
import com.mycompany.diseniointeligente.Modelos.NumeroIdentificador;
import com.mycompany.diseniointeligente.Modelos.EColecciones;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

/**
 *
 * @author Lorenzo Buero
 */
public abstract class EditorCartaController {
    
    @FXML
    public Button btn_volver;
    @FXML
    public Button btn_continuar;
    @FXML
    public TextField nombreCarta;
    @FXML 
    public ImageView visorDeImagen;
    @FXML
    public ChoiceBox<EColecciones> cb_idColeccion;
    
    private BufferedImage imagenObtenida;
    private int numeroCarta;
    private String URLImagen;
    
    public EditorCartaController(){}
    
    public void initialize(){
        
        EColecciones[] tiposEnEnumColecciones = EColecciones.values();
        cb_idColeccion.getItems().addAll(tiposEnEnumColecciones);
        
        this.ocultarAvisosCamposObligatoriosVacios();
        
    }
    
    public void setURLImagen(String nombreArchivo) {
        this.URLImagen = nombreArchivo;
    }
    public String getURLImagen(){
        return this.URLImagen;
    }
    
    public abstract void ocultarAvisosCamposObligatoriosVacios();
    
    public int getNumeroCarta(){
        return this.numeroCarta;
    }
    public void setNumeroCarta(int numeroCarta){
        this.numeroCarta = numeroCarta;
    }
    
    public BufferedImage getImagenObtenida(){
        return imagenObtenida;
    }
    
    public void setImagenObtenida(BufferedImage imagen){
        this.imagenObtenida = imagen;
    }
    
    private Boolean guardarCambios = false;
    
    public Boolean getGuardarCambios(){
        return this.guardarCambios;
    }
    
    public void setGuardarCambios(Boolean valor){
        this.guardarCambios = valor;
    }
    
    public abstract Carta getCartaCreada();
    public abstract void ingresarCartaAEditar(Carta carta);
    
    
    public void volver(ActionEvent evento){
        Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
        escenario.close();
    }
    
    //TODO: ver si se puede cambiar esto a la nueva logica
    public void continuar(ActionEvent evento){
        this.ocultarAvisosCamposObligatoriosVacios();
        this.intentarCrearCarta();
        /*ArrayList<IAtributo> atributosFaltantes;  
        switch(this.getCartaCreada()){
            case CartaCriatura carta -> atributosFaltantes = carta.obtenerCamposObligatoriosVacios();
            case CartaEvento carta -> atributosFaltantes = carta.obtenerCamposObligatoriosVacios();
            case CartaHabilidadExtra carta -> atributosFaltantes = carta.obtenerCamposObligatoriosVacios();
        }*/
        //if(atributosFaltantes.isEmpty()){
        if(this.getCartaCreada() != null){
            this.setGuardarCambios(true);
            this.volver(evento);
        } 
    /*else {
            this.mostrarAvisosCamposObligatoriosVacios();//atributosFaltantes);
        }*/
    }

    public abstract void mostrarAvisosCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios);

    
    public void obtenerImagen(ActionEvent evento) throws IOException{
    
        FileChooser seleccionador = new FileChooser();
        
        FileChooser.ExtensionFilter tiposImagen = new FileChooser.ExtensionFilter(
                "Imagenes", "*.jpg", "*.jpeg", "*.png", "*.webp");
        
        seleccionador.getExtensionFilters().addAll(tiposImagen);
        
        File archivo = seleccionador.showOpenDialog(null);
        BufferedImage imagenBuff = ImageIO.read(archivo);
        WritableImage imagen = SwingFXUtils.toFXImage(imagenBuff, null);
    
        this.visorDeImagen.setImage(imagen);
        this.setImagenObtenida(imagenBuff);
    }
    
    public void guardarImagenDeCarta(Carta cartaConImagen){
        try {
                String nombreArchivo = "Img_" + cartaConImagen.getNombre();
                
                EscritorDeArchivos.guardarImagenAPNG(this.imagenObtenida, "ImagenesCartas", nombreArchivo);

                nombreArchivo += ".png";
                
                this.setURLImagen(nombreArchivo);
                
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public abstract ETipoCarta obtenerTipoDeCarta();
    
    public abstract void intentarCrearCarta();
    
    
    
    public NumeroIdentificador formarNumId(){
        
        EColecciones coleccion = this.cb_idColeccion.getValue();
            
        NumeroIdentificador numId = null;
        if(coleccion != null){
            numId = new NumeroIdentificador(coleccion, this.getNumeroCarta());
        }
        return numId;
    }
    
    protected void reasignarImagen(){
        try {
            if(this.getURLImagen() != null && !this.getURLImagen().isEmpty()){
                Path carpeta = Path.of("ImagenesCartas");
                Path archivo = carpeta.resolve(this.getURLImagen());

                BufferedImage imagenBuff = ImageIO.read(archivo.toFile());

                WritableImage imagen = SwingFXUtils.toFXImage(imagenBuff, null);

                this.visorDeImagen.setImage(imagen);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
