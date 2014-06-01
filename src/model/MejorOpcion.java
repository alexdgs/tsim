/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author Jose
 */
public class MejorOpcion {
    
    //mejor numero total de habitaciones
    int numeroHabitacionesSimple;
    int numeroHabitacionesDoble;
    int numeroHabitacionesSuite;
    //mejor precio para cada tipo de habitacion
    double precioHabitacionSimple;
    double precioHabitacionDoble;
    double precioHabitacionSuite;
    //el ingreso generado con el numero de habitaciones y el precio que se indican arriba
    double ingresoAnualSimple;
    double ingresoAnualDoble;
    double ingresoAnualSuite;
    //el ingreso neto del año con esos valores
    double ingresoAnualTotal;
    //el modelo de donde se obtuvieron esos datos
    Modelo modelo;
    int mejorOpcionDeSimulacion;
    
    public MejorOpcion(){
        numeroHabitacionesSimple = 0;
        numeroHabitacionesDoble = 0;
        numeroHabitacionesSuite = 0;
        precioHabitacionSimple = 0.0;
        precioHabitacionDoble = 0.0;
        precioHabitacionSuite = 0.0;
        ingresoAnualSimple = 0.0;
        ingresoAnualDoble = 0.0;
        ingresoAnualSuite = 0.0;
        ingresoAnualTotal = 0.0;
    }

    public boolean comparar(Object[][] tabla, Modelo modelo){
        boolean seMejora = false;
        if(tabla[0].length > 10){
            double ingresoAnualT = 0.0;
            double ingresoAnualSi = 0.0;
            double ingresoAnualDo = 0.0;
            double ingresoAnualSu = 0.0;
            int indiceFinal = tabla[0].length-1;
            for(int i = 0; i < 12; i++){
                //acumulamos los ingresos mensuales (ultimas 4 columna)
                ingresoAnualT += (double)tabla[i][indiceFinal];
                ingresoAnualSi += (double)tabla[i][indiceFinal-1];
                ingresoAnualDo += (double)tabla[i][indiceFinal-2];
                ingresoAnualSu += (double)tabla[i][indiceFinal-3];
            }
            //comparamos opciones:
            //si la mejor nueva simulacion tiene un mejor ingreso que la mejor hasta ahora,
            //actualizamos la mejor simulacion
            if(ingresoAnualT > ingresoAnualTotal){
                numeroHabitacionesSimple = (int)tabla[0][indiceFinal-9];
                numeroHabitacionesDoble = (int)tabla[0][indiceFinal-8];
                numeroHabitacionesSuite = (int)tabla[0][indiceFinal-7];
                precioHabitacionSimple = (double)tabla[0][indiceFinal-6];
                precioHabitacionDoble = (double)tabla[0][indiceFinal-5];
                precioHabitacionSuite = (double)tabla[0][indiceFinal-4];
                ingresoAnualSimple = ingresoAnualSi;
                ingresoAnualDoble = ingresoAnualDo;
                ingresoAnualSuite = ingresoAnualSu;
                ingresoAnualTotal = ingresoAnualT;
                this.modelo = new Modelo(modelo);
                seMejora = true;
                if(tabla[0].length == 33)
                    mejorOpcionDeSimulacion = 1;
                if(tabla[0].length == 25)
                    mejorOpcionDeSimulacion = 2;
                if(tabla[0].length == 47)
                    mejorOpcionDeSimulacion = 3;
            }
        }
        return seMejora;
    }
    public int getNumeroHabitacionesSimple() {
        return numeroHabitacionesSimple;
    }

    public int getNumeroHabitacionesDoble() {
        return numeroHabitacionesDoble;
    }

    public int getNumeroHabitacionesSuite() {
        return numeroHabitacionesSuite;
    }

    public double getPrecioHabitacionSimple() {
        return precioHabitacionSimple;
    }

    public double getPrecioHabitacionDoble() {
        return precioHabitacionDoble;
    }

    public double getPrecioHabitacionSuite() {
        return precioHabitacionSuite;
    }

    public double getIngresoAnualSimple() {
        return ingresoAnualSimple;
    }

    public double getIngresoAnualDoble() {
        return ingresoAnualDoble;
    }

    public double getIngresoAnualSuite() {
        return ingresoAnualSuite;
    }

    public double getIngresoAnualTotal() {
        return ingresoAnualTotal;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public int getMejorOpcionDeSimulacion() {
        return mejorOpcionDeSimulacion;
    }
    
}
