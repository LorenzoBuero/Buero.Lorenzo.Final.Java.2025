package com.mycompany.diseniointeligente.GestionDeDatos;

import com.mycompany.diseniointeligente.Modelos.ETipoCarta;
import java.util.ArrayList;

/**
 *
 * @author LorenzoBuero
 */
public class ConfigFiltroYOrdenamiento {
    public boolean mostrarCriaturas;
    public boolean mostrarEventos;
    public boolean mostrarHabilidadesExtra;
    public boolean mostrarSinImagen;
    public boolean ordenarMayorAMenor;
    public OrdenamientoCartas ordenamiento;
    
    public ConfigFiltroYOrdenamiento(){
        this(true, true, true, false, OrdenamientoCartas.NOMBRE, true);
    }
    
    public ConfigFiltroYOrdenamiento(boolean mostrarCriaturas, boolean mostrarEventos,
    boolean mostrarHabilidadesExtra,boolean mostrarSinImagen,
    OrdenamientoCartas ordenamiento, boolean ordenarMayorAMenor){
        
        this.mostrarCriaturas = mostrarCriaturas;
        this.mostrarEventos = mostrarEventos;
        this.mostrarHabilidadesExtra = mostrarHabilidadesExtra;
        this.mostrarSinImagen = mostrarSinImagen;
        this.ordenarMayorAMenor = ordenarMayorAMenor;
        this.ordenamiento = ordenamiento;
    }
    
    public ArrayList<ETipoCarta> getTiposCartasPermitidas(){
        ArrayList<ETipoCarta> cartas = new ArrayList<>();
    
        if(this.mostrarCriaturas){
            cartas.add(ETipoCarta.CRIATURA);
        }        
        if(this.mostrarEventos){
            cartas.add(ETipoCarta.EVENTO);
        }
        if(this.mostrarHabilidadesExtra){
            cartas.add(ETipoCarta.HABILIDAD_EXTRA);
        }
        
        return cartas;
    }
    
}
