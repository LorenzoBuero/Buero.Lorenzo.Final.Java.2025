/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author pirulo
 */
public enum EAtributoHabilidadExtra implements IAtributo{
    EFECTO,
    DESCRIPCION_OBJETIVO;
    
    public static String obtenerComoString(EAtributoHabilidadExtra atributo){
        return switch(atributo){
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
}
