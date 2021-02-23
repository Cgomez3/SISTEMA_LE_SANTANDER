/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Importar;

import Capa_Logica.SesionUsuario;
import java.awt.Point;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author User
 */

public class JDCargaRegistros extends javax.swing.JDialog {
 Point locked=null;
 
    /**
     * Creates new form JDCargaReportes
     */
    public JDCargaRegistros(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        dlg.setValue(0);
        lockLocation();
        super.addComponentListener(new ComponentAdapter() {
            public void componentMoved(ComponentEvent e) {
                if (locked != null) {
                    setLocation(locked);
                }
            }
        });
    }
    
    public void lockLocation() {
        locked = super.getLocation();
    }
    
    public static Timer tiempo;

    public void cerrar() {
        this.dispose();
    }

    public class progreso implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            int n = dlg.getValue();
            if (n < 100) {
                n++;
                lblCargaReporte.setText("Guardando registros " + n + "%");
                dlg.setValue(n);
            } else {
                tiempo.stop();
                cerrar();
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCargaReporte = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Espere un momento");
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        dlg.setString("");

        lblCargaReporte.setText("Cargando");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dlg, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCargaReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCargaReporte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dlg, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        if (SesionUsuario.nroaregistrar > 0 && SesionUsuario.nroaregistrar < 300) {
            tiempo = new Timer(40, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 300 && SesionUsuario.nroaregistrar < 1000) {
            tiempo = new Timer(80, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 1000 && SesionUsuario.nroaregistrar < 2000) {
            tiempo = new Timer(85, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 2000 && SesionUsuario.nroaregistrar < 3000) {
            tiempo = new Timer(90, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 3000 && SesionUsuario.nroaregistrar < 4000) {
            tiempo = new Timer(130, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 4000 && SesionUsuario.nroaregistrar < 6000) {
            tiempo = new Timer(300, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 6000 && SesionUsuario.nroaregistrar < 8000) {
            tiempo = new Timer(320, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 8000 && SesionUsuario.nroaregistrar < 10000) {
            tiempo = new Timer(350, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 10000 && SesionUsuario.nroaregistrar < 12000) {
            tiempo = new Timer(400, new progreso());
            tiempo.start();
        }
        if (SesionUsuario.nroaregistrar >= 12000) {
            tiempo = new Timer(500, new progreso());
            tiempo.start();
        }
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(JDCargaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDCargaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDCargaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDCargaRegistros.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDCargaRegistros dialog = new JDCargaRegistros(new javax.swing.JFrame(), true);
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
    private final javax.swing.JProgressBar dlg = new javax.swing.JProgressBar();
    private javax.swing.JLabel lblCargaReporte;
    // End of variables declaration//GEN-END:variables
}
