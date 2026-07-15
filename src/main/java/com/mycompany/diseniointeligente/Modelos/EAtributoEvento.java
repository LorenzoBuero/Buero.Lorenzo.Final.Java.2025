package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author Lorenzo Buero
 */
public enum EAtributoEvento implements IAtributo{
    EFECTO,
    DESCRIPCION_SACRIFICIO;
    
    
    public static String obtenerComoString(EAtributoEvento atributo){
        return switch(atributo){
            case EFECTO -> "Evento > Efecto";
            case DESCRIPCION_SACRIFICIO -> "Evento > Descripcion del sacrificio";
        };
    }
    
    public static EAtributoEvento obtenerComoAtributo(String atributo){
        return switch(atributo){
            case "Evento > Efecto" -> EFECTO;
            case "Evento > Descripcion del sacrificio" -> DESCRIPCION_SACRIFICIO;
            default -> null;
        };
    }
}
