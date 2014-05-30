/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jose
 */
public class GeneradorCongruencialMixto {

    int a, c, m, x0;
    ArrayList<Double> listaNumerosAleatorios;
    
    public GeneradorCongruencialMixto(){
    
    }
    public GeneradorCongruencialMixto(int cMultiplicativa, int cAditiva, int modulo, int semilla){
        asignarValores(cMultiplicativa, cAditiva, modulo, semilla);
    }
    
    public void asignarValores(int cMultiplicativa, int cAditiva, int modulo, int semilla){
        this.a = cMultiplicativa;
        this.c = cAditiva;
        this.m = modulo;
        this.x0 = semilla;
        listaNumerosAleatorios = generarNumerosAleatorios();
    }

    private ArrayList<Double> generarNumerosAleatorios(){
        ArrayList<Double> lista = new ArrayList<>();
        int xn = x0;
        int i = 0;
        while(i<m){
            int aux = (a*xn+c)%m;
            double valor = (double)aux/m;
            lista.add(valor);
            //System.out.println(valor);
            xn = aux;
            i++;
        }
        Collections.shuffle(lista);
        return lista;
    }

    public ArrayList<Double> getListaNumerosAleatorios() {
        return listaNumerosAleatorios;
    }
    
    // This method returns the cycle (period) length of the GCM
    public int getL() {
        return listaNumerosAleatorios.size();
    }

    public int getA() {
        return a;
    }
    
    public int getC() {
        return c;
    }
    public int getM() {
        return m;
    }

    public int getX0() {
        return x0;
    }
}