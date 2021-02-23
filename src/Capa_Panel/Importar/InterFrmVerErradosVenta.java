/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Importar;

import Capa_Entidades.EDataCompra;
import Capa_Entidades.EDataVenta;
import Capa_Logica.RenderDerecha;
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
public class InterFrmVerErradosVenta extends javax.swing.JInternalFrame {
    String titulos[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento", "Tipo de Comprobante de Pago o Doc",
        "Nro Serie Maquina Registradora", "Nro Comprobante Pago", "Tipo Doc. Identidad Cliente",
        "Nro. Doc. Identidad Cliente", "Razon Social Cliente", "Valor Facturado Export.",
        "Base Imponible de la Op. Gravada", "Importe Total Operacion Exonerada", "Importe Total Operacion Inafecta",
        "ISC", "IGV y/o IPM", "Otros Tributos", "Importe Total", "Tipo de Cambio",
        "Comprobante que se Modifica Fecha", "Comprobante o Doc. que se Modifica Tipo",
        "Comprobante o Doc. que se Modifica Serie", "Nro de Comprobante de Pago o Doc."};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmVerErradosCompra
     */
    public InterFrmVerErradosVenta() {
        initComponents();
        this.setTitle("Registros Errados de Venta e Ingresos");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblsterrados.setModel(modTbdatos);
        tblsterrados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblsterrados.getTableHeader().setReorderingAllowed(false);
         tblsterrados.getColumnModel().getColumn(9).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(10).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(11).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(17).setCellRenderer(new RenderDerecha());
    }

    
     public void llenartabla(ArrayList<EDataVenta> lstventa){
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos.setRowCount(0);
        if(lstventa.size()>0){
            for (int i = 0; i < lstventa.size(); i++) {
                Object obj[]=new Object[22];
                obj[0]=lstventa.get(i).getCorrelativo();
                obj[1]=lstventa.get(i).getFechaEmision();
                obj[2]=lstventa.get(i).getFechaVencimiento();
                obj[3]=lstventa.get(i).getTipoCompPago();
                obj[4]=lstventa.get(i).getNroSerieMaqReg();
                obj[5]=lstventa.get(i).getNroComproPago();
                obj[6]=lstventa.get(i).getTipoDocIdenClientt();
                obj[7] = lstventa.get(i).getNroDocIdentCliente();
                obj[8] = lstventa.get(i).getRazSocialCliente();
                obj[9] = formato.format(Double.parseDouble(lstventa.get(i).getValorFactExport().replaceAll(",", ".")));
                obj[10] = formato.format(Double.parseDouble(lstventa.get(i).getBaseImpOpeGravada().replaceAll(",", ".")));
                obj[11] = formato.format(Double.parseDouble(lstventa.get(i).getImportTotalOpeExon().replaceAll(",", ".")));
                if(lstventa.get(i).getImportTotalOpeInaf().trim().length()>0){
                    obj[12] = formato.format(Double.parseDouble(lstventa.get(i).getImportTotalOpeInaf().replaceAll(",", ".")));
                } else {
                    obj[12] = "0.00";
                }
                obj[13] = formato.format(Double.parseDouble(lstventa.get(i).getISC().replaceAll(",", ".")));
                obj[14] = formato.format(Double.parseDouble(lstventa.get(i).getIGVyIPM().replaceAll(",", ".")));
                obj[15] = formato.format(Double.parseDouble(lstventa.get(i).getOtrosTributos().replaceAll(",", ".")));
                obj[16] = formato.format(Double.parseDouble(lstventa.get(i).getImporteTotal().replaceAll(",", ".")));
                obj[17] = lstventa.get(i).getTipoCambio().replaceAll(",", ".");
                obj[18] = lstventa.get(i).getTipoDocMod();
                obj[19] = lstventa.get(i).getFechaDocMod();
                obj[20] = lstventa.get(i).getSerieDocMod();
                obj[21] = lstventa.get(i).getNroDocMod();
                modTbdatos.addRow(obj);
            }
        }
        tblsterrados.setModel(modTbdatos);
        lblnroregistro.setText(modTbdatos.getRowCount()+ " Registros");
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblsterrados = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistro = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTROS ERRADOS DE VENTAS E INGRESOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 900, -1));

        tblsterrados.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblsterrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblsterrados);

        lblnroregistro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistro.setText("Nro Registros");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblnroregistro)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnroregistro)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnroregistro;
    private javax.swing.JTable tblsterrados;
    // End of variables declaration//GEN-END:variables
}
