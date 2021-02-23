/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EEstado;
import Capa_Entidades.EFormaPago;
import Capa_Entidades.EParametro;
import Capa_Entidades.EProveedor;
import Capa_Entidades.ETipoComprobante;
import Capa_Logica.Interfaz.IComprobante;
import Capa_Logica.Interfaz.IConsultaComprobante;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDConsultaComprobante extends ConexionBD implements IConsultaComprobante{
    
      public BDConsultaComprobante(String bd) {
        super(bd);
    }
    



    @Override
    public ArrayList<EComprobante> ListaxEstado(String estado) {
        String prolisServCord = "{call [dbo].[ListarxEstado](?)}";
        ArrayList<EComprobante> liscom = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, estado);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {                
                EComprobante oservcor = new EComprobante();
                oservcor.setEstado(rss.getString(1));
                 oservcor.setTipoComprobante(rss.getString(2));
                 oservcor.setNumComprobante(rss.getString(3));
                 oservcor.setFechaComprobante(rss.getString(4));
                 oservcor.setDocProveedor(rss.getString(5));
                 oservcor.setRazonSocial(rss.getString(6));
                 oservcor.setGlosaComprante(rss.getString(7));
                 oservcor.setComprobanteSujeto(rss.getString(8));
                 oservcor.setTipoDetra(rss.getString(9));
                 oservcor.setFormaPago(rss.getString(10));
                 oservcor.setDestinoPago(rss.getString(11));
                 oservcor.setNomMoneda(rss.getString(12));
                 oservcor.setTCComprobante(rss.getDouble(13));
                 oservcor.setMontoBase(rss.getDouble(14));
                 oservcor.setMontoOtros(rss.getDouble(15));
                 oservcor.setMontoIGV(rss.getDouble(16));
                 oservcor.setMontoTotal(rss.getDouble(17));
                 oservcor.setMontoRetDet(rss.getDouble(18));
                 oservcor.setMontoPagar(rss.getDouble(19));     
                 oservcor.setMontoBaseDOL(rss.getDouble(20));
                 oservcor.setMontoOtrosDOL(rss.getDouble(21));
                 oservcor.setMontoIGVDOL(rss.getDouble(22));
                 oservcor.setMontoTotalDOL(rss.getDouble(23));
                 oservcor.setMontoRetDetDOL(rss.getDouble(24));
                 oservcor.setMontoPagarDOL(rss.getDouble(25));  
                 oservcor.setPorcentaje(rss.getDouble(26));
                 oservcor.setPorcIGV(rss.getDouble(27));
                 oservcor.setComprobanteRef(rss.getString(28));
                 oservcor.setRazonSocialRef(rss.getString(29));
                 oservcor.setFechaRef(rss.getString(30));
                 oservcor.setUsuCrea(rss.getString(31));
                 oservcor.setFechaCrea(rss.getString(32));
                 oservcor.setUsuCrea(rss.getString(33));
                 oservcor.setFechaCrea(rss.getString(34));
                 oservcor.setCodComprobante(rss.getInt(35));
                liscom.add(oservcor);
            }            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return liscom;
    }

    @Override
    public ArrayList<EComprobante> ListaxRazonSocial(String razonsocial) {
       String prolisServCord = "{call [dbo].[ListarxRazonSocial](?)}";
        ArrayList<EComprobante> liscom = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, razonsocial);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {                
                EComprobante oservcor = new EComprobante();
                 oservcor.setEstado(rss.getString(1));
                 oservcor.setTipoComprobante(rss.getString(2));
                 oservcor.setNumComprobante(rss.getString(3));
                 oservcor.setFechaComprobante(rss.getString(4));
                 oservcor.setDocProveedor(rss.getString(5));
                 oservcor.setRazonSocial(rss.getString(6));
                 oservcor.setGlosaComprante(rss.getString(7));
                 oservcor.setComprobanteSujeto(rss.getString(8));
                 oservcor.setTipoDetra(rss.getString(9));
                 oservcor.setFormaPago(rss.getString(10));
                 oservcor.setDestinoPago(rss.getString(11));
                 oservcor.setNomMoneda(rss.getString(12));
                 oservcor.setTCComprobante(rss.getDouble(13));
                 oservcor.setMontoBase(rss.getDouble(14));
                 oservcor.setMontoOtros(rss.getDouble(15));
                 oservcor.setMontoIGV(rss.getDouble(16));
                 oservcor.setMontoTotal(rss.getDouble(17));
                 oservcor.setMontoRetDet(rss.getDouble(18));
                 oservcor.setMontoPagar(rss.getDouble(19));     
                 oservcor.setMontoBaseDOL(rss.getDouble(20));
                 oservcor.setMontoOtrosDOL(rss.getDouble(21));
                 oservcor.setMontoIGVDOL(rss.getDouble(22));
                 oservcor.setMontoTotalDOL(rss.getDouble(23));
                 oservcor.setMontoRetDetDOL(rss.getDouble(24));
                 oservcor.setMontoPagarDOL(rss.getDouble(25));  
                 oservcor.setPorcentaje(rss.getDouble(26));
                 oservcor.setPorcIGV(rss.getDouble(27));
                 oservcor.setComprobanteRef(rss.getString(28));
                 oservcor.setRazonSocialRef(rss.getString(29));
                 oservcor.setFechaRef(rss.getString(30));
                 oservcor.setUsuCrea(rss.getString(31));
                 oservcor.setFechaCrea(rss.getString(32));
                 oservcor.setUsuCrea(rss.getString(33));
                 oservcor.setFechaCrea(rss.getString(34));
                 oservcor.setCodComprobante(rss.getInt(35));
                liscom.add(oservcor);
            }            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return liscom;
    }

    @Override
    public ArrayList<EEstado> ListaEstadoCompro() {
        ArrayList<EEstado> lisest=new ArrayList<>();
         String prolispro="{call ListadoEstado()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EEstado tipo = new EEstado();
              
                 tipo.setCod_Estado(rs.getInt(1));
                 tipo.setDes_Estado(rs.getString(2));
                 
                 lisest.add(tipo);
             }   
             return lisest;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lisest;
        }
    }

    @Override
    public ArrayList<EComprobante> ListaxFecha(String inicio, String fin) {
        String prolisServCord = "{call [ListarxFecha](?,?)}";
        ArrayList<EComprobante> liscom = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {                
                EComprobante oservcor = new EComprobante();
                 oservcor.setEstado(rss.getString(1));
                 oservcor.setTipoComprobante(rss.getString(2));
                 oservcor.setNumComprobante(rss.getString(3));
                 oservcor.setFechaComprobante(rss.getString(4));
                 oservcor.setDocProveedor(rss.getString(5));
                 oservcor.setRazonSocial(rss.getString(6));
                 oservcor.setGlosaComprante(rss.getString(7));
                 oservcor.setComprobanteSujeto(rss.getString(8));
                 oservcor.setTipoDetra(rss.getString(9));
                 oservcor.setFormaPago(rss.getString(10));
                 oservcor.setDestinoPago(rss.getString(11));
                 oservcor.setNomMoneda(rss.getString(12));
                 oservcor.setTCComprobante(rss.getDouble(13));
                 oservcor.setMontoBase(rss.getDouble(14));
                 oservcor.setMontoOtros(rss.getDouble(15));
                 oservcor.setMontoIGV(rss.getDouble(16));
                 oservcor.setMontoTotal(rss.getDouble(17));
                 oservcor.setMontoRetDet(rss.getDouble(18));
                 oservcor.setMontoPagar(rss.getDouble(19));     
                 oservcor.setMontoBaseDOL(rss.getDouble(20));
                 oservcor.setMontoOtrosDOL(rss.getDouble(21));
                 oservcor.setMontoIGVDOL(rss.getDouble(22));
                 oservcor.setMontoTotalDOL(rss.getDouble(23));
                 oservcor.setMontoRetDetDOL(rss.getDouble(24));
                 oservcor.setMontoPagarDOL(rss.getDouble(25));  
                 oservcor.setPorcentaje(rss.getDouble(26));
                 oservcor.setPorcIGV(rss.getDouble(27));
                 oservcor.setComprobanteRef(rss.getString(28));
                 oservcor.setRazonSocialRef(rss.getString(29));
                 oservcor.setFechaRef(rss.getString(30));
                 oservcor.setUsuCrea(rss.getString(31));
                 oservcor.setFechaCrea(rss.getString(32));
                 oservcor.setUsuCrea(rss.getString(33));
                 oservcor.setFechaCrea(rss.getString(34));
                 oservcor.setCodComprobante(rss.getInt(35));
                 liscom.add(oservcor);
            }            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return liscom;
    }

    @Override
    public ArrayList<EComprobante> ListaxRazonyForma(String razon, String forma) {
        String prolisServCord = "{call [ListarxRazonSocialyForma](?,?)}";
        ArrayList<EComprobante> liscom = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, razon);
            cs.setString(2, forma);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {                
                EComprobante oservcor = new EComprobante();
                 oservcor.setEstado(rss.getString(1));
                 oservcor.setTipoComprobante(rss.getString(2));
                 oservcor.setNumComprobante(rss.getString(3));
                 oservcor.setFechaComprobante(rss.getString(4));
                 oservcor.setDocProveedor(rss.getString(5));
                 oservcor.setRazonSocial(rss.getString(6));
                 oservcor.setGlosaComprante(rss.getString(7));
                 oservcor.setComprobanteSujeto(rss.getString(8));
                 oservcor.setTipoDetra(rss.getString(9));
                 oservcor.setFormaPago(rss.getString(10));
                 oservcor.setDestinoPago(rss.getString(11));
                 oservcor.setNomMoneda(rss.getString(12));
                 oservcor.setTCComprobante(rss.getDouble(13));
                 oservcor.setMontoBase(rss.getDouble(14));
                 oservcor.setMontoOtros(rss.getDouble(15));
                 oservcor.setMontoIGV(rss.getDouble(16));
                 oservcor.setMontoTotal(rss.getDouble(17));
                 oservcor.setMontoRetDet(rss.getDouble(18));
                 oservcor.setMontoPagar(rss.getDouble(19));     
                 oservcor.setMontoBaseDOL(rss.getDouble(20));
                 oservcor.setMontoOtrosDOL(rss.getDouble(21));
                 oservcor.setMontoIGVDOL(rss.getDouble(22));
                 oservcor.setMontoTotalDOL(rss.getDouble(23));
                 oservcor.setMontoRetDetDOL(rss.getDouble(24));
                 oservcor.setMontoPagarDOL(rss.getDouble(25));  
                 oservcor.setPorcentaje(rss.getDouble(26));
                 oservcor.setPorcIGV(rss.getDouble(27));
                 oservcor.setComprobanteRef(rss.getString(28));
                 oservcor.setRazonSocialRef(rss.getString(29));
                 oservcor.setFechaRef(rss.getString(30));
                 oservcor.setUsuCrea(rss.getString(31));
                 oservcor.setFechaCrea(rss.getString(32));
                 oservcor.setUsuCrea(rss.getString(33));
                 oservcor.setFechaCrea(rss.getString(34));
                 oservcor.setCodComprobante(rss.getInt(35));
                liscom.add(oservcor);
            }            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return liscom;
    }

    @Override
    public ArrayList<EComprobante> ListaxFechayForma(String inicio, String fin, String forma) {
             String prolisServCord = "{call [dbo].[ListarxFechayForma](?,?,?)}";
        ArrayList<EComprobante> liscom = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            cs.setString(3, forma);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {                
                EComprobante oservcor = new EComprobante();
                 oservcor.setEstado(rss.getString(1));
                 oservcor.setTipoComprobante(rss.getString(2));
                 oservcor.setNumComprobante(rss.getString(3));
                 oservcor.setFechaComprobante(rss.getString(4));
                 oservcor.setDocProveedor(rss.getString(5));
                 oservcor.setRazonSocial(rss.getString(6));
                 oservcor.setGlosaComprante(rss.getString(7));
                 oservcor.setComprobanteSujeto(rss.getString(8));
                 oservcor.setTipoDetra(rss.getString(9));
                 oservcor.setFormaPago(rss.getString(10));
                 oservcor.setDestinoPago(rss.getString(11));
                 oservcor.setNomMoneda(rss.getString(12));
                 oservcor.setTCComprobante(rss.getDouble(13));
                 oservcor.setMontoBase(rss.getDouble(14));
                 oservcor.setMontoOtros(rss.getDouble(15));
                 oservcor.setMontoIGV(rss.getDouble(16));
                 oservcor.setMontoTotal(rss.getDouble(17));
                 oservcor.setMontoRetDet(rss.getDouble(18));
                 oservcor.setMontoPagar(rss.getDouble(19));     
                 oservcor.setMontoBaseDOL(rss.getDouble(20));
                 oservcor.setMontoOtrosDOL(rss.getDouble(21));
                 oservcor.setMontoIGVDOL(rss.getDouble(22));
                 oservcor.setMontoTotalDOL(rss.getDouble(23));
                 oservcor.setMontoRetDetDOL(rss.getDouble(24));
                 oservcor.setMontoPagarDOL(rss.getDouble(25));  
                 oservcor.setPorcentaje(rss.getDouble(26));
                 oservcor.setPorcIGV(rss.getDouble(27));
                 oservcor.setComprobanteRef(rss.getString(28));
                 oservcor.setRazonSocialRef(rss.getString(29));
                 oservcor.setFechaRef(rss.getString(30));
                 oservcor.setUsuCrea(rss.getString(31));
                 oservcor.setFechaCrea(rss.getString(32));
                 oservcor.setUsuCrea(rss.getString(33));
                 oservcor.setFechaCrea(rss.getString(34));
                 oservcor.setCodComprobante(rss.getInt(35));
                liscom.add(oservcor);
            }            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return liscom;
    }
}

    
