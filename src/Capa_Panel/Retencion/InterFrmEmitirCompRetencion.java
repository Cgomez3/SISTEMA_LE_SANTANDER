/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Retencion;

import Capa_Entidades.EDataRetencion;
import Capa_Logica.LImportar;
import Capa_Logica.LProgPagosCabecera;
import Capa_Logica.LTipoCambio;
import Capa_Logica.SesionUsuario;
import Capa_Panel.Retencion.Reportes.CReporteRetencion;
import Capa_PrgMantemiento.TipoCambio;
import Capa_ProgPago.Menu;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmEmitirCompRetencion extends javax.swing.JInternalFrame {
   double tc= 0.000;
   ArrayList<EDataRetencion> lstretencion=new ArrayList<>();
   ArrayList<EDataRetencion> lstcomprobreten=new ArrayList<>();
   ArrayList<EDataRetencion> lstseleccionada=new ArrayList<>();
   ArrayList<String> lstprov=new ArrayList<>();
    String titulos[] = {"Seleccionar","CodComprobante","TipoComprobante","NumComprobante","Fecha","DocProv",
        "RazonSocial","Direccion", "Sujetoa", "MontoSoles", "MontoDolares", "Retencion",
        "Estado","Moneda"};
      DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
      String titulos2[] = {"Codigo", "Correlativo", "Nro Comprobante Retenc.", "Razon Social", "RUC", "Fecha", "Nro.Doc.",
        "Importe", "% Reten.", "Retencion", "SUNAT", "NETO"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmEmitirCompRetencion
     */
    public InterFrmEmitirCompRetencion() {
        initComponents();
        this.setTitle("Emitir Comprobantes de Retención");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblstretencion.setModel(modTbdatos2);
        tblstretencion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        llenardata(1);
        modTbdatos=new DefaultTableModel(){
          private static final long serialVersionUID = 1L;  
             @Override
            public Class<?> getColumnClass(int column) {  
                    switch (column) {  
                        case 0: return Boolean.class;
                        default: return   String.class;
                                     }  
            }      
        };
        modTbdatos.setColumnIdentifiers(titulos);
        Date hoy = new Date();
        dcfecha.setDate(hoy);
        tblstcomprobantes.setModel(modTbdatos);
        tblstcomprobantes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        llenartabcomprobantes();
        TraerTC();
        dcfecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                // If the 'date' property was changed...
                if ("date".equals(evt.getPropertyName())) {
                    JDateChooser aDateChooser = (JDateChooser) evt.getSource();
                    boolean isDateSelectedByUser = false;
                    // Get the otherwise unaccessible JDateChooser's 'dateSelected' field.
                    try {
                        // Get the desired field using reflection
                        Field dateSelectedField = JDateChooser.class.getDeclaredField("dateSelected");
                        // This line makes the value accesible (can be read and/or modified)
                        dateSelectedField.setAccessible(true);
                        TraerTC();
                        isDateSelectedByUser = dateSelectedField.getBoolean(aDateChooser);
                    } catch (Exception ignoreOrNot) {
                    }

                    // Do some important stuff depending on wether value was changed by user
                    if (isDateSelectedByUser) {
                    }

                    // Reset the value to false
                    try {
                    } catch (Exception ignoreOrNot) {
                    }
                }
            }
        });
    }
    
    private void llenardata(int cod){
        LImportar lim=new LImportar();
        lstcomprobreten=lim.ListarRetencionDetalle(cod);
        llenartabla();
    }
    
    private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
//        "Codigo","Correlativo", "Razon Social", "RUC", "Fecha", "Nro.Doc.",
//        "Importe", "% Reten.", "Retencion", "SUNAT", "NETO","Nro Comprobante Retenc."
        modTbdatos2.setRowCount(0);
        if (lstcomprobreten.size() > 0) {
            for (int i = 0; i < lstcomprobreten.size(); i++) {
                Object obj[] = new Object[12];
                obj[0] = lstcomprobreten.get(i).getCodigo();
                obj[1] = lstcomprobreten.get(i).getCorrelativo();
                obj[2] = lstcomprobreten.get(i).getNroComprobanteReten();
                obj[3] = lstcomprobreten.get(i).getRazonSocial();
                obj[4] = lstcomprobreten.get(i).getRUC();
                obj[5] = lstcomprobreten.get(i).getFecha();
                obj[6] = lstcomprobreten.get(i).getDoc();
                obj[7] = formato.format(Double.valueOf(lstcomprobreten.get(i).getImporte()));
                obj[8] = lstcomprobreten.get(i).getPorcentRetenc();
                obj[9] = formato.format(Double.valueOf(lstcomprobreten.get(i).getRetencion()));
                obj[10] = formato.format(Double.valueOf(lstcomprobreten.get(i).getSUNAT()));
                obj[11] = formato.format(Double.valueOf(lstcomprobreten.get(i).getNeto()));
                modTbdatos2.addRow(obj);    
            }
        }
        tblstretencion.setModel(modTbdatos2);
    }
    
    private void llenartabcomprobantes(){
        modTbdatos.setRowCount(0);
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        LProgPagosCabecera lp=new LProgPagosCabecera();
        lstretencion=lp.ComprobReten();
        if(lstretencion.size()>0){
            for (int i = 0; i < lstretencion.size(); i++) {
                Object obj[]=new Object[14];
                obj[0]=Boolean.TRUE;
                obj[1]=lstretencion.get(i).getCodigo();
                obj[2]=lstretencion.get(i).getTipoComprobante();
                obj[3]=lstretencion.get(i).getNroComprobanteReten();
                obj[4]=lstretencion.get(i).getFecha();
                obj[5]=lstretencion.get(i).getDoc();
                obj[6]=lstretencion.get(i).getRazonSocial();
                obj[7]=lstretencion.get(i).getDireccion();
                obj[8]=lstretencion.get(i).getSujetoA();
                obj[9]=formato.format(Double.valueOf(lstretencion.get(i).getMontoSoles()));
                obj[10]=formato.format(Double.valueOf(lstretencion.get(i).getMontoDolares()));
                obj[11]=formato.format(Double.valueOf(lstretencion.get(i).getRetencion()));
                obj[12]=lstretencion.get(i).getEstado();
                obj[13]=lstretencion.get(i).getMoneda();
                modTbdatos.addRow(obj);
            }
        }
        tblstcomprobantes.setModel(modTbdatos);
    }
    
    private void confirmarseleccionados(){
        lstseleccionada.removeAll(lstseleccionada);
        if(tblstcomprobantes.getRowCount()>0){
            for (int i = 0; i < tblstcomprobantes.getRowCount(); i++) {
                if(tblstcomprobantes.getValueAt(i, 0).toString().equals("true")){
                    lstseleccionada.add(lstretencion.get(i));
                }
            }
        }
    }
    
    private void nroclientes(){
        lstprov.removeAll(lstprov);
        if(lstseleccionada.size()>0){
            for (int i = 0; i < lstseleccionada.size(); i++) {
                int val = 0;
                if (lstprov.size() > 0) {
                    for (int j = 0; j < lstprov.size(); j++) {
                        if (lstprov.get(j).equalsIgnoreCase(lstseleccionada.get(i).getDoc().trim())) {
                            val = 1;
                        }
                    }
                }
                if(val==0){
                    lstprov.add(lstseleccionada.get(i).getDoc().trim());
                }
            }
        }
    }
    
     public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2 );
        int height = ((desktopSize.height - jInternalFrameSize.height) /4);
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

     public class CustomTableCellRenderer extends DefaultTableCellRenderer
{
    public CustomTableCellRenderer()
    {
        super();
    }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value instanceof Boolean) {
                if (value != null) {
                    JCheckBox jcb = new JCheckBox();
                    jcb.setSelected((Boolean) value);

                    return jcb;
                }
                return new JPanel();
            }
            return this;
        }
    }
     
    String fechaformateador(Date fecha) {
        String fechaletra = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        return fechaletra;
    }    
    
    private void TraerTC() {
        LTipoCambio ltc = new LTipoCambio();
        System.out.println("" + fechaformateador(dcfecha.getDate()));
        tc = ltc.RecuperarTipoCambio(fechaformateador(dcfecha.getDate()));
        if (tc == 0.000) {
            lblTC.setText("0.000");
            lblTC.setForeground(Color.red);
            lblTC.setEnabled(true);
        } else {
            lblTC.setText("" + tc);
            lblTC.setForeground(Color.GREEN);
            lblTC.setEnabled(false);
        }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstretencion = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel2 = new javax.swing.JLabel();
        dcfecha = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        lblTC = new javax.swing.JLabel();
        btnTC = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblstcomprobantes = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                if (column == 0){
                    return true;
                }else{
                    return false;
                }
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("COMPROBANTES DE RETENCIÓN GENERADOS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

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

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Fecha");

        dcfecha.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TC");

        lblTC.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblTC.setText("0.000");

        btnTC.setText("...");
        btnTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTCActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/importcompra.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imprimir.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tblstcomprobantes.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstcomprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblstcomprobantes);

        jPanel3.setBackground(new java.awt.Color(255, 0, 15));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("EMITIR COMPROBANTES DE RETENCIÓN");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PAGOS REALIZADOS");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2)
                        .addGap(12, 12, 12)
                        .addComponent(dcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblTC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnTC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(255, 255, 255)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(dcfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCActionPerformed
        TipoCambio tc = new TipoCambio();
        Menu.dpane2.add(tc);
        SesionUsuario.lblTC=lblTC;
        centerJIF(tc);
        tc.otrofrm=1;
        tc.mifecha=fechaformateador(dcfecha.getDate());
        tc.ingresartc(fechaformateador(dcfecha.getDate()));
        tc.setVisible(true);
    }//GEN-LAST:event_btnTCActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        confirmarseleccionados();
        if (lstseleccionada.size() > 0) {
            nroclientes();
            LProgPagosCabecera lp = new LProgPagosCabecera();
            double tc1 = Double.valueOf(lblTC.getText().trim());
            if (tc1 == 0) {
                JOptionPane.showMessageDialog(this, "Ingrese el Tipo de Cambio.");
            } else {
                if (JOptionPane.showConfirmDialog(this, "Se van a generar los comprobantes de Retención. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    for (int i = 0; i < lstprov.size(); i++) {
                        lp.GenerarCodigo();
                        int val = 0;
                        int correlativo = lp.ObtenerCodigoGenerado();
                        for (int j = 0; j < lstseleccionada.size(); j++) {
                            if (lstprov.get(i).trim().equalsIgnoreCase(lstseleccionada.get(j).getDoc())) {
                                lstseleccionada.get(j).setCorrelativo(correlativo);
                                lstseleccionada.get(j).setFechaPago(fechaformateador(dcfecha.getDate()));
                                lstseleccionada.get(j).setTipoCambio(lblTC.getText());
                                val = val + 1;
                                if (val == 5) {
                                    lp.GenerarCodigo();
                                    correlativo = lp.ObtenerCodigoGenerado();
                                }
                                if (val == 10) {
                                    lp.GenerarCodigo();
                                    correlativo = lp.ObtenerCodigoGenerado();
                                }
                                lp.InsertarComprobReten(lstseleccionada.get(j));
                            }
                        }
                    }
                    llenardata(1);
                    llenartabcomprobantes();
                    JOptionPane.showMessageDialog(this, "Se generaron correctamente los Comprobantes de Retención");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar al menos un comprobante para generar la Retención.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int fila = tblstretencion.getSelectedRow();
        if (fila >= 0) {
            try {
                CReporteRetencion crr = new CReporteRetencion();
                crr.llamarreporteReten(lstcomprobreten.get(fila));
            } catch (JRException ex) {
                System.out.println(ex.getMessage());
                Logger.getLogger(InterFrmFormatearEmitir.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione Fila para generar reporte.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTC;
    private com.toedter.calendar.JDateChooser dcfecha;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTC;
    private javax.swing.JTable tblstcomprobantes;
    private javax.swing.JTable tblstretencion;
    // End of variables declaration//GEN-END:variables
}
