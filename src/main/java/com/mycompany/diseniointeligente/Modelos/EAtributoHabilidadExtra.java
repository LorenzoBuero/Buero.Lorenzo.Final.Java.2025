package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author pirulo
 */
public enum EAtributoHabilidadExtra implements IAtributo{
    EFECTO,
    DESCRIPCION_OBJETIVO;
    
    @Override
    public String obtenerComoString(){
        return switch(this){
            case EFECTO -> "Habilidad Extra > Efecto";
            case DESCRIPCION_OBJETIVO -> "Habilidad Extra > Descripcion del objetivo";
        };
    }
    
    public static EAtributoHabilidadExtra obtenerComoAtributo(String atributo){
        return switch(atributo){
            case "Habilidad Extra > Efecto" -> EFECTO;
            case "Habilidad Extra > Descripcion del objetivo" -> DESCRIPCION_OBJETIVO;
            default -> null;
        };
    }

    

    @Override
    public Class obtenerClase() {
        return switch(this){
            case EFECTO -> String.class;
            case DESCRIPCION_OBJETIVO -> String.class;
        };
    }
}
