/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EFormaPago;
import Capa_Entidades.EMoneda;
import Capa_Entidades.EParametro;
import Capa_Entidades.EProveedor;
import Capa_Entidades.ETipoComprobante;
import Capa_Logica.LAgenteRetenedores;
import Capa_Logica.LComprobante;
import Capa_Logica.LMoneda;
import Capa_Logica.LParametro;
import Capa_Logica.LTipoCambio;
import Capa_Logica.RendererPersonalizado;
import Capa_Logica.RendererPersonalizado2;
import Capa_Logica.SesionUsuario;
import Capa_PrgMantemiento.TipoCambio;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
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
public class RegistroDeComprobantes extends javax.swing.JInternalFrame {

    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    ArrayList<ETipoComprobante> lsttipo = new ArrayList<>();
    ArrayList<EProveedor> lisdni = new ArrayList<>();
    ArrayList<EParametro> lispara = new ArrayList<>();
    ArrayList<EDetraccion> lisdetra = new ArrayList<>();
    ArrayList<EMoneda> lstmon = new ArrayList<>();
    ArrayList<EFormaPago> lisformapago = new ArrayList<>();
    ArrayList<ETipoComprobante> listipocom = new ArrayList<>();
    String titulos[] = {"TipoComp", "NumComp", "FecComp", "Estado", "RazonSocial",
        "DocProveedor", "Glosa", "Sujeto A", "ConcepDetra", "FormaPago", "DestinoPago", "Moneda", "TC",
        "MBaseSOL", "MOtrosSOL", "MIGVSOL", "MTotalSOL", "MRetDetSOL", "MPagarSOL",
        "MBaseDOL", "MOtrosDOL", "MIGVDOL", "MTotalDOL", "MRetDetDOL",
        "MPagarDOL", "PorcRetDet", "PorcIGV", "CompRef", "RazonSocialRef", "FechaRef","Nro. Cta. Contable" ,"UsuCrea", "FecCrea","cod Detracción"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    ArrayList<EComprobante> liscom = new ArrayList<>();
    LComprobante lcom = new LComprobante();
    String destinopa;
    String despago;
    String destip;
    String tipodoc;
    String estadocomprobante;
    int codcompro;
    int opc = 0;
    int accion = 0;
    double tc = 0.000;
    String estado;
    double porcentaje;
    double porcigv;
    double porcdetra;
    double conceptodetra;
    int indice;
    String codigodetra;
    int indicemon;
    int codmon;
    String coddetra;
    String desccomprobante;
    String codcomprobante;
    int indicecompro;
    String formapago;
    int indicepago;
    String destinopago;
    int indicedestino;

    /**
     * Creates new form RegistroDeComprobantes
     */
    public RegistroDeComprobantes() {
        initComponents();
        this.setTitle("Registro de Comprobantes.");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        tblistacomprobante.setModel(modTbdatos);
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
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
        tblistacomprobante.getColumnModel().getColumn(13).setCellRenderer(new RendererPersonalizado());
        tblistacomprobante.getColumnModel().getColumn(14).setCellRenderer(new RendererPersonalizado());
        tblistacomprobante.getColumnModel().getColumn(15).setCellRenderer(new RendererPersonalizado());
        tblistacomprobante.getColumnModel().getColumn(16).setCellRenderer(new RendererPersonalizado());
        tblistacomprobante.getColumnModel().getColumn(17).setCellRenderer(new RendererPersonalizado());
        tblistacomprobante.getColumnModel().getColumn(18).setCellRenderer(new RendererPersonalizado());
        tblistacomprobante.getColumnModel().getColumn(19).setCellRenderer(new RendererPersonalizado2());
        tblistacomprobante.getColumnModel().getColumn(20).setCellRenderer(new RendererPersonalizado2());
        tblistacomprobante.getColumnModel().getColumn(21).setCellRenderer(new RendererPersonalizado2());
        tblistacomprobante.getColumnModel().getColumn(22).setCellRenderer(new RendererPersonalizado2());
        tblistacomprobante.getColumnModel().getColumn(23).setCellRenderer(new RendererPersonalizado2());

        llenarmoneda();
        llenaTipoComprobante();
        llenaConceptoDetra();
        llenaFormaPago();
        llenaDestinoPago();
        llenarigv();
        listacomprobante();
        limpiar();
        cboconceptodetra.setEnabled(false);

        Date hoy = new Date();
        dcFechaEmision.setDate(hoy);
        TraerTC();
        dcFechaEmision.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
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

    private void limpiar() {
        cbotipocomprobante.setSelectedIndex(0);
        txtnumcompro.setText("");
        Date hoy = new Date();
        dcFechaEmision.setDate(hoy);
        txtcomprobanteref.setText("");
        txtpro.setText("");
        txtrazref.setText("");
        txtfecref.setText("");
        txtrucpro.setText("");
        txtglosa.setText("");

        rbdetraccion.setEnabled(true);
        rbretencion.setEnabled(true);
        rbnada.setEnabled(true);
        cboconceptodetra.setEnabled(true);
        cbodestinopago.setEnabled(true);
        cbodestinopago.setSelectedIndex(0);
        rbretencion.setSelected(false);
        rbdetraccion.setSelected(false);
        rbnada.setSelected(true);
        cbotipocomprobante.setEnabled(true);
        txtnumcompro.setEnabled(true);
        cboconceptodetra.setSelectedIndex(0);
        cboconceptodetra.setEnabled(false);
        cboformapago.setSelectedIndex(0);

        cbomoneda.setSelectedIndex(0);
        btncomprobante.setEnabled(false);
//        lblTC.setText("0.000");
        TraerTC();
        lblporc.setText("(0.00)");
        txtmontobase.setText("0.00");
        txtmontootros.setText("0.00");
        txtmontoigv.setText("0.00");
        txtmontototal.setText("0.00");
        txtmonretdet.setText("0.00");
        txtmontopagar.setText("0.00");
        txtNumeroCuentaContable.setText("");
    }

    ;
    
    
     private void TraerTC() {
        LTipoCambio ltc = new LTipoCambio();
        System.out.println("sssssssssss" + fechaformateador(dcFechaEmision.getDate()));
        tc = ltc.RecuperarTipoCambio(fechaformateador(dcFechaEmision.getDate()));
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

    private void llenarigv() {
        LParametro limp = new LParametro();
        lispara = limp.ListaParametros();
        for (int i = 0; i < lispara.size(); i++) {
            porcigv = lispara.get(i).getIGV();
            lblporcigv.setText("(" + lispara.get(i).getIGV() + ")");
        }
    }

    private void llenarporcentaje() {
        LParametro limp = new LParametro();
        lispara = limp.ListaParametros();
        for (int i = 0; i < lispara.size(); i++) {
            porcentaje = lispara.get(i).getCampo1();
            lblporc.setText("(" + lispara.get(i).getCampo1() + ")");
        }
    }

    private void llenarmoneda() {
        LMoneda limp = new LMoneda();
        lstmon = limp.ListaMoneda();
        cbomoneda.removeAllItems();
        cbomoneda.addItem("<Seleccione>");
        for (int i = 0; i < lstmon.size(); i++) {
            cbomoneda.addItem(lstmon.get(i).getNomMoneda());
        }
    }

    private void llenaTipoComprobante() {
        LComprobante limp = new LComprobante();
        listipocom = limp.ListaTipoComprobante();
        cbotipocomprobante.removeAllItems();
        cbotipocomprobante.addItem("<Seleccione>");
        for (int i = 0; i < listipocom.size(); i++) {
            cbotipocomprobante.addItem(listipocom.get(i).getDescComprobante());
        }
    }

    private void llenaConceptoDetra() {
        LComprobante limp = new LComprobante();
        lisdetra = limp.listaDetra();
        cboconceptodetra.removeAllItems();
        cboconceptodetra.addItem("<Seleccione>");
        for (int i = 0; i < lisdetra.size(); i++) {

            cboconceptodetra.addItem(lisdetra.get(i).getDescripcion());
            System.out.println(lisdetra.get(i).getDescripcion());
        }
    }

    private void llenaPorcDetra() {

//           LDetraccion ldetra = new LDetraccion();
        indice = cboconceptodetra.getSelectedIndex();
        System.out.println("indice selecionad "+ indice);
        if (indice != 0) {
            LComprobante limp = new LComprobante();
            lisdetra = limp.listaDetra();
            coddetra = lisdetra.get(indice - 1).getCodDetraccion();
            porcentaje = conceptodetra;
            conceptodetra = lisdetra.get(indice - 1).getPorcentajeDetra();
            porcentaje = conceptodetra;
            lblporc.setText("(" + conceptodetra + ")");
        } else {
            porcentaje = 0.0;
            lblporc.setText("(0.00)");
        }
    }

    private void llenacodMoneda() {

//           LDetraccion ldetra = new LDetraccion();
        indicemon = cbomoneda.getSelectedIndex();
        if (indicemon != 0) {
            LMoneda limp = new LMoneda();
            lstmon = limp.ListaMoneda();
            codmon = lstmon.get(indicemon - 1).getCodMoneda();
        } else {
            codmon = 0;
        }
    }

    private void llenacodComprobante() {

//           LDetraccion ldetra = new LDetraccion();
        indicecompro = cbotipocomprobante.getSelectedIndex();
        if (indicecompro != 0) {
            LComprobante limp = new LComprobante();
            listipocom = limp.ListaTipoComprobante();
            codcomprobante = listipocom.get(indicecompro - 1).getCodComprobante();
            desccomprobante = listipocom.get(indicecompro - 1).getDescComprobante();
        } else {
            codcomprobante = "0";
            desccomprobante = "0";
        }
    }

    private void llenaformapago1() {

//           LDetraccion ldetra = new LDetraccion();
        indicepago = cboformapago.getSelectedIndex();
        if (indicepago != 0) {
            LComprobante limp = new LComprobante();
            lisformapago = limp.ListaFormaPago();
            formapago = lisformapago.get(indicepago - 1).getDescPago();
        } else {
            formapago = "0";
        }
    }

    private void llenadestino1() {

//           LDetraccion ldetra = new LDetraccion();
//          
//            indicedestino = cboformapago.getSelectedIndex();
//            if(indicedestino !=0){
//            LComprobante limp = new LComprobante();
//            lisdni = limp.listaProDNI();
//            destinopago=lisdni.get(indicedestino-1).getRsDestino();
//        }else{
//                 destinopago="";
//            }
    }

    private void llenaFormaPago() {
        LComprobante limp = new LComprobante();
        lisformapago = limp.ListaFormaPago();
        cboformapago.removeAllItems();
        cboformapago.addItem("<Seleccione>");
        for (int i = 0; i < lisformapago.size(); i++) {
            cboformapago.addItem(lisformapago.get(i).getDescPago());
        }
    }

    private void llenaDestinoPago() {
        LComprobante limp = new LComprobante();
        lisdni = limp.listaProDNI();
        cbodestinopago.removeAllItems();
        cbodestinopago.addItem("<Seleccione>");
        for (int i = 0; i < lisdni.size(); i++) {
            cbodestinopago.addItem(lisdni.get(i).getRsDestino().trim());
        }
    }

    private void listacomprobante() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        LComprobante lpro = new LComprobante();
        liscom = lpro.listaComprobante4();
        modTbdatos.setRowCount(0);
        if (liscom.size() > 0) {
            for (int i = 0; i < liscom.size(); i++) {
                System.out.println("valor nro contable "+ liscom.get(i).getNroCuentaContable());
                Object nuevafila[] = new Object[34];
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
                nuevafila[30] = liscom.get(i).getNroCuentaContable();
                nuevafila[31] = liscom.get(i).getUsuCrea();
                nuevafila[32] = liscom.get(i).getFechaCrea();
                nuevafila[33] = liscom.get(i).getCodDetraccion();
                modTbdatos.addRow(nuevafila);
            }
        }
        tblistacomprobante.setModel(modTbdatos);
        lblnrocomprobante.setText(tblistacomprobante.getRowCount() + " Comprobante(es)");
    }

    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2);
        int height = ((desktopSize.height - jInternalFrameSize.height) / 4);
        jif.setLocation(width, height);
        jif.setVisible(true);
    }

    public Date miFecha(String cad) {
        StringBuilder strb = new StringBuilder();
        strb.append(cad.substring(3, 5));
        strb.append("/");
        strb.append(cad.substring(0, 2));
        strb.append("/");
        strb.append(cad.substring(6, 10));
        Date fecha = new Date(strb.toString());
        return fecha;
    }

    String fechaformateador(Date fecha) {
        String fechaletra = new SimpleDateFormat("dd/MM/yyyy").format(fecha);
        return fechaletra;
    }

    private boolean validacionxrh() {
        int val = 0;
        if (cbotipocomprobante.getSelectedItem().toString().trim().equalsIgnoreCase("RECIBO POR HONORARIOS") && !cbotipocomprobante.getSelectedItem().toString().trim().equalsIgnoreCase("FACTURA")) {
            if (rbnada.isSelected()) {
                if (cbomoneda.getSelectedIndex() == 1) {
                    if (Double.valueOf(txtmontopagar.getText().replace(",", "")) >= 1500.00) {
                        val = 0;
                    } else {
                        val = 1;
                    }
                } else {
                    if ((Double.valueOf(txtmontopagar.getText().replace(",", "")) * tc) >= 1500.00) {
                        val = 0;
                    } else {
                        val = 1;
                    }
                }
            } else {
                val = 1;
            }
        } else {
            val = 1;
        }
        if (val == 1) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validacionxfactura() {
        int val = 0;
        LAgenteRetenedores lar = new LAgenteRetenedores();
        int agent = 0;
        agent = lar.VerificarAgente(txtrucpro.getText().trim());
        if (agent == 0) {
            if (cbotipocomprobante.getSelectedItem().toString().trim().equalsIgnoreCase("FACTURA")) {
                if (rbnada.isSelected()) {
                    if (!txtmontoigv.getText().trim().equalsIgnoreCase("0.00")) {
                        System.out.println("ENTRO IGV");
                        if (cbomoneda.getSelectedIndex() == 1) {

                            if (Double.valueOf(txtmontopagar.getText().replace(",", "")) >= 700.00) {
                                val = 0;
                            } else {
                                val = 1;
                            }
                        } else {
                            if ((Double.valueOf(txtmontopagar.getText().replace(",", "")) * tc) >= 700.00) {
                                val = 0;
                            } else {
                                val = 1;
                            }
                        }
                    } else {
                        val = 1;
                    }
                } else {
                    val = 1;
                }
            } else {
                val = 1;
            }
        } else {
            val = 1;
        }

        if (val == 1) {
            return true;
        } else {
            return false;
        }
    }

    private void seleccionarfila(int fila) {
        accion = 1;

        LComprobante limp = new LComprobante();
        codcompro = liscom.get(fila).getCodComprobante();
        tipodoc = (tblistacomprobante.getValueAt(fila, 0)).toString().trim();
         
        destip = limp.DescTipoComprobante2(tipodoc);
       cbotipocomprobante.setSelectedItem(destip);
        if (destip.equals("LIQUIDACIÓN DE COBRANZA") || destip.equals("NOTA DE CREDITO") || destip.equals("NOTA DE DEBITO") || destip.equals("RECIBO DE SERVICIOS")) {
            rbdetraccion.setEnabled(false);
            rbretencion.setEnabled(false);
            rbnada.setEnabled(true);
            cboconceptodetra.setEnabled(false);
            coddetra = "0";
        }
        if (destip.equals("RECIBO POR HONORARIOS")) {
            rbdetraccion.setEnabled(false);
            rbretencion.setEnabled(true);
            rbnada.setEnabled(true);
            cboconceptodetra.setEnabled(false);
            coddetra = "0";

        }
        if (destip.equals("FACTURA")) {            
            String destaccion = BuscarDetraccion(lisdetra, tblistacomprobante.getValueAt(fila, 33).toString());
             cboconceptodetra.setSelectedItem(destaccion.toUpperCase());
             cboconceptodetra.setSelectedIndex(cboconceptodetra.getSelectedIndex());
            System.out.println("ndice seleccionado " + cboconceptodetra.getSelectedIndex());
             if (cboconceptodetra.getSelectedIndex() != 0) {
                lisdetra = limp.listaDetra();
                coddetra = lisdetra.get(cboconceptodetra.getSelectedIndex() - 1).getCodDetraccion();
                System.out.println("coddetra " + coddetra);
            } else {
                coddetra = "0";
            }
           
//          cde= tblistacomprobante.getValueAt(fila, 8).toString();  

            rbdetraccion.setEnabled(true);
            rbretencion.setEnabled(true);
            rbnada.setEnabled(true);
            cboconceptodetra.setEnabled(true);

        }
        cbotipocomprobante.setEnabled(false);
        txtnumcompro.setText(tblistacomprobante.getValueAt(fila, 1).toString());
        txtnumcompro.setEnabled(false);
        dcFechaEmision.setDate(miFecha(liscom.get(fila).getFechaComprobante()));
        estadocomprobante = (tblistacomprobante.getValueAt(fila, 3)).toString().trim();
        txtNumeroCuentaContable.setText((tblistacomprobante.getValueAt(fila, 30)).toString().trim());
         
        if (estadocomprobante.equals("PENDIENTE") || estadocomprobante.equals("MIGRADO")) {
            if (destip.equals("NOTA DE CREDITO") || destip.equals("NOTA DE DEBITO")) {
                btncomprobante.setEnabled(true);
            } else {
                btncomprobante.setEnabled(false);
            }
        } else {
            btncomprobante.setEnabled(false);
        }

        txtpro.setText(tblistacomprobante.getValueAt(fila, 4).toString());
        txtrucpro.setText(tblistacomprobante.getValueAt(fila, 5).toString());
        
        if (tblistacomprobante.getValueAt(fila, 6) != null) {
            txtglosa.setText(tblistacomprobante.getValueAt(fila, 6).toString());
        } else {
            txtglosa.setText("");
        }
        if (tblistacomprobante.getValueAt(fila, 7) == null) {
            estado = "SIN RET/DET";
        } else {
            estado = tblistacomprobante.getValueAt(fila, 7).toString();
        }
        
        switch (estado) {
            case "RETENCION":
                rbretencion.setSelected(true);
                rbdetraccion.setSelected(false);
                cboconceptodetra.setEnabled(false);
                rbnada.setSelected(false);
                System.out.println("Capa_ProgPago.RegistroDeComprobantes.seleccionarfila()");
                break;
            case "DETRACCION":
                rbdetraccion.setSelected(true);
                rbretencion.setSelected(false);
                rbnada.setSelected(false);
                break;
            default:
                rbnada.setSelected(true);
                rbretencion.setSelected(false);
                rbdetraccion.setSelected(false);
                break;

        }

        if (tblistacomprobante.getValueAt(fila, 9) == null) {
            cbodestinopago.setSelectedIndex(0);
            cbodestinopago.setEnabled(false);
            destinopago = "";
        } else {
            cboformapago.setSelectedItem(tblistacomprobante.getValueAt(fila, 9).toString().trim());
            if (tblistacomprobante.getValueAt(fila, 9).toString().trim().equalsIgnoreCase("")) {
                cboformapago.setSelectedIndex(0);
            }
            if (tblistacomprobante.getValueAt(fila, 9).toString().trim().equalsIgnoreCase("TRANS-REEMBOLSO")) {
                cbodestinopago.setEnabled(true);
                cbodestinopago.setSelectedItem(tblistacomprobante.getValueAt(fila, 10));
            } else {
                cbodestinopago.setEnabled(false);
                cbodestinopago.setSelectedIndex(0);
            }
        }
        cbomoneda.setSelectedItem(tblistacomprobante.getValueAt(fila, 11));
        lblTC.setText(tblistacomprobante.getValueAt(fila, 12).toString());
        System.out.println();
        if (tblistacomprobante.getValueAt(fila, 11).toString().equalsIgnoreCase("SOLES")) {
            txtmontobase.setText(tblistacomprobante.getValueAt(fila, 13).toString());
            txtmontootros.setText(tblistacomprobante.getValueAt(fila, 14).toString());
            txtmontoigv.setText(tblistacomprobante.getValueAt(fila, 15).toString());
            if (Double.valueOf(tblistacomprobante.getValueAt(fila, 15).toString().replace(",", "")) == 0.00) {
                rbretencion.setEnabled(true);
                cboconceptodetra.setSelectedIndex(0);
            }
            txtmontototal.setText(tblistacomprobante.getValueAt(fila, 16).toString());
            txtmonretdet.setText(tblistacomprobante.getValueAt(fila, 17).toString());
            txtmontopagar.setText(tblistacomprobante.getValueAt(fila, 18).toString());
        } else {
            txtmontobase.setText(tblistacomprobante.getValueAt(fila, 19).toString());
            txtmontootros.setText(tblistacomprobante.getValueAt(fila, 20).toString());
            txtmontoigv.setText(tblistacomprobante.getValueAt(fila, 21).toString().trim());
            if (Double.valueOf(tblistacomprobante.getValueAt(fila, 21).toString().replace(",", "").trim()) == 0.00) {
                rbretencion.setEnabled(true);
                cboconceptodetra.setSelectedIndex(0);
            }
            txtmontototal.setText(tblistacomprobante.getValueAt(fila, 22).toString());
            txtmonretdet.setText(tblistacomprobante.getValueAt(fila, 23).toString());
            txtmontopagar.setText(tblistacomprobante.getValueAt(fila, 24).toString());
        }
        lblporc.setText("(" + tblistacomprobante.getValueAt(fila, 25).toString() + ")");
        lblporcigv.setText("(" + tblistacomprobante.getValueAt(fila, 26).toString() + ")");
        if (tblistacomprobante.getValueAt(fila, 27) == null) {
            txtcomprobanteref.setText("");
        } else {
            txtcomprobanteref.setText(tblistacomprobante.getValueAt(fila, 27).toString());
        }
        int agent = 0;
        LAgenteRetenedores lar = new LAgenteRetenedores();
        agent = lar.VerificarAgente(txtrucpro.getText().trim());
        System.out.println("agent " + agent);
//        if (agent == 1) {
//            rbdetraccion.setEnabled(true);
//            rbretencion.setEnabled(false);
//            rbnada.setSelected(true);
//        } else {
//            //rbdetraccion.setEnabled(true);
//            if (destip.equals("LIQUIDACIÓN DE COBRANZA") || destip.equals("NOTA DE CREDITO") || destip.equals("NOTA DE DEBITO") || destip.equals("RECIBO DE SERVICIOS")) {
//                rbdetraccion.setEnabled(true);
//            } else {
//                if (txtmontoigv.getText().equalsIgnoreCase("0.0") || txtmontoigv.getText().equalsIgnoreCase("0.00")) {
//                    
//                } else {
//                    rbretencion.setEnabled(true);
//                }
//            }
//        }
    }

    private void calcularmontos() {
        LParametro limp = new LParametro();
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        double n1 = 0.00;
        double igv = limp.ListaParametros().get(0).getIGV();
        double n2 = 0.00;
        double migv = 0.00;
        n1 = Double.valueOf(txtmontobase.getText().replace(",", ""));
        n2 = Double.valueOf(txtmontootros.getText().replace(",", ""));
        migv = (n1) * igv;
        String combotipo = cbotipocomprobante.getSelectedItem().toString().trim();
        if (combotipo.equalsIgnoreCase("LIQUIDACIÓN DE COBRANZA") || combotipo.equalsIgnoreCase("RECIBO POR HONORARIOS")) {
            migv = 0.00;
        }
        double retdet = ((n1 + n2 + migv) * porcentaje);
        txtmontobase.setText(formato.format(n1));
        txtmontootros.setText(formato.format(n2));
        txtmontoigv.setText("" + formato.format(migv));
        txtmontototal.setText("" + formato.format(n1 + n2 + migv));
        if (cbomoneda.getSelectedItem().toString().equalsIgnoreCase("SOLES")) {
            if (rbdetraccion.isSelected()) {
                txtmonretdet.setText("" + formato.format(round(retdet)));
                txtmontopagar.setText("" + formato.format((n1 + n2 + migv) - (round(retdet))));
            } else {
                txtmonretdet.setText("" + formato.format(retdet));
                txtmontopagar.setText("" + formato.format((n1 + n2 + migv) - (retdet)));
            }
        } else {
            txtmonretdet.setText("" + formato.format(retdet));
            txtmontopagar.setText("" + formato.format((n1 + n2 + migv) - (retdet)));
        }

    }

    private int round(double d) {
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if (result < 0.5) {
            return d < 0 ? -i : i;
        } else {
            return d < 0 ? -(i + 1) : i + 1;
        }
    }

    public boolean validar() {
        int bool = 0;
        if (cbotipocomprobante.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Escoja el Tipo de Documento");
            bool = 1;
            cbotipocomprobante.requestFocus();
        } else {
            if (txtnumcompro.getText().toString().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Ingrese el Numero de Comprobante");
                bool = 1;
                txtnumcompro.requestFocus();
            } else {
                if (dcFechaEmision.getDate() == null) {
                    JOptionPane.showMessageDialog(this, "Ingrese la Fecha.");
                    bool = 1;
                    dcFechaEmision.requestFocus();
                } else {
                    if (txtpro.getText().toString().trim().length() == 0) {
                        JOptionPane.showMessageDialog(this, "Ingrese la Razon Social.");
                        bool = 1;
                        txtpro.requestFocus();
                    } else {
                        if (txtrucpro.getText().toString().trim().length() == 0) {
                            JOptionPane.showMessageDialog(this, "Ingrese el Ruc.");
                            bool = 1;
                            txtrucpro.requestFocus();
                        } else {
                            if (txtglosa.getText().toString().trim().length() == 0) {
                                JOptionPane.showMessageDialog(this, "Ingrese la Glosa.");
                                bool = 1;
                                txtglosa.requestFocus();
                            } else {
                                if (estado.equals("DETRACCION") && cboconceptodetra.getSelectedIndex() == 0) {
                                    JOptionPane.showMessageDialog(this, "Escoja el Concepto de Detracción.");
                                    bool = 1;
                                    cboconceptodetra.requestFocus();
                                } else {
                                    if (cboformapago.getSelectedIndex() == 0) {
                                        JOptionPane.showMessageDialog(this, "Escoja la Forma de Pago.");
                                        bool = 1;
                                        cboformapago.requestFocus();
                                    } else {
                                        if (cbodestinopago.getSelectedIndex() == 0 && cbodestinopago.getSelectedItem() == "TRANS-REEMBOLSO") {
                                            JOptionPane.showMessageDialog(this, "Escoja el Destino de Pago.");
                                            bool = 1;
                                            cbodestinopago.requestFocus();
                                        } else {
                                            if (cbomoneda.getSelectedIndex() == 0) {
                                                JOptionPane.showMessageDialog(this, "Escoja el Tipo de Moneda.");
                                                bool = 1;
                                                cbomoneda.requestFocus();
                                            } else {
                                                if (lblTC.getText().equalsIgnoreCase("0.000") && cbomoneda.getSelectedItem().toString().equalsIgnoreCase("DOLARES")) {
                                                    JOptionPane.showMessageDialog(this, "Ingrese Tipo de Cambio.");
                                                    bool = 1;
                                                    lblTC.requestFocus();
                                                } else {

                                                    if (txtmontobase.getText().toString().trim().length() == 0) {
                                                        JOptionPane.showMessageDialog(this, "Ingrese el Monto Base.");
                                                        bool = 1;
                                                        txtmontobase.requestFocus();
                                                    } else {
                                                        if (txtmontootros.getText().toString().trim().length() == 0) {
                                                            JOptionPane.showMessageDialog(this, "Ingrese el Monto Otro.");
                                                            bool = 1;
                                                            txtmontootros.requestFocus();

                                                        } else {
                                                            if (opc == 0) {
                                                                if (desccomprobante.equals("NOTA DE CREDITO") || desccomprobante.equals("NOTA DE DEBITO")) {
                                                                    if (txtcomprobanteref.getText().toString().trim().length() == 0) {
                                                                        JOptionPane.showMessageDialog(this, "Seleccione Factura.");
                                                                        bool = 1;
                                                                        btncomprobante.requestFocus();
                                                                    }
                                                                }
                                                            } else {
                                                                if (destip.equals("NOTA DE CREDITO") || destip.equals("NOTA DE DEBITO")) {
                                                                    if (txtcomprobanteref.getText().toString().trim().length() == 0) {
                                                                        JOptionPane.showMessageDialog(this, "Seleccione Factura.");
                                                                        bool = 1;
                                                                        btncomprobante.requestFocus();
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (bool == 0) {
            return true;
        } else {
            return false;
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

        bggrupo = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtmontobase = new javax.swing.JTextField();
        rbnada = new javax.swing.JRadioButton();
        rbretencion = new javax.swing.JRadioButton();
        rbdetraccion = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnnuevo = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnproveedor = new javax.swing.JButton();
        txtpro = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtglosa = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        txtnumcompro = new javax.swing.JTextField();
        txtfecref = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnTC = new javax.swing.JButton();
        dcFechaEmision = new com.toedter.calendar.JDateChooser()

        ;
        lblTC = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtrucpro = new javax.swing.JTextField();
        txtmontootros = new javax.swing.JTextField();
        txtmontototal = new javax.swing.JTextField();
        txtmontopagar = new javax.swing.JTextField();
        txtmontoigv = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtmonretdet = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        txtcomprobanteref = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtrazref = new javax.swing.JTextField();
        btncomprobante = new javax.swing.JButton();
        cboconceptodetra = new javax.swing.JComboBox();
        cbotipocomprobante = new javax.swing.JComboBox();
        cboformapago = new javax.swing.JComboBox();
        cbodestinopago = new javax.swing.JComboBox();
        cbomoneda = new javax.swing.JComboBox();
        lblporcigv = new javax.swing.JLabel();
        lblporc = new javax.swing.JLabel();
        btncompro = new javax.swing.JLabel();
        chkbtnactualizar = new javax.swing.JCheckBox();
        btnactualizatodo = new javax.swing.JButton();
        lblnrocomprobante = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtNumeroCuentaContable = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacomprobante = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        setPreferredSize(new java.awt.Dimension(910, 570));

        jPanel1.setBackground(new java.awt.Color(255, 0, 15));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRO DE COMPROBANTES");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Tipo de Comprobante");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel4.setText("Fecha de Emisión");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, 90, -1));

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel5.setText("Monto a Pagar");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 180, 90, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("TC");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 20, 20));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel7.setText("Proveedor");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        jLabel15.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel15.setText("Num. Comprobante");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        txtmontobase.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtmontobase.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontobase.setText("0");
        txtmontobase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmontobaseKeyReleased(evt);
            }
        });
        jPanel2.add(txtmontobase, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 90, 20));

        rbnada.setBackground(java.awt.Color.white);
        bggrupo.add(rbnada);
        rbnada.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbnada.setText("Sin Ret/Det");
        rbnada.setPreferredSize(new java.awt.Dimension(6, 20));
        rbnada.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbnadaItemStateChanged(evt);
            }
        });
        rbnada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbnadaActionPerformed(evt);
            }
        });
        jPanel2.add(rbnada, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 80, 20));

        rbretencion.setBackground(java.awt.Color.white);
        bggrupo.add(rbretencion);
        rbretencion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbretencion.setText("Retención");
        rbretencion.setPreferredSize(new java.awt.Dimension(6, 20));
        rbretencion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbretencionItemStateChanged(evt);
            }
        });
        rbretencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbretencionActionPerformed(evt);
            }
        });
        jPanel2.add(rbretencion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 80, 20));

        rbdetraccion.setBackground(java.awt.Color.white);
        bggrupo.add(rbdetraccion);
        rbdetraccion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbdetraccion.setText("Detracción");
        rbdetraccion.setPreferredSize(new java.awt.Dimension(6, 20));
        rbdetraccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbdetraccionItemStateChanged(evt);
            }
        });
        rbdetraccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbdetraccionActionPerformed(evt);
            }
        });
        jPanel2.add(rbdetraccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 140, 80, 20));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, -1, 112));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 70, 30));

        btnsalir.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 110, 70, 30));

        btnnuevo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });
        jPanel2.add(btnnuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 20, 70, 30));

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/borrar.png"))); // NOI18N
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btneliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, 70, 30));

        btnproveedor.setText("jButton2");
        btnproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedorActionPerformed(evt);
            }
        });
        jPanel2.add(btnproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 30, 20));

        txtpro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtpro.setEnabled(false);
        txtpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtproActionPerformed(evt);
            }
        });
        jPanel2.add(txtpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 270, 20));

        txtglosa.setColumns(20);
        txtglosa.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtglosa.setLineWrap(true);
        txtglosa.setRows(5);
        txtglosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtglosaKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(txtglosa);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 300, 40));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel8.setText("Ruc Proveedor");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        txtnumcompro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtnumcompro.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(txtnumcompro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 90, -1));

        txtfecref.setForeground(new java.awt.Color(255, 255, 255));
        txtfecref.setBorder(null);
        jPanel2.add(txtfecref, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, 50, 20));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel9.setText("Glosa Comprobante");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel10.setText("Comprobante Sujeto A ");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        btnTC.setText("...");
        btnTC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTCActionPerformed(evt);
            }
        });
        jPanel2.add(btnTC, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 30, 20));

        dcFechaEmision.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcFechaEmision.setPreferredSize(new java.awt.Dimension(91, 20));
        jPanel2.add(dcFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 110, -1));

        lblTC.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblTC.setText("0.000");
        jPanel2.add(lblTC, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 60, 40, 20));

        jLabel13.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel13.setText("Concepto de Detra");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 165, -1, -1));

        txtrucpro.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtrucpro.setEnabled(false);
        txtrucpro.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(txtrucpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 90, 20));

        txtmontootros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtmontootros.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontootros.setText("0");
        txtmontootros.setPreferredSize(new java.awt.Dimension(20, 20));
        txtmontootros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmontootrosKeyReleased(evt);
            }
        });
        jPanel2.add(txtmontootros, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 90, -1));

        txtmontototal.setEditable(false);
        txtmontototal.setBackground(new java.awt.Color(204, 204, 204));
        txtmontototal.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtmontototal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontototal.setText("0");
        txtmontototal.setPreferredSize(new java.awt.Dimension(20, 20));
        txtmontototal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmontototalActionPerformed(evt);
            }
        });
        jPanel2.add(txtmontototal, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 90, 20));

        txtmontopagar.setEditable(false);
        txtmontopagar.setBackground(new java.awt.Color(204, 204, 204));
        txtmontopagar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtmontopagar.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtmontopagar.setText("0");
        txtmontopagar.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(txtmontopagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 180, 90, -1));

        txtmontoigv.setEditable(false);
        txtmontoigv.setBackground(new java.awt.Color(204, 204, 204));
        txtmontoigv.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtmontoigv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmontoigv.setText("0");
        txtmontoigv.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(txtmontoigv, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 120, 90, -1));

        jLabel14.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel14.setText("F = D-E");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, 40, 20));

        jLabel16.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel16.setText("Monto Ret/Det");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 160, 70, -1));

        jLabel17.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel17.setText("Monto Base");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 80, 90, -1));

        txtmonretdet.setEditable(false);
        txtmonretdet.setBackground(new java.awt.Color(204, 204, 204));
        txtmonretdet.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtmonretdet.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtmonretdet.setText("0");
        txtmonretdet.setPreferredSize(new java.awt.Dimension(20, 20));
        txtmonretdet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtmonretdetActionPerformed(evt);
            }
        });
        jPanel2.add(txtmonretdet, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 160, 90, -1));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 140, 110, 20));

        txtcomprobanteref.setEnabled(false);
        jPanel2.add(txtcomprobanteref, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 80, -1));

        jLabel18.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel18.setText("Monto Exon/Otro");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 90, -1));

        jLabel19.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel19.setText("A");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 80, 20, 20));

        jLabel20.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel20.setText("C");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 20, 20));

        jLabel21.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel21.setText("D=A+B+C");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 50, 20));

        jLabel22.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel22.setText("E");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 20, 20));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 180, 110, 18));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 300, 25));

        jLabel26.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel26.setText("Reembolsar a");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 90, -1));

        jLabel27.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel27.setText("Monto Total");
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 70, -1));

        jLabel28.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel28.setText("Moneda del Comp.");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 90, -1));

        jLabel29.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel29.setText("Forma de Pago");
        jPanel2.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 90, -1));

        jLabel24.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel24.setText("Monto IGV");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 60, -1));

        jLabel25.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel25.setText("B");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 20, 20));

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel12.setText("FT");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 20, 20));

        txtrazref.setForeground(new java.awt.Color(255, 255, 255));
        txtrazref.setBorder(null);
        jPanel2.add(txtrazref, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 170, 20));

        btncomprobante.setText("jButton1");
        btncomprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobanteActionPerformed(evt);
            }
        });
        jPanel2.add(btncomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, 30, 20));

        cboconceptodetra.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboconceptodetra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboconceptodetraItemStateChanged(evt);
            }
        });
        jPanel2.add(cboconceptodetra, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 165, 300, 20));

        cbotipocomprobante.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbotipocomprobante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbotipocomprobanteItemStateChanged(evt);
            }
        });
        jPanel2.add(cbotipocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 150, 20));

        cboformapago.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboformapago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboformapagoItemStateChanged(evt);
            }
        });
        jPanel2.add(cboformapago, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 20, 140, 20));

        cbodestinopago.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbodestinopago.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbodestinopagoItemStateChanged(evt);
            }
        });
        jPanel2.add(cbodestinopago, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 140, 20));

        cbomoneda.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbomoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbomonedaItemStateChanged(evt);
            }
        });
        jPanel2.add(cbomoneda, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 90, 20));

        lblporcigv.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblporcigv.setText("(0.00)");
        jPanel2.add(lblporcigv, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 120, -1, -1));

        lblporc.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblporc.setText("(0.00)");
        jPanel2.add(lblporc, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, -1, -1));

        btncompro.setBackground(new java.awt.Color(255, 255, 255));
        btncompro.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Datos de Comprobante", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel2.add(btncompro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 890, 210));

        chkbtnactualizar.setBackground(new java.awt.Color(255, 255, 255));
        chkbtnactualizar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkbtnactualizarItemStateChanged(evt);
            }
        });
        jPanel2.add(chkbtnactualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(799, 142, -1, -1));

        btnactualizatodo.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        btnactualizatodo.setText("M >> P");
        btnactualizatodo.setEnabled(false);
        btnactualizatodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizatodoActionPerformed(evt);
            }
        });
        jPanel2.add(btnactualizatodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, 70, 30));

        lblnrocomprobante.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnrocomprobante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblnrocomprobante.setText("0 Comprobantes");
        lblnrocomprobante.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblnrocomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 180, 100, -1));

        jLabel23.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel23.setText("Nro. Cta. Contable");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 184, -1, 20));

        txtNumeroCuentaContable.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        txtNumeroCuentaContable.setPreferredSize(new java.awt.Dimension(20, 20));
        jPanel2.add(txtNumeroCuentaContable, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 184, 300, 20));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(800, 237));
        jPanel3.setPreferredSize(new java.awt.Dimension(800, 237));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblistacomprobante.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tblistacomprobante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblistacomprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblistacomprobanteMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblistacomprobanteMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblistacomprobanteMouseReleased(evt);
            }
        });
        tblistacomprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblistacomprobanteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblistacomprobante);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 890, 260));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
////
        if (validar()) {
            EComprobante objtc = new EComprobante();

            objtc.setCodComprobante(codcompro);
            objtc.setTipoComprobante(codcomprobante);
            objtc.setNumComprobante(txtnumcompro.getText().toString());
            objtc.setFechaComprobante(fechaformateador(dcFechaEmision.getDate()));
            objtc.setRazonSocial(txtpro.getText());
            objtc.setDocProveedor(txtrucpro.getText());
            objtc.setGlosaComprante(txtglosa.getText());
            objtc.setComprobanteSujeto(estado);
            objtc.setCodDetraccion(coddetra);
            if (rbnada.isSelected()) {
                objtc.setCodDetraccion("0");
            }
            objtc.setFormaPago(formapago);
            if (cboformapago.getSelectedItem().toString().equalsIgnoreCase("TRANS-REEMBOLSO")) {
                objtc.setDestinoPago(lisdni.get(cbodestinopago.getSelectedIndex() - 1).getRsDestino());
            } else {
                objtc.setDestinoPago("");
            }
            objtc.setCodMoneda(codmon);
            objtc.setTCComprobante(Double.parseDouble(lblTC.getText().toString()));
            if (tc == 0.00) {
                tc = Double.valueOf(lblTC.getText().trim());
            }
            if (codmon == 1) {
                objtc.setMontoBase(Double.parseDouble(txtmontobase.getText().toString().replace(",", "")));
                objtc.setMontoOtros(Double.parseDouble(txtmontootros.getText().toString().replace(",", "")));
                objtc.setMontoTotal(Double.parseDouble(txtmontototal.getText().toString().replace(",", "")));
                objtc.setMontoIGV(Double.parseDouble(txtmontoigv.getText().toString().replace(",", "")));
                objtc.setMontoRetDet(Double.parseDouble(txtmonretdet.getText().toString().replace(",", "")));
                objtc.setMontoPagar(Double.parseDouble(txtmontopagar.getText().toString().replace(",", "")));
                if (tc == 0.00 || lblTC.getText().toString().equalsIgnoreCase("0.0")) {
                    objtc.setMontoBaseDOL(0.00);
                    objtc.setMontoOtrosDOL(0.00);
                    objtc.setMontoTotalDOL(0.00);
                    objtc.setMontoIGVDOL(0.00);
                    objtc.setMontoRetDetDOL(0.00);
                    objtc.setMontoPagarDOL(0.00);
                } else {
                    objtc.setMontoBaseDOL(Double.parseDouble(txtmontobase.getText().toString().replace(",", "")) / Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoOtrosDOL(Double.parseDouble(txtmontootros.getText().toString().replace(",", "")) / Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoTotalDOL(Double.parseDouble(txtmontototal.getText().toString().replace(",", "")) / Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoIGVDOL(Double.parseDouble(txtmontoigv.getText().toString().replace(",", "")) / Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoRetDetDOL(Double.parseDouble(txtmonretdet.getText().toString().replace(",", "")) / Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoPagarDOL(Double.parseDouble(txtmontopagar.getText().toString().replace(",", "")) / Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                }
            } else {
                objtc.setMontoBaseDOL(Double.parseDouble(txtmontobase.getText().toString().replace(",", "")));
                objtc.setMontoOtrosDOL(Double.parseDouble(txtmontootros.getText().toString().replace(",", "")));
                objtc.setMontoTotalDOL(Double.parseDouble(txtmontototal.getText().toString().replace(",", "")));
                objtc.setMontoIGVDOL(Double.parseDouble(txtmontoigv.getText().toString().replace(",", "")));
                objtc.setMontoRetDetDOL(Double.parseDouble(txtmonretdet.getText().toString().replace(",", "")));
                objtc.setMontoPagarDOL(Double.parseDouble(txtmontopagar.getText().toString().replace(",", "")));
                if (tc == 0.00) {
                    objtc.setMontoBase(0.00);
                    objtc.setMontoOtros(0.00);
                    objtc.setMontoTotal(0.00);
                    objtc.setMontoIGV(0.00);
                    objtc.setMontoRetDet(0.00);
                    objtc.setMontoPagar(0.00);
                } else {
                    objtc.setMontoBase(Double.parseDouble(txtmontobase.getText().toString().replace(",", "")) * Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoOtros(Double.parseDouble(txtmontootros.getText().toString().replace(",", "")) * Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoTotal(Double.parseDouble(txtmontototal.getText().toString().replace(",", "")) * Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoIGV(Double.parseDouble(txtmontoigv.getText().toString().replace(",", "")) * Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoRetDet(Double.parseDouble(txtmonretdet.getText().toString().replace(",", "")) * Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                    objtc.setMontoPagar(Double.parseDouble(txtmontopagar.getText().toString().replace(",", "")) * Double.parseDouble(lblTC.getText().toString().replace(",", "")));
                }
            }
            objtc.setPorcentaje(porcentaje);
            objtc.setPorcIGV(porcigv);
            if (formapago.equalsIgnoreCase("RENDICION-CANCELADO") || formapago.equalsIgnoreCase("SERVICIOS-CANCELADO") || formapago.equalsIgnoreCase("CHEQUE")) {
                objtc.setEstado("CANCELADO");
            } else {
                objtc.setEstado("PENDIENTE");
            }
            objtc.setComprobanteRef(txtcomprobanteref.getText());
            objtc.setRazonSocialRef(txtrazref.getText());
            System.out.println("hola  " + txtfecref.getText());
            objtc.setFechaRef(txtfecref.getText());
            objtc.setUsuCrea(SesionUsuario.misesion.getUsuario());
            objtc.setUsuModi(SesionUsuario.misesion.getUsuario());
            objtc.setNroCuentaContable(txtNumeroCuentaContable.getText());
            LComprobante ltc = new LComprobante();
            if (opc == 0) {
                //agregar
                if (validacionxfactura()) {
                    if (!validacionxrh()) {
                        JOptionPane.showMessageDialog(this, "Recibo por honorario no cuenta con Retención/Detracción.");
                    }
                    if (JOptionPane.showConfirmDialog(null, "Se va GRABAR el Comprobante " + txtpro.getText().toString().trim().toUpperCase() + " - " + txtnumcompro.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        if (ltc.InsertarComprobante(objtc)) {
                            JOptionPane.showMessageDialog(this, "Se grabó correctamente el Comprobante " + txtpro.getText().toString().trim().toUpperCase() + " - " + txtnumcompro.getText().trim() + ".");
                            listacomprobante();
                            limpiar();
                        } else {
                            JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");

                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No puede grabar una factura con un monto a pagar mayor a 700 soles sin Retención/Detracción.");
                }
            } else {
//                  JOptionPane.showMessageDialog(this, "La Detraccion que intenta registrar ya existe, ingrese uno nuevo.");
//                  txtcod.requestFocus();
            }
            if (opc == 1) {
                if (estadocomprobante.equals("PENDIENTE") || estadocomprobante.equalsIgnoreCase("MIGRADO") || estadocomprobante.equalsIgnoreCase("RECHAZADO") || estadocomprobante.equalsIgnoreCase("MIGRADO AUTOMATICO")) {
                    //modificar
                    if (validacionxfactura()) {
                        if (JOptionPane.showConfirmDialog(null, "Se va MODIFICAR el Comprobante " + txtpro.getText().toString().trim().toUpperCase() + " - " + txtnumcompro.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                            if (ltc.ActualizarComprobante(objtc)) {
                                JOptionPane.showMessageDialog(this, "Se actualizó correctamente el Comprobante " + txtpro.getText().toString().trim().toUpperCase() + " - " + txtnumcompro.getText().trim() + ".");
                                listacomprobante();
                                limpiar();
                            } else {
                                JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                            }

                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No puede grabar una factura con un monto a pagar mayor a 700 soles sin Retención/Detracción.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se puede MODIFICAR el Comprobante.");
                }
            }

        } else {

        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        opc = 0;
//       llenarigv();
        limpiar();

// TODO add your handling code here:
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed

        if (estadocomprobante.equals("PENDIENTE") || estadocomprobante.equals("MIGRADO")) {

//             int fila = tblistacomprobante.getSelectedRow();
            if (JOptionPane.showConfirmDialog(null, "Se va Eliminar el Comprobante " + txtpro.getText().toString().trim().toUpperCase() + " - " + txtnumcompro.getText().trim() + ". ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                String fill = txtnumcompro.getText().trim();
                if (lcom.eliminarComprobante(codcompro)) {
                    listacomprobante();
                    limpiar();
                    JOptionPane.showMessageDialog(this, "Se ELIMINÓ correctamente el comprobante N°" + fill + ".");
                } else {
                    JOptionPane.showMessageDialog(this, "Error de tipo de Dato y/o Conexión de BD.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sólo se puede Eliminar Comprobante en estado PENDIENTE.");
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproveedorActionPerformed
        ConsultaProveedor conpro = new ConsultaProveedor();
        Menu.dpane2.add(conpro);
        centerJIF(conpro);
        conpro.setVisible(true);
        conpro.otrofrm = 0;
        SesionUsuario.txtrazonsocial = txtpro;
        SesionUsuario.txtrucpro = txtrucpro;
        SesionUsuario.rbtretencion = rbretencion;
        SesionUsuario.rbtdetraccion = rbdetraccion;
        SesionUsuario.rbtninguno = rbnada;

    }//GEN-LAST:event_btnproveedorActionPerformed

    private void btnTCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTCActionPerformed
        TipoCambio tc = new TipoCambio();
        Menu.dpane2.add(tc);
        SesionUsuario.lblTC = lblTC;
        centerJIF(tc);
        tc.otrofrm = 1;
        tc.mifecha = fechaformateador(dcFechaEmision.getDate());
        tc.ingresartc(fechaformateador(dcFechaEmision.getDate()));
        tc.setVisible(true);

    }//GEN-LAST:event_btnTCActionPerformed

    private void tblistacomprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacomprobanteMouseClicked

    }//GEN-LAST:event_tblistacomprobanteMouseClicked

    private void tblistacomprobanteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblistacomprobanteKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            int fila = tblistacomprobante.getSelectedRow();
            seleccionarfila(fila);
            System.out.print("paso 1");
        }
        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.print("paso 2");
            int fila = tblistacomprobante.getSelectedRow();
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_tblistacomprobanteKeyReleased
 private String BuscarDetraccion(ArrayList<EDetraccion> lista,String coddestra){
    String descripcion="";
     for(EDetraccion objde: lista){
     if(objde.getCodDetraccion().equalsIgnoreCase(coddestra)){
          descripcion = objde.getDescripcion();
          break;
     }
    }
     return descripcion;
 }
    private void txtmonretdetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmonretdetActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmonretdetActionPerformed

    private void txtmontobaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmontobaseKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcularmontos();
        }
    }//GEN-LAST:event_txtmontobaseKeyReleased

    private void txtmontototalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtmontototalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtmontototalActionPerformed

    private void rbretencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbretencionActionPerformed


    }//GEN-LAST:event_rbretencionActionPerformed

    private void rbdetraccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbdetraccionActionPerformed
        rbretencion.setSelected(false);
        rbdetraccion.setSelected(true);
        cboconceptodetra.setEnabled(true);
        rbnada.setSelected(false);
        lblporc.setText("(0.00)");
        porcentaje = 0.00;
        calcularmontos();
        coddetra = "0";

        estado = "DETRACCION";

    }//GEN-LAST:event_rbdetraccionActionPerformed

    private void rbnadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbnadaActionPerformed
        rbretencion.setSelected(false);
        rbdetraccion.setSelected(false);
        cboconceptodetra.setEnabled(false);
        cboconceptodetra.setSelectedIndex(0);
        rbnada.setSelected(true);
        lblporc.setText("(0.00)");
        porcentaje = 0.00;
        coddetra = "0";
        estado = "SIN RET/DET";
    }//GEN-LAST:event_rbnadaActionPerformed

    private void cboconceptodetraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboconceptodetraItemStateChanged

        llenaPorcDetra();
        calcularmontos();


    }//GEN-LAST:event_cboconceptodetraItemStateChanged

    private void txtmontootrosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmontootrosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            calcularmontos();
        }
    }//GEN-LAST:event_txtmontootrosKeyReleased

    private void cbomonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbomonedaItemStateChanged
        llenacodMoneda();
    }//GEN-LAST:event_cbomonedaItemStateChanged

    private void cbotipocomprobanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbotipocomprobanteItemStateChanged
        llenacodComprobante();

        LComprobante lc = new LComprobante();
        porcentaje = lc.porcentajeretencion("0");
        lblporc.setText("(" + porcentaje + ")");
        System.out.println("" + porcentaje);
        txtrazref.setText("");
        txtrucpro.setText("");
        cboconceptodetra.setEnabled(false);
        if (desccomprobante.equals("LIQUIDACIÓN DE COBRANZA") || desccomprobante.equals("NOTA DE CREDITO") || desccomprobante.equals("NOTA DE DEBITO") || desccomprobante.equals("RECIBO DE SERVICIOS")) {
            rbdetraccion.setEnabled(false);
            rbretencion.setEnabled(false);
            rbnada.setEnabled(true);
            porcentaje = 0.0;
            lblporc.setText("(" + porcentaje + ")");
            System.out.println("" + porcentaje);
            cboconceptodetra.setEnabled(false);
            coddetra = "0";
            rbnada.setSelected(true);
            porcentaje = 0.00;
            estado = "SIN RET/DET";
            lblporc.setText("(" + porcentaje + ")");
        }
        if (desccomprobante.equals("RECIBO POR HONORARIOS")) {
            rbdetraccion.setEnabled(false);
            rbretencion.setEnabled(true);
            rbnada.setEnabled(true);
            cboconceptodetra.setEnabled(false);
            porcentaje = lc.porcentajeretencion("1");
            lblporc.setText("(" + porcentaje + ")");
            rbnada.setSelected(true);
            porcentaje = 0.00;
            lblporc.setText("(" + porcentaje + ")");
            estado = "SIN RET/DET";
            coddetra = "0";
        }
        if (desccomprobante.equals("FACTURA")) {
            rbdetraccion.setEnabled(true);
            rbretencion.setEnabled(true);
            rbnada.setEnabled(true);
            cboconceptodetra.setEnabled(false);
            rbnada.setSelected(true);
            porcentaje = 0.00;
            lblporc.setText("(" + porcentaje + ")");
            estado = "SIN RET/DET";
        }

        if (desccomprobante.equals("NOTA DE CREDITO") || desccomprobante.equals("NOTA DE DEBITO")) {
            btncomprobante.setEnabled(true);
        } else {
            btncomprobante.setEnabled(false);
        }
    }//GEN-LAST:event_cbotipocomprobanteItemStateChanged

    private void cboformapagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboformapagoItemStateChanged
        llenaformapago1();

        if (formapago.equals("CHEQUE") || formapago.equals("TRANSFERENCIA") || formapago.equalsIgnoreCase("RENDICION-CANCELADO")) {
            cbodestinopago.setSelectedIndex(0);
            cbodestinopago.setEnabled(false);
            destinopago = "";

        }
        if (formapago.equals("TRANS-REEMBOLSO")) {
            if (cbotipocomprobante.getSelectedItem().toString().trim().equalsIgnoreCase("FACTURA") || cbotipocomprobante.getSelectedItem().toString().trim().equalsIgnoreCase("LIQUIDACIÓN DE COBRANZA")) {
                cbodestinopago.setEnabled(true);
            }
        }

    }//GEN-LAST:event_cboformapagoItemStateChanged

    private void cbodestinopagoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbodestinopagoItemStateChanged
        llenadestino1();        // TODO add your handling code here:
    }//GEN-LAST:event_cbodestinopagoItemStateChanged

    private void txtproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtproActionPerformed

    }//GEN-LAST:event_txtproActionPerformed

    private void btncomprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprobanteActionPerformed
        ConsultaComprobante conpro = new ConsultaComprobante();
        Menu.dpane2.add(conpro);
        centerJIF(conpro);
        conpro.setVisible(true);

        SesionUsuario.txtcomprobante = txtcomprobanteref;
        SesionUsuario.txtrazonref = txtrazref;
        SesionUsuario.txtfecharef = txtfecref;
        SesionUsuario.txtrazonsocial = txtpro;
        SesionUsuario.txtrucpro = txtrucpro;


    }//GEN-LAST:event_btncomprobanteActionPerformed

    private void rbnadaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbnadaItemStateChanged
        // TODO add your handling code here:
        if (rbnada.isSelected()) {
            cboconceptodetra.setSelectedIndex(0);
            porcentaje = 0.0;
            lblporc.setText("0.00");
        }
    }//GEN-LAST:event_rbnadaItemStateChanged

    private void rbdetraccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbdetraccionItemStateChanged
        // TODO add your handling code here:
        if (rbdetraccion.isSelected()) {

        }
    }//GEN-LAST:event_rbdetraccionItemStateChanged

    private void rbretencionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbretencionItemStateChanged
        // TODO add your handling code here:
        if (rbretencion.isSelected()) {
            estado = "RETENCION";
            cboconceptodetra.setSelectedIndex(0);
            cboconceptodetra.setEnabled(false);
            if (cbotipocomprobante.getSelectedItem().toString().equalsIgnoreCase("RECIBO POR HONORARIOS")) {
                LComprobante lc = new LComprobante();
                porcentaje = lc.porcentajeretencion("1");
                lblporc.setText("(" + porcentaje + ")");
            } else {
                LComprobante lc = new LComprobante();
                porcentaje = lc.porcentajeretencion("0");
                lblporc.setText("(" + porcentaje + ")");
            }
            calcularmontos();
        }
    }//GEN-LAST:event_rbretencionItemStateChanged

    private void txtglosaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtglosaKeyTyped
        // TODO add your handling code here:
        if (txtglosa.getText().length() > 29) {
            evt.consume();
        }
    }//GEN-LAST:event_txtglosaKeyTyped

    private void tblistacomprobanteMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacomprobanteMouseReleased
        // TODO add your handling code here:
        int fila = tblistacomprobante.getSelectedRow();
        if (fila >= 0) {
            opc = 1;
            System.out.print("paso en este paso 3");
            seleccionarfila(fila);
        }
    }//GEN-LAST:event_tblistacomprobanteMouseReleased

    private void chkbtnactualizarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkbtnactualizarItemStateChanged
        // TODO add your handling code here:
        if (chkbtnactualizar.isSelected()) {
            btnactualizatodo.setEnabled(true);
        } else {
            btnactualizatodo.setEnabled(false);
        }
    }//GEN-LAST:event_chkbtnactualizarItemStateChanged

    private void btnactualizatodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizatodoActionPerformed
        // TODO add your handling code here:
        if (liscom.size() > 0) {
            LComprobante lc = new LComprobante();
            if (JOptionPane.showConfirmDialog(this, "Se van Actualizar todos los Comprobantes en estado MIGRADO. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                for (int i = 0; i < liscom.size(); i++) {
                    if (liscom.get(i).getEstado().equalsIgnoreCase("MIGRADO")) {
                        lc.ActualizarEstadoComprobante(liscom.get(i).getNumComprobante().trim());
                    }
                }
                listacomprobante();
                JOptionPane.showMessageDialog(this, "Se actualizó el estado de los comprobantes.");
            }
        }
    }//GEN-LAST:event_btnactualizatodoActionPerformed

    private void tblistacomprobanteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacomprobanteMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tblistacomprobanteMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bggrupo;
    private javax.swing.JButton btnTC;
    private javax.swing.JButton btnactualizatodo;
    private javax.swing.JLabel btncompro;
    private javax.swing.JButton btncomprobante;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnproveedor;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cboconceptodetra;
    private javax.swing.JComboBox cbodestinopago;
    private javax.swing.JComboBox cboformapago;
    private javax.swing.JComboBox cbomoneda;
    private javax.swing.JComboBox cbotipocomprobante;
    private javax.swing.JCheckBox chkbtnactualizar;
    private com.toedter.calendar.JDateChooser dcFechaEmision;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblTC;
    private javax.swing.JLabel lblnrocomprobante;
    private javax.swing.JLabel lblporc;
    private javax.swing.JLabel lblporcigv;
    public javax.swing.JRadioButton rbdetraccion;
    private javax.swing.JRadioButton rbnada;
    public javax.swing.JRadioButton rbretencion;
    private javax.swing.JTable tblistacomprobante;
    private javax.swing.JTextField txtNumeroCuentaContable;
    private javax.swing.JTextField txtcomprobanteref;
    private javax.swing.JTextField txtfecref;
    private javax.swing.JTextArea txtglosa;
    private javax.swing.JTextField txtmonretdet;
    private javax.swing.JTextField txtmontobase;
    private javax.swing.JTextField txtmontoigv;
    private javax.swing.JTextField txtmontootros;
    private javax.swing.JTextField txtmontopagar;
    private javax.swing.JTextField txtmontototal;
    private javax.swing.JTextField txtnumcompro;
    private javax.swing.JTextField txtpro;
    private javax.swing.JTextField txtrazref;
    private javax.swing.JTextField txtrucpro;
    // End of variables declaration//GEN-END:variables
}
