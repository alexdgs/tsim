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

    int a, c, m, x0, nActual;
    ArrayList<Double> listaNumerosAleatorios;
    ArrayList<Integer> numerosPrimos;
    
    public GeneradorCongruencialMixto(){
        nActual = 1;
        asignarValores(365*nActual+1, 7, 365,4);
    }
    public GeneradorCongruencialMixto(int cMultiplicativa, int cAditiva, int modulo, int semilla){
        nActual = 1;
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
    
    public int getN() {
        return nActual;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setC(int c) {
        this.c = c;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setX0(int x0) {
        this.x0 = x0;
    }
    
    public void setN(int n) {
        this.nActual = n;
    }
    
    public ArrayList<Integer> cribaErastotenes(int n){
        ArrayList res = new ArrayList<Integer>();
        int tope = (int) Math.floor(Math.sqrt(n)) + 1;
        ArrayList<Long> compuestos = new ArrayList<Long>();
        int pos = 0;
        for (int i = 2; i <= tope; i++) {
            if (!compuestos.contains(Long.valueOf(i))) {
                for (int j = i; j <= n / i + 1; j++)
                    compuestos.add(Long.valueOf(i * j));
            }
        }
        for (pos = 2; pos < n; pos++) {
            if (!compuestos.contains(Long.valueOf(pos)))
                res.add(pos);
        }
        return res;
    }
    
    
}