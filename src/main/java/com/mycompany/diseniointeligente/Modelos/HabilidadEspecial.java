package com.mycompany.diseniointeligente.Modelos;


 
//@author pirulo
 
public class HabilidadEspecial implements IParseable{

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

    public String nombre;
    public String habilidad;

    public HabilidadEspecial(String nombre, String habilidad){
        this.nombre = nombre;
        this.habilidad = habilidad;
    }
    
}
