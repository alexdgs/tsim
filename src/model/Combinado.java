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
    
    public Combinado(Modelo modelo){
        gcm = modelo.getGeneradorCongruencialMixto();
        gvt = modelo.getGeneradorVariableTriangular();
        cantidadHabitaciones = modelo.getCantidadHabitaciones();
        precioActual = modelo.getPrecioActual();
        precioIncrementado = modelo.getPrecioIncrementado();
        preferenciaHabitaciones = modelo.getPreferenciaHabitaciones();
        aceptacionIncremento = modelo.getAceptacionIncremento();
        tabla = new Object[12][47];
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
        dia = 0;
    }
    
    public Object[] nextStep(){
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
                //incremento de numero de habitaciones actual
                tabla[i][13] = (int)0;
                tabla[i][14] = (int)0;
                tabla[i][15] = (int)0;
                //ingreso actual por mes, por tipo de habitacion
                tabla[i][16] = (double)0.0;
                tabla[i][17] = (double)0.0;
                tabla[i][18] = (double)0.0;
                //demanda total acumulada con el precio incrementado por tipo de habitacion
                tabla[i][19] = (int)0;
                tabla[i][20] = (int)0;
                tabla[i][21] = (int)0;
                //demanda satisfecha total acumulada con el precio incrementado por tipo de habitacion
                tabla[i][22] = (int)0;
                tabla[i][23] = (int)0;
                tabla[i][24] = (int)0;
                //demanda insatisfecha total acumulada con el precio incrementado por tipo de habitacion
                tabla[i][25] = (int)0;
                tabla[i][26] = (int)0;
                tabla[i][27] = (int)0;
                //cantidad actual de habitaciones
                tabla[i][28] = (int)cantidadHabitaciones[0];
                tabla[i][29] = (int)cantidadHabitaciones[1];
                tabla[i][30] = (int)cantidadHabitaciones[2];
                //incremento de numero de habitaciones con el precio incrementado
                tabla[i][31] = (int)0;
                tabla[i][32] = (int)0;
                tabla[i][33] = (int)0;
                //ingreso con el precio incrementado por mes
                tabla[i][34] = (double)0.0;
                tabla[i][35] = (double)0.0;
                tabla[i][36] = (double)0.0;
                //nuevo numero de habitaciones
                tabla[i][37] = (int)0;
                tabla[i][38] = (int)0;
                tabla[i][39] = (int)0;
                //nuevo precio de las habitaciones
                tabla[i][40] = (double)0;
                tabla[i][41] = (double)0;
                tabla[i][42] = (double)0;
                //ingreso con el incremento por mes, por tipo de habitacion
                tabla[i][43] = (double)0.0;
                tabla[i][44] = (double)0.0;
                tabla[i][45] = (double)0.0;
                //ingreso con el incremento total por mes
                tabla[i][46] = (double)0.0;
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
                //calculamos los valores de demanda del dia por tipo de habitacion para el incremento de precios
                demandaSimpleDiaT = (int)(demandaSimpleDiaT*aceptacionIncremento[0]);
                demandaDobleDiaT = (int)(demandaDobleDiaT*aceptacionIncremento[1]);
                demandaSuiteDiaT = (int)(demandaSuiteDiaT*aceptacionIncremento[2]);
                //acumulamos los valores de demanda por tipo de habitacion para el incremento de precios al acumulado mensual
                tabla[i][19] = (int)tabla[i][19] + demandaSimpleDiaT;
                tabla[i][20] = (int)tabla[i][20] + demandaDobleDiaT;
                tabla[i][21] = (int)tabla[i][21] + demandaSuiteDiaT;
                //acumulamos la demanda satisfecha e insatisfecha total por mes por habitacion para el incremento
                if(demandaSimpleDiaT>cantidadHabitaciones[0]){
                    tabla[i][22] = (int)tabla[i][22] + cantidadHabitaciones[0];
                    tabla[i][25] = (int)tabla[i][25]+ (demandaSimpleDiaT - cantidadHabitaciones[0]);
                }
                else{
                    tabla[i][22] = (int)tabla[i][22] + demandaSimpleDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                if(demandaDobleDiaT>cantidadHabitaciones[1]){
                    tabla[i][23] = (int)tabla[i][23] + cantidadHabitaciones[1];
                    tabla[i][26] = (int)tabla[i][26] + (demandaDobleDiaT - cantidadHabitaciones[1]);
                }
                else{
                    tabla[i][23] = (int)tabla[i][23] + demandaDobleDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                if(demandaSuiteDiaT>cantidadHabitaciones[2]){
                    tabla[i][24] = (int)tabla[i][24] + cantidadHabitaciones[2];
                    tabla[i][27] = (int)tabla[i][27] + (demandaSuiteDiaT - cantidadHabitaciones[2]);
                }
                else{
                    tabla[i][24] = (int)tabla[i][24] + demandaSuiteDiaT;
                    //no sumamos nada a la demanda insatisfecha porque en este caso toda la demanda se satisfizo
                }
                
                j++;
                indiceGenerador++;
                
                // Data retrieval block
                data[0] = demandaDia;
                data[1] = demandaSimpleDiaT;
                data[2] = demandaDobleDiaT;
                data[3] = demandaSuiteDiaT;
                data[6] = ++dia;
                data[8] = false;
            }
            else{
                j = 0;
                
                // Data retrieval block
                data[4] = -1; // Revenue not available!
                data[7] = i+1;
                data[8] = true;
                
                i++;
            }
        }
        else{
            //cuando se acaba el año calculamos los nuevos numeros de habitaciones (actual y precio incrementado) y sus respectivos ingresos
            //obtenemos el numero de habitaciones a incrementar
            int incActSimple = 0;
            int incActDoble = 0;
            int incActSuite = 0;
            int incIncSimple = 0;
            int incIncDoble = 0;
            int incIncSuite = 0;
            for(int k = 0; k < 12; k++){
                incActSimple += (int)tabla[k][7];
                incActDoble += (int)tabla[k][8];
                incActSuite += (int)tabla[k][9];
                incIncSimple += (int)tabla[k][25];
                incIncDoble += (int)tabla[k][26];
                incIncSuite += (int)tabla[k][27];
            }
            incActSimple = (int)((incActSimple/30)/12);
            incActDoble = (int)((incActDoble/30)/12);
            incActSuite = (int)((incActSuite/30)/12);
            incIncSimple = (int)((incIncSimple/30)/12);
            incIncDoble = (int)((incIncDoble/30)/12);
            incIncSuite = (int)((incIncSuite/30)/12);
            //declaramos una variable para acumular los ingresos por tipo de habitacion
            double ingAcumActSimple = 0.0;
            double ingAcumActDoble = 0.0;
            double ingAcumActSuite = 0.0;
            double ingAcumIncSimple = 0.0;
            double ingAcumIncDoble = 0.0;
            double ingAcumIncSuite = 0.0;
            //llenamos los nuevos numeros de habitaciones y calculamos el ingreso de cada tipo
            for(int k = 0; k < 12; k++){
                //nuevo numero de habitaciones actual
                tabla[k][13] = cantidadHabitaciones[0] + incActSimple;
                tabla[k][14] = cantidadHabitaciones[1] + incActDoble;
                tabla[k][15] = cantidadHabitaciones[2] + incActSuite;
                //nuevo numero de habitaciones con el incremento de precios
                tabla[k][31] = cantidadHabitaciones[0] + incIncSimple;
                tabla[k][32] = cantidadHabitaciones[1] + incIncDoble;
                tabla[k][33] = cantidadHabitaciones[2] + incIncSuite;
                //calculamos el ingreso actual por mes, por tipo de habitacion
                if((int)tabla[k][13] < (int)tabla[k][1]/30)
                    tabla[k][16] = (double)((int)tabla[k][13]*precioActual[0]*30);
                else
                    tabla[k][16] = (double)((int)tabla[k][1]*precioActual[0]);
                if((int)tabla[k][14] < (int)tabla[k][2]/30)
                    tabla[k][17] = (double)((int)tabla[k][14]*precioActual[1]*30);
                else
                    tabla[k][17] = (double)((int)tabla[k][2]*precioActual[1]);
                if((int)tabla[k][15] < (int)tabla[k][3]/30)
                    tabla[k][18] = (double)((int)tabla[k][15]*precioActual[2]*30);
                else
                    tabla[k][18] = (double)((int)tabla[k][3]*precioActual[2]);
                //acumulamos al ingreso anual actual
                ingAcumActSimple += (double)tabla[k][16];
                ingAcumActDoble += (double)tabla[k][17];
                ingAcumActSuite += (double)tabla[k][18];
                //calculamos el ingreso con el precio incrementado por mes
                if((int)tabla[k][31] < (int)tabla[k][19]/30)
                    tabla[k][34] = (double)((int)tabla[k][31]*precioIncrementado[0]*30);
                else
                    tabla[k][34] = (double)((int)tabla[k][19]*precioIncrementado[0]);
                if((int)tabla[k][32] < (int)tabla[k][20]/30)
                    tabla[k][35] = (double)((int)tabla[k][32]*precioIncrementado[1]*30);
                else
                    tabla[k][35] = (double)((int)tabla[k][20]*precioIncrementado[1]);
                if((int)tabla[k][33] < (int)tabla[k][21]/30)
                    tabla[k][36] = (double)((int)tabla[k][33]*precioIncrementado[2]*30);
                else
                    tabla[k][36] = (double)((int)tabla[k][21]*precioIncrementado[2]);
                //acumulamos al ingreso anual con el incremento de precios
                ingAcumIncSimple += (double)tabla[k][34];
                ingAcumIncDoble += (double)tabla[k][35];
                ingAcumIncSuite += (double)tabla[k][36];
            }
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
                    tabla[k][37] = (int)tabla[k][31];
                    tabla[k][40] = precioIncrementado[0];
                    tabla[k][43] = tabla[k][34];
                }
                else{
                    tabla[k][37] = (int)tabla[k][13];
                    tabla[k][40] = precioActual[0];
                    tabla[k][43] = tabla[k][16];
                }
                if(mejoraDoble){
                    tabla[k][38] = (int)tabla[k][32];
                    tabla[k][41] = precioIncrementado[1];
                    tabla[k][44] = tabla[k][35];
                }
                else{
                    tabla[k][38] = (int)tabla[k][14];
                    tabla[k][41] = precioActual[1];
                    tabla[k][44] = tabla[k][17];
                }
                if(mejoraSuite){
                    tabla[k][39] = (int)tabla[k][33];
                    tabla[k][42] = precioIncrementado[2];
                    tabla[k][45] = tabla[k][36];
                }
                else{
                    tabla[k][39] = (int)tabla[k][15];
                    tabla[k][42] = precioActual[2];
                    tabla[k][45] = tabla[k][18];
                }
                //ingreso con el incremento total por mes
                tabla[k][46] = (double)tabla[k][43] + (double)tabla[k][44] + (double)tabla[k][45];
            }
            data[9] = true;
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
                "D. sat. Simple", "D. sat. Doble", "D. sat. Suite Jr.",
                "D. ins. Simple", "D. ins. Doble", "D. ins. Suite Jr.",
                "Cant. Simple", "Cant. Doble", "Cant. Suite Jr.",
                "Inc. Hab. Simple", "Inc. Hab. Doble", "Inc. Hab. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "D. sat. Simple", "D. sat. Doble", "D. sat. Suite Jr.",
                "D. ins. Simple", "D. ins. Doble", "D. ins. Suite Jr.",
                "Cant. Simple", "Cant. Doble", "Cant. Suite Jr.",
                "Inc. Hab. Simple", "Inc. Hab. Doble", "Inc. Hab. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Cant. Simple*", "Cant. Doble*", "Cant. Suite Jr.*",
                "Precio Simple*", "Precio Doble*", "Precio Suite Jr.*",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Total Nuevo Ingreso"
            };
        return columns;
    }
}
