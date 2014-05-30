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
import model.GenerarPdf;
import model.Modelo;
import model.Grafico;
import model.MejorOpcion;
import view.FormularioDeDatos;
import view.Menu;
import view.MostrarMejorOpcion;
import view.Simulador;

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
    final int COMPLETADO = 7; // Distinct from stopped
    
    Menu menu;
    Modelo m;
    Simulador s;
    Grafico g;
    FormularioDeDatos f;
    MostrarMejorOpcion mmo;
    MejorOpcion mo;
    public int tipo;
    int estado; // Flag for sim state
    int nroGraficoPdf;
    int nroMejorOpcionPdf;
    
    public void setComponents(Menu menu, Modelo m, Simulador s) {
        this.menu = menu;
        this.m = m;
        this.s = s;
        g = new Grafico();
        mo=new MejorOpcion();
        tipo = MEJOR_HABITACIONES; // Default sim type
        estado = DETENIDO; // Default sim state
        nroGraficoPdf=1;
        nroMejorOpcionPdf=1;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] data = m.nextStep(tipo); // Retrieve data
        // If simulation is complete, stop and enable results button
        if((boolean)data[9]) {
            m.stop();
            s.showText(m.getResultText(tipo));
            s.enableResults(true);
            s.setSimState(COMPLETADO);
            mo.comparar(m.getTabla(tipo), m);
            estado = COMPLETADO;
        }
        s.updateView(data); // Send data to Simulador
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==menu.getLabelMejorPrecio() || e.getSource()==menu.getLabelCantidadHabitaciones() || e.getSource()==menu.getLabelMejorCombinacion())
        {
            menu.mostrar(false);
            s.cleanTextArea1();
            s.disableButton();
            
            if(e.getSource()==menu.getLabelCantidadHabitaciones()) tipo = MEJOR_HABITACIONES;
            else if(e.getSource()==menu.getLabelMejorCombinacion()) tipo = MEJOR_COMBINACION;
            else tipo = MEJOR_PRECIO;
            
            s.mostrarOpcion(tipo,m.getCantidadHabitaciones());
        }
        else
            // Time button handlers (regardless sim type)
            if(e.getSource()==s.getJBPlay())
            {
                if(estado != EN_EJECUCION) {
                    if(estado == DETENIDO) {
                        s.setSimState(EN_EJECUCION);
                        estado = EN_EJECUCION;
                        m.play();
                    }
                    else if(estado == PAUSADO) {
                        s.setSimState(EN_EJECUCION);
                        estado = EN_EJECUCION;
                        m.resume();
                    }
                    else if(estado == COMPLETADO)
                        if((JOptionPane.showConfirmDialog(s, "Un proceso de simulación ha sido completado."
                                + "Si lo\nreinicia perderá los datos no guardados.\n¿Desea continuar?",
                                "Iniciar simulación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)) == JOptionPane.OK_OPTION)
                        {
                            s.setSimState(EN_EJECUCION);
                            estado = EN_EJECUCION;
                            m.play();
                        }
                }
            } else if(e.getSource()==s.getJBStop())
            {
                if(estado != DETENIDO) {
                    m.stop();
                    s.setSimState(DETENIDO);
                    s.enableResults(false);
                    estado = DETENIDO;
                }
            } else if(e.getSource()==s.getJBPause())
            {
                if(estado != DETENIDO && estado != COMPLETADO) {
                    if(estado == EN_EJECUCION) {
                        m.stop();
                        s.setSimState(PAUSADO);
                        estado = PAUSADO;
                    } else if(estado == PAUSADO) {
                        s.setSimState(EN_EJECUCION);
                        estado = EN_EJECUCION;
                        m.resume();
                    }
                }
            } else if(e.getSource()==s.getJBFirst()) s.setSlider(s.getSlider() - 100);
             else if(e.getSource()==s.getJBLast()) s.setSlider(s.getSlider() + 100);
            else{
                if(e.getSource()==s.getJBVerResultados())
                {
                    // Get table form Model and send it to Simulador
                    s.mostrarResultados(m.getTabla(tipo),tipo);
                }
                else
                {
                    if(e.getSource()==s.getJBGrafico())
                    {
                        if(s.getChartComboBox().getSelectedIndex()==0)
                          g.generarGraficoBarras(m.getTabla(tipo), tipo);
                        else
                          g.generarGraficoDobleEje(m.getTabla(tipo), tipo);
                        
                    }
                    else
                    {
                       if(e.getSource()==s.getPieChart())
                           g.generarPieHabitaciones(m.getSimple(), m.getDoble(), m.getSuite());
                       else
                       {
                           if(e.getSource()==s.getPrintJButton())
                           {
                               String titulo="Incremento de Habitaciones";
                               if(tipo==2) titulo="Incremento de Precio";
                               else if(tipo==3) titulo="Combinado, incremento de habitaciones y de precio";
                               GenerarPdf pdf=new GenerarPdf(titulo+nroGraficoPdf, titulo);
                               pdf.createTable(m.getTabla(tipo), m.getColums(tipo));
                               nroGraficoPdf++;
                           }
                           else
                           {
                               if(e.getSource()==s.getMejorOpcionJButton())
                               {                                
                                   mmo = new MostrarMejorOpcion(new javax.swing.JFrame(), true, this);
                                   mmo.setMejorOpcion(mo.getMejorOpcionDeSimulacion());
                                   mmo.setCantHabitaciones(""+mo.getNumeroHabitacionesSimple(), ""+mo.getNumeroHabitacionesDoble(), ""+mo.getNumeroHabitacionesSuite());
                                   mmo.setPrecio(""+mo.getPrecioHabitacionSimple(), ""+mo.getPrecioHabitacionDoble(), ""+mo.getPrecioHabitacionSuite());
                                   mmo.setIngreso(""+mo.getIngresoAnualSimple(), ""+mo.getIngresoAnualDoble(), ""+mo.getIngresoAnualSuite());
                                   mmo.setVisible(true);
                               }
                               else
                               {
                                   if(e.getSource()==mmo.getAceptar())
                                     mmo.dispose();
                                   else
                                   {
                                       if(e.getSource()==mmo.getGenerarPdf())
                                       {
                                           GenerarPdf pdf=new GenerarPdf("Mejor opcion"+nroMejorOpcionPdf, "Mejor Opcion");

                                           String [] habitaciones = {""+mo.getNumeroHabitacionesSimple(), ""+mo.getNumeroHabitacionesDoble(), ""+mo.getNumeroHabitacionesSuite()};
                                           String [] precio = {""+mo.getPrecioHabitacionSimple(), ""+mo.getPrecioHabitacionDoble(), ""+mo.getPrecioHabitacionSuite()};
                                           String [] ingresos = {""+mo.getIngresoAnualSimple(), ""+mo.getIngresoAnualDoble(), ""+mo.getIngresoAnualSuite()};
                                           mmo.setVisible(true);
                                           pdf.betterOption(habitaciones, precio, ingresos);
                                           nroMejorOpcionPdf++;
                                       }
                                   }
                                   
                               }
                              
                           }
                       }
                    }
                }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
       if(e.getSource()==s.getCambiarModeloJMenuItem()) 
          menu.mostrar(true);
       else
       {
           if(e.getSource()==s.getCambiarDatoJMenuItem())
           {
               f=new FormularioDeDatos(new javax.swing.JFrame(), true, this, m);
               f.setVisible(true);
           }
           else
           {

           }
       }
       
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

    public int getTipo() {
        return tipo;
    }
}
