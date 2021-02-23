/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EConsolidado;
import Capa_Entidades.ECuentaBancaria;
import Capa_Entidades.EParametro;
import Capa_Entidades.EProgPagosCabecera;
import Capa_Logica.LAgenteRetenedores;
import Capa_Logica.LComprobante;
import Capa_Logica.LCuentaBancaria;
import Capa_Logica.LParametro;
import Capa_Logica.LProgPagosCabecera;
import Capa_Logica.RenderDerecha;
import Capa_Logica.RendererPersonalizado;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class MantenimientoProgramación extends javax.swing.JInternalFrame {
     ArrayList<EParametro> liscuenpara = new ArrayList<>();
    ArrayList<EParametro> lisbanpara = new ArrayList<>();
    ArrayList<ECuentaBancaria> liscuenta = new ArrayList<>();
    ArrayList<EConsolidado> lstconsolidado = new ArrayList<>();
    ArrayList<EComprobante> lstdetalle = new ArrayList();
    ArrayList<EProgPagosCabecera> lstcabecera = new ArrayList<>();
    String titulos[] = {"CodProg", "Estado", "Fecha", "Banco", "Moneda", "NroCuentaOrigen"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    String titulos2[] = {"CodProg", "DocProveedor", "RazonSocial", "PagarSoles", "PagarDolares","Mensaje"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    String titulos3[] = {"CodProg", "CodCompr", "TipoComprobante", "NumComp", "Fecha", "DocProv", "Glosa", "CompSujeto",
        "FormaPago", "MontoSoles", "MontoDolares", "Estado", "Banco", "Moneda", "CtaBancaria"};
    DefaultTableModel modTbdatos3 = new DefaultTableModel(titulos3, 0);
    int opc = 0;
    int codcompro;
    LProgPagosCabecera lpro = new LProgPagosCabecera();
    int fila2=-1;
    int filacons=-1;
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    
    
    /**
     * Creates new form MantenimientoProgramación
     */
    public MantenimientoProgramación() {
        initComponents();
        this.setTitle("Mantenimiento de Programación");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
         Date hoy=new Date();
          simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        dcfechainicio.setDate(hoy);
        dcfechafin.setDate(hoy);
        tblistacabecera.setModel(modTbdatos);
        tblistacabecera.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblistacabecera.getTableHeader().setReorderingAllowed(false);
        tblistacabecera.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblistacabecera.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblistacabecera.getColumnModel().getColumn(2).setPreferredWidth(75);
        tblistacabecera.getColumnModel().getColumn(3).setPreferredWidth(125);
         tblistacabecera.getColumnModel().getColumn(4).setPreferredWidth(125);
        tblistconsolidado.setModel(modTbdatos2);   
        tblistconsolidado.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblistconsolidado.getTableHeader().setReorderingAllowed(false);
        tblistconsolidado.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblistconsolidado.getColumnModel().getColumn(1).setPreferredWidth(75);
        tblistconsolidado.getColumnModel().getColumn(2).setPreferredWidth(175);
        tblistconsolidado.getColumnModel().getColumn(3).setPreferredWidth(75);       
        tblistconsolidado.getColumnModel().getColumn(4).setPreferredWidth(75);       
        tblistconsolidado.getColumnModel().getColumn(5).setPreferredWidth(175); 
        tblistconsolidado.getColumnModel().getColumn(3).setCellRenderer(new RenderDerecha());
        tblistconsolidado.getColumnModel().getColumn(4).setCellRenderer(new RenderDerecha());
        llenarbancoprincipal();
        tblstdetalle.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstdetalle.getTableHeader().setReorderingAllowed(false);
        tblstdetalle.setVisible(false);
        jScrollPane3.setVisible(false);
        jPanel9.setVisible(false);
    }
    
    
      private void llenarbancoprincipal() {
        LParametro limp = new LParametro();
        lisbanpara = limp.ListaBancoPrincipal();
    }
    
    
     private void llenarcuentaprincipal() {
           LParametro limp = new LParametro();
        liscuenpara = limp.ListaCuentasPrincipal();
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
        lblnumprogramaciones.setText(tblistacabecera.getRowCount() + " Comprobante(es)");
    }
    
     private void listaconsolidado() {
         DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos2.setRowCount(0);
        double montosoles=0.0;
        double montodolares=0.0;
        if (lstconsolidado.size() > 0) {
            for (int i = 0; i < lstconsolidado.size(); i++) {
                Object nuevafila[] = new Object[6];
                nuevafila[0] = lstconsolidado.get(i).getCodProgramacion();
                nuevafila[1] = lstconsolidado.get(i).getDocProv();
                nuevafila[2] = lstconsolidado.get(i).getRazonSocial();
                nuevafila[3] = formato.format(lstconsolidado.get(i).getMontosoles());
                montosoles=montosoles+lstconsolidado.get(i).getMontosoles();
                nuevafila[4] = formato.format(lstconsolidado.get(i).getMontodolares());
                montodolares=montodolares+lstconsolidado.get(i).getMontodolares();
                if(lstconsolidado.get(i).getMontosoles()>=700){
                    LAgenteRetenedores la=new LAgenteRetenedores();
                    if(la.VerificarAgente(lstconsolidado.get(i).getDocProv())>0){
                        nuevafila[5]= "CORRECTO";
                    }else{
                        if(la.VerificarConsolidado(lstconsolidado.get(i).getCodProgramacion(), lstconsolidado.get(i).getDocProv())>0){
                            nuevafila[5]= "NECESITA APLICAR RET/DET";
                        }else{
                            nuevafila[5]= "CORRECTO";
                        }
                    }
                }else{
                    nuevafila[5]= "CORRECTO";
                }
                modTbdatos2.addRow(nuevafila);
            }
        }
        tblistconsolidado.setModel(modTbdatos2);
        lbltotalsoles.setText("MONTO SOLES: "+formato.format(montosoles));
        lbltotaldolares.setText("MONTO DOLARES: "+formato.format(montodolares));
        lblnumcuentas.setText(tblistconsolidado.getRowCount() + "Registro(s)");
    }
     
    private void listadetalle() {
        modTbdatos3.setRowCount(0);
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
                nuevafila[11] = lstdetalle.get(i).getEstado();
                nuevafila[12] = lstdetalle.get(i).getUsuModi();
                nuevafila[13] = lstdetalle.get(i).getNomMoneda();
                nuevafila[14] = lstdetalle.get(i).getCuentasoles();
                modTbdatos3.addRow(nuevafila);
            }
        }
        tblstdetalle.setModel(modTbdatos3);
        lblnumcuentas1.setText(tblstdetalle.getRowCount() + "Registro(s)");
    }
     
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2)+250;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2);
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
       
    String fechaformato(Date fecha) {
        String fec = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        return fec;
    }
    
    public void centerJIF2(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2 );
        int height = ((desktopSize.height - jInternalFrameSize.height) /2)-40;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dcfechafin = new com.toedter.calendar.JDateChooser()

        ;
        dcfechainicio = new com.toedter.calendar.JDateChooser()

        ;
        btnBuscar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnsalir2 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        lblnumprogramaciones = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        lblnumcuentas = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacabecera = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblistconsolidado = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel24 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblnumcuentas1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblstdetalle = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jLabel3 = new javax.swing.JLabel();
        lbltotaldolares = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbltotalsoles = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        setMaximumSize(new java.awt.Dimension(589, 570));
        setMinimumSize(new java.awt.Dimension(589, 570));
        setPreferredSize(new java.awt.Dimension(589, 570));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText("Fec. Fin");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, -1, 20));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel9.setText("Fec. Ini");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 35, -1, 20));

        dcfechafin.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcfechafin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcfechafinKeyTyped(evt);
            }
        });
        getContentPane().add(dcfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 55, -1, -1));

        dcfechainicio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcfechainicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcfechainicioKeyTyped(evt);
            }
        });
        getContentPane().add(dcfechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 35, -1, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 70, 30));

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        getContentPane().add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 70, 30));

        btnsalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalir2ActionPerformed(evt);
            }
        });
        getContentPane().add(btnsalir2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 50, 70, 30));

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 204, 0));
        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PROGRAMACIONES PENDIENTES");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 250, 20));

        lblnumprogramaciones.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnumprogramaciones.setText("0 Registro(s)");
        jPanel7.add(lblnumprogramaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 60, 20));

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 560, 20));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblnumcuentas.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnumcuentas.setText("0 Registro(s)");
        jPanel8.add(lblnumcuentas, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 70, 20));

        jLabel5.setBackground(new java.awt.Color(255, 204, 0));
        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("CONSOLIDADO POR PROVEEDOR");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 540, 20));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 213, 560, -1));

        jPanel1.setBackground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 0, 0));
        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE PROGRAMACIÓN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 560, 18));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseEntered(evt);
            }
        });

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
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblistacabeceraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblistacabecera);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 560, 90));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 25, 150, 60));

        tblistconsolidado.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistconsolidado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblistconsolidado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblistconsolidadoMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblistconsolidado);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 233, 560, 280));

        jLabel24.setBackground(new java.awt.Color(255, 255, 255));
        jLabel24.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 560, 70));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblnumcuentas1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnumcuentas1.setText("0 Registro(s)");
        jPanel9.add(lblnumcuentas1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 70, 20));

        jLabel6.setBackground(new java.awt.Color(255, 204, 0));
        jLabel6.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DETALLE POR PROVEEDOR");
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 530, 20));

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 560, -1));

        tblstdetalle.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblstdetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tblstdetalle);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 560, 110));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jLabel3.setText("*Doble clic para modificar Programación");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 180, -1));

        lbltotaldolares.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbltotaldolares.setText("TOTAL DOLARES: ");
        getContentPane().add(lbltotaldolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 516, 150, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jLabel8.setText("*Doble clic para aplicar Ret/Det.");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 516, 150, -1));

        lbltotalsoles.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        lbltotalsoles.setText("TOTAL SOLES: ");
        getContentPane().add(lbltotalsoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 516, 150, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dcfechafinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcfechafinKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcfechafinKeyTyped

    private void dcfechainicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcfechainicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcfechainicioKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        opc=1;
         // FILTRO POR FECHA
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
                } else {
                    LProgPagosCabecera lo = new LProgPagosCabecera();
                    lstcabecera = lo.ListaxFecha(fechaformato(dcfechainicio.getDate()), fechaformato(dcfechafin.getDate()));
                    if (lstcabecera.size() > 0) {
                        listacomprobante();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                        lstcabecera.removeAll(lstcabecera);
                        listacomprobante();
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int fila = tblistacabecera.getSelectedRow();;
        if (fila >= 0) {
            String estadoactual = tblistacabecera.getValueAt(fila, 1).toString().trim();
            if (!estadoactual.equalsIgnoreCase("EJECUTADO")) {
                codcompro = Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString());
                if (JOptionPane.showConfirmDialog(null, "Se va Eliminar la Programación . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    if (lpro.EliminarProgramacion(codcompro)) {
                        LProgPagosCabecera lo = new LProgPagosCabecera();
                        lstcabecera = lo.ListaxFecha(fechaformato(dcfechainicio.getDate()), fechaformato(dcfechafin.getDate()));
                        listacomprobante();
                        lstdetalle.removeAll(lstdetalle);
                        lstconsolidado.removeAll(lstconsolidado);
                        listadetalle();
                        listaconsolidado();
                        JOptionPane.showMessageDialog(this, "Se ELIMINÓ correctamente la Programación.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Solo puede eliminar programaciones pendientes.");
            }
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnsalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalir2ActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalir2ActionPerformed

    private void jScrollPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseEntered
       
    }//GEN-LAST:event_jScrollPane1MouseEntered

    private void tblistacabeceraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacabeceraMouseClicked
        try{
         int fila= tblistacabecera.getSelectedRow();
        if (fila >= 0) {
            LProgPagosCabecera lp = new LProgPagosCabecera();
            lstconsolidado = lp.buscaConsolidado(Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString()));
            lstdetalle = lp.lstdetalleprg(Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString()));
            listaconsolidado();
            listadetalle();
            if(fila==fila2){
                System.out.print("asdasdasd " + tblistacabecera.getValueAt(fila, 0).toString());
                System.out.print("asdasdasd " + tblistacabecera.getValueAt(fila, 3).toString());
                ModProgramacionPagos mpp=new ModProgramacionPagos();
                LComprobante lc=new LComprobante();
                Menu.dpane2.add(mpp);
                mpp.codigomax=Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString());
                mpp.modificacion(lc.ConsultaListaDetalle(Integer.valueOf(tblistacabecera.getValueAt(fila, 0).toString())));
                switch(tblistacabecera.getValueAt(fila, 3).toString()){
                    case "BCP":
                        mpp.cbobancoprincipal.setSelectedIndex(1);
                        break;
                    case "Interbank":
                        mpp.cbobancoprincipal.setSelectedIndex(2);
                        break;
                    case "Santander":
                        mpp.cbobancoprincipal.setSelectedIndex(2);
                        break;    
                     default:
                          break;
                }
//                if(tblistacabecera.getValueAt(fila, 3).toString().equalsIgnoreCase("BCP")){
//                    mpp.cbobancoprincipal.setSelectedIndex(1);
//                }else{
//                    mpp.cbobancoprincipal.setSelectedIndex(1);
//                }
                if(tblistacabecera.getValueAt(fila, 4).toString().equalsIgnoreCase("SOLES")){
                    mpp.cbocuentaprincipal.setSelectedIndex(1);
                }else{
                    mpp.cbocuentaprincipal.setSelectedIndex(2);
                }
                centerJIF(mpp);
                mpp.setVisible(true);
            }else{
                fila2=fila;
            }
        }
        }catch(Exception e){
         JOptionPane.showMessageDialog(this, e.getMessage());
        }
       
    }//GEN-LAST:event_tblistacabeceraMouseClicked

    private void tblistconsolidadoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistconsolidadoMouseReleased
        // TODO add your handling code here:
        int fila=tblistconsolidado.getSelectedRow();
        if(filacons==fila){
            String estado=tblistconsolidado.getValueAt(fila, 5).toString().trim();
            if(!estado.equalsIgnoreCase("CORRECTO")){
                LProgPagosCabecera lp=new LProgPagosCabecera();
                ProgramacionSinRetDet progsin=new ProgramacionSinRetDet();
                Menu.dpane2.add(progsin);
                centerJIF2(progsin);
                progsin.lstdetalle=lp.lstdetallexprov(Integer.valueOf(tblistconsolidado.getValueAt(fila, 0).toString()), tblistconsolidado.getValueAt(fila, 1).toString());
                progsin.listadetalle(lp.lstdetallexprov(Integer.valueOf(tblistconsolidado.getValueAt(fila, 0).toString()), tblistconsolidado.getValueAt(fila, 1).toString()));
                progsin.moneda=tblistacabecera.getValueAt(tblistacabecera.getSelectedRow(), 4).toString();
                progsin.setVisible(true);
            }
        }else{
            filacons=fila;
        }
    }//GEN-LAST:event_tblistconsolidadoMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnsalir2;
    private com.toedter.calendar.JDateChooser dcfechafin;
    public com.toedter.calendar.JDateChooser dcfechainicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblnumcuentas;
    private javax.swing.JLabel lblnumcuentas1;
    private javax.swing.JLabel lblnumprogramaciones;
    private javax.swing.JLabel lbltotaldolares;
    private javax.swing.JLabel lbltotalsoles;
    private javax.swing.JTable tblistacabecera;
    private javax.swing.JTable tblistconsolidado;
    private javax.swing.JTable tblstdetalle;
    // End of variables declaration//GEN-END:variables
}
