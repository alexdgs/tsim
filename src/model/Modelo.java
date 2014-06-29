/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.Controlador;
import javax.swing.Timer;

/**
 *  
 * @author Jose
 */
public class Modelo {
    
    // Simulation model constants
    final int MEJOR_HABITACIONES = 1;
    final int MEJOR_PRECIO = 2;
    final int MEJOR_COMBINACION = 3;
    
    Controlador c;
    Timer t;
    Datos datos;
    
    public Modelo(Controlador c) {
        datos = new Datos();
        this.c = c;
        t = new Timer(500,c);
    }

    public GeneradorCongruencialMixto getGCM() {
        return datos.getGeneradorCongruencialMixto();
    }

    public GeneradorVariableTriangular getGVT() {
        return datos.getGeneradorVariableTriangular();
    }

    public int getMEJOR_HABITACIONES() {
        return MEJOR_HABITACIONES;
    }

    public int getMEJOR_PRECIO() {
        return MEJOR_PRECIO;
    }

    public int getMEJOR_COMBINACION() {
        return MEJOR_COMBINACION;
    }

    public GeneradorCongruencialMixto getGcm() {
        return datos.getGeneradorCongruencialMixto();
    }

    public Controlador getC() {
        return c;
    }

    public MejorIncrementoHabitaciones getMih() {
        return datos.getMih();
    }

    public MejorIncrementoPrecio getMip() {
        return datos.getMip();
    }

    public Combinado getMc() {
        return datos.getMc();
    }

    public Timer getT() {
        return t;
    }
    
    public int[] getCantidadHabitaciones() {
        return datos.getCantidadHabitaciones();
    }

    public double[] getPrecioActual() {
        return datos.getPrecioActual();
    }

    public double[] getPrecioIncrementado() {
        return datos.getPrecioIncrementado();
    }

    public double[] getPreferenciaHabitaciones() {
        return datos.getPreferenciaHabitaciones();
    }

    public double[] getAceptacionIncremento() {
        return datos.getAceptacionIncremento();
    }
    
    public GeneradorCongruencialMixto getGeneradorCongruencialMixto() {
        return datos.getGeneradorCongruencialMixto();
    }
    
    public GeneradorVariableTriangular getGeneradorVariableTriangular() {
        return datos.getGeneradorVariableTriangular();
    }

    public void setCantidadHabitaciones(int simple, int doble, int suitejr) {
        datos.setCantidadHabitaciones(simple, doble, suitejr);
        datos.reiniciar();
    }

    public void setPrecioActual(double simple, double doble, double suitejr) {
        datos.setPrecioActual(simple, doble, suitejr);
        datos.reiniciar();
    }

    public void setPrecioIncrementado(double simple, double doble, double suitejr) {
        datos.setPrecioIncrementado(simple, doble, suitejr);
        datos.reiniciar();
    }

    public void setPreferenciaHabitaciones(double simple, double doble, double suitejr) {
        datos.setPreferenciaHabitaciones(simple, doble, suitejr);
        datos.reiniciar();
    }

    public void setAceptacionIncremento(double simple, double doble, double suitejr) {
        datos.setAceptacionIncremento(simple, doble, suitejr);
        datos.reiniciar();
    }
    
    public void setGeneradorCongruencialMixto(int cMultiplicativa, int cAditiva, int modulo, int semilla) {
        datos.getGeneradorCongruencialMixto().asignarValores(cMultiplicativa, cAditiva, modulo, semilla);
        datos.reiniciar();
    }
    
    public void setGeneradorVariableTriangular(int a, int b, int c){
        datos.getGeneradorVariableTriangular().asignarValores(a, b, c);
        datos.reiniciar();
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
      
    // Start simulation (re-create model for clean start)
    public void play() {
        datos.reiniciar();
        t.restart();
    }
    
    // Stop/Pause simulation (timer only)
    public void stop() {
        t.stop();
    }
    
    // Resume simulation (dont reset)
    public void resume() {
        t.start();
    }
    
    // Set simulation speed (timer delay)
    public void setSpeed(int i) {
        t.setDelay(i);
    }
    
    // Do next simulaton step and send response to Controller
    public Object[] nextStep(int op) {
        switch(op) {
            case MEJOR_HABITACIONES:
                datos.setUltimaOpcionCorrida(1);
                return datos.getMih().nextStep();
            case MEJOR_PRECIO:
                datos.setUltimaOpcionCorrida(2);
                return datos.getMip().nextStep();
            case MEJOR_COMBINACION:
                datos.setUltimaOpcionCorrida(3);
                return datos.getMc().nextStep();
        }
        return null;
    }
    
    public String getResultText(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return datos.getMih().getInforme();
            case MEJOR_PRECIO: return datos.getMip().getInforme();
            case MEJOR_COMBINACION: return datos.getMc().getInforme();
        }
        return null;
    }
    
    // Get required table and send it to Controller
    public Object[][] getTabla(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return datos.getMih().getTabla();
            case MEJOR_PRECIO: return datos.getMip().getTabla();
            case MEJOR_COMBINACION: return datos.getMc().getTabla();
        }
        return null;
    }
    public String [] getColums(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return datos.getMih().getColumns();
            case MEJOR_PRECIO: return datos.getMip().getColumns();
            case MEJOR_COMBINACION: return datos.getMc().getColumns();
        }
        return null;
    }
    
    public String [] getDatosSimulacion(int op) {
        switch(op) {
            case MEJOR_HABITACIONES: return datos.getMih().getDatosSimulacion();
            case MEJOR_PRECIO: return datos.getMip().getDatosSimulacion();
            case MEJOR_COMBINACION: return datos.getMc().getDatosSimulacion();
        }
        return null;
    }
        
    public int getSimple()
    {
        return datos.getCantidadHabitaciones()[0];
    }
    public int getDoble()
    {
        return datos.getCantidadHabitaciones()[1];
    }
    public int getSuite()
    {
        return datos.getCantidadHabitaciones()[2];
    }

    public int getUltimaOpcionCorrida() {
        return datos.getUltimaOpcionCorrida();
    }
    
    public int getTotalHabitaciones() {
        return datos.getTotalHabitaciones();
    }

    public Datos getDatos() {
        return datos;
    }

    public void setDatos(Datos dat) {
        datos = dat;
    }

    public void restablecer() {
        datos.restablecer();
    }
}
