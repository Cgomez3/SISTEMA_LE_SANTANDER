/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EFormaPago;
import Capa_Entidades.EParametro;
import Capa_Entidades.EProveedor;
import Capa_Entidades.ETipoComprobante;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IComprobante {
        public ArrayList<EProveedor>listaProDNI();
        public ArrayList<EDetraccion>listaDetra();
        public ArrayList<EComprobante>listaComprobante();
        public boolean InsertarComprobante(EComprobante objpro);
        public boolean ActualizarComprobante(EComprobante objpro);
        public boolean eliminarComprobante(int cod);
        public ArrayList<EParametro> ListaParametros();
        public ArrayList<EDetraccion> ListaxCodigo(String codigo);
        public ArrayList<ETipoComprobante> ListaTipoComprobante();
        public ArrayList<EFormaPago> ListaFormaPago();
        public ArrayList<ETipoComprobante> DescTipoComprobante(String codtipo);
        public String DescTipoComprobante2(String codtipo);
        public ArrayList<EProveedor> SeleccionarProveedor(String razonsocial);
        public ArrayList<EComprobante> SeleccionarComprobante(String razonso);
        public ArrayList<EComprobante>listaComprobanteNCND();
        public boolean CambioEstadoCompro(EComprobante objpro);
        public ArrayList<EComprobante> ListaCuentaAdjunto();
        public double porcentajeretencion (String opc);
        public int VerificarNroComprobante (String NroComprob, String Ruc);
        public boolean InsertarComprobanteImport(EComprobante ecom);
        public boolean InsertarComprobanteImportNew(EComprobante ecom);
        public ArrayList<EComprobante> ConsultaListaDetalle(int codprog);
        public ArrayList<EProveedor> SeleccionarProveedorxRuc(String ruc);
        public ArrayList<EComprobante>listaComprobante2();
        public ArrayList<EComprobante>listaComprobante3();
        public ArrayList<EComprobante>listaComprobante4();
        public boolean ActualizarEstadoComprobante(String numcomprob);
        public ArrayList<EComprobante> ListaParaExportarAGP(String ini, String fin, String moneda);
        
}
