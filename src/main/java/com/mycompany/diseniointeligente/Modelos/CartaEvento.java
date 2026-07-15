package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;

/**
 *
 * @author Lorenzo Buero
 */
public final class CartaEvento extends Carta implements IParseable, ISacrificable{

    private String efecto = null;
    private String descripcionSacrificio = null;
    private Sacrificio sacrificio = null;
    
    private static final char CARACTER_REPRESENTATIVO = 'E';
    
    
    public CartaEvento(){}
    
    public CartaEvento(String nombre) {
        super(nombre);
    }
    
    public void setEfecto(String efecto){
        this.efecto = efecto;
    }
    public String getEfecto(){
        return this.efecto;
    }
    
    public String getDescripcionSacrificio(){
        return this.descripcionSacrificio;    
    }
    public void setDescripcionSacrificio(String descripcion){
        this.descripcionSacrificio = descripcion;
    }
    
    public Sacrificio getSacrificio(){
        return this.sacrificio;
    }
    public void setSacrificio(Sacrificio sacrificio){
        this.sacrificio = sacrificio;
    }
    
    
    @Override
    public String obtenerAtributo(IAtributo atributoBuscado) {
        String retorno = null;
        
        switch (atributoBuscado) {
            case EAtributoCarta atributoCast -> {
                switch(atributoCast){
                    case NUM_IDENT -> retorno = this.getNumId().getNumeroIdentificador();
                    case NOMBRE -> retorno = this.getNombre();
                }
            }
            case EAtributoEvento atributoCast -> {
                switch(atributoCast){
                    case EFECTO -> retorno = this.getEfecto();
                    case DESCRIPCION_SACRIFICIO -> retorno = this.getDescripcionSacrificio();
                }
            }
            default -> retorno = null;
        }
        
        return retorno;
    }
    
    @Override
    public ArrayList<IAtributo> obtenerCamposObligatoriosVacios(){
    
        ArrayList<IAtributo> retorno =  super.obtenerCamposObligatoriosVacios();
        
        if(this.efecto == null){
            retorno.add(EAtributoEvento.EFECTO);
        }
        
        return retorno;
    }

    
    @Override
    public char getCaracterRepresentativo(){
        return CARACTER_REPRESENTATIVO;
    }

    @Override
    public int compareTo(Carta t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aCSV() {
        String retorno = super.aCSV();
        
        
        return retorno;
    }

    @Override
    public String aJSON() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }

    @Override
    public String aTextoDescriptivo() {
        String retorno = super.aTextoDescriptivo();
        
        return retorno;
    }
    
    
}
