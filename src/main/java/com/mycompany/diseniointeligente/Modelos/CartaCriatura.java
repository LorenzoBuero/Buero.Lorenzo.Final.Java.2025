package com.mycompany.diseniointeligente.Modelos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
 
//@author Lorenzo Buero

public final class CartaCriatura extends Carta implements IParseable, ISacrificable{
    //RamaCladistica cladistica = null;
    String genero = null;
    String especie = null;
    ArrayList<EHabilidadBasica> habilidadesBasicas = null;
    HabilidadEspecial habilidadEspecial = null;
    Estadisticas estadisticas = null;
    EHabitat habitat = null;
    EDieta dieta = null;

    private static final char CARACTER_REPRESENTATIVO = 'H';
    
    public CartaCriatura(){}
    
    public CartaCriatura(String nombre) {
        super(nombre);
    }

    /*public RamaCladistica getCladistica() {
        return cladistica;
    }

    public void setCladistica(RamaCladistica cladistica) {
        this.cladistica = cladistica;
    }*/

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
    
    public void setNumeroColeccion(int numero){
        this.getNumId().setNumeroColeccion(numero);
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
                    
                    case HABILIDAD_ESPECIAL -> this.habilidadEspecial.toString();
                    case ESTADISTICAS -> this.estadisticas.toString();
                    case HABITAT -> this.habitat.toString();
                    case DIETA -> this.dieta.toString();
                }
            }
            default -> retorno = null;
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

    @Override
    public String aJSON() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(this);
        return json;
    }

    @Override
    public String aTextoDescriptivo() {
        String retorno = super.aTextoDescriptivo();
        
        
        int numeroHabilidad = 1;
        for(EHabilidadBasica habilidadB : this.habilidadesBasicas){
            retorno += "Habilidad basica #" + numeroHabilidad + ": " + habilidadB + "\n";
            numeroHabilidad++;
        }
        
        retorno += this.habilidadEspecial.aTextoDescriptivo();
        
        retorno += estadisticas.aTextoDescriptivo();
        
        retorno += "Habitat: " + habitat.toString() + "\n";
        
        retorno += "Dieta: " + dieta.toString() + "\n";
        
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