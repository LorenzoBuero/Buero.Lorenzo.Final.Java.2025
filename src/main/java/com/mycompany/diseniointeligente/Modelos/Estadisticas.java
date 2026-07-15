package com.mycompany.diseniointeligente.Modelos;



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
    
    public void agregarFase(AtaqueVida stat, CosteDeCrecimiento coste){
        this.progresoAtaqueVida.add(stat);
        this.requisitoCrecimiento.add(coste);
    }
    
    public int getCantidadEstadisticas(){
        return this.progresoAtaqueVida.size();
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
    public String aJSON() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aTextoDescriptivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
