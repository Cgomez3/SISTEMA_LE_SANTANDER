/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Retencion;

import Capa_Entidades.EDetraccTxt;
import Capa_Entidades.EProgPagosCabecera;
import Capa_Logica.LDetraccion;
import Capa_Logica.LProgPagosCabecera;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmTXTDetraccion extends javax.swing.JInternalFrame {
    String titulos[] = {"TipoDocProv", "DocProv", "RazonSocial", "NroProforma", "CodServicio", "CtaDetracciones", "Importe",
        "TipoOperacion", "PeriodoTributario", "TipoComprob", "SerieComprob", "NroComprob"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    ArrayList<EDetraccTxt> lstbusq = new ArrayList<>();
    ArrayList<EProgPagosCabecera> lstcab = new ArrayList<>();
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmTXTRetencion
     */
    public InterFrmTXTDetraccion() {
        initComponents();
        this.setTitle("Emitir Txt de Detracciones.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblstretencion.setModel(modTbdatos);
        tblstretencion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstretencion.getTableHeader().setReorderingAllowed(false);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        llenarcbopagos();
    }
    
    private void llenarcbopagos() {
        LProgPagosCabecera lpc = new LProgPagosCabecera();
        lstcab = lpc.listacabparagp();
        cboprogramacion.removeAllItems();
        cboprogramacion.addItem("Seleccione");
        for (int i = 0; i < lstcab.size(); i++) {
            cboprogramacion.addItem(lstcab.get(i).getFecha() + " | " + lstcab.get(i).getMoneda() + " | " + lstcab.get(i).getBanco());
        }
    }

    private void llenartab(){
//"TipoDocProv","DocProv","RazonSocial","NroProforma","CodServicio","CtaDetracciones",
//        "Importe",
//     "TipoOperacion", "PeriodoTributario","TipoComprob","SerieComprob","NroComprob"

        DecimalFormat formato = new DecimalFormat("##0", simbolo);
        DecimalFormat formato2 = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos.setRowCount(0);
        double total = 0.00;
        if (lstbusq.size() > 0) {
            for (int i = 0; i < lstbusq.size(); i++) {
                Object obj[] = new Object[12];
                String dosprimeras = lstbusq.get(i).getDocProv().substring(0, 2);
                obj[1] = lstbusq.get(i).getDocProv();
                if (dosprimeras.equalsIgnoreCase("20")) {
                    obj[0] = "6";
                } else {
                    obj[0] = "6";
                }
                obj[2] = "";
                obj[3] = "";
                String tipodet = lstbusq.get(i).getCodDetraccion();
                if (tipodet.trim().length() == 2) {
                    tipodet = "0" + tipodet;
                }
                if (tipodet.trim().length() == 1) {
                    tipodet = "00" + tipodet;
                }
                obj[4] = tipodet;
                obj[5] = lstbusq.get(i).getCtaDetraccion();

                obj[6] = formato.format(lstbusq.get(i).getImporte());
                total = total + lstbusq.get(i).getImporte();
                obj[7] = lstbusq.get(i).getTipoOp();
                int mes = Integer.valueOf(lstcab.get(cboprogramacion.getSelectedIndex() - 1).getFecha().substring(3, 5));
                String mimes = "";
                if (mes < 10){
                    mimes = "0" + mes;
                } else {
                    mimes = "" + mes;
                }
                obj[8] = lstbusq.get(i).getPeriodoTributario(); // Integer.valueOf(lstcab.get(cboprogramacion.getSelectedIndex() - 1).getFecha().substring(6, 10)) + mimes;
                System.out.println(obj[8]);
                switch (lstbusq.get(i).getTipoComprobante().trim()) {
                    case "FT":
                        obj[9] = "01";
                        break;
                    case "BV":
                        obj[9] = "02";
                        break;
                    case "LC":
                        obj[9] = "54";
                        break;
                    case "NC":
                        obj[9] = "07";
                        break;
                    case "ND":
                        obj[9] = "08";
                        break;
                }

                int indc = lstbusq.get(i).getNroComprobante().indexOf("-");
                obj[10] = lstbusq.get(i).getNroComprobante().substring(0, indc);
                obj[11] = lstbusq.get(i).getNroComprobante().substring(indc + 1, lstbusq.get(i).getNroComprobante().length());
                modTbdatos.addRow(obj);
            }
        }
        tblstretencion.setModel(modTbdatos);
        lblnroregistros.setText(lstbusq.size() + " Registros");
        lbltotalsoles.setText("TOTAL SOLES: " + formato2.format(total));
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
        btnexportar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnbuscar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstretencion = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistros = new javax.swing.JLabel();
        lbltotalsoles = new javax.swing.JLabel();
        cboprogramacion = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtnrolotedetracion = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel2.setBackground(new java.awt.Color(255, 0, 15));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EMITIR TXT DE DETRACCIÓN");
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
        jLabel2.setText("Programación");

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

        cboprogramacion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboprogramacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboprogramacionItemStateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel8.setText("Lote");

        txtnrolotedetracion.setBackground(new java.awt.Color(255, 255, 255));
        txtnrolotedetracion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtnrolotedetracion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnrolotedetracionKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cboprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(7, 7, 7)
                .addComponent(txtnrolotedetracion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(btnbuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 106, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblnroregistros)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbltotalsoles, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
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
                    .addComponent(btnbuscar1)
                    .addComponent(btnexportar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtnrolotedetracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboprogramacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblnroregistros)
                    .addComponent(lbltotalsoles))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnexportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexportarActionPerformed
        // TODO add your handling code here:
        if (tblstretencion.getRowCount() > 0) {
            
            DecimalFormat formato = new DecimalFormat("#####0.00", simbolo);
            javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
            String ruta = "";
            try {
                if(txtnrolotedetracion.getText().trim().length() == 0 ){
                 JOptionPane.showMessageDialog(this, "Ingresar el numero de Lote.");
                 txtnrolotedetracion.isFocusable();
                }else if(txtnrolotedetracion.getText().trim().length() < 6 || txtnrolotedetracion.getText().trim().length() > 6 ){
                 JOptionPane.showMessageDialog(this, "El numero de Lote debe tener maximo 6 caracteres.");
                 txtnrolotedetracion.isFocusable();
                }else{
                jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ruta = jF1.getSelectedFile().getAbsolutePath();
                    String nombrearchivo = "";
                    int mes = Integer.valueOf(lstcab.get(cboprogramacion.getSelectedIndex() - 1).getFecha().substring(3, 5));
                    String mimes = "";
                    if (mes < 10) {
                        mimes = "0" + mes;
                    } else {
                        mimes = "" + mes;
                    }
                    Date hoy = new Date();
                    //nombrearchivo = "D20550226589" + "0" + hoy.getDay() + mimes + "15";
                    nombrearchivo = "D20550226589" + right("000000" + txtnrolotedetracion.getText(),6);
                    File archivo = new File(ruta + "\\" + nombrearchivo + ".txt");
                    FileWriter escribir = new FileWriter(archivo, true);
                    int filas = tblstretencion.getRowCount();
                    escribir.write("*20550226589SANTANDER CONSUMO PERU S.A.        ");
                    //escribir.write("0" + hoy.getDay() + mimes + "15");
                    escribir.write(right("000000" + txtnrolotedetracion.getText(),6));
                    String total = formato.format(lstbusq.get(0).getTotal()).replace(".", "");
                    if (total.trim().length() == 5) {
                        total = "0000000000" + total.trim();
                    }
                    if (total.trim().length() == 6) {
                        total = "000000000" + total.trim();
                    }
                    if (total.trim().length() == 7) {
                        total = "00000000" + total.trim();
                    }
                    if (total.trim().length() == 8) {
                        total = "0000000" + total.trim();
                    }
                    if (total.trim().length() == 9) {
                        total = "000000" + total.trim();
                    }
                    if (total.trim().length() == 10) {
                        total = "00000" + total.trim();
                    }
                    if (total.trim().length() == 11) {
                        total = "0000" + total.trim();
                    }
                    if (total.trim().length() == 12) {
                        total = "000" + total.trim();
                    }
                    if (total.trim().length() == 13) {
                        total = "00" + total.trim();
                    }
                    if (total.trim().length() == 14) {
                        total = "0" + total.trim();
                    }
                    escribir.write(total);
                    escribir.write("\r\n");
                    for (int i = 0; i < filas; i++) {
                        escribir.write(tblstretencion.getValueAt(i, 0).toString().trim());
                        escribir.write(tblstretencion.getValueAt(i, 1).toString().trim());
                        escribir.write("                      ");
                        escribir.write("             000000000");
                        escribir.write(right("000"+ tblstretencion.getValueAt(i, 4).toString().trim(), 3) );
                        escribir.write(right("00000000000"+ tblstretencion.getValueAt(i, 5).toString().trim(), 11));
                        String subtotal =  formato.format(Double.valueOf(tblstretencion.getValueAt(i, 6).toString())).replace(".", "");
                        if (subtotal.trim().length() == 3) {
                            subtotal = "000000000000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 4) {
                            subtotal = "00000000000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 5) {
                            subtotal = "0000000000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 6) {
                            subtotal = "000000000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 7) {
                            subtotal = "00000000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 8) {
                            subtotal = "0000000" + subtotal.trim();
                        }
                        if (total.trim().length() == 9) {
                            subtotal = "000000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 10) {
                            subtotal = "00000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 11) {
                            subtotal = "0000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 12) {
                            subtotal = "000" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 13) {
                            subtotal = "00" + subtotal.trim();
                        }
                        if (subtotal.trim().length() == 14) {
                            subtotal = "0" + subtotal.trim();
                        }
                        escribir.write(subtotal);
                        escribir.write(tblstretencion.getValueAt(i, 7).toString().trim());
                        escribir.write(tblstretencion.getValueAt(i, 8).toString().trim());
                        escribir.write(tblstretencion.getValueAt(i, 9).toString().trim());
                        String nrocomprobante = tblstretencion.getValueAt(i, 10).toString().trim();
                        if (nrocomprobante.trim().length() == 3) {
                            nrocomprobante = "F" + nrocomprobante.trim();
                        }
                        escribir.write(nrocomprobante);
                        String seriecomprobante = tblstretencion.getValueAt(i, 11).toString().trim();
                        if (seriecomprobante.trim().length() == 3) {
                            seriecomprobante = "00000" + seriecomprobante.trim();
                        }
                        if (seriecomprobante.trim().length() == 4) {
                            seriecomprobante = "0000" + seriecomprobante.trim();
                        }
                        if (seriecomprobante.trim().length() == 5) {
                            seriecomprobante = "000" + seriecomprobante.trim();
                        }
                        if (seriecomprobante.trim().length() == 6) {
                            seriecomprobante = "00" + seriecomprobante.trim();
                        }
                        if (seriecomprobante.trim().length() == 7) {
                            seriecomprobante = "0" + seriecomprobante.trim();
                        }
                        escribir.write(seriecomprobante.trim());
                        escribir.write("\r\n");
                    }
                    escribir.close();
                    JOptionPane.showMessageDialog(this, "Se exportó correctamente.");
                }
                }
            } catch (HeadlessException | NumberFormatException | IOException ex) {
                ex.getMessage();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cuenta con data para exportar.");
        }
    }//GEN-LAST:event_btnexportarActionPerformed
   public static String right(String value, int length) {
        return value.substring(value.length() - length);
    }
    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnbuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscar1ActionPerformed
        // TODO add your handling code here:
        LDetraccion lp = new LDetraccion();
        if (cboprogramacion.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Seleccione Programación de Pagos.");
            cboprogramacion.requestFocus();
        } else {
            lstbusq = lp.ListarDetracciones(lstcab.get(cboprogramacion.getSelectedIndex() - 1).getCod_programacion());
            if (lstbusq.size() > 0) {
                llenartab();
            } else {
                llenartab();
                JOptionPane.showMessageDialog(this, "No se encontraron registros.");
            }
        }
    }//GEN-LAST:event_btnbuscar1ActionPerformed

    private void cboprogramacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboprogramacionItemStateChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_cboprogramacionItemStateChanged

    private void txtnrolotedetracionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnrolotedetracionKeyTyped
        // TODO add your handling code here:
         char caracter = evt.getKeyChar();

      // Verificar si la tecla pulsada no es un digito
      if(((caracter < '0') ||
         (caracter > '9')) &&
         (caracter != '\b' /*corresponde a BACK_SPACE*/))
      {
         evt.consume();  // ignorar el evento de teclado
      }
    }//GEN-LAST:event_txtnrolotedetracionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar1;
    private javax.swing.JButton btnexportar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cboprogramacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JLabel lbltotalsoles;
    private javax.swing.JTable tblstretencion;
    private javax.swing.JTextField txtnrolotedetracion;
    // End of variables declaration//GEN-END:variables
}
