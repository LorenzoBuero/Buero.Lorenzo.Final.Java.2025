package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mycompany.diseniointeligente.Excepciones.ParametroObligatorioEsNullException;
import java.util.ArrayList;




  //@author Lorenzo Buero
 
public final class CartaHabilidadExtra extends Carta implements IParseable{

    
    
    private String efecto = null;
    private String descripcionObjetivos = null;
    private static final char CARACTER_REPRESENTATIVO = ETipoCarta.HABILIDAD_EXTRA.tipo;
    
    
    
    public CartaHabilidadExtra(String nombre, NumeroIdentificador numId, String efecto)
        throws ParametroObligatorioEsNullException{
        

        this(nombre, numId, null, efecto, null);
    }
    
    @JsonCreator
    private CartaHabilidadExtra(
            @JsonProperty("nombre") String nombreCarta,
            @JsonProperty("numId") NumeroIdentificador numId,
            @JsonProperty("urlImagen") String URL,
            @JsonProperty("efecto") String efecto,
            @JsonProperty("descripcionObjetivos") String descripcionObjetivos)
            throws ParametroObligatorioEsNullException{
    
        super(nombreCarta, numId, URL);
        
        this.efecto = efecto;
        this.descripcionObjetivos = descripcionObjetivos;
        
        if(this.getNumId() != null){
            this.getNumId().setTipoCarta(this.getCaracterRepresentativo());
        }
        
        ArrayList<IAtributo> obligatoriosFaltantes = this.obtenerCamposObligatoriosVacios();
        if(!obligatoriosFaltantes.isEmpty()){
            throw new ParametroObligatorioEsNullException(obligatoriosFaltantes);
        }
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
        
        if(this.efecto == null || this.efecto.isBlank()){
            retorno.add(EAtributoHabilidadExtra.EFECTO);
        }
        
        return retorno;
    }
    
}
