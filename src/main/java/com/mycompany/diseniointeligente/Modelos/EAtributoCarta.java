package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author Lorenzo Buero
 */
public enum EAtributoCarta implements IAtributo{
    NUM_IDENT,
    NOMBRE;
    
    @Override
    public String obtenerComoString(){
        return switch(this){
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
    
    @Override
    public Class obtenerClase(){   
        return switch(this){
            case NUM_IDENT -> NumeroIdentificador.class;
            case NOMBRE -> String.class;
        };
    }
}
