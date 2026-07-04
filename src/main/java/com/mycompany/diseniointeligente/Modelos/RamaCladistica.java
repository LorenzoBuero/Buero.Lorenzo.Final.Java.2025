package com.mycompany.diseniointeligente.Modelos;



import java.util.ArrayList;

 
  //@author Lorenzo Buero
 
public class RamaCladistica implements IParseable{
    
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
