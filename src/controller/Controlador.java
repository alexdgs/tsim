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
import model.Modelo;
import javax.swing.*;
import view.FormularioDeDatos;
import view.Menu;
import view.Simulador;
import view.TablaMejorIncremento;
import view.TablaVariacionPrecios;

/**
 *
 * @author User
 */
public class Controlador implements ActionListener, MouseListener {
    
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
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
       
        if(e.getSource()==menu.getLabelMejorPrecio() || e.getSource()==menu.getLabelCantidadHabitaciones() || e.getSource()==menu.getLabelMejorCombinacion())
        {
            menu.dispose();
            s.mostrar(e.getComponent().getName());
            
            tipo=2;
            s.cleanTextArea1();
            s.disableButton();
            
            if(e.getSource()==menu.getLabelCantidadHabitaciones()){
               tipo=1;
               s.mostrar("1");
            }
            else
            {
              if(e.getSource()==menu.getLabelMejorCombinacion())
              {
                tipo=3;
                s.mostrar("3");
              }
              else s.mostrar("2");
            }
        }
        else{
            if(e.getSource()==s.getJBPlay())
            {
                if(tipo==2)
                {
                  s.setTablaVariacionPrecios(new TablaVariacionPrecios(m));
                  s.cleanTextArea1();
                  s.getjTextArea1().setText(m.informeSegundoCaso());
                  s.mostrar("2");
                }
                else
                {
                   if(tipo==1)
                   {
                      s.setTablaMejorIncremento(new TablaMejorIncremento(m));
                      s.cleanTextArea1();
                      s.getjTextArea1().setText(m.informePrimerCaso()); //cambiar caso 
                   }
                }
                s.getJBVerResultados().setEnabled(true);
            }
            else{
                if(e.getSource()==s.getJBVerResultados())
                {
                    if(tipo==2)
                      s.getTablaVariacionPrecios().setVisible(true);
                    else
                    {
                        if(tipo==1)
                          s.getTablaMejorIncremento().setVisible(true);
                    }
                }
                if(e.getSource()==s.getCambiarModeloJMenuItem())
                { 
                    
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
    
}
