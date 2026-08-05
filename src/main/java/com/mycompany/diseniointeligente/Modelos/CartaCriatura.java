package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import com.mycompany.diseniointeligente.Excepciones.ParametroObligatorioEsNullException;
//@author Lorenzo Buero

public final class CartaCriatura extends Carta implements IParseable, ISacrificable{
    
    String genero = null;
    String especie = null;
    ArrayList<EHabilidadBasica> habilidadesBasicas = null;
    HabilidadEspecial habilidadEspecial = null;
    Estadisticas estadisticas = null;
    EHabitat habitat = null;
    EDieta dieta = null;

    private static final char CARACTER_REPRESENTATIVO = ETipoCarta.CRIATURA.tipo;
    
    
    public CartaCriatura(String genero, String especie, NumeroIdentificador numId, 
            Estadisticas estadisticas, EHabitat habitat, EDieta dieta) throws
            ParametroObligatorioEsNullException{
        
        this(genero, especie, numId, null, null, null, estadisticas, habitat, dieta);
    }
    
    @JsonCreator
    private CartaCriatura(
            @JsonProperty("genero") String genero, 
            @JsonProperty("especie") String especie, 
            @JsonProperty("numId") NumeroIdentificador numId,
            @JsonProperty("urlImagen") String URL,
            @JsonProperty("habilidadesBasicas") ArrayList<EHabilidadBasica> habilidadesBasicas,
            @JsonProperty("habilidadEspecial") HabilidadEspecial habilidadEspecial,
            @JsonProperty("estadisticas") Estadisticas estadisticas,
            @JsonProperty("habitat") EHabitat habitat,
            @JsonProperty("dieta") EDieta dieta) throws
            ParametroObligatorioEsNullException{
    
        String nombreCarta = genero + " " + especie;
        
        super(nombreCarta, numId, URL);
        
        this.genero = genero;
        this.especie = especie;
        this.habilidadesBasicas = habilidadesBasicas;
        this.habilidadEspecial = habilidadEspecial;
        this.estadisticas = estadisticas;
        this.habitat = habitat;
        this.dieta = dieta;
        
        if(this.getNumId() != null){
            this.getNumId().setTipoCarta(this.getCaracterRepresentativo());
        }
        ArrayList<IAtributo> obligatoriosFaltantes = this.obtenerCamposObligatoriosVacios();
        if(!obligatoriosFaltantes.isEmpty()){
            throw new ParametroObligatorioEsNullException(obligatoriosFaltantes);
        }
    }
    
    
    @JsonIgnore
    @Override
    public String getNombre(){
        return super.getNombre();
    }
    
    public String getGenero(){
        return this.genero;
    }
    public void setGenero(String genero){
        this.genero = genero;
    }
    public String getEspecie(){
        return this.especie;
    }
    public void setEspecie(String especie){
        this.especie = especie;
    }
    
    public ArrayList<EHabilidadBasica> getHabilidadesBasicas() {
        return habilidadesBasicas;
    }

    public void setHabilidadesBasicas(ArrayList<EHabilidadBasica> habilidadesBasicas) {
        this.habilidadesBasicas = habilidadesBasicas;
    }

    public HabilidadEspecial getHabilidadEspecial() {
        return habilidadEspecial;
    }

    public void setHabilidadEspecial(HabilidadEspecial habilidadEspecial) {
        this.habilidadEspecial = habilidadEspecial;
    }

    public Estadisticas getEstadisticas() {
        return estadisticas;
    }

    public void setEstadisticas(Estadisticas estadisticas) {
        this.estadisticas = estadisticas;
    }

    public EHabitat getHabitat() {
        return habitat;
    }

    public void setHabitat(EHabitat habitat) {
        this.habitat = habitat;
    }

    public EDieta getDieta() {
        return dieta;
    }

    public void setDieta(EDieta dieta) {
        this.dieta = dieta;
    }
    
    
    @JsonIgnore
    @Override
    public Character getCaracterRepresentativo(){
        return CARACTER_REPRESENTATIVO;
    }
    
    /*@JsonIgnore
    public void setNumeroColeccion(int numero){
        this.getNumId().setNumeroColeccion(numero);
    }
    */
    
    @Override
    public Object obtenerAtributo(IAtributo atributoBuscado) {
        Object retorno = null;
        
        switch (atributoBuscado) {
            case EAtributoCarta atributoCast -> {
                switch(atributoCast){
                    case NUM_IDENT -> retorno = this.getNumId().getNumeroIdentificador();
                    case NOMBRE -> retorno = this.getNombre();
                }
            }
            
            case EAtributoCriatura atributoCast -> {    
                switch(atributoCast){
                    
                    //case CLADISTICA -> retorno = null;
                    
                    case HABILIDADES_BASICAS -> {
                        String aux = "";
                        for(EHabilidadBasica hab : this.habilidadesBasicas){
                            aux += hab.toString() + " ";
                        }
                        retorno = aux;
                    }
                    
                    case HABILIDAD_ESPECIAL -> retorno = this.habilidadEspecial.toString();
                    case ESTADISTICAS -> retorno = this.estadisticas;
                    case HABITAT -> retorno = this.habitat.toString();
                    case DIETA -> retorno = this.dieta.toString();
                }
            }
            default -> retorno = null;
        }
        return retorno;
    }
    
    

    @Override
    public String aCSV() {
        String retorno = super.aCSV();
        
        //retorno += cladistica.aCSV();
        
        for(EHabilidadBasica habilidadB : this.habilidadesBasicas){
            retorno += habilidadB;
            if(habilidadB != this.habilidadesBasicas.getLast()){
                retorno += " | ";
            }
            
        }
        retorno += " , ";
        
        retorno += this.habilidadEspecial.aCSV();
        
        retorno += " , ";
        
        retorno += estadisticas.aCSV();
        
        retorno += habitat.toString() + " , ";
        
        retorno += dieta.toString() + " ;";
        
        return retorno;
    }

    /*@Override
    public String aJSON(){
        try{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
        } catch (JsonProcessingException e){
            System.out.println(e.getMessage());
            return null;
        }
    }*/

    @Override
    public String aTextoDescriptivo() {
        String retorno = super.aTextoDescriptivo() + "\n";
        
        int numeroHabilidad = 1;
        if(this.habilidadesBasicas != null){
            for(EHabilidadBasica habilidadB : this.habilidadesBasicas){
                retorno += "Habilidad basica #" + numeroHabilidad + ": " + habilidadB + "\n";
                numeroHabilidad++;
            }
        }
        
        if(this.habilidadEspecial != null){
            retorno += this.habilidadEspecial.aTextoDescriptivo() + "\n";
        }
        
        retorno += estadisticas.aTextoDescriptivo() + "\n";
        
        retorno += "Habitat: " + habitat.toString() + "\n\n";
        
        retorno += "Dieta: " + dieta.toString() + "\n\n";
        
        return retorno;
    }
    
    @Override
    public ArrayList<IAtributo> obtenerCamposObligatoriosVacios(){
    
        ArrayList<IAtributo> retorno =  super.obtenerCamposObligatoriosVacios();
        
        if(this.dieta == null){
            retorno.add(EAtributoCriatura.DIETA);
        }
        if(this.estadisticas == null){
            retorno.add(EAtributoCriatura.ESTADISTICAS);
        }
        if(this.habitat == null){
            retorno.add(EAtributoCriatura.HABITAT);
        }
        
        return retorno;
    }
} 