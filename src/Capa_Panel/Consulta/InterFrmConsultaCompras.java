/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Consulta;

import Capa_Entidades.EDataCompra;
import Capa_Entidades.EDataConsultaCompras;
import Capa_Entidades.EDataFormatCompras;
import Capa_Entidades.EPeriodo;
import Capa_Logica.LImportar;
import Capa_Logica.RenderDerecha;
import Capa_Panel.Consulta.Reportes.CLlamarReporte;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
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
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmConsultaCompras extends javax.swing.JInternalFrame {

    ArrayList<EPeriodo> lstperiodo = new ArrayList<>();
    ArrayList<EDataConsultaCompras> lstconscompras = new ArrayList<>();
    ArrayList<EDataCompra> lstcompra = new ArrayList<>();
    ArrayList<EDataFormatCompras> lstformat = new ArrayList<>();
    String titulos[] = {"Código", "Período", "Fecha", "Registrados", "Formateados", "Errados", "FechaExportación", "Exportados"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    String titulos2[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento", "Tipo de Comprobante de Pago o Doc",
        "Serie Codigo de Dependencia Aduanera", "Año de Emisión de DUA o DSI", "Documentos Emitidos por Sunat para",
        "Tipo Doc. Identidad Prov.", "Nro. Doc. Identidad Prov.", "Razon Social Proveedor", "Adquisicion Gravadas Base Imponible",
        "Adquisicion Gravadas IGV", "Gravadas y No Gravadas Base Imponible", "Gravadas y No Gravadas IGV", "No Gravadas Base Imponible",
        "No Gravadas IGV", "Adquisiciones No Gravadas", "ISC", "Otros Tributos y Cargos", "Importe Total", "Nro Comprobante No Domiciliado",
        "Nro Constancia de Detracción", "Fecha de Emisión de Constancia de Detracción", "Tipo de Cambio",
        "Comprobante que se Modifica Fecha", "Comprobante que se Modifica Tipo", "Comprobante que se Modifica Serie",
        "Nro de Comprobante de Pago o Doc"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    String titulos3[] = {"1-Periodo", "2-CUO", "3-MovCUO", "4-FechaEmisión", "5-FechaVencimiento", "6-TipoComprob", "7-SerieComprob",
        "8-AñoDUA", "9-NroComprob", "10-MontoConsolOPsinIGV", "11-TipoDocIden", "12-NroDoc", "13-NomRazonSocial", "14-BaseImpGrav", "15-MontoIGV",
        "16-BaseImpAdqGrav", "17-MontoIGV", "18-BaseImpAdqNoGrav", "19-MontoIGVNG", "20-ValorAdqNG", "21-MontoISC", "22-OtrosMontos", "23-ImporteTotalAdq",
        "24-TipoCambio", "25-FechaEmiComprobRef", "26-TipoComprobRef", "27-SerieComprobRef", "28-CodDUA", "29-NroComprobRef",
        "30-NroComprobNoDomicil", "31-FechaConstDetracc", "32-NroConstDetracc", "33-MarcaComprobSujReten", "34-EstadoOperación"};
    DefaultTableModel modTbdatos3 = new DefaultTableModel(titulos3, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    
    /**
     * Creates new form InterFrmConsultaCompras
     */
    
    public InterFrmConsultaCompras() {
        initComponents();
        this.setTitle("Consulta Registro de Compras");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        llenarperiodo();
        llenarrango();
        tblstcab.setModel(modTbdatos);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblstdata.setModel(modTbdatos2);
        tblstcab.getTableHeader().setReorderingAllowed(false);
        tblstformateada.setModel(modTbdatos3);
        tblstdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstformateada.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstformateada.getTableHeader().setReorderingAllowed(false);
        tblstdata.getTableHeader().setReorderingAllowed(false);
        tblstdata.getColumnModel().getColumn(10).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(11).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(18).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(19).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(17).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(18).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(19).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(20).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(21).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(22).setCellRenderer(new RenderDerecha());
        tblstformateada.getColumnModel().getColumn(23).setCellRenderer(new RenderDerecha());
        rbtperiodo.setSelected(true);
        btnReporte.setVisible(false);
    }

    
    private void llenarperiodo() {
        LImportar limp = new LImportar();
        lstperiodo = limp.ListaPeriodoCompra();
        cboperiodo.removeAllItems();
        cboperiodo.addItem("<Seleccione>");
        for (int i = 0; i < lstperiodo.size(); i++) {
            cboperiodo.addItem(lstperiodo.get(i).getMesChar() + " - " + lstperiodo.get(i).getAño());
        }
    }
    
    private void llenarrango() {
        LImportar limp = new LImportar();
        lstperiodo = limp.ListaPeriodoCompra();
        cbofin.removeAllItems();
        cboinicio.removeAllItems();
        cboinicio.addItem("<Seleccione>");
        cbofin.addItem("<Seleccione>");
        for (int i = 0; i < lstperiodo.size(); i++) {
            cboinicio.addItem(lstperiodo.get(i).getMesChar() + " - " + lstperiodo.get(i).getAño());
            cbofin.addItem(lstperiodo.get(i).getMesChar() + " - " + lstperiodo.get(i).getAño());
        }
    }
    
    private void llenartablacab() {
        modTbdatos.setRowCount(0);
        if (lstconscompras.size() > 0) {
            for (int i = 0; i < lstconscompras.size(); i++) {
                Object obj[] = new Object[8];
                obj[0] = lstconscompras.get(i).getCodigo();
                obj[1] = lstconscompras.get(i).getPeriodo();
                obj[2] = lstconscompras.get(i).getFecha();
                obj[3] = lstconscompras.get(i).getRegistrados();
                obj[4] = lstconscompras.get(i).getFormateados();
                obj[5] = lstconscompras.get(i).getRegistrados()-lstconscompras.get(i).getFormateados();
                obj[6] = lstconscompras.get(i).getFechaExportacion();
                obj[7] = lstconscompras.get(i).getExportados();
                modTbdatos.addRow(obj);
            }
        }
        tblstcab.setModel(modTbdatos);
    }
    
    private void llenartablaformato() {
        modTbdatos3.setRowCount(0);
        if (lstformat.size() > 0) {
            for (int i = 0; i < lstformat.size(); i++) {
                Object obj[] = new Object[34];
                obj[0] = lstformat.get(i).getCampo1();
                obj[1] = lstformat.get(i).getCampo2();
                obj[2] = lstformat.get(i).getCampo3();
                obj[3] = lstformat.get(i).getCampo4();
                obj[4] = lstformat.get(i).getCampo5();
                obj[5] = lstformat.get(i).getCampo6();
                obj[6] = lstformat.get(i).getCampo7();
                obj[7] = lstformat.get(i).getCampo8();
                obj[8] = lstformat.get(i).getCampo9();
                obj[9] = lstformat.get(i).getCampo10();
                obj[10] = lstformat.get(i).getCampo11();
                obj[11] = lstformat.get(i).getCampo12();
                obj[12] = lstformat.get(i).getCampo13();
                obj[13] = lstformat.get(i).getCampo14();
                obj[14] = lstformat.get(i).getCampo15();
                obj[15] = lstformat.get(i).getCampo16();
                obj[16] = lstformat.get(i).getCampo17();
                obj[17] = lstformat.get(i).getCampo18();
                obj[18] = lstformat.get(i).getCampo19();
                obj[19] = lstformat.get(i).getCampo20();
                obj[20] = lstformat.get(i).getCampo21();
                obj[21] = lstformat.get(i).getCampo22();
                obj[22] = lstformat.get(i).getCampo23();
                obj[23] = lstformat.get(i).getCampo24();
                obj[24] = lstformat.get(i).getCampo25();
                obj[25] = lstformat.get(i).getCampo26();
                obj[26] = lstformat.get(i).getCampo27();
                obj[27] = lstformat.get(i).getCampo28();
                obj[28] = lstformat.get(i).getCampo29();
                obj[29] = lstformat.get(i).getCampo30();
                obj[30] = lstformat.get(i).getCampo31();
                obj[31] = lstformat.get(i).getCampo32();
                obj[32] = lstformat.get(i).getCampo33();
                obj[33] = lstformat.get(i).getCampo34();
                modTbdatos3.addRow(obj);
            }
        }
        tblstformateada.setModel(modTbdatos3);
        lblnroformateados.setText("Formateados: "+tblstformateada.getRowCount());
    }
    
    private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos2.setRowCount(0);
        if (lstcompra.size() > 0) {
            for (int i = 0; i < lstcompra.size(); i++) {
                Object obj[] = new Object[28];
                obj[0] = lstcompra.get(i).getFila();
                obj[1] = lstcompra.get(i).getFechaEmision();
                obj[2] = lstcompra.get(i).getFechaVencimiento();
                obj[3] = lstcompra.get(i).getTipoComprobantePago();
                obj[4] = lstcompra.get(i).getSerieCodDepenAduanera();
                obj[5] = lstcompra.get(i).getAñoEmisionDua();
                obj[6] = lstcompra.get(i).getDocumentosEmitidos();
                obj[7] = lstcompra.get(i).getTipoDocuIdenProv();
                obj[8] = lstcompra.get(i).getNroDocIdentProv();
                obj[9] = lstcompra.get(i).getRazonSocialProv();
                obj[10] = formato.format(Double.parseDouble(lstcompra.get(i).getGOBaseDisponible()));
                obj[11] = formato.format(Double.parseDouble(lstcompra.get(i).getGOIGV()));
                obj[12] = formato.format(Double.parseDouble(lstcompra.get(i).getGYGBaseDisponible()));
                obj[13] = formato.format(Double.parseDouble(lstcompra.get(i).getGYGIGV()));
                obj[14] = formato.format(Double.parseDouble(lstcompra.get(i).getNGBaseDisponible()));
                obj[15] = formato.format(Double.parseDouble(lstcompra.get(i).getNGIGV()));
                obj[16] = formato.format(Double.parseDouble(lstcompra.get(i).getAdquisionesNG()));
                obj[17] = lstcompra.get(i).getISC();
                obj[18] = formato.format(Double.parseDouble(lstcompra.get(i).getOtrosTribyCarg()));
                obj[19] = formato.format(Double.parseDouble(lstcompra.get(i).getImporteTotal()));
                obj[20] = lstcompra.get(i).getNComprNoDomic();
                obj[21] = lstcompra.get(i).getNroConstanciaDetraccion();
                obj[22] = lstcompra.get(i).getFechaEmisionConsDetracc();
                obj[23] = lstcompra.get(i).getTipoDeCambio();
                obj[24] = lstcompra.get(i).getCMFecha();
                obj[25] = lstcompra.get(i).getCMTipo();
                obj[26] = lstcompra.get(i).getCMSerie();
                obj[27] = lstcompra.get(i).getCMNCPagoDoc();
                modTbdatos2.addRow(obj);
            }
        }
        tblstdata.setModel(modTbdatos2);
        lblnroregistrados.setText("Registrados: "+tblstdata.getRowCount());
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
        rbtrango = new javax.swing.JRadioButton();
        rbtperiodo = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        cbofin = new javax.swing.JComboBox();
        cboperiodo = new javax.swing.JComboBox();
        cboinicio = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnReporte = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstcab = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblstdata = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblstformateada = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistrados = new javax.swing.JLabel();
        lblnroformateados = new javax.swing.JLabel();
        btnExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE REGISTRO DE COMPRAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1000, -1));

        rbtrango.setBackground(new java.awt.Color(255, 255, 255));
        bgfiltro.add(rbtrango);
        rbtrango.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbtrango.setText("Rango de Períodos");
        rbtrango.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtrangoItemStateChanged(evt);
            }
        });

        rbtperiodo.setBackground(new java.awt.Color(255, 255, 255));
        bgfiltro.add(rbtperiodo);
        rbtperiodo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbtperiodo.setText("Período");
        rbtperiodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbtperiodoItemStateChanged(evt);
            }
        });
        rbtperiodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtperiodoActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N

        cbofin.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbofin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));

        cboperiodo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboperiodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        cboperiodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboperiodoActionPerformed(evt);
            }
        });

        cboinicio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboinicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel3.setText("Fin");

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setText("Período");

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setText("Inicio");

        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/imprimir.png"))); // NOI18N
        btnReporte.setPreferredSize(new java.awt.Dimension(79, 35));
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tblstcab.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstcab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblstcab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblstcabMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblstcab);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblstdata.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstdata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblstdata);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1015, 259));

        jTabbedPane1.addTab("Data Importada", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblstformateada.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstformateada.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblstformateada);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1015, 259));

        jTabbedPane1.addTab("Formato de SUNAT", jPanel3);

        lblnroregistrados.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnroregistrados.setText("Registrados: 0");

        lblnroformateados.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnroformateados.setText("Formateados: 0");

        btnExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excelmini.png"))); // NOI18N
        btnExcel.setPreferredSize(new java.awt.Dimension(79, 35));
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbtperiodo)
                                            .addComponent(rbtrango))))
                                .addGap(60, 60, 60)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboinicio, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3)
                                .addGap(26, 26, 26)
                                .addComponent(cbofin, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(1, 1, 1)
                                .addComponent(btnsalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 2, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblnroregistrados, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(lblnroformateados, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(rbtperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(rbtrango))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel4)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(cboperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(cboinicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(cbofin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnsalir1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblnroregistrados)
                    .addComponent(lblnroformateados)
                    .addComponent(btnExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void limpiar(){
        lstcompra.removeAll(lstcompra);
        lstformat.removeAll(lstformat);
        llenartabla();
        llenartablaformato();
    }
    
    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:
        if (rbtperiodo.isSelected()) {
            if (cboperiodo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione período para generar reporte.");
                cboperiodo.requestFocus();
            } else {
                String periodo = "01-" + lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getMesChar() + "-" + lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getAño();
                CLlamarReporte cl = new CLlamarReporte();
                try {
                    cl.llamarreporteProv(periodo, periodo);
                } catch (JRException ex) {
                    System.out.println(ex.getMessage());
                    Logger.getLogger(InterFrmConsultaCompras.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            if (cboinicio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione período de inicio.");
                cboinicio.requestFocus();
            } else {
                if (cboinicio.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Seleccione período de fin.");
                    cbofin.requestFocus();
                } else {
                    String periodo1 = "01-" + lstperiodo.get(cboinicio.getSelectedIndex() - 1).getMesChar() + "-" + lstperiodo.get(cboinicio.getSelectedIndex() - 1).getAño();
                    String periodo2 = "01-" + lstperiodo.get(cbofin.getSelectedIndex() - 1).getMesChar() + "-" + lstperiodo.get(cbofin.getSelectedIndex() - 1).getAño();
                    CLlamarReporte cl = new CLlamarReporte();
                    try {
                        cl.llamarreporteProv(periodo1, periodo2);
                    } catch (JRException ex) {
                        System.out.println(ex.getMessage());
                        Logger.getLogger(InterFrmConsultaCompras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        if (rbtperiodo.isSelected()) {
            if (cboperiodo.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione Período.");
                cboperiodo.requestFocus();
            } else {
                LImportar lim = new LImportar();
                lstconscompras = lim.ConsultaCompras(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                llenartablacab();
                limpiar();
                if (tblstcab.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                }
            }
        } else {
            if (cboinicio.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione Período de Inicio.");
                cboinicio.requestFocus();
            } else {
                if (cbofin.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Seleccione Período de Fin.");
                    cbofin.requestFocus();
                } else {
                    String fecini = "";
                    String fecfin = "";
                    fecini = "01-" + lstperiodo.get(cboinicio.getSelectedIndex() - 1).getMesChar() + "-" + lstperiodo.get(cboinicio.getSelectedIndex() - 1).getAño();
                    fecfin = "01-" + lstperiodo.get(cbofin.getSelectedIndex() - 1).getMesChar() + "-" + lstperiodo.get(cbofin.getSelectedIndex() - 1).getAño();
                    LImportar lim = new LImportar();
                    lstconscompras = lim.ConsultaRangCompras(fecini, fecfin);
                    llenartablacab();
                    limpiar();
                    if (tblstcab.getRowCount() == 0) {
                        JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void tblstcabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstcabMouseReleased
        // TODO add your handling code here:
        int fila = tblstcab.getSelectedRow();
        if (fila >= 0) {
            LImportar lim = new LImportar();
            int codigo = Integer.valueOf(tblstcab.getValueAt(tblstcab.getSelectedRow(), 0).toString());
            lstcompra = lim.ListaCompra(codigo);
            lstformat = lim.ListaDataFormatCompras(codigo);
            llenartabla();
            llenartablaformato();
        }
    }//GEN-LAST:event_tblstcabMouseReleased

    private void rbtperiodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtperiodoItemStateChanged
        // TODO add your handling code here:
        if(rbtperiodo.isSelected()){
            cboperiodo.setSelectedIndex(0);
            cboperiodo.setEnabled(true);
            cboinicio.setSelectedIndex(0);
            cbofin.setSelectedIndex(0);
            cboinicio.setEnabled(false);
            cbofin.setEnabled(false);
        }
    }//GEN-LAST:event_rbtperiodoItemStateChanged

    private void rbtrangoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbtrangoItemStateChanged
        // TODO add your handling code here:
        if(rbtrango.isSelected()){
            cboperiodo.setSelectedIndex(0);
            cboperiodo.setEnabled(false);
            cboinicio.setSelectedIndex(0);
            cbofin.setSelectedIndex(0);
            cboinicio.setEnabled(true);
            cbofin.setEnabled(true);
        }
    }//GEN-LAST:event_rbtrangoItemStateChanged

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        if(lstformat.size()>0){
            try { 
                javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
                jF1.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                String ruta = "";
                if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    ruta = jF1.getSelectedFile().getAbsolutePath();
                    WritableWorkbook workbook = Workbook.createWorkbook(new File(ruta + "/DatosFormatCompras.xls"));
                    WritableSheet sheet = workbook.createSheet("Datos Formateada Compras", 0);
                    WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 11);
                    cellFont.setColour(Colour.BLUE);
                    WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
                    WritableCellFormat cellFormat2 = new WritableCellFormat();
                    cellFormat2.setBorder(Border.ALL, BorderLineStyle.THIN);
                    sheet.setColumnView(0, 15);
                    sheet.addCell(new jxl.write.Label(0, 0, "1-Periodo", cellFormat));
                    sheet.setColumnView(1, 15);
                    sheet.addCell(new jxl.write.Label(1, 0, "2-CUO", cellFormat));
                    sheet.setColumnView(2, 15);
                    sheet.addCell(new jxl.write.Label(2, 0, "3-MovCUO", cellFormat));
                    sheet.setColumnView(3, 15);
                    sheet.addCell(new jxl.write.Label(3, 0, "4-FechaEmisión", cellFormat));
                    sheet.setColumnView(4, 15);
                    sheet.addCell(new jxl.write.Label(4, 0, "5-FechaVencimiento", cellFormat));
                    sheet.setColumnView(5, 15);
                    sheet.addCell(new jxl.write.Label(5, 0, "6-TipoComprob", cellFormat));
                    sheet.setColumnView(6, 15);
                    sheet.addCell(new jxl.write.Label(6, 0, "7-SerieComprob", cellFormat));
                    sheet.setColumnView(7, 15);
                    sheet.addCell(new jxl.write.Label(7, 0, "8-AñoDUA", cellFormat));
                    sheet.setColumnView(8, 15);
                    sheet.addCell(new jxl.write.Label(8, 0, "9-NroComprob", cellFormat));
                    sheet.setColumnView(9, 15);
                    sheet.addCell(new jxl.write.Label(9, 0, "10-MontoConsolOPsinIGV", cellFormat));
                    sheet.setColumnView(10, 15);
                    sheet.addCell(new jxl.write.Label(10, 0, "11-TipoDocIden", cellFormat));
                    sheet.setColumnView(11, 15);
                    sheet.addCell(new jxl.write.Label(11, 0, "12-NroDoc", cellFormat));
                    sheet.setColumnView(12, 15);
                    sheet.addCell(new jxl.write.Label(12, 0, "13-NomRazonSocial", cellFormat));
                    sheet.setColumnView(13, 15);
                    sheet.addCell(new jxl.write.Label(13, 0, "14-BaseImpGrav", cellFormat));
                    sheet.setColumnView(14, 15);
                    sheet.addCell(new jxl.write.Label(14, 0, "15-MontoIGV", cellFormat));
                    sheet.setColumnView(15, 15);
                    sheet.addCell(new jxl.write.Label(15, 0, "16-BaseImpAdqGrav", cellFormat));
                    sheet.setColumnView(16, 15);
                    sheet.addCell(new jxl.write.Label(16, 0, "17-MontoIGV", cellFormat));
                    sheet.setColumnView(17, 15);
                    sheet.addCell(new jxl.write.Label(17, 0, "18-BaseImpAdqNoGrav", cellFormat));
                    sheet.setColumnView(18, 15);
                    sheet.addCell(new jxl.write.Label(18, 0, "19-MontoIGVNG", cellFormat));
                    sheet.setColumnView(19, 15);
                    sheet.addCell(new jxl.write.Label(19, 0, "20-ValorAdqNG", cellFormat));
                    sheet.setColumnView(20, 15);
                    sheet.addCell(new jxl.write.Label(20, 0, "21-MontoISC", cellFormat));
                    sheet.setColumnView(21, 15);
                    sheet.addCell(new jxl.write.Label(21, 0, "22-OtrosMontos", cellFormat));
                    sheet.setColumnView(22, 15);
                    sheet.addCell(new jxl.write.Label(22, 0, "23-ImporteTotalAdq", cellFormat));
                    sheet.setColumnView(23, 15);
                    sheet.addCell(new jxl.write.Label(23, 0, "24-TipoCambio", cellFormat));
                    sheet.setColumnView(24, 15);
                    sheet.addCell(new jxl.write.Label(24, 0, "25-FechaEmiComprobRef", cellFormat));
                    sheet.setColumnView(25, 15);
                    sheet.addCell(new jxl.write.Label(25, 0, "26-TipoComprobRef", cellFormat));
                    sheet.setColumnView(26, 15);
                    sheet.addCell(new jxl.write.Label(26, 0, "27-SerieComprobRef", cellFormat));
                    sheet.setColumnView(27, 15);
                    sheet.addCell(new jxl.write.Label(27, 0, "28-CodDUA", cellFormat));
                    sheet.setColumnView(28, 15);
                    sheet.addCell(new jxl.write.Label(28, 0, "29-NroComprobRef", cellFormat));
                    sheet.setColumnView(29, 15);
                    sheet.addCell(new jxl.write.Label(29, 0, "30-NroComprobNoDomicil", cellFormat));
                    sheet.setColumnView(30, 15);
                    sheet.addCell(new jxl.write.Label(30, 0, "31-FechaConstDetracc", cellFormat));
                    sheet.setColumnView(31, 15);
                    sheet.addCell(new jxl.write.Label(31, 0, "32-NroConstDetracc", cellFormat));
                    sheet.setColumnView(32, 15);
                    sheet.addCell(new jxl.write.Label(32, 0, "33-MarcaComprobSujReten", cellFormat));
                    sheet.setColumnView(33, 15);
                    sheet.addCell(new jxl.write.Label(33, 0, "34-EstadoOperación", cellFormat));
                    for (int i = 0; i < lstformat.size(); i++) {
                        sheet.addCell(new jxl.write.Label(0, i + 1, lstformat.get(i).getCampo1(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(1, i + 1, lstformat.get(i).getCampo2(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(2, i + 1, lstformat.get(i).getCampo3(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(3, i + 1, lstformat.get(i).getCampo4(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(4, i + 1, lstformat.get(i).getCampo5(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(5, i + 1, lstformat.get(i).getCampo6(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(6, i + 1, lstformat.get(i).getCampo7(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(7, i + 1, lstformat.get(i).getCampo8(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(8, i + 1, lstformat.get(i).getCampo9(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(9, i + 1, lstformat.get(i).getCampo10(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(10, i + 1, lstformat.get(i).getCampo11(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(11, i + 1, lstformat.get(i).getCampo12(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(12, i + 1, lstformat.get(i).getCampo13(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(13, i + 1, lstformat.get(i).getCampo14(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(14, i + 1, lstformat.get(i).getCampo15(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(15, i + 1, lstformat.get(i).getCampo16(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(16, i + 1, lstformat.get(i).getCampo17(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(17, i + 1, lstformat.get(i).getCampo18(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(18, i + 1, lstformat.get(i).getCampo19(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(19, i + 1, lstformat.get(i).getCampo20(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(20, i + 1, lstformat.get(i).getCampo21(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(21, i + 1, lstformat.get(i).getCampo22(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(22, i + 1, lstformat.get(i).getCampo23(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(23, i + 1, lstformat.get(i).getCampo24(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(24, i + 1, lstformat.get(i).getCampo25(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(25, i + 1, lstformat.get(i).getCampo26(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(26, i + 1, lstformat.get(i).getCampo27(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(27, i + 1, lstformat.get(i).getCampo28(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(28, i + 1, lstformat.get(i).getCampo29(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(29, i + 1, lstformat.get(i).getCampo30(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(30, i + 1, lstformat.get(i).getCampo31(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(31, i + 1, lstformat.get(i).getCampo32(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(32, i + 1, lstformat.get(i).getCampo33(), cellFormat2));
                        sheet.addCell(new jxl.write.Label(33, i + 1, lstformat.get(i).getCampo34(), cellFormat2));
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
    }//GEN-LAST:event_btnExcelActionPerformed

    private void cboperiodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboperiodoActionPerformed
       
    }//GEN-LAST:event_cboperiodoActionPerformed

    private void rbtperiodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtperiodoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtperiodoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgfiltro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JComboBox cbofin;
    private javax.swing.JComboBox cboinicio;
    private javax.swing.JComboBox cboperiodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblnroformateados;
    private javax.swing.JLabel lblnroregistrados;
    private javax.swing.JRadioButton rbtperiodo;
    private javax.swing.JRadioButton rbtrango;
    private javax.swing.JTable tblstcab;
    private javax.swing.JTable tblstdata;
    private javax.swing.JTable tblstformateada;
    // End of variables declaration//GEN-END:variables
}
