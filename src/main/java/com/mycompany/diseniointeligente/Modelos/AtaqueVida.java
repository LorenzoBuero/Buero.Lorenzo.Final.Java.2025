package com.mycompany.diseniointeligente.Modelos;


//import java.lang.IllegalArgumentException;

//@author Lorenzo Buero


public class AtaqueVida implements IParseable{

    private int ataque;
    private int vida;

    public AtaqueVida(int ataque, int vida){
        if(ataque > 0 && vida > 0){
            this.ataque = ataque;
            this.vida = vida;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aJSON() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aTextoDescriptivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
