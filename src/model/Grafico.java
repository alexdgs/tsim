/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author A
 */


public class Grafico
{
    
    public void generarPieHabitaciones(int simple, int doble, int suite)
    {
       int total=simple+doble+suite;
       DefaultPieDataset pieH = new DefaultPieDataset();
       
       /*pieH.setValue("Suite. Jr", new Double((suite*100)/total));
       pieH.setValue("Simple", new Double((simple*100)/total));
       pieH.setValue("Doble", new Double((doble*100)/total));
       */
       
       pieH.setValue("Suite. Jr", new Double(suite));
       pieH.setValue("Simple", new Double(simple));
       pieH.setValue("Doble", new Double(doble));
       
       JFreeChart chart = ChartFactory.createPieChart3D("Cantidad de Habitaciones", pieH, true, true, false);
       PiePlot3D p=(PiePlot3D)chart.getPlot();
       p.setSectionPaint("Suite. Jr", new Color(51,0,102));
       p.setSectionPaint("Simple", new Color(0,0,153));
       p.setSectionPaint("Doble", new Color(0,116,215));
      
       p.setStartAngle(290);
        
       p.setForegroundAlpha(0.5f);
        
       ChartFrame frame= new ChartFrame("Cantidad de Habitaciones", chart);
       frame.setVisible(true);
       frame.setSize(450, 500);
       frame.setLocationRelativeTo(null);
    }
    
    public void generarGraficoBarras(Object [][] tabla, int opcion)
    {
        String [] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre","Noviembre", "Diciembre"};
        String [] tipo ={"Simple Actual", "Simple Mejorada", "Doble Actual", "Doble Mejorada", "Suite Jr. Actual", "Suite Jr. Mejorada"};
        
        int [] primeraOpcion = {7, 16, 8, 17, 9, 18};
        int [] segundaOpcion = {4, 11, 5, 12, 6, 13};
        int [] terceraOpcion ={};
        
        int [] posiciones =new int [6];
        
        if(opcion==1) posiciones=Arrays.copyOfRange(primeraOpcion, 0, 6);
        else
        {
            if(opcion==2) posiciones=Arrays.copyOfRange(segundaOpcion, 0, 6);
            else posiciones=Arrays.copyOfRange(terceraOpcion, 0, 6);
        }
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(int i=0; i<12; i++)
            for(int j=0; j<6; j++)
                dataset.setValue(new Double(""+tabla[i][posiciones[j]]), tipo[j], meses[i]);
            
        
        String titulo="Comparación de demanda instatisfecha Actual con Mejorado";
        if(opcion==2)titulo="Comparación de ingresos Actual con Mejorado";
        else if(opcion==3)titulo="";
        
        JFreeChart chart = ChartFactory.createBarChart3D( titulo, "Meses", "Valores", dataset, PlotOrientation.VERTICAL, true, true, false );
        
        ChartFrame frame= new ChartFrame(titulo, chart);
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);   
    } 
    public void generarGraficoDobleEje(Object [][] tabla, int opcion)
    {  
        int [] primeraOpcion = {7, 8, 9, 16, 17, 18};
        int [] segundaOpcion = {4, 5, 6, 11, 12, 13};
        int [] terceraOpcion ={};
        
        int [] posiciones =new int [6];
        
        if(opcion==1) posiciones=Arrays.copyOfRange(primeraOpcion, 0, 6);
        else
        {
            if(opcion==2) posiciones=Arrays.copyOfRange(segundaOpcion, 0, 6);
            else posiciones=Arrays.copyOfRange(terceraOpcion, 0, 6);
        }
        
        XYSeries actual = new XYSeries("Actual");
        XYSeries mejorado = new XYSeries("Mejorado");
        
        double valorA=0;
        double valorM=0;
        
        for(int i=1; i<=12; i++)
        {
           valorA+=new Double(""+tabla[i-1][posiciones[0]])+new Double(""+tabla[i-1][posiciones[1]])+new Double(""+tabla[i-1][posiciones[2]]);
           valorM+=new Double(""+tabla[i-1][posiciones[3]])+new Double(""+tabla[i-1][posiciones[4]])+new Double(""+tabla[i-1][posiciones[5]]);
           actual.add(i, valorA);
           mejorado.add(i, valorM);
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(actual);
        dataset.addSeries(mejorado);
        
        String titulo="Comparación de demanda instatisfecha Actual con Mejorado";
        if(opcion==2)titulo="Comparación de ingresos Actual con Mejorado";
        else if(opcion==3)titulo="";
        
        JFreeChart chart = ChartFactory.createXYLineChart(titulo,"Meses","Valores",dataset,PlotOrientation.VERTICAL, true, true, false);
    
        ChartFrame frame= new ChartFrame(titulo, chart);
        frame.setVisible(true);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);   
        
    }
}
