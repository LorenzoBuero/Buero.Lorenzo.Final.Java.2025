/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.diseniointeligente.Modelos;

import java.util.ArrayList;

/**
 *
 * @author Lorenzo Buero
 */

public class SacrificioNumero extends Sacrificio{

    int valorBuscado;

    @Override
    public Boolean calcularValidez(ISacrificable cartaAVerificar) {
        Boolean retorno = false;
        
        if(cartaAVerificar.obtenerAtributo(atributoDeCarta).equals(valorBuscado)){
            retorno = true;
        }
        
        return retorno;
    }

    
    
}
