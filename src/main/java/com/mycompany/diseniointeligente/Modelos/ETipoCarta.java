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
    
    public static String aString(ETipoCarta tipoCarta){
        return switch (tipoCarta) {
            case CRIATURA -> "Criatura";
            case EVENTO -> "Evento";
            case HABILIDAD_EXTRA -> "Habilidad Extra";
            default -> "Error al cargar este tipo de carta";
        };
    }
    public static ETipoCarta aETipoCarta(String tipoCarta){
        return switch(tipoCarta){
            case "Criatura" -> CRIATURA;
            case "Evento" -> EVENTO;
            case "Habilidad Extra" -> HABILIDAD_EXTRA;
            default -> null;
        };
    }
}
