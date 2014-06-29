/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import controller.Controlador;
import java.io.Serializable;
import javax.swing.Timer;

/**
 *
 * @author Jose
 */
public class Datos implements Serializable {
    
    private static final GeneradorCongruencialMixto GCM = new GeneradorCongruencialMixto(366,7,365,4);
    private static final GeneradorVariableTriangular GVT = new GeneradorVariableTriangular(43,65,87);
    private static final int[] CA = {77,7,3};
    private static final double[] PA = {63,73,83};
    private static final double[] PI = {78,93,108};
    private static final double[] PH = {0.56,0.15,0.29};
    private static final double[] AI = {0.18,0.22,0.24};
    
    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    int[] cantidadHabitaciones;
    double[] precioActual;
    double[] precioIncrementado;
    double[] preferenciaHabitaciones;
    double[] aceptacionIncremento;
    int ultimaOpcionCorrida;
    
    MejorIncrementoHabitaciones mih;
    MejorIncrementoPrecio mip;
    Combinado mc;
    
    MejorOpcion mejorOpcion;
    
    public Datos(){
        gcm = GCM;
        gvt = GVT;
        cantidadHabitaciones = CA;
        precioActual = PA;
        precioIncrementado = PI;
        preferenciaHabitaciones = PH;
        aceptacionIncremento = AI;
        mih = new MejorIncrementoHabitaciones(this);
        mip = new MejorIncrementoPrecio(this);
        mc = new Combinado(this);
        ultimaOpcionCorrida = 0;
        mejorOpcion = new MejorOpcion();
    }

    public MejorOpcion getMejorOpcion() {
        return mejorOpcion;
    }

    Datos(Datos datos) {
        gcm = datos.getGeneradorCongruencialMixto();
        gvt = datos.getGeneradorVariableTriangular();
        cantidadHabitaciones = datos.getCantidadHabitaciones();
        precioActual = datos.getPrecioActual();
        precioIncrementado = datos.getPrecioIncrementado();
        preferenciaHabitaciones = datos.getPreferenciaHabitaciones();
        aceptacionIncremento = datos.getAceptacionIncremento();
        mih = new MejorIncrementoHabitaciones(datos);
        mip = new MejorIncrementoPrecio(datos);
        mc = new Combinado(datos);
        ultimaOpcionCorrida = datos.getUltimaOpcionCorrida();
    }

    public GeneradorCongruencialMixto getGeneradorCongruencialMixto() {
        return gcm;
    }

    public GeneradorVariableTriangular getGeneradorVariableTriangular() {
        return gvt;
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

    public int getUltimaOpcionCorrida() {
        return ultimaOpcionCorrida;
    }

    public MejorIncrementoHabitaciones getMih() {
        return mih;
    }

    public Combinado getMc() {
        return mc;
    }
    
    public MejorIncrementoPrecio getMip(){
        return mip;
    }

    public void setGeneredorCongruencialMixto(GeneradorCongruencialMixto gcm) {
        this.gcm = gcm;
    }

    public void setGeneradorVariabletriangular(GeneradorVariableTriangular gvt) {
        this.gvt = gvt;
    }

    public void setCantidadHabitaciones(int simple, int doble, int suitejr) {
        cantidadHabitaciones = new int [3];
        cantidadHabitaciones[0] = simple;
        cantidadHabitaciones[1] = doble;
        cantidadHabitaciones[2] = suitejr;
    }

    public void setPrecioActual(double simple, double doble, double suitejr) {
        precioActual = new double [3];
        precioActual[0] = simple;
        precioActual[1] = doble;
        precioActual[2] = suitejr;
    }

    public void setPrecioIncrementado(double simple, double doble, double suitejr) {
        precioIncrementado=new double [3];
        precioIncrementado[0] = simple;
        precioIncrementado[1] = doble;
        precioIncrementado[2] = suitejr;
    }

    public void setPreferenciaHabitaciones(double simple, double doble, double suitejr) {
        preferenciaHabitaciones = new double [3];
        preferenciaHabitaciones[0] = simple;
        preferenciaHabitaciones[1] = doble;
        preferenciaHabitaciones[2] = suitejr;
    }

    public void setAceptacionIncremento(double simple, double doble, double suitejr) {
        aceptacionIncremento =new double [3];
        aceptacionIncremento[0] = simple;
        aceptacionIncremento[1] = doble;
        aceptacionIncremento[2] = suitejr;
    }

    public void setUltimaOpcionCorrida(int ultimaOpcionCorrida) {
        this.ultimaOpcionCorrida = ultimaOpcionCorrida;
    }

    public void setMih(MejorIncrementoHabitaciones mih) {
        this.mih = mih;
    }

    public void setMip(MejorIncrementoPrecio mip) {
        this.mip = mip;
    }

    public void setMc(Combinado mc) {
        this.mc = mc;
    }
    
    
    void reiniciar(){
        mih = new MejorIncrementoHabitaciones(this);
        mip = new MejorIncrementoPrecio(this);
        mc = new Combinado(this);
    }
    
    public int getTotalHabitaciones() {
        return cantidadHabitaciones[0] + cantidadHabitaciones[1] + cantidadHabitaciones[2];
    }

    public void restablecer() {
        setCantidadHabitaciones(CA[0], CA[1], CA[2]);
        setPrecioActual(PA[0], PA[1], PA[2]);
        setPrecioIncrementado(PI[0], PI[1], PI[2]);
        setPreferenciaHabitaciones(PH[0], PH[1], PH[2]);
        setAceptacionIncremento(AI[0], AI[1], AI[2]);
   }
}
