/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EDataCompra;
import Capa_Entidades.EDataConsultaCompras;
import Capa_Entidades.EDataFormatCompras;
import Capa_Entidades.EDataFormatVentas;
import Capa_Entidades.EDataRetencion;
import Capa_Entidades.EDataVenta;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EFormatoDAOTVentas;
import Capa_Entidades.EPeriodo;
import Capa_Entidades.EUsuario;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IImportar {
    
    public boolean InsertarRegistroCompraCab(int mes, int año);
    public int ValExiste(int mes, int año);
    public int CodRegistroCompra(int mes, int año);
    public boolean InsertarRegistroCompraDet (EDataCompra objdata);
    public boolean LimpiarRegistroCompraDet(int cod);
    public boolean InsertarRegistroVentaCab(int mes, int año);
    public int ValVenExiste(int mes, int año);
    public int CodRegistroVenta(int mes, int año);
    public boolean InsertarRegistroVentaDet(EDataVenta objdata);
    public boolean LimpiarRegistroVentaDet(int cod);
    public ArrayList<EPeriodo> ListaPeriodoCompra();
    public ArrayList<EPeriodo> ListaPeriodoVenta();
    public ArrayList<EDataCompra> ListaCompra(int cod);
    public ArrayList<EDataVenta> ListaVenta(int cod);
    public boolean InsertarRegCompraFormat (EDataFormatCompras data);
    public int DataAnteriorFormatoCompras(int cod);
    public boolean LimpiarRegCompraFormat(int cod);
    public ArrayList<EDataFormatCompras> ListaDataFormatCompras(int cod);
    public boolean InsertarRegVentaFormat (EDataFormatVentas data);
    public int DataAnteriorFormatoVentas(int cod);
    public boolean LimpiarRegVentaFormat (int cod);
    public ArrayList<EDataFormatVentas> ListaDataFormatVentas(int cod);
    public ArrayList<EPeriodo> ListaPeriodoAmbos();
    public boolean InsertarDataExportCompras(int cod, int nroreg);
    public boolean InsertarDataExportVentas(int cod, int nroreg);
    
    public ArrayList<EDataConsultaCompras> ConsultaCompras(int cod);
    public ArrayList<EDataConsultaCompras> ConsultaVentas(int cod);
    public ArrayList<EDataConsultaCompras> ConsultaRangCompras(String inicio, String fin);
    public ArrayList<EDataConsultaCompras> ConsultaRangVentas(String inicio, String fin);
    public boolean InsertarDataRetencionDetalle(EDataRetencion det);
    public ArrayList<EDataRetencion> ListarRetencionDetalle(int cod);
    public ArrayList<EDataRetencion> ListaDetalle (int cod);
    public ArrayList<EDetraccion> ListaDetraccion();
    public ArrayList<EDataRetencion> ConsultaRetencionDetalle(String ruc, String fecini, String fecfin);
    
    public ArrayList<EFormatoDAOTVentas> ListarDaotVentas();
    
    public boolean GrabarVentaInafecto(EDataVenta objvent);
   
}
