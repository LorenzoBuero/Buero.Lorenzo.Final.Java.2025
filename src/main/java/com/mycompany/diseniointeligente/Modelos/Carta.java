package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;



//@author Lorenzo Buero
 
public sealed abstract class Carta implements Comparable<Carta>, IParseable permits CartaCriatura, CartaEvento, CartaHabilidadExtra{
    
    private NumeroIdentificador numId;
    private String nombre;
    private String urlImagen;
    
    public Carta(){}
    
    public Carta(String nombre){
        this.nombre = nombre;
    }
    
    public Carta(String nombre, NumeroIdentificador numId){
        this.numId = numId;   
        this(nombre);
    }
    
    public Carta(String nombre, char tipoCarta, int numeroDeCarta, int numeroDeColeccion){
        NumeroIdentificador numID = new NumeroIdentificador(tipoCarta, numeroDeCarta, numeroDeColeccion);
        this(nombre, numID);
    }
    
    public abstract char getCaracterRepresentativo();
    
    
    public NumeroIdentificador getNumId(){
        return this.numId;
    }
    
    public void setNumId(NumeroIdentificador numId){
        this.numId = numId;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getUrlImagen(){
        return this.urlImagen;
    }
    public void setUrlImagen(String url){
        this.urlImagen = url;
    }
    
    
    
    public boolean equals(Carta carta){
        boolean retorno = false;
        
        String numeroOtraCarta = carta.getNumId().getNumeroCarta();
        
        if( this.numId.getNumeroCarta().equals(numeroOtraCarta) ){
            retorno = true;
        }
        
        return retorno;
    }
    
    public boolean exactlyEquals(Carta carta){
        boolean retorno = false;
        
        String otraCartaID = carta.getNumId().getNumeroIdentificador();
        
        if( this.numId.getNumeroCarta().equals(otraCartaID) ){
            retorno = true;
        }
        
        return retorno;
    
    }
    
    public ArrayList<IAtributo> obtenerCamposObligatoriosVacios(){
    
        ArrayList<IAtributo> retorno = new ArrayList<>();
        
        if(this.getNombre() == null){
            retorno.add(EAtributoCarta.NOMBRE);
        }
        if(this.getNumId() == null){
            retorno.add(EAtributoCarta.NUM_IDENT);
        }
        return retorno;
    }
    
    @Override
    public String aCSV() {
        String retorno;
        
        retorno = this.numId.aCSV() + " , ";
        retorno += this.nombre + " , ";
        retorno += this.urlImagen;
        
        return retorno;
    }

    @Override
    public String aJSON()  throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }

    @Override
    public String aTextoDescriptivo() {
        String retorno;
        retorno = this.numId.aTextoDescriptivo();
        retorno += "Nombre: " + this.nombre + "\n";
        retorno += "URL: " + this.urlImagen + "\n";
        return retorno;
    }
    
}