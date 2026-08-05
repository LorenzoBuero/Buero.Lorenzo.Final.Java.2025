package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author Lorenzo Buero
 */
public enum EAtributoEstadisticas implements IAtributo{
    PRIMER_VIDA,
    PRIMER_ATAQUE,
    PRIMER_COMIDA,
    MAXIMA_VIDA,
    MAXIMA_COMIDA,
    MAXIMO_ATAQUE,
    SUMATORIA_COMIDA;

    @Override
    public Class obtenerClase() {
        return Integer.class;
    }

    @Override
    public String obtenerComoString() {
        return switch(this){
            case PRIMER_VIDA -> "Primer vida";
            case PRIMER_ATAQUE -> "Primer ataque";
            case PRIMER_COMIDA -> "Primer comida";
            case MAXIMA_VIDA -> "Máxima vida";
            case MAXIMA_COMIDA -> "Máxima comida";
            case MAXIMO_ATAQUE -> "Máximo ataque";
            case SUMATORIA_COMIDA -> "Sumatoria comida";
        };
    }
    
    public static EAtributoEstadisticas obtenerComoAtributo(String atributo){
        if(atributo == null){return null;}
        return switch(atributo){
            case "Primer vida" -> PRIMER_VIDA;
            case "Primer ataque" -> PRIMER_ATAQUE;
            case "Primer comida" -> PRIMER_COMIDA;
            case "Máxima vida" -> MAXIMA_VIDA;
            case "Máxima comida" -> MAXIMA_COMIDA;
            case "Máximo ataque" -> MAXIMO_ATAQUE;
            case "Sumatoria comida" -> SUMATORIA_COMIDA;
            default -> null;
        };
    }
}
