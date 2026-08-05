package com.mycompany.diseniointeligente.Modelos;

//@author Lorenzo Buero
 
public enum EColecciones {
    INICIOS(1),
    LA_GRAN_EXTINCION(2);
    
    public final int numero;
    
    private EColecciones(int numero){
        this.numero = numero;
    }
    
    public static EColecciones valueOf(int numero){
        return switch(numero){
            case 1 -> INICIOS;
            case 2 -> LA_GRAN_EXTINCION;
            default -> null;
        };
        
    }
}
