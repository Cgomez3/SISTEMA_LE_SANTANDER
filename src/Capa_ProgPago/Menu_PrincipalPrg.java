/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Presentacion.Menu_Principal;
import Capa_Conexion.ConexionBD;
import Capa_Logica.SesionUsuario;
import Capa_Logica.Reloj;
import Capa_Presentacion.FrmLogin;
import Capa_Presentacion.JDAcercaDe;
import Capa_Presentacion.MenuLibrosElectronico;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;

/**
 *
 * @author User
 */
public class Menu_PrincipalPrg extends javax.swing.JFrame {

    /**
     * Creates new form FrmPrincipal
     */
    public Menu_PrincipalPrg() {
        initComponents();
        this.setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/img/Santander.png")).getImage());
        this.setTitle("SANTANDER CONSUMO PERÚ S.A.C.");
//        Reloj hilo = new Reloj(txthora);
//        hilo.start();
//         txtfecha.setText(fecha());
        txtfecha.setText("Fecha de Compilación: 22/02/2021");
        txtConexion.setText("Sin Conexión");
        ConexionBD con = new ConexionBD("");
        con.leer_ini();
        verconexion();
    }

    private void verconexion() {
        Object obj = SesionUsuario.bddatos.getIp();

        if (obj == null) {
            txtConexion.setText("SIN CONEXIÓN");
        } else {
            txtConexion.setText(SesionUsuario.bddatos.getIp() + " / " + SesionUsuario.bddatos.getNombd());
        }
    }

    public String fecha() {
        GregorianCalendar fecha = new GregorianCalendar();
        int dia = fecha.get(fecha.DAY_OF_MONTH);
        int dia2 = fecha.get(fecha.DAY_OF_WEEK);
        int mes = fecha.get(fecha.MONTH);
        int año = fecha.get(fecha.YEAR);
        String mifecha = nombredia2(dia2) + " " + String.valueOf(dia) + " de " + nombremes(mes) + " de " + String.valueOf(año);
        return mifecha;
    }

    public String nombredia2(int dia) {
        switch (dia) {
            case 1:
                return "Domingo";
            case 2:
                return "Lunes";
            case 3:
                return "Martes";
            case 4:
                return "Miércoles";
            case 5:
                return "Jueves";
            case 6:
                return "Viernes";
            case 7:
                return "Sábado";
        }
        return null;
    }

    public String nombremes(int mes) {
        switch (mes) {
            case 0:
                return "Enero";
            case 1:
                return "Febrero";
            case 2:
                return "Marzo";
            case 3:
                return "Abril";
            case 4:
                return "Mayo";
            case 5:
                return "Junio";
            case 6:
                return "Julio";
            case 7:
                return "Agosto";
            case 8:
                return "Setiembre";
            case 9:
                return "Octubre";
            case 10:
                return "Noviembre";
            case 11:
                return "Diciembre";
        }
        return "Error";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnlibros = new javax.swing.JButton();
        btnprogramapagos = new javax.swing.JButton();
        btnseguridad = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnacercade = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtfecha = new javax.swing.JLabel();
        txtConexion = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(620, 350));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnlibros.setBackground(new java.awt.Color(153, 153, 153));
        btnlibros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnlibros.setText("Libros Electrónicos");
        btnlibros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnlibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlibrosActionPerformed(evt);
            }
        });
        getContentPane().add(btnlibros, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 110, 140, 31));

        btnprogramapagos.setBackground(new java.awt.Color(153, 153, 153));
        btnprogramapagos.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnprogramapagos.setText("Program. de Pagos");
        btnprogramapagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnprogramapagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnprogramapagosActionPerformed(evt);
            }
        });
        getContentPane().add(btnprogramapagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 140, 31));

        btnseguridad.setBackground(new java.awt.Color(153, 153, 153));
        btnseguridad.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnseguridad.setText("Seguridad");
        btnseguridad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnseguridad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnseguridadActionPerformed(evt);
            }
        });
        getContentPane().add(btnseguridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 140, 31));

        btnsalir.setBackground(new java.awt.Color(153, 153, 153));
        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.setToolTipText("");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 230, 137, 31));

        btnacercade.setBackground(new java.awt.Color(153, 153, 153));
        btnacercade.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnacercade.setText("Acerca de");
        btnacercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnacercadeActionPerformed(evt);
            }
        });
        getContentPane().add(btnacercade, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 260, 137, 31));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 54, 200, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("SISTEMA SANTANDER");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(427, 20, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/SANTANDER_LE.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 620, 350));

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtfecha.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        txtfecha.setForeground(new java.awt.Color(255, 255, 255));
        txtfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 5, 180, 20));

        txtConexion.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        txtConexion.setForeground(new java.awt.Color(255, 255, 255));
        txtConexion.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel1.add(txtConexion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 300, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 620, 30));

        jMenuBar1.setBorder(null);

        jMenu1.setText("SISTEMA SANTANDER 1.0 - GERENCIA DE CONTABILIDAD");
        jMenu1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login(String modulo) {
        FrmLogin logeo = new FrmLogin();
        logeo.modulo = modulo;
        logeo.setVisible(true);
    }

    private void btnseguridadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnseguridadActionPerformed
        // TODO add your handling code here:
        login("SEGURIDAD");
    }//GEN-LAST:event_btnseguridadActionPerformed

    private void btnprogramapagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnprogramapagosActionPerformed
        login("PAGOS");
    }//GEN-LAST:event_btnprogramapagosActionPerformed

    private void btnlibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlibrosActionPerformed
        login("LIBROS");

    }//GEN-LAST:event_btnlibrosActionPerformed

    private void btnacercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnacercadeActionPerformed
        // TODO add your handling code here:
        JDAcercaDe jda = new JDAcercaDe(this, true);
        jda.setVisible(true);
    }//GEN-LAST:event_btnacercadeActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        System.exit(DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnsalirActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_PrincipalPrg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu_PrincipalPrg().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnacercade;
    public javax.swing.JButton btnlibros;
    public javax.swing.JButton btnprogramapagos;
    private javax.swing.JButton btnsalir;
    public javax.swing.JButton btnseguridad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtConexion;
    private javax.swing.JLabel txtfecha;
    // End of variables declaration//GEN-END:variables
}
