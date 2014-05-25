/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Modelo;
import view.FormularioDeDatos;
import view.Menu;
import view.Simulador;
import view.TablaMejorIncremento;
import view.TablaVariacionPrecios;

/**
 *
 * @author User
 */
public class Controlador implements ActionListener, MouseListener, ChangeListener {
    
    // Simulation model constants
    final int MEJOR_HABITACIONES = 1;
    final int MEJOR_PRECIO = 2;
    final int MEJOR_COMBINACION = 3;
    
    // Simulation state constants
    final int DETENIDO = 4;
    final int EN_EJECUCION = 5;
    final int PAUSADO = 6;
    
    Menu menu;
    Modelo m;
    Simulador s;
    int tipo;
    
    public void setComponents(Menu menu, Modelo m, Simulador s) {
        this.menu = menu;
        this.m = m;
        this.s = s;
        tipo=2;
    }
    public void setComponents(Menu m) {
        menu = m;
        tipo=2;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // If simulation is complete, stop and enable results button
        if(m.nextStep(tipo)) {
            m.stop();
            s.enableResults(true,m.getResultText(tipo));
            s.setSimState(DETENIDO);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==menu.getLabelMejorPrecio() || e.getSource()==menu.getLabelCantidadHabitaciones() || e.getSource()==menu.getLabelMejorCombinacion())
        {
            menu.mostrar(false);
            //s.mostrar(e.getComponent().getName());
            
            tipo=2;
            s.cleanTextArea1();
            s.disableButton();
            
            if(e.getSource()==menu.getLabelCantidadHabitaciones()){
               tipo=1;
               s.mostrarOpcion(1);
            }
            else
            {
              if(e.getSource()==menu.getLabelMejorCombinacion())
              {
                tipo=3;
                s.mostrarOpcion(3);
              }
              else {
                  s.mostrarOpcion(2);
              }
            }
        }
        else{
            if(e.getSource()==s.getJBPlay())
            {
                if(tipo==2)
                {
                    //s.setTablaVariacionPrecios(new TablaVariacionPrecios(m));
                     //s.cleanTextArea1();
                    //s.getjTextArea1().setText(m.informeSegundoCaso());
                    //s.mostrar("2");
                    m.play();
                    s.setSimState(EN_EJECUCION);
                }
                else
                   if(tipo==1)
                   {
                       // Start simulation
                      m.play();
                      s.setSimState(EN_EJECUCION);
                      //s.cleanTextArea1();
                      //s.getjTextArea1().setText(m.informePrimerCaso()); //cambiar caso 
                   }
                //s.getJBVerResultados().setEnabled(true);
            }
            else{
                if(e.getSource()==s.getJBVerResultados())
                {
                    // Get table form Model and send it to Simulador
                    s.mostrarResultados(m.getTabla(tipo),tipo);
                }
                if(e.getSource()==s.getCambiarModeloJMenuItem())
                { 
                    menu.mostrar(true);
                }
            }
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource(); // Cast source to JSlider
        if (!source.getValueIsAdjusting()) {
            // If user finished moving slider
            int delay = 1010 - (int)source.getValue(); // Get slider value and obtain delay (greater value, smaller delay)
            m.setSpeed(delay); // Tell Model to adjust its timer
            s.setSpeed(delay); // Tell Simulador to update its labels
        }
    }
    
    public void showMenu(boolean b) {
        menu.setVisible(b);
    }
}
