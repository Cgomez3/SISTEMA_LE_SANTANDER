/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDImportar;
import Capa_Entidades.EDataCompra;
import Capa_Entidades.EDataConsultaCompras;
import Capa_Entidades.EDataFormatCompras;
import Capa_Entidades.EDataFormatVentas;
import Capa_Entidades.EDataRetencion;
import Capa_Entidades.EDataVenta;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EFormatoDAOTVentas;
import Capa_Entidades.EPeriodo;
import Capa_Logica.Interfaz.IImportar;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LImportar implements IImportar{
    
    BDImportar bdi=new BDImportar(SesionUsuario.bddatos.getNombd());

    @Override
    public boolean InsertarRegistroCompraCab(int mes, int año) {
        return bdi.InsertarRegistroCompraCab(mes, año);
    }

    @Override
    public int ValExiste(int mes, int año) {
        return bdi.ValExiste(mes, año);
    }

    @Override
    public int CodRegistroCompra(int mes, int año) {
        return bdi.CodRegistroCompra(mes, año);
    }

    @Override
    public boolean InsertarRegistroCompraDet(EDataCompra objdata) {
        return bdi.InsertarRegistroCompraDet(objdata);
    }

    @Override
    public boolean LimpiarRegistroCompraDet(int cod) {
        return bdi.LimpiarRegistroCompraDet(cod);
    }

    @Override
    public boolean InsertarRegistroVentaCab(int mes, int año) {
        return bdi.InsertarRegistroVentaCab(mes, año);
    }

    @Override
    public int ValVenExiste(int mes, int año) {
        return bdi.ValVenExiste(mes, año);
    }

    @Override
    public int CodRegistroVenta(int mes, int año) {
        return bdi.CodRegistroVenta(mes, año);
    }

    @Override
    public boolean InsertarRegistroVentaDet(EDataVenta objdata) {
        return bdi.InsertarRegistroVentaDet(objdata);
    }

    @Override
    public boolean LimpiarRegistroVentaDet(int cod) {
        return bdi.LimpiarRegistroVentaDet(cod);
    }

    @Override
    public ArrayList<EPeriodo> ListaPeriodoCompra() {
        return bdi.ListaPeriodoCompra();
    }
    

    @Override
    public ArrayList<EDataCompra> ListaCompra(int cod) {
        return bdi.ListaCompra(cod);
    }

    @Override
    public ArrayList<EDataVenta> ListaVenta(int cod) {
        return bdi.ListaVenta(cod);
    }

    @Override
    public boolean InsertarRegCompraFormat(EDataFormatCompras data) {
        return bdi.InsertarRegCompraFormat(data);
    }

    @Override
    public int DataAnteriorFormatoCompras(int cod) {
        return bdi.DataAnteriorFormatoCompras(cod);
    }

    @Override
    public boolean LimpiarRegCompraFormat(int cod) {
        return bdi.LimpiarRegCompraFormat(cod);
    }

    @Override
    public ArrayList<EDataFormatCompras> ListaDataFormatCompras(int cod) {
        return bdi.ListaDataFormatCompras(cod);
    }

    @Override
    public boolean InsertarRegVentaFormat(EDataFormatVentas data) {
        return bdi.InsertarRegVentaFormat(data);
    }

    @Override
    public int DataAnteriorFormatoVentas(int cod) {
        return bdi.DataAnteriorFormatoVentas(cod);
    }

    @Override
    public boolean LimpiarRegVentaFormat(int cod) {
        return bdi.LimpiarRegVentaFormat(cod);
    }

    @Override
    public ArrayList<EDataFormatVentas> ListaDataFormatVentas(int cod) {
        return bdi.ListaDataFormatVentas(cod);
    }

    @Override
    public ArrayList<EPeriodo> ListaPeriodoAmbos() {
        return bdi.ListaPeriodoAmbos();
    }

    @Override
    public boolean InsertarDataExportCompras(int cod, int nroreg) {
        return bdi.InsertarDataExportCompras(cod, nroreg);
    }

    @Override
    public boolean InsertarDataExportVentas(int cod, int nroreg) {
        return bdi.InsertarDataExportVentas(cod, nroreg);
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaCompras(int cod) {
        return bdi.ConsultaCompras(cod);
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaVentas(int cod) {
        return bdi.ConsultaVentas(cod);
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaRangCompras(String inicio, String fin) {
        return bdi.ConsultaRangCompras(inicio, fin);
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaRangVentas(String inicio, String fin) {
        return bdi.ConsultaRangVentas(inicio, fin);
    }

    @Override
    public boolean InsertarDataRetencionDetalle(EDataRetencion det) {
        return bdi.InsertarDataRetencionDetalle(det);
    }

    @Override
    public ArrayList<EDataRetencion> ListarRetencionDetalle(int cod) {
        return bdi.ListarRetencionDetalle(cod);
    }

    @Override
    public ArrayList<EPeriodo> ListaPeriodoVenta() {
         return bdi.ListaPeriodoVenta();
    }

    @Override
    public ArrayList<EDetraccion> ListaDetraccion() {
            return bdi.ListaDetraccion();
    }

    @Override
    public ArrayList<EDataRetencion> ListaDetalle(int cod) {
        return bdi.ListaDetalle(cod);
    }

    @Override
    public ArrayList<EDataRetencion> ConsultaRetencionDetalle(String ruc, String fecini, String fecfin) {
        return bdi.ConsultaRetencionDetalle(ruc, fecini, fecfin);
    }

    @Override
    public ArrayList<EFormatoDAOTVentas> ListarDaotVentas() {
        return bdi.ListarDaotVentas();
    }

    @Override
    public boolean GrabarVentaInafecto(EDataVenta objvent) {
        return bdi.GrabarVentaInafecto(objvent);
    }

    
   
    
}
