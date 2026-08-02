package com.mycompany.diseniointeligente.Modelos;



import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

//@author Lorenzo Buero
public class Estadisticas implements IParseable{
    ArrayList<AtaqueVida> progresoAtaqueVida;
    ArrayList<CosteDeCrecimiento> requisitoCrecimiento;

    public Estadisticas(AtaqueVida ataqueVida){
        this.progresoAtaqueVida = new ArrayList<>();
        this.progresoAtaqueVida.add(ataqueVida);
        
        this.requisitoCrecimiento = new ArrayList<>();
        this.requisitoCrecimiento.add(null);
    }
    
    @JsonCreator
    private Estadisticas(
            @JsonProperty("progresoAtaqueVida") ArrayList<AtaqueVida> atqVida, 
            @JsonProperty("requisitoDeCrecimiento") ArrayList<CosteDeCrecimiento> req){
        
        this.requisitoCrecimiento = req;
        this.progresoAtaqueVida = atqVida;
    }
    
    
    public void agregarFase(AtaqueVida stat, CosteDeCrecimiento coste){
        this.progresoAtaqueVida.add(stat);
        this.requisitoCrecimiento.add(coste);
    }
    
    @JsonIgnore
    public int getCantidadEstadisticas(){
        return this.progresoAtaqueVida.size();
    }
    
    public ArrayList<AtaqueVida> getProgresoAtaqueVida(){
        return this.progresoAtaqueVida;
    }
    
    public ArrayList<CosteDeCrecimiento> getRequisitoCrecimiento(){
        return this.requisitoCrecimiento;
    }
    
    
    @Override
    public String toString(){
        String retorno = "";
        
        for(int i = 0; i < progresoAtaqueVida.size(); i++){
            retorno += this.unaEstadisticaToString(i) + "\n";
        }
        return retorno;
    }
    
    public String unaEstadisticaToString(int posicion){
        String retorno = "";
        retorno += this.progresoAtaqueVida.get(posicion).toString() + "    ";
            
        if(this.requisitoCrecimiento.get(posicion) != null){
            retorno += this.requisitoCrecimiento.get(posicion).toString();
        } else {
            retorno += " - ";
        }
        
        return retorno;
    }
            
    
    @Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

    @Override
    public String aTextoDescriptivo() {
        String retorno = "Estadisticas: \n";
        final String IDENTADO = "  ";
        
        for(int i = 0; i < this.getCantidadEstadisticas(); i++){
            retorno += IDENTADO + "Etapa " + i +  ": \n";
            
            if(i > 0){
                retorno += IDENTADO + IDENTADO + this.requisitoCrecimiento.get(i).aTextoDescriptivo() + "\n";
            }
            
            retorno += IDENTADO + IDENTADO + this.progresoAtaqueVida.get(i).aTextoDescriptivo() + "\n";
        }
        
        
        retorno += "\n";
        return retorno;
    }
}
