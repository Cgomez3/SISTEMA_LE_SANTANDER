/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Presentacion;

import Capa_Panel.Consulta.InterFrmConsultaCompras;
import Capa_Panel.Consulta.InterFrmConsultaVentas;
import Capa_Panel.Exportar.FrmExportar;
import Capa_Panel.Formatear.InterFrmFormatearCompras;
import Capa_Panel.Formatear.InterFrmFormatearDAOTCompras;
import Capa_Panel.Formatear.InterFrmFormatearDAOTVentas;
import Capa_Panel.Formatear.InterFrmFormatearVentas;
import Capa_Panel.Formatear.InterFrmImportarExoInafVentas;
import Capa_Panel.Importar.InterFrmImportarCompras;
import Capa_Panel.Importar.InterFrmImportarVentas;
import Capa_ProgPago.*;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 *
 * @author EXPERTYA
 */
public class MenuLibrosElectronico extends JFrame  {

    /**
     * Creates new form Menu
     */
    public MenuLibrosElectronico() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/img/Santander.png")).getImage());
        this.setSize(1099 , 700);
        this.setTitle("Sistema Santander 1.0 - Gerencia de Contabilidad / Libros Electrónicos");
        fondo();
    }
    
        private void fondo(){
            Icon expertya = new ImageIcon(getClass().getResource("/img/LIBROSSANTANDER.jpg"));
            lbllogo.setIcon(expertya);    
    }
    
     public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2 )-10;
        int height = ((desktopSize.height - jInternalFrameSize.height) /2)-45;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
    
      private void importar() {
        InterFrmImportarCompras importar = new InterFrmImportarCompras();
        dpane2.add(importar);
        centerJIF(importar);
        importar.setVisible(true);
    }
    
    private void ventas(){
        InterFrmImportarVentas impventas = new InterFrmImportarVentas();
        dpane2.add(impventas);
        centerJIF(impventas);
        impventas.setVisible(true);
    }
    
      private void formatearcom() {
        InterFrmFormatearCompras importar = new InterFrmFormatearCompras();
        dpane2.add(importar);
        centerJIF(importar);
        importar.setVisible(true);
    }
    
    private void formatearven(){
        InterFrmFormatearVentas impventas = new InterFrmFormatearVentas();
        dpane2.add(impventas);
        centerJIF(impventas);
        impventas.setVisible(true);
    }
    
    private void consultarcom() {
        InterFrmConsultaCompras importar = new InterFrmConsultaCompras();
        dpane2.add(importar);
        centerJIF(importar);
        importar.setVisible(true);
    }
    
    private void consultarven(){
        InterFrmConsultaVentas impventas = new InterFrmConsultaVentas();
        dpane2.add(impventas);
        centerJIF(impventas);
        impventas.setVisible(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        btSalir = new javax.swing.JButton();
        dpane2 = new javax.swing.JDesktopPane();
        lbllogo = new javax.swing.JLabel();
        btPersonal1 = new javax.swing.JButton();
        btPersonal = new javax.swing.JButton();
        btPersonal2 = new javax.swing.JButton();
        btPersonal3 = new javax.swing.JButton();
        btPersonal4 = new javax.swing.JButton();
        btPersonal5 = new javax.swing.JButton();
        btPersonal6 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnmatenimiento = new javax.swing.JMenu();
        Proveedores = new javax.swing.JMenuItem();
        TipoCambio = new javax.swing.JMenuItem();
        TipoCambio3 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        Salir = new javax.swing.JMenuItem();
        btnregistros = new javax.swing.JMenu();
        btncomprobantes = new javax.swing.JMenuItem();
        Consulta = new javax.swing.JMenuItem();
        Consulta1 = new javax.swing.JMenuItem();
        Programacion = new javax.swing.JMenu();
        ProgramadePagos = new javax.swing.JMenuItem();
        ProgramadePagos1 = new javax.swing.JMenuItem();
        Emision = new javax.swing.JMenu();
        TipoCambio1 = new javax.swing.JMenuItem();
        TipoCambio2 = new javax.swing.JMenuItem();
        menusalir = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenuItem3.setText("jMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(900, 650));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btSalir.setToolTipText("Salir");
        btSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 35, 29));

        dpane2.setBackground(new java.awt.Color(255, 255, 255));

        lbllogo.setForeground(new java.awt.Color(255, 255, 255));
        lbllogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LIBROSSANTANDER.jpg"))); // NOI18N
        lbllogo.setText("jLabel1");
        lbllogo.setBounds(0, 0, 1091, 613);
        dpane2.add(lbllogo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        getContentPane().add(dpane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 1093, 613));

        btPersonal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/importcompra.png"))); // NOI18N
        btPersonal1.setToolTipText("Importar Compras");
        btPersonal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal1ActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 35, 29));

        btPersonal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/importventa.png"))); // NOI18N
        btPersonal.setToolTipText("Importar Ventas");
        btPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonalActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 0, 35, 29));

        btPersonal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export.png"))); // NOI18N
        btPersonal2.setToolTipText("Exportar Compras/Ventas");
        btPersonal2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal2ActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 35, 29));

        btPersonal3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/formatcom.png"))); // NOI18N
        btPersonal3.setToolTipText("Importar Compras");
        btPersonal3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal3ActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 0, 35, 29));

        btPersonal4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/formatven.png"))); // NOI18N
        btPersonal4.setToolTipText("Importar Ventas");
        btPersonal4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal4ActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 35, 29));

        btPersonal5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarcom.png"))); // NOI18N
        btPersonal5.setToolTipText("Consulta Compras");
        btPersonal5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal5ActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 0, 35, 29));

        btPersonal6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscarven.png"))); // NOI18N
        btPersonal6.setToolTipText("Consultar Ventas");
        btPersonal6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPersonal6ActionPerformed(evt);
            }
        });
        getContentPane().add(btPersonal6, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 0, 35, 29));

        btnmatenimiento.setText("   Importar  ");
        btnmatenimiento.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        Proveedores.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        Proveedores.setText("Importar Compras");
        Proveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedoresActionPerformed(evt);
            }
        });
        btnmatenimiento.add(Proveedores);

        TipoCambio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        TipoCambio.setText("Importar Ventas");
        TipoCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoCambioActionPerformed(evt);
            }
        });
        btnmatenimiento.add(TipoCambio);

        TipoCambio3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        TipoCambio3.setText("Importar Diario");
        TipoCambio3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoCambio3ActionPerformed(evt);
            }
        });
        btnmatenimiento.add(TipoCambio3);
        btnmatenimiento.add(jSeparator5);

        Salir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        btnmatenimiento.add(Salir);

        jMenuBar1.add(btnmatenimiento);

        btnregistros.setText("  Formatear  ");
        btnregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnregistros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregistrosActionPerformed(evt);
            }
        });

        btncomprobantes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        btncomprobantes.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btncomprobantes.setText("Formatear Compras");
        btncomprobantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobantesActionPerformed(evt);
            }
        });
        btnregistros.add(btncomprobantes);

        Consulta.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        Consulta.setText("Formatear Ventas");
        Consulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultaActionPerformed(evt);
            }
        });
        btnregistros.add(Consulta);

        Consulta1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        Consulta1.setText("Formatear Diario");
        Consulta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Consulta1ActionPerformed(evt);
            }
        });
        btnregistros.add(Consulta1);

        jMenuBar1.add(btnregistros);

        Programacion.setText("  Exportar  ");
        Programacion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        ProgramadePagos.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        ProgramadePagos.setText("Exportar Compras/Ventas");
        ProgramadePagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProgramadePagosActionPerformed(evt);
            }
        });
        Programacion.add(ProgramadePagos);

        ProgramadePagos1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        ProgramadePagos1.setText("Exportar Libro Diario");
        ProgramadePagos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProgramadePagos1ActionPerformed(evt);
            }
        });
        Programacion.add(ProgramadePagos1);

        jMenuBar1.add(Programacion);

        Emision.setText("  Consulta  ");
        Emision.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        TipoCambio1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        TipoCambio1.setText("Consultar Compras");
        TipoCambio1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoCambio1ActionPerformed(evt);
            }
        });
        Emision.add(TipoCambio1);

        TipoCambio2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        TipoCambio2.setText("Consultar Ventas");
        TipoCambio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoCambio2ActionPerformed(evt);
            }
        });
        Emision.add(TipoCambio2);

        jMenuBar1.add(Emision);

        menusalir.setText("   Salir  ");
        menusalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        menusalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menusalirMouseClicked(evt);
            }
        });
        menusalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menusalirActionPerformed(evt);
            }
        });
        jMenuBar1.add(menusalir);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresActionPerformed
       importar();
    }//GEN-LAST:event_ProveedoresActionPerformed

    private void TipoCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoCambioActionPerformed
      ventas();
    }//GEN-LAST:event_TipoCambioActionPerformed

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void btncomprobantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprobantesActionPerformed
       formatearcom();
    }//GEN-LAST:event_btncomprobantesActionPerformed

    private void ConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultaActionPerformed
        formatearven();
    }//GEN-LAST:event_ConsultaActionPerformed

    private void ProgramadePagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProgramadePagosActionPerformed
        FrmExportar export=new FrmExportar();
        export.setVisible(true);
    }//GEN-LAST:event_ProgramadePagosActionPerformed

    private void btnregistrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregistrosActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_btnregistrosActionPerformed

    private void btSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btSalirActionPerformed

    private void menusalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menusalirActionPerformed
        dispose();
    }//GEN-LAST:event_menusalirActionPerformed

    private void TipoCambio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoCambio1ActionPerformed
        // TODO add your handling code here:
        //consultarcom();
//        InterFrmFormatearDAOTCompras daot=new InterFrmFormatearDAOTCompras();
        InterFrmImportarExoInafVentas daot=new InterFrmImportarExoInafVentas();
        dpane2.add(daot);
        centerJIF(daot);
        daot.setVisible(true);
    }//GEN-LAST:event_TipoCambio1ActionPerformed

    private void TipoCambio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoCambio2ActionPerformed
        // TODO add your handling code here:
        //consultarven();
        InterFrmFormatearDAOTVentas daot=new InterFrmFormatearDAOTVentas();
        dpane2.add(daot);
        centerJIF(daot);
        daot.setVisible(true);
    }//GEN-LAST:event_TipoCambio2ActionPerformed

    private void btPersonal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal1ActionPerformed
        // TODO add your handling code here:
        importar();
    }//GEN-LAST:event_btPersonal1ActionPerformed

    private void btPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonalActionPerformed
        // TODO add your handling code here:
        ventas();
    }//GEN-LAST:event_btPersonalActionPerformed

    private void btPersonal2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal2ActionPerformed
        // TODO add your handling code here:
        FrmExportar export=new FrmExportar();
        export.setVisible(true);
    }//GEN-LAST:event_btPersonal2ActionPerformed

    private void btPersonal3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal3ActionPerformed
        // TODO add your handling code here:
        formatearcom();
    }//GEN-LAST:event_btPersonal3ActionPerformed

    private void btPersonal4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal4ActionPerformed
        // TODO add your handling code here:
        formatearven();
    }//GEN-LAST:event_btPersonal4ActionPerformed

    private void btPersonal5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal5ActionPerformed
        // TODO add your handling code here:
        consultarcom();
    }//GEN-LAST:event_btPersonal5ActionPerformed

    private void btPersonal6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPersonal6ActionPerformed
        // TODO add your handling code here:
        consultarven();
    }//GEN-LAST:event_btPersonal6ActionPerformed

    private void menusalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menusalirMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_menusalirMouseClicked

    private void TipoCambio3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoCambio3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoCambio3ActionPerformed

    private void Consulta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Consulta1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Consulta1ActionPerformed

    private void ProgramadePagos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProgramadePagos1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ProgramadePagos1ActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Consulta;
    private javax.swing.JMenuItem Consulta1;
    private javax.swing.JMenu Emision;
    private javax.swing.JMenu Programacion;
    private javax.swing.JMenuItem ProgramadePagos;
    private javax.swing.JMenuItem ProgramadePagos1;
    private javax.swing.JMenuItem Proveedores;
    private javax.swing.JMenuItem Salir;
    private javax.swing.JMenuItem TipoCambio;
    private javax.swing.JMenuItem TipoCambio1;
    private javax.swing.JMenuItem TipoCambio2;
    private javax.swing.JMenuItem TipoCambio3;
    private javax.swing.JButton btPersonal;
    private javax.swing.JButton btPersonal1;
    private javax.swing.JButton btPersonal2;
    private javax.swing.JButton btPersonal3;
    private javax.swing.JButton btPersonal4;
    private javax.swing.JButton btPersonal5;
    private javax.swing.JButton btPersonal6;
    private javax.swing.JButton btSalir;
    private javax.swing.JMenuItem btncomprobantes;
    private javax.swing.JMenu btnmatenimiento;
    private javax.swing.JMenu btnregistros;
    public static javax.swing.JDesktopPane dpane2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JLabel lbllogo;
    private javax.swing.JMenu menusalir;
    // End of variables declaration//GEN-END:variables
}
