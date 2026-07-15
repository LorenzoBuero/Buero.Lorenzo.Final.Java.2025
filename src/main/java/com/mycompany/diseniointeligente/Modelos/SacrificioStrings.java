package com.mycompany.diseniointeligente.Modelos;

import java.util.ArrayList;

/**
 *
 * @author Lorenzo Buero
 */
public class SacrificioStrings extends Sacrificio{
    
    String valorBuscado;
    
    public SacrificioStrings(IAtributo atributo, String valorBuscado){
        this.atributoDeCarta = atributo;
        this.valorBuscado = valorBuscado;
    }
    
    @Override
    public Boolean calcularValidez(ISacrificable cartaAVerificar){
        Boolean retorno = false;
        
        if(cartaAVerificar.obtenerAtributo(atributoDeCarta).contains(valorBuscado)){
            retorno = true;
        }
        
        return retorno;
    }

    

}
