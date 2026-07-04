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
    
    
}
