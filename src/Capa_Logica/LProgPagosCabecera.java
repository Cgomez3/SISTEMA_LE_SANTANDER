/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDProgPagosCabecera;
import Capa_Entidades.EComprobProcesados;
import Capa_Entidades.EComprobante;
import Capa_Entidades.EConsolidado;
import Capa_Entidades.ECuentaBancaria;
import Capa_Entidades.EDataRetencion;
import Capa_Entidades.EDetProgramPagos;
import Capa_Entidades.EExportado;
import Capa_Entidades.EExportarRetencion;
import Capa_Entidades.EProgPagosCabecera;
import Capa_Entidades.EProgPagosDetalle;
import Capa_Entidades.ETipoComprobante;
import Capa_Entidades.ExportacionInterbank;
import Capa_Logica.Interfaz.IProgPagosCabecera;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LProgPagosCabecera implements IProgPagosCabecera{

     BDProgPagosCabecera bdprgpago=new BDProgPagosCabecera(SesionUsuario.bddatos.getNombd());
    
    
    @Override
    public boolean InsertarPrgPagos(EProgPagosCabecera objpro) {
        return bdprgpago.InsertarPrgPagos(objpro);
    }

    @Override
    public boolean InsertarPrgPagosDeta(EProgPagosDetalle objpro) {
        return bdprgpago.InsertarPrgPagosDeta(objpro);
    }

    @Override
    public ArrayList<EProgPagosCabecera> MaxCodigo() {
       return bdprgpago.MaxCodigo();
    }

    @Override
    public boolean CambioEstadoCompro(EComprobante objpro) {
        return bdprgpago.CambioEstadoCompro(objpro);
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListarCabeceraComprobante() {
       return bdprgpago.ListarCabeceraComprobante();
    }

    @Override
    public boolean eliminarProgramacion(int cod) {
        return bdprgpago.eliminarProgramacion(cod);
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListaxFechaCabecera(String inicio, String fin) {
        return bdprgpago.ListaxFechaCabecera(inicio, fin);
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListaxRazonSocialCabecera(String estado) {
       return bdprgpago.ListaxRazonSocialCabecera(estado);
    }

    @Override
    public ArrayList<EProgPagosDetalle> ListaDetalle() {
        return bdprgpago.ListaDetalle();
    }

    @Override
    public int validarctabancaria(String banco, String moneda, String DocProveedor) {
        return bdprgpago.validarctabancaria(banco, moneda, DocProveedor);
    }

    @Override
    public boolean insertarProgDetalle(EDetProgramPagos edet, int codprog) {
        return bdprgpago.insertarProgDetalle(edet, codprog);
    }

    @Override
    public ArrayList<EConsolidado> lstConsolidado(int codprog) {
        return bdprgpago.lstConsolidado(codprog);
    }

    @Override
    public boolean insertarConsolidado(EConsolidado econ) {
        return bdprgpago.insertarConsolidado(econ);
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListaxFecha(String inicio, String fin) {
        return bdprgpago.ListaxFecha(inicio, fin);
    }

    @Override
    public ArrayList<EConsolidado> buscaConsolidado(int codprog) {
        return bdprgpago.buscaConsolidado(codprog);
    }

    @Override
    public ArrayList<EComprobante> lstdetalleprg(int codprog) {
        return bdprgpago.lstdetalleprg(codprog);
    }

    @Override
    public boolean EliminarProgramacion(int codprog) {
        return bdprgpago.EliminarProgramacion(codprog);
    }

    @Override
    public ArrayList<ECuentaBancaria> CtaBancaria(String docprov, String moneda) {
        return bdprgpago.CtaBancaria(docprov, moneda);
    }

    @Override
    public boolean ActualizarPrgPagos(EProgPagosCabecera objpro) {
        return bdprgpago.ActualizarPrgPagos(objpro);
    }

    @Override
    public boolean CambiarEstado(int codcompr) {
        return bdprgpago.CambiarEstado(codcompr);
    }

    @Override
    public boolean LimpiarDetConsolidado(int codprog) {
        return bdprgpago.LimpiarDetConsolidado(codprog);
    }

    @Override
    public ArrayList<EComprobante> lstdetallexprov(int codprog, String docprov) {
        return bdprgpago.lstdetallexprov(codprog, docprov);
    }

    @Override
    public boolean ActualizarRetDet(EComprobante econ) {
        return bdprgpago.ActualizarRetDet(econ);
    }

    @Override
    public String ObtChecksum(int codprog) {
        return bdprgpago.ObtChecksum(codprog);
    }

    @Override
    public boolean GrabarExportados(EExportado objexportar) {
        return bdprgpago.GrabarExportados(objexportar);
    }

    @Override
    public boolean InsertProcesados(EComprobProcesados objcomproc) {
        return bdprgpago.InsertProcesados(objcomproc);
    }

    @Override
    public boolean ExtornarProcesados(EComprobProcesados objcomproc) {
        return bdprgpago.ExtornarProcesados(objcomproc);
    }

    @Override
    public int ComprobarExtornar(EComprobProcesados objcomproc) {
        return bdprgpago.ComprobarExtornar(objcomproc);
    }

    @Override
    public ArrayList<EDataRetencion> ComprobReten() {
        return bdprgpago.ComprobReten();
    }

    @Override
    public boolean GenerarCodigo() {
        return bdprgpago.GenerarCodigo();
    }

    @Override
    public int ObtenerCodigoGenerado() {
        return bdprgpago.ObtenerCodigoGenerado();
    }

    @Override
    public boolean InsertarComprobReten(EDataRetencion objdat) {
        return bdprgpago.InsertarComprobReten(objdat);
    }

    @Override
    public ArrayList<EProgPagosCabecera> BuscarExportacion(String inicio, String fin) {
        return bdprgpago.BuscarExportacion(inicio, fin);
    }

    @Override
    public boolean EliminarDataExportada(int codprog) {
        return bdprgpago.EliminarDataExportada(codprog);
    }

    @Override
    public ArrayList<EExportarRetencion> ListarRetencionxExport(int mes, int año) {
        return bdprgpago.ListarRetencionxExport(mes, año);
    }

    @Override
    public ArrayList<EProgPagosCabecera> listacabparagp() {
        return bdprgpago.listacabparagp();
    }

    @Override
    public ArrayList<EComprobante> lstdetalleagp(int cod, String sujeto) {
        return bdprgpago.lstdetalleagp(cod,sujeto);
    }

    @Override
    public boolean ActualizarAContabilizado(String numcomprobante) {
        return bdprgpago.ActualizarAContabilizado(numcomprobante);
    }

    @Override
    public ArrayList<ExportacionInterbank> ListarExportacionInterbank(int idProgramacion) {
        return bdprgpago.ListarExportacionInterbank(idProgramacion);
    }

    @Override
    public ArrayList<ECuentaBancaria> validarCuentaBancaria(String docProveedor, String banco, String monedaPrincipal) {
       return bdprgpago.validarCuentaBancaria(docProveedor,banco,monedaPrincipal);
    }

    @Override
    public boolean insertarProgDetalleAutomatico(EDetProgramPagos edet) {
        return bdprgpago.insertarProgDetalleAutomatico(edet);
    }

    @Override
    public ArrayList<ECuentaBancaria> eliminarProgramacionDetalle(String cod,int option, String docProv,String banco,String moneda) {
  
        return bdprgpago.eliminarProgramacionDetalle(cod,option,docProv,banco,moneda);
    }

    @Override
    public ECuentaBancaria validarDocumentosProgramados(String numeroDocu,String DocProveedor,String TipoComprobante) {
        return bdprgpago.validarDocumentosProgramados(numeroDocu,DocProveedor,TipoComprobante);
    }

    @Override
    public ArrayList<ETipoComprobante> BuscarTipoComprobante(String codigo) {
        return bdprgpago.BuscarTipoComprobante(codigo);
    }

  
    
    
}
