/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Formatear;

import Capa_Entidades.EDataCompra;
import Capa_Entidades.EDataFormatCompras;
import Capa_Entidades.EPeriodo;
import Capa_Logica.LImportar;
import Capa_Logica.RenderDerecha;
import Capa_Panel.Exportar.FrmExportarLFormatear;
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
public class InterFrmFormatearCompras extends javax.swing.JInternalFrame {
    ArrayList<EPeriodo> lstperiodo=new ArrayList<>();
    ArrayList<EDataCompra> lstcompra=new ArrayList<>();
    ArrayList<EDataFormatCompras> lstformat=new ArrayList<>();
    ArrayList<EDataFormatCompras> lstformatcorrecto=new ArrayList<>();
    ArrayList<EDataFormatCompras> lstformatincorrecto=new ArrayList<>();
    String titulos[] = {"Nro Correlativo", "Fecha de Emisión", "Fecha de Vencimiento", "Tipo de Comprobante de Pago o Doc",
        "Serie Codigo de Dependencia Aduanera", "Año de Emisión de DUA o DSI", "Documentos Emitidos por Sunat para",
        "Tipo Doc. Identidad Prov.", "Nro. Doc. Identidad Prov.", "Razon Social Proveedor", "Adquisicion Gravadas Base Imponible",
        "Adquisicion Gravadas IGV", "Gravadas y No Gravadas Base Imponible", "Gravadas y No Gravadas IGV", "No Gravadas Base Imponible",
        "No Gravadas IGV", "Adquisiciones No Gravadas", "ISC", "Otros Tributos y Cargos", "Importe Total", "Nro Comprobante No Domiciliado",
        "Nro Constancia de Detracción", "Fecha de Emisión de Constancia de Detracción", "Tipo de Cambio",
        "Comprobante que se Modifica Fecha", "Comprobante que se Modifica Tipo", "Comprobante que se Modifica Serie",
        "Nro de Comprobante de Pago o Doc"};
    DefaultTableModel modTbdatos = new DefaultTableModel(titulos, 0);
    String titulos2[] = {"1-Periodo", "2-CUO", "3-MovCUO", "4-FechaEmisión", "5-FechaVencimiento", "6-TipoComprob", "7-SerieComprob",
        "8-AñoDUA", "9-NroComprob", "10-MontoConsolOPsinIGV", "11-TipoDocIden", "12-NroDoc", "13-NomRazonSocial", "14-BaseImpGrav", "15-MontoIGV",
        "16-BaseImpAdqGrav", "17-MontoIGV", "18-BaseImpAdqNoGrav", "19-MontoIGVNG", "20-ValorAdqNG", "21-MontoISC", "22-OtrosMontos", "23-ImporteTotalAdq",
        "24-TipoCambio", "25-FechaEmiComprobRef", "26-TipoComprobRef", "27-SerieComprobRef", "28-CodDUA", "29-NroComprobRef",
        "30-NroComprobNoDomicil", "31-FechaConstDetracc", "32-NroConstDetracc", "33-MarcaComprobSujReten", "34-EstadoOperación",
    "35","36","37","38","39","40","41"};
    DefaultTableModel modTbdatos2 = new DefaultTableModel(titulos2, 0);
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    /**
     * Creates new form InterFrmFormatearCompras
     */
    public InterFrmFormatearCompras() {
        initComponents();
        this.setTitle("Formatear Registro de Compras");
        setFrameIcon(new ImageIcon(getClass().getResource("/img/Santander.png")));
        llenarperiodo();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');
        tblstdata.setModel(modTbdatos);
        tblstformato.setModel(modTbdatos2);
        tblstdata.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstformato.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblstformato.getTableHeader().setReorderingAllowed(false);
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
        tblstformato.getColumnModel().getColumn(23).setCellRenderer(new RenderDerecha());
        btnver.setVisible(false);
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
    
    public int diferenciaenmeses(String Fecha){
        int añofecha=Integer.parseInt(Fecha.substring(6, 10));
        int mesfecha=Integer.parseInt(Fecha.substring(3, 5));
        int añoperiodo=lstperiodo.get(cboperiodo.getSelectedIndex()-1).getAño();
        int mesperiodo=lstperiodo.get(cboperiodo.getSelectedIndex()-1).getMes();
        int resultado=-1;
        if (añofecha == añoperiodo) {
            resultado = mesperiodo - mesfecha;
        } else {
            resultado = (mesperiodo + 12) - mesfecha;
        }
        return resultado;
    }
    
    public int diferenciaenmeses2(String Fecha){
        int añofecha=Integer.parseInt(Fecha.substring(6, 10));
        int mesfecha=Integer.parseInt(Fecha.substring(3, 5));
        int añoperiodo=lstperiodo.get(cboperiodo.getSelectedIndex()-1).getAño();
        int mesperiodo=lstperiodo.get(cboperiodo.getSelectedIndex()-1).getMes()+1;
        int resultado=-1;
        if (añofecha == añoperiodo) {
            resultado = mesperiodo - mesfecha;
        } else {
            resultado = (mesperiodo + 12) - mesfecha;
        }
        return resultado;
    }
    
    private boolean validarPagoqueseModifica(String campo6){
        int val=0;
        if(campo6.equalsIgnoreCase("07") || campo6.equalsIgnoreCase("08") || campo6.equalsIgnoreCase("87")
                || campo6.equalsIgnoreCase("88") || campo6.equalsIgnoreCase("97") || campo6.equalsIgnoreCase("98")){
            val=1;
        }
        if(val==1){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean validartipodocumento(String campo6){
        if(campo6.equalsIgnoreCase("00") || campo6.equalsIgnoreCase("03") || campo6.equalsIgnoreCase("05") ||
           campo6.equalsIgnoreCase("06") || campo6.equalsIgnoreCase("07") || campo6.equalsIgnoreCase("08") || 
           campo6.equalsIgnoreCase("11") || campo6.equalsIgnoreCase("12") || campo6.equalsIgnoreCase("13") ||  
           campo6.equalsIgnoreCase("14") || campo6.equalsIgnoreCase("15") || campo6.equalsIgnoreCase("16") ||
           campo6.equalsIgnoreCase("18") || campo6.equalsIgnoreCase("19") || campo6.equalsIgnoreCase("22") ||
           campo6.equalsIgnoreCase("23") || campo6.equalsIgnoreCase("26") || campo6.equalsIgnoreCase("28") ||
           campo6.equalsIgnoreCase("30") || campo6.equalsIgnoreCase("34") || campo6.equalsIgnoreCase("36") ||
           campo6.equalsIgnoreCase("37") || campo6.equalsIgnoreCase("55") || campo6.equalsIgnoreCase("87") ||
           campo6.equalsIgnoreCase("88") || campo6.equalsIgnoreCase("01") || campo6.equalsIgnoreCase("02") ||
           campo6.equalsIgnoreCase("04") || campo6.equalsIgnoreCase("09") || campo6.equalsIgnoreCase("10") ||
           campo6.equalsIgnoreCase("17") || campo6.equalsIgnoreCase("20") || campo6.equalsIgnoreCase("21") ||
           campo6.equalsIgnoreCase("24") || campo6.equalsIgnoreCase("25") || campo6.equalsIgnoreCase("27") || 
           campo6.equalsIgnoreCase("29") || campo6.equalsIgnoreCase("31") || campo6.equalsIgnoreCase("32") ||
           campo6.equalsIgnoreCase("35") || campo6.equalsIgnoreCase("50") || campo6.equalsIgnoreCase("52") ||
           campo6.equalsIgnoreCase("53") || campo6.equalsIgnoreCase("54") || campo6.equalsIgnoreCase("96") ||
           campo6.equalsIgnoreCase("99")){
            return true;
        }else{
            return false;
        }
    }
    
    private boolean validarcampo11(String campo6){
        if(campo6.equalsIgnoreCase("00") || campo6.equalsIgnoreCase("03") || campo6.equalsIgnoreCase("05") ||
           campo6.equalsIgnoreCase("06") || campo6.equalsIgnoreCase("07") || campo6.equalsIgnoreCase("08") || 
           campo6.equalsIgnoreCase("11") || campo6.equalsIgnoreCase("12") || campo6.equalsIgnoreCase("13") ||  
           campo6.equalsIgnoreCase("14") || campo6.equalsIgnoreCase("15") || campo6.equalsIgnoreCase("16") ||
           campo6.equalsIgnoreCase("18") || campo6.equalsIgnoreCase("19") || campo6.equalsIgnoreCase("22") ||
           campo6.equalsIgnoreCase("23") || campo6.equalsIgnoreCase("26") || campo6.equalsIgnoreCase("28") ||
           campo6.equalsIgnoreCase("30") || campo6.equalsIgnoreCase("34") || campo6.equalsIgnoreCase("36") ||
           campo6.equalsIgnoreCase("37") || campo6.equalsIgnoreCase("55") || campo6.equalsIgnoreCase("87") ||
                campo6.equalsIgnoreCase("88") || campo6.equalsIgnoreCase("91") || campo6.equalsIgnoreCase("97") ||
                campo6.equalsIgnoreCase("98")){
         return true;   
        }else{
            return false;
        }
    }
   
    private void formateardato(EDataCompra data) {
        String periodo = "";
        int val=0;
        EDataFormatCompras dataformato = new EDataFormatCompras();
        periodo=lstperiodo.get(cboperiodo.getSelectedIndex()-1).getAño()+lstperiodo.get(cboperiodo.getSelectedIndex()-1).getMesChar()+"00";
//        if(data.getFechaEmision().trim().length()==10){
//            periodo = data.getFechaEmision().substring(6, 10) + data.getFechaEmision().substring(3, 5) + "00";
//        }else{
//            if(data.getFechaEmision().trim().length()==8){
//                periodo = "20"+data.getFechaEmision().substring(6, 8) + data.getFechaEmision().substring(3, 5) + "00";
//            }else{
//                dataformato.setCampoOtros("ERROR: Campo 4");
//            }
//        }
        dataformato.setCampo1(periodo);
        dataformato.setCampo2(data.getFila()+"");
        dataformato.setCampo3("M"+data.getFila());
        dataformato.setCampo4(data.getFechaEmision().replace("-", "/"));
        int dif=diferenciaenmeses(data.getFechaEmision().trim());
        if (data.getTipoComprobantePago().trim().length() == 0) {
            dataformato.setCampoOtros("ERROR: Campo 6");
            val = val + 1;
        }
        dataformato.setCampo6(data.getTipoComprobantePago());
        if (!validartipodocumento(dataformato.getCampo6())) {
            dataformato.setCampoOtros("ERROR: Campo 6");
            val = val + 1;
        }
        if (dataformato.getCampo6().equalsIgnoreCase("14")) {
            if (data.getFechaVencimiento().trim().length() == 0) {
                dataformato.setCampoOtros("ERROR: Campo 5");
                val = val + 1;
            }
        }
        dataformato.setCampo5(data.getFechaVencimiento().replace("-", "/"));
        dataformato.setCampo7(data.getSerieCodDepenAduanera());
        if (data.getSerieCodDepenAduanera().trim().length() == 0) {
            dataformato.setCampo7(data.getDocumentosEmitidos().substring(0, 1));
        }
        if (dataformato.getCampo7().trim().length() == 0) {
            val = val + 1;
            dataformato.setCampoOtros("ERROR: Campo 7");
        }
        if (dataformato.getCampo7().trim().length() == 4) {
        } else {
            String campo = dataformato.getCampo7().trim();
            if (!dataformato.getCampo6().equalsIgnoreCase("54")) {
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
        }
        if (dataformato.getCampo6().equalsIgnoreCase("50") || dataformato.getCampo6().equalsIgnoreCase("52")) {
            if (data.getAñoEmisionDua().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 8");
            } else {
                if (Integer.valueOf(data.getAñoEmisionDua()) < 1981) {
                    val = val + 1;
                    dataformato.setCampoOtros("ERROR: Campo 8");
                }
            }
        }
        dataformato.setCampo8(data.getAñoEmisionDua());
        dataformato.setCampo9(data.getDocumentosEmitidos().replace("-", "").replace("´", ""));
        if (data.getDocumentosEmitidos().trim().length() == 0) {
            val = val + 1;
            dataformato.setCampoOtros("ERROR: Campo 9");
        }
        if(dataformato.getCampo6().equalsIgnoreCase("01")){
            if(data.getDocumentosEmitidos().trim().length() > 7){
                dataformato.setCampoOtros("ERROR: Campo 9 - Longitud Maxima permitida es 7 para este tipo de dato.");
            }
        }
//        if(data.getDocumentosEmitidos().trim().indexOf("-") > 0) {
//            val=val+1;
//            dataformato.setCampoOtros("ERROR: Campo 9");
//        }
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
            dataformato.setCampo10("0");
        } else {
            dataformato.setCampo10("");
        }
        if (data.getTipoDocuIdenProv().equalsIgnoreCase("0")) {
            dataformato.setCampo11("");
        } else {
            dataformato.setCampo11(data.getTipoDocuIdenProv());
        }
        if (!validarcampo11(dataformato.getCampo6())) {
            if (dataformato.getCampo11().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 11");
            }
            if (dataformato.getCampo11().equalsIgnoreCase("0") || dataformato.getCampo11().equalsIgnoreCase("1")
                    || dataformato.getCampo11().equalsIgnoreCase("4") || dataformato.getCampo11().equalsIgnoreCase("6")
                    || dataformato.getCampo11().equalsIgnoreCase("7")) {
            } else {
                val = val + 1;
            }
        }
        dataformato.setCampo12(data.getNroDocIdentProv());
        if (!validarcampo11(dataformato.getCampo6())) {
            if (dataformato.getCampo12().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 12");
            }
        }
        dataformato.setCampo13(data.getRazonSocialProv().replace("|", ""));
        if (!validarcampo11(dataformato.getCampo6())) {
            if (dataformato.getCampo13().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 13");
            }
        }
        dataformato.setCampo14(data.getGOBaseDisponible());
        dataformato.setCampo15(data.getGOIGV());
        dataformato.setCampo16(data.getGYGBaseDisponible());
        dataformato.setCampo17(data.getGYGIGV());
        dataformato.setCampo18(data.getNGBaseDisponible());
        dataformato.setCampo19(data.getNGIGV());
        dataformato.setCampo20(data.getAdquisionesNG());
        dataformato.setCampo21(data.getISC());
        dataformato.setCampo22(data.getOtrosTribyCarg());
        dataformato.setCampo23(data.getImporteTotal());
        dataformato.setCampo24("PEN");
        dataformato.setCampo25(data.getTipoDeCambio());
        if (validarPagoqueseModifica(dataformato.getCampo6())) {
            if (data.getCMFecha().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 26");
            }
            if (data.getCMTipo().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 27");
            }
            if (data.getCMSerie().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 28");
            }
            if (data.getCMNCPagoDoc().trim().length() == 0) {
                val = val + 1;
                dataformato.setCampoOtros("ERROR: Campo 30");
            }
        }
        dataformato.setCampo26(data.getCMFecha().replace("-", "/"));
        if(dataformato.getCampo26().trim().length()==8){
            dataformato.setCampo26(dataformato.getCampo26().substring(0, 5)+"/20"+dataformato.getCampo26().substring(6,8));
        }
        dataformato.setCampo27(data.getCMTipo());
        dataformato.setCampo28(data.getCMSerie());
        dataformato.setCampo29("");
        if (dataformato.getCampo27().trim().length() > 0) {
            if (dataformato.getCampo27().trim().equalsIgnoreCase("50") || dataformato.getCampo27().trim().equalsIgnoreCase("52")) {
                if (dataformato.getCampo29().trim().length() == 0) {
                    val = val + 1;
                    dataformato.setCampoOtros("ERROR: Campo 27");
                }
            }
        }
        dataformato.setCampo30(data.getCMNCPagoDoc());
        dataformato.setCampo31(data.getFechaEmisionConsDetracc().replace("-", "/"));
        dataformato.setCampo32(data.getNroConstanciaDetraccion());
        dataformato.setCampo33("");
        dataformato.setCampo34("");
        dataformato.setCampo35("");
        dataformato.setCampo36("");
        dataformato.setCampo37("");
        dataformato.setCampo38("");
        dataformato.setCampo39("");
        dataformato.setCampo40("0");
        if (dif == 0) {
            dataformato.setCampo41("1");
        } else {
            if (dif > 0 && dif < 13) {
                dataformato.setCampo41("6");
            } else {
                dataformato.setCampo41("7");
            }
        }
        if (data.getTipoComprobantePago().equalsIgnoreCase("03")) {
            dataformato.setCampo41("0");
        }
        lstformat.add(dataformato);
        if (val == 0) {
            lstformatcorrecto.add(dataformato);
        } else {
            lstformatincorrecto.add(dataformato);
        }
    }

    private void llenartablaformato() {
        modTbdatos2.setRowCount(0);
        if (lstformat.size() > 0) {
            for (int i = 0; i < lstformat.size(); i++) {
                Object obj[] = new Object[41];
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
                obj[34] = lstformat.get(i).getCampo35();
                obj[35] = lstformat.get(i).getCampo36();
                obj[36] = lstformat.get(i).getCampo37();
                obj[37] = lstformat.get(i).getCampo38();
                obj[38] = lstformat.get(i).getCampo39();
                obj[39] = lstformat.get(i).getCampo40();
                obj[40] = lstformat.get(i).getCampo41();
                modTbdatos2.addRow(obj);
            }
        }
        tblstformato.setModel(modTbdatos2);
        lblregistrosformat.setText(tblstformato.getRowCount() + "");
        lblerradosformat.setText(lstformatincorrecto.size() + "");
        lblcorrectosformat.setText(lstformatcorrecto.size() + "");
        if (lstformatincorrecto.size() > 0) {
            btnver.setVisible(true);
        } else {
            btnver.setVisible(false);
        }
    }

    private void formateartodo() {
        if (lstcompra.size() > 0) {
            lstformat.removeAll(lstformat);
            lstformatcorrecto.removeAll(lstformatcorrecto);
            lstformatincorrecto.removeAll(lstformatincorrecto);
            for (int i = 0; i < lstcompra.size(); i++) {
                formateardato(lstcompra.get(i));
            }
            llenartablaformato();
            JOptionPane.showMessageDialog(this, "Formato Terminado.");
        }
    }
    
    private void limpiar(){
        cboperiodo.setSelectedIndex(0);
        lstformatcorrecto.removeAll(lstformatcorrecto);
        lstformat.removeAll(lstformat);
        lstformatincorrecto.removeAll(lstformatincorrecto);
        llenartablaformato();
    }
    
    public void centerJIF(JInternalFrame jif) {
        Dimension desktopSize = InterFrmFormatearCompras.this.size();
        Dimension jInternalFrameSize = jif.getSize();
        int width = ((desktopSize.width - jInternalFrameSize.width) / 2) + 70;
        int height = ((desktopSize.height - jInternalFrameSize.height) / 2) + 20;
        jif.setLocation(width, height);
        jif.setVisible(true);
    }
   
    private void llenartabla() {
        DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
        modTbdatos.setRowCount(0);
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
                modTbdatos.addRow(obj);
            }
        }
        tblstdata.setModel(modTbdatos);
        lblregistros.setText(modTbdatos.getRowCount() + "");
    }
    
    private void exportar() {
        if (JOptionPane.showConfirmDialog(this, "¿Desea Exportar la Data?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
            FrmExportarLFormatear frmexp = new FrmExportarLFormatear();
            frmexp.exportaciondeafuera("COMPRAS", cboperiodo.getSelectedItem().toString().trim());
            frmexp.setVisible(true);
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
        cboperiodo = new javax.swing.JComboBox();
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
        lblregistros = new javax.swing.JLabel();
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
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblcorrectosformat = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        jPanel1.setBackground(new java.awt.Color(204, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FORMATEAR REGISTRO DE COMPRAS");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1000, -1));

        cboperiodo.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        cboperiodo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "<Seleccione>" }));
        cboperiodo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboperiodoItemStateChanged(evt);
            }
        });

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
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 405, 90, 30));

        jLabel9.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel9.setText(":");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 10, -1));

        lblregistros.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblregistros.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblregistros.setText("0");
        jPanel2.add(lblregistros, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 40, -1));

        jTabbedPane1.addTab("Data Importada", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel5.setText(":");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 10, -1));

        jLabel6.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel6.setText("Total Errados");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, -1, -1));

        btnver.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnver.setText("Ver");
        btnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverActionPerformed(evt);
            }
        });
        jPanel3.add(btnver, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 407, -1, -1));

        btnguardar.setFont(new java.awt.Font("Calibri", 0, 11)); // NOI18N
        btnguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/guardar.png"))); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 405, 70, 30));

        jLabel7.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel7.setText("Total Registros");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jLabel8.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel8.setText(":");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 10, -1));

        lblregistrosformat.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblregistrosformat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblregistrosformat.setText("0");
        jPanel3.add(lblregistrosformat, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 410, 40, -1));

        lblerradosformat.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblerradosformat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblerradosformat.setText("0");
        jPanel3.add(lblerradosformat, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 410, 50, -1));

        jLabel11.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel11.setText("Total Correctos");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, -1, -1));

        jLabel12.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        jLabel12.setText(":");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 10, -1));

        lblcorrectosformat.setFont(new java.awt.Font("Calibri", 1, 11)); // NOI18N
        lblcorrectosformat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblcorrectosformat.setText("0");
        jPanel3.add(lblcorrectosformat, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 410, 40, -1));

        jTabbedPane1.addTab("Formato de SUNAT", jPanel3);

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
                                .addGap(13, 13, 13)
                                .addComponent(cboperiodo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(640, 640, 640)
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
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboperiodo, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        if (lstformatcorrecto.size() > 0) {
            LImportar lim = new LImportar();
            int reemp = lim.DataAnteriorFormatoCompras(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
            if (reemp == 0) {
                if (lstformatincorrecto.size() > 0) {
                    if (JOptionPane.showConfirmDialog(this, "Tiene registros errados. Va a GRABAR solo la data formateada correcta del período " + cboperiodo.getSelectedItem().toString() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        for (int i = 0; i < lstformatcorrecto.size(); i++) {
                            lstformatcorrecto.get(i).setCodigo(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                            lim.InsertarRegCompraFormat(lstformatcorrecto.get(i));
                        }
                        JOptionPane.showMessageDialog(this, "Se grabó correctamente la data del periodo " + cboperiodo.getSelectedItem().toString() + ".");
                        exportar();
                        limpiar();
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(this, "Va a GRABAR la data formateada del período " + cboperiodo.getSelectedItem().toString() + " . ¿Desea Continuar?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                        for (int i = 0; i < lstformatcorrecto.size(); i++) {
                            lstformatcorrecto.get(i).setCodigo(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                            lim.InsertarRegCompraFormat(lstformatcorrecto.get(i));
                        }
                        JOptionPane.showMessageDialog(this, "Se grabó correctamente la data del periodo " + cboperiodo.getSelectedItem().toString() + ".");
                        exportar();
                        limpiar();
                    }
                }
            } else {
                if (JOptionPane.showConfirmDialog(this, "Ya cuenta con data registrada para este período. ¿Desea Reemplazarla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    if (lim.LimpiarRegCompraFormat(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo())) {
                        for (int i = 0; i < lstformatcorrecto.size(); i++) {
                            lstformatcorrecto.get(i).setCodigo(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                            lim.InsertarRegCompraFormat(lstformatcorrecto.get(i));
                        }
                        JOptionPane.showMessageDialog(null, "Se grabó correctamente la data del periodo " + cboperiodo.getSelectedItem().toString() + ".");
                        exportar();
                        limpiar();
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cuenta con data para grabar.");
        }
    }//GEN-LAST:event_btnguardarActionPerformed

    private void cboperiodoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboperiodoItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == 1) {
            if (cboperiodo.getSelectedIndex() >= 1) {
                LImportar lim = new LImportar();
                lstcompra = lim.ListaCompra(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                lstformat = lim.ListaDataFormatCompras(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                lstformatcorrecto = lim.ListaDataFormatCompras(lstperiodo.get(cboperiodo.getSelectedIndex() - 1).getCodigo());
                llenartabla();
                llenartablaformato();
            } else {
                lstcompra.removeAll(lstcompra);
                llenartabla();
            }
        }
    }//GEN-LAST:event_cboperiodoItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (lstcompra.size() > 0) {
            if (lstformat.size() > 0) {
                if (JOptionPane.showConfirmDialog(this, "Ya tiene data formateada para este período. ¿Desea Volver a Formatearla?", "Confirmar", JOptionPane.YES_NO_OPTION) == (JOptionPane.YES_OPTION)) {
                    formateartodo();
                }
            } else {
                formateartodo();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No cuenta con data para formatear.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverActionPerformed
        // TODO add your handling code here:
        InterFrmVerErradosFormatCompra errados = new InterFrmVerErradosFormatCompra();
        MenuLibrosElectronico.dpane2.add(errados);
        centerJIF(errados);
        errados.llenartablaformato(lstformatincorrecto);
        errados.setVisible(true);
    }//GEN-LAST:event_btnverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnver;
    private javax.swing.JComboBox cboperiodo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel lblcorrectosformat;
    private javax.swing.JLabel lblerradosformat;
    private javax.swing.JLabel lblregistros;
    private javax.swing.JLabel lblregistrosformat;
    private javax.swing.JTable tblstdata;
    private javax.swing.JTable tblstformato;
    // End of variables declaration//GEN-END:variables
}
