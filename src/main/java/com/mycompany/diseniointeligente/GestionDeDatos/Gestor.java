package com.mycompany.diseniointeligente.GestionDeDatos;


import com.mycompany.diseniointeligente.Modelos.IParseable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lorenzo Buero
 */
public class Gestor<T extends IParseable> implements CRUD<T>, IParseable{
    private final List<T> elementos;
    private final Class<T> clase;
    
    public Gestor(Class<T> clase){
        elementos = new ArrayList<>();
        this.clase = clase;
    }
    
    @Override
    public void crear(T elemento) {
        elementos.add(elemento);
    }
    
    @Override
    public void crear(List<T> elementos){
        this.elementos.addAll(elementos);
    }

    @Override
    public List<T> leer() {
        return elementos;
    }

    @Override
    public void actualizar(int indice, T elemento) {
        elementos.set(indice, elemento);
    }

    @Override
    public void eliminar(int indice) {
        elementos.remove(indice);
    }
    
    public GestorIterator<T> iterator() {
        return new GestorIterator<>(elementos);
    }

    @Override
    public boolean guardarDatos(String carpeta, String nombre){
        boolean retorno;
        
        EscritorDeArchivos<T> escritor = new EscritorDeArchivos<>(clase);
    
        try{
            escritor.guardarComoJSON(this.elementos, carpeta, nombre);
            retorno = true;
            
        } catch(IOException e){
            System.out.println(e.getMessage());
            retorno = false;
        }
        
        return retorno;
    }

    @Override
    public boolean cargarDatos(String carpeta, String nombre){
        boolean retorno;
        
        EscritorDeArchivos<T> escritor = new EscritorDeArchivos<>(clase);

        try{
            this.crear(escritor.leerDeJSON(carpeta, nombre));
            retorno = true;
            
        } catch(IOException e){
            System.out.println(e.getMessage());
            retorno = false;
        }
        return retorno;
    }
    
    
    
    @Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aTextoDescriptivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
