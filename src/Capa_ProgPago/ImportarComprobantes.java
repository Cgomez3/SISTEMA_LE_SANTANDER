/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EConsolidado;
import Capa_Entidades.ECuentaBancaria;
import Capa_Entidades.EDetProgramPagos;
import Capa_Entidades.EParametrosRetorno;
import Capa_Entidades.EProgPagosCabecera;
import Capa_Entidades.ETipoComprobante;
import Capa_Logica.LComprobante;
import Capa_Logica.LProgPagosCabecera;
import Capa_Logica.SesionUsuario;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.read.biff.BiffException;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import sun.security.krb5.internal.crypto.Des3;

/**
 *
 * @author HOME
 */
public class ImportarComprobantes extends javax.swing.JInternalFrame {

    public static String proveedoressincuenta = "";
    String estado = "SOLES";
    ArrayList<EComprobante> lstcomp = new ArrayList<>();
    String titulos1[] = {"Forma de Pago", "NroDocumento", "Tipo de Comprobante", "FechaEmisión", "FechaContable", "DocProv", "NomProveedor", "Moneda", "Referencia",
        "BaseSoles", "BaseDolares", "IMP. Soles", "IMP. Dolares", "TotalSoles", "TotalDolares", "TipoCambio",
        "Tipo Doc. Mod", "Nro Doc. Mod", "FechaDocMod"};

    String titulos2[] = {"Forma de Pago", "NroDocumento", "Tipo de Comprobante", "FechaEmisión", "FechaContable", "DocProv", "NomProveedor", "Moneda", "Referencia",
        "BaseSoles", "BaseDolares", "IMP. Soles", "IMP. Dolares", "TotalSoles", "TotalDolares", "Nro Cuenta Contable", "TipoCambio",
        "Tipo Doc. Mod", "Nro Doc. Mod", "FechaDocMod", "CodDetrac", "Tipo Bien Servicio", "Porcentaje", "Cta Cte Banco Nacion", "Banco", "Pago Detracción"};

    DefaultTableModel modTbdatos;
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    Object[] cboobj = new Object[6];
    ArrayList<EProgPagosCabecera> lstMAX = new ArrayList<>();
    ArrayList<EConsolidado> lstconsolidado = new ArrayList<>();
    ArrayList<EComprobante> arrayEComprobante = new ArrayList<>();
    public int codigomax = 0;

    /**
     * Creates new form ImportarComprobantes
     */
    public ImportarComprobantes() {
        initComponents();
        this.setTitle("Importar Comprobantes.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        chkNuevo.setSelected(true);

        if (chkNuevo.isEnabled()) {
            modTbdatos = new DefaultTableModel(titulos2, 0);
        } else {
            modTbdatos = new DefaultTableModel(titulos1, 0);
        }
        tblistacomprobantes.setModel(modTbdatos);

        tblistacomprobantes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblistacomprobantes.getTableHeader().setReorderingAllowed(false);
        cboobj[0] = "";
        cboobj[1] = "CHEQUE";
        cboobj[2] = "TRANSFERENCIA";
        cboobj[3] = "TRANS-REEMBOLSO";
        cboobj[4] = "RENDICION-CANCELADO";
        cboobj[5] = "SERVICIOS-CANCELADO";
        Font mifont = new Font("Calibri", Font.PLAIN, 11);
        JComboBox micombo = new JComboBox(cboobj);
        micombo.setBackground(Color.decode("#003399"));
        micombo.setForeground(Color.WHITE);
        micombo.setFont(mifont);
        micombo.setRenderer(new MyCellRenderer());
        tblistacomprobantes.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(micombo));
    }

    class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {

        public MyCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            return this;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private String mifecha(String cad) {
        String nfecha = "";
        nfecha = cad.substring(0, 2) + "-" + cad.substring(3, 5) + "-20" + cad.substring(6, 8);
        return nfecha;
    }

    private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        DecimalFormat formato2 = new DecimalFormat("#0.000", simbolo);
        modTbdatos.setRowCount(0);
        if (lstcomp.size() > 0) {
            for (int i = 0; i < lstcomp.size(); i++) {
                Object obj[] = new Object[19];
                obj[0] = "TRANSFERENCIA";
                obj[1] = lstcomp.get(i).getNumComprobante();
                obj[2] = lstcomp.get(i).getTipoComprobante();
                obj[3] = lstcomp.get(i).getFechaComprobante();
                obj[4] = lstcomp.get(i).getFechaContable();
                obj[5] = lstcomp.get(i).getDocProveedor();
                obj[6] = lstcomp.get(i).getNomProveedor();
                obj[7] = lstcomp.get(i).getNomMoneda();
                obj[8] = lstcomp.get(i).getReferencia();
                obj[9] = formato.format(lstcomp.get(i).getMontoBase());
                obj[10] = formato.format(lstcomp.get(i).getMontoBaseDOL());
                obj[11] = formato.format(lstcomp.get(i).getMontoIGV());
                obj[12] = formato.format(lstcomp.get(i).getMontoIGVDOL());
                obj[13] = formato.format(lstcomp.get(i).getMontoTotal());
                obj[14] = formato.format(lstcomp.get(i).getMontoTotalDOL());
                obj[15] = formato2.format(lstcomp.get(i).getTCComprobante());
                obj[16] = lstcomp.get(i).getTipoComprobanteRef();
                obj[17] = lstcomp.get(i).getComprobanteRef();
                obj[18] = lstcomp.get(i).getFechaRef();
                modTbdatos.addRow(obj);
            }
        }
        tblistacomprobantes.setModel(modTbdatos);
        lblnroregistros.setText(tblistacomprobantes.getRowCount() + " Comprobante(s)");
    }

    private void llenartablaNuevo() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        DecimalFormat formato2 = new DecimalFormat("#0.000", simbolo);
        modTbdatos.setRowCount(0);
        if (lstcomp.size() > 0) {
            for (int i = 0; i < lstcomp.size(); i++) {
                Object obj[] = new Object[27];
                System.out.println(lstcomp.get(i).getFechaComprobante());
                obj[0] = "TRANSFERENCIA";
                obj[1] = lstcomp.get(i).getNumComprobante();
                obj[2] = lstcomp.get(i).getTipoComprobante();
                obj[3] = lstcomp.get(i).getFechaComprobante();
                obj[4] = lstcomp.get(i).getFechaContable();
                obj[5] = lstcomp.get(i).getDocProveedor();
                obj[6] = lstcomp.get(i).getNomProveedor();
                obj[7] = lstcomp.get(i).getNomMoneda();
                obj[8] = lstcomp.get(i).getReferencia();
                obj[9] = formato.format(lstcomp.get(i).getMontoBase());
                obj[10] = formato.format(lstcomp.get(i).getMontoBaseDOL());
                obj[11] = formato.format(lstcomp.get(i).getMontoIGV());
                obj[12] = formato.format(lstcomp.get(i).getMontoIGVDOL());
                obj[13] = formato.format(lstcomp.get(i).getMontoTotal());
                obj[14] = formato.format(lstcomp.get(i).getMontoTotalDOL());
                obj[15] = lstcomp.get(i).getNroCuentaContable();
                obj[16] = formato2.format(lstcomp.get(i).getTCComprobante());
                obj[17] = lstcomp.get(i).getTipoComprobanteRef();
                obj[18] = lstcomp.get(i).getComprobanteRef();
                obj[19] = lstcomp.get(i).getFechaRef();
                obj[20] = lstcomp.get(i).getCodDetraccion();
                obj[21] = lstcomp.get(i).getDescripcionBienServicio();
                obj[22] = lstcomp.get(i).getPorcentaje();
                obj[23] = lstcomp.get(i).getCuentaCorrienteBNDetraccion();
                obj[24] = lstcomp.get(i).getBanco();
                obj[25] = lstcomp.get(i).getNroFacturaRelacionada();
                obj[26] = lstcomp.get(i).getComprobanteSujeto();
                modTbdatos.addRow(obj);
            }
        }
        tblistacomprobantes.setModel(modTbdatos);
        lblnroregistros.setText(tblistacomprobantes.getRowCount() + " Comprobante(s)");
    }

    public boolean esNumeroDecimal(String cad) {
        try {
            System.out.println(cad);
            Double.parseDouble(cad);
            return true;
        } catch (NumberFormatException nfe) {
            System.out.println(nfe.toString());
            return false;
        }
    }

    private void setearformapago() {
        for (int i = 0; i < lstcomp.size(); i++) {
            lstcomp.get(i).setFormaPago(tblistacomprobantes.getValueAt(i, 0).toString());
            System.out.println(lstcomp.get(i).getFormaPago());
        }
    }

    private int nrodecomprobantes() {
        int val = 0;
        LComprobante lc = new LComprobante();
        for (int i = 0; i < lstcomp.size(); i++) {
            if (lc.VerificarNroComprobante(lstcomp.get(i).getNumComprobante(), lstcomp.get(i).getDocProveedor()) == 1) {
                val = val + 1;
            }
        }
        return val;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgmoneda = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacomprobantes = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                if (column == 0){
                    return true;
                }else{
                    return false;
                }
            }
        };
        lblnroregistros = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        chkAnterior = new javax.swing.JCheckBox();
        chkNuevo = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txturl = new javax.swing.JTextField();
        btnimportar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        lblmoneda = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jPanel2.setBackground(new java.awt.Color(255, 0, 15));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPORTAR COMPROBANTES");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnguardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnsalir1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        btnsalir1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        tblistacomprobantes.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistacomprobantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblistacomprobantes);

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setText("Nro Registros");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        chkAnterior.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chkAnterior);
        chkAnterior.setText("Anterior");
        chkAnterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkAnteriorMouseClicked(evt);
            }
        });

        chkNuevo.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(chkNuevo);
        chkNuevo.setText("Nuevo");
        chkNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkNuevoMouseClicked(evt);
            }
        });

        jLabel3.setText("Formato");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(8, 8, 8)
                .addComponent(chkNuevo)
                .addGap(15, 15, 15)
                .addComponent(chkAnterior))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(chkNuevo)
                .addComponent(chkAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        txturl.setEditable(false);
        txturl.setBackground(new java.awt.Color(255, 255, 255));
        txturl.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        btnimportar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnimportar.setText("Importar");
        btnimportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnimportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txturl, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnimportar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txturl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnimportar)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblmoneda.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        lblmoneda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(lblmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblmoneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblnroregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnsalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblnroregistros)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:

        if (lstcomp.size() > 0) {
            setearformapago();
            if (nrodecomprobantes() > 0 && chkAnterior.isSelected() == true) {
                if (nrodecomprobantes() == lstcomp.size()) {
                    JOptionPane.showMessageDialog(this, "Todos los comprobantes seleccionados ya se encuentran registrados.");
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Algunos comprobantes ya estan grabados. Se van a GRABAR sólo los Comprobantes nuevos. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        LComprobante lc = new LComprobante();
                        for (int i = 0; i < lstcomp.size(); i++) {
                            lc.InsertarComprobanteImport(lstcomp.get(i));
                        }
                        JOptionPane.showMessageDialog(this, "Se GRABÓ correctamente los comprobantes.");
                    }
                }
            } else {
                String mensage = "";
                if (chkNuevo.isSelected()) {
                    mensage = "Se va a grabar Comprobantes y Programación de Pagos. ¿Desea Continuar?";
                } else {
                    mensage = "Se van a GRABAR los Comprobantes. ¿Desea Continuar?";
                }
                LComprobante lc = new LComprobante();
                LProgPagosCabecera ltc = new LProgPagosCabecera();
                String numeroDocu = "";
                String DocProveedor = "";
                String TipoComprobante = "";
                ArrayList<ECuentaBancaria> listaCuentaBancaria = new ArrayList<>();
                for (int i = 0; i < lstcomp.size(); i++) {
                    numeroDocu = lstcomp.get(i).getNumComprobante();
                    DocProveedor = lstcomp.get(i).getDocProveedor();
                    TipoComprobante = lstcomp.get(i).getTipoComprobante();
                    ECuentaBancaria objReturnCuentaBancaria = ltc.validarDocumentosProgramados(numeroDocu, DocProveedor, TipoComprobante);
                    if (objReturnCuentaBancaria != null) {
                        listaCuentaBancaria.add(objReturnCuentaBancaria);
                    }
                }

                System.out.println("cuenta bancaria " + listaCuentaBancaria.size());
                if (listaCuentaBancaria.size() > 0) {
                    proveedoressincuenta = "";
                    proveedoressincuenta += "<html><body width='500'><h4>Los siguientes documentos se encuentran Programados: </h4>";

                    for (int i = 0; i < listaCuentaBancaria.size(); i++) {
                        proveedoressincuenta += "<p>- " + listaCuentaBancaria.get(i).getmoneda() + ". " + listaCuentaBancaria.get(i).getnomProveedor() + ". RUC: " + listaCuentaBancaria.get(i).getDocProveedor() + ".<br>";

                    }
                    proveedoressincuenta += "</p></body></html>";
                    JOptionPane.showMessageDialog(this, proveedoressincuenta);
                    return;
                }

                ArrayList<EComprobante> listaNoExisteProveedor = new ArrayList<>();
                for (int i = 0; i < lstcomp.size(); i++) {
                    ArrayList<ECuentaBancaria> resultado = ltc.eliminarProgramacionDetalle(lstcomp.get(i).getNumComprobante(), 1, lstcomp.get(i).getDocProveedor(), lstcomp.get(i).getBanco(), lblmoneda.getText());

                    if (resultado == null || resultado.isEmpty()) {
                        listaNoExisteProveedor.add(lstcomp.get(i));
                    }
                }
                ArrayList<EComprobante> listaNoExisteCuenta = new ArrayList<>();
                ArrayList<EComprobante> listaNoExisteCuentaCI = new ArrayList<>();
                for (int i = 0; i < lstcomp.size(); i++) {
                    ArrayList<ECuentaBancaria> resultado = ltc.eliminarProgramacionDetalle(lstcomp.get(i).getNumComprobante(), 1, lstcomp.get(i).getDocProveedor(), lstcomp.get(i).getBanco(), lblmoneda.getText());

                    if (resultado != null || !resultado.isEmpty()) {
                        for (int k = 0; k < resultado.size(); k++) {
                            if (resultado.get(k).getNomBanco().equalsIgnoreCase(lstcomp.get(i).getBanco())) {
                                if (resultado.get(k).getCuenta().isEmpty() || resultado.get(k).getCuenta() == null) {
                                    listaNoExisteCuenta.add(lstcomp.get(i));
                                }
                            } else {
                                if (resultado.get(k).getCuentaci().isEmpty() || resultado.get(k).getCuentaci() == null) {
                                    listaNoExisteCuentaCI.add(lstcomp.get(i));
                                }
                            }
                        }
                    }
                }

                List listaLimpNoExisteProveedor = new ArrayList();
                List listaLimpNoExisteCuenta = new ArrayList();
                List listaLimpNoExisteCuentaCI = new ArrayList();
                Map<String, EComprobante> mapProvedorNoExiste = new HashMap<String, EComprobante>(listaNoExisteProveedor.size());
                Map<String, EComprobante> mapNoExisteCuenta = new HashMap<String, EComprobante>(listaNoExisteCuenta.size());
                Map<String, EComprobante> mapNoExisteCuentaCI = new HashMap<String, EComprobante>(listaNoExisteCuentaCI.size());

                List listacompro = new ArrayList<>();
                for (EComprobante d : listaNoExisteProveedor) {
                    mapProvedorNoExiste.put(d.getNomProveedor(), d);

                }
                for (EComprobante d : listaNoExisteCuenta) {
                    mapNoExisteCuenta.put(d.getDocProveedor(), d);
                }
                for (EComprobante d : listaNoExisteCuentaCI) {
                    mapNoExisteCuentaCI.put(d.getDocProveedor(), d);
                }

                for (Entry<String, EComprobante> p : mapProvedorNoExiste.entrySet()) {
                    listaLimpNoExisteProveedor.add(p.getValue());
                    listacompro.add(p.getValue().getNomProveedor() + ". RUC: " + p.getValue().getDocProveedor() + ".<br>");
                    proveedoressincuenta = proveedoressincuenta + "<p>- El Proveedor " + p.getValue().getNomProveedor() + ". RUC: " + p.getValue().getDocProveedor() + " No se encuentra Registrado y/ó no tiene cuenta en " + lblmoneda.getText() + ".<br>" + "  ";

                }
                for (Entry<String, EComprobante> p : mapNoExisteCuenta.entrySet()) {
                    listaLimpNoExisteCuenta.add(p.getValue());
                    listacompro.add(p.getValue().getNomProveedor() + ". RUC: " + p.getValue().getDocProveedor() + ".<br>");
                    proveedoressincuenta = proveedoressincuenta + "<p>- El Proveedor " + p.getValue().getNomProveedor() + ". RUC: " + p.getValue().getDocProveedor() + " no tiene Cuenta en Moneda " + lblmoneda.getText() + ".<br>" + "  ";

                }
                for (Entry<String, EComprobante> p : mapNoExisteCuentaCI.entrySet()) {
                    listaLimpNoExisteCuentaCI.add(p.getValue());
                    listacompro.add(p.getValue().getNomProveedor() + ". RUC: " + p.getValue().getDocProveedor() + ".<br>");
                    proveedoressincuenta = proveedoressincuenta + "<p>- El Proveedor " + p.getValue().getNomProveedor() + "  nro " + p.getValue().getDocProveedor() + " no tiene Cuenta CCI en Moneda " + lblmoneda.getText() + ".<br>" + "  ";

                }
                List lista = listacompro;
                Collections.sort(lista);
                showList(lista, lblmoneda.getText());
                if (listaLimpNoExisteProveedor.size() > 0 || listaLimpNoExisteCuenta.size() > 0 || listaLimpNoExisteCuentaCI.size() > 0) {
                    JOptionPane.showMessageDialog(this, proveedoressincuenta);
                    return;
                }
                if (JOptionPane.showConfirmDialog(this, mensage, "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {

                    for (int i = 0; i < lstcomp.size(); i++) {
                        if (chkNuevo.isSelected()) {
                            lc.InsertarComprobanteImportNew(lstcomp.get(i));
                        } else {
                            lc.InsertarComprobanteImport(lstcomp.get(i));
                        }
                    }

                    if (chkNuevo.isSelected()) {
                        String banco = "";
                        String moneda = "";
                        arrayEComprobante.removeAll(arrayEComprobante);
                        for (int i = 0; i < lstcomp.size(); i++) {
                            if (!VerificarSiExisteNombreBanco(lstcomp.get(i))) {
                                arrayEComprobante.add(lstcomp.get(i));
                            }
                        }

                        for (int i = 0; i < arrayEComprobante.size(); i++) {
                            banco += arrayEComprobante.get(i).getBanco() + "-";
                            moneda = arrayEComprobante.get(i).getNomMoneda();
                        }
                        String[] valor = banco.split("-");
                        LProgPagosCabecera lp = new LProgPagosCabecera();
                        for (int i = 0; i < valor.length; i++) {
                            EProgPagosCabecera objtc = new EProgPagosCabecera();
                            objtc.setBanco(valor[i]);
                            objtc.setMoneda(moneda);
                            objtc.setEstado_progpagos("PENDIENTE");
                            objtc.setUsucrea(SesionUsuario.misesion.getUsuario());
                            ltc.InsertarPrgPagos(objtc);
                            MaxCodigo();
                            for (int q = 0; q < lstcomp.size(); q++) {

                                if (valor[i].equalsIgnoreCase(lstcomp.get(q).getBanco())) {
                                    EDetProgramPagos obj = new EDetProgramPagos();
                                    obj.setCodprog(codigomax);
                                    obj.setNroDocumento(lstcomp.get(q).getNumComprobante());
                                    ArrayList<ECuentaBancaria> lstbanc = new ArrayList<>();
                                    lstbanc = lp.CtaBancaria(lstcomp.get(q).getDocProveedor(), moneda);
                                    if (lstbanc.size() > 0) {
                                        obj.setBanco(lstbanc.get(0).getNomBanco());
                                        obj.setMoneda(moneda);
                                        if (objtc.getBanco().equalsIgnoreCase(lstbanc.get(0).getNomBanco())) {
                                            obj.setCtaBancaria(lstbanc.get(0).getCuenta());
                                        } else {
                                            obj.setCtaBancaria(lstbanc.get(0).getCuentaci());
                                        }
                                    }

                                    obj.setEstado("PENDIENTE");
                                    obj.setUsuCrea(SesionUsuario.misesion.getUsuario());
                                    obj.setTccomprobante(lstcomp.get(q).getTipoComprobante());
                                    obj.setDocProveedor(lstcomp.get(q).getDocProveedor());
                                    System.out.println("DocProveedor " + lstcomp.get(q).getDocProveedor());
                                    System.out.println("TipoComprobante " + lstcomp.get(q).getTipoComprobante());
                                    ltc.insertarProgDetalleAutomatico(obj);
                                }

                            }

                            lstconsolidado = lstconsolidadofinal(ltc.lstConsolidado(codigomax));
                            for (int z = 0; z < lstconsolidado.size(); z++) {
                                ltc.insertarConsolidado(lstconsolidado.get(z));
                            }

                        }
                    }

                    lstcomp.removeAll(lstcomp);
                    if (chkAnterior.isSelected()) {
                        JOptionPane.showMessageDialog(this, "Se GRABÓ correctamente los comprobantes.");
                        llenartabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "Se Grabaron correctamente los Comprobantes y Programación respectiva.");

                        llenartablaNuevo();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Debe tener al menos un comprobante para importar.");
        }


    }//GEN-LAST:event_btnguardarActionPerformed
    private static void showList(List theList, String moneda) {
        int size = theList.size();
        proveedoressincuenta = "";
        proveedoressincuenta = "<html><body width='500'><h4>Los siguientes proveedores no se encuentran Registrados y/o No tienen Cuenta /CCI en " + moneda + ": </h4>";
        for (int i = 0; i < size; i++) {
            proveedoressincuenta += "<p>- " + theList.get(i);
        }
    }

    private boolean VerificarSiExisteNombreBanco(EComprobante objEntidad) {
        try {
            boolean rpta = false;

            for (int i = 0; i < arrayEComprobante.size(); i++) {
                if (objEntidad.getBanco().equals(arrayEComprobante.get(i).getBanco())) {
                    rpta = true;
                }
            }

            return rpta;
        } catch (Exception ex) {
            throw ex;
        }

    }

    
    private ArrayList<EConsolidado> lstconsolidadofinal(ArrayList<EConsolidado> cons) {
        ArrayList<EConsolidado> miconsolidado = new ArrayList();
        for (int i = 0; i < cons.size(); i++) {
            int val = 0;
            for (int j = 0; j < miconsolidado.size(); j++) {
                System.out.println(cons.get(i).getTipoComprob());
                if (miconsolidado.get(j).getDocProv().equalsIgnoreCase(cons.get(i).getDocProv()) && !cons.get(i).getTipoComprob().equalsIgnoreCase("NC")) {
                    val = 1;
                    miconsolidado.get(j).setMontosoles(miconsolidado.get(j).getMontosoles() + cons.get(i).getMontosoles());
                    miconsolidado.get(j).setMontodolares(miconsolidado.get(j).getMontodolares() + cons.get(i).getMontodolares());
                }
                if (miconsolidado.get(j).getDocProv().trim().equalsIgnoreCase(cons.get(i).getDocProv().trim()) && cons.get(i).getTipoComprob().equalsIgnoreCase("NC")) {
                    val = 2;
                    miconsolidado.get(j).setMontosoles(miconsolidado.get(j).getMontosoles() - cons.get(i).getMontosoles());
                    miconsolidado.get(j).setMontodolares(miconsolidado.get(j).getMontodolares() - cons.get(i).getMontodolares());
                }
            }
            if (val == 0) {
                if (cons.get(i).getTipoComprob().trim().equalsIgnoreCase("NC")) {
                    cons.get(i).setMontosoles(cons.get(i).getMontosoles() * -1);
                    cons.get(i).setMontodolares(cons.get(i).getMontodolares() * -1);
                    miconsolidado.add(cons.get(i));
                } else {
                    miconsolidado.add(cons.get(i));
                }
            }
        }
        return miconsolidado;
    }

    public void MaxCodigo() {
        LProgPagosCabecera limp = new LProgPagosCabecera();
        lstMAX = limp.MaxCodigo();
        for (int i = 0; i < lstMAX.size(); i++) {
            codigomax = (lstMAX.get(i).getCod_programacion());
            System.out.println(codigomax);
        }
    }
    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        // TODO add your handling code here:
        this.dispose();

    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void btnimportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnimportarActionPerformed
        // TODO add your handling code here:
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        String ruta = "";
        if (chkNuevo.isSelected()) {
            try {
                if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ruta = jF1.getSelectedFile().getAbsolutePath();
                    System.out.println(ruta);
                    txturl.setText(ruta);
                    WorkbookSettings wbSettings = new WorkbookSettings();
                    wbSettings.setEncoding("ISO-8859-1");
                    wbSettings.setLocale(new Locale("es", "ES"));
                    wbSettings.setExcelDisplayLanguage("ES");
                    wbSettings.setExcelRegionalSettings("ES");
                    wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                    Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                    Sheet sheet = workbook.getSheet(0);
                    
                    if (ValidarNroColumnas(sheet) != 28) {
                        JOptionPane.showMessageDialog(this, "El formato del archivo de Importación es Incorrecto.");
                        txturl.setText("");
                        return;
                    }

                    lstcomp.removeAll(lstcomp);
                    if (esNumeroDecimal(sheet.getCell(9, 1).getContents().toString().replace(".", "").replace(",", "."))) {
                        if (sheet.getCell(7, 1).getContents().toString().equalsIgnoreCase("US DOLLAR")) {
                            lblmoneda.setText("DOLARES");
                            estado = "DOLARES";
                        } else {
                            lblmoneda.setText("SOLES");
                            estado = "SOLES";
                        }

                        EParametrosRetorno objTempSheet = ValidarMoneda(sheet, sheet.getCell(7, 1).getContents());
                        if (objTempSheet != null) {
                            JOptionPane.showMessageDialog(this, "La fila con Nro.Diario: " + objTempSheet.getNroDiario() + " tiene diferente tipo de moneda.");
                            txturl.setText("");
                            return;
                        }

                        for (int fila = 1; fila < sheet.getRows(); fila++) {

                            if (sheet.getCell(1, fila).getContents().toString().trim().length() > 0) {

                                EComprobante ecom = new EComprobante();
                                ecom.setFechaContable(FormatearFecha(sheet.getCell(1, fila).getContents().toString()));
                                ecom.setFechaComprobante(FormatearFecha(sheet.getCell(2, fila).getContents().toString()));
                                String tipo = "";
                                LProgPagosCabecera ltc = new LProgPagosCabecera();
                                ArrayList<ETipoComprobante>lista =ltc.BuscarTipoComprobante(sheet.getCell(3, fila).getContents().toString());
                                if(lista.size() > 0){
                                   tipo = lista.get(0).getCodComprobante();
                                }else{
                                JOptionPane.showMessageDialog(this, "El Tipo de Documento " + sheet.getCell(3, fila).getContents().toString() + " no se encuentra registrado en BD.");
                                return;
                                }
                                
                                /*switch (sheet.getCell(3, fila).getContents().toString()) {
                                    case "01":
                                        tipo = "FT";
                                        break;
                                    case "02":
                                        tipo = "RH";
                                        break;
                                    case "03":
                                        tipo = "BV";
                                        break;
                                    case "05":
                                        tipo = "BA";
                                        break;
                                    case "07":
                                        tipo = "NC";
                                        break;
                                    case "12":
                                        tipo = "TC";
                                        break;
                                    case "14":
                                        tipo = "RS";
                                        break;
                                    case "54":
                                        tipo = "LC";
                                        break;
                                    case "00":
                                        tipo = "NT";
                                        break;
                                }*/
                                ecom.setTipoComprobante(tipo);
                                ecom.setNumComprobante(sheet.getCell(4, fila).getContents().toString());
                                ecom.setDocProveedor(sheet.getCell(5, fila).getContents().toString());
                                ecom.setNomProveedor(sheet.getCell(6, fila).getContents().toString());
                                ecom.setNomMoneda(estado);
                                ecom.setReferencia(sheet.getCell(8, fila).getContents().toString());

                                ecom.setMontoBase(Double.valueOf(sheet.getCell(9, fila).getContents().toString().replace(".", "").replace(",", ".").replace("(", "").replace(")", "") ));

                                ecom.setMontoBaseDOL(Double.valueOf(sheet.getCell(10, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));

                                ecom.setMontoIGV(Double.valueOf(sheet.getCell(11, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));
                                
                                ecom.setMontoIGVDOL(Double.valueOf(sheet.getCell(12, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));

                                if (tipo.equalsIgnoreCase("NC")) {
                                    ecom.setMontoTotal(Double.valueOf(sheet.getCell(13, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));

                                    ecom.setMontoTotalDOL(Double.valueOf(sheet.getCell(15, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));

                                } else {
                                    ecom.setMontoTotal(Double.valueOf(sheet.getCell(14, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));

                                    ecom.setMontoTotalDOL(Double.valueOf(sheet.getCell(16, fila).getContents().toString().replace(".", "").replace("(", "").replace(")", "") .replace(",", ".")));

                                }
                                ecom.setNroCuentaContable(sheet.getCell(17, fila).getContents().toString());
                                if (estado.equalsIgnoreCase("SOLES")) {
                                    ecom.setTCComprobante(0.00);
                                    ecom.setMontoBaseDOL(0.00);
                                    ecom.setMontoTotalDOL(0.00);
                                    ecom.setMontoIGVDOL(0.00);
                                } else {
                                    ecom.setTCComprobante(Double.valueOf(sheet.getCell(18, fila).getContents().toString().replace(".", "").replace(",", ".")));

                                }
                                if (tipo.equalsIgnoreCase("NC")) {
                                    String tipoRef = "";
                               ArrayList<ETipoComprobante>listanc =ltc.BuscarTipoComprobante(sheet.getCell(19, fila).getContents().toString());
                                if(listanc.size() > 0){
                                   tipo = listanc.get(0).getCodComprobante();
                                }else{
                                    JOptionPane.showMessageDialog(this, "El Tipo de Documento " + sheet.getCell(19, fila).getContents().toString() + " no se encuentra registrado en BD.");
                                return;
                                }
                                    /*switch (sheet.getCell(19, fila).getContents().toString()) {
                                        case "01":
                                            tipoRef = "FT";
                                            break;
                                        case "02":
                                            tipoRef = "RH";
                                            break;
                                        case "03":
                                            tipoRef = "BV";
                                            break;
                                        case "05":
                                            tipoRef = "BA";
                                            break;
                                        case "07":
                                            tipoRef = "NC";
                                            break;
                                        case "12":
                                            tipoRef = "TC";
                                            break;
                                        case "14":
                                            tipoRef = "RS";
                                            break;
                                        case "54":
                                            tipoRef = "LC";
                                            break;
                                        case "00":
                                            tipoRef = "NT";
                                            break;
                                    }*/
                                    System.out.println("comprobante referencia");
                                    System.out.println(tipoRef);
                                    
                                    ecom.setTipoComprobanteRef(tipoRef);
                                    ecom.setComprobanteRef(sheet.getCell(20, fila).getContents().toString());
                                    ecom.setFechaRef(FormatearFecha(sheet.getCell(21, fila).getContents().toString()));
                                } else {
                                    ecom.setTipoComprobanteRef("");
                                    ecom.setComprobanteRef("");
                                    ecom.setFechaRef("");
                                }

                                if (sheet.getCell(22, fila).getContents().toString().trim().length() == 0) {
                                    ecom.setCodDetraccion("0");
                                } else {
                                    ecom.setCodDetraccion(sheet.getCell(22, fila).getContents().toString());
                                }

                                ecom.setDescripcionBienServicio(sheet.getCell(23, fila).getContents().toString());
                                if (sheet.getCell(24, fila).getContents().toString().trim().length() == 0) {
                                    ecom.setPorcentaje(0);
                                } else {
                                    ecom.setPorcentaje(Double.valueOf(sheet.getCell(24, fila).getContents().toString()));
                                }
                                ecom.setCuentaCorrienteBNDetraccion(sheet.getCell(25, fila).getContents().toString());
                                ecom.setBanco(sheet.getCell(26, fila).getContents().toString());
                                ecom.setNroFacturaRelacionada(sheet.getCell(27, fila).getContents().toString());
                                if (sheet.getCell(27, fila).getContents().toString().length() > 0 && sheet.getCell(27, fila).getContents().toString().equalsIgnoreCase("SI")) {
                                    ecom.setFlagDetraccion(1);
                                } else {
                                    ecom.setFlagDetraccion(0);
                                }
                                lstcomp.add(ecom);
                            }
                        }
                        llenartablaNuevo();
                    } else {
                        JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
                    }
                }
            } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
                System.out.println(ex);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        }
        if (chkAnterior.isSelected()) {
            try {
                if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ruta = jF1.getSelectedFile().getAbsolutePath();
                    System.out.println(ruta);
                    txturl.setText(ruta);
                    WorkbookSettings wbSettings = new WorkbookSettings();
                    wbSettings.setEncoding("ISO-8859-1");
                    wbSettings.setLocale(new Locale("es", "ES"));
                    wbSettings.setExcelDisplayLanguage("ES");
                    wbSettings.setExcelRegionalSettings("ES");
                    wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                    Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                    Sheet sheet = workbook.getSheet(0);
                    if (ValidarNroColumnas(sheet) != 22) {
                        JOptionPane.showMessageDialog(this, "El formato es Incorrecto.");
                        txturl.setText("");
                        return;
                    }

                    lstcomp.removeAll(lstcomp);
                    if (esNumeroDecimal(sheet.getCell(9, 1).getContents().replace(".", "").replace(",", "."))) {
                        if (sheet.getCell(7, 1).getContents().equalsIgnoreCase("US DOLLAR")) {
                            lblmoneda.setText("DOLARES");
                            estado = "DOLARES";
                        } else {
                            lblmoneda.setText("SOLES");
                            estado = "SOLES";
                        }
                        EParametrosRetorno objTempSheet = ValidarMoneda(sheet, sheet.getCell(7, 1).getContents());
                        if (objTempSheet != null) {
                            JOptionPane.showMessageDialog(this, "La fila con Nro.Diario: " + objTempSheet.getNroDiario() + " tiene diferente tipo de moneda.");
                            txturl.setText("");
                            return;
                        }
                        for (int fila = 1; fila < sheet.getRows(); fila++) {

                            if (sheet.getCell(1, fila).getContents().trim().length() > 0) {
                                EComprobante ecom = new EComprobante();
                                ecom.setFechaContable(FormatearFecha(sheet.getCell(1, fila).getContents().toString()));
                                ecom.setFechaComprobante(FormatearFecha(sheet.getCell(2, fila).getContents().toString()));

                                String tipo = "";
                                switch (sheet.getCell(3, fila).getContents().toString()) {
                                    case "01":
                                        tipo = "FT";
                                        break;
                                    case "02":
                                        tipo = "RH";
                                        break;
                                    case "03":
                                        tipo = "BV";
                                        break;
                                    case "07":
                                        tipo = "NC";
                                        break;
                                    case "12":
                                        tipo = "TC";
                                        break;
                                    case "14":
                                        tipo = "RS";
                                        break;
                                    case "54":
                                        tipo = "LC";
                                        break;
                                }
                                ecom.setTipoComprobante(tipo);
                                ecom.setNumComprobante(sheet.getCell(4, fila).getContents().toString());
                                ecom.setDocProveedor(sheet.getCell(5, fila).getContents().toString());
                                ecom.setNomProveedor(sheet.getCell(6, fila).getContents().toString());
                                ecom.setNomMoneda(estado);
                                ecom.setReferencia(sheet.getCell(8, fila).getContents().toString());
                                ecom.setMontoBase(Double.valueOf(sheet.getCell(9, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                ecom.setMontoBaseDOL(Double.valueOf(sheet.getCell(10, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                ecom.setMontoIGV(Double.valueOf(sheet.getCell(11, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                ecom.setMontoIGVDOL(Double.valueOf(sheet.getCell(12, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                if (tipo.equalsIgnoreCase("NC")) {
                                    ecom.setMontoTotal(Double.valueOf(sheet.getCell(13, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                    ecom.setMontoTotalDOL(Double.valueOf(sheet.getCell(15, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                } else {
                                    ecom.setMontoTotal(Double.valueOf(sheet.getCell(14, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                    ecom.setMontoTotalDOL(Double.valueOf(sheet.getCell(16, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                }

                                if (estado.equalsIgnoreCase("SOLES")) {
                                    ecom.setTCComprobante(0.00);
                                    ecom.setMontoBaseDOL(0.00);
                                    ecom.setMontoTotalDOL(0.00);
                                    ecom.setMontoIGVDOL(0.00);
                                } else {
                                    ecom.setTCComprobante(Double.valueOf(sheet.getCell(18, fila).getContents().toString().replace(".", "").replace(",", ".")));
                                }
                                if (tipo.equalsIgnoreCase("NC")) {
                                    String tipoRef = "";
                                    switch (sheet.getCell(19, fila).getContents().toString()) {
                                        case "01":
                                            tipoRef = "FT";
                                            break;
                                        case "02":
                                            tipoRef = "RH";
                                            break;
                                        case "03":
                                            tipoRef = "BV";
                                            break;
                                        case "07":
                                            tipoRef = "NC";
                                            break;
                                        case "12":
                                            tipoRef = "TC";
                                            break;
                                        case "14":
                                            tipoRef = "RS";
                                            break;
                                        case "54":
                                            tipoRef = "LC";
                                            break;
                                    }
                                    ecom.setTipoComprobanteRef(tipoRef);
                                    ecom.setComprobanteRef(sheet.getCell(20, fila).getContents().toString());
                                    ecom.setFechaRef(FormatearFecha(sheet.getCell(21, fila).getContents().toString()));
                                } else {
                                    ecom.setTipoComprobanteRef("");
                                    ecom.setComprobanteRef("");
                                    ecom.setFechaRef("");
                                }

                                lstcomp.add(ecom);
                            }
                        }
                        llenartabla();
                    } else {
                        JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.1");
                    }
                }
            } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.2");
                System.out.println(ex);
            }
        }


    }//GEN-LAST:event_btnimportarActionPerformed
    private String FormatearFecha(String fecha) {
        String FechaImp = "";
        if (fecha.trim().length() > 0) {
            int dia = Integer.valueOf(fecha.split("/")[0]);
            int mes = Integer.valueOf(fecha.split("/")[1]);
            String anio = fecha.split("/")[2];
            System.out.println(anio);
            System.out.println(fecha);
            int year = 0;
            if (anio.trim().length() == 2) {
                Calendar cal = Calendar.getInstance();
                year =Integer.parseInt("20"+ anio);
            } else {
                year = Integer.valueOf(anio);
            }
            FechaImp = String.format("%02d", dia) + "/" + String.format("%02d", mes) + "/" + String.valueOf(year);
        }
        return FechaImp;
    }

    private int ValidarNroColumnas(Sheet objSheet) {
        int valor = 0;
        for (int i = 0; i < objSheet.getColumns(); i++) {
            if (!objSheet.getCell(i, 0).getContents().isEmpty()) {
                valor += 1;
            }
        }
        return valor;
    }

    private EParametrosRetorno ValidarMoneda(Sheet objSheet, String moneda) {
        EParametrosRetorno objRetorno = null;
        for (int fila = 1; fila < objSheet.getRows(); fila++) {
            if (objSheet.getCell(1, fila).getContents().trim().length() > 0) {
                if (!objSheet.getCell(7, fila).getContents().equalsIgnoreCase(moneda)) {
                    objRetorno = new EParametrosRetorno();
                    objRetorno.setNroDiario(objSheet.getCell(0, fila).getContents());
                    objRetorno.setNroDocumento(objSheet.getCell(4, fila).getContents());
                    objRetorno.setRuc(objSheet.getCell(5, fila).getContents());
                    objRetorno.setNombreProveedor(objSheet.getCell(6, fila).getContents());
                    objRetorno.setModeda(objSheet.getCell(7, fila).getContents());
                    break;
                }
            } else {
                break;
            }

        }
        return objRetorno;
    }

    private void chkAnteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkAnteriorMouseClicked
        // TODO add your handling code here:
        modTbdatos = new DefaultTableModel(titulos1, 0);
        tblistacomprobantes.setModel(modTbdatos);
        txturl.setText("");
    }//GEN-LAST:event_chkAnteriorMouseClicked

    private void chkNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkNuevoMouseClicked
        // TODO add your handling code here:
        modTbdatos = new DefaultTableModel(titulos2, 0);
        tblistacomprobantes.setModel(modTbdatos);
        txturl.setText("");
    }//GEN-LAST:event_chkNuevoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgmoneda;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnimportar;
    private javax.swing.JButton btnsalir1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkAnterior;
    private javax.swing.JCheckBox chkNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblmoneda;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JTable tblistacomprobantes;
    private javax.swing.JTextField txturl;
    // End of variables declaration//GEN-END:variables
}
