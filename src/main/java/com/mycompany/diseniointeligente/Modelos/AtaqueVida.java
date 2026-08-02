package com.mycompany.diseniointeligente.Modelos;

//import java.lang.IllegalArgumentException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


//@author Lorenzo Buero


public class AtaqueVida implements IParseable{

    private int ataque;
    private int vida;

    @JsonCreator
    public AtaqueVida(
            @JsonProperty("ataque") int ataque, 
            @JsonProperty("vida") int vida){
        
        if(ataque >= 0 && vida > 0){
            this.ataque = ataque;
            this.vida = vida;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getAtaque() {
        return ataque;
    }

    public int getVida() {
        return vida;
    }
    
    
    @Override
    public String toString(){
        String retorno = "";
        retorno += "Ataque: " + this.ataque + "    Vida: " + this.vida;
        return retorno;
    }

    @Override
    public String aCSV() {
        String retorno = " ";
        retorno = retorno + ataque + " , ";
        retorno = retorno + vida + " ";
        return retorno;
    }

    @Override
    public String aTextoDescriptivo() {
        String retorno;
        retorno = "Ataque/Vida: " + ataque + "/" + vida;
        return retorno;
    }
    
    
}
