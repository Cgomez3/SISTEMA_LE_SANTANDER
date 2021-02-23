/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Retencion;

import Capa_Entidades.EDataFormatRetencion;
import Capa_Entidades.EDataRetencion;
import Capa_Entidades.EProveedor;
import Capa_Logica.LImportar;
import Capa_Logica.LProveedor;
import Capa_Panel.Retencion.Reportes.CReporteRetencion;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmFormatearEmitir extends javax.swing.JInternalFrame {
    ArrayList<EDataRetencion> lstretencion = new ArrayList<>();
    ArrayList<EDataFormatRetencion> lstformat = new ArrayList<>();
    ArrayList<EProveedor> lstprov = new ArrayList<>();
    String titulos[] = {"Codigo", "Correlativo", "N° Comprb. Ret.", "Fec.Ret.", "Razon Social", "RUC", "FechaComprob", "Nro.Doc.",
        "Importe", "% Reten.", "Retencion", "SUNAT", "NETO"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    String titulos2[] = {"1-RucProv", "2-RazSocial", "3-ApePat", "4-ApeMat", "5-Nombres", "6-SerieComprobReten",
        "7-NroComprobReten", "8-FecEmiComprobReten", "9-MontoComprobReten", "10-TipoComprobPago", "11-SerieComprobPago",
        "12-NroComprobPago", "13-FecEmiComprobPago", "14-TotalComprobPago"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmFormatearEmitir
     */
    public InterFrmFormatearEmitir() {
        initComponents();
        this.setTitle("Consulta de Comprobantes de Retención.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblstdetreten.setModel(modTbdatos);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        Date hoy =new Date();
        dcfechaini.setDate(hoy);
        dcfechafin.setDate(hoy);
        llenarcomboprov();
    }

    private void Buscar(){
        LImportar lim=new LImportar();
        if (cboproveedor.getSelectedIndex() == 1) {
            lstretencion = lim.ConsultaRetencionDetalle("TODOS", fechaformato(dcfechaini.getDate()), fechaformato(dcfechafin.getDate()));
        } else {
            lstretencion = lim.ConsultaRetencionDetalle(lstprov.get(cboproveedor.getSelectedIndex() - 2).getDocProveedor(), fechaformato(dcfechaini.getDate()), fechaformato(dcfechafin.getDate()));
        }
        if(lstretencion.size()>0){
            llenartabla();    
        }else{
            llenartabla();
            JOptionPane.showMessageDialog(this, "No se encontraron datos.");
        }
        
    }
    
      String fechaformato(Date fecha) {
        String fec = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        return fec;
    }
    
    private void llenarcomboprov(){
        LProveedor lp=new LProveedor();
        lstprov=lp.Llenarcomboretencion();
        cboproveedor.removeAllItems();
        cboproveedor.addItem("<Seleccione>");
        cboproveedor.addItem("TODOS");
        if (lstprov.size() > 0) {
            for (int i = 0; i < lstprov.size(); i++) {
                cboproveedor.addItem(lstprov.get(i).getRazonSocial());
            }
        }
    }
    
    private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
//        "Codigo","Correlativo", "Razon Social", "RUC", "Fecha", "Nro.Doc.",
//        "Importe", "% Reten.", "Retencion", "SUNAT", "NETO","Nro Comprobante Retenc."
        modTbdatos.setRowCount(0);
        if (lstretencion.size() > 0) {
            for (int i = 0; i < lstretencion.size(); i++) {
                Object obj[] = new Object[13];
                obj[0] = lstretencion.get(i).getCodigo();
                obj[1] = lstretencion.get(i).getCorrelativo();
                obj[2] = lstretencion.get(i).getNroComprobanteReten();
                obj[3]= lstretencion.get(i).getFechaRetencion();
                obj[4] = lstretencion.get(i).getRazonSocial();
                obj[5] = lstretencion.get(i).getRUC();
                obj[6] = lstretencion.get(i).getFecha();
                obj[7] = lstretencion.get(i).getDoc();
                obj[8] = formato.format(Double.valueOf(lstretencion.get(i).getImporte()));
                obj[9] = lstretencion.get(i).getPorcentRetenc();
                obj[10] = formato.format(Double.valueOf(lstretencion.get(i).getRetencion()));
                obj[11] = formato.format(Double.valueOf(lstretencion.get(i).getSUNAT()));
                obj[12] = formato.format(Double.valueOf(lstretencion.get(i).getNeto()));
                modTbdatos.addRow(obj);    
            }
        }
        tblstdetreten.setModel(modTbdatos);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblstdetreten = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        dcfechaini = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        dcfechafin = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        cboproveedor = new javax.swing.JComboBox();
        btnbuscar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tblstdetreten.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstdetreten.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblstdetreten);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imprimir.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 0, 15));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE COMPROBANTES DE RETENCIÓN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setText("Fecha Inicial");

        dcfechaini.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setText("Fecha Final");

        dcfechafin.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel7.setText("Proveedor");

        cboproveedor.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcfechaini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcfechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(cboproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)))
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcfechaini, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcfechafin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnbuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int fila = tblstdetreten.getSelectedRow();
        if (fila >= 0) {
            try {
                CReporteRetencion crr = new CReporteRetencion();
                crr.llamarreporteReten(lstretencion.get(fila));
            } catch (JRException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(InterFrmFormatearEmitir.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione Fila para generar reporte.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        // TODO add your handling code here:
        if(cboproveedor.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(this, "Seleccione Proveedor.");
            cboproveedor.requestFocus();
        }else{
            Buscar();
        }
    }//GEN-LAST:event_btnbuscarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox cboproveedor;
    private com.toedter.calendar.JDateChooser dcfechafin;
    private com.toedter.calendar.JDateChooser dcfechaini;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblstdetreten;
    // End of variables declaration//GEN-END:variables
}
