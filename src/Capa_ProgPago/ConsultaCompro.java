/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EEstado;
import Capa_Logica.LComprobante;
import Capa_Logica.LConsultaComprobante;
import Capa_Logica.SesionUsuario;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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
public class ConsultaCompro extends javax.swing.JInternalFrame {
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    ArrayList<EComprobante> liscom = new ArrayList<>();
    ArrayList<EComprobante> liscom1 = new ArrayList<>();
    ArrayList<EEstado> lisest = new ArrayList<>();
    String titulos[] = {"TipoComp", "NumComp", "FecComp", "Estado", "RazonSocial",
        "DocProveedor", "Glosa", "Sujeto A", "ConcepDetra", "FormaPago", "DestinoPago", "Moneda", "TC",
        "MBaseSOL", "MOtrosSOL", "MIGVSOL", "MTotalSOL", "MRetDetSOL", "MPagarSOL",
        "MBaseDOL", "MOtrosDOL", "MIGVDOL", "MTotalDOL", "MRetDetDOL",
        "MPagarDOL", "PorcRetDet", "PorcIGV", "CompRef", "RazonSocialRef", "FechaRef", "UsuCrea", "FecCrea"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    LComprobante lcom = new LComprobante();
    int opc = 0;
      
    /**
     * Creates new form ConsultaCompro
     */
    public ConsultaCompro() {
        initComponents();
        this.setTitle("Consulta De Comprobante.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblistacomprobante.setModel(modTbdatos);
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
        llenarEstado();
        listacomprobante();
        Date hoy=new Date();
        
        dcfechainicio.setDate(hoy);
        dcfechafin.setDate(hoy);
        cboestado.setEnabled(false);
        btnproveedor.setEnabled(false);
        dcfechafin.setEnabled(false);
        dcfechainicio.setEnabled(false);
        rbfecha.setSelected(true);
    }

    
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2 );
        int height = ((desktopSize.height - jInternalFrameSize.height) /4);
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
    
    
    
    
    private void llenarEstado(){
    LConsultaComprobante lre=new LConsultaComprobante();
    lisest=lre.ListaEstadoCompro();
    cboestado.removeAllItems();
        cboestado.addItem("<Seleccione>");
     for(int i=0; i<lisest.size();i++){
        cboestado.addItem(lisest.get(i).getDes_Estado());
     }   
}
    
      private String codcliente(String estado){
         String cod="";
         for(int i=0;i<liscom.size();i++){
             if(liscom.get(i).getEstado().trim().equals(estado)){
                 cod=liscom.get(i).getEstado();
             }
         }
         return cod;
     }
    
    
    
    
    
    
    String fechaformateador(Date fecha){
    String fechanaci=new SimpleDateFormat("dd-MM-yyyy").format(fecha);
    return fechanaci;
    }
    
    
        private void listacomprobante(){
            DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
            if(opc==0){
        LComprobante lpro=new LComprobante();
            liscom=lpro.listaComprobante3();
            }
                  modTbdatos.setRowCount(0);
                if(liscom.size()>0){
             for(int i=0;i<liscom.size();i++){
                 Object nuevafila[] = new Object[32];
//               nuevafila[0] = liscom.get(i).getCodComprobante();
                 nuevafila[0] = liscom.get(i).getTipoComprobante();
                 nuevafila[1] = liscom.get(i).getNumComprobante();
                 nuevafila[2] = liscom.get(i).getFechaComprobante();
                 nuevafila[3] = liscom.get(i).getEstado(); 
                 nuevafila[4] = liscom.get(i).getRazonSocial();
                 nuevafila[5] = liscom.get(i).getDocProveedor();
                 nuevafila[6] = liscom.get(i).getGlosaComprante();
                 nuevafila[7] = liscom.get(i).getComprobanteSujeto();
                 nuevafila[8] = liscom.get(i).getTipoDetra();
                 nuevafila[9] = liscom.get(i).getFormaPago();
                 nuevafila[10] = liscom.get(i).getDestinoPago();
                 nuevafila[11] = liscom.get(i).getNomMoneda();
                 nuevafila[12] = liscom.get(i).getTCComprobante();
                 nuevafila[13] = formato.format(liscom.get(i).getMontoBase());
                 nuevafila[14] = formato.format(liscom.get(i).getMontoOtros());
                 nuevafila[15] = formato.format(liscom.get(i).getMontoIGV());
                 nuevafila[16] = formato.format(liscom.get(i).getMontoTotal());
                 nuevafila[17] = formato.format(liscom.get(i).getMontoRetDet());
                 nuevafila[18] = formato.format(liscom.get(i).getMontoPagar());
                 nuevafila[19] = formato.format(liscom.get(i).getMontoBaseDOL());  
                 nuevafila[20] = formato.format(liscom.get(i).getMontoOtrosDOL());
                 nuevafila[21] = formato.format(liscom.get(i).getMontoIGVDOL());
                 nuevafila[22] = formato.format(liscom.get(i).getMontoTotalDOL());
                 nuevafila[23] = formato.format(liscom.get(i).getMontoRetDetDOL());
                 nuevafila[24] = formato.format(liscom.get(i).getMontoPagarDOL());
                 nuevafila[25] = formato.format(liscom.get(i).getPorcentaje());
                 nuevafila[26] = formato.format(liscom.get(i).getPorcIGV());
                 nuevafila[27] = liscom.get(i).getComprobanteRef();
                 nuevafila[28] = liscom.get(i).getRazonSocialRef();
                 nuevafila[29] = liscom.get(i).getFechaRef();
                 nuevafila[30] = liscom.get(i).getUsuCrea(); 
                 nuevafila[31] = liscom.get(i).getFechaCrea(); 

                 modTbdatos.addRow(nuevafila);
             }
        }
        tblistacomprobante.setModel(modTbdatos);
         lblnrocomprobante.setText(tblistacomprobante.getRowCount() + " Comprobante(es)");
    }
    
        
        
    String fechaformato(Date fecha){
    String fec=new SimpleDateFormat("dd-MM-yyyy").format(fecha);
    return fec;
    }
    
    
    

    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgfiltro = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rbfecha = new javax.swing.JRadioButton();
        rbestado = new javax.swing.JRadioButton();
        rbproveedor = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        dcfechainicio = new com.toedter.calendar.JDateChooser()

        ;
        dcfechafin = new com.toedter.calendar.JDateChooser()

        ;
        jLabel8 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cboestado = new javax.swing.JComboBox();
        btnBuscar = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        txtpro = new javax.swing.JTextField();
        btnproveedor = new javax.swing.JButton();
        lblnrocomprobante = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacomprobante = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btnReporte1 = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(847, 579));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE COMPROBANTES");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 810, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 18));

        rbfecha.setBackground(new java.awt.Color(255, 255, 255));
        rbfecha.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbfecha.setText("Por Fecha");
        rbfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfechaActionPerformed(evt);
            }
        });
        getContentPane().add(rbfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        rbestado.setBackground(new java.awt.Color(255, 255, 255));
        rbestado.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbestado.setText("Estado");
        rbestado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbestadoItemStateChanged(evt);
            }
        });
        rbestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbestadoActionPerformed(evt);
            }
        });
        getContentPane().add(rbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 60, 20));

        rbproveedor.setBackground(new java.awt.Color(255, 255, 255));
        rbproveedor.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbproveedor.setText("Proveedor");
        rbproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbproveedorActionPerformed(evt);
            }
        });
        getContentPane().add(rbproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, 20));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 90, 80));

        dcfechainicio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        getContentPane().add(dcfechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        dcfechafin.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        getContentPane().add(dcfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel8.setText("Fec. Ini");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, 20));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText("Fec. Fin");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, 20));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel11.setText("Proveedor");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, -1, 20));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel10.setText("Estado");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, -1, 20));

        cboestado.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        cboestado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cboestadoKeyReleased(evt);
            }
        });
        getContentPane().add(cboestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 190, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 70, 30));

        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 70, 30));

        txtpro.setEnabled(false);
        txtpro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtproKeyReleased(evt);
            }
        });
        getContentPane().add(txtpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 190, -1));

        btnproveedor.setText("jButton1");
        btnproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedorActionPerformed(evt);
            }
        });
        getContentPane().add(btnproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 30, 30, 20));

        lblnrocomprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnrocomprobante.setText("0 Comprobantes");
        lblnrocomprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblnrocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 210, 10));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

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
        jScrollPane1.setViewportView(tblistacomprobante);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 810, 390));

        btnReporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excelmini.png"))); // NOI18N
        btnReporte1.setPreferredSize(new java.awt.Dimension(79, 35));
        btnReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporte1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnReporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 70, 30));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 810, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfechaActionPerformed
        rbfecha.setSelected(true);
        rbestado.setSelected(false);
        rbproveedor.setSelected(false);
        
        btnproveedor.setEnabled(false);
        cboestado.setEnabled(false);
        dcfechainicio.setEnabled(true);
        dcfechafin.setEnabled(true);
        
    }//GEN-LAST:event_rbfechaActionPerformed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void rbproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbproveedorActionPerformed
        rbfecha.setSelected(false);
        rbestado.setSelected(false);
        rbproveedor.setSelected(true);
        
        
        dcfechainicio.setEnabled(false);
        dcfechafin.setEnabled(false);
        cboestado.setEnabled(false);
        btnproveedor.setEnabled(true);
        
    }//GEN-LAST:event_rbproveedorActionPerformed

    private void rbestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbestadoActionPerformed
        rbfecha.setSelected(false);
        rbestado.setSelected(true);
        rbproveedor.setSelected(false);
        
        dcfechainicio.setEnabled(false);
        dcfechafin.setEnabled(false);
        btnproveedor.setEnabled(false);
        cboestado.setEnabled(true);
        
    }//GEN-LAST:event_rbestadoActionPerformed

    private void cboestadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboestadoKeyReleased
//          if(cboestado.getSelectedIndex()==0){
//            LConsultaComprobante lo = new LConsultaComprobante();
//            listservc = lo.ListaxEstado(cboestado.getSelectedItem().toString());
//            if (listservc.size() > 0) {
//                listacomprobante();
//            }else{
//                listacomprobante();
//            }
//        }
    }//GEN-LAST:event_cboestadoKeyReleased

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
     opc=1;
           if(rbestado.isSelected()==false && rbfecha.isSelected()==false && rbproveedor.isSelected()==false ){
               JOptionPane.showMessageDialog(this, "Seleccione Filtro que desea buscar.");
               rbfecha.requestFocus();
               return;
           }
           
              if(rbfecha.isSelected()){
            if(dcfechainicio.getDate()==null){
                JOptionPane.showMessageDialog(this, "Seleccione Fecha de Inicio.");
                dcfechainicio.requestFocus();
            }else{
              if(dcfechafin.getDate()==null){
                  JOptionPane.showMessageDialog(this, "Seleccine Fecha de Fin.");
                  dcfechafin.requestFocus();
              }else{
                   if(dcfechafin.getDate().before(dcfechainicio.getDate())){ 
                     JOptionPane.showMessageDialog(this, "Fecha de Fin no puede ser anterior a la Fecha de Inicio.");
                     dcfechafin.requestFocus();
                      liscom.removeAll(liscom);
                      listacomprobante();
                   }else{
                  LConsultaComprobante lo=new LConsultaComprobante();
                  liscom=lo.ListaxFecha(fechaformato(dcfechainicio.getDate()), fechaformato(dcfechafin.getDate()));
                  if (liscom.size() > 0) {
                      listacomprobante();
                  } else {
                      JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                      liscom.removeAll(liscom);
                      listacomprobante();
                  }
              }
            }
        }
      }    
           
            if(rbestado.isSelected()){
            if(cboestado.getSelectedIndex()==0){
                JOptionPane.showMessageDialog(this, "Seleccione Estado que desea buscar.");
                cboestado.requestFocus();
            }else{
                LConsultaComprobante lo=new LConsultaComprobante();
                liscom=lo.ListaxEstado(cboestado.getSelectedItem().toString());
                System.out.println(liscom.size());
                if (liscom.size() > 0) {
                    
                    listacomprobante();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                    listacomprobante();
                    txtpro.requestFocus();
                }
            }
        }
     
     
     
     
     
     
     
            if(rbproveedor.isSelected()){
            if(txtpro.getText().trim().length()==0){
                JOptionPane.showMessageDialog(this, "Ingrese Nombre que desea buscar.");
                txtpro.requestFocus();
            }else{
                LConsultaComprobante lo=new LConsultaComprobante();
                liscom=lo.ListaxRazonSocial(txtpro.getText());
                System.out.println(liscom.size());
                if (liscom.size() > 0) {
                    
                    listacomprobante();
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                    listacomprobante();
                    txtpro.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void rbestadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbestadoItemStateChanged
        cboestado.setEnabled(false);
        cboestado.setSelectedIndex(0);
    }//GEN-LAST:event_rbestadoItemStateChanged

    private void btnproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproveedorActionPerformed
        ConsultaProveedor conpro = new ConsultaProveedor();
        Menu.dpane2.add(conpro);
        centerJIF(conpro);
        conpro.setVisible(true);
        conpro.otrofrm=1;
        
         SesionUsuario.txtrazonsocial = txtpro;
    }//GEN-LAST:event_btnproveedorActionPerformed

    private void txtproKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtproKeyReleased
//        if(txtpro.getText().trim().length()>0){
//            LConsultaComprobante lo = new LConsultaComprobante();
//            liscom1 = lo.ListaxRazonSocial(txtpro.getText().trim());
//            if (liscom1.size() > 0) {
//                listacomprobante();
//            }else{
//                listacomprobante();
//            }
//        }
    }//GEN-LAST:event_txtproKeyReleased

    private void btnReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporte1ActionPerformed
        if(liscom.size()>0){
            try { 
                javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
                jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String ruta = "";
                if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ruta = jF1.getSelectedFile().getAbsolutePath();
                    WritableWorkbook workbook =Workbook.createWorkbook(new File(ruta+"/DatosRegistrados_Consulta_Comprobante.xls"));
                    WritableSheet sheet =workbook.createSheet("Datos Registrados", 0);
                    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 11);
                    cellFont.setColour(Colour.BLUE);
                    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
                    WritableCellFormat cellFormat2 = new WritableCellFormat();
                    cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
                             
                    sheet.setColumnView(0, 20);
                    sheet.addCell(new jxl.write.Label(0, 0, "TipoComp",cellFormat));
                    sheet.setColumnView(1, 20);
                    sheet.addCell(new jxl.write.Label(1, 0, "NumComp",cellFormat));
                    sheet.setColumnView(2, 20);
                    sheet.addCell(new jxl.write.Label(2, 0, "FecComp",cellFormat));
                    sheet.setColumnView(3, 20);
                    sheet.addCell(new jxl.write.Label(3, 0, "Estado",cellFormat));
                    sheet.setColumnView(4, 20);
                    sheet.addCell(new jxl.write.Label(4, 0, "RazonSocial",cellFormat));
                    sheet.setColumnView(5, 20);
                    sheet.addCell(new jxl.write.Label(5, 0, "DocProveedor",cellFormat));
                    sheet.setColumnView(6, 20);
                    sheet.addCell(new jxl.write.Label(6, 0, "Glosa",cellFormat));
                    sheet.setColumnView(7, 20);
                    sheet.addCell(new jxl.write.Label(7, 0, "Sujeto A",cellFormat));
                    sheet.setColumnView(8, 20);
                    sheet.addCell(new jxl.write.Label(8, 0, "ConcepDetra",cellFormat));
                    
                    sheet.setColumnView(9, 20);
                    sheet.addCell(new jxl.write.Label(9, 0, "FormaPago",cellFormat));
                    sheet.setColumnView(10, 20);
                    sheet.addCell(new jxl.write.Label(10, 0, "DestinoPago",cellFormat));
                    sheet.setColumnView(11, 20);
                    sheet.addCell(new jxl.write.Label(11, 0, "Moneda",cellFormat));
                    sheet.setColumnView(12, 20);
                    sheet.addCell(new jxl.write.Label(12, 0, "TC",cellFormat));
                    sheet.setColumnView(13, 20);
                    sheet.addCell(new jxl.write.Label(13, 0, "MBaseSOL",cellFormat));
                    sheet.setColumnView(14, 20);
                    sheet.addCell(new jxl.write.Label(14, 0, "MOtrosSOL",cellFormat));
                    sheet.setColumnView(15, 20);
                    sheet.addCell(new jxl.write.Label(15, 0, "MIGVSOL",cellFormat));
                    sheet.setColumnView(16, 20);
                    sheet.addCell(new jxl.write.Label(16, 0, "MTotalSOL",cellFormat));
                    sheet.setColumnView(17, 20);
                    sheet.addCell(new jxl.write.Label(17, 0, "MRetDetSOL",cellFormat));
                    
                    
                    sheet.setColumnView(18, 20);
                    sheet.addCell(new jxl.write.Label(18, 0, "MPagarSOL",cellFormat));
                    sheet.setColumnView(19, 20);
                    sheet.addCell(new jxl.write.Label(19, 0, "MBaseDOL",cellFormat));
                    sheet.setColumnView(20, 20);
                    sheet.addCell(new jxl.write.Label(20, 0, "MOtrosDOL",cellFormat));
                    sheet.setColumnView(21, 20);
                    sheet.addCell(new jxl.write.Label(21, 0, "MIGVDOL",cellFormat));
                    sheet.setColumnView(22, 20);
                    sheet.addCell(new jxl.write.Label(22, 0, "MTotalDOL",cellFormat));
                    sheet.setColumnView(23, 20);
                    sheet.addCell(new jxl.write.Label(23, 0, "MRetDetDOL",cellFormat));
                    sheet.setColumnView(24, 20);
                    sheet.addCell(new jxl.write.Label(24, 0, "MPagarDOL",cellFormat));
                    sheet.setColumnView(25, 20);
                    sheet.addCell(new jxl.write.Label(25, 0, "PorcRetDet",cellFormat));
                    sheet.setColumnView(26, 20);
                    sheet.addCell(new jxl.write.Label(26, 0, "PorcIGV",cellFormat));
                    
                    sheet.setColumnView(27, 20);
                    sheet.addCell(new jxl.write.Label(27, 0, "CompRefe",cellFormat));
                    sheet.setColumnView(28, 20);
                    sheet.addCell(new jxl.write.Label(28, 0, "RazonSocialRef",cellFormat));
                    sheet.setColumnView(29, 20);
                    sheet.addCell(new jxl.write.Label(29, 0, "FechaRef",cellFormat));
                    sheet.setColumnView(30, 20);
                    sheet.addCell(new jxl.write.Label(30, 0, "UsuCrea",cellFormat));
                    sheet.setColumnView(31, 20);
                    sheet.addCell(new jxl.write.Label(31, 0, "FecCrea",cellFormat));
                    for (int i = 0; i < liscom.size(); i++) {
                    sheet.addCell(new jxl.write.Label(0, i+1, liscom.get(i).getTipoComprobante(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(1, i+1, liscom.get(i).getNumComprobante(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(2, i+1, liscom.get(i).getFechaComprobante().toString(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(3, i+1, liscom.get(i).getEstado(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(4, i+1, liscom.get(i).getRazonSocial(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(5, i+1, liscom.get(i).getDocProveedor(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(6, i+1, liscom.get(i).getGlosaComprante(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(7, i+1, liscom.get(i).getComprobanteSujeto(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(8, i+1, liscom.get(i).getTipoDetra(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(9, i+1, liscom.get(i).getFormaPago(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(10, i+1, liscom.get(i).getDestinoPago(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(11, i+1, liscom.get(i).getNomMoneda(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(12, i+1, liscom.get(i).getTCComprobante(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(13, i+1, liscom.get(i).getMontoBase(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(14, i+1, liscom.get(i).getMontoOtros(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(15, i+1, liscom.get(i).getMontoIGV(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(16, i+1, liscom.get(i).getMontoTotal(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(17, i+1, liscom.get(i).getMontoRetDet(),cellFormat2)); 
                    sheet.addCell(new jxl.write.Number(18, i+1, liscom.get(i).getMontoPagar(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(19, i+1, liscom.get(i).getMontoBaseDOL(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(20, i+1, liscom.get(i).getMontoOtrosDOL(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(21, i+1, liscom.get(i).getMontoIGVDOL(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(22, i+1, liscom.get(i).getMontoTotalDOL(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(23, i+1, liscom.get(i).getMontoRetDetDOL(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(24, i+1, liscom.get(i).getMontoPagarDOL(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(25, i+1, liscom.get(i).getPorcentaje(),cellFormat2));
                    sheet.addCell(new jxl.write.Number(26, i+1, liscom.get(i).getPorcIGV(),cellFormat2)); 
                    sheet.addCell(new jxl.write.Label(27, i+1, liscom.get(i).getComprobanteRef(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(28, i+1, liscom.get(i).getRazonSocialRef(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(29, i+1, liscom.get(i).getFechaRef().toString(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(30, i+1, liscom.get(i).getUsuCrea(),cellFormat2));
                    sheet.addCell(new jxl.write.Label(31, i+1, liscom.get(i).getFechaCrea(),cellFormat2));

                    }
                    workbook.write();
                    workbook.close(); 
                }
            } catch (IOException | WriteException ex) {
                System.out.println(ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(this, "No cuenta con data para exportar a excel.");
        }
    }//GEN-LAST:event_btnReporte1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgfiltro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnReporte1;
    private javax.swing.JButton btnproveedor;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JComboBox cboestado;
    private com.toedter.calendar.JDateChooser dcfechafin;
    private com.toedter.calendar.JDateChooser dcfechainicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblnrocomprobante;
    private javax.swing.JRadioButton rbestado;
    private javax.swing.JRadioButton rbfecha;
    private javax.swing.JRadioButton rbproveedor;
    private javax.swing.JTable tblistacomprobante;
    private javax.swing.JTextField txtpro;
    // End of variables declaration//GEN-END:variables
}
