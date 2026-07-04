package com.mycompany.diseniointeligente.Modelos;




//@author pirulo

public class NumeroIdentificador implements IParseable{
    
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
}