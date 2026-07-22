package com.mycompany.diseniointeligente.GestionDeDatos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lorenzo Buero
 */
public class Gestor<T> implements CRUD<T>{
    private final List<T> elementos;
            
    public Gestor(){
        elementos = new ArrayList<>();
    }
    
    @Override
    public void crear(T elemento) {
        elementos.add(elemento);
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
    
    public Iterator<T> iterator() {
        return new GestorIterator<>(elementos);
    }
}
