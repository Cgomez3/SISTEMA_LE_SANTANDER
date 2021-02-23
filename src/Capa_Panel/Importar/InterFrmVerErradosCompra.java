/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Importar;

import Capa_Entidades.EDataCompra;
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
public class InterFrmVerErradosCompra extends javax.swing.JInternalFrame {
 String titulos[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento","Tipo de Comprobante de Pago o Doc",
        "Serie Codigo de Dependencia Aduanera","Año de Emisión de DUA o DSI","Documentos Emitidos por Sunat para",
        "Tipo Doc. Identidad Prov.","Nro. Doc. Identidad Prov.", "Razon Social Proveedor", "Adquisicion Gravadas Base Imponible", 
        "Adquisicion Gravadas IGV", "Gravadas y No Gravadas Base Imponible", "Gravadas y No Gravadas IGV", "No Gravadas Base Imponible",
        "No Gravadas IGV","Adquisiciones No Gravadas","ISC","Otros Tributos y Cargos","Importe Total","Nro Comprobante No Domiciliado",
        "Nro Constancia de Detracción","Fecha de Emisión de Constancia de Detracción","Tipo de Cambio",
        "Comprobante que se Modifica Fecha","Comprobante que se Modifica Tipo","Comprobante que se Modifica Serie",
        "Nro de Comprobante de Pago o Doc"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmVerErradosCompra
     */
    public InterFrmVerErradosCompra() {
        initComponents();
        this.setTitle("Registros Errados de Compras");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblsterrados.setModel(modTbdatos);
        tblsterrados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblsterrados.getTableHeader().setReorderingAllowed(false);
        tblsterrados.getColumnModel().getColumn(10).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(11).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(18).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(19).setCellRenderer(new RenderDerecha());
    }

    
     public void llenartabla(ArrayList<EDataCompra> lstdatacompra){
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos.setRowCount(0);
        if(lstdatacompra.size()>0){
            for (int i = 0; i < lstdatacompra.size(); i++) {
                Object obj[]=new Object[28];
                obj[0]=lstdatacompra.get(i).getFila();
                obj[1]=lstdatacompra.get(i).getFechaEmision();
                obj[2]=lstdatacompra.get(i).getFechaVencimiento();
                obj[3]=lstdatacompra.get(i).getTipoComprobantePago();
                obj[4]=lstdatacompra.get(i).getSerieCodDepenAduanera();
                obj[5]=lstdatacompra.get(i).getAñoEmisionDua();
                obj[6]=lstdatacompra.get(i).getDocumentosEmitidos();
                obj[7]=lstdatacompra.get(i).getTipoDocuIdenProv();
                obj[8]=lstdatacompra.get(i).getNroDocIdentProv();
                obj[9]=lstdatacompra.get(i).getRazonSocialProv();
                obj[10]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGOBaseDisponible().replace(",", ".")));
                obj[11]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGOIGV().replace(",", ".")));
                obj[12]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGYGBaseDisponible().replace(",", ".")));
                obj[13]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGYGIGV().replace(",", ".")));
                obj[14]=formato.format(Double.parseDouble(lstdatacompra.get(i).getNGBaseDisponible().replace(",", ".")));
                obj[15]=formato.format(Double.parseDouble(lstdatacompra.get(i).getNGIGV().replace(",", ".")));
                obj[16]=formato.format(Double.parseDouble(lstdatacompra.get(i).getAdquisionesNG().replace(",", ".")));
                obj[17]=lstdatacompra.get(i).getISC();
                obj[18]=formato.format(Double.parseDouble(lstdatacompra.get(i).getOtrosTribyCarg().replace(",", ".")));
                obj[19]=formato.format(Double.parseDouble(lstdatacompra.get(i).getImporteTotal().replace(",", ".")));
                obj[20]=lstdatacompra.get(i).getNComprNoDomic();
                obj[21]=lstdatacompra.get(i).getNroConstanciaDetraccion();
                obj[22]=lstdatacompra.get(i).getFechaEmisionConsDetracc();
                obj[23]=lstdatacompra.get(i).getTipoDeCambio();
                obj[24]=lstdatacompra.get(i).getCMFecha();
                obj[25]=lstdatacompra.get(i).getCMTipo();
                obj[26]=lstdatacompra.get(i).getCMSerie();
                obj[27]=lstdatacompra.get(i).getCMNCPagoDoc();
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
        jLabel1.setText("REGISTROS ERRADOS DE COMPRAS");
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
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 920, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblnroregistro)
                        .addGap(787, 787, 787)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
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
