package com.mycompany.diseniointeligente.Modelos;



//@author Lorenzo Buero
 
public abstract class Carta implements Comparable, IParseable{
    
    private NumeroIdentificador numId;
    private String nombre;
    //private XX arte;
    
    
    public Carta(String nombre){
        this.nombre = nombre;
    }
    
    public Carta(String nombre, NumeroIdentificador numId){
        //this.numId = numId;   
        this(nombre);
    }
    
    public Carta(String nombre, char tipoCarta, int numeroDeCarta, int numeroDeColeccion){
        NumeroIdentificador numID = new NumeroIdentificador(tipoCarta, numeroDeCarta, numeroDeColeccion);
        //this(nombre, numID);
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
    /*
    public String aResumenTXT(){
        String retorno = "";
        
        retorno = retorno + "Nombre: " + nombre + "\n";
        retorno = retorno + "Número identificador: " + numId.getNumeroIdentificador() + "\n";
        
        return retorno;
    }*/
    
    //@Override
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
    
}