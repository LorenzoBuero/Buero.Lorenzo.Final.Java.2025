package com.mycompany.diseniointeligente.GestionDeDatos;


import com.mycompany.diseniointeligente.Modelos.IParseable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 *
 * @author Lorenzo Buero
 */
public class Gestor<T extends IParseable> implements CRUD<T>{//, IParseable{
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
    public void crear(List<? extends T> elementosNuevos){
        Gestor.copiar(elementosNuevos, elementos);
        //elementos.addAll(elementosNuevos);
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
    
    public static <T> void copiar(
        List<? extends T> origen,
        List<? super T> destino){

            destino.addAll(origen);
    }
    
    public List<T> obtenerFiltrado(Predicate<? super T> condicion, GestorIterator<T> iterador){
        List<T> retorno = new ArrayList<>();
        
        while(iterador.hasNext()){
            T valor = iterador.next();
            if(condicion.test(valor)){
                retorno.add(valor);
            }
        }

        return retorno;
    }
    
    public List<T> obtenerOrdenado(BiFunction<? super T, ? super T, Integer> ordenador, List<T> lista){
        
        List<T> retorno = new ArrayList<>(lista);
        int tamanio = retorno.size();

        for(int i = 0; i < tamanio - 1; i++){

            for(int j = 0; j < tamanio - i - 1; j++){

                T actual = retorno.get(j);
                T siguiente = retorno.get(j + 1);

                if(ordenador.apply(actual, siguiente) > 0){

                    retorno.set(j, siguiente);
                    retorno.set(j + 1, actual);

                }
            }
        }

        return retorno;
    }
    
    /*@Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aTextoDescriptivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
