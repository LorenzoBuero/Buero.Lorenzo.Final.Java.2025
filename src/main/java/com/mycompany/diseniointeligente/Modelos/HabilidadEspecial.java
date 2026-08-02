package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;


 
//@author pirulo
 
public class HabilidadEspecial implements IParseable{

    @Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public String aTextoDescriptivo() {
        String retorno = "";
        
        retorno += "Nombre de Habilidad Especial: " + this.nombre + "\n";
        retorno += "Descripcion: " + this.habilidad + "\n";
        
        return retorno;
    }

    public String nombre;
    public String habilidad;

    @JsonCreator
    public HabilidadEspecial(@JsonProperty("nombre") String nombre, 
            @JsonProperty("habilidad") String habilidad){
        
        this.nombre = nombre;
        this.habilidad = habilidad;
    }
     
    @Override
    public String toString(){
        String retorno = "";
        
        retorno += this.nombre + " " + this.habilidad;
        
        return retorno;
    }
}
