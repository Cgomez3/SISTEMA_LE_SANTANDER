/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Importar;

import Capa_Entidades.EDataCompra;
import Capa_Logica.LImportar;
import Capa_Logica.RenderDerecha;
import Capa_Logica.SesionUsuario;
import Capa_Presentacion.MenuLibrosElectronico;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
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
public class InterFrmImportarCompras extends javax.swing.JInternalFrame {
    ArrayList<EDataCompra> lstdatacompra = new ArrayList<>();
    ArrayList<EDataCompra> lstdatacorrecta = new ArrayList<>();
    ArrayList<EDataCompra> lstdataincorrecta = new ArrayList<>();
    String titulos[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento", "Tipo de Comprobante de Pago o Doc",
        "Serie Codigo de Dependencia Aduanera", "Año de Emisión de DUA o DSI", "Documentos Emitidos por Sunat para",
        "Tipo Doc. Identidad Prov.", "Nro. Doc. Identidad Prov.", "Razon Social Proveedor", "Adquisicion Gravadas Base Imponible",
        "Adquisicion Gravadas IGV", "Gravadas y No Gravadas Base Imponible", "Gravadas y No Gravadas IGV", "No Gravadas Base Imponible",
        "No Gravadas IGV", "Adquisiciones No Gravadas", "ISC", "Otros Tributos y Cargos", "Importe Total", "Nro Comprobante No Domiciliado",
        "Nro Constancia de Detracción", "Fecha de Emisión de Constancia de Detracción", "Tipo de Cambio",
        "Comprobante que se Modifica Fecha", "Comprobante que se Modifica Tipo", "Comprobante que se Modifica Serie",
        "Nro de Comprobante de Pago o Doc"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    JDCargaRegistros jdc;
    int periodoactual=0;
    /**
     * Creates new form InterFrmImportarCompras
     */
    public InterFrmImportarCompras() {
        initComponents();
        this.setTitle("Importar Registro de Compras");
        tblistadatoscompra.setModel(modTbdatos);
        tblistadatoscompra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblistadatoscompra.getTableHeader().setReorderingAllowed(false);
        tblistadatoscompra.getColumnModel().getColumn(10).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(11).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(12).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(13).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(14).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(15).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(16).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(18).setCellRenderer(new RenderDerecha());
        tblistadatoscompra.getColumnModel().getColumn(19).setCellRenderer(new RenderDerecha());
        btnvererrados.setVisible(false);
    }
    
    class PrimeThread implements Runnable {

        PrimeThread() {
        }

        public void run() {
            LImportar lim = new LImportar();
            System.out.println("TOTAL: " + lstdatacorrecta.size());
            for (int i = 0; i < lstdatacorrecta.size(); i++) {
                lim.InsertarRegistroCompraDet(lstdatacorrecta.get(i));
                System.out.println("GRABANDO: " + (i + 1));
            }
            jdc.setVisible(false);
            jdc.tiempo.stop();
            JOptionPane.showMessageDialog(null, "Se grabó correctamente la data del periodo " + charmes(mcMes.getMonth()) + "-" + mcAño.getYear() + ".");
            limpiar();
        }
    }

    private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos.setRowCount(0);
        lstdatacorrecta.removeAll(lstdatacorrecta);
        lstdataincorrecta.removeAll(lstdataincorrecta);
        double total=0.0;
        double totalbimp=0.0;
        double totaligv=0.0;
        double totalcal=0.0;
        double totaldemas=0.0;
        double importetotalcal=0.0;
        double dif=0.0;
        if(lstdatacompra.size()>0){
            periodoactual=0;
            for (int i = 0; i < lstdatacompra.size(); i++) {
                Object obj[]=new Object[28];
                obj[0]=lstdatacompra.get(i).getFila();
                obj[1]=lstdatacompra.get(i).getFechaEmision();
                if (lstdatacompra.get(i).getFechaEmision().trim().length() == 10) {
                    if (validarperiodo(lstdatacompra.get(i).getFechaEmision())) {
                        lstdatacorrecta.add(lstdatacompra.get(i));
                    } else {
                        lstdataincorrecta.add(lstdatacompra.get(i));
                    }
                }
                obj[2]=lstdatacompra.get(i).getFechaVencimiento();
                obj[3]=lstdatacompra.get(i).getTipoComprobantePago();
                obj[4]=lstdatacompra.get(i).getSerieCodDepenAduanera();
                obj[5]=lstdatacompra.get(i).getAñoEmisionDua();
                obj[6]=lstdatacompra.get(i).getDocumentosEmitidos();
                obj[7]=lstdatacompra.get(i).getTipoDocuIdenProv();
                obj[8]=lstdatacompra.get(i).getNroDocIdentProv();
                obj[9]=lstdatacompra.get(i).getRazonSocialProv();
                obj[10]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGOBaseDisponible().replace(".","").replaceAll(",", ".")));
                totalbimp=totalbimp+Double.parseDouble(lstdatacompra.get(i).getGOBaseDisponible().replace(".","").replaceAll(",", "."));
                obj[11]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGOIGV().replace(".","").replaceAll(",", ".")));
                totaligv=totaligv+Double.parseDouble(lstdatacompra.get(i).getGOIGV().replace(".","").replaceAll(",", "."));
                obj[12]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGYGBaseDisponible().replace(".","").replaceAll(",", ".")));
                totaldemas=totaldemas+Double.parseDouble(lstdatacompra.get(i).getGYGBaseDisponible().replace(".","").replaceAll(",", "."));
                obj[13]=formato.format(Double.parseDouble(lstdatacompra.get(i).getGYGIGV().replace(".","").replaceAll(",", ".")));
                totaldemas=totaldemas+Double.parseDouble(lstdatacompra.get(i).getGYGIGV().replace(".","").replaceAll(",", "."));
                obj[14]=formato.format(Double.parseDouble(lstdatacompra.get(i).getNGBaseDisponible().replace(".","").replaceAll(",", ".")));
                totaldemas=totaldemas+Double.parseDouble(lstdatacompra.get(i).getNGBaseDisponible().replace(".","").replaceAll(",", "."));
                obj[15]=formato.format(Double.parseDouble(lstdatacompra.get(i).getNGIGV().replace(".","").replaceAll(",", ".")));
                totaldemas=totaldemas+Double.parseDouble(lstdatacompra.get(i).getNGIGV().replace(".","").replaceAll(",", "."));
                obj[16]=formato.format(Double.parseDouble(lstdatacompra.get(i).getAdquisionesNG().replace(".","").replaceAll(",", ".")));
                totaldemas=totaldemas+Double.parseDouble(lstdatacompra.get(i).getAdquisionesNG().replace(".","").replaceAll(",", "."));
                obj[17]=lstdatacompra.get(i).getISC();
                obj[18]=formato.format(Double.parseDouble(lstdatacompra.get(i).getOtrosTribyCarg().replace(".","").replaceAll(",", ".")));
                totaldemas=totaldemas+Double.parseDouble(lstdatacompra.get(i).getOtrosTribyCarg().replace(".","").replaceAll(",", "."));
                obj[19]=formato.format(Double.parseDouble(lstdatacompra.get(i).getImporteTotal().replace(".","").replaceAll(",", ".")));
                total=total+Double.parseDouble(lstdatacompra.get(i).getImporteTotal().replace(".","").replaceAll(",", "."));
                obj[20]=lstdatacompra.get(i).getNComprNoDomic();
                obj[21]=lstdatacompra.get(i).getNroConstanciaDetraccion();
                obj[22]=lstdatacompra.get(i).getFechaEmisionConsDetracc();
                obj[23]=lstdatacompra.get(i).getTipoDeCambio().replace(",", ".");
                obj[24]=lstdatacompra.get(i).getCMFecha();
                obj[25]=lstdatacompra.get(i).getCMTipo();
                obj[26]=lstdatacompra.get(i).getCMSerie();
                obj[27]=lstdatacompra.get(i).getCMNCPagoDoc();
                modTbdatos.addRow(obj);
            }
        }
        tblistadatoscompra.setModel(modTbdatos);
        totalcal=totalbimp*0.18;
        dif=totaligv-totalcal;
        lbltotal.setText(formato.format(total));
        lblbaseimponible.setText(formato.format(totalbimp));
        lbligvsum.setText(formato.format(totaligv));
        lbligvmul.setText(formato.format(totalcal));
        lbldif.setText(formato.format(dif));
        importetotalcal=totalbimp+totaligv+totaldemas;
        lbltotalcal.setText(formato.format((importetotalcal)));
        lbldiftotal.setText(formato.format((total-importetotalcal)));
        lblnroregistros.setText(modTbdatos.getRowCount()+ "");
        lblnrocorrecto.setText(""+lstdatacorrecta.size());
        int errados=modTbdatos.getRowCount()-lstdatacorrecta.size();
        lblnroerrados.setText(""+errados);
        if(errados>0){
            btnvererrados.setVisible(true);
        }else{
            btnvererrados.setVisible(false);
        }
        if (periodoactual == 0 && tblistadatoscompra.getRowCount() > 0) {
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
    
    private boolean validarperiodo(String Fecha) {
        int año;
        int mes;
        año = mcAño.getYear();
        mes = mcMes.getMonth() + 1;
        if (Integer.valueOf(Fecha.trim().substring(6, 10)) == año && Integer.valueOf(Fecha.trim().substring(3, 5)) == mes) {
            periodoactual=periodoactual+1;
            return true;
        } else {
            if (Integer.valueOf(Fecha.trim().substring(6, 10)) < año) {
                return true;
            } else {
                if (Integer.valueOf(Fecha.trim().substring(6, 10)) == año && Integer.valueOf(Fecha.trim().substring(3, 5)) < mes) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }
    
    private void limpiar(){
        lstdatacompra.removeAll(lstdatacompra);
        lstdatacorrecta.removeAll(lstdatacorrecta);
        lstdataincorrecta.removeAll(lstdataincorrecta);
        llenartabla();
    }
 
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = InterFrmImportarCompras.this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2) + 50;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2) + 20;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
    private boolean validarExiste(int mes, int año) {
        LImportar lim = new LImportar();
        if (lim.ValExiste(mes, año) > 0) {
            return false;
        } else {
            return true;
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mcMes = new com.toedter.calendar.JMonthChooser();
        mcAño = new com.toedter.calendar.JYearChooser();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistadatoscompra = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistros = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnguarda = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnvererrados = new javax.swing.JButton();
        lblnroerrados = new javax.swing.JLabel();
        lblnrocorrecto = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbltotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lblbaseimponible = new javax.swing.JLabel();
        lbligvsum = new javax.swing.JLabel();
        lbligvmul = new javax.swing.JLabel();
        lbldif = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbltotalcal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbldiftotal = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IMPORTAR REGISTRO DE COMPRAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1000, -1));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Periodo (MM-AAAA)");

        mcMes.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N

        jButton1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jButton1.setText("Importar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblistadatoscompra.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistadatoscompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblistadatoscompra);

        lblnroregistros.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnroregistros.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnroregistros.setText("0");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel4.setText(":");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel5.setText("Registros Errados     ");

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

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btnvererrados.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnvererrados.setText("Ver");
        btnvererrados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvererradosActionPerformed(evt);
            }
        });

        lblnroerrados.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnroerrados.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnroerrados.setText("0");
        lblnroerrados.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        lblnrocorrecto.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblnrocorrecto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnrocorrecto.setText("0");
        lblnrocorrecto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Seleccione Periodo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel6.setText("Registros Correctos");

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel9.setText("Registros Importados");

        jLabel10.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel10.setText(":");

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel11.setText(":");

        lbltotal.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbltotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotal.setText("0.00");

        jLabel13.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel13.setText("Base Imponible Adquisicion IGV Sumado");

        jLabel14.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel14.setText("Diferencia");

        jLabel15.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel15.setText("Diferencia");

        jLabel16.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel16.setText("Base Imponible Adquisicion IGV Calculado");

        jLabel17.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel17.setText("Base Imponible Adquisición Gravada");

        lblbaseimponible.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblbaseimponible.setText("0.00");

        lbligvsum.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbligvsum.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbligvsum.setText("0.00");

        lbligvmul.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbligvmul.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbligvmul.setText("0.00");

        lbldif.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbldif.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldif.setText("0.00");

        jLabel18.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel18.setText("Importe Total Sumado");

        jLabel19.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel19.setText("Importe Total Calculado");

        lbltotalcal.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbltotalcal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbltotalcal.setText("0.00");

        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Datos de Importación", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N

        lbldiftotal.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lbldiftotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldiftotal.setText("0.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(210, 210, 210)
                                .addComponent(mcAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(mcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel9)
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblnroregistros, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(lblnroerrados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btnvererrados, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(lblnrocorrecto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnguarda, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel17)
                        .addGap(17, 17, 17)
                        .addComponent(lblbaseimponible, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(jLabel13)
                        .addGap(24, 24, 24)
                        .addComponent(lbligvsum, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(lbltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel16)
                        .addGap(16, 16, 16)
                        .addComponent(lbligvmul, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(jLabel19)
                        .addGap(10, 10, 10)
                        .addComponent(lbltotalcal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel15)
                        .addGap(162, 162, 162)
                        .addComponent(lbldif, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)
                        .addComponent(jLabel14)
                        .addGap(72, 72, 72)
                        .addComponent(lbldiftotal, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(mcAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(mcMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(lblnroregistros))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel11))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnvererrados, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(lblnroerrados)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(lblnrocorrecto))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnguarda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lblbaseimponible)
                    .addComponent(jLabel13)
                    .addComponent(lbligvsum)
                    .addComponent(jLabel18)
                    .addComponent(lbltotal))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lbligvmul)
                    .addComponent(jLabel19)
                    .addComponent(lbltotalcal))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lbldif)
                    .addComponent(jLabel14)
                    .addComponent(lbldiftotal))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public boolean esNumeroEntero(String cad) {
        try {
            Integer.parseInt(cad);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        javax.swing.JFileChooser jF1 = new javax.swing.JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("XLS files", "xls");
        jF1.setFileFilter(filtro);
        String ruta = "";
        try {
            if (jF1.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = jF1.getSelectedFile().getAbsolutePath();
                System.out.println(ruta);
                int val=0;
                WorkbookSettings wbSettings = new WorkbookSettings();
                wbSettings.setEncoding("ISO-8859-1");
                wbSettings.setLocale(new Locale("es", "ES"));
                wbSettings.setExcelDisplayLanguage("ES");
                wbSettings.setExcelRegionalSettings("ES");
                wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());
                Workbook workbook = Workbook.getWorkbook(new File(ruta), wbSettings);
                lstdatacompra.removeAll(lstdatacompra);
                Sheet sheet = workbook.getSheet(0);
                System.out.println(sheet.getCell(0, 18).getContents().trim());
                if(!esNumeroEntero(sheet.getCell(0, 18).getContents().trim())){   
                    val=1;
                    JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
                }
                for (int fila = 18; fila < sheet.getRows(); fila++) {
                    //recorremos las filas
                    if(sheet.getCell(0, fila).getContents().toString().trim().length()>0){
                        EDataCompra objdata=new EDataCompra();
                        if(val==1){
                            break;
                        }
                        if(!esNumeroEntero(sheet.getCell(0, fila).getContents().trim())){ 
                        System.out.println("ERROR: "+sheet.getCell(0, fila).getContents());    
                        }else{
                        objdata.setFila(Integer.valueOf(sheet.getCell(0, fila).getContents()));
                        objdata.setFechaEmision(sheet.getCell(1, fila).getContents());
                        objdata.setFechaVencimiento(sheet.getCell(3, fila).getContents());
                        objdata.setTipoComprobantePago(sheet.getCell(4, fila).getContents());
                        objdata.setSerieCodDepenAduanera(sheet.getCell(5, fila).getContents());
                        objdata.setAñoEmisionDua(sheet.getCell(6, fila).getContents());
                        objdata.setDocumentosEmitidos(sheet.getCell(8, fila).getContents());
                        objdata.setTipoDocuIdenProv(sheet.getCell(11, fila).getContents());
                        objdata.setNroDocIdentProv(sheet.getCell(12, fila).getContents());
                        objdata.setRazonSocialProv(sheet.getCell(13, fila).getContents());
                        objdata.setGOBaseDisponible(sheet.getCell(15, fila).getContents());
                        objdata.setGOIGV(sheet.getCell(19, fila).getContents());
                        objdata.setGYGBaseDisponible(sheet.getCell(20, fila).getContents());
                        objdata.setGYGIGV(sheet.getCell(22, fila).getContents());
                        objdata.setNGBaseDisponible(sheet.getCell(23, fila).getContents());
                        objdata.setNGIGV(sheet.getCell(24, fila).getContents());
                        objdata.setAdquisionesNG(sheet.getCell(25, fila).getContents());
                        objdata.setISC(sheet.getCell(26, fila).getContents());
                        objdata.setOtrosTribyCarg(sheet.getCell(27, fila).getContents());
                        objdata.setImporteTotal(sheet.getCell(28, fila).getContents());
                        objdata.setNComprNoDomic(sheet.getCell(29, fila).getContents());
                        objdata.setNroConstanciaDetraccion(sheet.getCell(30, fila).getContents());
                        objdata.setFechaEmisionConsDetracc(sheet.getCell(31, fila).getContents());
                        objdata.setTipoDeCambio(sheet.getCell(32, fila).getContents());
                        objdata.setCMFecha(sheet.getCell(33, fila).getContents());
                        objdata.setCMTipo(sheet.getCell(34, fila).getContents());
                        objdata.setCMSerie(sheet.getCell(35, fila).getContents());
                        objdata.setCMNCPagoDoc(sheet.getCell(36, fila).getContents());
                        lstdatacompra.add(objdata);    
                        }
                    }
                }
                llenartabla();
            }
        } catch (HeadlessException | IOException | BiffException | IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "El archivo que intenta cargar no cuenta con el formato adecuado.");
            System.out.println(ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
                    if (JOptionPane.showConfirmDialog(this, "Tiene registros errados. Va a GRABAR sólo la data correcta del periodo " + charmes(mcMes.getMonth()) + "-" + mcAño.getYear() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        lim.InsertarRegistroCompraCab(mes, año);
                        int cod = lim.CodRegistroCompra(mes, año);
                        for (int i = 0; i < lstdatacorrecta.size(); i++) {
                            lstdatacorrecta.get(i).setCodigo(cod);
                        }
                        PrimeThread prt = new PrimeThread();
                        new Thread(prt).start();
                        jdc.setVisible(true);
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Va a GRABAR la data del periodo " + charmes(mcMes.getMonth()) + "-" + mcAño.getYear() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        lim.InsertarRegistroCompraCab(mes, año);
                        int cod = lim.CodRegistroCompra(mes, año);
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
                    int cod = lim.CodRegistroCompra(mes, año);
                    if (lim.LimpiarRegistroCompraDet(cod)) {
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

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnvererradosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvererradosActionPerformed
        // TODO add your handling code here:
        InterFrmVerErradosCompra vererrados=new InterFrmVerErradosCompra();
        MenuLibrosElectronico.dpane2.add(vererrados);
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
    private javax.swing.JLabel lblbaseimponible;
    private javax.swing.JLabel lbldif;
    private javax.swing.JLabel lbldiftotal;
    private javax.swing.JLabel lbligvmul;
    private javax.swing.JLabel lbligvsum;
    private javax.swing.JLabel lblnrocorrecto;
    private javax.swing.JLabel lblnroerrados;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JLabel lbltotal;
    private javax.swing.JLabel lbltotalcal;
    private com.toedter.calendar.JYearChooser mcAño;
    private com.toedter.calendar.JMonthChooser mcMes;
    private javax.swing.JTable tblistadatoscompra;
    // End of variables declaration//GEN-END:variables
}
