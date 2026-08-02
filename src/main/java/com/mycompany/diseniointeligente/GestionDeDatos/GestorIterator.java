package com.mycompany.diseniointeligente.GestionDeDatos;

import com.mycompany.diseniointeligente.Modelos.IParseable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Lorenzo Buero
 */
public class GestorIterator<T extends IParseable> implements Iterator<T> {

    private List<T> lista;
    private int indice = 0;

    public GestorIterator(List<T> lista) {
        this.lista = lista;
    }

    @Override
    public boolean hasNext() {
        return indice < lista.size();
    }

    @Override
    public T next() {
        return lista.get(indice++);
    }
}
