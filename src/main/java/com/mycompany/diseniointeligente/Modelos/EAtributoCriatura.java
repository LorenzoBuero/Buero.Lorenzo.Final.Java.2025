package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author Lorenzo Buero
 */
public enum EAtributoCriatura implements IAtributo{
    //CLADISTICA,
    HABILIDADES_BASICAS,
    HABILIDAD_ESPECIAL,
    ESTADISTICAS,
    HABITAT,
    DIETA;
    
    @Override
    public String obtenerComoString(){
        return switch(this){
            //case CLADISTICA -> "Criatura > Numero Identificador";
            case HABILIDADES_BASICAS -> "Criatura > Habilidades basicas";
            case HABILIDAD_ESPECIAL -> "Criatura > Habilidad especial";
            case ESTADISTICAS -> "Criatura > Estadisticas";
            case HABITAT -> "Criatura > Habitat";
            case DIETA -> "Criatura > Dieta";
        };
    }
    
    public static EAtributoCriatura obtenerComoAtributo(String atributo){
        return switch(atributo){
            case "Criatura > Habilidades basicas" -> HABILIDADES_BASICAS;
            case "Criatura > Habilidad especial" -> HABILIDAD_ESPECIAL;
            case "Criatura > Estadisticas" -> ESTADISTICAS;
            case "Criatura > Habitat" -> HABITAT;
            case "Criatura > Dieta" -> DIETA;
            default -> null;
        };
    }

    @Override
    public Class obtenerClase() {
        return switch(this){
            case HABILIDADES_BASICAS -> EHabilidadBasica.class;
            case HABILIDAD_ESPECIAL -> HabilidadEspecial.class;
            case ESTADISTICAS -> Estadisticas.class;
            case HABITAT -> EHabitat.class;
            case DIETA -> EDieta.class;
        };
    }
    
    
    
}
