package com.mycompany.diseniointeligente.Modelos;


import java.lang.IllegalArgumentException;
//@author Lorenzo Buero

public class CosteDeCrecimiento implements IParseable{
    int cantidad;
    EDieta comida;
    
    public CosteDeCrecimiento(int cantidad, EDieta comida){
        if(cantidad > 0){
            this.cantidad = cantidad;
            this.comida = comida;
        } else {
            throw new IllegalArgumentException("Error, la cantidad debe ser mayor a 0");
        }
    }
}
