package com.mycompany.diseniointeligente.Modelos;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;


//@author Lorenzo Buero
 

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "tipo"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = CartaCriatura.class, name = "Criatura"),
    @JsonSubTypes.Type(value = CartaHabilidadExtra.class, name = "Habilidad Extra"),
    @JsonSubTypes.Type(value = CartaEvento.class, name = "Evento")
})
public sealed abstract class Carta implements Comparable<Carta>, IParseable permits CartaCriatura, CartaEvento, CartaHabilidadExtra{
    
    private NumeroIdentificador numId;
    private String nombre;
    private String urlImagen;
    
    
    public Carta(String nombre, NumeroIdentificador numId, String URL) {
        
        this.numId = numId;
        this.nombre = nombre;
        this.urlImagen = URL;
    }
    
    public abstract Character getCaracterRepresentativo();
    
    
    public NumeroIdentificador getNumId(){
        return this.numId;
    }
    
    public void setNumId(NumeroIdentificador numId){
        this.numId = numId;
    }
    
    public String getNombre(){
        return this.nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getUrlImagen(){
        return this.urlImagen;
    }
    public void setUrlImagen(String url){
        this.urlImagen = url;
    }
    
    public boolean equals(Carta carta){
        boolean retorno = false;
        
        String numeroOtraCarta = carta.getNumId().getNumeroCarta();
        
        if( this.numId.getNumeroCarta().equals(numeroOtraCarta) ){
            retorno = true;
        }
        
        return retorno;
    }
    
    public boolean exactlyEquals(Carta carta){
        boolean retorno = false;
        
        String otraCartaID = carta.getNumId().getNumeroIdentificador();
        
        if( this.numId.getNumeroCarta().equals(otraCartaID) ){
            retorno = true;
        }
        
        return retorno;
    
    }
    
    public ArrayList<IAtributo> obtenerCamposObligatoriosVacios(){
    
        ArrayList<IAtributo> retorno = new ArrayList<>();
        
        if(this.getNombre() == null || this.getNombre().isBlank()){
            retorno.add(EAtributoCarta.NOMBRE);
        }
        if(this.getNumId() == null || this.getNumId().valoresIncompletos()){
            retorno.add(EAtributoCarta.NUM_IDENT);
        }
        return retorno;
    }
    
    @Override 
    public int compareTo(Carta comparado) {
        return this.getNumId().compareTo(comparado.getNumId());
    }
    
    @Override
    public String aCSV() {
        String retorno;
        
        retorno = this.getNumId().aCSV() + " , ";
        retorno += this.getNombre() + " , ";
        retorno += this.getUrlImagen();
        
        return retorno;
    }

    
    @Override
    public String aTextoDescriptivo() {
        String retorno;
        retorno = this.numId.aTextoDescriptivo();
        retorno += "Nombre: " + this.nombre + "\n";
        retorno += "URL: " + this.urlImagen + "\n";
        return retorno;
    }
    
}