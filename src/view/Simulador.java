/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import com.jtattoo.plaf.*;
import controller.Controlador;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JTextArea;
 
/** 
 *
 * @author User
 */
public class Simulador extends javax.swing.JFrame {
    
    // Simulation model constants
    final int MEJOR_HABITACIONES = 1;
    final int MEJOR_PRECIO = 2;
    final int MEJOR_COMBINACION = 3;
    
    // Simulation state constants
    final int DETENIDO = 4;
    final int EN_EJECUCION = 5;
    final int PAUSADO = 6;
    final int COMPLETADO = 7;
    
    TablaCombinado tc;
    TablaVariacionPrecios tvp;
    TablaMejorIncremento tmi;
    Controlador c;
    
    JLabel[] rooms; // Rooms
    JLabel jLabel9; // Background
    ImageIcon on; // Image for occupied room
    ImageIcon unavailable; // Image for unavailable room
    int dem; // last known demand
    int [] habitaciones;
    int totalHabitaciones;
    
    Font font;
    Color color;
    JLabel porcentajeLabel;
    JLabel porcentaje;
    /**
     * Creates new form Simulador
     */
    public Simulador(Controlador c) {
        try{
          UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
        }
        catch(Exception e){}
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/img/hotel1.jpg")).getImage());  
        setListeners(c);
        this.c=c;
        this.setLocationRelativeTo(null);
        
        resultadosJButton.setEnabled(false);
        jSlider1.addChangeListener(c);
        tc = new TablaCombinado();
        tvp = new TablaVariacionPrecios();
        tmi = new TablaMejorIncremento();
        
        // Room animation setter block
        jPanel4.setLayout(null); // jPanel4 is a JLayeredPane
        jLabel9 = new JLabel(new ImageIcon(getClass().getResource("/img/ani_background.png")));
        jLabel9.setVisible(true);
        jPanel4.add(jLabel9, new Integer(-1)); // Send it to bottom
        jLabel9.setBounds(0,0,417,240); // Size and locate
        //jPanel4.setVisible(true);
        int x = 41;
        int y = 177;
        on = new ImageIcon(getClass().getResource("/img/room_on.png"));
        unavailable = new ImageIcon(getClass().getResource("/img/room_unavailable.png"));
        rooms = new JLabel[121];
        for(int r = 1; r < rooms.length; r++) {
            rooms[r] = new JLabel(unavailable);
            jPanel4.add(rooms[r],new Integer(r));
            rooms[r].setBounds(x,y,14,14);
            //rooms[r].setVisible(true);
            x += 17;
            if(x > 364) {
                x = 41;
                y -= 22;
            }
        }
        setAvailableRooms(100);
        dem = 0;
        
        font = new Font("Tahoma", Font.BOLD, 20);
        color = new Color(0,200,0);
        
        porcentajeLabel = new JLabel("Porcentaje ocupado:");
        porcentajeLabel.setForeground(color);
        porcentajeLabel.setFont(font);
        jPanel4.add(porcentajeLabel, new Integer(130));
        porcentajeLabel.setBounds(65,13,250,50);
        
        porcentaje = new JLabel("100%");
        porcentaje.setForeground(color);
        porcentaje.setFont(font);
        jPanel4.add(porcentaje, new Integer(131));
        porcentaje.setBounds(290,13,100,50);
    }
    
    private void setListeners(Controlador c) {
        cambiarModeloJMenuItem.addMouseListener(c);
        cambiarDatoJMenuItem.addMouseListener(c);
        opcionesAvanzadasJMenuItem.addMouseListener(c);
        nuevoJMenuItem.addMouseListener(c);
        abrirJMenuItem.addMouseListener(c);
        guardarJMenuItem.addMouseListener(c);
        cerrarJMenuItem.addMouseListener(c);
        manualJMenuItem.addMouseListener(c);
        acercaDeJMenuItem.addMouseListener(c);
        restablecerJMenuItem.addMouseListener(c);
        pieChartJButton.addMouseListener(c);
        resultadosJButton.addMouseListener(c);
        playJButton.addMouseListener(c);
        pauseJButton.addMouseListener(c);
        stopJButton.addMouseListener(c);
        firstJButton.addMouseListener(c);
        lastJButton.addMouseListener(c);
        this.addWindowListener(c);
    }
    
    // Show window elements according to choosen option
    public void mostrarOpcion(int op, int[] hab) {
        habitaciones=hab;
        switch(op) {
            case MEJOR_HABITACIONES: tipoJLabel.setText("Mejor cantidad de habitaciones"); break;
            case MEJOR_PRECIO: tipoJLabel.setText("Mejor precio de habitaciones"); break;
            case MEJOR_COMBINACION: tipoJLabel.setText("Mejor combinación: precio y habitaciones"); break;
        }
        jProgressBar2.setMaximum(hab[0]);
        jProgressBar3.setMaximum(hab[1]);
        jProgressBar4.setMaximum(hab[2]);
        //setAvailableRooms(hab[0] + hab[1] + hab[2]);
        totalHabitaciones = hab[0] + hab[1] + hab[2];
        this.setVisible(true);
        dem=0;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JLayeredPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar4 = new javax.swing.JProgressBar();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();
        firstJButton = new javax.swing.JButton();
        playJButton = new javax.swing.JButton();
        lastJButton = new javax.swing.JButton();
        pauseJButton = new javax.swing.JButton();
        stopJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        resultadosJButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        tipoJLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        tipoJLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tipoJLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tipoJLabel3 = new javax.swing.JLabel();
        pieChartJButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivoJMenu = new javax.swing.JMenu();
        nuevoJMenuItem = new javax.swing.JMenuItem();
        abrirJMenuItem = new javax.swing.JMenuItem();
        guardarJMenuItem = new javax.swing.JMenuItem();
        cerrarJMenuItem = new javax.swing.JMenuItem();
        simulacionJMenu = new javax.swing.JMenu();
        cambiarModeloJMenuItem = new javax.swing.JMenuItem();
        cambiarDatoJMenuItem = new javax.swing.JMenuItem();
        opcionesAvanzadasJMenuItem = new javax.swing.JMenuItem();
        restablecerJMenuItem = new javax.swing.JMenuItem();
        ayudaJMenu = new javax.swing.JMenu();
        manualJMenuItem = new javax.swing.JMenuItem();
        acercaDeJMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Hotel Diplomat");
        setResizable(false);

        jPanel.setBackground(new java.awt.Color(0, 0, 51));

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Demanda del día:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("0 habitaciones");

        jPanel4.setPreferredSize(new java.awt.Dimension(417, 240));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 0, 51));
        jPanel5.setPreferredSize(new java.awt.Dimension(417, 230));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Demanda por tipo de habitación:");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/simple.png"))); // NOI18N
        jLabel13.setText("jLabel13");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/doble.png"))); // NOI18N
        jLabel15.setText("jLabel13");

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/suite.png"))); // NOI18N
        jLabel16.setText("jLabel13");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("Simple");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Doble");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("Suite Jr.");

        jProgressBar2.setString("0");
        jProgressBar2.setStringPainted(true);

        jProgressBar3.setString("0");
        jProgressBar3.setStringPainted(true);

        jProgressBar4.setString("0");
        jProgressBar4.setStringPainted(true);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jProgressBar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel19))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("Ingresos del mes 0:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("$ 0.00");

        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("Progreso de la Simulación:");

        jProgressBar1.setMaximum(365);
        jProgressBar1.setStringPainted(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Velocidad de la Simulación");

        jSlider1.setBackground(new java.awt.Color(0, 0, 51));
        jSlider1.setMaximum(1000);
        jSlider1.setMinimum(10);
        jSlider1.setValue(500);

        firstJButton.setBackground(new java.awt.Color(0, 0, 51));
        firstJButton.setForeground(new java.awt.Color(0, 0, 51));
        firstJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/first.png"))); // NOI18N
        firstJButton.setToolTipText("Disminuir velocidad");
        firstJButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        playJButton.setBackground(new java.awt.Color(0, 0, 51));
        playJButton.setForeground(new java.awt.Color(0, 0, 51));
        playJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/play.png"))); // NOI18N
        playJButton.setToolTipText("Reproducir");
        playJButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lastJButton.setBackground(new java.awt.Color(0, 0, 51));
        lastJButton.setForeground(new java.awt.Color(0, 0, 51));
        lastJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/last.png"))); // NOI18N
        lastJButton.setToolTipText("Aumentar velocidad");
        lastJButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        pauseJButton.setBackground(new java.awt.Color(0, 0, 51));
        pauseJButton.setForeground(new java.awt.Color(0, 0, 51));
        pauseJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pause.png"))); // NOI18N
        pauseJButton.setToolTipText("Pausar");
        pauseJButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        stopJButton.setBackground(new java.awt.Color(0, 0, 51));
        stopJButton.setForeground(new java.awt.Color(0, 0, 51));
        stopJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/stop.png"))); // NOI18N
        stopJButton.setToolTipText("Detener");
        stopJButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));
        jPanel2.setForeground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 51));
        jLabel1.setText("ESTADO DE LA SIMULACIÓN");

        resultadosJButton.setText("Ver resultados");
        resultadosJButton.setToolTipText("Ver reportes");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Opción elegida:");

        tipoJLabel.setForeground(new java.awt.Color(0, 0, 51));
        tipoJLabel.setText("0");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setForeground(new java.awt.Color(204, 204, 255));

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 102));
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Velocidad actual:");

        tipoJLabel1.setForeground(new java.awt.Color(0, 0, 51));
        tipoJLabel1.setText("Aprox. 500 ms/día");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Estado de la Simulación:");

        tipoJLabel2.setForeground(new java.awt.Color(0, 0, 51));
        tipoJLabel2.setText("Detenido");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Día de la Simulación:");

        tipoJLabel3.setForeground(new java.awt.Color(0, 0, 51));
        tipoJLabel3.setText("0");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(resultadosJButton)
                        .addGap(13, 13, 13))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoJLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoJLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoJLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoJLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tipoJLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tipoJLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tipoJLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tipoJLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultadosJButton)
                .addGap(23, 23, 23))
        );

        pieChartJButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pieChart.png"))); // NOI18N
        pieChartJButton.setToolTipText("Generar grafico de torta");
        pieChartJButton.setBorder(null);

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(firstJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(playJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pauseJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pieChartJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(firstJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(stopJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(playJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lastJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pauseJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pieChartJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        archivoJMenu.setLabel("Archivo");

        nuevoJMenuItem.setLabel("Nuevo");
        archivoJMenu.add(nuevoJMenuItem);

        abrirJMenuItem.setText("Abrir...");
        archivoJMenu.add(abrirJMenuItem);

        guardarJMenuItem.setText("Guardar...");
        archivoJMenu.add(guardarJMenuItem);

        cerrarJMenuItem.setText("Cerrar");
        archivoJMenu.add(cerrarJMenuItem);

        jMenuBar1.add(archivoJMenu);

        simulacionJMenu.setText("Simulación");

        cambiarModeloJMenuItem.setText("Cambiar Modelo");
        simulacionJMenu.add(cambiarModeloJMenuItem);

        cambiarDatoJMenuItem.setText("Cambiar Datos");
        simulacionJMenu.add(cambiarDatoJMenuItem);

        opcionesAvanzadasJMenuItem.setText("Opciones Avanzadas");
        simulacionJMenu.add(opcionesAvanzadasJMenuItem);

        restablecerJMenuItem.setText("Restablecer Valores");
        simulacionJMenu.add(restablecerJMenuItem);

        jMenuBar1.add(simulacionJMenu);

        ayudaJMenu.setText("Ayuda");

        manualJMenuItem.setText("Manual de Usuario");
        ayudaJMenu.add(manualJMenuItem);

        acercaDeJMenuItem.setText("Acerca de...");
        ayudaJMenu.add(acercaDeJMenuItem);

        jMenuBar1.add(ayudaJMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem abrirJMenuItem;
    private javax.swing.JMenuItem acercaDeJMenuItem;
    private javax.swing.JMenu archivoJMenu;
    private javax.swing.JMenu ayudaJMenu;
    private javax.swing.JMenuItem cambiarDatoJMenuItem;
    private javax.swing.JMenuItem cambiarModeloJMenuItem;
    private javax.swing.JMenuItem cerrarJMenuItem;
    private javax.swing.JButton firstJButton;
    private javax.swing.JMenuItem guardarJMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLayeredPane jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton lastJButton;
    private javax.swing.JMenuItem manualJMenuItem;
    private javax.swing.JMenuItem nuevoJMenuItem;
    private javax.swing.JMenuItem opcionesAvanzadasJMenuItem;
    private javax.swing.JButton pauseJButton;
    private javax.swing.JButton pieChartJButton;
    private javax.swing.JButton playJButton;
    private javax.swing.JMenuItem restablecerJMenuItem;
    private javax.swing.JButton resultadosJButton;
    private javax.swing.JMenu simulacionJMenu;
    private javax.swing.JButton stopJButton;
    private javax.swing.JLabel tipoJLabel;
    private javax.swing.JLabel tipoJLabel1;
    private javax.swing.JLabel tipoJLabel2;
    private javax.swing.JLabel tipoJLabel3;
    // End of variables declaration//GEN-END:variables

    public JButton getJBVerResultados(){
        return resultadosJButton;
    }
    
    public JButton getJBPlay(){
        return playJButton;
    } 
    public JButton getPieChart(){
        return pieChartJButton;
    }
    
    public JMenuItem getCambiarModeloJMenuItem(){
        return cambiarModeloJMenuItem;
    }
    
    public JMenuItem getCambiarDatoJMenuItem(){
        return cambiarDatoJMenuItem;
    }
    
    public JMenuItem getOpcionesAvanzadasJMenuItem() {
        return opcionesAvanzadasJMenuItem;
    }
    
    public JMenuItem getNuevoJMenuItem() {
        return nuevoJMenuItem;
    }
    
    public JMenuItem getAbrirJMenuItem() {
        return abrirJMenuItem;
    }
    
    public JMenuItem getGuardarJMenuItem() {
        return guardarJMenuItem;
    }
    
    public JMenuItem getCerrarJMenuItem(){
        return cerrarJMenuItem;
    }
    
    public JMenuItem getManualJMenuItem(){
        return manualJMenuItem;
    }
    
    public JMenuItem getAcercaDeJMenuItem(){
        return acercaDeJMenuItem;
    }
    
    public JMenuItem getRestablecerJMenuItem(){
        return restablecerJMenuItem;
    }
    
    public JTextArea getjTextArea1() {
        return jTextArea1;
    }
    
    public TablaCombinado getTablaCombinado(){
        return tc;
    }
    
    public TablaMejorIncremento getTablaMejorIncremento(){
        return tmi;
    }
    
    public TablaVariacionPrecios getTablaVariacionPrecios(){
        return tvp;
    }
    
    public void setTablaVariacionPrecios(TablaVariacionPrecios t){
        this.tvp = t;
    }
    
    public void setTablaMejorIncremento(TablaMejorIncremento t){
        this.tmi = t;
    }

    public void cleanTextArea1() {
        jTextArea1.setText("");
    }
    
    public void disableButton()
    {
        resultadosJButton.setEnabled(false);
        guardarJMenuItem.setEnabled(false);
    }
    
    public void cambiarEstadoMenuSimulacion(boolean estado){
        cambiarDatoJMenuItem.setEnabled(estado);
        cambiarModeloJMenuItem.setEnabled(estado);
        opcionesAvanzadasJMenuItem.setEnabled(estado);
        restablecerJMenuItem.setEnabled(estado);
    }
    
    // Sets speed text to delay value
    public void setSpeed(int delay) {
        tipoJLabel1.setText("Aprox. " + delay + " ms/día");
    }
    
    // Sends results table from Controller to adecuate table frame and show
    public JPanel mostrarResultados(Object[][] tabla, int op) {
        switch(op) {
            case 1: return tmi.mostrar(tabla);
            case 2: return tvp.mostrar(tabla);
            case 3: return tc.mostrar(tabla); 
        }
        return null;
    }
    
    // Enables results button
    public void enableResults(boolean b) {
        resultadosJButton.setEnabled(b);
        guardarJMenuItem.setEnabled(b);
    }
    
    public void showText(String s) {
        jTextArea1.setText(s);
    }
    
    // Sets interface elements to match current simulation state
    public void setSimState(int st) {
        switch(st) {
            case DETENIDO:
                tipoJLabel2.setText("Detenido");
                jTextArea1.setText("La simulación ha sido detenida.");
                break;
            case EN_EJECUCION:
                tipoJLabel2.setText("En ejecución");
                jTextArea1.setText("Simulación en proceso...\n\nEn cuanto finalize el proceso aparecerá un\nresumen en esta área.");
                break;
            case PAUSADO:
                tipoJLabel2.setText("Pausado");
                jTextArea1.setText("Simulación en pausa.\n\nPresione el botón Iniciar o el botón Pausa\npara reanudar la simulación.");
                break;
            case COMPLETADO:
                tipoJLabel2.setText("Completado");
        }
    }

    public Object getJBStop() {
        return stopJButton;
    }

    public Object getJBPause() {
        return pauseJButton;
    }

    public Object getJBFirst() {
        return firstJButton;
    }

    public Object getJBLast() {
        return lastJButton;
    }

    public int getSlider() {
        return jSlider1.getValue();
    }

    public void setSlider(int i) {
        if(i > jSlider1.getMaximum()) jSlider1.setValue(jSlider1.getMaximum());
        else if(i < jSlider1.getMinimum()) jSlider1.setValue(jSlider1.getMinimum());
        else jSlider1.setValue(i);
    }
    
    public void setAvailableRooms(int i) {
        for(int j = 1; j <= i; j++) {
            rooms[j].setIcon(on);
            rooms[j].setVisible(false);
        }
        for(int j = i+1; j < rooms.length; j++) {
            rooms[j].setIcon(unavailable);
            rooms[j].setVisible(true);
        }
    }
    
    public void setRooms(int i) {
        i = (int)((i*100)/totalHabitaciones);
        if(i>totalHabitaciones) i = 100;
        if(i > dem)
            for(int j = dem+1; j <= i; j++)
                rooms[j].setVisible(true);
        else if(i < dem)
            for(int j = dem; j > i; j--)
                rooms[j].setVisible(false);
        color = new Color(Math.min(200,i*4),Math.min(50,100-i)*4,0);
        porcentajeLabel.setForeground(color);
        porcentaje.setForeground(color);
        porcentaje.setText(i + "%");
        dem = i;
    }

    public void updateView(Object[] data) {
        if((boolean)data[8]) { // if monthly report
            jLabel10.setText("Ingresos del mes " + (int)data[7] + ":");
            jLabel11.setText(((double)data[4] < 0.0) ? "No disponible" : "$ " + (double)data[4]);
        } else { // if daily report
            tipoJLabel3.setText(Integer.toString((int)data[6]));
            jLabel8.setText((int)data[0] + " habitaciones.");
            setRooms((int)data[0]);
            jProgressBar2.setValue((int)data[1]);
            jProgressBar2.setString(Integer.toString((int)data[1]));
            jProgressBar3.setValue((int)data[2]);
            jProgressBar3.setString(Integer.toString((int)data[2]));
            jProgressBar4.setValue((int)data[3]);
            jProgressBar4.setString(Integer.toString((int)data[3]));
            jProgressBar1.setValue((int)data[6]);
        }
    }
    
    public void reinicializar()
    {
        jLabel8.setText("0 habitaciones");
        jLabel10.setText("Ingresos del mes 0:");
        jLabel11.setText("$ 0.00");
        tipoJLabel3.setText("0");
        jProgressBar2.setValue(0);
        jProgressBar2.setString("0");
        jProgressBar3.setValue(0);
        jProgressBar3.setString("0");
        jProgressBar4.setValue(0);
        jProgressBar4.setString("0");
        jProgressBar1.setValue(0);
        setAvailableRooms(100);
        habitaciones = c.getModelo().getCantidadHabitaciones();
        totalHabitaciones = habitaciones[0] + habitaciones[1] + habitaciones[2];
        dem=0;
    }
    
    public void setModeloPorDefecto(){
        cleanTextArea1();
        disableButton();
        c.tipo = 3;
        mostrarOpcion(c.tipo,c.getModelo().getCantidadHabitaciones());
        reinicializar();
    }

}
