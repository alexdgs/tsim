/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.Controlador;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *  
 * @author Jose
 */
public class Modelo {
    
    private static final GeneradorCongruencialMixto GCM = new GeneradorCongruencialMixto(366,7,365,4);
    private static final GeneradorVariableTriangular GVT = new GeneradorVariableTriangular(43,65,87);
    private static final int[] CA = {77,7,3};
    private static final double[] PA = {63,73,83};
    private static final double[] PI = {78,93,108};
    private static final double[] PH = {0.56,0.15,0.29};
    private static final double[] AI = {0.18,0.22,0.24};
    
    // Simulation model constants
    final int MEJOR_HABITACIONES = 1;
    final int MEJOR_PRECIO = 2;
    final int MEJOR_COMBINACION = 3;
    
    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    int[] cantidadHabitaciones;
    double[] precioActual;
    double[] precioIncrementado;
    double[] preferenciaHabitaciones;
    double[] aceptacionIncremento;
    Controlador c;
    
    MejorIncrementoHabitaciones mih;
    Timer t;
    
    public Modelo(Controlador c) {
        gcm = GCM;
        gvt = GVT;
        cantidadHabitaciones = CA;
        precioActual = PA;
        precioIncrementado = PI;
        preferenciaHabitaciones = PH;
        aceptacionIncremento = AI;
        this.c = c;
        t = new Timer(500,c);
        mih = new MejorIncrementoHabitaciones(c);
    }
    
    public Object[][] generarTablaVariacionPrecios(){
        
        ArrayList<Object[]> lista;
        lista = new ArrayList<>();
        int n = gcm.getM();
        int indiceGenerador = 0;
        for(int i=0;i<12;i++){
            
            int acumSimpleAct = 0;
            int acumDobleAct = 0;
            int acumSuiteAct = 0;
            int acumSimpleInc = 0;
            int acumDobleInc = 0;
            int acumSuiteInc = 0;
            Object [] tupla = new Object[15];
            
            int j = 0;
            int diasDelMes = diasDelMes(i,2014);
            while(j<diasDelMes && indiceGenerador<n){
                int demandaDia = gvt.generarValor(gcm.getListaNumerosAleatorios().get(indiceGenerador));
                int demandaSimpleDiaT = (int)(demandaDia*this.preferenciaHabitaciones[0]);
                int demandaDobleDiaT = (int)(demandaDia*this.preferenciaHabitaciones[1]);
                int demandaSuiteDiaT = (int)(demandaDia*this.preferenciaHabitaciones[2]);
                int demandaSimpleDia;
                int demandaDobleDia;
                int demandaSuiteDia;
                if(demandaSimpleDiaT>cantidadHabitaciones[0])
                    demandaSimpleDia = cantidadHabitaciones[0];
                else
                    demandaSimpleDia = demandaSimpleDiaT;
                if(demandaDobleDiaT>cantidadHabitaciones[1])
                    demandaDobleDia = cantidadHabitaciones[1];
                else
                    demandaDobleDia = demandaDobleDiaT;
                if(demandaSuiteDiaT>cantidadHabitaciones[2])
                    demandaSuiteDia = cantidadHabitaciones[2];
                else
                    demandaSuiteDia = demandaSuiteDiaT;
                acumSimpleAct = acumSimpleAct + demandaSimpleDia;
                acumDobleAct = acumDobleAct + demandaDobleDia;
                acumSuiteAct = acumSuiteAct + demandaSuiteDia;
                int demandaSimpleDiaIncT = (int)(demandaSimpleDiaT*this.aceptacionIncremento[0]);
                int demandaDobleDiaIncT = (int)(demandaDobleDiaT*this.aceptacionIncremento[1]);
                int demandaSuiteDiaIncT = (int)(demandaSuiteDiaT*this.aceptacionIncremento[2]);
                int demandaSimpleDiaInc;
                int demandaDobleDiaInc;
                int demandaSuiteDiaInc;
                if(demandaSimpleDiaIncT>cantidadHabitaciones[0])
                    demandaSimpleDiaInc = cantidadHabitaciones[0];
                else
                    demandaSimpleDiaInc = demandaSimpleDiaIncT;
                if(demandaDobleDiaIncT>cantidadHabitaciones[1])
                    demandaDobleDiaInc = cantidadHabitaciones[1];
                else
                    demandaDobleDiaInc = demandaDobleDiaIncT;
                if(demandaSuiteDiaIncT>cantidadHabitaciones[2])
                    demandaSuiteDiaInc = cantidadHabitaciones[2];
                else
                    demandaSuiteDiaInc = demandaSuiteDiaIncT;
                acumSimpleInc = acumSimpleInc + demandaSimpleDiaInc;
                acumDobleInc = acumDobleInc + demandaDobleDiaInc;
                acumSuiteInc = acumSuiteInc + demandaSuiteDiaInc;
                j++;
                indiceGenerador++;
            }
            
            tupla[0] = i+1;
            tupla[1] = acumSimpleAct;
            tupla[2] = acumDobleAct;
            tupla[3] = acumSuiteAct;
            tupla[4] = acumSimpleAct*this.precioActual[0];
            tupla[5] = acumDobleAct*this.precioActual[1];
            tupla[6] = acumSuiteAct*this.precioActual[2];
            tupla[7] = (double)tupla[4] + (double)tupla[5] + (double)tupla[6];
            tupla[8] = acumSimpleInc;
            tupla[9] = acumDobleInc;
            tupla[10] = acumSuiteInc;
            tupla[11] = acumSimpleInc*this.precioIncrementado[0];
            tupla[12] = acumDobleInc*this.precioIncrementado[1];
            tupla[13] = acumSuiteInc*this.precioIncrementado[2];
            tupla[14] = (double)tupla[11] + (double)tupla[12] + (double)tupla[13];
            
            lista.add(tupla);
            
        }
        Object[][] res = new Object[lista.size()][15];
        for(int i=0;i<res.length;i++){
            res[i] = lista.get(i);
        }
        return res;
    }
    
    public int[] getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public double[] getPrecioActual() {
        return precioActual;
    }

    public double[] getPrecioIncrementado() {
        return precioIncrementado;
    }

    public double[] getPreferenciaHabitaciones() {
        return preferenciaHabitaciones;
    }

    public double[] getAceptacionIncremento() {
        return aceptacionIncremento;
    }
    
    public GeneradorCongruencialMixto getGeneradorCongruencialMixto() {
        return gcm;
    }
    
    public GeneradorVariableTriangular getGeneradorVariableTriangular() {
        return gvt;
    }

    public void setCantidadHabitaciones(int simple, int doble, int suitejr) {
        cantidadHabitaciones[0] = simple;
        cantidadHabitaciones[1] = doble;
        cantidadHabitaciones[2] = suitejr;
    }

    public void setPrecioActual(double simple, double doble, double suitejr) {
        precioActual[0] = simple;
        precioActual[1] = doble;
        precioActual[2] = suitejr;
    }

    public void setPrecioIncrementado(double simple, double doble, double suitejr) {
        precioIncrementado[0] = simple;
        precioIncrementado[1] = doble;
        precioIncrementado[2] = suitejr;
    }

    public void setPreferenciaHabitaciones(double simple, double doble, double suitejr) {
        preferenciaHabitaciones[0] = simple;
        preferenciaHabitaciones[1] = doble;
        preferenciaHabitaciones[2] = suitejr;
    }

    public void setAceptacionIncremento(double simple, double doble, double suitejr) {
        aceptacionIncremento[0] = simple;
        aceptacionIncremento[1] = doble;
        aceptacionIncremento[2] = suitejr;
    }
    
    public void setGeneradorCongruencialMixto(int cMultiplicativa, int cAditiva, int modulo, int semilla) {
        gcm.asignarValores(cMultiplicativa, cAditiva, modulo, semilla);
    }
    
    public void setGeneradorVariableTriangular(int a, int b, int c){
        gvt.asignarValores(a, b, c);
    }
    
    public static int diasDelMes(int mes, int año){
        switch(mes){
            case 0:  // Enero
            case 2:  // Marzo
            case 4:  // Mayo
            case 6:  // Julio
            case 7:  // Agosto
            case 9:  // Octubre
            case 11: // Diciembre
                return 31;
            case 3:  // Abril
            case 5:  // Junio
            case 8:  // Septiembre
            case 10: // Noviembre
                return 30;
            case 1:  // Febrero
                if ( ((año%100 == 0) && (año%400 == 0)) ||
                        ((año%100 != 0) && (año%  4 == 0))   )
                    return 29;  // Año Bisiesto
                else
                    return 28;
            default:
                throw new java.lang.IllegalArgumentException(
                "El mes debe estar entre 0 y 11");
        }
    }
    
        public String informeSegundoCaso(){
        String res = new String("***Valores maximo y minimo de demanda del hotel***\n\n"+
                                "Maximo: "+gvt.getC()+"\nMinimo: "+gvt.getA()+"\n\n"+
                                "***Cantidad de habitaciones actualmente***\n\n"+
                                "Habitacion simple: "+cantidadHabitaciones[0]+"\nHabitacion doble: "+cantidadHabitaciones[1]+"\nSuite Jr: "+cantidadHabitaciones[2]+"\n\n"+
                                "***Precio actual de las habitaciones***\n\n"+
                                "Habitacion simple: "+precioActual[0]+"\nHabitacion doble: "+precioActual[1]+"\nSuite Jr: "+precioActual[2]+"\n\n"+
                                "***Precio incrementado de las habitaciones***\n\n"+
                                "Habitacion simple: "+precioIncrementado[0]+"\nHabitacion doble: "+precioIncrementado[1]+"\nSuite Jr: "+precioIncrementado[2]+"\n\n"+
                                "***Porcentaje de preferencia por las habitaciones***\n\n"+
                                "Habitacion simple: "+preferenciaHabitaciones[0]+"\nHabitacion doble: "+preferenciaHabitaciones[1]+"\nSuite Jr: "+preferenciaHabitaciones[2]+"\n\n"+
                                "***Probabilidad de aceptacion del precio incrementado***\n\n"+
                                "Habitacion simple: "+aceptacionIncremento[0]+"\nHabitacion doble: "+aceptacionIncremento[1]+"\nSuite Jr: "+aceptacionIncremento[2]+"\n");
        return res;
    }
    
    // Start simulation
    public void play() {
        t.start();
    }
    
    // Stop simulation
    public void stop() {
        t.stop();
    }
    
    // Set simulation speed (timer delay)
    public void setSpeed(int i) {
        t.setDelay(i);
    }
    
    // Do next simulaton step and send response to Controller
    public boolean nextStep(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return mih.nextStep();
        }
        return true;
    }
    
    public String getResultText(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return mih.getInforme();
        }
        return null;
    }
    
    // Get required table and send it to Controller
    public Object[][] getTabla(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return mih.getTabla();
            //case 2: return vph.getTablaVariacionPrecios();
            //case 3: return mc.getTablaMejorCombinacion();
        }
        return null;
    }
    
}
