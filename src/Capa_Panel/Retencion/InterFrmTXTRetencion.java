/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Retencion;

import Capa_Entidades.EExportarRetencion;
import Capa_Logica.LProgPagosCabecera;
import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmTXTRetencion extends javax.swing.JInternalFrame {
     String titulos[] = {"Ruc","RazonSocial","Ape.Paterno","Ape.Materno","Nombres","Serie Reten","Nro Reten","Fecha Reten",
     "Suma Monto Bruto", "Tipo Comprob","Serie Comprob","Nro Comprob","FechaComprob","MontoNeto","Validación"};
      DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
      ArrayList<EExportarRetencion> lstbusq=new ArrayList<>();
      DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
      int val=0;
    /**
     * Creates new form InterFrmTXTRetencion
     */
    public InterFrmTXTRetencion() {
        initComponents();
        this.setTitle("Emitir Txt de Retención.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblstretencion.setModel(modTbdatos);
        tblstretencion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstretencion.getTableHeader().setReorderingAllowed(false);
        tblstretencion.getColumnModel().getColumn(14).setPreferredWidth(180);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
    }

    public boolean esNumeroEntero(String cad) {
        try {
            Integer.parseInt(cad);
            if (Integer.parseInt(cad) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private boolean validarnroserie(){
        int v=0;
        int filas=tblstretencion.getRowCount();
                for (int i = 0; i < filas; i++) {
                    if(tblstretencion.getValueAt(i, 10).toString().trim().length()!=3){
                        v=1;
                    }
                    if(!esNumeroEntero(tblstretencion.getValueAt(i, 11).toString().trim())){
                        v=1;
                    }
                }
        if(v==0){
            return true;
        }else{
            return false;
        }
    }
    
    private void llenartab(){
//     "Ruc","RazonSocial","Ape.Paterno","Ape.Materno","Nombres","Serie Reten","Nro Reten","Fecha Reten",
//     "Suma Monto Bruto", "Tipo Comprob","Serie Comprob","Nro Comprob","FechaComprob","MontoNeto"
        DecimalFormat formato = new DecimalFormat("##0.00", simbolo);
        DecimalFormat formato2 = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos.setRowCount(0);
        double total=0.00;
        if(lstbusq.size()>0){
            int v=0;
            for (int i = 0; i < lstbusq.size(); i++) {
                Object obj[]=new Object[15];
                String dosprimeras=lstbusq.get(i).getRuc().substring(0, 2);
                obj[0]=lstbusq.get(i).getRuc();
                if (dosprimeras.equalsIgnoreCase("10")) {
                    String nombreCompleto, nombre, apellido, espacio, apepat, apemat;
                    nombreCompleto = lstbusq.get(i).getRazonSocial();
                    espacio = " ";
                    nombre = nombreCompleto.substring(0,nombreCompleto.indexOf(espacio));
                    apellido = nombreCompleto.substring(nombreCompleto.indexOf(espacio)+1,nombreCompleto.length());
                    apepat=apellido.substring(apellido.indexOf(" ")+1, apellido.length());
                    obj[1] = "";
                    obj[2]="";
                    obj[3]="";
//                    obj[2]=apellido.substring(0, apellido.indexOf(" "));
//                    obj[3]=apepat.substring(0, apepat.indexOf(" "));
                    obj[4]=nombreCompleto;
                } else {
                    obj[1] = lstbusq.get(i).getRazonSocial();
                    obj[2]="";
                    obj[3]="";
                    obj[4]="";
                }
                int indsr=lstbusq.get(i).getNroReten().indexOf("-");
                obj[5]=lstbusq.get(i).getNroReten().substring(0, indsr);
                obj[6]=lstbusq.get(i).getNroReten().substring(indsr+1,lstbusq.get(i).getNroReten().length());
                obj[7]=lstbusq.get(i).getFechaReten().replaceAll("-", "/");
                if(lstbusq.get(i).getMoneda().trim().equalsIgnoreCase("US$")){
                    total=total+(lstbusq.get(i).getMontoBruto()*lstbusq.get(i).getTipoCambio());
                    obj[8]=formato.format(lstbusq.get(i).getMontoBruto()*lstbusq.get(i).getTipoCambio());
                }else{
                    total=total+lstbusq.get(i).getMontoBruto();
                    obj[8]=formato.format(lstbusq.get(i).getMontoBruto());
                }
                switch (lstbusq.get(i).getTipoComprobante().trim()) {
                    case "FT":
                        obj[9]="01";
                        break;
                    case "BV":
                        obj[9]="02";
                        break;
                    case "LC":
                        obj[9]="54";
                        break;    
                    case "NC":
                        obj[9]="07";
                    break;  
                    case "ND":
                        obj[9]="08";
                    break;
                    default: obj[9]="12";
                }
                
                int indc=lstbusq.get(i).getNroComprobante().indexOf("-");
                obj[10]=lstbusq.get(i).getNroComprobante().substring(0, indc);
                if(lstbusq.get(i).getNroComprobante().substring(0, indc).trim().length()!=3){
                    v=1;
                }
                obj[11]=lstbusq.get(i).getNroComprobante().substring(indc+1, lstbusq.get(i).getNroComprobante().length());
                if(!esNumeroEntero(lstbusq.get(i).getNroComprobante().substring(indc+1,lstbusq.get(i).getNroComprobante().length()))){
                    if(v==1){
                     v=3;   
                    }else{
                    v=2;    
                    }
                    
                }
                obj[12]=lstbusq.get(i).getFechaComprobante().replaceAll("-", "/");
                obj[13]=formato.format(lstbusq.get(i).getNeto());
                if(v==0){
                obj[14]="CORRECTO";    
                }
                if (v == 1) {
                    obj[14] = "ERROR EN CAMPO SERIE DE COMPROBANTE SOLO PUEDE TENER 3 DIGITOS.";
                }
                if (v == 2) {
                    obj[14] = "ERROR EN CAMPO NRO DE COMPROBANTE DEBE TENER VALOR NUMERICO.";
                }
                if (v == 3) {
                    obj[14] = "ERROR EN CAMPO NRO DE COMPROBANTE DEBE TENER VALOR NUMERICO, CAMPO SERIE DE COMPROBANTE SOLO PUEDE TENER 3 DIGITOS.";
                }
                v=0;
                modTbdatos.addRow(obj);
            }
        }
        tblstretencion.setModel(modTbdatos);
        lblnroregistros.setText(lstbusq.size()+" Registros");
        lbltotalsoles.setText("TOTAL SOLES: "+formato2.format(total));
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
        jLabel2 = new javax.swing.JLabel();
        mcMes = new com.toedter.calendar.JMonthChooser();
        mcAño = new com.toedter.calendar.JYearChooser();
        btnexportar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnbuscar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstretencion = new javax.swing.JTable(){

        };
        lblnroregistros = new javax.swing.JLabel();
        lbltotalsoles = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel2.setBackground(new java.awt.Color(255, 0, 15));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMITIR TXT DE RETENCIÓN");
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

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Periodo (MM-AAAA)");

        mcMes.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        btnexportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/export.png"))); // NOI18N
        btnexportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexportarActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnbuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnbuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscar1ActionPerformed(evt);
            }
        });

        tblstretencion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstretencion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblstretencion);

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setText("0 Registros");

        lbltotalsoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lbltotalsoles.setText("TOTAL SOLES: 0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(mcAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(243, 243, 243)
                .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 60, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblnroregistros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltotalsoles, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mcAño, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mcMes, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar1)
                    .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblnroregistros)
                    .addComponent(lbltotalsoles))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        // TODO add your handling code here:
        if(validarnroserie()){
            if(tblstretencion.getRowCount()>0){
            DecimalFormat formato = new DecimalFormat("#####0.00",simbolo);
       javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        String ruta = "";
        try {
              jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath();
                String nombrearchivo = "";
                int mes=mcMes.getMonth()+1;
                String mimes="";
                if(mes<10){
                    mimes="0"+mes;
                }else{
                    mimes=""+mes;
                }
                nombrearchivo = "062620550226589"+mcAño.getYear()+mimes;
                File archivo = new File(ruta + "\\" + nombrearchivo + ".txt");
                FileWriter escribir=new FileWriter(archivo,true);
                int filas=tblstretencion.getRowCount();
                for (int i = 0; i < filas; i++) {
                    escribir.write(tblstretencion.getValueAt(i, 0).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 1).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 2).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 3).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 4).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 5).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 6).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 7).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 8).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 9).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 10).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 11).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 12).toString().trim());
                    escribir.write("|");
                    escribir.write(tblstretencion.getValueAt(i, 13).toString().trim());
                    escribir.write("|");
                    escribir.write("\r\n"); 
                }
                escribir.close();
                JOptionPane.showMessageDialog(this, "Se exportó correctamente.");
            }
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
        }
        }else{
            JOptionPane.showMessageDialog(this, "No cuenta con data para exportar.");
        }
        }else{
         JOptionPane.showMessageDialog(this, "Validar errores encontrados.");
        }
    }//GEN-LAST:event_btnexportarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        // TODO add your handling code here:
        LProgPagosCabecera lp=new LProgPagosCabecera();
        lstbusq=lp.ListarRetencionxExport(mcMes.getMonth()+1, mcAño.getYear());
        if(lstbusq.size()>0){
            llenartab();    
        }else{
            llenartab();
            JOptionPane.showMessageDialog(this, "No se encontraron registros.");
        }
        
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnexportar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JLabel lbltotalsoles;
    private com.toedter.calendar.JYearChooser mcAño;
    private com.toedter.calendar.JMonthChooser mcMes;
    private javax.swing.JTable tblstretencion;
    // End of variables declaration//GEN-END:variables
}
