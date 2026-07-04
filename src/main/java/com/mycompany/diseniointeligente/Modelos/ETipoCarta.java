package com.mycompany.diseniointeligente.Modelos;



//@author Lorenzo Buero


public enum ETipoCarta {
    CRIATURA('C'),
    EVENTO('E'),
    HABILIDAD_EXTRA('H');
    
    public final char tipo;
    
    private ETipoCarta(char tipo){
        this.tipo = tipo;
    }
}
