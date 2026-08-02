package com.mycompany.diseniointeligente.ControladoresUI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Lorenzo Buero
 */
public class PopupVerImagenController {
    
    @FXML
    Label textoSinImagen;
    @FXML
    ImageView campoImagen;
    
    String URLImg;
    
    
    public void setImagen(String URL){
        this.URLImg = URL;
        String texto = "La carta seleccionada no tiene una imagen asignada.";
                
        if(!URL.equals("") && this.asignarImagen()){
            texto = "";
        } 
        this.textoSinImagen.setText(texto);
    }
    
    public void volver(ActionEvent evento){
        Stage escenario = (Stage) ((Node)(evento.getSource())).getScene().getWindow();
        escenario.close();
    }
    
    private boolean asignarImagen() {
        try {
            Path carpeta = Path.of("ImagenesCartas");
            Path archivo = carpeta.resolve(this.URLImg);

            BufferedImage imagenBuff = ImageIO.read(archivo.toFile());

            WritableImage imagen = SwingFXUtils.toFXImage(imagenBuff, null);

            this.campoImagen.setImage(imagen);
            
            return true;
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
}
    
}
