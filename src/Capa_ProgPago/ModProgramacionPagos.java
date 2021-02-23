/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_ProgPago;


import Capa_Entidades.EComprobante;
import Capa_Entidades.EConsolidado;
import Capa_Entidades.ECuentaBancaria;
import Capa_Entidades.EDetProgramPagos;
import Capa_Entidades.EFormaPago;
import Capa_Entidades.EParametro;
import Capa_Entidades.EPeriodo;
import Capa_Entidades.EProgPagosCabecera;
import javax.swing.ImageIcon;
import Capa_Entidades.EUsuario;
import Capa_Logica.LComprobante;
import Capa_Logica.LConsultaComprobante;
import Capa_Logica.LParametro;
import Capa_Logica.LProgPagosCabecera;
import Capa_Logica.SesionUsuario;
import java.awt.Dimension;
import java.awt.Point;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class ModProgramacionPagos extends javax.swing.JInternalFrame {
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    ArrayList<EFormaPago> lisformapago = new ArrayList<>();
    ArrayList<EUsuario> usu123 = new ArrayList<>();
    ArrayList<EParametro> liscuenpara = new ArrayList<>();
    ArrayList<EParametro> lisbanpara = new ArrayList<>();
    ArrayList<EConsolidado> lstconsolidado = new ArrayList<>();
    ArrayList<EProgPagosCabecera> lstMAX = new ArrayList<>();
    String titulos[] = {"CodCompro", "TipoComp", "NumComp", "FecComp", "Estado", "RazonSocial",
        "DocProveedor", "Glosa", "Sujeto A", "ConcepDetra", "FormaPago", "DestinoPago", "Moneda", "TC",
        "MBaseSOL", "MOtrosSOL", "MIGVSOL", "MTotalSOL", "MRetDetSOL", "MPagarSOL",
        "MBaseDOL", "MOtrosDOL", "MIGVDOL", "MTotalDOL", "MRetDetDOL",
        "MPagarDOL", "PorcRetDet", "PorcIGV", "CompRef", "RazonSocialRef", "FechaRef"};
    ArrayList<EUsuario> lstusuario = new ArrayList<>();
    ArrayList<EPeriodo> lstperiodo = new ArrayList<>();
    ArrayList<EComprobante> liscom = new ArrayList<>();
    ArrayList<EComprobante> liscom2 = new ArrayList<>();
    ArrayList<EComprobante> lismodi=new ArrayList<>();
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    ArrayList<EDetProgramPagos> lstdetalleprog = new ArrayList<>();
    String titulos2[] = {"CodCompro", "TipoComp", "NumComp", "FecComp", "Estado", "RazonSocial",
        "DocProveedor", "Glosa", "Sujeto A", "ConcepDetra", "FormaPago", "DestinoPago", "Moneda", "TC",
        "MBaseSOL", "MOtrosSOL", "MIGVSOL", "MTotalSOL", "MRetDetSOL", "MPagarSOL",
        "MBaseDOL", "MOtrosDOL", "MIGVDOL", "MTotalDOL", "MRetDetDOL",
        "MPagarDOL", "PorcRetDet", "PorcIGV", "CompRef", "RazonSocialRef", "FechaRef"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    Point locked = null;
    public int codigomax = 0;
    int opc = 0;
    public String aten = "P";
    String tipestado;
    String proveedoressincuenta="";
    
    /**
     * Creates new form ProgramacionDePagos
     */
    
    public ModProgramacionPagos() {
        initComponents();
        this.setTitle("Modificar Programación de Pagos");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        Date hoy = new Date();
        dcfechainicio.setDate(hoy);
        dcfechafin.setDate(hoy);
        llenaFormaPago();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblistacomprobante.setModel(modTbdatos);
        tblistacomprobante.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbListaSeleccion.getTableHeader().setReorderingAllowed(false);
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
        rbtsoles.setSelected(true);
        listacomprobante();
        llenarbancoprincipal();
    }

    private void llenarbancoprincipal() {
        LParametro limp = new LParametro();
        lisbanpara = limp.ListaBancoPrincipal();
        cbobancoprincipal.removeAllItems();
        cbobancoprincipal.addItem("<Seleccione>");
        for (int i = 0; i < lisbanpara.size(); i++) {
            cbobancoprincipal.addItem("Principal - " + lisbanpara.get(i).getNomBanco());
            cbobancoprincipal.addItem("Secundario - " + lisbanpara.get(i).getNomBanco2());
        }
    }
    
    private void llenarcuentaprincipal() {
        LParametro limp = new LParametro();
        liscuenpara = limp.ListaCuentasPrincipal();
        cbocuentaprincipal.removeAllItems();
        cbocuentaprincipal.addItem("<Seleccione>");
        for (int i = 0; i < liscuenpara.size(); i++) {
            if (cbobancoprincipal.getSelectedIndex() == 1) {
                cbocuentaprincipal.addItem(" S/. - " + liscuenpara.get(i).getCTASoles());
                cbocuentaprincipal.addItem(" US$. - " + liscuenpara.get(i).getCTADolares());
            }
            if (cbobancoprincipal.getSelectedIndex() == 2) {
                cbocuentaprincipal.addItem(" S/. - " + liscuenpara.get(i).getCTASoles2());
                cbocuentaprincipal.addItem(" US$. - " + liscuenpara.get(i).getCTADolares2());
            }
        }
    }
    
    private void limpiar(){
        rbfecha.setSelected(true);
        rbproveedor.setSelected(false);
        cboformapago.setSelectedIndex(0);
        txtpro.setText("");
        Date hoy=new Date();
        dcfechafin.setDate(hoy);
        dcfechainicio.setDate(hoy);
        modTbdatos2.setRowCount(0);
        tblistacomprobante.setModel(modTbdatos2);
        liscom2.removeAll(liscom2);
        listacomprobante();
    }
    
    
    public void MaxCodigo(){
        LProgPagosCabecera limp = new LProgPagosCabecera();
        lstMAX=limp.MaxCodigo();
        for (int i = 0; i < lstMAX.size(); i++) {
        codigomax=(lstMAX.get(i).getCod_programacion());
        System.out.println(codigomax);
        }
    }
    
    public void centerJIF2(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2 );
        int height = ((desktopSize.height - jInternalFrameSize.height) /2)-40;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
    
    public void modificacion(ArrayList<EComprobante> lisdetalle){
        liscom2=lisdetalle;
        lismodi=lisdetalle;
        llenartabseleccion();
    }
    
    private void volveralestadoanterior(){
        if(lismodi.size()>0){
            
        }
    }
    
    private void listacomprobante() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        if (opc == 0) {
            LComprobante lpro = new LComprobante();
            liscom = lpro.listaComprobante2();
        }
        for (int i = liscom.size() - 1; i >= 0; i--) {
            if (liscom.get(i).getEstado().equalsIgnoreCase("PROGRAMADO") || liscom.get(i).getEstado().equalsIgnoreCase("RE-PROGRAMADO")) {
                liscom.remove(i);
            }
        }
        for (int i = liscom.size() - 1; i >= 0; i--) {
            if (rbtsoles.isSelected()) {
                if(liscom.get(i).getNomMoneda().equalsIgnoreCase("DOLARES")){
                liscom.remove(i);    
                }
            }else{
                if(liscom.get(i).getNomMoneda().equalsIgnoreCase("SOLES")){
                   liscom.remove(i);    
                }
            }
        }
        modTbdatos.setRowCount(0);
        if (liscom.size() > 0) {
            for (int i = 0; i < liscom.size(); i++) {
                Object nuevafila[] = new Object[33];
                nuevafila[0] = liscom.get(i).getCodComprobante();
                nuevafila[1] = liscom.get(i).getTipoComprobante();
                nuevafila[2] = liscom.get(i).getNumComprobante();
                nuevafila[3] = liscom.get(i).getFechaComprobante();
                nuevafila[4] = liscom.get(i).getEstado();
                nuevafila[5] = liscom.get(i).getRazonSocial();
                nuevafila[6] = liscom.get(i).getDocProveedor();
                nuevafila[7] = liscom.get(i).getGlosaComprante();
                nuevafila[8] = liscom.get(i).getComprobanteSujeto();
                nuevafila[9] = liscom.get(i).getTipoDetra();
                nuevafila[10] = liscom.get(i).getFormaPago();
                nuevafila[11] = liscom.get(i).getDestinoPago();
                nuevafila[12] = liscom.get(i).getNomMoneda();
                nuevafila[13] = liscom.get(i).getTCComprobante();
                nuevafila[14] = formato.format(liscom.get(i).getMontoBase());
                nuevafila[15] = formato.format(liscom.get(i).getMontoOtros());
                nuevafila[16] = formato.format(liscom.get(i).getMontoIGV());
                nuevafila[17] = formato.format(liscom.get(i).getMontoTotal());
                nuevafila[18] = formato.format(liscom.get(i).getMontoRetDet());
                nuevafila[19] = formato.format(liscom.get(i).getMontoPagar());
                nuevafila[20] = formato.format(liscom.get(i).getMontoBaseDOL());
                nuevafila[21] = formato.format(liscom.get(i).getMontoOtrosDOL());
                nuevafila[22] = formato.format(liscom.get(i).getMontoIGVDOL());
                nuevafila[23] = formato.format(liscom.get(i).getMontoTotalDOL());
                nuevafila[24] = formato.format(liscom.get(i).getMontoRetDetDOL());
                nuevafila[25] = formato.format(liscom.get(i).getMontoPagarDOL());
                nuevafila[26] = formato.format(liscom.get(i).getPorcentaje());
                nuevafila[27] = formato.format(liscom.get(i).getPorcIGV());
                nuevafila[28] = liscom.get(i).getComprobanteRef();
                nuevafila[29] = liscom.get(i).getRazonSocialRef();
                nuevafila[30] = liscom.get(i).getFechaRef();

                modTbdatos.addRow(nuevafila);
            }
        }
        tblistacomprobante.setModel(modTbdatos);
        lblnumcomprobante.setText(tblistacomprobante.getRowCount() + " Comprobante(es)");
    }

     public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2 );
        int height = ((desktopSize.height - jInternalFrameSize.height) /4);
        jif.setLocation(width, height);
        jif.setVisible(true);
    } 
     
     private void llenaFormaPago() {
        LComprobante limp = new LComprobante();
        lisformapago = limp.ListaFormaPago();
        cboformapago.removeAllItems();
        cboformapago.addItem("TODOS");
        for (int i = 0; i < lisformapago.size(); i++) {
            cboformapago.addItem(lisformapago.get(i).getDescPago());
        }
    }
     
    String fechaformato(Date fecha) {
        String fec = new SimpleDateFormat("dd-MM-yyyy").format(fecha);
        return fec;
    }
     
    private void cambiarestado(){
        if(lismodi.size()>0){
            LProgPagosCabecera lp=new LProgPagosCabecera();
            for (int i = 0; i < lismodi.size(); i++) {
                lp.CambiarEstado(lismodi.get(i).getCodComprobante());
            }
        }
    }
    
    private void llenartabseleccion() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        if (aten.equals("P")) {
            modTbdatos2.setRowCount(0);
            tbListaSeleccion.setModel(modTbdatos2);
            tbListaSeleccion.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            tbListaSeleccion.getColumnModel().getColumn(0).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(1).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(2).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(3).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(4).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(5).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(6).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(7).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(8).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(9).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(10).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(11).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(12).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(13).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(14).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(15).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(16).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(17).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(18).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(19).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(20).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(21).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(22).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(23).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(24).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(25).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(27).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(28).setPreferredWidth(100);
            tbListaSeleccion.getColumnModel().getColumn(29).setPreferredWidth(80);
            tbListaSeleccion.getColumnModel().getColumn(30).setPreferredWidth(100);
            if (liscom2.size() > 0) {
                for (int i = 0; i < liscom2.size(); i++) {
                    EComprobante at;
                    at = liscom2.get(i);
                    Object nuevafila[] = new Object[33];
                    nuevafila[0] = at.getCodComprobante();
                    nuevafila[1] = at.getTipoComprobante();
                    nuevafila[2] = at.getNumComprobante();
                    nuevafila[3] = at.getFechaComprobante();
                    nuevafila[4] = at.getEstado();
                    nuevafila[5] = at.getRazonSocial();
                    nuevafila[6] = at.getDocProveedor();
                    nuevafila[7] = at.getGlosaComprante();
                    nuevafila[8] = at.getComprobanteSujeto();
                    nuevafila[9] = at.getTipoDetra();
                    nuevafila[10] = at.getFormaPago();
                    nuevafila[11] = at.getDestinoPago();
                    nuevafila[12] = at.getNomMoneda();
                    nuevafila[13] = at.getTCComprobante();
                    nuevafila[14] = formato.format(at.getMontoBase());
                    nuevafila[15] = formato.format(at.getMontoOtros());
                    nuevafila[16] = formato.format(at.getMontoIGV());
                    nuevafila[17] = formato.format(at.getMontoTotal());
                    nuevafila[18] = formato.format(at.getMontoRetDet());
                    nuevafila[19] = formato.format(at.getMontoPagar());
                    nuevafila[20] = formato.format(at.getMontoBaseDOL());
                    nuevafila[21] = formato.format(at.getMontoOtrosDOL());
                    nuevafila[22] = formato.format(at.getMontoIGVDOL());
                    nuevafila[23] = formato.format(at.getMontoTotalDOL());
                    nuevafila[24] = formato.format(at.getMontoRetDetDOL());
                    nuevafila[25] = formato.format(at.getMontoPagarDOL());
                    nuevafila[26] = formato.format(at.getPorcentaje());
                    nuevafila[27] = formato.format(at.getPorcIGV());
                    nuevafila[28] = at.getComprobanteRef();
                    nuevafila[29] = at.getRazonSocialRef();
                    nuevafila[30] = at.getFechaRef();
                    modTbdatos2.addRow(nuevafila);
                }
            } else {
                modTbdatos2.setRowCount(0);
            }
            tbListaSeleccion.setModel(modTbdatos2);
            lblnumseleccionados.setText(tbListaSeleccion.getRowCount() + " Seleccion(es)");
        }
    }

    private ArrayList<EConsolidado> lstconsolidadofinal(ArrayList<EConsolidado> cons){
        ArrayList<EConsolidado> miconsolidado=new ArrayList();
        for (int i = 0; i < cons.size(); i++) {
            int val=0;
            for (int j = 0; j < miconsolidado.size(); j++) {
                if(miconsolidado.get(j).getDocProv().equalsIgnoreCase(cons.get(i).getDocProv()) && !cons.get(i).getTipoComprob().equalsIgnoreCase("NC")){
                    val=1;
                    miconsolidado.get(j).setMontosoles(miconsolidado.get(j).getMontosoles()+cons.get(i).getMontosoles());
                    miconsolidado.get(j).setMontodolares(miconsolidado.get(j).getMontodolares()+cons.get(i).getMontodolares());
                }
                if(miconsolidado.get(j).getDocProv().equalsIgnoreCase(cons.get(i).getDocProv()) && cons.get(i).getTipoComprob().equalsIgnoreCase("NC")){
                    val=2;
                    miconsolidado.get(j).setMontosoles(miconsolidado.get(j).getMontosoles()-cons.get(i).getMontosoles());
                    miconsolidado.get(j).setMontodolares(miconsolidado.get(j).getMontodolares()-cons.get(i).getMontodolares());
            }
            }
            if(val==0){
                if(cons.get(i).getTipoComprob().trim().equalsIgnoreCase("NC")){
                    cons.get(i).setMontosoles(cons.get(i).getMontosoles()*-1);
                    cons.get(i).setMontodolares(cons.get(i).getMontodolares()*-1);
                    miconsolidado.add(cons.get(i));
                }else{
                    miconsolidado.add(cons.get(i));    
                }
            }
        }
        return miconsolidado;
    }
    
    private boolean validarcabecera() {
        int count = 0;
        if (liscom2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar comprobantes para continuar.");
        } else {
            if (cbobancoprincipal.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione Banco.");
                cbobancoprincipal.requestFocus();
            } else {
                if (cbocuentaprincipal.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(this, "Seleccione Cuenta.");
                    cbocuentaprincipal.requestFocus();
                } else {
                    count = 1;
                }
            }
        }
        if (count == 1) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean validardetalle(String banco, String moneda){
        proveedoressincuenta="Los siguientes proveedores no tienen cuenta Bancaria: \n";
        LProgPagosCabecera lp = new LProgPagosCabecera();
        lstdetalleprog.removeAll(lstdetalleprog);
        for (int i = 0; i < liscom2.size(); i++) {
            if (lp.validarctabancaria(banco, moneda, liscom2.get(i).getDocProveedor()) == 1) {
                EDetProgramPagos edet = new EDetProgramPagos();
                ArrayList<ECuentaBancaria> lstbanc=new ArrayList<>();
                lstbanc=lp.CtaBancaria(liscom2.get(i).getDocProveedor(), moneda);
                edet.setCodcompr(liscom2.get(i).getCodComprobante());
                edet.setEstado(liscom2.get(i).getEstado());
                edet.setBanco(lstbanc.get(0).getNomBanco());
                edet.setMoneda(moneda);
                if(banco.equalsIgnoreCase(lstbanc.get(0).getNomBanco())){
                    edet.setCtaBancaria(lstbanc.get(0).getCuenta());
                }else{
                    edet.setCtaBancaria(lstbanc.get(0).getCuentaci());
                }
                lstdetalleprog.add(edet);
            }else{
                proveedoressincuenta=proveedoressincuenta+" "+liscom2.get(i).getRazonSocial()+ ", \n";
            }
        }
        System.out.println(proveedoressincuenta.substring(0, proveedoressincuenta.length()-2)+".");
        if (lstdetalleprog.isEmpty()) {
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

        bgfiltro = new javax.swing.ButtonGroup();
        bgsod = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        lbltitulo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        dcfechainicio = new com.toedter.calendar.JDateChooser()

        ;
        dcfechafin = new com.toedter.calendar.JDateChooser()

        ;
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rbfecha = new javax.swing.JRadioButton();
        rbproveedor = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        btnlimpiar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        txtpro = new javax.swing.JTextField();
        btnproveedor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cboformapago = new javax.swing.JComboBox();
        rbtsoles = new javax.swing.JRadioButton();
        rbtdolares = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblistacomprobante = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnroregistros = new javax.swing.JLabel();
        btnQuitar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbListaSeleccion = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        lblnumseleccionados = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbocuentaprincipal = new javax.swing.JComboBox();
        cbobancoprincipal = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblnumcomprobante = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(847, 579));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(514, 20));

        lbltitulo.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        lbltitulo.setForeground(new java.awt.Color(255, 255, 255));
        lbltitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbltitulo.setText("MODIFICAR PROGRAMACION DE PAGOS");
        lbltitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 835, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lbltitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(lbltitulo)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 835, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 70, 30));

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel10.setText("Proveedor");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, -1, 20));

        dcfechainicio.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcfechainicio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcfechainicioKeyTyped(evt);
            }
        });
        jPanel1.add(dcfechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, -1, -1));

        dcfechafin.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        dcfechafin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dcfechafinKeyTyped(evt);
            }
        });
        jPanel1.add(dcfechafin, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel9.setText("Fec Fin");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, 20));

        jLabel8.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel8.setText("Fec. Ini");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, 20));

        rbfecha.setBackground(new java.awt.Color(255, 255, 255));
        rbfecha.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbfecha.setText("Por Fecha");
        rbfecha.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbfechaItemStateChanged(evt);
            }
        });
        rbfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbfechaActionPerformed(evt);
            }
        });
        jPanel1.add(rbfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 10, 80, 18));

        rbproveedor.setBackground(new java.awt.Color(255, 255, 255));
        rbproveedor.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbproveedor.setText("Por Proveedor");
        rbproveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rbproveedorItemStateChanged(evt);
            }
        });
        rbproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbproveedorActionPerformed(evt);
            }
        });
        jPanel1.add(rbproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 30, 100, 18));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, 120, 50));

        btnlimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buttonnuevo.png"))); // NOI18N
        btnlimpiar.setEnabled(false);
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 70, 30));

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/salir.png"))); // NOI18N
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 70, 30));

        btnbuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/buscar.png"))); // NOI18N
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        jPanel1.add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 70, 30));

        txtpro.setEnabled(false);
        jPanel1.add(txtpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 160, -1));

        btnproveedor.setText("jButton1");
        btnproveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproveedorActionPerformed(evt);
            }
        });
        jPanel1.add(btnproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 30, 20));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel2.setText("Forma de Pago");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 30, -1, -1));

        jPanel1.add(cboformapago, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 160, -1));

        rbtsoles.setBackground(new java.awt.Color(255, 255, 255));
        bgsod.add(rbtsoles);
        rbtsoles.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbtsoles.setText("Soles");
        jPanel1.add(rbtsoles, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, -1, -1));

        rbtdolares.setBackground(new java.awt.Color(255, 255, 255));
        bgsod.add(rbtdolares);
        rbtdolares.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        rbtdolares.setText("Dolares");
        jPanel1.add(rbtdolares, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 30, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 26, 813, 83));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(840, 330));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setBackground(new java.awt.Color(255, 204, 0));
        jLabel4.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("COMPROBANTES");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 18));

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
        tblistacomprobante.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblistacomprobanteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblistacomprobante);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 18, 800, 121));

        lblnroregistros.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnroregistros.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblnroregistros.setText("0 Proveedores");
        jPanel4.add(lblnroregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 190, 210, -1));

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 800, 140));

        btnQuitar.setBackground(new java.awt.Color(255, 255, 255));
        btnQuitar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arriba.png"))); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        jPanel6.add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 155, 37, 30));

        btnAgregar.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/abajo.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel6.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 155, 37, 30));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBackground(new java.awt.Color(153, 153, 153));

        jLabel5.setBackground(new java.awt.Color(255, 204, 0));
        jLabel5.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("COMPROBANTES SELECCIONADOS");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 20));

        tbListaSeleccion.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        tbListaSeleccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbListaSeleccion);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 20, 798, 140));

        jPanel6.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 195, 800, 160));

        lblnumseleccionados.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnumseleccionados.setText("0 Seleccionado(s)");
        jPanel6.add(lblnumseleccionados, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 170, 110, 20));

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel6.setText("Cuenta");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 165, -1, -1));

        cbocuentaprincipal.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbocuentaprincipal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        jPanel6.add(cbocuentaprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 163, 160, -1));

        cbobancoprincipal.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cbobancoprincipal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        cbobancoprincipal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbobancoprincipalItemStateChanged(evt);
            }
        });
        jPanel6.add(cbobancoprincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 163, 180, -1));

        jLabel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10)), "Seleccione Cuenta Origen", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 10))); // NOI18N
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 490, 38));

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        jLabel7.setText("Banco");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 165, -1, -1));

        jTabbedPane1.addTab("Seleccion de Comprobantes", jPanel6);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, 400));

        lblnumcomprobante.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        lblnumcomprobante.setText("0 Comprobante(s)");
        getContentPane().add(lblnumcomprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 126, 110, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        EProgPagosCabecera objtc = new EProgPagosCabecera();
        EComprobante o = new EComprobante();
        if (validarcabecera()) {
            switch (cbobancoprincipal.getSelectedIndex()) {
                case 1:
                    objtc.setBanco("BCP");
                    break;
                case 2:
                    objtc.setBanco("Interbank");
                    break;
                default:
                    throw new AssertionError();
            }
            switch (cbocuentaprincipal.getSelectedIndex()) {
                case 1:
                    objtc.setMoneda("SOLES");
                    break;
                case 2:
                    objtc.setMoneda("DOLARES");
                    break;
                default:
                    throw new AssertionError();
            }
            int fila = 0;
            tipestado = (tbListaSeleccion.getValueAt(fila, 4)).toString().trim();
            String var1 = "PENDIENTE";
            objtc.setEstado_progpagos(var1);
            objtc.setUsucrea(SesionUsuario.misesion.getUsuario());
            objtc.setUsuModi(SesionUsuario.misesion.getUsuario());
            objtc.setCod_programacion(codigomax);
            LProgPagosCabecera ltc = new LProgPagosCabecera();
            if (validardetalle(objtc.getBanco(), objtc.getMoneda())) {
                if (lstdetalleprog.size() == liscom2.size()) {
                    String docProveedor ="";
                    for (int i = 0; i < liscom2.size(); i++) {
                             docProveedor += liscom2.get(i).getDocProveedor().toString() +",";
                    }
                    ArrayList<ECuentaBancaria> licue = ltc.validarCuentaBancaria(docProveedor, objtc.getBanco(), objtc.getMoneda());
                    if (licue.size() > 0){
                    String mensage="Los Siguientes Proveedores no tienen Cuenta bancaria: \n";
                    for (int i = 0; i < licue.size(); i++) {
                             mensage += licue.get(i).getnomProveedor().toString() +" no tiene "+ licue.get(i).getDescripcion().toString() + ". \n";
                    }
                    mensage+="\n Añadir las cuentas respectivas a los provedores mencionados.";
                    JOptionPane.showMessageDialog(this, mensage);
                    }else{
                    if (JOptionPane.showConfirmDialog(this, "Se va MODIFICAR la Programación de Pagos. ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        if (ltc.LimpiarDetConsolidado(codigomax)) {
                            cambiarestado();
                            if (ltc.ActualizarPrgPagos(objtc)) {
                                for (int i = 0; i < lstdetalleprog.size(); i++) {
                                    ltc.insertarProgDetalle(lstdetalleprog.get(i), codigomax);
                                }
                                lstconsolidado = lstconsolidadofinal(ltc.lstConsolidado(codigomax));
                                for (int i = 0; i < lstconsolidado.size(); i++) {
                                    ltc.insertarConsolidado(lstconsolidado.get(i));
                                }
                                limpiar();
                                JOptionPane.showMessageDialog(this, "Se ACTUALIZÓ correctamente la Programación N° " + codigomax + ".");
                                dispose();
                            }
                        }
                        
                    }
                    }
                } else {
                    System.out.print("pasosososososo");
                    if (JOptionPane.showConfirmDialog(this, proveedoressincuenta.substring(0, proveedoressincuenta.length()-2)+"\n Grabar sólo los comprobantes(proveedores) que tienen cuenta bancaria.?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
//                        if (ltc.InsertarPrgPagos(objtc)) {
//                            MaxCodigo();
//                            for (int i = 0; i < lstdetalleprog.size(); i++) {
//                                ltc.insertarProgDetalle(lstdetalleprog.get(i), codigomax);
//                            }
//                            lstconsolidado = lstconsolidadofinal(ltc.lstConsolidado(codigomax));
//                            for (int i = 0; i < lstconsolidado.size(); i++) {
//                                ltc.insertarConsolidado(lstconsolidado.get(i));
//                            }
//                            limpiar();
//                            JOptionPane.showMessageDialog(this, "Se ACTUALIZÓ correctamente la Programación N° "+codigomax+".");
//                            dispose();
//                        }
                        if (ltc.LimpiarDetConsolidado(codigomax)) {
                            cambiarestado();
                            if (ltc.ActualizarPrgPagos(objtc)) {
                                for (int i = 0; i < lstdetalleprog.size(); i++) {
                                    ltc.insertarProgDetalle(lstdetalleprog.get(i), codigomax);
                                }
                                lstconsolidado = lstconsolidadofinal(ltc.lstConsolidado(codigomax));
                                for (int i = 0; i < lstconsolidado.size(); i++) {
                                    ltc.insertarConsolidado(lstconsolidado.get(i));
                                }
                                limpiar();
                                JOptionPane.showMessageDialog(this, "Se ACTUALIZÓ correctamente la Programación N° " + codigomax + ".");
                                dispose();
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ningún proveedor(comprobante) seleccionado tiene cuenta bancaria.");
            }
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void dcfechainicioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcfechainicioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcfechainicioKeyTyped

    private void dcfechafinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dcfechafinKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_dcfechafinKeyTyped

    private void rbfechaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbfechaItemStateChanged
        
    }//GEN-LAST:event_rbfechaItemStateChanged

    private void rbproveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rbproveedorItemStateChanged
       
    }//GEN-LAST:event_rbproveedorItemStateChanged

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        opc = 1;
        if (rbfecha.isSelected() == false && rbproveedor.isSelected() == false) {
            JOptionPane.showMessageDialog(this, "Seleccione Filtro que desea buscar.");
            rbfecha.requestFocus();
            return;
        }
        // FILTRO POR FECHA       
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
                        liscom.removeAll(liscom);
                        listacomprobante();
                    } else {
                        if (cboformapago.getSelectedIndex() == 0) {
                            LConsultaComprobante lo = new LConsultaComprobante();
                            liscom = lo.ListaxFecha(fechaformato(dcfechainicio.getDate()), fechaformato(dcfechafin.getDate()));
                            if (liscom.size() > 0) {
                                listacomprobante();
                            }
                        } else {
                            LConsultaComprobante lo = new LConsultaComprobante();
                            liscom = lo.ListaxFechayForma(fechaformato(dcfechainicio.getDate()), fechaformato(dcfechafin.getDate()), cboformapago.getSelectedItem().toString());
                            if (liscom.size() > 0) {
                                listacomprobante();
                            } else {
                                JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                                listacomprobante();
                                cboformapago.requestFocus();
                            }

                        }

                    }
                }
            }
     }   
           
           
 // FILTRO POR PROVEEDOR
         if (rbproveedor.isSelected()) {
            if (txtpro.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(this, "Ingrese Nombre que desea buscar.");
                txtpro.requestFocus();
            } else {
                if (cboformapago.getSelectedIndex() == 0) {
                    LConsultaComprobante lo = new LConsultaComprobante();
                    liscom = lo.ListaxRazonSocial(txtpro.getText());

                    if (liscom.size() > 0) {

                        listacomprobante();
                    }
                } else {
                    LConsultaComprobante lo = new LConsultaComprobante();
                    liscom = lo.ListaxRazonyForma(txtpro.getText(), cboformapago.getSelectedItem().toString());

                    if (liscom.size() > 0) {

                        listacomprobante();
                    } else {
                        JOptionPane.showMessageDialog(this, "No se encontraron registros.");
                        listacomprobante();
                        txtpro.requestFocus();
                    }
                }
            }
        }

    }//GEN-LAST:event_btnbuscarActionPerformed

    private void tblistacomprobanteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblistacomprobanteMouseClicked
       
    }//GEN-LAST:event_tblistacomprobanteMouseClicked

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
       int fila6=tbListaSeleccion.getSelectedRow();
        if(fila6>=0){
            liscom2.remove(fila6);
//            for (int i = lsttab2.size()-1; i>=0 ; i--) {
//                if( lsttab2.get(i).getCodProgramacion()== Integer.valueOf(tbListaSeleccion.getValueAt(fila6, 0).toString())
//                        && 
//                        lsttab2.get(i).getSede().equalsIgnoreCase(tbListaSeleccion.getValueAt(fila6, 5).toString())){
//                    lsttab2.remove(i);
//                }
//            }
//            llenartab2();
            llenartabseleccion();
//            for (int i = lstdetabast.size()-1; i >=0 ; i--) {
//                int cant=0;
//                for (int j = lsttab2.size()-1; j >=0; j--) {
//                    if(lstdetabast.get(i).getCodItem()==lsttab2.get(j).getCodItem()){
//                        cant=cant+lsttab2.get(j).getCantidad();
//                    }
//                }
//                lstdetabast.get(i).setCantidad(cant);
//                if(lstdetabast.get(i).getCantidad()==0){
//                    lstdetabast.remove(i);
//                }
//            }
//            llenartabitems();
        }else{
            JOptionPane.showMessageDialog(this,"Seleccione Fila que desea Quitar.");
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       // TODO add your handling code here:
        try{
            
        int fila = tblistacomprobante.getSelectedRow();
        int[] filas = tblistacomprobante.getSelectedRows();
        int rep = 0;
        if (filas.length > 1) {
            for (int i = 0; i < filas.length; i++) {
                rep = 0;
                for (int j = 0; j < liscom2.size(); j++) {
                    if (liscom2.get(j).getNumComprobante().equals(tblistacomprobante.getValueAt(filas[j], 0).toString())) {
                        JOptionPane.showMessageDialog(this, "Ya se encuentra en la lista seleccione uno diferente.");
                        rep = 1;
                    }
                }
                if (rep == 0) {
                    EComprobante atencion = new EComprobante();
                    atencion.setCodComprobante(Integer.parseInt(tblistacomprobante.getValueAt(fila, 0).toString()));
                    atencion.setTipoComprobante(tblistacomprobante.getValueAt(fila, 1).toString());
                    atencion.setNumComprobante(tblistacomprobante.getValueAt(fila, 2).toString());
                    atencion.setFechaComprobante(tblistacomprobante.getValueAt(fila, 3).toString());
                    atencion.setEstado(tblistacomprobante.getValueAt(fila, 4).toString());
                    atencion.setRazonSocial(tblistacomprobante.getValueAt(fila, 5).toString());
                    atencion.setDocProveedor(tblistacomprobante.getValueAt(fila, 6).toString());
                    atencion.setGlosaComprante(tblistacomprobante.getValueAt(fila, 7).toString());
                    atencion.setComprobanteSujeto(tblistacomprobante.getValueAt(fila, 8).toString());
                    atencion.setTipoDetra(tblistacomprobante.getValueAt(fila, 9).toString());
                    atencion.setFormaPago(tblistacomprobante.getValueAt(fila, 10).toString());
                    atencion.setDestinoPago(tblistacomprobante.getValueAt(fila, 11).toString());
                    atencion.setNomMoneda(tblistacomprobante.getValueAt(fila, 12).toString());
                     atencion.setTCComprobante(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 13).toString().replace(",", "")));
                        atencion.setMontoBase(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 14).toString().replace(",", "")));
                        atencion.setMontoOtros(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 15).toString().replace(",", "")));
                        atencion.setMontoIGV(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 16).toString().replace(",", "")));
                        atencion.setMontoTotal(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 17).toString().replace(",", "")));
                        atencion.setMontoRetDet(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 18).toString().replace(",", "")));
                        atencion.setMontoPagar(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 18).toString().replace(",", "")));
                        atencion.setMontoBaseDOL(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 20).toString().replace(",", "")));
                        atencion.setMontoOtrosDOL(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 21).toString().replace(",", "")));
                        atencion.setMontoIGVDOL(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 22).toString().replace(",", "")));
                        atencion.setMontoTotalDOL(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 23).toString().replace(",", "")));
                        atencion.setMontoRetDetDOL(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 24).toString().replace(",", "")));
                        atencion.setMontoPagarDOL(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 25).toString().replace(",", "")));
                        atencion.setPorcentaje(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 26).toString().replace(",", "")));
                        atencion.setPorcIGV(Double.parseDouble(tblistacomprobante.getValueAt(filas[i], 27).toString().replace(",", "")));
                    atencion.setComprobanteRef(tblistacomprobante.getValueAt(fila, 28).toString());
                    atencion.setRazonSocialRef(tblistacomprobante.getValueAt(fila, 29).toString());
                    atencion.setFechaRef(tblistacomprobante.getValueAt(fila, 30).toString());
                    liscom2.add(atencion);
                }
            }
            llenartabseleccion();
            System.out.println(tbListaSeleccion);
        } else {
            if (fila >= 0) {
                System.out.println("ENTRO FILA");
                for (int i = 0; i < liscom2.size(); i++) {
                    if (liscom2.get(i).getCodComprobante() == Integer.parseInt(tblistacomprobante.getValueAt(fila, 0).toString())) {
                        JOptionPane.showMessageDialog(this, "Ya se encuentra en la lista seleccione uno diferente.");
                        rep = 1;
                    }
                }
                if (rep == 0) {
                    if (aten.equals("P")) {
                        EComprobante atencion = new EComprobante();
                        atencion.setCodComprobante(Integer.parseInt(tblistacomprobante.getValueAt(fila, 0).toString()));
                        atencion.setTipoComprobante(tblistacomprobante.getValueAt(fila, 1).toString());
                        atencion.setNumComprobante(tblistacomprobante.getValueAt(fila, 2).toString());
                        atencion.setFechaComprobante(tblistacomprobante.getValueAt(fila, 3).toString());
                        atencion.setEstado(tblistacomprobante.getValueAt(fila, 4).toString());
                        atencion.setRazonSocial(tblistacomprobante.getValueAt(fila, 5).toString());
                        atencion.setDocProveedor(tblistacomprobante.getValueAt(fila, 6).toString());
                        atencion.setGlosaComprante(tblistacomprobante.getValueAt(fila, 7).toString());
                        atencion.setComprobanteSujeto(tblistacomprobante.getValueAt(fila, 8).toString());
                        atencion.setTipoDetra(tblistacomprobante.getValueAt(fila, 9).toString());
                        atencion.setFormaPago(tblistacomprobante.getValueAt(fila, 10).toString());
                        atencion.setDestinoPago(tblistacomprobante.getValueAt(fila, 11).toString());
                        atencion.setNomMoneda(tblistacomprobante.getValueAt(fila, 12).toString());
                        atencion.setTCComprobante(Double.parseDouble(tblistacomprobante.getValueAt(fila, 13).toString().replace(",", "")));
                        atencion.setMontoBase(Double.parseDouble(tblistacomprobante.getValueAt(fila, 14).toString().replace(",", "")));
                        atencion.setMontoOtros(Double.parseDouble(tblistacomprobante.getValueAt(fila, 15).toString().replace(",", "")));
                        atencion.setMontoIGV(Double.parseDouble(tblistacomprobante.getValueAt(fila, 16).toString().replace(",", "")));
                        atencion.setMontoTotal(Double.parseDouble(tblistacomprobante.getValueAt(fila, 17).toString().replace(",", "")));
                        atencion.setMontoRetDet(Double.parseDouble(tblistacomprobante.getValueAt(fila, 18).toString().replace(",", "")));
                        atencion.setMontoPagar(Double.parseDouble(tblistacomprobante.getValueAt(fila, 18).toString().replace(",", "")));
                        atencion.setMontoBaseDOL(Double.parseDouble(tblistacomprobante.getValueAt(fila, 20).toString().replace(",", "")));
                        atencion.setMontoOtrosDOL(Double.parseDouble(tblistacomprobante.getValueAt(fila, 21).toString().replace(",", "")));
                        atencion.setMontoIGVDOL(Double.parseDouble(tblistacomprobante.getValueAt(fila, 22).toString().replace(",", "")));
                        atencion.setMontoTotalDOL(Double.parseDouble(tblistacomprobante.getValueAt(fila, 23).toString().replace(",", "")));
                        atencion.setMontoRetDetDOL(Double.parseDouble(tblistacomprobante.getValueAt(fila, 24).toString().replace(",", "")));
                        atencion.setMontoPagarDOL(Double.parseDouble(tblistacomprobante.getValueAt(fila, 25).toString().replace(",", "")));
                        atencion.setPorcentaje(Double.parseDouble(tblistacomprobante.getValueAt(fila, 26).toString().replace(",", "")));
                        atencion.setPorcIGV(Double.parseDouble(tblistacomprobante.getValueAt(fila, 27).toString().replace(",", "")));
                        atencion.setComprobanteRef(tblistacomprobante.getValueAt(fila, 28).toString());
                        atencion.setRazonSocialRef(tblistacomprobante.getValueAt(fila, 29).toString());
                        atencion.setFechaRef(tblistacomprobante.getValueAt(fila, 30).toString());
                        liscom2.add(atencion);
                    }
                    llenartabseleccion();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione Fila que desea Agregar.");
            }
        }
        }catch(Exception e){
         JOptionPane.showMessageDialog(this, e.getMessage());
        }
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        
       
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void rbfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbfechaActionPerformed
        rbfecha.setSelected(true);
        rbproveedor.setSelected(false);
        btnproveedor.setEnabled(false);
        dcfechainicio.setEnabled(true);
        dcfechafin.setEnabled(true);
        txtpro.setText("");
    }//GEN-LAST:event_rbfechaActionPerformed

    private void rbproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbproveedorActionPerformed
        rbfecha.setSelected(false);
        rbproveedor.setSelected(true);
        btnproveedor.setEnabled(true);
        dcfechainicio.setEnabled(false);
        dcfechafin.setEnabled(false);
    }//GEN-LAST:event_rbproveedorActionPerformed

    private void btnproveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproveedorActionPerformed
        ConsultaProveedor conpro = new ConsultaProveedor();
        Menu.dpane2.add(conpro);
        centerJIF(conpro);
        conpro.setVisible(true);
        conpro.otrofrm=1;
        SesionUsuario.txtrazonsocial = txtpro;
    }//GEN-LAST:event_btnproveedorActionPerformed

    private void cbobancoprincipalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbobancoprincipalItemStateChanged
        // TODO add your handling code here:
        llenarcuentaprincipal();
    }//GEN-LAST:event_cbobancoprincipalItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgfiltro;
    private javax.swing.ButtonGroup bgsod;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnlimpiar;
    private javax.swing.JButton btnproveedor;
    private javax.swing.JButton btnsalir;
    public static javax.swing.JComboBox cbobancoprincipal;
    public static javax.swing.JComboBox cbocuentaprincipal;
    private javax.swing.JComboBox cboformapago;
    private com.toedter.calendar.JDateChooser dcfechafin;
    private com.toedter.calendar.JDateChooser dcfechainicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblnroregistros;
    private javax.swing.JLabel lblnumcomprobante;
    private javax.swing.JLabel lblnumseleccionados;
    public javax.swing.JLabel lbltitulo;
    private javax.swing.JRadioButton rbfecha;
    private javax.swing.JRadioButton rbproveedor;
    private javax.swing.JRadioButton rbtdolares;
    private javax.swing.JRadioButton rbtsoles;
    private javax.swing.JTable tbListaSeleccion;
    private javax.swing.JTable tblistacomprobante;
    private javax.swing.JTextField txtpro;
    // End of variables declaration//GEN-END:variables
}
