package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper; 
import com.fasterxml.jackson.databind.ObjectWriter; 
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
    public String aJSON() throws JsonProcessingException {
        
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }

    @Override
    public String aTextoDescriptivo() {
        String retorno;
        retorno = "Ataque: " + ataque + "Vida: " + vida;
        return retorno;
    }
    
    
}
