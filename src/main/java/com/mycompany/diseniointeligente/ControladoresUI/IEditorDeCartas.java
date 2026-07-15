package com.mycompany.diseniointeligente.ControladoresUI;

import com.mycompany.diseniointeligente.Modelos.Carta;
import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Lorenzo Buero
 */
public abstract interface IEditorDeCartas{
    
    public abstract Carta getCartaCreada();
    
    public abstract ETipoCarta obtenerTipoDeCarta();
    
    public abstract void intentarCrearCarta();
     
}
