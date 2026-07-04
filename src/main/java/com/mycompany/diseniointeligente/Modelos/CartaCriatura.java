package com.mycompany.diseniointeligente.Modelos;



import java.util.ArrayList;
 
//@author Lorenzo Buero
 
public final class CartaCriatura extends Carta implements IParseable{
    RamaCladistica cladistica;
    ArrayList<EHabilidadBasica> habilidadesBasicas;
    ArrayList<HabilidadEspecial> habilidadesEspeciales;
    Estadisticas estadisticas;
    ArrayList<EHabitat> habitats;
    EDieta dieta;
    
    CartaCriatura(){
        getCaracterRepresentativo();
    }
    
    @Override
    public char getCaracterRepresentativo(){
        return 'C';
    }
    
}
