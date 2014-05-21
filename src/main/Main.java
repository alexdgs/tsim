/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import controller.Controlador;
import model.Modelo;
import view.Menu;
import view.Simulador;
import com.jtattoo.plaf.*;
import javax.swing.*;

/**
 *
 * @author User
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Splash splash=new Splash();
        splash.run();
        Controlador c = new Controlador();
        //Menu menu = new Menu(c);
        
        try{
          UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");   
        }
        catch(Exception e){}
        
        Menu menu = new Menu(new javax.swing.JFrame(), true, c);
        Modelo m = new Modelo(c);
        Simulador s = new Simulador(c);
        c.setComponents(menu, m, s);
        menu.setVisible(true);
    }
    
}
