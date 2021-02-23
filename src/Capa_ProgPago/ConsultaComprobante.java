/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Entidades.EComprobante;
import Capa_Logica.LComprobante;
import Capa_Logica.SesionUsuario;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class ConsultaComprobante extends javax.swing.JInternalFrame {
    
    String titulos[] = {"TipoComp", "NumComp", "FecComp", "Estado", "RazonSocial",
        "DocProveedor", "Glosa", "Sujeto A", "ConcepDetra", "FormaPago", "DestinoPago", "Moneda", "TC",
        "MBaseSOL", "MOtrosSOL", "MIGVSOL", "MTotalSOL", "MRetDetSOL", "MPagarSOL",
        "MBaseDOL", "MOtrosDOL", "MIGVDOL", "MTotalDOL", "MRetDetDOL",
        "MPagarDOL", "PorcRetDet", "PorcIGV", "CompRef", "RazonSocialRef", "FechaRef", "UsuCrea", "FecCrea"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    ArrayList<EComprobante> liscomprobante = new ArrayList<>();
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    String comprob;
    int opc = 0;
    int fila = 1;
    /**
     * Creates new form ConsultaComprobante
     */
    public ConsultaComprobante() {
        initComponents();
        this.setLocation(230, 50);
        this.setTitle("Seleccionar Comprobante.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblistacomprobante.setModel(modTbdatos);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblistacomprobante.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblistacomprobante.getTableHeader().setReorderingAllowed(false);
        tblistacomprobante.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblistacomprobante.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(3).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(4).setPreferredWidth(125);
        tblistacomprobante.getColumnModel().getColumn(5).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(6).setPreferredWidth(125);
        tblistacomprobante.getColumnModel().getColumn(7).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(8).setPreferredWidth(125);
        tblistacomprobante.getColumnModel().getColumn(9).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(10).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(11).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(12).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(13).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(14).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(15).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(16).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(17).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(18).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(19).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(20).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(21).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(22).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(23).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(24).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(25).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(26).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(27).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(28).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(29).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(30).setPreferredWidth(75);
        tblistacomprobante.getColumnModel().getColumn(31).setPreferredWidth(75);
        
        
        listacomprobante();
    }

    
    private void listacomprobante(){
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        if(opc==0){
        LComprobante lpro=new LComprobante();
            liscomprobante=lpro.listaComprobanteNCND();
        }
                  modTbdatos.setRowCount(0);
                if(liscomprobante.size()>0){
             for(int i=0;i<liscomprobante.size();i++){
                 Object nuevafila[] = new Object[33];
                 nuevafila[0] = liscomprobante.get(i).getTipoComprobante();
                 nuevafila[1] = liscomprobante.get(i).getNumComprobante();
                 nuevafila[2] = liscomprobante.get(i).getFechaComprobante();
                 nuevafila[3] = liscomprobante.get(i).getEstado(); 
                 nuevafila[4] = liscomprobante.get(i).getRazonSocial();
                 nuevafila[5] = liscomprobante.get(i).getDocProveedor();
                 nuevafila[6] = liscomprobante.get(i).getGlosaComprante();
                 nuevafila[7] = liscomprobante.get(i).getComprobanteSujeto();
                 nuevafila[8] = liscomprobante.get(i).getTipoDetra();
                 nuevafila[9] = liscomprobante.get(i).getFormaPago();
                 nuevafila[10] = liscomprobante.get(i).getDestinoPago();
                 nuevafila[11] = liscomprobante.get(i).getNomMoneda();
                 nuevafila[12] = liscomprobante.get(i).getTCComprobante();
                 nuevafila[13] = formato.format(liscomprobante.get(i).getMontoBase());
                 nuevafila[14] = formato.format(liscomprobante.get(i).getMontoOtros());
                 nuevafila[15] = formato.format(liscomprobante.get(i).getMontoIGV());
                 nuevafila[16] = formato.format(liscomprobante.get(i).getMontoTotal());
                 nuevafila[17] = formato.format(liscomprobante.get(i).getMontoRetDet());
                 nuevafila[18] = formato.format(liscomprobante.get(i).getMontoPagar());
                 nuevafila[19] = formato.format(liscomprobante.get(i).getMontoBaseDOL());  
                 nuevafila[20] = formato.format(liscomprobante.get(i).getMontoOtrosDOL());
                 nuevafila[21] = formato.format(liscomprobante.get(i).getMontoIGVDOL());
                 nuevafila[22] = formato.format(liscomprobante.get(i).getMontoTotalDOL());
                 nuevafila[23] = formato.format(liscomprobante.get(i).getMontoRetDetDOL());
                 nuevafila[24] = formato.format(liscomprobante.get(i).getMontoPagarDOL());
                 nuevafila[25] = formato.format(liscomprobante.get(i).getPorcentaje());
                 nuevafila[26] = formato.format(liscomprobante.get(i).getPorcIGV());
                 nuevafila[27] = liscomprobante.get(i).getComprobanteRef();
                 nuevafila[28] = liscomprobante.get(i).getRazonSocialRef();
                 nuevafila[29] = liscomprobante.get(i).getFechaRef();
                 nuevafila[30] = liscomprobante.get(i).getUsuCrea(); 
                 nuevafila[31] = liscomprobante.get(i).getFechaCrea(); 
                 
                 modTbdatos.addRow(nuevafila);
             }
        }
        tblistacomprobante.setModel(modTbdatos);
        lblnroregistros.setText(tblistacomprobante.getRowCount() + " Comprobante(es).");
    }
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtcomprobante = new javax.swing.JTextField();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacomprobante = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistros = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 15));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SELECCIONAR COMPROBANTE");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 710, 20));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setText("Proveedor");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        txtcomprobante.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtcomprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcomprobanteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcomprobanteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcomprobanteKeyTyped(evt);
            }
        });
        jPanel1.add(txtcomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 530, -1));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 70, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 690, 63));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });
        jScrollPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jScrollPane1KeyReleased(evt);
            }
        });

        tblistacomprobante.setAutoCreateRowSorter(true);
        tblistacomprobante.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistacomprobante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblistacomprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblistacomprobanteMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblistacomprobanteMouseReleased(evt);
            }
        });
        tblistacomprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblistacomprobanteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblistacomprobante);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 96, 690, 320));

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setText("0 Comprobantes");
        lblnroregistros.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(lblnroregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 210, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
      dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tblistacomprobanteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacomprobanteMouseReleased
        int fila2=tblistacomprobante.getSelectedRow();
        if(fila==fila2){
     
//           LComprobante limp = new LComprobante();
            String com=(tblistacomprobante.getValueAt(fila2, 1).toString());
            String fec=(tblistacomprobante.getValueAt(fila2, 2).toString());
            String raz=(tblistacomprobante.getValueAt(fila2, 4).toString());
            String razonsocial=(tblistacomprobante.getValueAt(fila2, 4).toString());
            String doc=(tblistacomprobante.getValueAt(fila2, 5).toString());
//           comprob = limp.SeleccionarComprobante(com);
            
            SesionUsuario.txtcomprobante.setText(com);
            SesionUsuario.txtfecharef.setText(fec);
            SesionUsuario.txtrazonref.setText(raz);
            SesionUsuario.txtrazonsocial.setText(razonsocial);
            SesionUsuario.txtrucpro.setText(doc);
            
//            
            this.dispose();
        }else{
            fila=fila2;
        }
    }//GEN-LAST:event_tblistacomprobanteMouseReleased

    private void tblistacomprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacomprobanteMouseClicked

    }//GEN-LAST:event_tblistacomprobanteMouseClicked

    private void tblistacomprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblistacomprobanteKeyReleased
        //        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            //            int fila = tblstproveedores.getSelectedRow();
            //            seleccionarfila(fila);
            //        }
        //        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            //            int fila = tblstproveedores.getSelectedRow();
            //            seleccionarfila(fila);
            //        }
    }//GEN-LAST:event_tblistacomprobanteKeyReleased

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
//        int fila = tblistacomprobante.getSelectedRow();
//        if(fila>=0){
//
//            //            seleccionarfila(fila);
//
//        }
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void jScrollPane1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jScrollPane1KeyReleased
//       int fila = tblistacomprobante.getSelectedRow();
//        if(fila>=0){
//        if (evt.getKeyCode() == KeyEvent.VK_UP) {
//           
//            //            seleccionarfila(fila);
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            
//            //            seleccionarfila(fila);
//        }
//        }
    }//GEN-LAST:event_jScrollPane1KeyReleased

    private void txtcomprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomprobanteKeyReleased
        opc = 1;
        if (txtcomprobante.getText().trim().length() > 0) {
            LComprobante lo = new LComprobante();
            liscomprobante = lo.SeleccionarComprobante(txtcomprobante.getText());
            System.out.println(txtcomprobante.getText());
            System.out.println(liscomprobante.size());
            if (liscomprobante.size() > 0) {
                listacomprobante();
            } else {
                listacomprobante();
            }
        }
 
    }//GEN-LAST:event_txtcomprobanteKeyReleased

    private void txtcomprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomprobanteKeyPressed
        opc = 1;
        if (txtcomprobante.getText().trim().length() > 0) {
            LComprobante lo = new LComprobante();
            liscomprobante = lo.SeleccionarComprobante(txtcomprobante.getText());
            System.out.println(txtcomprobante.getText());
            System.out.println(liscomprobante.size());
            if (liscomprobante.size() > 0) {
                listacomprobante();
            } else {
                listacomprobante();
            }
        }
    }//GEN-LAST:event_txtcomprobanteKeyPressed

    private void txtcomprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcomprobanteKeyTyped
        opc = 1;
        if (txtcomprobante.getText().trim().length() > 0) {
            LComprobante lo = new LComprobante();
            liscomprobante = lo.SeleccionarComprobante(txtcomprobante.getText());
            System.out.println(txtcomprobante.getText());
            System.out.println(liscomprobante.size());
            if (liscomprobante.size() > 0) {
                listacomprobante();
            } else {
                listacomprobante();
            }
        } else {
            LComprobante lo = new LComprobante();
            liscomprobante = lo.SeleccionarComprobante("");
            System.out.println(txtcomprobante.getText());
            System.out.println(liscomprobante.size());
            if (liscomprobante.size() > 0) {
                listacomprobante();
            } else {
                listacomprobante();
            }
        }
    }//GEN-LAST:event_txtcomprobanteKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JTable tblistacomprobante;
    private javax.swing.JTextField txtcomprobante;
    // End of variables declaration//GEN-END:variables
}
