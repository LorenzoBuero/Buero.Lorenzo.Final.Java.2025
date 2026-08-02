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
    
    public Carta(){}
    
    public Carta(String nombre){
        this.nombre = nombre;
    }
    
    public Carta(String nombre, NumeroIdentificador numId){
        this.numId = numId;   
        this(nombre);
    }
    
    public Carta(String nombre, NumeroIdentificador numId, String URL){
        this.numId = numId;   
        this.urlImagen = URL;
        this(nombre);
    }
    
    
    public Carta(String nombre, char tipoCarta, int numeroDeCarta, int numeroDeColeccion){
        NumeroIdentificador numID = new NumeroIdentificador(tipoCarta, numeroDeCarta, numeroDeColeccion);
        this(nombre, numID);
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
        
        if(this.getNombre() == null){
            retorno.add(EAtributoCarta.NOMBRE);
        }
        if(this.getNumId() == null){
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