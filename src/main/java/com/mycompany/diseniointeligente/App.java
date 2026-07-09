package com.mycompany.diseniointeligente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Group;
import javafx.scene.paint.Color;


/**
 * JavaFX App
 */
public class App extends Application {

    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenario) throws Exception {
        
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/com/mycompany/diseniointeligente/Escenas/Principal.fxml"));
            Scene scene = new Scene(root);
            escenario.setScene(scene);
            escenario.show();
        }catch(Exception e){
            System.out.println("Algo Salió muy mal");
            e.printStackTrace();
        }
    }
}