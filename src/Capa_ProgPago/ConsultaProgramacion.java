/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EConsolidado;
import Capa_Entidades.EEstado;
import Capa_Entidades.EProgPagosCabecera;
import Capa_Logica.LConsultaComprobante;
import Capa_Logica.LProgPagosCabecera;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class ConsultaProgramacion extends javax.swing.JInternalFrame {

    ArrayList<EEstado> lisest = new ArrayList<>();
    ArrayList<EProgPagosCabecera> lstcabecera = new ArrayList<>();
    ArrayList<EProgPagosCabecera> lstcabecera2 = new ArrayList<>();
    ArrayList<EConsolidado> lstconsolidado = new ArrayList<>();
    ArrayList<EComprobante> lstdetalle = new ArrayList();
    String titulos[] = {"CodProg", "Estado", "Fecha", "Banco", "Moneda", "NroCuentaOrigen"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    String titulos2[] = {"CodProg", "DocProveedor", "RazonSocial", "PagarSoles", "PagarDolares"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    String titulos3[] = {"CodProg", "CodCompr", "TipoComprobante", "NumComp", "Fecha", "DocProv", "Glosa", "CompSujeto",
        "FormaPago", "MontoSoles", "MontoDolares", "Estado", "Banco", "Moneda", "CtaBancaria"};
    DefaultTableModel modTbdatos3 = new DefaultTableModel(titulos3, 0);
    int opc = 0;
    LProgPagosCabecera lpro = new LProgPagosCabecera();
    public String aten = "P";
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    DecimalFormatSymbols simbolosIngles = DecimalFormatSymbols.getInstance(Locale.ENGLISH);

    /**
     * Creates new form ConsultaProgramacion
     */
    public ConsultaProgramacion() {
        initComponents();
        this.setTitle("CONSULTA DE PROGRAMACION");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        Date hoy = new Date();
        dcfechainicio.setDate(hoy);
        btnReporte1.setVisible(false);
        dcfechafin.setDate(hoy);
        tblistacabecera.setModel(modTbdatos);
        tblistacabecera.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblistacabecera.getTableHeader().setReorderingAllowed(false);
        tblistacabecera.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblistacabecera.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblistacabecera.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblistacabecera.getColumnModel().getColumn(3).setPreferredWidth(125);
        tblstdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstdata.getTableHeader().setReorderingAllowed(false);
        tblstconsolidado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstconsolidado.getTableHeader().setReorderingAllowed(false);
        rbfecha.setSelected(true);
        listacomprobante();
        llenarEstado();
    }

    private void listacomprobante() {
        modTbdatos.setRowCount(0);
        if (lstcabecera.size() > 0) {
            for (int i = 0; i < lstcabecera.size(); i++) {
                EProgPagosCabecera at;
                at = lstcabecera.get(i);
                Object nuevafila[] = new Object[6];
                nuevafila[0] = at.getCod_programacion();
                nuevafila[1] = at.getEstado_progpagos();
                nuevafila[2] = at.getFecha();
                nuevafila[3] = at.getBanco();
                nuevafila[4] = at.getMoneda();
                nuevafila[5] = at.getNumCuenta();
                modTbdatos.addRow(nuevafila);
            }
        }
        tblistacabecera.setModel(modTbdatos);

    }

    private void listaconsolidado() {
        modTbdatos2.setRowCount(0);
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolosIngles);
        double montosoles = 0.0;
        double montodolares = 0.0;
        if (lstconsolidado.size() > 0) {
            for (int i = 0; i < lstconsolidado.size(); i++) {
                Object nuevafila[] = new Object[5];
                nuevafila[0] = lstconsolidado.get(i).getCodProgramacion();
                nuevafila[1] = lstconsolidado.get(i).getDocProv();
                nuevafila[2] = lstconsolidado.get(i).getRazonSocial();
                nuevafila[3] = lstconsolidado.get(i).getMontosoles();
                nuevafila[4] = lstconsolidado.get(i).getMontodolares();
                montosoles = montosoles + lstconsolidado.get(i).getMontosoles();
                montodolares = montodolares + lstconsolidado.get(i).getMontodolares();
                modTbdatos2.addRow(nuevafila);
            }
        }
        tblstconsolidado.setModel(modTbdatos2);
        lbltotalsoles.setText("MONTO SOLES: " + formato.format(montosoles));
        lbltotaldolares.setText("MONTO DOLARES: " + formato.format(montodolares));
        lbltotalconsolidadoProg.setText(String.valueOf(modTbdatos2.getRowCount()) + " Reg.");
    }

    private void listadetalle() {
        modTbdatos3.setRowCount(0);
        

        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolosIngles);
        double montosoles = 0.0;
        double montodolares = 0.0;
        double montosolesRef = 0.0;
        double montodolaresRef = 0.0;
        if (lstdetalle.size() > 0) {
            for (int i = 0; i < lstdetalle.size(); i++) {
                Object nuevafila[] = new Object[15];
                nuevafila[0] = lstdetalle.get(i).getCodProgramacion();
                nuevafila[1] = lstdetalle.get(i).getCodComprobante();
                nuevafila[2] = lstdetalle.get(i).getTipoComprobante();
                nuevafila[3] = lstdetalle.get(i).getNumComprobante();
                nuevafila[4] = lstdetalle.get(i).getFechaComprobante();
                nuevafila[5] = lstdetalle.get(i).getDocProveedor();
                nuevafila[6] = lstdetalle.get(i).getGlosaComprante();

                nuevafila[7] = lstdetalle.get(i).getComprobanteSujeto();
                nuevafila[8] = lstdetalle.get(i).getFormaPago();
                nuevafila[9] = lstdetalle.get(i).getMontoPagar();
                nuevafila[10] = lstdetalle.get(i).getMontoPagarDOL();
                montosoles = montosoles + lstdetalle.get(i).getMontoPagar();
                montodolares = montodolares + lstdetalle.get(i).getMontoPagarDOL();
                montosolesRef = montosolesRef + lstdetalle.get(i).getMontoPagarRefSoles();
                montodolaresRef = montodolaresRef + lstdetalle.get(i).getMontoPagarRefDol();
                nuevafila[11] = lstdetalle.get(i).getEstado();
                nuevafila[12] = lstdetalle.get(i).getUsuModi();
                nuevafila[13] = lstdetalle.get(i).getNomMoneda();
                nuevafila[14] = lstdetalle.get(i).getCuentasoles();
                modTbdatos3.addRow(nuevafila);
            }
        }
        tblstdata.setModel(modTbdatos3);
        lbltotalsolesD.setText("MONTO SOLES: " + formato.format(montosoles));
        lbltotaldolaresd.setText("MONTO DOLARES: " + formato.format(montodolares));
        lbltotalRegProgramacion.setText(String.valueOf(modTbdatos3.getRowCount()) + " Reg.");
    }

    String fechaformato(Date fecha) {
        String fec = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        return fec;
    }

    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2);
        int height = ((desktopSize.height - jInternalFrameSize.height) / 4);
        jif.setLocation(width, height);
        jif.setVisible(true);

    }

    private void llenarEstado() {
        LConsultaComprobante lre = new LConsultaComprobante();
        lisest = lre.ListaEstadoCompro();
        cboestado.removeAllItems();
        cboestado.addItem("<Seleccione>");
        cboestado.addItem("PENDIENTE");
        cboestado.addItem("EJECUTADO");
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
        jLabel2 = new javax.swing.JLabel();
        cboestado = new javax.swing.JComboBox();
        dcfechainicio = new com.toedter.calendar.JDateChooser()

        ;
        dcfechafin = new com.toedter.calendar.JDateChooser()

        ;
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnsalir1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacabecera = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        btnReporte1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblstdata = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbltotalRegProgramacion = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lbltotalconsolidadoProg = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblstconsolidado = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbltotalsoles = new javax.swing.JLabel();
        lbltotaldolares = new javax.swing.JLabel();
        lbltotalsolesD = new javax.swing.JLabel();
        lbltotaldolaresd = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        setPreferredSize(new java.awt.Dimension(812, 556));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CONSULTA DE PROGRAMACION");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 790, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 790, 18));

        rbfecha.setBackground(new java.awt.Color(255, 255, 255));
        rbfecha.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbfecha.setText("Por Fecha");
        rbfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfechaActionPerformed(evt);
            }
        });
        getContentPane().add(rbfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        rbestado.setBackground(new java.awt.Color(255, 255, 255));
        rbestado.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbestado.setText("Estado");
        rbestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbestadoActionPerformed(evt);
            }
        });
        getContentPane().add(rbestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 60, 20));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, 60));

        cboestado.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboestado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        getContentPane().add(cboestado, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 170, -1));

        dcfechainicio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcfechainicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcfechainicioKeyTyped(evt);
            }
        });
        getContentPane().add(dcfechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, -1, -1));

        dcfechafin.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcfechafin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcfechafinKeyTyped(evt);
            }
        });
        getContentPane().add(dcfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel8.setText("Fec. Ini");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, -1, 20));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel10.setText("Estado");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, -1, 20));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 60, 70, 30));

        btnsalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 70, 30));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        tblistacabecera.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistacabecera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblistacabecera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblistacabeceraMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblistacabecera);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 350, 160));

        btnReporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/excelmini.png"))); // NOI18N
        btnReporte1.setPreferredSize(new java.awt.Dimension(79, 35));
        btnReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporte1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnReporte1, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 70, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jScrollPane2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane2MouseClicked(evt);
            }
        });

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

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 790, 170));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText("Fec Fin");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, -1, 20));

        jLabel23.setBackground(new java.awt.Color(255, 255, 255));
        jLabel23.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 790, 70));

        jPanel3.setBackground(new java.awt.Color(153, 153, 153));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DETALLE DE PROGRAMACIÓN");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 1, 770, 20));

        lbltotalRegProgramacion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lbltotalRegProgramacion.setForeground(new java.awt.Color(255, 255, 255));
        lbltotalRegProgramacion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotalRegProgramacion.setText("0 Registro(s)");
        jPanel3.add(lbltotalRegProgramacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 110, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 790, 20));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CONSOLIDADO DE PROGRAMACIÓN");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 1, 400, 20));

        lbltotalconsolidadoProg.setBackground(new java.awt.Color(255, 255, 255));
        lbltotalconsolidadoProg.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lbltotalconsolidadoProg.setForeground(new java.awt.Color(255, 255, 255));
        lbltotalconsolidadoProg.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotalconsolidadoProg.setText("0 Registro(s)");
        jPanel4.add(lbltotalconsolidadoProg, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 110, 20));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 410, 20));

        tblstconsolidado.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstconsolidado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblstconsolidado);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 410, 160));

        jPanel5.setBackground(new java.awt.Color(153, 153, 153));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("PROGRAMACIÓN CABECERA");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 1, 330, 20));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 350, 20));

        lbltotalsoles.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbltotalsoles.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotalsoles.setText("TOTAL SOLES: ");
        getContentPane().add(lbltotalsoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 150, 20));

        lbltotaldolares.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbltotaldolares.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotaldolares.setText("TOTAL DOLARES: ");
        getContentPane().add(lbltotaldolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 290, 150, 20));

        lbltotalsolesD.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbltotalsolesD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotalsolesD.setText("TOTAL SOLES: ");
        getContentPane().add(lbltotalsolesD, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 500, 150, 20));

        lbltotaldolaresd.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbltotaldolaresd.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotaldolaresd.setText("TOTAL DOLARES: ");
        getContentPane().add(lbltotaldolaresd, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 500, 150, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfechaActionPerformed
        rbfecha.setSelected(true);
        rbestado.setSelected(false);
        cboestado.setEnabled(false);
        dcfechainicio.setEnabled(true);
        dcfechafin.setEnabled(true);
    }//GEN-LAST:event_rbfechaActionPerformed

    private void dcfechainicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcfechainicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcfechainicioKeyTyped

    private void dcfechafinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcfechafinKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcfechafinKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        opc = 1;

        if (rbestado.isSelected() == false && rbfecha.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Seleccione Filtro que desea buscar.");
            rbfecha.requestFocus();
            return;
        }

        if (rbfecha.isSelected()) {
            if (dcfechainicio.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione Fecha de Inicio.");
                dcfechainicio.requestFocus();
            } else {
                if (dcfechafin.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Seleccine Fecha de Fin.");
                    dcfechafin.requestFocus();
                } else {
                    if (dcfechafin.getDate().before(dcfechainicio.getDate())) {
                        JOptionPane.showMessageDialog(this, "Fecha de Fin no puede ser anterior a la Fecha de Inicio.");
                        dcfechafin.requestFocus();
                        lstcabecera.removeAll(lstcabecera);
                        listacomprobante();
                    } else {
                        LProgPagosCabecera lo = new LProgPagosCabecera();
                        lstcabecera = lo.ListaxFechaCabecera(fechaformato(dcfechainicio.getDate()), fechaformato(dcfechafin.getDate()));
                        if (lstcabecera.size() > 0) {
                            listacomprobante();
                        } else {
                            JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                            lstcabecera.removeAll(lstcabecera);
                            lstdetalle.removeAll(lstdetalle);
                            lstconsolidado.removeAll(lstconsolidado);
                            listadetalle();
                            listaconsolidado();
                            listacomprobante();
                        }
                    }
                }
            }
        }

        if (rbestado.isSelected()) {
            if (cboestado.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione Estado que desea buscar.");
                cboestado.requestFocus();
            } else {
                LProgPagosCabecera lo = new LProgPagosCabecera();
                lstcabecera = lo.ListaxRazonSocialCabecera(cboestado.getSelectedItem().toString());
                System.out.println(lstcabecera.size());
                if (lstcabecera.size() > 0) {

                    listacomprobante();
                } else {
                    lstcabecera.removeAll(lstcabecera);
                    lstdetalle.removeAll(lstdetalle);
                    lstconsolidado.removeAll(lstconsolidado);
                    listadetalle();
                    listaconsolidado();
                    listacomprobante();
                    JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                    listacomprobante();
                }
            }
        }


    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnsalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnsalir1ActionPerformed

    private void tblistacabeceraMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacabeceraMouseReleased
        // TODO add your handling code here:
        int fila = tblistacabecera.getSelectedRow();
        if (fila >= 0) {
            LProgPagosCabecera lp = new LProgPagosCabecera();
            lstconsolidado = lp.buscaConsolidado(Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString()));
            lstdetalle = lp.lstdetalleprg(Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString()));
            listaconsolidado();
            listadetalle();
        }
    }//GEN-LAST:event_tblistacabeceraMouseReleased

    private void btnReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporte1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnReporte1ActionPerformed

    private void rbestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbestadoActionPerformed
        rbfecha.setSelected(false);
        rbestado.setSelected(true);

        dcfechainicio.setEnabled(false);
        dcfechafin.setEnabled(false);
        cboestado.setEnabled(true);
    }//GEN-LAST:event_rbestadoActionPerformed

    private void jScrollPane2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane2MouseClicked


    }//GEN-LAST:event_jScrollPane2MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgfiltro;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnReporte1;
    private javax.swing.JButton btnsalir1;
    private javax.swing.JComboBox cboestado;
    private com.toedter.calendar.JDateChooser dcfechafin;
    private com.toedter.calendar.JDateChooser dcfechainicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbltotalRegProgramacion;
    private javax.swing.JLabel lbltotalconsolidadoProg;
    private javax.swing.JLabel lbltotaldolares;
    private javax.swing.JLabel lbltotaldolaresd;
    private javax.swing.JLabel lbltotalsoles;
    private javax.swing.JLabel lbltotalsolesD;
    private javax.swing.JRadioButton rbestado;
    private javax.swing.JRadioButton rbfecha;
    private javax.swing.JTable tblistacabecera;
    private javax.swing.JTable tblstconsolidado;
    private javax.swing.JTable tblstdata;
    // End of variables declaration//GEN-END:variables
}
