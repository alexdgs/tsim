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
    MejorIncrementoPrecio mip;
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
        mip = new MejorIncrementoPrecio(this);
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
            case MEJOR_PRECIO: return mip.nextStep();
        }
        return true;
    }
    
    public String getResultText(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return mih.getInforme();
            case MEJOR_PRECIO: return mip.getInforme();
        }
        return null;
    }
    
    // Get required table and send it to Controller
    public Object[][] getTabla(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return mih.getTabla();
            case MEJOR_PRECIO: return mip.getTabla();
            //case 2: return vph.getTablaVariacionPrecios();
            //case 3: return mc.getTablaMejorCombinacion();
        }
        return null;
    }
    public String [] getColums(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return mih.getColumns();
            case MEJOR_PRECIO: return mip.getColumns();
        }
        return null;
    }
    public int getSimple()
    {
        return cantidadHabitaciones[0];
    }
    public int getDoble()
    {
        return cantidadHabitaciones[1];
    }
    public int getSuite()
    {
        return cantidadHabitaciones[2];
    }
    
}
