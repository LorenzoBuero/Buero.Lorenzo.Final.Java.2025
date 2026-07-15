package com.mycompany.diseniointeligente.Modelos;




 
  //@author Lorenzo Buero
 
public enum EHabilidadBasica {
    VOLAR,
    NADAR,
    ATAQUE_DE_ESPINAS,
    ROMPEHUESOS,
    GARRAZO;
    
    public static String aString(EHabilidadBasica habilidad){
        return switch (habilidad) {
            case VOLAR -> "Volar";
            case NADAR -> "Nadar";
            case ATAQUE_DE_ESPINAS -> "Ataque de Espinas";
            case ROMPEHUESOS -> "Rompehuesos";
            case GARRAZO -> "Garrazo";
            default -> "Error al cargar esta habilidad basica";
        };
    }
    public static EHabilidadBasica aEHabilidadBasica(String habilidad){
        return switch(habilidad){
            case "Volar" -> VOLAR;
            case "Nadar" -> NADAR;
            case "Ataque de Espinas" -> ATAQUE_DE_ESPINAS;
            case "Rompehuesos" -> ROMPEHUESOS;
            case "Garrazo" -> GARRAZO;
            default -> null;
        };
    }
}
