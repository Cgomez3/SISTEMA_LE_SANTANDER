/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDComprobante;
import Capa_Entidades.EComprobante;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EFormaPago;
import Capa_Entidades.EParametro;
import Capa_Entidades.EProveedor;
import Capa_Entidades.ETipoComprobante;
import Capa_Logica.Interfaz.IComprobante;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LComprobante implements IComprobante{

    
        BDComprobante bdcom=new BDComprobante(SesionUsuario.bddatos.getNombd());
    
    @Override
    public ArrayList<EComprobante> listaComprobante() {
        return bdcom.listaComprobante();
    }

    @Override
    public boolean InsertarComprobante(EComprobante objpro) {
        return bdcom.InsertarComprobante(objpro);
    }

    @Override
    public boolean ActualizarComprobante(EComprobante objpro) {
        return bdcom.ActualizarComprobante(objpro);
    }

    @Override
    public boolean eliminarComprobante(int cod) {
        return bdcom.eliminarComprobante(cod);
    }

    @Override
    public ArrayList<EDetraccion> listaDetra() {
        return bdcom.listaDetra();
    }

    @Override
    public ArrayList<EProveedor> listaProDNI() {
        return bdcom.listaProDNI();
    }

    @Override
    public ArrayList<EParametro> ListaParametros() {
        return bdcom.ListaParametros();
    }



    @Override
    public ArrayList<EDetraccion> ListaxCodigo(String codigo) {
          return bdcom.ListaxCodigo(codigo);
    }

    @Override
    public ArrayList<ETipoComprobante> ListaTipoComprobante() {
       return bdcom.ListaTipoComprobante();
    }

    @Override
    public ArrayList<EFormaPago> ListaFormaPago() {
        return bdcom.ListaFormaPago();
    }

    @Override
    public ArrayList<ETipoComprobante> DescTipoComprobante(String codtipo) {
       return bdcom.DescTipoComprobante(codtipo);
    }

    @Override
    public String DescTipoComprobante2(String codtipo) {
        return bdcom.DescTipoComprobante2(codtipo);
    }

    @Override
    public ArrayList<EProveedor> SeleccionarProveedor(String razonsocial) {
        return bdcom.SeleccionarProveedor(razonsocial);
    }

    @Override
    public ArrayList<EComprobante> SeleccionarComprobante(String razonso) {
        return bdcom.SeleccionarComprobante(razonso);
    }

    @Override
    public ArrayList<EComprobante> listaComprobanteNCND() {
        return bdcom.listaComprobanteNCND();
    }

    @Override
    public boolean CambioEstadoCompro(EComprobante objpro) {
       return bdcom.CambioEstadoCompro(objpro);
    }

    @Override
    public ArrayList<EComprobante> ListaCuentaAdjunto() {
       return bdcom.ListaCuentaAdjunto();
    }

    @Override
    public double porcentajeretencion(String opc) {
       return bdcom.porcentajeretencion(opc);
    }

    @Override
    public int VerificarNroComprobante(String NroComprob,String Ruc) {
       return bdcom.VerificarNroComprobante(NroComprob,Ruc);
    }

    @Override
    public boolean InsertarComprobanteImport(EComprobante ecom) {
       return bdcom.InsertarComprobanteImport(ecom);
    }

    @Override
    public ArrayList<EComprobante> ConsultaListaDetalle(int codprog) {
       return bdcom.ConsultaListaDetalle(codprog);
    }

    @Override
    public ArrayList<EProveedor> SeleccionarProveedorxRuc(String ruc) {
       return bdcom.SeleccionarProveedorxRuc(ruc);
    }

    @Override
    public ArrayList<EComprobante> listaComprobante2() {
       return bdcom.listaComprobante2();
    }

    @Override
    public ArrayList<EComprobante> listaComprobante3() {
       return bdcom.listaComprobante3();
    }

    @Override
    public ArrayList<EComprobante> listaComprobante4() {
        return bdcom.listaComprobante4();
    }

    @Override
    public boolean ActualizarEstadoComprobante(String numcomprob) {
        return bdcom.ActualizarEstadoComprobante(numcomprob);
    }

    @Override
    public ArrayList<EComprobante> ListaParaExportarAGP(String ini, String fin, String moneda) {
        return bdcom.ListaParaExportarAGP(ini, fin, moneda);
    }

    @Override
    public boolean InsertarComprobanteImportNew(EComprobante ecom) {
       return bdcom.InsertarComprobanteImportNew(ecom);
    }

  
    
}
