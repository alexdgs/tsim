/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;

import java.util.ArrayList;
import javax.swing.JViewport;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Modelo;

/**
 *
 * @author Jose
 */
public class TablaCombinado extends javax.swing.JDialog {

    Object[][] tabla;
    final String[] columns = {
                "Mes",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "D. sat. Simple", "D. sat. Doble", "D. sat. Suite Jr.",
                "D. ins. Simple", "D. ins. Doble", "D. ins. Suite Jr.",
                "Cant. Simple", "Cant. Doble", "Cant. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Total Ingreso Actual",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "D. sat. Simple", "D. sat. Doble", "D. sat. Suite Jr.",
                "D. ins. Simple", "D. ins. Doble", "D. ins. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Inc. Hab. Simple", "Inc. Hab. Doble", "Inc. Hab. Suite Jr.",
                "Cant. Simple", "Cant. Doble", "Cant. Suite Jr.",
                "Inc. Hab. Simple", "Inc. Hab. Doble", "Inc. Hab. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "D. Simple", "D. Doble", "D. Suite Jr.",
                "D. sat. Simple", "D. sat. Doble", "D. sat. Suite Jr.",
                "D. ins. Simple", "D. ins. Doble", "D. ins. Suite Jr.",
                "Cant. Simple", "Cant. Doble", "Cant. Suite Jr.",
                "Inc. Hab. Simple", "Inc. Hab. Doble", "Inc. Hab. Suite Jr.",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Cant. Simple*", "Cant. Doble*", "Cant. Suite Jr.*",
                "Precio Simple*", "Precio Doble*", "Precio Suite Jr.*",
                "I. Simple", "I. Doble", "I. Suite Jr.",
                "Total Nuevo Ingreso"
            };
    /**
     * Creates new form TablaVariacionPrecios
     */
    public TablaCombinado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        tabla = new Object[12][15];
        initComponents();
        setLocationRelativeTo(null);
    }

    public TablaCombinado() {
        super(new javax.swing.JFrame(), true);
        initComponents();
        this.setVisible(false);
        setLocationRelativeTo(null);
        TableColumn columnaTabla; 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        aceptarJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));

        panelTabla.setBackground(new java.awt.Color(0, 0, 51));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tabla,
            columns
        ));
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.getTableHeader().setResizingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        aceptarJButton.setText("Aceptar");
        aceptarJButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aceptarJButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                aceptarJButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout panelTablaLayout = new javax.swing.GroupLayout(panelTabla);
        panelTabla.setLayout(panelTablaLayout);
        panelTablaLayout.setHorizontalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(aceptarJButton))
        );
        panelTablaLayout.setVerticalGroup(
            panelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarJButton)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Mejor combinación: Precio y Habitaciones");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabla, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(14, 14, 14)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aceptarJButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarJButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_aceptarJButtonMouseClicked

    private void aceptarJButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aceptarJButtonMousePressed
        this.dispose();
    }//GEN-LAST:event_aceptarJButtonMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TablaCombinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaCombinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaCombinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaCombinado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TablaCombinado dialog = new TablaCombinado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelTabla;
    // End of variables declaration//GEN-END:variables

    public Object[] getTabla(){
        return tabla;
    }

    public void setTabla(Object[][] tabla) {
        this.tabla = tabla;
    }
    
    public void mostrar(Object[][] t) {
        // Create new TableModel and set it in JTable
        jTable1.setModel(new javax.swing.table.DefaultTableModel(t,columns));
        
        setVisible(true);
    }
}
