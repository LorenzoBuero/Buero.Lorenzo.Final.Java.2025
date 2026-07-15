package com.mycompany.diseniointeligente.Modelos;

  //@author Lorenzo Buer
import com.fasterxml.jackson.core.JsonProcessingException;


public interface IParseable {
    
    public abstract String aCSV();
    
    public abstract String aJSON () throws JsonProcessingException;
    
    public abstract String aTextoDescriptivo();
}
