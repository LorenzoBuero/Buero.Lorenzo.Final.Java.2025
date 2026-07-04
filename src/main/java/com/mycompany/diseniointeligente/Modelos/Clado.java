package com.mycompany.diseniointeligente.Modelos;


 
import java.util.ArrayList;
//@author pirulo
 
public class Clado implements IParseable{
    
    String clado;
    
    public Clado(String clado){
        this.setClado(clado);
    }
    
    public String getClado(){
        return this.clado;
    }
    
    private void setClado(String clado){
        this.clado = clado;
    }
    
    
    public ArrayList<Clado> obtenerCladosAscendentes(){}
    public ArrayList<Clado> obtenerCladosDescendentes(){}
    public static Boolean certificarCladoValido(){}
    public static ArrayList<Clado> obtenerCladosAscendentes(String clado){}
    public static ArrayList<Clado> obtenerCladosDescendentes(Clado clado){}
    public static ArrayList<Clado> obtenerCladosAscendentes(String clado){}
    public static ArrayList<Clado> obtenerCladosDescendentes(Clado clado){}
}
