package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.CartaCriatura;
import com.mycompany.diseniointeligente.Modelos.CartaEvento;
import com.mycompany.diseniointeligente.Modelos.CartaHabilidadExtra;
import com.mycompany.diseniointeligente.Modelos.IAtributo;
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
    
    
    public void initialize(){
        
        EColecciones[] tiposEnEnumColecciones = EColecciones.values();
        cb_idColeccion.getItems().addAll(tiposEnEnumColecciones); 
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
    
    
    public void volver(ActionEvent evento){
        Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
        escenario.close();   
    }
    
    public void continuar(ActionEvent evento){

        ArrayList<IAtributo> atributosFaltantes;  
        switch(this.getCartaCreada()){
            case CartaCriatura carta -> atributosFaltantes = carta.obtenerCamposObligatoriosVacios();
            case CartaEvento carta -> atributosFaltantes = carta.obtenerCamposObligatoriosVacios();
            case CartaHabilidadExtra carta -> atributosFaltantes = carta.obtenerCamposObligatoriosVacios();
        }
        if(atributosFaltantes.isEmpty()){
            this.setGuardarCambios(true);
            this.volver(evento);
        } else {
            this.mostrarAvisoCamposObligatoriosVacios(atributosFaltantes);
        }
    }


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
    
    
    public abstract void mostrarAvisoCamposObligatoriosVacios(ArrayList<IAtributo> atributosObligatorios);
    
    public void guardarImagenDeCarta(Carta cartaConImagen){
        try {
                Path carpeta = Path.of("ImagenesCartas");
                Files.createDirectories(carpeta);

                String nombreArchivo = "Img_" + cartaConImagen.getNombre() + ".png";
                Path archivo = carpeta.resolve(nombreArchivo);

                ImageIO.write(this.getImagenObtenida(), "png", archivo.toFile());

                cartaConImagen.setUrlImagen(nombreArchivo);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }
    
}
