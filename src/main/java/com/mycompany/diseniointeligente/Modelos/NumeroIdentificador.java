package com.mycompany.diseniointeligente.Modelos;

//@author Lorenzo Buero

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class NumeroIdentificador implements IParseable, Comparable<NumeroIdentificador>{

    
    
    private char tipoCarta;
    private int numeroColeccion;
    private int numeroCarta;
    
    private final String DIVISOR = "-";
    
    public NumeroIdentificador(char tipoCarta, int numeroColeccion, int numeroCarta){
        this.tipoCarta = tipoCarta;
        this.numeroColeccion = numeroColeccion;
        this.numeroCarta = numeroCarta;
    }
    
    public NumeroIdentificador(int numeroColeccion, int numeroCarta){
        this.numeroColeccion = numeroColeccion;
        this.numeroCarta = numeroCarta;
    }
    
    public NumeroIdentificador(char tipoCarta, int numeroColeccion){
        this.numeroColeccion = numeroColeccion;
        this.tipoCarta = tipoCarta;
    }
    
    public NumeroIdentificador(String numID){

        String[] secciones = numID.split(DIVISOR);
        
        if(char.class.isInstance(secciones[0]) && int.class.isInstance(secciones[1]) && int.class.isInstance(secciones[2])){
            this.tipoCarta = secciones[0].charAt(0);
            this.numeroColeccion = Integer.parseInt(secciones[1]);
            this.numeroCarta = Integer.parseInt(secciones[2]); 
        }
    }

    
    public char getTipoCarta() {
        return tipoCarta;
    }
    
    public void setTipoCarta(char tipo) {
        this.tipoCarta = tipo;
    }

    public String getNumeroColeccion() {
        return String.valueOf(numeroColeccion);
    } 
    public void setNumeroColeccion(int numero){
        this.numeroColeccion = numero;
    }

    public String getNumeroCarta() {
        return String.valueOf(numeroCarta);
    }

    
    public String getNumeroIdentificador(){
        String retorno = "";
        
        retorno = retorno + tipoCarta;
        retorno = retorno + DIVISOR;
        retorno = retorno + this.getNumeroColeccion();
        retorno = retorno + DIVISOR;
        retorno = retorno + this.getNumeroCarta();
        
        return retorno;
    }
    
    
    @Override
    public String aCSV() {
        String retorno = this.getNumeroIdentificador() + " ";
        
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