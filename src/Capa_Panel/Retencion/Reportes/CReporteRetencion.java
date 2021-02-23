/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Retencion.Reportes;

import Capa_Entidades.EDataRetencion;
import Capa_Logica.LImportar;
import Capa_Logica.SesionUsuario;
import java.awt.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author EXPERTYA
 */
public class CReporteRetencion {
    DecimalFormatSymbols simbolo = new DecimalFormatSymbols();
    
     public void llamarreporteReten(EDataRetencion edr) throws JRException{ 
         simbolo.setDecimalSeparator('.');
         simbolo.setGroupingSeparator(',');
         DecimalFormat formato = new DecimalFormat("###, ##0.00", simbolo);
         double totalimporte=0.0, totalpagado=0.0, importeretenido=0.0;
         String totalimp,totalpag,impor,ndoc,fechas,series,tipo;
         totalimp="";
         totalpag="";
         impor="";
         ndoc="";
         fechas="";
         series="";
         tipo="";
         HashMap hm = new HashMap();
         ArrayList<EDataRetencion> lstretencion=new ArrayList<>();
         LImportar lim=new LImportar();
         lstretencion=lim.ListaDetalle(edr.getCorrelativo());
         System.out.println(lstretencion.size());
         for (int i = 0; i < lstretencion.size(); i++) {
             totalimporte= totalimporte+Double.valueOf(lstretencion.get(i).getImporte());
             totalpagado= totalpagado+Double.valueOf(lstretencion.get(i).getSUNAT());
             importeretenido=importeretenido+Double.valueOf(lstretencion.get(i).getNeto());
             totalimp=totalimp+formato.format(Double.parseDouble(lstretencion.get(i).getImporte()))+"\n";
             totalpag=totalpag+formato.format(Double.valueOf(lstretencion.get(i).getSUNAT()))+"\n";
             impor=impor+formato.format(Double.valueOf(lstretencion.get(i).getNeto()))+"\n";
             fechas=fechas+lstretencion.get(i).getFecha()+"\n";
             int index = edr.getDoc().indexOf("-");
             ndoc=ndoc+lstretencion.get(i).getDoc().substring(index+1, lstretencion.get(i).getDoc().length())+"\n";
             series=series+lstretencion.get(i).getDoc().substring(0, index)+"\n";
             tipo=tipo+lstretencion.get(i).getTipoComprobante()+"\n";
         }
         hm.put("Tipo", tipo);
         hm.put("Serie", series);
         hm.put("Importe", totalimp);
         hm.put("Retencion", totalpag);
         hm.put("Neto", impor);
         hm.put("Codigo", edr.getCodigo());
         hm.put("FechaEmision", fechas);
         hm.put("NDocument", ndoc);
         hm.put("New", importeretenido);
         hm.put("Correlativo", edr.getCorrelativo());
         hm.put("TotalImporte",formato.format(totalimporte));
         hm.put("TotalPagado",formato.format(totalpagado));
         hm.put("ImporteRetenido",formato.format(importeretenido));
         String ruta = "C:/Sistema Libros Electronicos/Reportes/Retencion/ReporteComprobanteRetencion.jasper";
         String ruta2 = "C:/Sistema Libros Electronicos/Reportes/Retencion/ReporteComprobanteRetencion_2.jasper";
         JOptionPane.showMessageDialog(null, "Se va Generar el Reporte.");
         JasperPrint informe = JasperFillManager.fillReport(ruta, hm, SesionUsuario.con);
         JasperPrint informe2 = JasperFillManager.fillReport(ruta2, hm, SesionUsuario.con);
         SesionUsuario.llamarBarra(SesionUsuario.fr, true);
         informe.addPage((JRPrintPage) informe2.getPages().get(0));
         JasperViewer.viewReport(informe, false);
    }
}
