package com.mycompany.diseniointeligente.GestionDeDatos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mycompany.diseniointeligente.Modelos.IParseable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.imageio.ImageIO;

/**
 *
 * @author Lorenzo Buero
 */
public final class EscritorDeArchivos <T extends IParseable>{

    private final Class<T> clase;
    
    public EscritorDeArchivos(Class<T> clase){
        this.clase = clase;
    }
    
    public static boolean guardarImagenAPNG(BufferedImage imagen, String direccion, String nombre) throws IOException{
        boolean retorno = false;
        
        File direccionArchivo = obtenerDireccionArchivo(direccion, nombre, ".png");

        ImageIO.write(imagen, "png", direccionArchivo);
        
        return retorno;
    }
    
    
    public boolean guardarComoCSV(T objetoAGuardar, String direccion, String nombre) throws IOException{
        //File direccionArchivo = obtenerDireccionArchivo(direccion, nombre, ".csv");
        throw new UnsupportedOperationException("No lo hice");
    }
    
    
    public boolean guardarComoJSON(List<T> objetoAGuardar, String direccion, String nombre) throws IOException{
       
        boolean retorno;
        
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter ow = mapper.writerFor(
                mapper.getTypeFactory()
                .constructCollectionType(List.class, this.clase))
                .withDefaultPrettyPrinter();
        
        File direccionArchivo = obtenerDireccionArchivo(direccion, nombre, ".json");
        
        try{    
            
            ow.writeValue(direccionArchivo, objetoAGuardar);        
            retorno = true;
            
        } catch(JsonProcessingException e){
            System.out.println(e.getMessage());
            throw new IOException(e.getMessage());
        }
        return retorno;
    }
    
    public List<T> leerDeJSON(String direccion, String nombre) throws IOException{
        ObjectMapper ow = new ObjectMapper();

        File direccionArchivo = obtenerDireccionArchivo(direccion, nombre, ".json");

        List<T> retorno = ow.readValue(direccionArchivo, 
                ow.getTypeFactory().constructCollectionType(List.class, this.clase));
        
        return retorno;
    }
    
    
    
    
    private static File obtenerDireccionArchivo(String direccion, String nombre, String extension) throws IOException{
        Path carpeta = Path.of(direccion);
        Files.createDirectories(carpeta);

        Path archivo = carpeta.resolve(nombre + extension);
        
        return archivo.toFile();
    }
}
