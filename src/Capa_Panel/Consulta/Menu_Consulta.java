/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Consulta;

import Capa_Logica.SesionUsuario;
import Capa_Panel.Seguridad.*;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

/**
 *
 * @author User
 */
public class Menu_Consulta extends javax.swing.JFrame {

    /**
     * Creates new form Menu_Seguridad
     */
    public Menu_Consulta() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/img/Santander.png")).getImage());
        this.setTitle("MÓDULO DE CONSULTA");
        SesionUsuario.fr=this;
    }
    
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2) - 22;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpane = new javax.swing.JDesktopPane();
        jPanel1 = new javax.swing.JPanel();
        btPersonal = new javax.swing.JButton();
        btSalir = new javax.swing.JButton();
        btPersonal1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        dpane.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarven.png"))); // NOI18N
        btPersonal.setToolTipText("Consulta Ventas");
        btPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonalActionPerformed(evt);
            }
        });
        jPanel1.add(btPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 0, 35, 29));

        btSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btSalir.setToolTipText("Salir");
        btSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 0, 35, 29));

        btPersonal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarcom.png"))); // NOI18N
        btPersonal1.setToolTipText("Consulta Compras");
        btPersonal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal1ActionPerformed(evt);
            }
        });
        jPanel1.add(btPersonal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 35, 29));

        jPanel1.setBounds(0, 0, 480, 30);
        dpane.add(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu2.setText("Consulta Compras");
        jMenu2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Consulta Ventas");
        jMenu3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Salir");
        jMenu4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpane, javax.swing.GroupLayout.DEFAULT_SIZE, 1168, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dpane, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ccompras(){
        InterFrmConsultaCompras conscompras=new InterFrmConsultaCompras();
        dpane.add(conscompras);
        centerJIF(conscompras);
        conscompras.setVisible(true);
    }
    
    private void cventas(){
        InterFrmConsultaVentas consventas=new InterFrmConsultaVentas();
        dpane.add(consventas);
        centerJIF(consventas);
        consventas.setVisible(true);
    }
    
    private void btPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonalActionPerformed
        // TODO add your handling code here:
        cventas();
    }//GEN-LAST:event_btPersonalActionPerformed

    private void btSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btSalirActionPerformed
        
    private void btPersonal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal1ActionPerformed
        // TODO add your handling code here:
        ccompras();
    }//GEN-LAST:event_btPersonal1ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        // TODO add your handling code here:
        ccompras();
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        cventas();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jMenu4MouseClicked

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Seguridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Seguridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Seguridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Seguridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Seguridad().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPersonal;
    private javax.swing.JButton btPersonal1;
    private javax.swing.JButton btSalir;
    public static javax.swing.JDesktopPane dpane;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
