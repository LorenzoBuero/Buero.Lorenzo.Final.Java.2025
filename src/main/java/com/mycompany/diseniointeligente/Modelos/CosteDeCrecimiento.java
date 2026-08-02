package com.mycompany.diseniointeligente.Modelos;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
//@author Lorenzo Buero

public class CosteDeCrecimiento implements IParseable{
    int cantidad;
    //EDieta comida;
    
    @JsonCreator
    public CosteDeCrecimiento(@JsonProperty("cantidad") int cantidad){
        if(cantidad > 0){
            this.cantidad = cantidad;
            //this.comida = comida;
        } else {
            throw new IllegalArgumentException("Error, la cantidad debe ser mayor a 0");
        }
    }

    public int getCantidad() {
        return cantidad;
    }
    
    @Override
    public String toString(){
        String retorno = "";
        retorno += "Comida: " + this.cantidad;
        return retorno;
    }

    @Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public String aTextoDescriptivo() {
        String retorno = "";
        
        retorno += "Comida necesaria:" + this.cantidad;
        
        
        return retorno;
    }
}
