package com.mycompany.diseniointeligente.Modelos;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 *
 * @author Lorenzo Buero
 */
public class Sacrificio {
    
    private EOperacionSacrificio operacion;
    private Object valorBuscado;
    private IAtributo atributoDeCarta;
    
    public Sacrificio(EOperacionSacrificio operacion, IAtributo atributoDeCarta, Object valorBuscado){
        this.operacion = operacion;
        this.atributoDeCarta = atributoDeCarta;
        this.valorBuscado = valorBuscado;
    }
    
    @JsonCreator
    private Sacrificio(
            @JsonProperty("operacion") EOperacionSacrificio operacion, 
            @JsonProperty("atributoDeCarta") String atributoDeCarta, 
            @JsonProperty("valorBuscado") Object valorBuscado){
        
        this.operacion = operacion;
        this.valorBuscado = valorBuscado;
    
        
        IAtributo atributoIngresado;
        //esto es así porque "atributoDeCarta" no puede ser una interfaz (IAtributo)
        //porque si lo fuera JsonProperty no podría asignar el valor al no saber si
        //es un valor posible, IAtributo es una Interfáz, no un Enum.
        try{
            atributoIngresado = EAtributoCarta.valueOf(atributoDeCarta);
       
        } catch(IllegalArgumentException _){
            try{
            atributoIngresado = EAtributoCriatura.valueOf(atributoDeCarta);
            
            } catch(IllegalArgumentException _){
                try{
                    atributoIngresado = EAtributoEvento.valueOf(atributoDeCarta);
                    
                } catch(IllegalArgumentException _){
                    atributoIngresado = null;
                }
            }
        }
        
        this.atributoDeCarta = atributoIngresado;
    }

    public EOperacionSacrificio getOperacion() {
        return operacion;
    }

    public Object getValorBuscado() {
        return valorBuscado;
    }

    public IAtributo getAtributoDeCarta() {
        return atributoDeCarta;
    }
    
    public ArrayList<ISacrificable> obtenerSacrificiosValidos(ArrayList<ISacrificable> sacrificiosAFiltrar) {
        ArrayList<ISacrificable> retorno = new ArrayList<>();
    
        for(ISacrificable sacrificio : sacrificiosAFiltrar){
            
            boolean esValido = operacion.compararElementos(this.valorBuscado, 
                            sacrificio.obtenerAtributo(atributoDeCarta));
            if(esValido){
                retorno.add(sacrificio);
            }
        }
        return retorno;
    }
    
}