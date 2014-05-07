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
public class GeneradorVariableTriangular {
    
    private int a, b, c;
    private double area1, area2;
    
    public GeneradorVariableTriangular(){
        
    }
    
    public GeneradorVariableTriangular(int a, int b, int c){
        asignarValores(a,b,c);
    }
    public void asignarValores(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
        this.area1 = (double)(b-a)/(c-a);
        this.area2 = (double)(c-b)/(c-a);
    }
    
    public int generarValor(Double numAleatorio){
        int res = 0;
        if(numAleatorio>=0 && numAleatorio<=area1){
            res = (int)(a+Math.sqrt((double)numAleatorio*(b-a)*(c-a)));
        }
        else{
            if(numAleatorio>=area1 && numAleatorio<=1){
                res = (int)(c-Math.sqrt((double)(numAleatorio-1)*(c-a)*(b-c)));
            }
        }
        return res;
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
    
    
}