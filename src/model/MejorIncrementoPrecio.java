/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import static model.Modelo.diasDelMes;

/**
 *
 * @author Jose
 */
public class MejorIncrementoPrecio {
    
    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    int[] cantidadHabitaciones;
    double[] precioActual;
    double[] precioIncrementado;
    double[] preferenciaHabitaciones;
    double[] aceptacionIncremento;
    Object[][] tabla;
    int i,j,diasDelMes,n,indiceGenerador;
    
    public MejorIncrementoPrecio(Modelo modelo){
        gcm = modelo.getGeneradorCongruencialMixto();
        gvt = modelo.getGeneradorVariableTriangular();
        cantidadHabitaciones = modelo.getCantidadHabitaciones();
        precioActual = modelo.getPrecioActual();
        precioIncrementado = modelo.getPrecioIncrementado();
        preferenciaHabitaciones = modelo.getPreferenciaHabitaciones();
        aceptacionIncremento = modelo.getAceptacionIncremento();
        tabla = new Object[12][15];
        i = 0;
        j = 0;
        diasDelMes = 0;
        n = modelo.getGeneradorCongruencialMixto().getL();
        indiceGenerador = 0;
    }
    
    public boolean nextStep(){
        boolean res = false;
        if(i < 12){
            if(j == 0){
                //calculamos cuantos dias hay en el mes actual y formateamos la tupla correpondiente
                diasDelMes = diasDelMes(i,2014);
                tabla[i][0] = (int)i+1;
                tabla[i][1] = (int)0;
                tabla[i][2] = (int)0;
                tabla[i][3] = (int)0;
                tabla[i][4] = (double)0.0;
                tabla[i][5] = (double)0.0;
                tabla[i][6] = (double)0.0;
                tabla[i][7] = (double)0.0;
                tabla[i][8] = (int)0;
                tabla[i][9] = (int)0;
                tabla[i][10] = (int)0;
                tabla[i][11] = (double)0.0;
                tabla[i][12] = (double)0.0;
                tabla[i][13] = (double)0.0;
                tabla[i][14] = (double)0.0;
            }
            if(j < diasDelMes && indiceGenerador < n){
                //generamos la demanda del dia
                int demandaDia = gvt.generarValor(gcm.getListaNumerosAleatorios().get(indiceGenerador));
                //calculamos los valores de demanda por tipo de habitacion para la situacion actual
                int demandaSimpleDiaT = (int)(demandaDia*this.preferenciaHabitaciones[0]);
                int demandaDobleDiaT = (int)(demandaDia*this.preferenciaHabitaciones[1]);
                int demandaSuiteDiaT = (int)(demandaDia*this.preferenciaHabitaciones[2]);
                //acumulamos la demanda satisfecha con la demanda mensual
                if(demandaSimpleDiaT>cantidadHabitaciones[0])
                    tabla[i][1] = (int)tabla[i][1] + cantidadHabitaciones[0];
                else
                    tabla[i][1] = (int)tabla[i][1] + demandaSimpleDiaT;
                if(demandaDobleDiaT>cantidadHabitaciones[1])
                    tabla[i][2] = (int)tabla[i][2] + cantidadHabitaciones[1];
                else
                    tabla[i][2] = (int)tabla[i][2] + demandaDobleDiaT;
                if(demandaSuiteDiaT>cantidadHabitaciones[2])
                    tabla[i][3] = (int)tabla[i][3] + cantidadHabitaciones[2];
                else
                    tabla[i][3] = (int)tabla[i][3] + demandaSuiteDiaT;
                //calculamos los valores de demanda por tipo de habitacion para el incremento de precios
                demandaSimpleDiaT = (int)(demandaSimpleDiaT*aceptacionIncremento[0]);
                demandaDobleDiaT = (int)(demandaDobleDiaT*aceptacionIncremento[1]);
                demandaSuiteDiaT = (int)(demandaSuiteDiaT*aceptacionIncremento[2]);
                //acumulamos la demanda satisfecha con la demanda mensual
                if(demandaSimpleDiaT>cantidadHabitaciones[0])
                    tabla[i][8] = (int)tabla[i][8] + cantidadHabitaciones[0];
                else
                    tabla[i][8] = (int)tabla[i][8] + demandaSimpleDiaT;
                if(demandaDobleDiaT>cantidadHabitaciones[1])
                    tabla[i][9] = (int)tabla[i][9] + cantidadHabitaciones[1];
                else
                    tabla[i][9] = (int)tabla[i][9] + demandaDobleDiaT;
                if(demandaSuiteDiaT>cantidadHabitaciones[2])
                    tabla[i][10] = (int)tabla[i][10] + cantidadHabitaciones[2];
                else
                    tabla[i][10] = (int)tabla[i][10] + demandaSuiteDiaT;
                j++;
                indiceGenerador++;
            }
            else{
                //cuando se acaba el mes calculamos el valor de los ingresos generados por las habitaciones
                //actual
                tabla[i][4] = (double)((int)tabla[i][1]*precioActual[0]);
                tabla[i][5] = (double)((int)tabla[i][2]*precioActual[1]);
                tabla[i][6] = (double)((int)tabla[i][3]*precioActual[2]);
                tabla[i][7] = (double)tabla[i][4]+(double)tabla[i][5]+(double)tabla[i][6];
                //posible incremento
                tabla[i][11] = (double)((int)tabla[i][8]*precioIncrementado[0]);
                tabla[i][12] = (double)((int)tabla[i][9]*precioIncrementado[1]);
                tabla[i][13] = (double)((int)tabla[i][10]*precioIncrementado[2]);
                tabla[i][14] = (double)tabla[i][11]+(double)tabla[i][12]+(double)tabla[i][13];
                i++;
                j = 0;
            }
        }
        else{
            i = 0;
            j = 0;
            indiceGenerador = 0;
            res = true;
        }
        return res;
    }
    
    public Object[][] getTabla(){
        return tabla;
    }

    String getInforme() {
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
    public String [] getColumns()
    {
        String[] columns = {
                "Mes",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Total Ingreso Actual",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Total Ingreso Incrementado"
            };
        return columns;
    }
}
