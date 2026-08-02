package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    
    @JsonCreator
    private CartaHabilidadExtra(
            @JsonProperty("numId") NumeroIdentificador numId,
            @JsonProperty("urlImagen") String URL,
            @JsonProperty("nombre") String nombreCarta,
            @JsonProperty("efecto") String efecto,
            @JsonProperty("descripcionObjetivos") String descripcionObjetivos){
    
        
        super(nombreCarta, numId, URL);
        
        this.efecto = efecto;
        this.descripcionObjetivos = descripcionObjetivos;
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
    
    
    @JsonIgnore
    @Override
    public Character getCaracterRepresentativo(){
        return CARACTER_REPRESENTATIVO;
    }

    @Override
    public String aCSV() {
        String retorno = super.aCSV();
        
        retorno += efecto + " , ";
        //retorno += objetivosValidos.aCSV() + " , ";

        return retorno;
    }

    

    @Override
    public String aTextoDescriptivo() {
        String retorno = super.aTextoDescriptivo() + "\n";        
        
        retorno += "Efecto: " + efecto + "\n\n";
        
        retorno += "Objetivos: " + this.descripcionObjetivos + "\n\n";
       
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
