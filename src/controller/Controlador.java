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
import view.FormularioDeDatos;
import view.Menu;
import view.Simulador;
import view.TablaVariacionPrecios;

/**
 *
 * @author User
 */
public class Controlador implements ActionListener, MouseListener {
    
    Menu menu;
    Modelo m;
    Simulador s;
    
    public void setComponents(Menu menu, Modelo m, Simulador s) {
        this.menu = menu;
        this.m = m;
        this.s = s;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==menu.getjLabel3()){
            System.out.println("asdas");
            menu.dispose();
            s.mostrar(e.getComponent().getName());
        }
        else{
            if(e.getSource()==s.getJBPlay()){
                s.setTablaVariacionPrecios(new TablaVariacionPrecios(m));
                s.getJBVerResultados().setEnabled(true);
                s.getjTextArea1().setText("");
                s.getjTextArea1().setText(m.toString());
            }
            else{
                if(e.getSource()==s.getJBVerResultados()){
                    s.getTablaVariacionPrecios().setVisible(true);
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
