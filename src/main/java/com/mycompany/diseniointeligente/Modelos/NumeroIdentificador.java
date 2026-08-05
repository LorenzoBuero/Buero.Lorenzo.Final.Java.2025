package com.mycompany.diseniointeligente.Modelos;

//@author Lorenzo Buero

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;



public class NumeroIdentificador implements IParseable, Comparable<NumeroIdentificador>{

    
    private Integer numeroColeccion;
    private Character tipoCarta;
    private Integer numeroCarta;
    
    private final String DIVISOR = "-";
    
    
    
    @JsonCreator
    public NumeroIdentificador(
            @JsonProperty("numeroColeccion") int numeroColeccion, 
            @JsonProperty("tipoCarta") Character tipoCarta, 
            @JsonProperty("numeroCarta") int numeroCarta){
        
        this.numeroColeccion = numeroColeccion;
        this.tipoCarta = tipoCarta;
        this.numeroCarta = numeroCarta;
    }
    
    public NumeroIdentificador(EColecciones coleccion, int numeroCarta){
        this(coleccion.numero, null, numeroCarta);
    }
    
    
    public NumeroIdentificador(String numID){

        String[] secciones = numID.split(DIVISOR);
        
        if(char.class.isInstance(secciones[0]) && int.class.isInstance(secciones[1]) && int.class.isInstance(secciones[2])){
            this.tipoCarta = secciones[0].charAt(0);
            this.numeroColeccion = Integer.parseInt(secciones[1]);
            this.numeroCarta = Integer.parseInt(secciones[2]); 
        }
    }

    
    public Character getTipoCarta() {
        return tipoCarta;
    }
    
    public void setTipoCarta(Character tipo) {
        this.tipoCarta = tipo;
    }

    public Integer getNumeroColeccion() {
        return numeroColeccion;
    } 
    private void setNumeroColeccion(int numero){
        this.numeroColeccion = numero;
    }

    public String getNumeroCarta() {
        return String.valueOf(numeroCarta);
    }
    public void setNumeroCarta(int numeroCarta) {
        this.numeroCarta = numeroCarta;
    }

    @JsonIgnore
    public String getNumeroIdentificador(){
        String retorno = "";
        
        retorno = retorno + tipoCarta;
        retorno = retorno + DIVISOR;
        retorno = retorno + this.getNumeroColeccion();
        retorno = retorno + DIVISOR;
        retorno = retorno + this.getNumeroCarta();
        
        return retorno;
    }
    
    public boolean valoresIncompletos(){
        boolean retorno = false;
        
        if(this.numeroColeccion == null || this.numeroColeccion < 0){
            retorno = true;
        }
        if(this.tipoCarta == null){
            retorno = true;
        }
        if(this.numeroCarta == null || this.numeroCarta < 0){
            retorno = true;
        }
        
        return retorno;
    }
    
    
    @Override
    public String aCSV() {
        String retorno = this.getNumeroIdentificador() + " ";
        
        return retorno;
    }

    @Override
    public String aTextoDescriptivo() {
        String retorno = "ID: " + getNumeroIdentificador() + "\n";
        return retorno;
    }

    @Override
    public int compareTo(NumeroIdentificador comparado) {
        int retorno = 0;
        if(this.numeroColeccion < comparado.numeroColeccion){
            retorno = -1;
        } else if(this.numeroColeccion > comparado.numeroColeccion){
            retorno = 1;
        } else if(this.numeroCarta < comparado.numeroCarta){
            retorno = -1;
        } else if(this.numeroCarta > comparado.numeroCarta){
            retorno = 1;
        }
        
        return retorno;
    }
}