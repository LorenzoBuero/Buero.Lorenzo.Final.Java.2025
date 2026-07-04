package com.mycompany.diseniointeligente.Modelos;




  //@author pirulo
 
public final class CartaHabilidadExtra extends Carta implements IParseable{
    
    public String efecto;
    public RamaCladistica objetivosValidos;
    
    
    CartaHabilidadExtra(String nombre, NumeroIdentificador numId, String efecto, RamaCladistica objValidos){
        numId.setTipoCarta(getCaracterRepresentativo()); 
        this.efecto = efecto;
        this.objetivosValidos = objValidos;
        super(nombre, numId);
    
    }
    
    @Override
    public char getCaracterRepresentativo(){
        return 'H';
    }
    
}
