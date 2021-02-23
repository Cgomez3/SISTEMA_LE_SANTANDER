/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

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
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IProgPagosCabecera {

    public boolean InsertarPrgPagos(EProgPagosCabecera objpro);

    public boolean InsertarPrgPagosDeta(EProgPagosDetalle objpro);
    

    public ArrayList<EProgPagosCabecera> MaxCodigo();

    public boolean CambioEstadoCompro(EComprobante objpro);

    public ArrayList<EProgPagosCabecera> ListarCabeceraComprobante();

    public boolean eliminarProgramacion(int cod);
    
    public ArrayList<ECuentaBancaria> eliminarProgramacionDetalle(String cod,int option, String docProv,String banco,String Moneda);

    public ArrayList<EProgPagosCabecera> ListaxFechaCabecera(String inicio, String fin);

    public ArrayList<EProgPagosCabecera> ListaxRazonSocialCabecera(String estado);

    public ArrayList<EProgPagosDetalle> ListaDetalle();

    public int validarctabancaria(String banco, String moneda, String DocProveedor);

    public boolean insertarProgDetalle(EDetProgramPagos edet, int codprog);
    
    public boolean insertarProgDetalleAutomatico(EDetProgramPagos edet);

    public ArrayList<EConsolidado> lstConsolidado(int codprog);

    public boolean insertarConsolidado(EConsolidado econ);

    public ArrayList<EProgPagosCabecera> ListaxFecha(String inicio, String fin);

    public ArrayList<EConsolidado> buscaConsolidado(int codprog);

    public ArrayList<EComprobante> lstdetalleprg(int codprog);

    public boolean EliminarProgramacion(int codprog);

    public ArrayList<ECuentaBancaria> CtaBancaria(String docprov, String moneda);

    public boolean ActualizarPrgPagos(EProgPagosCabecera objpro);

    public boolean CambiarEstado(int codcompr);

    public boolean LimpiarDetConsolidado(int codprog);

    public ArrayList<EComprobante> lstdetallexprov(int codprog, String docprov);

    public boolean ActualizarRetDet(EComprobante econ);

    public String ObtChecksum(int codprog);

    public boolean GrabarExportados(EExportado objexportar);

    public boolean InsertProcesados(EComprobProcesados objcomproc);

    public boolean ExtornarProcesados(EComprobProcesados objcomproc);

    public int ComprobarExtornar(EComprobProcesados objcomproc);

    public ArrayList<EDataRetencion> ComprobReten();

    public boolean GenerarCodigo();

    public int ObtenerCodigoGenerado();

    public boolean InsertarComprobReten(EDataRetencion objdat);

    public ArrayList<EProgPagosCabecera> BuscarExportacion(String inicio, String fin);

    public boolean EliminarDataExportada(int codprog);

    public ArrayList<EExportarRetencion> ListarRetencionxExport(int mes, int año);

    public ArrayList<EProgPagosCabecera> listacabparagp();

    public ArrayList<EComprobante> lstdetalleagp(int cod, String sujeto);

    public boolean ActualizarAContabilizado(String numcomprobante);

    public ArrayList<ExportacionInterbank> ListarExportacionInterbank(int idProgramacion);
    
    public ArrayList<ECuentaBancaria> validarCuentaBancaria(String docProveedor,String banco,String monedaPrincipal);
    
    public ECuentaBancaria  validarDocumentosProgramados(String numeroDocu,String DocProveedor,String TipoComprobante);

    public ArrayList<ETipoComprobante> BuscarTipoComprobante(String codigo);
}
