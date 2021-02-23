/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Importar;

import Capa_Entidades.EDataVenta;
import Capa_Logica.LImportar;
import Capa_Logica.RenderDerecha;
import Capa_Logica.SesionUsuario;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;
import jxl.read.biff.BiffException;

/**
 *
 * @author EXPERTYA
 */
public class InterFrmImportarVentas extends javax.swing.JInternalFrame {
    ArrayList<EDataVenta> lstventa = new ArrayList<>();
    ArrayList<EDataVenta> lstdatacorrecta = new ArrayList<>();
    ArrayList<EDataVenta> lstdataincorrecta = new ArrayList<>();
    String titulos[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento", "Tipo de Comprobante de Pago o Doc",
        "Nro Serie Maquina Registradora", "Nro Comprobante Pago", "Tipo Doc. Identidad Cliente",
        "Nro. Doc. Identidad Cliente", "Razon Social Cliente", "Valor Facturado Export.",
        "Base Imponible de la Op. Gravada", "Importe Total Operacion Exonerada", "Importe Total Operacion Inafecta",
        "ISC", "IGV y/o IPM", "Otros Tributos", "Importe Total", "Tipo de Cambio",
        "Comprobante que se Modifica Fecha", "Comprobante o Doc. que se Modifica Tipo",
        "Comprobante o Doc. que se Modifica Serie", "Nro de Comprobante de Pago o Doc."};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    JDCargaRegistros jdc;
    JDCargaImportRegistros carga;
    String ruta = "";
    int periodoactual = 0;
    /**
     * Creates new form InterFrmImportarVentas
     */
    public InterFrmImportarVentas() {
        initComponents();
        this.setTitle("Importar Registro de Ventas e Ingresos");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblistaventas.setModel(modTbdatos);
        tblistaventas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblistaventas.getColumnModel().getColumn(9).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(10).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(11).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblistaventas.getColumnModel().getColumn(17).setCellRenderer(new RenderDerecha());
        btnvererrados.setVisible(false);
    }

    class PrimeThread implements Runnable {

         PrimeThread() {
         }

        public void run() {
            LImportar lim=new LImportar();
            for (int i = 0; i < lstdatacorrecta.size(); i++) {
                lstdatacorrecta.get(i).setImporteTotal(lstdatacorrecta.get(i).getImporteTotal().replace(".", ""));
                if(lim.InsertarRegistroVentaDet(lstdatacorrecta.get(i))){
                    
                }else{
                    System.out.println("NO GRABO: "+ lstdatacorrecta.get(i).getCorrelativo());
                    System.out.println("MONTO: " + lstdatacorrecta.get(i).getImporteTotal());
                }
            }
            jdc.setVisible(false);
            jdc.tiempo.stop();
            JOptionPane.showMessageDialog(null, "Se grabó correctamente la data del periodo " + charmes(mcMes.getMonth()) + "-" + mcAño.getYear() + ".");
            limpiar();
        }
     }
    
    class PrimeThread2 implements Runnable {

         PrimeThread2() {
             
         }

         @Override
        public void run() {
            lstventa.removeAll(lstventa);
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook=null;
             try {
                 workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
             } catch (IOException ex) {
                 Logger.getLogger(InterFrmImportarVentas.class.getName()).log(Level.SEVERE, null, ex);
             } catch (BiffException ex) {
                 Logger.getLogger(InterFrmImportarVentas.class.getName()).log(Level.SEVERE, null, ex);
             }
                Sheet sheet = workbook.getSheet(0);
                periodoactual=0;
                if(esDecimal(sheet.getCell(13, 19).getContents().toString().replace(",", "."))){
                   for (int fila = 19; fila < sheet.getRows(); fila++) {
                    if(sheet.getCell(0, fila).getContents().toString().trim().length()>0){
                        EDataVenta objventa=new EDataVenta();
                        objventa.setCorrelativo(Integer.valueOf(sheet.getCell(0, fila).getContents()));
                        objventa.setFechaEmision(sheet.getCell(1, fila).getContents());
                        objventa.setFechaVencimiento(sheet.getCell(3, fila).getContents());
                        objventa.setTipoCompPago(sheet.getCell(4, fila).getContents());
                        objventa.setNroSerieMaqReg(sheet.getCell(5, fila).getContents());
                        objventa.setNroComproPago(sheet.getCell(6, fila).getContents());
                        objventa.setTipoDocIdenClientt(sheet.getCell(9, fila).getContents());
                        objventa.setNroDocIdentCliente(sheet.getCell(11, fila).getContents());
                        objventa.setRazSocialCliente(sheet.getCell(12, fila).getContents());
                        if(sheet.getCell(13, fila).getContents().toString().trim().length()==0){
                            objventa.setValorFactExport("0");
                        }else{
                            objventa.setValorFactExport(sheet.getCell(13, fila).getContents());
                        }
                        if(sheet.getCell(16, fila).getContents().toString().trim().length()==0){
                            objventa.setBaseImpOpeGravada("0");
                        }else{
                            objventa.setBaseImpOpeGravada(sheet.getCell(16, fila).getContents());
                        }
                        if(sheet.getCell(17, fila).getContents().toString().trim().length()==0){
                        objventa.setImportTotalOpeExon("0");    
                        }else{
                            objventa.setImportTotalOpeExon(sheet.getCell(17, fila).getContents());
                        }
                        if(sheet.getCell(18, fila).getContents().toString().trim().length()==0){
                        objventa.setImportTotalOpeInaf("0");    
                        }else{
                        objventa.setImportTotalOpeInaf(sheet.getCell(18, fila).getContents());    
                        }
                        if(sheet.getCell(19, fila).getContents().toString().trim().length()==0){
                            objventa.setISC("0");
                        }else{
                            objventa.setISC(sheet.getCell(19, fila).getContents());
                        }
                        if(sheet.getCell(20, fila).getContents().toString().trim().length()==0){
                            objventa.setIGVyIPM("0");
                        }else{
                            objventa.setIGVyIPM(sheet.getCell(20, fila).getContents());
                        }
                        if(sheet.getCell(21, fila).getContents().toString().trim().length()==0){
                        objventa.setOtrosTributos("0");    
                        }else{
                        objventa.setOtrosTributos(sheet.getCell(21, fila).getContents());    
                        }
                        if(sheet.getCell(22, fila).getContents().toString().trim().length()==0){
                            objventa.setImporteTotal("0");
                        }else{
                            objventa.setImporteTotal(sheet.getCell(22, fila).getContents());
                        }
                        if(sheet.getCell(23, fila).getContents().toString().trim().length()==0){
                        objventa.setTipoCambio("0");    
                        }else{
                        objventa.setTipoCambio(sheet.getCell(23, fila).getContents());    
                        }
                        objventa.setTipoDocMod(sheet.getCell(25, fila).getContents());
                        objventa.setFechaDocMod(sheet.getCell(26, fila).getContents());
                        objventa.setSerieDocMod(sheet.getCell(28, fila).getContents());
                        objventa.setNroDocMod(sheet.getCell(29, fila).getContents());
                        lstventa.add(objventa);
                    }
                } 
                }else{
                    carga.setVisible(false);
                    JOptionPane.showMessageDialog(null, "El archivo que intenta cargar no cuenta con el formato adecuado.");
                }
                llenartabla();
                carga.setVisible(false);
        }
     }
    
    
    /**
     * 
     * 
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        mcAño = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();
        mcMes = new com.toedter.calendar.JMonthChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistaventas = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistro = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        btnguarda = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblnrocorrecto = new javax.swing.JLabel();
        lblnroerrados = new javax.swing.JLabel();
        btnvererrados = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbltotalbase = new javax.swing.JLabel();
        lbltotaligvsum = new javax.swing.JLabel();
        lbltotaligvcal = new javax.swing.JLabel();
        lbldifigv = new javax.swing.JLabel();
        lbldif = new javax.swing.JLabel();
        lblimptotalcal = new javax.swing.JLabel();
        lblimptotalsum = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel6.setText(":");

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPORTAR REGISTRO DE VENTAS E INGRESOS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1000, -1));

        jButton1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jButton1.setText("Importar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mcMes.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Periodo (MM-AAAA)");

        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Seleccione Periodo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N

        tblistaventas.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistaventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblistaventas);

        lblnroregistro.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnroregistro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnroregistro.setText("0");

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

        btnguarda.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguarda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguarda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel4.setText("Registros Correctos");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel5.setText("Registros Errados");

        lblnrocorrecto.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnrocorrecto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnrocorrecto.setText("0");
        lblnrocorrecto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblnroerrados.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnroerrados.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnroerrados.setText("0");
        lblnroerrados.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnvererrados.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnvererrados.setText("Ver");
        btnvererrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvererradosActionPerformed(evt);
            }
        });

        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Datos de Importación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel9.setText("Registros Importados");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel10.setText(":");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel11.setText(":");

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel17.setText("Base Imponible Operación Gravada");

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel13.setText("IGV y/o IPM Sumado");

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel16.setText("IGV y/o IPM Calculado");

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel15.setText("Diferencia");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel14.setText("Diferencia");

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel19.setText("Importe Total Calculado");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel18.setText("Importe Total Sumado");

        lbltotalbase.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbltotalbase.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotalbase.setText("0.00");

        lbltotaligvsum.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbltotaligvsum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotaligvsum.setText("0.00");

        lbltotaligvcal.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbltotaligvcal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotaligvcal.setText("0.00");

        lbldifigv.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbldifigv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldifigv.setText("0.00");

        lbldif.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbldif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldif.setText("0.00");

        lblimptotalcal.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblimptotalcal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblimptotalcal.setText("0.00");

        lblimptotalsum.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblimptotalsum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblimptotalsum.setText("0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(280, 280, 280)
                                        .addComponent(jButton1))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(110, 110, 110)
                                        .addComponent(mcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(210, 210, 210)
                                        .addComponent(mcAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(90, 90, 90)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel9))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel4)
                                        .addGap(21, 21, 21)
                                        .addComponent(lblnrocorrecto, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(lblnroerrados, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(190, 190, 190)
                                        .addComponent(btnvererrados))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(130, 130, 130)
                                        .addComponent(lblnroregistro, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(120, 120, 120)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(70, 70, 70)
                                .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnguarda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbltotalbase, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(lbltotaligvsum, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel15))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbldifigv, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbltotaligvcal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblimptotalsum, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbldif, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblimptotalcal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jButton1))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(mcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(mcAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblnrocorrecto))
                        .addGap(6, 6, 6)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblnroerrados))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnvererrados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lblnroregistro))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnguarda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel17)
                            .addComponent(lbltotalbase)
                            .addComponent(lbltotaligvsum))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(lbltotaligvcal))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(lbldifigv)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel19)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel14))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblimptotalsum)
                        .addGap(0, 0, 0)
                        .addComponent(lblimptotalcal)
                        .addGap(0, 0, 0)
                        .addComponent(lbldif)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public boolean esDecimal(String cad) {
        try {
            Double.parseDouble(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private void llenartabla(){
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        DecimalFormat formato2 = new DecimalFormat("###, ##0.000", simbolo);
        modTbdatos.setRowCount(0);
        lstdatacorrecta.removeAll(lstdatacorrecta);
        lstdataincorrecta.removeAll(lstdataincorrecta);
        double totaligvsum=0.0;
        double totalbase=0.0;
        double totaligvcal=0.0;
        double totaltotalsum=0.0;
        double totaltotalcalc=0.0;
        double otros=0.0;
        System.out.println("LISTA: " + lstventa.size());
        if(lstventa.size()>0){
            for (int i = 0; i < lstventa.size(); i++) {
                Object obj[]=new Object[22];
                obj[0]=lstventa.get(i).getCorrelativo();
                obj[1]=lstventa.get(i).getFechaEmision();
                if (lstventa.get(i).getFechaEmision().length() >= 10) {
                    if (validarperiodo(lstventa.get(i).getFechaEmision())) {
                        lstdatacorrecta.add(lstventa.get(i));
                    } else {
                        lstdataincorrecta.add(lstventa.get(i));
                    }
                }else{
                    if (validarperiodode8(lstventa.get(i).getFechaEmision())) {
                        lstdatacorrecta.add(lstventa.get(i));
                    } else {
                        lstdataincorrecta.add(lstventa.get(i));
                    }
                }
                obj[2]=lstventa.get(i).getFechaVencimiento();
                obj[3]=lstventa.get(i).getTipoCompPago();
                obj[4]=lstventa.get(i).getNroSerieMaqReg();
                obj[5]=lstventa.get(i).getNroComproPago();
                obj[6]=lstventa.get(i).getTipoDocIdenClientt();
                obj[7] = lstventa.get(i).getNroDocIdentCliente();
                obj[8] = lstventa.get(i).getRazSocialCliente();
                obj[9] = formato.format(Double.parseDouble(lstventa.get(i).getValorFactExport().replace(".","").replaceAll(",", ".")));
                otros=otros+Double.parseDouble(lstventa.get(i).getValorFactExport().replace(".","").replaceAll(",", "."));
                obj[10] = formato.format(Double.parseDouble(lstventa.get(i).getBaseImpOpeGravada().replace(".","").replaceAll(",", ".")));
                totalbase=totalbase+Double.parseDouble(lstventa.get(i).getBaseImpOpeGravada().replace(".","").replaceAll(",", "."));
                obj[11] = formato.format(Double.parseDouble(lstventa.get(i).getImportTotalOpeExon().replace(".","").replaceAll(",", ".")));
                otros=otros+Double.parseDouble(lstventa.get(i).getImportTotalOpeExon().replace(".","").replaceAll(",", "."));
                if(lstventa.get(i).getImportTotalOpeInaf().trim().length()>0){
                    obj[12] = formato.format(Double.parseDouble(lstventa.get(i).getImportTotalOpeInaf().replace(".","").replaceAll(",", ".")));
                    otros=otros+Double.parseDouble(lstventa.get(i).getImportTotalOpeInaf().replace(".","").replaceAll(",", "."));
                } else {
                    obj[12] = "0.00";
                }
                obj[13] = formato.format(Double.parseDouble(lstventa.get(i).getISC().replace(".","").replaceAll(",", ".")));
                otros=otros+Double.parseDouble(lstventa.get(i).getISC().replace(".","").replaceAll(",", "."));
                obj[14] = formato.format(Double.parseDouble(lstventa.get(i).getIGVyIPM().replace(".","").replaceAll(",", ".")));
                totaligvsum=totaligvsum+Double.parseDouble(lstventa.get(i).getIGVyIPM().replace(".","").replaceAll(",", "."));
                obj[15] = formato.format(Double.parseDouble(lstventa.get(i).getOtrosTributos().replace(".","").replaceAll(",", ".")));
                otros=otros+Double.parseDouble(lstventa.get(i).getOtrosTributos().replace(".","").replaceAll(",", "."));
                obj[16] = formato.format(Double.parseDouble(lstventa.get(i).getImporteTotal().replace(".","").replaceAll(",", ".")));
                totaltotalsum=totaltotalsum+Double.parseDouble(lstventa.get(i).getImporteTotal().replace(".","").replaceAll(",", "."));
                if(lstventa.get(i).getTipoCambio().trim().length()>0){
                obj[17] = formato2.format(Double.parseDouble(lstventa.get(i).getTipoCambio().replace(".","").replaceAll(",", ".")));    
                }else{
                obj[17] = "0.000";    
                }
                obj[18] = lstventa.get(i).getTipoDocMod();
                obj[19] = lstventa.get(i).getFechaDocMod();
                obj[20] = lstventa.get(i).getSerieDocMod();
                obj[21] = lstventa.get(i).getNroDocMod();
                modTbdatos.addRow(obj);
            }
        }
        tblistaventas.setModel(modTbdatos);
        lblnroregistro.setText(modTbdatos.getRowCount()+"");
        lblnrocorrecto.setText(""+lstdatacorrecta.size());
        lblnroerrados.setText(""+lstdataincorrecta.size());
        lbltotalbase.setText(""+formato.format(totalbase));
        totaligvcal=totalbase*0.18;
        lbltotaligvsum.setText(formato.format(totaligvsum));
        lbltotaligvcal.setText(formato.format(totaligvcal));
        lbldifigv.setText(formato.format((totaligvcal-totaligvsum)));
        lblimptotalsum.setText(formato.format(totaltotalsum));
        totaltotalcalc=totalbase+totaligvsum+otros;
        lblimptotalcal.setText(formato.format(totaltotalcalc));
        lbldif.setText(formato.format((totaltotalcalc-totaltotalsum)));
        if(lstdataincorrecta.size()>0){
            btnvererrados.setVisible(true);
        }else{
            btnvererrados.setVisible(false);
        }
        if(periodoactual==0 && tblistaventas.getRowCount()>0){
            JOptionPane.showMessageDialog(this, "No hay registros para el período a importar.");
        }
    }
    
    public String charmes(int mes) {
        switch (mes) {
            case 0:
                return "01";
            case 1:
                return "02";
            case 2:
                return "03";
            case 3:
                return "04";
            case 4:
                return "05";
            case 5:
                return "06";
            case 6:
                return "07";
            case 7:
                return "08";
            case 8:
                return "09";
            case 9:
                return "10";
            case 10:
                return "11";
            case 11:
                return "12";
        }
        return "Error";
    }
    
    private boolean validarExiste(int mes, int año) {
        LImportar lim = new LImportar();
        if (lim.ValVenExiste(mes, año) > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    private void limpiar() {
        lstventa.removeAll(lstventa);
        lstdatacorrecta.removeAll(lstdatacorrecta);
        lstdataincorrecta.removeAll(lstdataincorrecta);
        llenartabla();
    }
    
    private boolean validarperiodo(String Fecha) {
        int año;
        int mes;
        año = mcAño.getYear();
        mes = mcMes.getMonth() + 1;
        if (Integer.valueOf(Fecha.trim().substring(6, 10)) == año && Integer.valueOf(Fecha.trim().substring(3, 5)) <= mes) {
            periodoactual=periodoactual+1;
            return true;
        } else {
            if (Integer.valueOf(Fecha.trim().substring(6, 10)) < año) {
                    return true;
                } else {
                    return false;
                }
        }
    }
     
     public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = InterFrmImportarVentas.this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2) + 50;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2) + 20;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
     
     private boolean validarperiodode8(String Fecha) {
         int año;
         int mes;
         año = mcAño.getYear();
         mes = mcMes.getMonth() + 1;
         System.out.println(Fecha.trim().substring(6, 8));
         if (Integer.valueOf(Fecha.trim().substring(6, 8)) + 2000 == año && Integer.valueOf(Fecha.trim().substring(3, 5)) == mes) {
             periodoactual=periodoactual+1;
             return true;
         } else {
             if (Integer.valueOf(Fecha.trim().substring(6, 8))+2000 == año && Integer.valueOf(Fecha.trim().substring(3, 5)) < mes) {
                    return true;
                } else {
                    if(Integer.valueOf(Fecha.trim().substring(6, 8))+2000 < año){
                        return true;
                    }else{
                        return false;
                    }
                }
         }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        try {
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                carga = new JDCargaImportRegistros(null, true);
                PrimeThread2 prt = new PrimeThread2();
                new Thread(prt).start();
                carga.setVisible(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
            System.out.println(e);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here:
        limpiar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnguardaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardaActionPerformed
        // TODO add your handling code here:
       int mes = mcMes.getMonth() + 1;
        int año = mcAño.getYear();
        int reemp = 0;
        LImportar lim = new LImportar();
        if(lstdatacorrecta.size()>0){
            jdc = new JDCargaRegistros(null, true);
            if (validarExiste(mes, año)) {
                reemp = 0;
            } else {
                reemp = 1;
            }
            SesionUsuario.nroaregistrar = lstdatacorrecta.size();
            if (reemp == 0) {
                if (lstdataincorrecta.size() > 0) {
                    if (JOptionPane.showConfirmDialog(this, "Tiene registros errados. Va a GRABAR solo la data correcta del periodo " + charmes(mcMes.getMonth()) + "-" + mcAño.getYear() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        lim.InsertarRegistroVentaCab(mes, año);
                        int cod = lim.CodRegistroVenta(mes, año);
                        for (int i = 0; i < lstdatacorrecta.size(); i++) {
                            lstdatacorrecta.get(i).setCodigo(cod);
                        }
                        PrimeThread prt = new PrimeThread();
                        new Thread(prt).start();
                        jdc.setVisible(true);
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Va a GRABAR la data del periodo " + charmes(mcMes.getMonth()) + "-" + mcAño.getYear() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        lim.InsertarRegistroVentaCab(mes, año);
                        int cod = lim.CodRegistroVenta(mes, año);
                        for (int i = 0; i < lstdatacorrecta.size(); i++) {
                            lstdatacorrecta.get(i).setCodigo(cod);
                        }
                        PrimeThread prt = new PrimeThread();
                        new Thread(prt).start();
                        jdc.setVisible(true);
                    }
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Ya cuenta con data para este periodo . ¿Desea Reemplazarla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    int cod = lim.CodRegistroVenta(mes, año);
                    if (lim.LimpiarRegistroVentaDet(cod)) {
                        for (int i = 0; i < lstdatacorrecta.size(); i++) {
                            lstdatacorrecta.get(i).setCodigo(cod);
                        }
                        PrimeThread prt = new PrimeThread();
                        new Thread(prt).start();
                        jdc.setVisible(true);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cuenta con datos correctos para grabar.");
        }
    }//GEN-LAST:event_btnguardaActionPerformed

    private void btnvererradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvererradosActionPerformed
        // TODO add your handling code here:
        InterFrmVerErradosVenta vererrados=new InterFrmVerErradosVenta();
        Menu_Importar.dpane.add(vererrados);
        vererrados.llenartabla(lstdataincorrecta);
        centerJIF(vererrados);
        vererrados.setVisible(true);
    }//GEN-LAST:event_btnvererradosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguarda;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnvererrados;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbldif;
    private javax.swing.JLabel lbldifigv;
    private javax.swing.JLabel lblimptotalcal;
    private javax.swing.JLabel lblimptotalsum;
    private javax.swing.JLabel lblnrocorrecto;
    private javax.swing.JLabel lblnroerrados;
    private javax.swing.JLabel lblnroregistro;
    private javax.swing.JLabel lbltotalbase;
    private javax.swing.JLabel lbltotaligvcal;
    private javax.swing.JLabel lbltotaligvsum;
    private com.toedter.calendar.JYearChooser mcAño;
    private com.toedter.calendar.JMonthChooser mcMes;
    private javax.swing.JTable tblistaventas;
    // End of variables declaration//GEN-END:variables
}
