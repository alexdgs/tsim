/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.Controlador;
import java.util.ArrayList;
import javax.swing.Timer;
import static model.Modelo.diasDelMes;

/**
 *
 * @author User
 */
public class MejorIncrementoHabitaciones {
    
    private static final GeneradorCongruencialMixto GCM = new GeneradorCongruencialMixto(366,7,365,4);
    private static final GeneradorVariableTriangular GVT = new GeneradorVariableTriangular(43,65,87);
    private static final int[] CA = {77,7,3};
    private static final double[] PH = {0.56,0.15,0.29};
    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    
    ArrayList<Object[]> lista;
    int[] cantidadHabitaciones;
    double[] preferenciaHabitaciones;
    int n;
    int indiceGenerador = 0;
    int incSimple =0;
    int incDoble = 0;
    int incSuite = 0;
    int meses = 12;
    int i = 0;
    int j = 0;
    int diasDelMes;
    int L;
    int demSimple;
    int demDoble;
    int demSuite;
    Object [] tupla;
    
    public MejorIncrementoHabitaciones(Controlador c) {
        gcm = GCM;
        gvt = GVT;
        n = gcm.getM();
        L = gcm.getL();
        lista = new ArrayList<>();
        preferenciaHabitaciones = PH;
        cantidadHabitaciones = CA;
    }
    
    // Processes next simulation step (a single day)
    public boolean nextStep(){
        if(i < 12){
            // If isn't year end, do math...
            if(j < 1) {
                // If we're starting a new month, config values
                // System.out.print("Mes " + i + ": "); // Debug output
                diasDelMes = diasDelMes(i,2014);
                demSimple = 0;
                demDoble = 0;
                demSuite = 0;
                tupla = new Object[19];
            }
            
            if(j<diasDelMes){
                // If more days remain, do math...
                // (indiceGenerador % L) iterates over GCM values
                int demandaDia = gvt.generarValor(gcm.getListaNumerosAleatorios().get(indiceGenerador%L));
                int demandaSimpleDia = (int)(demandaDia*this.preferenciaHabitaciones[0]);
                int demandaDobleDia = (int)(demandaDia*this.preferenciaHabitaciones[1]);
                int demandaSuiteDia = (int)(demandaDia*this.preferenciaHabitaciones[2]);

                demSimple = demSimple + demandaSimpleDia;
                demDoble = demDoble + demandaDobleDia;
                demSuite = demSuite + demandaSuiteDia;

                j++;
                // System.out.print(j + " "); // Debug output
                indiceGenerador++;
            } else {
                // If we reached end of month, create table registry
                demSimple = (int)(demSimple/diasDelMes);
                demDoble = (int)(demDoble/diasDelMes);
                demSuite = (int)(demSuite/diasDelMes);
                int demInsSimple, demInsDoble, demInsSuite;
                if(demSimple>cantidadHabitaciones[0]){
                    demInsSimple = demSimple-cantidadHabitaciones[0];
                }
                else{
                    demInsSimple = 0;
                }
                if(demDoble>cantidadHabitaciones[1]){
                    demInsDoble = demDoble-cantidadHabitaciones[1];
                }
                else{
                    demInsDoble = 0;
                }
                if(demSuite>cantidadHabitaciones[2]){
                    demInsSuite = demSuite-cantidadHabitaciones[2];
                }
                else{
                    demInsSuite = 0;
                }

                tupla[0] = i+1;
                tupla[1] = demSimple;
                tupla[2] = demDoble;
                tupla[3] = demSuite;
                tupla[4] = cantidadHabitaciones[0];
                tupla[5] = cantidadHabitaciones[1];
                tupla[6] = cantidadHabitaciones[2];
                tupla[7] = demInsSimple;
                tupla[8] = demInsDoble;
                tupla[9] = demInsSuite;
                lista.add(tupla);

                incSimple = incSimple + demInsSimple;
                incDoble = incDoble + demInsDoble;
                incSuite = incSuite + demInsSuite;
                
                j = 0; // Reset current day
                i++; // Go next month
                // System.out.println(); // Debug output
            }
            return false; // We're not ready yet
        } else {
            // If we reached end of year, do final work
            incSimple = (int)incSimple/lista.size();
            incDoble = (int)incDoble/lista.size();
            incSuite = (int)incSuite/lista.size();
            return true; // All done, notify
        }
    }
    
    // Builds and returns result table
    public Object[][] getTabla() {
        Object[][] res = new Object[lista.size()][15];
        for(int k=0;k<res.length;k++){
            Object[] tupla = lista.get(k);
            tupla[10] = incSimple;
            tupla[11] = incDoble;
            tupla[12] = incSuite;
            tupla[13] = (int)tupla[4]+(int)tupla[10];
            tupla[14] = (int)tupla[5]+(int)tupla[11];
            tupla[15] = (int)tupla[6]+(int)tupla[12];
            
            if((int)tupla[1]>(int)tupla[13])
                tupla[16] = (int)tupla[1]-(int)tupla[13];
            else
                tupla[16] = 0;
            if((int)tupla[2]>(int)tupla[14])
            tupla[17] = (int)tupla[2]-(int)tupla[14];
            else
                tupla[17] = 0;
            if((int)tupla[3]>(int)tupla[15])
            tupla[18] = (int)tupla[3]-(int)tupla[15];
            else
                tupla[18] = 0;
            res[k] = tupla;
        }
        return res;
    }
    
    // Formats and returns informative text
    public String getInforme(){
        String res = new String("***Valores maximo y minimo de demanda del hotel***\n\n"+
                                "Maximo: "+gvt.getC()+"\nMinimo: "+gvt.getA()+"\n\n"+
                                "***Cantidad de habitaciones actualmente***\n\n"+
                                "Habitacion simple: "+cantidadHabitaciones[0]+"\nHabitacion doble: "+cantidadHabitaciones[1]+"\nSuite Jr: "+cantidadHabitaciones[2]+"\n");
        return res;
    }
}
