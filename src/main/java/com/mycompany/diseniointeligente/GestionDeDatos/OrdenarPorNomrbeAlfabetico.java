package com.mycompany.diseniointeligente.GestionDeDatos;

import com.mycompany.diseniointeligente.Modelos.Carta;
import java.util.Comparator;

/**
 *
 * @author Lorenzo Buero
 */
public class OrdenarPorNomrbeAlfabetico implements Comparator<Carta>{

    @Override
    public int compare(Carta carta1, Carta carta2) {
        return carta1.getNombre().compareToIgnoreCase(carta2.getNombre());
    }
}
