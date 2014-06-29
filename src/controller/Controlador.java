/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Datos;
import model.GenerarPdf;
import model.Grafico;
import model.MejorOpcion;
import model.Modelo;
import view.AcercaDe;
import view.FormularioDeDatos;
import view.Menu;
import view.MejorOpcionVista;
import view.OpcionesAvanzadas;
import view.Reporte;
import view.Simulador;

/**
 *
 * @author User
 */

public class Controlador implements ActionListener, MouseListener, ChangeListener, WindowListener {
    
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
    Archivador a;
    Grafico g;
    Reporte r;
    FormularioDeDatos f;
    MejorOpcionVista mov;
    MejorOpcion mo;
    public int tipo;
    int estado; // Flag for sim state
    int nroGraficoPdf;
    int nroMejorOpcionPdf;
    
    public void setComponents(Menu menu, Modelo m, Simulador s, Archivador a) {
        this.menu = menu;
        this.m = m;
        this.s = s;
        this.a = a;
        g = new Grafico();
        mo=m.getDatos().getMejorOpcion();
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
            actualizarVista();
            s.cambiarEstadoMenuSimulacion(true);
        }
        s.updateView(data); // Send data to Simulador
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        Component componente = (Component)e.getSource();
        
        if(componente.isEnabled()){
            if(e.getSource()==menu.getLabelMejorPrecio() || e.getSource()==menu.getLabelCantidadHabitaciones() || e.getSource()==menu.getLabelMejorCombinacion())
                establecerModelo(e);
            else if(e.getSource()==s.getJBPlay())
                ejecutarAccionJBPlay(e);
            else if(e.getSource()==s.getJBStop())
                ejecutarAccionJBStop(e);
            else if(e.getSource()==s.getJBPause())
                ejecutarAccionJBPause(e);
            else if(e.getSource()==s.getJBFirst())
                s.setSlider(s.getSlider() - 100);
            else if(e.getSource()==s.getJBLast())
                s.setSlider(s.getSlider() + 100);
            else if(e.getSource()==s.getJBVerResultados())
                mostrarResultados();
            else if(e.getSource()==s.getPieChart())
                g.generarPieHabitaciones(m.getSimple(), m.getDoble(), m.getSuite());
            else if(e.getSource()==r.getPdfJButton())
                generarPdf(e);
            else if(e.getSource()==r.getPrintJButton()){
                try {
                    imprimir(e);
                } catch (PrinterException ex) {
                     Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        Component componente = (Component)e.getSource();
        
        if(componente.isEnabled()){
            if(e.getSource()==s.getCambiarModeloJMenuItem()) 
                menu.mostrar(true);
            else if(e.getSource()==s.getCambiarDatoJMenuItem())
                crearFormularioDeDatos(e);
            else if(e.getSource() == s.getOpcionesAvanzadasJMenuItem())
                crearVentanaOpcionesAvanzadas(e);
            else if(e.getSource() == s.getAbrirJMenuItem())
                abrirSimulacion(e);                  
            else if(e.getSource() == s.getGuardarJMenuItem())
                a.guardar(m.getDatos());
            else if(e.getSource() == s.getCerrarJMenuItem())
                preguntarSalir(e);
            else if(e.getSource() == s.getManualJMenuItem())
                abrirManualDeUsuario(e);    
            else if(e.getSource() == s.getAcercaDeJMenuItem())
                crearVentanaAcercaDe(e);
            else if(e.getSource() == s.getRestablecerJMenuItem())
                restablecer(e);
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

    public Simulador getSimulador() {
        return s;
    }

    public Modelo getModelo() {
        return m;
    }

    @Override
    public void windowOpened(WindowEvent e) { }
    
    // This method activates on any attempt for closing main window
    @Override
    public void windowClosing(WindowEvent e) {
        if(e.getSource() == this.s){
            if(JOptionPane.showConfirmDialog(s, "¿Está seguro de que desea salir?", "Salir",
                JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                System.exit(0);
            }
        }
        if(e.getSource() == this.menu){
            s.setModeloPorDefecto();
        }
    }
    
    // Unused -but mandatory- methods
    @Override
    public void windowClosed(WindowEvent e) { }

    @Override
    public void windowIconified(WindowEvent e) { }

    @Override
    public void windowDeiconified(WindowEvent e) { }

    @Override
    public void windowActivated(WindowEvent e) { }

    @Override
    public void windowDeactivated(WindowEvent e) { }

    public void actualizarVista() {
        m.stop();
        s.showText(m.getResultText(tipo));
        s.enableResults(true);
        s.setSimState(COMPLETADO);
        mo.comparar(m.getTabla(tipo), m.getDatos());
        estado = COMPLETADO;
    }

    private void establecerModelo(MouseEvent e) {
        menu.mostrar(false);
        s.cleanTextArea1();
        s.disableButton();
        
        if(e.getSource()==menu.getLabelCantidadHabitaciones()) tipo = MEJOR_HABITACIONES;
        else if(e.getSource()==menu.getLabelMejorCombinacion()) tipo = MEJOR_COMBINACION;
        else tipo = MEJOR_PRECIO;
        
        s.mostrarOpcion(tipo,m.getCantidadHabitaciones());
        s.reinicializar();
    }

    private void ejecutarAccionJBPlay(MouseEvent e) {
        if(estado != EN_EJECUCION) {
            if(estado == DETENIDO) {
                s.setSimState(EN_EJECUCION);
                estado = EN_EJECUCION;
                s.disableButton();
                m.play();
            }
            else if(estado == PAUSADO) {
                s.setSimState(EN_EJECUCION);
                estado = EN_EJECUCION;
                m.resume();
            }
            else if(estado == COMPLETADO){
                if((JOptionPane.showConfirmDialog(s, "Un proceso de simulación ha sido completado."
                                + "Si lo\nreinicia perderá los datos no guardados.\n¿Desea continuar?",
                                "Iniciar simulación", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)) == JOptionPane.OK_OPTION)
                {
                    s.setSimState(EN_EJECUCION);
                    estado = EN_EJECUCION;
                    s.disableButton();
                    m.play();
                }
            }
            s.cambiarEstadoMenuSimulacion(false);
        }
    }

    private void ejecutarAccionJBStop(MouseEvent e) {
        if(estado != DETENIDO && estado != COMPLETADO) {
            if(estado == EN_EJECUCION) m.stop(); // If running, stop while user chooses
            if((JOptionPane.showConfirmDialog(s, "¿Desea detener el proceso de simulación actual?.",
                                                "Detener simulación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) == JOptionPane.OK_OPTION)
            {
                s.setSimState(DETENIDO);
                s.enableResults(false);
                estado = DETENIDO;
                s.reinicializar();
                s.cambiarEstadoMenuSimulacion(true);
            }
            else {
                if(estado == EN_EJECUCION) m.resume(); // If was running, let it go
            }
        }
    }

    private void ejecutarAccionJBPause(MouseEvent e) {
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
    }

    private void generarPdf(MouseEvent e)
    {
        int nroTab = r.nroTab();
        
        if(nroTab == 0)
            generarPdfTabla(e);
        else
            if(nroTab == 3)
                generarPdfMejorOpcion(e);
        
    }
    private void generarPdfTabla(MouseEvent e)
    {
        String titulo="Incremento de Habitaciones";
        
        if(tipo==2) titulo="Incremento de Precio";
        else if(tipo==3) titulo="Combinado, incremento de habitaciones y de precio";
        
        GenerarPdf pdf=new GenerarPdf(titulo+nroGraficoPdf, titulo);
        
        pdf.results(m.getTabla(tipo), m.getColums(tipo), m.getDatosSimulacion(tipo));
        pdf.shownPdf();
        
        nroGraficoPdf++;
    }
    
    private void generarPdfMejorOpcion(MouseEvent e)
    {
        GenerarPdf pdf=new GenerarPdf("Mejor opcion"+nroMejorOpcionPdf, "Mejor Opción");
        
        String [] habitaciones = {""+mo.getNumeroHabitacionesSimple(), ""+mo.getNumeroHabitacionesDoble(), ""+mo.getNumeroHabitacionesSuite()};
        String [] precio = {""+mo.getPrecioHabitacionSimple(), ""+mo.getPrecioHabitacionDoble(), ""+mo.getPrecioHabitacionSuite()};
        String [] ingresos = {""+mo.getIngresoAnualSimple(), ""+mo.getIngresoAnualDoble(), ""+mo.getIngresoAnualSuite()};
        
        pdf.betterOption(habitaciones, precio, ingresos); 
        pdf.shownPdf();
        
        nroMejorOpcionPdf++;
    }
    private JPanel mostrarMejorOpcion()
    {
        mov = new MejorOpcionVista(this);
        mov.setMejorOpcion(mo.getMejorOpcionDeSimulacion());
        mov.setCantHabitaciones(""+mo.getNumeroHabitacionesSimple(), ""+mo.getNumeroHabitacionesDoble(), ""+mo.getNumeroHabitacionesSuite());
        mov.setPrecio(""+mo.getPrecioHabitacionSimple(), ""+mo.getPrecioHabitacionDoble(), ""+mo.getPrecioHabitacionSuite());
        mov.setIngreso(""+mo.getIngresoAnualSimple(), ""+mo.getIngresoAnualDoble(), ""+mo.getIngresoAnualSuite());
        return mov.getPanel();
    }



    private void crearFormularioDeDatos(MouseEvent e) {    
        f=new FormularioDeDatos(new javax.swing.JFrame(), true, this, m);
        f.setVisible(true);
    }

    private void crearVentanaOpcionesAvanzadas(MouseEvent e) {
        OpcionesAvanzadas opciones = new OpcionesAvanzadas(new javax.swing.JFrame(), true, this.m);
        opciones.setVisible(true);
    }

    private void abrirSimulacion(MouseEvent e) {
        // If some simulation is completed, loading another one will overwrite its results; so ask before
        if(estado == COMPLETADO && (JOptionPane.showConfirmDialog(s, "Si coninúa puede perder los datos no guardados."
                + "\n¿Desea continuar?","Iniciar simulación",
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) == JOptionPane.OK_OPTION)
        {
            Datos dat = a.abrir();
            if(dat != null) {
                m.setDatos(dat);
                tipo = dat.getUltimaOpcionCorrida();
                s.mostrarOpcion(tipo,m.getCantidadHabitaciones());
                actualizarVista();
            }
        } else {
            Datos dat = a.abrir();
            if(dat != null){
                m.setDatos(dat);
                tipo = dat.getUltimaOpcionCorrida();
                s.mostrarOpcion(tipo,m.getCantidadHabitaciones());
                actualizarVista();
            }
        }
    }

    private void preguntarSalir(MouseEvent e) {
        // Ask first...
        if(JOptionPane.showConfirmDialog(s, "¿Está seguro de que desea salir?", "Salir",
                JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    private void abrirManualDeUsuario(MouseEvent e) 
    {
        try{
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+"/Documentacion/Manual de usuario.pdf");
         }catch(Exception e1){
             JOptionPane.showMessageDialog(null, "Error");
         }
    }

    private void crearVentanaAcercaDe(MouseEvent e) {
        AcercaDe acerca=new AcercaDe(new JFrame(), true);
        acerca.setVisible(true);
    }

    private void imprimir(MouseEvent e) throws PrinterException
    {
         int nroTab = r.nroTab();
        
        if(nroTab == 0)
            imprimirTablas(e);
        else
            r.imprimir(nroTab);  
    }
     
    private void imprimirTablas(MouseEvent e)
    {
        String titulo="Incremento de Habitaciones";
        if(tipo==2) titulo="Incremento de Precio";
        else if(tipo==3) titulo="Combinado, incremento de habitaciones y de precio";
        
        MessageFormat header = new MessageFormat(titulo);
        MessageFormat footer = new MessageFormat("Página {0,number, Integer}");
        
        try{
            if(tipo == 1)
                s.getTablaMejorIncremento().getIncrementoJTable().print(JTable.PrintMode.NORMAL, header, footer);
            else if(tipo == 2)
                s.getTablaVariacionPrecios().getPrecioJTable().print(JTable.PrintMode.NORMAL, header, footer);
            else
                s.getTablaCombinado().getCombinadoJTable().print(JTable.PrintMode.NORMAL, header, footer);
        
        } catch(java.awt.print.PrinterException e1){
           System.err.format("Cannot print %s%n", e1.getMessage());
        }
    }

    private void restablecer(MouseEvent e) 
    {
        if(JOptionPane.showConfirmDialog(s, "¿Está seguro de restablecer los valores?", "Restablecer valores",
            JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
             m.restablecer();
        }
    }

    private void mostrarResultados()
    {
        r = new Reporte(m.getTabla(tipo), tipo, s.mostrarResultados(m.getTabla(tipo),tipo), mostrarMejorOpcion(), this);
    }
}
