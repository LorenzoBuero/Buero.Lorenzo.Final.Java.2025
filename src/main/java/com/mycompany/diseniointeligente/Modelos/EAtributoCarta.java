package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author Lorenzo Buero
 */
public enum EAtributoCarta implements IAtributo{
    NUM_IDENT,
    NOMBRE;
    
    public static String obtenerComoString(EAtributoCarta atributo){
        return switch(atributo){
            case NUM_IDENT -> "Carta > Numero Identificador";
            case NOMBRE -> "Carta > Nombre";
        };
    }
    
    public static EAtributoCarta obtenerComoAtributo(String atributo){
        return switch(atributo){
            case "Carta > Numero Identificador" -> NUM_IDENT;
            case "Carta > Nombre" -> NOMBRE;
            default -> null;
        };
    }
}
