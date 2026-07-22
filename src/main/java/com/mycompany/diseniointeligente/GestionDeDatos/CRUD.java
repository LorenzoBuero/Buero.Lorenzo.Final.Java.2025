/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.diseniointeligente.GestionDeDatos;

import java.util.List;

/**
 *
 * @author pirulo
 */
public interface CRUD<T> {
    void crear(T elemento);

    List<T> leer();

    void actualizar(int indice, T elemento);

    void eliminar(int indice);
}
