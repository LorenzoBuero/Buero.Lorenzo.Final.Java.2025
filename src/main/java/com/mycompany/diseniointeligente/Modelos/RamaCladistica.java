package com.mycompany.diseniointeligente.Modelos;



import java.util.ArrayList;

 
  //@author Lorenzo Buero
 
public class RamaCladistica implements IParseable{

    @Override
    public String aCSV() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aJSON() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String aTextoDescriptivo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private ArrayList<Clado> lineaCladistica;
    private ArrayList<CosteDeCrecimiento> requisitoCrecimiento;

    public ArrayList<Clado> getLineaCladistica() {
        return lineaCladistica;
    }

    public void setLineaCladistica(ArrayList<Clado> lineaCladistica) {
        this.lineaCladistica = lineaCladistica;
    }

    public ArrayList<CosteDeCrecimiento> getRequisitoCrecimiento() {
        return requisitoCrecimiento;
    }

    public void setRequisitoCrecimiento(ArrayList<CosteDeCrecimiento> requisitoCrecimiento) {
        this.requisitoCrecimiento = requisitoCrecimiento;
    }
    
}
