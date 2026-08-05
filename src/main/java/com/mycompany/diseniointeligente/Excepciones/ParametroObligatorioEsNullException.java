package com.mycompany.diseniointeligente.Excepciones;

import java.util.ArrayList;
import com.mycompany.diseniointeligente.Modelos.IAtributo;

/**
 * @author Lorenzo Buero
 */
public class ParametroObligatorioEsNullException extends Exception{
    
    ArrayList<IAtributo> camposRequeridos;// = "";
    
    public ParametroObligatorioEsNullException(ArrayList<IAtributo> parametrosVacios){
        String campos = obtenerCamposRequeridosComoString(parametrosVacios);
        
        String mensaje = "ERROR, el/los campo/s obligatorio/s de: ";
        mensaje += campos;
        mensaje += " estan vacios o son null";
        
        super(mensaje);
        
        this.camposRequeridos = parametrosVacios;
    }
    
    public ArrayList<IAtributo> getCamposRequeridos(){
        return this.camposRequeridos;
    }
    
    private static String obtenerCamposRequeridosComoString(ArrayList<IAtributo> parametrosVacios){
        
        String valorAsignado = "";
        
        for(IAtributo parametro : parametrosVacios){
            valorAsignado += parametro.toString();
            if(!valorAsignado.equals("")){
                valorAsignado += ", ";
            }
        }
    
        return valorAsignado;
    }
    
    
}
