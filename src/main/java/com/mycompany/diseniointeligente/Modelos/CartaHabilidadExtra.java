package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;




  //@author Lorenzo Buero
 
public final class CartaHabilidadExtra extends Carta implements IParseable{

    
    
    private String efecto = null;
    private String descripcionObjetivos = null;
    //public RamaCladistica objetivosValidos;// = null;
    private static final char CARACTER_REPRESENTATIVO = 'H';
    
    public CartaHabilidadExtra(){}
    
    public CartaHabilidadExtra(String nombre, NumeroIdentificador numId, String efecto){
        //, RamaCladistica objValidos) {
        numId.setTipoCarta(CARACTER_REPRESENTATIVO);
        //this.efecto = efecto;
        //this.objetivosValidos = objValidos;
        super(nombre, numId);
    }
    
    public String getEfecto() {
        return efecto;
    }

    public void setEfecto(String efecto) {
        this.efecto = efecto;
    }

    public String getDescripcionObjetivos() {
        return descripcionObjetivos;
    }

    public void setDescripcionObjetivos(String descripcionObjetivos) {
        this.descripcionObjetivos = descripcionObjetivos;
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
        
        retorno += efecto + " , ";
        //retorno += objetivosValidos.aCSV() + " , ";

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
        retorno += "Efecto :" + efecto;
        //retorno += objetivosValidos.aTextoDescriptivo();
       
        return retorno;
    }
    
    @Override
    public ArrayList<IAtributo> obtenerCamposObligatoriosVacios(){
    
        ArrayList<IAtributo> retorno =  super.obtenerCamposObligatoriosVacios();
        
        if(this.efecto == null){
            retorno.add(EAtributoHabilidadExtra.EFECTO);
        }
        
        return retorno;
    }
    
}
