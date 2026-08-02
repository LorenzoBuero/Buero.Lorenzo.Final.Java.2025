package com.mycompany.diseniointeligente.Modelos;

/**
 *
 * @author pirulo
 */
public enum EOperacionSacrificio {
    IGUALDAD,
    MENOR,
    MAYOR/*,
    CONTENER*/;
    
    
    public boolean compararElementos(Object valorBuscado, Object valorDeCarta){
        Boolean retorno = false;
        
        switch(this){
            case IGUALDAD:
                if(valorBuscado instanceof String comp1 && valorDeCarta instanceof String comp2){
                    if(comp1.equals(comp2)){
                        retorno = true;
                    }
                } else if(valorBuscado instanceof Integer comp1 && valorDeCarta instanceof Integer comp2){
                    if(comp1.equals(comp2)){
                        retorno = true;
                    }
                } 
                break;
            
            case MENOR:
                if(valorBuscado instanceof Integer comp1 && valorDeCarta instanceof Integer comp2){
                    if(comp1 < comp2){
                        retorno = true;
                    }
                } 
                break;
            case MAYOR:
                if(valorBuscado instanceof Integer comp1 && valorDeCarta instanceof Integer comp2){
                    if(comp1 > comp2){
                        retorno = true;
                    }
                } 
                break;
        }
        
        return retorno;
    }
    
    
}
