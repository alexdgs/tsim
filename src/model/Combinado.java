/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import static model.Modelo.diasDelMes;

/**
 *
 * @author Jose
 */
public class Combinado {
    
    GeneradorCongruencialMixto gcm;
    GeneradorVariableTriangular gvt;
    int[] cantidadHabitaciones;
    double[] precioActual;
    double[] precioIncrementado;
    double[] preferenciaHabitaciones;
    double[] aceptacionIncremento;
    Object[][] tabla;
    boolean mejoraSimple, mejoraDoble, mejoraSuite;
    Double ingresoSimpleAct, ingresoDobleAct, ingresoSuiteAct, ingresoSimpleInc, ingresoDobleInc, ingresoSuiteInc;
    int i,j,diasDelMes,n,indiceGenerador;
    
    public Combinado(Modelo modelo){
        gcm = modelo.getGeneradorCongruencialMixto();
        gvt = modelo.getGeneradorVariableTriangular();
        cantidadHabitaciones = modelo.getCantidadHabitaciones();
        precioActual = modelo.getPrecioActual();
        precioIncrementado = modelo.getPrecioIncrementado();
        preferenciaHabitaciones = modelo.getPreferenciaHabitaciones();
        aceptacionIncremento = modelo.getAceptacionIncremento();
        tabla = new Object[12][39];
        mejoraSimple = false;
        mejoraDoble = false;
        mejoraSuite = false;
        ingresoSimpleAct = 0.0;
        ingresoDobleAct = 0.0;
        ingresoSuiteAct = 0.0;
        ingresoSimpleInc = 0.0;
        ingresoDobleInc = 0.0;
        ingresoSuiteInc = 0.0; 
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
                //numero de mes
                tabla[i][0] = (int)i+1;
                //demanda total por mes
                tabla[i][1] = (int)0;
                tabla[i][2] = (int)0;
                tabla[i][3] = (int)0;
                //demanda satisfecha total por mes
                tabla[i][4] = (int)0;
                tabla[i][5] = (int)0;
                tabla[i][6] = (int)0;
                //demanda insatisfecha total por mes
                tabla[i][7] = (int)0;
                tabla[i][8] = (int)0;
                tabla[i][9] = (int)0;
                //cantidad actual de habitaciones
                tabla[i][10] = (int)cantidadHabitaciones[0];
                tabla[i][11] = (int)cantidadHabitaciones[1];
                tabla[i][12] = (int)cantidadHabitaciones[2];
                //ingreso actual por mes, por tipo de habitacion
                tabla[i][13] = (double)0.0;
                tabla[i][14] = (double)0.0;
                tabla[i][15] = (double)0.0;
                //ingreso actual total por mes
                tabla[i][16] = (double)0.0;
                //demanda total acumulada con el precio incrementado por tipo de habitacion
                tabla[i][17] = (int)0;
                tabla[i][18] = (int)0;
                tabla[i][19] = (int)0;
                //demanda satisfecha total acumulada con el precio incrementado por tipo de habitacion
                tabla[i][20] = (int)0;
                tabla[i][21] = (int)0;
                tabla[i][22] = (int)0;
                //demanda insatisfecha total acumulada con el precio incrementado por tipo de habitacion
                tabla[i][23] = (int)0;
                tabla[i][24] = (int)0;
                tabla[i][25] = (int)0;
                //ingreso con el precio incrementado por mes
                tabla[i][26] = (double)0.0;
                tabla[i][27] = (double)0.0;
                tabla[i][28] = (double)0.0;
                //incremento de numero de habitaciones
                tabla[i][29] = (int)0;
                tabla[i][30] = (int)0;
                tabla[i][31] = (int)0;
                //nuevo numero de habitaciones
                tabla[i][32] = (int)0;
                tabla[i][33] = (int)0;
                tabla[i][34] = (int)0;
                //ingreso con el incremento por mes, por tipo de habitacion
                tabla[i][35] = (double)0.0;
                tabla[i][36] = (double)0.0;
                tabla[i][37] = (double)0.0;
                //ingreso con el incremento total por mes
                tabla[i][38] = (double)0.0;
            }
            if(j < diasDelMes && indiceGenerador < n){
                //generamos la demanda del dia
                int demandaDia = gvt.generarValor(gcm.getListaNumerosAleatorios().get(indiceGenerador));
                //calculamos los valores de demanda total por tipo de habitacion para la situacion actual
                int demandaSimpleDiaT = (int)(demandaDia*this.preferenciaHabitaciones[0]);
                int demandaDobleDiaT = (int)(demandaDia*this.preferenciaHabitaciones[1]);
                int demandaSuiteDiaT = (int)(demandaDia*this.preferenciaHabitaciones[2]);
                //acumulamos la demanda total por tipo de habitacion para la situacion actual
                tabla[i][1] = (int)tabla[i][1] + demandaSimpleDiaT;
                tabla[i][2] = (int)tabla[i][2] + demandaDobleDiaT;
                tabla[i][3] = (int)tabla[i][3] + demandaSuiteDiaT;
                //acumulamos la demanda satisfecha e insatisfecha total por mes por habitacion
                if(demandaSimpleDiaT>cantidadHabitaciones[0]){
                    tabla[i][4] = (int)tabla[i][4] + cantidadHabitaciones[0];
                    tabla[i][7] = (int)tabla[i][7] + (demandaSimpleDiaT - cantidadHabitaciones[0]);
                }
                else{
                    tabla[i][4] = (int)tabla[i][4] + demandaSimpleDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                if(demandaDobleDiaT>cantidadHabitaciones[1]){
                    tabla[i][5] = (int)tabla[i][5] + cantidadHabitaciones[1];
                    tabla[i][8] = (int)tabla[i][8] + (demandaDobleDiaT - cantidadHabitaciones[1]);
                }
                else{
                    tabla[i][5] = (int)tabla[i][5] + demandaDobleDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                if(demandaSuiteDiaT>cantidadHabitaciones[2]){
                    tabla[i][6] = (int)tabla[i][6] + cantidadHabitaciones[2];
                    tabla[i][9] = (int)tabla[i][9] + (demandaSuiteDiaT - cantidadHabitaciones[2]);
                }
                else{
                    tabla[i][6] = (int)tabla[i][6] + demandaSuiteDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                //calculamos el ingreso del dia y lo acumulamos al mensual
                tabla[i][13] = (double)tabla[i][13] + (double)((int)tabla[i][4]*precioActual[0]);
                tabla[i][14] = (double)tabla[i][14] + (double)((int)tabla[i][5]*precioActual[1]);
                tabla[i][15] = (double)tabla[i][15] + (double)((int)tabla[i][6]*precioActual[2]);
                //calculamos el ingreso total del dia y lo acumulamos al mensual
                tabla[i][16] = (double)tabla[i][16] + (double)tabla[i][13] + (double)tabla[i][14] + (double)tabla[i][15];
                //calculamos los valores de demanda por tipo de habitacion para el incremento de precios
                demandaSimpleDiaT = (int)(demandaSimpleDiaT*aceptacionIncremento[0]);
                demandaDobleDiaT = (int)(demandaDobleDiaT*aceptacionIncremento[1]);
                demandaSuiteDiaT = (int)(demandaSuiteDiaT*aceptacionIncremento[2]);
                //acumulamos los valores de demanda por tipo de habitacion para el incremento de precios al acumulado mensual
                tabla[i][17] = (int)tabla[i][17] + demandaSimpleDiaT;
                tabla[i][18] = (int)tabla[i][18] + demandaDobleDiaT;
                tabla[i][19] = (int)tabla[i][19] + demandaSuiteDiaT;
                //acumulamos la demanda satisfecha e insatisfecha total por mes por habitacion para el incremento
                if(demandaSimpleDiaT>cantidadHabitaciones[0]){
                    tabla[i][20] = (int)tabla[i][20] + cantidadHabitaciones[0];
                    tabla[i][23] = (int)tabla[i][23]+ (demandaSimpleDiaT - cantidadHabitaciones[0]);
                }
                else{
                    tabla[i][20] = (int)tabla[i][20] + demandaSimpleDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                if(demandaDobleDiaT>cantidadHabitaciones[1]){
                    tabla[i][21] = (int)tabla[i][21] + cantidadHabitaciones[1];
                    tabla[i][24] = (int)tabla[i][24] + (demandaDobleDiaT - cantidadHabitaciones[1]);
                }
                else{
                    tabla[i][21] = (int)tabla[i][21] + demandaDobleDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                if(demandaSuiteDiaT>cantidadHabitaciones[2]){
                    tabla[i][22] = (int)tabla[i][22] + cantidadHabitaciones[2];
                    tabla[i][25] = (int)tabla[i][25] + (demandaSuiteDiaT - cantidadHabitaciones[2]);
                }
                else{
                    tabla[i][22] = (int)tabla[i][22] + demandaSuiteDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                //calculamos en ingreso con el precio incrementado y lo acumulamos al mensual
                tabla[i][26] = (double)tabla[i][26] + (double)((int)tabla[i][20]*precioIncrementado[0]);
                tabla[i][27] = (double)tabla[i][27] + (double)((int)tabla[i][21]*precioIncrementado[1]);
                tabla[i][28] = (double)tabla[i][28] + (double)((int)tabla[i][22]*precioIncrementado[2]);
                
                j++;
                indiceGenerador++;
            }
            else{
                //cuando se acaba el mes calculamos acumulamos los ingresos generados en el mes a un acumulado anual
                //actual
                ingresoSimpleAct += (double)tabla[i][13];
                ingresoDobleAct += (double)tabla[i][14];
                ingresoSuiteAct += (double)tabla[i][15];
                //incrementado
                ingresoSimpleInc += (double)tabla[i][26];
                ingresoDobleInc += (double)tabla[i][27];
                ingresoSuiteInc += (double)tabla[i][28];
                i++;
                j = 0;
            }
        }
        else{
            //cuando termina el año comparamos los ingresos anuales para saber en que tipo de habitacion se debe increnetar el numero de habitaciones
            //por el momento asumimos que el actual es el mejor
            int indiceSimple = 7;
            int indiceDoble = 8;
            int indiceSuite = 9;
            //comparamos con el incrementado y re-asignamos el indice y el mejorado, de ser necesario
            if(ingresoSimpleAct < ingresoSimpleInc){
                mejoraSimple = true;
                indiceSimple = 23;
            }
            if(ingresoDobleAct < ingresoDobleInc){
                mejoraDoble = true;
                indiceDoble = 24;
            }
            if(ingresoSuiteAct < ingresoSuiteInc){
                mejoraSuite = true;
                indiceSuite = 25;
            }
            //declaramos variables para sacar un promedio de la demanda insatisfecha por tipo de habitacion
            int demInsSimple = 0;
            int demInsDoble = 0;
            int demInsSuite = 0;
            //acumulamos la demanda insatisfecha
            for(int k = 0; k < 12; k++){
                demInsSimple += (int)tabla[k][indiceSimple];
                demInsDoble += (int)tabla[k][indiceDoble];
                demInsSuite += (int)tabla[k][indiceSuite];
            }
            //sacamos el promedio y este se vuelve el incremento de habitaciones para cada tipo
            demInsSimple = (int)((demInsSimple/12)/30);
            demInsDoble = (int)((demInsDoble/12)/30);
            demInsSuite = (int)((demInsSuite/12)/30);
            //calculamos en nuevo numero de habitaciones por tipo
            int numHabIncSimple = (int)(demInsSimple) + cantidadHabitaciones[0];
            int numHabIncDoble = (int)(demInsDoble) + cantidadHabitaciones[1];
            int numHabIncSuite = (int)(demInsSuite) + cantidadHabitaciones[2];
            //llenamos los valores faltantes de la tabla
            for(int k = 0; k < 12; k++){
                //asignamos los valores del incremento de habitaciones a la tabla
                tabla[k][29] = demInsSimple;
                tabla[k][30] = demInsDoble;
                tabla[k][31] = demInsSuite;
                //asignamos los valores del nuevo numero de habitaciones a la tabla
                tabla[k][32] = numHabIncSimple;
                tabla[k][33] = numHabIncDoble;
                tabla[k][34] = numHabIncSuite;
                //calculamos el nuevo ingreso son todas las mejoras por tipo de habitacion
                if(mejoraSimple)
                    tabla[k][35] = (double)numHabIncSimple*precioIncrementado[0];
                else
                    tabla[k][35] = numHabIncSimple*precioActual[0];
                if(mejoraDoble)
                    tabla[k][36] = numHabIncDoble*precioIncrementado[1];
                else
                    tabla[k][36] = numHabIncDoble*precioActual[1];
                if(mejoraSuite)
                    tabla[k][37] = numHabIncSuite*precioIncrementado[2];
                else
                    tabla[k][37] = numHabIncSuite*precioActual[2];
                //calculamos el nuevo ingreso total por mes con todas las mejoras
                tabla[k][38] = (double)tabla[k][35] +(double)tabla[k][36] +(double)tabla[k][37];
            }
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
}
