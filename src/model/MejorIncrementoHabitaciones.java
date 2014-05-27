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
    
    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    int[] cantidadHabitaciones;
    double[] precioActual;
    double[] preferenciaHabitaciones;
    int n;
    int indiceGenerador = 0;
    int i = 0;
    int j = 0;
    int diasDelMes;
    int L;
    int incSimple, incDoble, incSuite;
    Object[][] tabla;
    
    public MejorIncrementoHabitaciones(Modelo modelo) {
        gcm = modelo.getGeneradorCongruencialMixto();
        gvt = modelo.getGeneradorVariableTriangular();
        n = gcm.getM();
        L = gcm.getL();
        preferenciaHabitaciones = modelo.getPreferenciaHabitaciones();
        cantidadHabitaciones = modelo.getCantidadHabitaciones();
        precioActual = modelo.getPrecioActual();
        incSimple = 0;
        incDoble = 0;
        incSuite = 0;
        tabla = new Object[12][33];
    }
    
    // Processes next simulation step (a single day)
    public boolean nextStep(){
        if(i < 12){
            // If isn't year end, do math...
            if(j < 1) {
                // If we're starting a new month, config values
                // System.out.print("Mes " + i + ": "); // Debug output
                diasDelMes = diasDelMes(i,2014);
                //numero de mes
                tabla[i][0] = (int)i+1;
                //demanda actual promedio de cada dia
                tabla[i][1] = (int)0;
                tabla[i][2] = (int)0;
                tabla[i][3] = (int)0;
                //nro actual de habitaciones
                tabla[i][4] = cantidadHabitaciones[0];;
                tabla[i][5] = cantidadHabitaciones[1];;
                tabla[i][6] = cantidadHabitaciones[2];;
                //demanda insatisfecha actual
                tabla[i][7] = (int)0;
                tabla[i][8] = (int)0;
                tabla[i][9] = (int)0;
                //incremento de habitaciones
                tabla[i][10] = (int)0;
                tabla[i][11] = (int)0;
                tabla[i][12] = (int)0;
                //nuevo numero de habitaciones
                tabla[i][13] = (int)0;
                tabla[i][14] = (int)0;
                tabla[i][15] = (int)0;
                //nueva demanda insatisfecha
                tabla[i][16] = (int)0;
                tabla[i][17] = (int)0;
                tabla[i][18] = (int)0;
                //ingresos actuales por tipo
                tabla[i][19] = (double)0;
                tabla[i][20] = (double)0;
                tabla[i][21] = (double)0;
                //ingresos actuales total por mes
                tabla[i][22] = (double)0;
                //nuevo numero de habitaciones
                tabla[i][23] = (int)0;
                tabla[i][24] = (int)0;
                tabla[i][25] = (int)0;
                //precio de las habitaciones
                tabla[i][26] = (int)0;
                tabla[i][27] = (int)0;
                tabla[i][28] = (int)0;
                //nuevos ingresos por tipo
                tabla[i][29] = (double)0;
                tabla[i][30] = (double)0;
                tabla[i][31] = (double)0;
                //nuevos ingresos total por mes
                tabla[i][32] = (double)0;
            }
            
            if(j<diasDelMes){
                // If more days remain, do math...
                // (indiceGenerador % L) iterates over GCM values
                int demandaDia = gvt.generarValor(gcm.getListaNumerosAleatorios().get(indiceGenerador%L));
                int demandaSimpleDia = (int)(demandaDia*this.preferenciaHabitaciones[0]);
                int demandaDobleDia = (int)(demandaDia*this.preferenciaHabitaciones[1]);
                int demandaSuiteDia = (int)(demandaDia*this.preferenciaHabitaciones[2]);
                //acumulamos la demanda actual del mes
                tabla[i][1] = (int)tabla[i][1] + demandaSimpleDia;
                tabla[i][2] = (int)tabla[i][2] + demandaDobleDia;
                tabla[i][3] = (int)tabla[i][3] + demandaSuiteDia;
                j++;
                // System.out.print(j + " "); // Debug output
                indiceGenerador++;
            } else {
                // If we reached end of month, create table registry
                tabla[i][1] = (int)((int)tabla[i][1]/diasDelMes);
                tabla[i][2] = (int)((int)tabla[i][2]/diasDelMes);
                tabla[i][3] = (int)((int)tabla[i][3]/diasDelMes);
                if((int)tabla[i][1]>cantidadHabitaciones[0]){
                    tabla[i][7] = (int)tabla[i][1]-cantidadHabitaciones[0];
                    tabla[i][19] = (double)(cantidadHabitaciones[0]*diasDelMes*precioActual[0]);
                    
                }
                else{
                    tabla[i][7] = 0;
                    tabla[i][19] = (double)(((int)tabla[i][1]-(int)tabla[i][7])*diasDelMes*precioActual[0]);
                }
                if((int)tabla[i][2]>cantidadHabitaciones[1]){
                    tabla[i][8] = (int)tabla[i][2]-cantidadHabitaciones[1];
                    tabla[i][20] = (double)(cantidadHabitaciones[1]*diasDelMes*precioActual[1]);
                }
                else{
                    tabla[i][8] = 0;
                    tabla[i][20] = (double)(((int)tabla[i][2]-(int)tabla[i][8])*diasDelMes*precioActual[1]);
                }
                if((int)tabla[i][3]>cantidadHabitaciones[2]){
                    tabla[i][9] = (int)tabla[i][3]-cantidadHabitaciones[2];
                    tabla[i][21] = (double)(cantidadHabitaciones[2]*diasDelMes*precioActual[2]);
                }
                else{
                    tabla[i][9] = 0;
                    tabla[i][21] = (double)(((int)tabla[i][3]-(int)tabla[i][9])*diasDelMes*precioActual[2]);
                }
                tabla[i][22] = (double)tabla[i][19] + (double)tabla[i][20] + (double)tabla[i][21];
                incSimple = incSimple + (int)tabla[i][7];
                incDoble = incDoble + (int)tabla[i][8];
                incSuite = incSuite + (int)tabla[i][9];
                j = 0; // Reset current day
                i++; // Go next month
                // System.out.println(); // Debug output
            }
            return false; // We're not ready yet
        } else {
            // If we reached end of year, do final work
            incSimple = (int)incSimple/12;
            incDoble = (int)incDoble/12;
            incSuite = (int)incSuite/12;
            return true; // All done, notify
        }
    }
    
    // Builds and returns result table
    public Object[][] getTabla() {
        for(int k=0;k<12;k++){
            //calculamos el numero de habitaciones a incrementar
            tabla[k][10] = incSimple;
            tabla[k][11] = incDoble;
            tabla[k][12] = incSuite;
            //calculamos el nuevo numero de habitaciones
            tabla[k][13] = (int)tabla[k][4]+(int)tabla[k][10];
            tabla[k][14] = (int)tabla[k][5]+(int)tabla[k][11];
            tabla[k][15] = (int)tabla[k][6]+(int)tabla[k][12];
            //calculamos la nueva demanda insatisfecha
            if((int)tabla[k][1]>(int)tabla[k][13]){
                tabla[k][16] = (int)tabla[k][1]-(int)tabla[k][13];
                tabla[k][29] = (double)((int)tabla[k][13]*diasDelMes*precioActual[0]);
            }
            else{
                tabla[k][16] = 0;
                tabla[k][29] = (double)(((int)tabla[k][1]-(int)tabla[k][16])*diasDelMes*precioActual[0]);
            }
            if((int)tabla[k][2]>(int)tabla[k][14]){
                tabla[k][17] = (int)tabla[k][2]-(int)tabla[k][14];
                tabla[k][30] = (double)((int)tabla[k][14]*diasDelMes*precioActual[1]);
            }
            else{
                tabla[k][17] = 0;
                tabla[k][30] = (double)(((int)tabla[k][2]-(int)tabla[k][17])*diasDelMes*precioActual[1]);
            }
            if((int)tabla[k][3]>(int)tabla[k][15]){
                tabla[k][18] = (int)tabla[k][3]-(int)tabla[k][15];
                tabla[k][31] = (double)((int)tabla[k][15]*diasDelMes*precioActual[2]);
            }
            else{
                tabla[k][18] = 0;
                tabla[k][31] = (double)(((int)tabla[k][3]-(int)tabla[k][18])*diasDelMes*precioActual[2]);
            }
            tabla[k][32] = (double)tabla[k][29] + (double)tabla[k][30] + (double)tabla[k][31];
            //nuevo numero de habitaciones
            tabla[k][23] = tabla[k][13];
            tabla[k][24] = tabla[k][14];
            tabla[k][25] = tabla[k][15];
            //precio de las habitaciones
            tabla[k][26] = precioActual[0];
            tabla[k][27] = precioActual[1];
            tabla[k][28] = precioActual[2];
            //
        }
        return tabla;
    }
    
    // Formats and returns informative text
    public String getInforme(){
        String res = new String("***Valores maximo y minimo de demanda del hotel***\n\n"+
                                "Maximo: "+gvt.getC()+"\nMinimo: "+gvt.getA()+"\n\n"+
                                "***Precio actual de cada habitacion***\n\n"+
                                "Habitacion simple: "+precioActual[0]+"\nHabitacion doble: "+precioActual[1]+"\nSuite Jr: "+precioActual[2]+"\n"+
                                "***Cantidad de habitaciones actualmente***\n\n"+
                                "Habitacion simple: "+cantidadHabitaciones[0]+"\nHabitacion doble: "+cantidadHabitaciones[1]+"\nSuite Jr: "+cantidadHabitaciones[2]+"\n");
        return res;
    }
    public String [] getColumns()
    {
        String[] columns = {
                "Mes",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "Nro. H. Simple", "Nro. H. Doble", "Nro. H. Suite Jr.",
                "D. Ins. Simple", "D. Ins. Doble", "D. Ins. Suite Jr.",
                "Inc. Simple", "Inc. Doble", "Inc. Suite Jr.",
                "Nro. H. Simple*", "Nro. H. Doble*", "Nro. H. Suite Jr.*",
                "D. Ins. Simple*", "D. Ins. Doble*", "D. Ins. Suite Jr.*"
            };
        return columns;
    }
}
