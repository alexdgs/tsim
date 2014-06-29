/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import controller.Controlador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.Grafico;

/**
 *
 * @author A
 */
public class Reporte extends JDialog implements Printable {
    
    private Object [][] tabla;
    private int opcion;
    private Grafico g;
    private JPanel panelTabla;
    private int tipo;
    private JPanel panelMejorOpcion;
    private Controlador c;
    private Simulador s;
    private int nroGraficoPdf;

    private JPanel panel;
    private JButton printJButton;
    private JButton pdfJButton;
    private JTabbedPane tabPanel;
    private JPanel tabTablas;
    private JPanel tabGraficoBarras;
    private JPanel tabGraficoDE;
    private JPanel tabMejorO;
    private JPanel panelImprimir;
    
    
    public Reporte(Object [][] tabla, int opcion, JPanel panelTabla, JPanel panelMejorOpcion, Controlador c) 
    {
        this.tabla = tabla;
        this.opcion = opcion;
        this.panelTabla = panelTabla;
        this.panelMejorOpcion = panelMejorOpcion;
        this.c = c;
        g = new Grafico();

        init();
        setIconImage(new ImageIcon(getClass().getResource("/img/hotel1.jpg")).getImage());
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void init()
    {
       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);        
        this.getContentPane().setLayout(new BorderLayout());
        this.setSize(new Dimension(800, 650));
        this.setTitle("Reportes");
        this.setResizable(false);
       
        panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(750, 650));
        panel.setBackground(new Color(0, 0, 51));
        
        anadirBotones();
        crearTabs();
        anadirTabs();   
    }


    private void anadirBotones()
    {
        printJButton = new JButton();
        pdfJButton = new JButton();
        
        pdfJButton.setIcon(new ImageIcon(getClass().getResource("/img/pdf.png")));
        pdfJButton.setToolTipText("Generar pdf");
        pdfJButton.setDisabledIcon(new ImageIcon(getClass().getResource("/img/pdf.png"))); 
        pdfJButton.setSelectedIcon(new ImageIcon(getClass().getResource("/img/pdf.png"))); 
        pdfJButton.setBackground(new Color(51,51,55));
        pdfJButton.setPreferredSize(new Dimension(30, 30));
        pdfJButton.addMouseListener(c);
        
        printJButton.setIcon(new ImageIcon(getClass().getResource("/img/printer.png")));
        printJButton.setToolTipText("Imprimir");
        printJButton.setDisabledIcon(new ImageIcon(getClass().getResource("/img/printer.png")));
        printJButton.setPressedIcon(new ImageIcon(getClass().getResource("/img/printer.png")));
        printJButton.setBackground(new Color(51,51,55));
        printJButton.setPreferredSize(new Dimension(30, 30));
        printJButton.addMouseListener(c);
        
        panel.setLayout(null);
        panel.add(printJButton);
        printJButton.setBounds (750,10,35,35);
        panel.add(pdfJButton);
        pdfJButton.setBounds (710,10,35,35);
    }
    
    private void crearTabs()
    {
        
       
        tabTablas = new JPanel();
        tabTablas.setPreferredSize(new Dimension(750,300));
        tabTablas.setLayout(new BorderLayout());
        tabTablas.add(panelTabla);
       
        tabGraficoBarras = new JPanel();
        tabGraficoBarras.setPreferredSize(new Dimension(750,500));
        tabGraficoBarras.setLayout(new BorderLayout());
        tabGraficoBarras.add(g.generarGraficoBarras(tabla, opcion));
        
        tabGraficoDE = new JPanel();
        tabGraficoDE.setPreferredSize(new Dimension(750,500));
        tabGraficoDE.setLayout(new BorderLayout());
        tabGraficoDE.add(g.generarGraficoDobleEje(tabla, opcion));
        
        tabMejorO = new JPanel();
        tabMejorO.setPreferredSize(new Dimension(750,500));
        tabMejorO.setLayout(new BorderLayout());
        tabMejorO.add(panelMejorOpcion);
    }
    
    private void anadirTabs()
    {
        tabPanel = new JTabbedPane();
        
        tabPanel.addTab("Tabla" , tabTablas);
        tabPanel.addTab("Grafico de barras" , tabGraficoBarras);
        tabPanel.addTab("Grafico de doble eje" , tabGraficoDE);
        tabPanel.addTab("Mejor opcion" , tabMejorO);
        
        tabPanel.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e) {
                int nroTab = nroTab();
                
                if(nroTab == 0)
                {
                   printJButton.setEnabled(true);
                   pdfJButton.setEnabled(true);
                }
                else
                {
                    if(nroTab == 1)
                    {
                        printJButton.setEnabled(true);
                        pdfJButton.setEnabled(false);                        
                    }
                    else
                    {
                        if(nroTab == 2)
                        {
                            printJButton.setEnabled(true);
                            pdfJButton.setEnabled(false);
                        }
                        else
                        {
                            if(nroTab == 3)
                            {
                                printJButton.setEnabled(true);
                                pdfJButton.setEnabled(true);
                            }
                        }
                    }
                }
            }
        });
        
        this.getContentPane().add(panel);
        panel.add(tabPanel );
        tabPanel.setBounds(5, 45, 790, 560);
    }
    
    public void imprimir(int op) throws PrinterException
    {
        if(op == 1)
          panelImprimir = tabGraficoBarras;
        else
        {
            if(op == 2)
                panelImprimir = tabGraficoDE;
            else
            {
                if(op == 3)
                    panelImprimir = tabMejorO;
            }
        }
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        job.printDialog(); 
        job.print();
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException
    {
        if (pageIndex > 0) return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D)graphics;
        g2d.translate(pageFormat.getImageableX()+70, pageFormat.getImageableY()+130);
        g2d.scale(0.6, 0.6); 
        panelImprimir.printAll(graphics);
        return PAGE_EXISTS;                
    }
    
    public JButton getPdfJButton()
    {
        return pdfJButton;
    }

    public JButton getPrintJButton()
    {
        return printJButton;
    }
    
    public JPanel getGraficoBarras()
    {
        return g.generarGraficoBarras(tabla, opcion);
    }
    
    public JPanel getGraficoDobleEje()
    {
        return g.generarGraficoDobleEje(tabla, opcion);
    }

    public int nroTab()
    {
        return tabPanel.getSelectedIndex();
    }

}

