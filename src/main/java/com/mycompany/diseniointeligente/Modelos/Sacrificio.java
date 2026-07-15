package com.mycompany.diseniointeligente.Modelos;

import java.util.ArrayList;

/**
 *
 * @author Lorenzo Buero
 */
public abstract class Sacrificio {
    public IAtributo atributoDeCarta;
    
    public abstract Boolean calcularValidez(ISacrificable cartaAVerificar);
    
    public ArrayList<Carta> obtenerSacrificiosValidos(ArrayList<Carta> cartasAFiltrar) {
        ArrayList<Carta> retorno = new ArrayList<>();
    
        for(Carta carta : cartasAFiltrar){
            if(carta instanceof ISacrificable cartaSacrificable){
            
                if(this.calcularValidez(cartaSacrificable)){
                    retorno.add(carta);
                }               
            }
        }
        return retorno;
    }
}
