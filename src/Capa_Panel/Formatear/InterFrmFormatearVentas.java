/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Formatear;

import Capa_Entidades.EDataFormatVentas;
import Capa_Entidades.EDataVenta;
import Capa_Entidades.EPeriodo;
import Capa_Logica.LImportar;
import Capa_Logica.RenderDerecha;
import Capa_Logica.SesionUsuario;
import Capa_Panel.Exportar.FrmExportarLFormatear;
import Capa_Panel.Importar.JDCargaImportRegistros;
import Capa_Panel.Importar.JDCargaRegistros;
import Capa_Presentacion.MenuLibrosElectronico;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmFormatearVentas extends javax.swing.JInternalFrame {
    ArrayList<EPeriodo> lstperiodo = new ArrayList<>();
    ArrayList<EDataVenta> lstventa = new ArrayList<>();
    ArrayList<EDataFormatVentas> lstventacorrecta = new ArrayList<>();
    ArrayList<EDataFormatVentas> lstventaincorrecta = new ArrayList<>();
    ArrayList<EDataFormatVentas> lstformat = new ArrayList<>();
    String titulos[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento", "Tipo de Comprobante de Pago o Doc",
        "Nro Serie Maquina Registradora", "Nro Comprobante Pago", "Tipo Doc. Identidad Cliente",
        "Nro. Doc. Identidad Cliente", "Razon Social Cliente", "Valor Facturado Export.",
        "Base Imponible de la Op. Gravada", "Importe Total Operacion Exonerada", "Importe Total Operacion Inafecta",
        "ISC", "IGV y/o IPM", "Otros Tributos", "Importe Total", "Tipo de Cambio",
        "Comprobante que se Modifica Fecha", "Comprobante o Doc. que se Modifica Tipo",
        "Comprobante o Doc. que se Modifica Serie", "Nro de Comprobante de Pago o Doc."};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    String titulos2[] = {"1-Período", "2-CUO", "3-MovCUO", "4-FechaEmisión", "5-FechaVencimiento", "6-TipoComprob", "7-NroSerieComprob",
        "8-NroComprob", "9-MontoConsTicketsinIGV", "10-TipoDocIdent", "11-NroDocIden", "12-NomRazonSocial", "13-ValorFactExport", "14-BaseImponGrav", "15-ImpOpeExon",
        "16-ImportOpeInaf", "17-MontoISC", "18-MontoIGV", "19-BaseImpArrozPil", "20-IGVArrozPil", "21-OtrosMontos", "22-ImporteTotalComprob", "23-TipoCambio",
        "24-FechaComprobRef", "25-TipoComprobRef", "26-NroSerieComprobRef", "27-NroComprobRefoDUA", "28-ValorFOBExport", "29-EstadoOperación"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    JDCargaImportRegistros carga;
    JDCargaRegistros gcarga;
    JDFormateaRegistros fcarga;
    PrimeThreadCarga prt;

    /**
     * Creates new form InterFrmFormatearCompras
     */
    public InterFrmFormatearVentas() {
        initComponents();
        this.setTitle("Formatear Registro de Ventas e Ingresos");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        llenarperiodo();
        tblstdata.setModel(modTbdatos);
        tblstdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblstdata.getTableHeader().setReorderingAllowed(false);
        tblstdata.getColumnModel().getColumn(9).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(10).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(11).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblstdata.getColumnModel().getColumn(17).setCellRenderer(new RenderDerecha());
        tblstformato.setModel(modTbdatos2);
        tblstformato.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstformato.getTableHeader().setReorderingAllowed(false);
        tblstformato.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(17).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(18).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(19).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(20).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(21).setCellRenderer(new RenderDerecha());
        tblstformato.getColumnModel().getColumn(22).setCellRenderer(new RenderDerecha());
        btnver.setVisible(false);
    }

    class PrimeThreadCarga implements Runnable {

        PrimeThreadCarga() {
        }

        public void run() {
            LImportar lim = new LImportar();
            lstventa = lim.ListaVenta(lstperiodo.get(cboper.getSelectedIndex() - 1).getCodigo());
            lstformat = lim.ListaDataFormatVentas(lstperiodo.get(cboper.getSelectedIndex() - 1).getCodigo());
            lstventacorrecta = lim.ListaDataFormatVentas(lstperiodo.get(cboper.getSelectedIndex() - 1).getCodigo());
            llenartabla();
            llenartablaformato();
            carga.dispose();
        }
    }
    
    class PrimeThreadFormatea implements Runnable {

        PrimeThreadFormatea() {
        }

        public void run() {
            for (int i = 0; i < lstventa.size(); i++) {
                formateardato(lstventa.get(i));
            }
            for (int i = 0; i < lstventaincorrecta.size(); i++) {
                for (int j = lstventacorrecta.size() - 1; j >= 0; j--) {
                    if (lstventacorrecta.get(j).getCampo2().trim().equalsIgnoreCase(lstventaincorrecta.get(i).getCampo2().trim())) {
                        lstventacorrecta.remove(j);
                    }
                }
            }
            llenartablaformato();
            JOptionPane.showMessageDialog(null, "Formato Terminado.");
            fcarga.dispose();
        }
    }
    
    
    class PrimeThreadGuardar implements Runnable {

        PrimeThreadGuardar() {
        }

        public void run() {
            LImportar lim = new LImportar();
            for (int i = 0; i < lstventacorrecta.size(); i++) {
                lstventacorrecta.get(i).setCodigo(lstperiodo.get(cboper.getSelectedIndex() - 1).getCodigo());
                lim.InsertarRegVentaFormat(lstventacorrecta.get(i));
            }
            JOptionPane.showMessageDialog(null, "Se grabó correctamente la data del período " + cboper.getSelectedItem().toString() + ".");
            gcarga.dispose();
            exportar();
            limpiar();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    private void llenarperiodo() {
        LImportar limp = new LImportar();
        lstperiodo = limp.ListaPeriodoVenta();
        cboper.removeAllItems();
        cboper.addItem("<Seleccione>");
        for (int i = 0; i < lstperiodo.size(); i++) {
            cboper.addItem(lstperiodo.get(i).getMesChar() + " - " + lstperiodo.get(i).getAño());
        }
    }
    
    private void exportar() {
        if (JOptionPane.showConfirmDialog(this, "¿Desea Exportar la Data?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
            FrmExportarLFormatear frmexp = new FrmExportarLFormatear();
            frmexp.exportaciondeafuera("VENTAS", cboper.getSelectedItem().toString().trim());
            frmexp.setVisible(true);
        }
    }
    
    public int diferenciaenmeses(String Fecha) {
        int añofecha = Integer.parseInt(Fecha.substring(6, 10));
        int mesfecha = Integer.parseInt(Fecha.substring(3, 5));
        int añoperiodo = lstperiodo.get(cboper.getSelectedIndex() - 1).getAño();
        int mesperiodo = lstperiodo.get(cboper.getSelectedIndex() - 1).getMes();
        int resultado = 0;
        if (añofecha == añoperiodo) {
            resultado = mesperiodo - mesfecha;
        } else {
            resultado = (mesperiodo + 12) - mesfecha;
        }
        return resultado;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstdata = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lbltotalregistros = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblstformato = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnver = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblregistrosformat = new javax.swing.JLabel();
        lblerradosformat = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblcorrectoformat = new javax.swing.JLabel();
        cboper = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORMATEAR REGISTRO DE VENTAS E INGRESOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1000, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Período");

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnnuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(tblstdata);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 990, 390));

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel4.setText("Total Registros Importados");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jButton2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jButton2.setText("Formatear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 410, 90, 30));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel9.setText(":");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 10, -1));

        lbltotalregistros.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbltotalregistros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbltotalregistros.setText("0");
        jPanel2.add(lbltotalregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 40, -1));

        jTabbedPane1.addTab("Data Importada", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblstformato.setAutoCreateRowSorter(true);
        tblstformato.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstformato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblstformato);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 990, 390));

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText(":");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, 10, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Total Errados");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 410, -1, -1));

        btnver.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnver.setText("Ver");
        btnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverActionPerformed(evt);
            }
        });
        jPanel3.add(btnver, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 406, -1, -1));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 410, 70, 30));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Total Registros");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText(":");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 10, -1));

        lblregistrosformat.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblregistrosformat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblregistrosformat.setText("0");
        jPanel3.add(lblregistrosformat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 40, -1));

        lblerradosformat.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblerradosformat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblerradosformat.setText("0");
        jPanel3.add(lblerradosformat, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 410, 40, -1));

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Total Correctos");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, -1, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText(":");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 410, 10, -1));

        lblcorrectoformat.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblcorrectoformat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblcorrectoformat.setText("0");
        jPanel3.add(lblcorrectoformat, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 410, 40, -1));

        jTabbedPane1.addTab("Formato de SUNAT", jPanel3);

        cboper.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboper.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cboperMouseReleased(evt);
            }
        });
        cboper.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboperItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cboper, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(570, 570, 570)
                                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTabbedPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 483, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarlst() {
        carga = new JDCargaImportRegistros(null, true);
        prt = new PrimeThreadCarga();
        new Thread(prt).start();
        carga.setVisible(true);
    }
    
    public boolean esNumeroEntero(String cad) {
        try {
            Integer.parseInt(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private boolean validartipodocumento(String campo6){
        if (campo6.equalsIgnoreCase("00") || campo6.equalsIgnoreCase("03") || campo6.equalsIgnoreCase("05")
                || campo6.equalsIgnoreCase("06") || campo6.equalsIgnoreCase("07") || campo6.equalsIgnoreCase("08")
                || campo6.equalsIgnoreCase("11") || campo6.equalsIgnoreCase("12") || campo6.equalsIgnoreCase("13")
                || campo6.equalsIgnoreCase("14") || campo6.equalsIgnoreCase("15") || campo6.equalsIgnoreCase("16")
                || campo6.equalsIgnoreCase("18") || campo6.equalsIgnoreCase("19") || campo6.equalsIgnoreCase("22")
                || campo6.equalsIgnoreCase("23") || campo6.equalsIgnoreCase("26") || campo6.equalsIgnoreCase("28")
                || campo6.equalsIgnoreCase("30") || campo6.equalsIgnoreCase("34") || campo6.equalsIgnoreCase("36")
                || campo6.equalsIgnoreCase("37") || campo6.equalsIgnoreCase("55") || campo6.equalsIgnoreCase("87")
                || campo6.equalsIgnoreCase("88") || campo6.equalsIgnoreCase("91") || campo6.equalsIgnoreCase("97")
                || campo6.equalsIgnoreCase("98") || campo6.equalsIgnoreCase("01") || campo6.equalsIgnoreCase("02")
                || campo6.equalsIgnoreCase("04") || campo6.equalsIgnoreCase("09") || campo6.equalsIgnoreCase("10")
                || campo6.equalsIgnoreCase("17") || campo6.equalsIgnoreCase("20") || campo6.equalsIgnoreCase("21")
                || campo6.equalsIgnoreCase("24") || campo6.equalsIgnoreCase("25") || campo6.equalsIgnoreCase("27")
                || campo6.equalsIgnoreCase("29") || campo6.equalsIgnoreCase("31") || campo6.equalsIgnoreCase("32")
                || campo6.equalsIgnoreCase("35") || campo6.equalsIgnoreCase("50") || campo6.equalsIgnoreCase("52")
                || campo6.equalsIgnoreCase("53") || campo6.equalsIgnoreCase("54") || campo6.equalsIgnoreCase("96")
                || campo6.equalsIgnoreCase("99")) {
            return true;
        } else {
            return false;
        }
    }
    
    private void formateardato(EDataVenta data) {
        EDataFormatVentas dataformato = new EDataFormatVentas();
        int val = 0;
        String periodo = "";
        periodo = lstperiodo.get(cboper.getSelectedIndex() - 1).getAño() + lstperiodo.get(cboper.getSelectedIndex() - 1).getMesChar() + "00";
        int noval = 0;
        dataformato.setCampo1(periodo);
        dataformato.setCampo2(data.getCorrelativo() + "");
        dataformato.setCampo3("M" + data.getCorrelativo());
        dataformato.setCampo6(data.getTipoCompPago());
        dataformato.setCampo8(data.getNroComproPago());
        dataformato.setCampo12(data.getRazSocialCliente().replace("|", ""));
        dataformato.setCampo4("");
        dataformato.setCampo5("");
        dataformato.setCampo7("");
        dataformato.setCampo9("");
        dataformato.setCampo10("");
        dataformato.setCampo11("");
        dataformato.setCampo13("");
        dataformato.setCampo14("");
        dataformato.setCampo15("");
        dataformato.setCampo16("");
        dataformato.setCampo17("");
        dataformato.setCampo18("");
        dataformato.setCampo19("");
        dataformato.setCampo20("");
        dataformato.setCampo21("");
        dataformato.setCampo22("");
        dataformato.setCampo23("");
        dataformato.setCampo24("");
        dataformato.setCampo25("");
        dataformato.setCampo26("");
        dataformato.setCampo27("");
        dataformato.setCampo28("");
        dataformato.setCampo29("");
        if (diferenciaenmeses(data.getFechaEmision()) > 0) {
            dataformato.setCampo29("8");
        }
        if (diferenciaenmeses(data.getFechaEmision()) == 0) {
            dataformato.setCampo29("1");
        }
        if (data.getRazSocialCliente().trim().equalsIgnoreCase("ANULADO")) {
            dataformato.setCampo29("2");
            noval = 1;
        }
        if (!validartipodocumento(dataformato.getCampo6())) {
            val = val + 1;
            dataformato.setCampoOtros("Error Campo 6");
        }
        if (!dataformato.getCampo29().equalsIgnoreCase("2")) {
            if (data.getFechaEmision().toString().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("Error Campo 4");
            }
        }
        if (!dataformato.getCampo29().equalsIgnoreCase("2")) {
            if (dataformato.getCampo6().equalsIgnoreCase("14")) {
                if (data.getFechaVencimiento().toString().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 5");
                }
            }
        }
        if (data.getTipoCompPago().trim().length() == 0) {
            val = val + 1;
        }
        dataformato.setCampo4(data.getFechaEmision().replace("-", "/"));
        dataformato.setCampo5(data.getFechaVencimiento().replace("-", "/"));
        dataformato.setCampo7(data.getNroSerieMaqReg());
        if (dataformato.getCampo7().trim().length() == 0) {
            val = val + 1;
            dataformato.setCampoOtros("ERROR: Campo 7");
        }
        if (dataformato.getCampo7().trim().length() == 4) {
        } else {
            String campo = dataformato.getCampo7().trim();
            if (dataformato.getCampo7().trim().length() == 3) {
                dataformato.setCampo7("0" + campo);
            }
            if (dataformato.getCampo7().trim().length() == 2) {
                dataformato.setCampo7("00" + campo);
            }
            if (dataformato.getCampo7().trim().length() == 1) {
                dataformato.setCampo7("000" + campo);
            }

        }
        if(data.getNroComproPago()==null){
            dataformato.setCampo8("");
        }
        if (data.getNroComproPago().trim().length() == 0) {
            val = val + 1;
            dataformato.setCampoOtros("Error Campo 8");
        }
        if (data.getNroComproPago().trim().indexOf("-") > 0) {
            val = val + 1;
            dataformato.setCampoOtros("Error Campo 8");
        }
        dataformato.setCampo9("");
        if (dataformato.getCampo6().equalsIgnoreCase("00") || dataformato.getCampo6().equalsIgnoreCase("03")
                || dataformato.getCampo6().equalsIgnoreCase("05") || dataformato.getCampo6().equalsIgnoreCase("06")
                || dataformato.getCampo6().equalsIgnoreCase("07") || dataformato.getCampo6().equalsIgnoreCase("08")
                || dataformato.getCampo6().equalsIgnoreCase("11") || dataformato.getCampo6().equalsIgnoreCase("12")
                || dataformato.getCampo6().equalsIgnoreCase("13") || dataformato.getCampo6().equalsIgnoreCase("14")
                || dataformato.getCampo6().equalsIgnoreCase("15") || dataformato.getCampo6().equalsIgnoreCase("16")
                || dataformato.getCampo6().equalsIgnoreCase("18") || dataformato.getCampo6().equalsIgnoreCase("19")
                || dataformato.getCampo6().equalsIgnoreCase("23") || dataformato.getCampo6().equalsIgnoreCase("26")
                || dataformato.getCampo6().equalsIgnoreCase("28") || dataformato.getCampo6().equalsIgnoreCase("30")
                || dataformato.getCampo6().equalsIgnoreCase("34") || dataformato.getCampo6().equalsIgnoreCase("35")
                || dataformato.getCampo6().equalsIgnoreCase("36") || dataformato.getCampo6().equalsIgnoreCase("37")
                || dataformato.getCampo6().equalsIgnoreCase("55") || dataformato.getCampo6().equalsIgnoreCase("56")
                || dataformato.getCampo6().equalsIgnoreCase("87") || dataformato.getCampo6().equalsIgnoreCase("88")) {
        } else {
            if (data.getTipoDocIdenClientt().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("Error Campo 10");
            }
            if (data.getNroDocIdentCliente().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("Error Campo 11");
            }
            if (data.getRazSocialCliente().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("Error Campo 12");
            }
            if (!dataformato.getCampo29().equalsIgnoreCase("2")) {
                if (data.getTipoDocIdenClientt().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 10");
                }
                if (data.getNroDocIdentCliente().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 11");
                }
                if (data.getRazSocialCliente().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 12");
                }
            }
        }
        if (noval == 0) {
            if (esNumeroEntero(data.getTipoDocIdenClientt())) {
                dataformato.setCampo10(Integer.valueOf(data.getTipoDocIdenClientt()).toString());
                int valor = Integer.valueOf(data.getTipoDocIdenClientt());
                if (valor == 0 || valor == 1 || valor == 4 || valor == 6 || valor == 7) {
                } else {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 10");
                }
            } else {
                val = val + 1;
                dataformato.setCampoOtros("Error Campo 10");
            }
        }
        dataformato.setCampo11(data.getNroDocIdentCliente());
        dataformato.setCampo13(data.getValorFactExport());
        dataformato.setCampo14(data.getBaseImpOpeGravada());
        dataformato.setCampo15(data.getImportTotalOpeExon());
        dataformato.setCampo16(data.getImportTotalOpeInaf());
        dataformato.setCampo17(data.getISC());
        dataformato.setCampo18(data.getIGVyIPM());
        dataformato.setCampo19("");
        dataformato.setCampo20("");
        if (dataformato.getCampo6().trim().equalsIgnoreCase("40")) {
            if (!dataformato.getCampo29().trim().equalsIgnoreCase("2")) {
                if (dataformato.getCampo19().trim().length() == 0 || dataformato.getCampo20().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 20");
                }
            }
        }
        dataformato.setCampo21(data.getOtrosTributos());
        dataformato.setCampo22(data.getImporteTotal());
        dataformato.setCampo23(data.getTipoCambio());
        if (data.getTipoDocMod().trim().length() == 8) {
            String nuevodia = data.getTipoDocMod().trim().substring(0, 2) + "/"
                    + data.getTipoDocMod().trim().substring(3, 5) + "/"
                    + "20" + data.getTipoDocMod().trim().substring(6, 8);
            dataformato.setCampo24(nuevodia);
        } else {
            dataformato.setCampo24(data.getTipoDocMod().replace("-", "/"));
        }
        dataformato.setCampo25(data.getFechaDocMod());
        dataformato.setCampo26(data.getSerieDocMod());
        if (dataformato.getCampo6().trim().equalsIgnoreCase("07") || dataformato.getCampo6().trim().equalsIgnoreCase("08")
                || dataformato.getCampo6().trim().equalsIgnoreCase("87") || dataformato.getCampo6().trim().equalsIgnoreCase("88")
                || dataformato.getCampo6().trim().equalsIgnoreCase("97") || dataformato.getCampo6().trim().equalsIgnoreCase("98")) {
            if (!dataformato.getCampo29().equalsIgnoreCase("2")) {
                if (dataformato.getCampo24().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 24");
                }
                if (dataformato.getCampo26().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 26");
                }
                if (dataformato.getCampo25().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("Error Campo 25");
                } else {
                    if (dataformato.getCampo25().equalsIgnoreCase("00")) {
                        val = val + 1;
                        dataformato.setCampoOtros("Error Campo 25");
                    }
                }
            }
        }
        dataformato.setCampo27(data.getNroDocMod());
        dataformato.setCampo28("");
        if (dataformato.getCampo25().equalsIgnoreCase("50") || dataformato.getCampo25().equalsIgnoreCase("52")) {
            if (dataformato.getCampo28().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("Error Campo 28");
            }
            dataformato.setCampo28("0.00");
        }
        for (int i = lstventacorrecta.size() - 1; i >= 0; i--) {
                if (dataformato.getCampo8().trim().equalsIgnoreCase(lstventacorrecta.get(i).getCampo8().trim()) && dataformato.getCampo7().trim().equalsIgnoreCase(lstventacorrecta.get(i).getCampo7().trim())) {
                int rep = 0;
                int rep2 = 0;
                dataformato.setCampoOtros("Comprobante de Pago Duplicado");
                lstventacorrecta.get(i).setCampoOtros("Comprobante de Pago Duplicado");
                for (int j = 0; j < lstventaincorrecta.size(); j++) {
                    if (lstventaincorrecta.get(j).getCampo2().trim().equalsIgnoreCase(lstventacorrecta.get(i).getCampo2().trim())) {
                        rep2 = 1;
                    }
                }
                for (int j = 0; j < lstventaincorrecta.size(); j++) {
                    if (lstventaincorrecta.get(j).getCampo2().trim().equalsIgnoreCase(dataformato.getCampo2().trim())) {
                        rep = 1;
                    }
                }
                if (rep == 0) {
                    lstventaincorrecta.add(dataformato);
                }
                if (rep2 == 0) {
                    lstventaincorrecta.add(lstventacorrecta.get(i));
                }
            }
        }
        if (val == 0) {
            lstventacorrecta.add(dataformato);
        } else {
            lstventaincorrecta.add(dataformato);
        }
        lstformat.add(dataformato);
    }
    
    private void llenartablaformato() {
        modTbdatos2.setRowCount(0);
        if (lstformat.size() > 0) {
            for (int i = 0; i < lstformat.size(); i++) {
                Object obj[] = new Object[29];
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
                modTbdatos2.addRow(obj);
            }
        }
        tblstformato.setModel(modTbdatos2);
        lblregistrosformat.setText(modTbdatos2.getRowCount()+"");
        lblcorrectoformat.setText(lstventacorrecta.size()+"");
        lblerradosformat.setText(lstventaincorrecta.size()+"");
        if (lstventaincorrecta.size() > 0) {
            btnver.setVisible(true);
        } else {
            btnver.setVisible(false);
        }
    }
    
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = InterFrmFormatearVentas.this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2) + 70;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2) + 20;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
     private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        DecimalFormat formato2 = new DecimalFormat("###, ##0.000", simbolo);
        modTbdatos.setRowCount(0);
        double otros = 0.0;
        if (lstventa.size() > 0) {
            for (int i = 0; i < lstventa.size(); i++) {
                Object obj[] = new Object[22];
                obj[0] = lstventa.get(i).getCorrelativo();
                obj[1] = lstventa.get(i).getFechaEmision();
                obj[2] = lstventa.get(i).getFechaVencimiento();
                obj[3] = lstventa.get(i).getTipoCompPago();
                obj[4] = lstventa.get(i).getNroSerieMaqReg();
                obj[5] = lstventa.get(i).getNroComproPago();
                obj[6] = lstventa.get(i).getTipoDocIdenClientt();
                obj[7] = lstventa.get(i).getNroDocIdentCliente();
                obj[8] = lstventa.get(i).getRazSocialCliente();
                obj[9] = formato.format(Double.parseDouble(lstventa.get(i).getValorFactExport()));
                otros = otros + Double.parseDouble(lstventa.get(i).getValorFactExport());
                obj[10] = formato.format(Double.parseDouble(lstventa.get(i).getBaseImpOpeGravada()));
                obj[11] = formato.format(Double.parseDouble(lstventa.get(i).getImportTotalOpeExon()));
                otros = otros + Double.parseDouble(lstventa.get(i).getImportTotalOpeExon());
                if (lstventa.get(i).getImportTotalOpeInaf().trim().length() > 0) {
                    obj[12] = formato.format(Double.parseDouble(lstventa.get(i).getImportTotalOpeInaf()));
                } else {
                    obj[12] = "0.00";
                }
                obj[13] = formato.format(Double.parseDouble(lstventa.get(i).getISC()));
                obj[14] = formato.format(Double.parseDouble(lstventa.get(i).getIGVyIPM()));
                obj[15] = formato.format(Double.parseDouble(lstventa.get(i).getOtrosTributos()));
                obj[16] = formato.format(Double.parseDouble(lstventa.get(i).getImporteTotal()));
                if (lstventa.get(i).getTipoCambio().trim().length() > 0) {
                    obj[17] = formato2.format(Double.parseDouble(lstventa.get(i).getTipoCambio()));
                } else {
                    obj[17] = "0.000";
                }
                obj[18] = lstventa.get(i).getTipoDocMod();
                obj[19] = lstventa.get(i).getFechaDocMod();
                obj[20] = lstventa.get(i).getSerieDocMod();
                obj[21] = lstventa.get(i).getNroDocMod();
                modTbdatos.addRow(obj);
            }
        }
        tblstdata.setModel(modTbdatos);
        lbltotalregistros.setText(tblstdata.getRowCount()+"");
    }
     
     private void limpiar(){
        cboper.setSelectedIndex(0);
        lstventacorrecta.removeAll(lstventacorrecta);
        lstformat.removeAll(lstformat);
        lstventaincorrecta.removeAll(lstventaincorrecta);
        llenartablaformato();
    }
    
    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        // TODO add your handling code here:
        if (lstventacorrecta.size() > 0) {
            LImportar lim = new LImportar();
            int reemp = lim.DataAnteriorFormatoVentas(lstperiodo.get(cboper.getSelectedIndex() - 1).getCodigo());
            gcarga = new JDCargaRegistros(null, true);
            SesionUsuario.nroaregistrar = lstventacorrecta.size();
            if (reemp == 0) {
                if (lstventaincorrecta.size() > 0) {
                    if (JOptionPane.showConfirmDialog(this, "Tiene registros errados. Va a GRABAR solo la data formateada correcta del período " + cboper.getSelectedItem().toString() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        PrimeThreadGuardar prt2 = new PrimeThreadGuardar();
                        new Thread(prt2).start();
                        gcarga.setVisible(true);
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Va a GRABAR la data formateada del período " + cboper.getSelectedItem().toString() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        PrimeThreadGuardar prt2 = new PrimeThreadGuardar();
                        new Thread(prt2).start();
                        gcarga.setVisible(true);
                    }
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Ya cuenta con data registrada para este período. ¿Desea Reemplazarla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    if (lim.LimpiarRegVentaFormat(lstperiodo.get(cboper.getSelectedIndex() - 1).getCodigo())) {
                        PrimeThreadGuardar prt2 = new PrimeThreadGuardar();
                        new Thread(prt2).start();
                        gcarga.setVisible(true);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cuenta con data para grabar.");
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void cboperItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboperItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (cboper.getSelectedIndex() == 0) {
                lstventa.removeAll(lstventa);
                llenartabla();
            } else {
                cargarlst();
            }
        }
    }//GEN-LAST:event_cboperItemStateChanged

    private void cboperMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboperMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_cboperMouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (lstventa.size() > 0) {
            fcarga=new JDFormateaRegistros(null, true);
            SesionUsuario.nroaregistrar=lstventa.size();
            if (lstformat.size() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Ya tiene data formateada para este período. ¿Desea Volver a Formatearla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    lstformat.removeAll(lstformat);
                    lstventacorrecta.removeAll(lstventacorrecta);
                    lstventaincorrecta.removeAll(lstventaincorrecta);
                    PrimeThreadFormatea prt3 = new PrimeThreadFormatea();
                    new Thread(prt3).start();
                    fcarga.setVisible(true);
                }
            } else {
                lstformat.removeAll(lstformat);
                lstventacorrecta.removeAll(lstventacorrecta);
                lstventaincorrecta.removeAll(lstventaincorrecta);
                PrimeThreadFormatea prt3 = new PrimeThreadFormatea();
                new Thread(prt3).start();
                fcarga.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cuenta con data para formatear.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverActionPerformed
        // TODO add your handling code here:
        if (lstventaincorrecta.size() > 0) {
            InterFrmVerErradosFormatVenta errados = new InterFrmVerErradosFormatVenta();
            MenuLibrosElectronico.dpane2.add(errados);
            centerJIF(errados);
            errados.llenartablaformato(lstventaincorrecta);
            errados.setVisible(true);
        }
    }//GEN-LAST:event_btnverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnver;
    private javax.swing.JComboBox cboper;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblcorrectoformat;
    private javax.swing.JLabel lblerradosformat;
    private javax.swing.JLabel lblregistrosformat;
    private javax.swing.JLabel lbltotalregistros;
    private javax.swing.JTable tblstdata;
    private javax.swing.JTable tblstformato;
    // End of variables declaration//GEN-END:variables
}
