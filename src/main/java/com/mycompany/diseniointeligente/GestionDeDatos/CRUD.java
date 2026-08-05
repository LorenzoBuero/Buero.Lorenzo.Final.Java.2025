/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.diseniointeligente.GestionDeDatos;

import com.mycompany.diseniointeligente.Modelos.IParseable;
import java.util.List;

/**
 *
 * @author pirulo
 */
public interface CRUD<T extends IParseable> {
    void crear(T elemento);
    
    void crear(List<? extends T> elementos);

    List<T> leer();

    void actualizar(int indice, T elemento);

    void eliminar(int indice);
    
    public boolean cargarDatos(String carpeta, String nombre);
    
    public boolean guardarDatos(String carpeta, String nombre);

}
