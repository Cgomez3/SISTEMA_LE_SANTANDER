/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Panel.Consulta.Reportes;

import Capa_Logica.SesionUsuario;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author EXPERTYA
 */
public class CLlamarReporte {
    
     public void llamarreporteProv(String Fecha1, String Fecha2) throws JRException{ 
        HashMap hm=new HashMap();
        hm.put("Usuario", SesionUsuario.misesion.getUsuario());
        hm.put("FecIni", Fecha1);
        hm.put("FecFin", Fecha2);
        hm.put("PerIni", Fecha1.subSequence(3, 10));
        hm.put("PerFin", Fecha2.subSequence(3, 10));
        String ruta  = "C:/Sistema Libros Electronicos/Reportes/Consulta/ReportConsultaCompras.jasper";
        JOptionPane.showMessageDialog(null,"Se va Generar el Reporte.");
//      JasperReport report = JasperCompileManager.compileReport(ruta);
        JasperPrint informe = JasperFillManager.fillReport(ruta,hm, SesionUsuario.con);
        SesionUsuario.llamarBarra(SesionUsuario.fr, true);
        JasperViewer.viewReport(informe, false);
    }
     
     public void LlamarReporteLibroRetenciones(int mes, int año, String tipo) throws JRException{ 
        HashMap hm=new HashMap();
        String mimes="";
        if(mes+1>9){
            mimes=""+(mes+1);
        }else{
            mimes="0"+(mes+1);
        }
        hm.put("Mes",(mes+1));
        hm.put("Año",año);
        hm.put("Periodo", mimes+"-"+año);
        hm.put(JRParameter.REPORT_LOCALE, Locale.US);
        String ruta  = "C:/Sistema Libros Electronicos/Reportes/Retencion/ReportLibroRetenciones.jasper";
        if(tipo.equalsIgnoreCase("IGV")){
            ruta  = "C:/Sistema Libros Electronicos/Reportes/Retencion/ReportLibroRetencionesIGV.jasper";
        }
        JOptionPane.showMessageDialog(null,"Se va Generar el Reporte.");
        JasperPrint informe = JasperFillManager.fillReport(ruta,hm, SesionUsuario.con);
        SesionUsuario.llamarBarra(SesionUsuario.fr, true);
        JasperViewer.viewReport(informe, false);
    }
     
}
