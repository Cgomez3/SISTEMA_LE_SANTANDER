/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Formatear;

import Capa_Entidades.EDataFormatCompras;
import Capa_Logica.RenderDerecha;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmVerErradosFormatCompra extends javax.swing.JInternalFrame {
   String titulos2[] = {"Error","1-Periodo", "2-CUO", "3-MovCUO", "4-FechaEmisión", "5-FechaVencimiento", "6-TipoComprob", "7-SerieComprob",
        "8-AñoDUA", "9-NroComprob", "10-MontoConsolOPsinIGV", "11-TipoDocIden", "12-NroDoc", "13-NomRazonSocial", "14-BaseImpGrav", "15-MontoIGV",
        "16-BaseImpAdqGrav", "17-MontoIGV", "18-BaseImpAdqNoGrav", "19-MontoIGVNG", "20-ValorAdqNG", "21-MontoISC", "22-OtrosMontos", "23-ImporteTotalAdq",
        "24-TipoCambio", "25-FechaEmiComprobRef", "26-TipoComprobRef", "27-SerieComprobRef", "28-CodDUA", "29-NroComprobRef",
        "30-NroComprobNoDomicil", "31-FechaConstDetracc", "32-NroConstDetracc", "33-MarcaComprobSujReten", "34-EstadoOperación"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    ArrayList<EDataFormatCompras> milstformat=new ArrayList<>();
    /**
     * Creates new form InterFrmVerErradosCompra
     */
    public InterFrmVerErradosFormatCompra() {
        initComponents();
        this.setTitle("Registros Formateados Errados de Compras");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblsterrados.setModel(modTbdatos2);
        tblsterrados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblsterrados.getTableHeader().setReorderingAllowed(false);
        tblsterrados.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(17).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(18).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(19).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(20).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(21).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(22).setCellRenderer(new RenderDerecha());
        tblsterrados.getColumnModel().getColumn(23).setCellRenderer(new RenderDerecha());
    }

    
     public void llenartablaformato(ArrayList<EDataFormatCompras> lstformat) {
         milstformat=lstformat;
        modTbdatos2.setRowCount(0);
        if (lstformat.size() > 0) {
            for (int i = 0; i < lstformat.size(); i++) {
                Object obj[] = new Object[35];
                obj[0]=lstformat.get(i).getCampoOtros();
                obj[1] = lstformat.get(i).getCampo1();
                obj[2] = lstformat.get(i).getCampo2();
                obj[3] = lstformat.get(i).getCampo3();
                obj[4] = lstformat.get(i).getCampo4();
                obj[5] = lstformat.get(i).getCampo5();
                obj[6] = lstformat.get(i).getCampo6();
                obj[7] = lstformat.get(i).getCampo7();
                obj[8] = lstformat.get(i).getCampo8();
                obj[9] = lstformat.get(i).getCampo9();
                obj[10] = lstformat.get(i).getCampo10();
                obj[11] = lstformat.get(i).getCampo11();
                obj[12] = lstformat.get(i).getCampo12();
                obj[13] = lstformat.get(i).getCampo13();
                obj[14] = lstformat.get(i).getCampo14();
                obj[15] = lstformat.get(i).getCampo15();
                obj[16] = lstformat.get(i).getCampo16();
                obj[17] = lstformat.get(i).getCampo17();
                obj[18] = lstformat.get(i).getCampo18();
                obj[19] = lstformat.get(i).getCampo19();
                obj[20] = lstformat.get(i).getCampo20();
                obj[21] = lstformat.get(i).getCampo21();
                obj[22] = lstformat.get(i).getCampo22();
                obj[23] = lstformat.get(i).getCampo23();
                obj[24] = lstformat.get(i).getCampo24();
                obj[25] = lstformat.get(i).getCampo25();
                obj[26] = lstformat.get(i).getCampo26();
                obj[27] = lstformat.get(i).getCampo27();
                obj[28] = lstformat.get(i).getCampo28();
                obj[29] = lstformat.get(i).getCampo29();
                obj[30] = lstformat.get(i).getCampo30();
                obj[31] = lstformat.get(i).getCampo31();
                obj[32] = lstformat.get(i).getCampo32();
                obj[33] = lstformat.get(i).getCampo33();
                obj[34] = lstformat.get(i).getCampo34();
                modTbdatos2.addRow(obj);
            }
        }
        tblsterrados.setModel(modTbdatos2);
        lblnroregistro.setText(tblsterrados.getRowCount()+" Registro(s)");
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
        btnexcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTROS FORMATEADOS ERRADOS DE COMPRAS");
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

        btnexcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excelmini.png"))); // NOI18N
        btnexcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexcelActionPerformed(evt);
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
                        .addGap(711, 711, 711)
                        .addComponent(btnexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnexcel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnexcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexcelActionPerformed
        // TODO add your handling code here:
        if(milstformat.size()>0){
            try { 
                javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
                jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String ruta = "";
                if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ruta = jF1.getSelectedFile().getAbsolutePath();
                    WritableWorkbook workbook =Workbook.createWorkbook(new File(ruta+"/DatosErradosFormatCompras.xls"));
                    WritableSheet sheet =workbook.createSheet("Datos Errados", 0);
                    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 11);
                    cellFont.setColour(Colour.BLUE);
                    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
                    WritableCellFormat cellFormat2 = new WritableCellFormat();
                    cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
                    sheet.setColumnView(0, 30);
                    sheet.addCell(new jxl.write.Label(0, 0, "Error",cellFormat));
                    sheet.setColumnView(1, 15);
                    sheet.addCell(new jxl.write.Label(1, 0, "1-Periodo",cellFormat));
                    sheet.setColumnView(2, 15);
                    sheet.addCell(new jxl.write.Label(2, 0, "2-CUO",cellFormat));
                    sheet.setColumnView(3, 15);
                    sheet.addCell(new jxl.write.Label(3, 0, "3-MovCUO",cellFormat));
                    sheet.setColumnView(4, 15);
                    sheet.addCell(new jxl.write.Label(4, 0, "4-FechaEmisión",cellFormat));
                    sheet.setColumnView(5, 15);
                    sheet.addCell(new jxl.write.Label(5, 0, "5-FechaVencimiento",cellFormat));
                    sheet.setColumnView(6, 15);
                    sheet.addCell(new jxl.write.Label(6, 0, "6-TipoComprob",cellFormat));
                    sheet.setColumnView(7, 15);
                    sheet.addCell(new jxl.write.Label(7, 0, "7-SerieComprob",cellFormat));
                    sheet.setColumnView(8, 15);
                    sheet.addCell(new jxl.write.Label(8, 0, "8-AñoDUA",cellFormat));
                    sheet.setColumnView(9, 15);
                    sheet.addCell(new jxl.write.Label(9, 0, "9-NroComprob",cellFormat));
                    sheet.setColumnView(10, 15);
                    sheet.addCell(new jxl.write.Label(10, 0, "10-MontoConsolOPsinIGV",cellFormat));
                    sheet.setColumnView(11, 15);
                    sheet.addCell(new jxl.write.Label(11, 0, "11-TipoDocIden",cellFormat));
                    sheet.setColumnView(12, 15);
                    sheet.addCell(new jxl.write.Label(12, 0, "12-NroDoc",cellFormat));
                    sheet.setColumnView(13, 15);
                    sheet.addCell(new jxl.write.Label(13, 0, "13-NomRazonSocial",cellFormat));
                    sheet.setColumnView(14, 15);
                    sheet.addCell(new jxl.write.Label(14, 0, "14-BaseImpGrav",cellFormat));
                    sheet.setColumnView(15, 15);
                    sheet.addCell(new jxl.write.Label(15, 0, "15-MontoIGV",cellFormat));
                    sheet.setColumnView(16, 15);
                    sheet.addCell(new jxl.write.Label(16, 0, "16-BaseImpAdqGrav",cellFormat));
                    sheet.setColumnView(17, 15);
                    sheet.addCell(new jxl.write.Label(17, 0, "17-MontoIGV",cellFormat));
                    sheet.setColumnView(18, 15);
                    sheet.addCell(new jxl.write.Label(18, 0, "18-BaseImpAdqNoGrav",cellFormat));
                    sheet.setColumnView(19, 15);
                    sheet.addCell(new jxl.write.Label(19, 0, "19-MontoIGVNG",cellFormat));
                    sheet.setColumnView(20, 15);
                    sheet.addCell(new jxl.write.Label(20, 0, "20-ValorAdqNG",cellFormat));
                    sheet.setColumnView(21, 15);
                    sheet.addCell(new jxl.write.Label(21, 0, "21-MontoISC",cellFormat));
                    sheet.setColumnView(22, 15);
                    sheet.addCell(new jxl.write.Label(22, 0, "22-OtrosMontos",cellFormat));
                    sheet.setColumnView(23, 15);
                    sheet.addCell(new jxl.write.Label(23, 0, "23-ImporteTotalAdq",cellFormat));
                    sheet.setColumnView(24, 15);
                    sheet.addCell(new jxl.write.Label(24, 0, "24-TipoCambio",cellFormat));
                    sheet.setColumnView(25, 15);
                    sheet.addCell(new jxl.write.Label(25, 0, "25-FechaEmiComprobRef",cellFormat));
                    sheet.setColumnView(26, 15);
                    sheet.addCell(new jxl.write.Label(26, 0, "26-TipoComprobRef",cellFormat));
                    sheet.setColumnView(27, 15);
                    sheet.addCell(new jxl.write.Label(27, 0, "27-SerieComprobRef",cellFormat));
                    sheet.setColumnView(28, 15);
                    sheet.addCell(new jxl.write.Label(28, 0, "28-CodDUA",cellFormat));
                    sheet.setColumnView(29, 15);
                    sheet.addCell(new jxl.write.Label(29, 0, "29-NroComprobRef",cellFormat));
                    sheet.setColumnView(30, 15);
                    sheet.addCell(new jxl.write.Label(30, 0, "30-NroComprobNoDomicil",cellFormat));
                    sheet.setColumnView(31, 15);
                    sheet.addCell(new jxl.write.Label(31, 0, "31-FechaConstDetracc",cellFormat));
                    sheet.setColumnView(32, 15);
                    sheet.addCell(new jxl.write.Label(32, 0, "32-NroConstDetracc",cellFormat));
                    sheet.setColumnView(33, 15);
                    sheet.addCell(new jxl.write.Label(33, 0, "33-MarcaComprobSujReten",cellFormat));
                    sheet.setColumnView(34, 15);
                    sheet.addCell(new jxl.write.Label(34, 0, "34-EstadoOperación",cellFormat));
                    for (int i = 0; i < milstformat.size(); i++) {
                    sheet.addCell(new jxl.write.Label(0, i+1, milstformat.get(i).getCampoOtros(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(1, i+1, milstformat.get(i).getCampo1(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(2, i+1, milstformat.get(i).getCampo2(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(3, i+1, milstformat.get(i).getCampo3(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(4, i+1, milstformat.get(i).getCampo4(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(5, i+1, milstformat.get(i).getCampo5(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(6, i+1, milstformat.get(i).getCampo6(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(7, i+1, milstformat.get(i).getCampo7(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(8, i+1, milstformat.get(i).getCampo8(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(9, i+1, milstformat.get(i).getCampo9(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(10, i+1, milstformat.get(i).getCampo10(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(11, i+1, milstformat.get(i).getCampo11(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(12, i+1, milstformat.get(i).getCampo12(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(13, i+1, milstformat.get(i).getCampo13(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(14, i+1, milstformat.get(i).getCampo14(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(15, i+1, milstformat.get(i).getCampo15(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(16, i+1, milstformat.get(i).getCampo16(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(17, i+1, milstformat.get(i).getCampo17(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(18, i+1, milstformat.get(i).getCampo18(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(19, i+1, milstformat.get(i).getCampo19(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(20, i+1, milstformat.get(i).getCampo20(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(21, i+1, milstformat.get(i).getCampo21(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(22, i+1, milstformat.get(i).getCampo22(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(23, i+1, milstformat.get(i).getCampo23(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(24, i+1, milstformat.get(i).getCampo24(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(25, i+1, milstformat.get(i).getCampo25(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(26, i+1, milstformat.get(i).getCampo26(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(27, i+1, milstformat.get(i).getCampo27(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(28, i+1, milstformat.get(i).getCampo28(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(29, i+1, milstformat.get(i).getCampo29(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(30, i+1, milstformat.get(i).getCampo30(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(31, i+1, milstformat.get(i).getCampo31(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(32, i+1, milstformat.get(i).getCampo32(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(33, i+1, milstformat.get(i).getCampo33(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(34, i+1, milstformat.get(i).getCampo34(),cellFormat2));
                    }
                    workbook.write();
                    workbook.close(); 
                }
            } catch (IOException | WriteException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_btnexcelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexcel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnroregistro;
    private javax.swing.JTable tblsterrados;
    // End of variables declaration//GEN-END:variables
}
