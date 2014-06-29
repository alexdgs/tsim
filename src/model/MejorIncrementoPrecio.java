/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;
import static model.Modelo.diasDelMes;

/**
 *
 * @author Jose
 */
public class MejorIncrementoPrecio implements Serializable {

    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    int[] cantidadHabitaciones;
    double[] precioActual;
    double[] precioIncrementado;
    double[] preferenciaHabitaciones;
    double[] aceptacionIncremento;
    Object[][] tabla;
    int i,j,diasDelMes,n,indiceGenerador;
    boolean mejoraSimple, mejoraDoble, mejoraSuite;
    double ingAcumActSimple;
    double ingAcumActDoble;
    double ingAcumActSuite;
    double ingAcumIncSimple;
    double ingAcumIncDoble;
    double ingAcumIncSuite;
    
    /*
    * The real-time data retrieval array
    * This Object array gets data from model and send it to Control to Simulator
    * for animation purposes. As new data is generated at every step, this array
    * is sent at every step.
    * 
    * data[0] (int) daily generated demand
    * data[1] (int) daily demand for "Simple" roooms for new price
    + data[2] (int) daily demand for "Doble" rooms for new price
    * data[3] (int) daily demand for "Suite Jr." rooms for new price
    * data[4] (double) monthly overall revenue for new price
    * data[7] (int) current month
    * data[8] (boolean) false = daily report, true = monthly report
    * data[9] (boolean) false = sim not finished, true = sim finished
    */
    
    Object data[];
    int dia;
    
    public MejorIncrementoPrecio(Datos datos){
        
        gcm = datos.getGeneradorCongruencialMixto();
        gvt = datos.getGeneradorVariableTriangular();
        cantidadHabitaciones = datos.getCantidadHabitaciones();
        precioActual = datos.getPrecioActual();
        precioIncrementado = datos.getPrecioIncrementado();
        preferenciaHabitaciones = datos.getPreferenciaHabitaciones();
        aceptacionIncremento = datos.getAceptacionIncremento();
        tabla = new Object[12][25];
        i = 0;
        j = 0;
        diasDelMes = 0;
        n = datos.getGeneradorCongruencialMixto().getL();
        indiceGenerador = 0;
        mejoraSimple = false;
        mejoraDoble = false;
        mejoraSuite = false;
        ingAcumActSimple = 0.0;
        ingAcumActDoble = 0.0;
        ingAcumActSuite = 0.0;
        ingAcumIncSimple = 0.0;
        ingAcumIncDoble = 0.0;
        ingAcumIncSuite = 0.0;
        
        data = new Object[10]; // Data rerieval declaration
        data[9] = false; // Default
        dia = 0;
    }
    
    public Object[] nextStep(){
        if(i < 12){
            if(j == 0){
                //calculamos cuantos dias hay en el mes actual y formateamos la tupla correpondiente
                diasDelMes = diasDelMes(i,2014);
                //numero de mes
                tabla[i][0] = (int)i+1;
                //demanda mensual
                tabla[i][1] = (int)0;
                tabla[i][2] = (int)0;
                tabla[i][3] = (int)0;
                //ingreso actual
                tabla[i][4] = (double)0.0;
                tabla[i][5] = (double)0.0;
                tabla[i][6] = (double)0.0;
                //total ingreso actual
                tabla[i][7] = (double)0.0;
                //demanda con el precio incrementado
                tabla[i][8] = (int)0;
                tabla[i][9] = (int)0;
                tabla[i][10] = (int)0;
                //ingreso con el precio incrementado
                tabla[i][11] = (double)0.0;
                tabla[i][12] = (double)0.0;
                tabla[i][13] = (double)0.0;
                //ingreso por mes con el precio incrementado
                tabla[i][14] = (double)0.0;
                //nuevo numero de habitaciones
                tabla[i][15] = (int)0;
                tabla[i][16] = (int)0;
                tabla[i][17] = (int)0;
                //nuevo precio de las habitaciones
                tabla[i][18] = (double)0;
                tabla[i][19] = (double)0;
                tabla[i][20] = (double)0;
                //ingreso con el incremento por mes, por tipo de habitacion
                tabla[i][21] = (double)0.0;
                tabla[i][22] = (double)0.0;
                tabla[i][23] = (double)0.0;
                //ingreso con el incremento total por mes
                tabla[i][24] = (double)0.0;
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
                
                // Data retrieval block
                data[0] = demandaSimpleDiaT + demandaDobleDiaT + demandaSuiteDiaT;
                data[1] = demandaSimpleDiaT;
                data[2] = demandaDobleDiaT;
                data[3] = demandaSuiteDiaT;
                data[6] = ++dia;
                data[8] = false;
            }
            else{
                //cuando se acaba el mes calculamos el valor de los ingresos generados por las habitaciones
                //actual
                tabla[i][4] = (double)((int)tabla[i][1]*precioActual[0]);
                tabla[i][5] = (double)((int)tabla[i][2]*precioActual[1]);
                tabla[i][6] = (double)((int)tabla[i][3]*precioActual[2]);
                ingAcumActSimple += (double)tabla[i][4];
                ingAcumActDoble += (double)tabla[i][5];
                ingAcumActSuite += (double)tabla[i][6];
                tabla[i][7] = (double)tabla[i][4]+(double)tabla[i][5]+(double)tabla[i][6];
                //posible incremento
                tabla[i][11] = (double)((int)tabla[i][8]*precioIncrementado[0]);
                tabla[i][12] = (double)((int)tabla[i][9]*precioIncrementado[1]);
                tabla[i][13] = (double)((int)tabla[i][10]*precioIncrementado[2]);
                ingAcumIncSimple += (double)tabla[i][11];
                ingAcumIncDoble += (double)tabla[i][12];
                ingAcumIncSuite += (double)tabla[i][13];
                tabla[i][14] = (double)tabla[i][11]+(double)tabla[i][12]+(double)tabla[i][13];
                j = 0;
                
                // Data retrieval block
                data[4] = tabla[i][14];
                data[7] = i+1;
                data[8] = true;
                
                i++;
            }
        }
        else{
            //cuando se acaba el año comparamos los ingresos de cada tipo, tanto actual como mejorado
            //definimos el nuevo numero de habitaciones y los nuevos precios
            if(ingAcumActSimple < ingAcumIncSimple)
                mejoraSimple = true;
            if(ingAcumActDoble < ingAcumIncDoble)
                mejoraDoble = true;
            if(ingAcumActSuite < ingAcumIncSuite)
                mejoraSuite = true;
            
            for(int k = 0; k <12; k++){
                //nuevo numero de habitaciones, nuevo precio e ingreso por mes, por tipo de habitacion
                if(mejoraSimple){
                    tabla[k][15] = cantidadHabitaciones[0];
                    tabla[k][18] = precioIncrementado[0];
                    tabla[k][21] = tabla[k][11];
                }
                else{
                    tabla[k][15] = cantidadHabitaciones[0];
                    tabla[k][18] = precioActual[0];
                    tabla[k][21] = tabla[k][4];
                }
                if(mejoraDoble){
                    tabla[k][16] = cantidadHabitaciones[1];
                    tabla[k][19] = precioIncrementado[1];
                    tabla[k][22] = tabla[k][12];
                }
                else{
                    tabla[k][16] = cantidadHabitaciones[1];
                    tabla[k][19] = precioActual[1];
                    tabla[k][22] = tabla[k][5];
                }
                if(mejoraSuite){
                    tabla[k][17] = cantidadHabitaciones[2];
                    tabla[k][20] = precioIncrementado[2];
                    tabla[k][23] = tabla[k][13];
                }
                else{
                    tabla[k][17] = cantidadHabitaciones[2];
                    tabla[k][20] = precioActual[2];
                    tabla[k][23] = tabla[k][6];
                }
                //ingreso con el incremento total por mes
                tabla[k][24] = (double)tabla[k][21] + (double)tabla[k][22] + (double)tabla[k][23];
            }
            
            data[9] = true; // We're done!
        }
        return data;
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
                "Total Ingreso Incrementado",
                "Cant. Simple*", "Cant. Doble*", "Cant. Suite Jr.*",
                "Precio Simple*", "Precio Doble*", "Precio Suite Jr.*",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Total Nuevo Ingreso"
        };
        return columns;
    }
    
    public String [] getDatosSimulacion()
    {
        String [] res = { "Valores máximo y mínimo de demanda del hotel", "   Máximo: "+gvt.getC(), "   Mínimo: "+gvt.getA(), 
                         "Cantidad de habitaciones actualmente", "   Habitación simple: "+cantidadHabitaciones[0], "   Habitación doble: "+cantidadHabitaciones[1], "   Suite Jr: "+cantidadHabitaciones[2],
                         "Precio actual de cada habitación", "   Habitación simple: "+precioActual[0], "   Habitación doble: "+precioActual[1], "   Suite Jr: "+precioActual[2], 
                         "Precio incrementado de las habitaciones", "   Habitación simple: "+precioIncrementado[0], "   Habitación doble: "+precioIncrementado[1], "   Suite Jr: "+precioIncrementado[2], 
                         "Porcentaje de preferencia por las habitaciones", "   Habitación simple: "+preferenciaHabitaciones[0], "   Habitación doble: "+preferenciaHabitaciones[1], "   Suite Jr: "+preferenciaHabitaciones[2], 
                         "Probabilidad de aceptacion del precio incrementado", "   Habitación simple: "+aceptacionIncremento[0], "   Habitación doble: "+aceptacionIncremento[1], "   Suite Jr: "+aceptacionIncremento[2], 
                        };
        return res;
    }
}
