/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EComprobante;
import Capa_Entidades.EDetraccion;
import Capa_Entidades.EFormaPago;
import Capa_Entidades.EParametro;
import Capa_Entidades.EProveedor;
import Capa_Entidades.ETipoComprobante;
import Capa_Logica.Interfaz.IComprobante;
import Capa_Logica.SesionUsuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
/**
 *
 * @author EXPERTYA
 */
public class BDComprobante extends ConexionBD implements IComprobante {

    public BDComprobante(String bd) {
        super(bd);
    }

    @Override
    public ArrayList<EComprobante> listaComprobante() {
        ArrayList<EComprobante> liscom = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaBasicaComprobante]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();
                tipo.setEstado(rs.getString(1));
                tipo.setTipoComprobante(rs.getString(2));
                tipo.setNumComprobante(rs.getString(3));
                tipo.setFechaComprobante(rs.getString(4));
                tipo.setDocProveedor(rs.getString(5));
                tipo.setRazonSocial(rs.getString(6));
                tipo.setGlosaComprante(rs.getString(7));
                tipo.setComprobanteSujeto(rs.getString(8));
                tipo.setTipoDetra(rs.getString(9));
                tipo.setFormaPago(rs.getString(10));
                tipo.setDestinoPago(rs.getString(11));
                tipo.setNomMoneda(rs.getString(12));
                tipo.setTCComprobante(rs.getDouble(13));
                tipo.setMontoBase(rs.getDouble(14));
                tipo.setMontoOtros(rs.getDouble(15));
                tipo.setMontoIGV(rs.getDouble(16));
                tipo.setMontoTotal(rs.getDouble(17));
                tipo.setMontoRetDet(rs.getDouble(18));
                tipo.setMontoPagar(rs.getDouble(19));
                tipo.setMontoBaseDOL(rs.getDouble(20));
                tipo.setMontoOtrosDOL(rs.getDouble(21));
                tipo.setMontoIGVDOL(rs.getDouble(22));
                tipo.setMontoTotalDOL(rs.getDouble(23));
                tipo.setMontoRetDetDOL(rs.getDouble(24));
                tipo.setMontoPagarDOL(rs.getDouble(25));
                tipo.setPorcentaje(rs.getDouble(26));
                tipo.setPorcIGV(rs.getDouble(27));
                tipo.setComprobanteRef(rs.getString(28));
                tipo.setRazonSocialRef(rs.getString(29));
                tipo.setFechaRef(rs.getString(30));
                tipo.setUsuCrea(rs.getString(31));
                tipo.setFechaCrea(rs.getString(32));
                tipo.setUsuCrea(rs.getString(33));
                tipo.setFechaCrea(rs.getString(34));
                tipo.setCodComprobante(rs.getInt(35));
                liscom.add(tipo);
            }
            System.out.println(liscom.size());
            return liscom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscom;
        }

    }

    @Override
    public boolean InsertarComprobante(EComprobante objpro) {
        String procInsertpro = "{call [dbo].[sp_AgregarComprobante](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);
//             
//             cs.setInt(1, objpro.getCodComprobante());
            cs.setString(1, objpro.getTipoComprobante());
            cs.setString(2, objpro.getNumComprobante());
            cs.setString(3, objpro.getFechaComprobante());
            cs.setString(4, objpro.getDocProveedor());
            cs.setString(5, objpro.getGlosaComprante());
            cs.setString(6, objpro.getComprobanteSujeto());
            cs.setString(7, objpro.getCodDetraccion());
            cs.setString(8, objpro.getFormaPago());
            cs.setString(9, objpro.getDestinoPago());
            cs.setInt(10, objpro.getCodMoneda());
            cs.setDouble(11, objpro.getTCComprobante());
            cs.setDouble(12, objpro.getMontoBase());
            cs.setDouble(13, objpro.getMontoOtros());
            cs.setDouble(14, objpro.getMontoIGV());
            cs.setDouble(15, objpro.getMontoTotal());
            cs.setDouble(16, objpro.getMontoRetDet());
            cs.setDouble(17, objpro.getMontoPagar());
            cs.setDouble(18, objpro.getMontoBaseDOL());
            cs.setDouble(19, objpro.getMontoOtrosDOL());
            cs.setDouble(20, objpro.getMontoIGVDOL());
            cs.setDouble(21, objpro.getMontoTotalDOL());
            cs.setDouble(22, objpro.getMontoRetDetDOL());
            cs.setDouble(23, objpro.getMontoPagarDOL());
            cs.setDouble(24, objpro.getPorcentaje());
            cs.setDouble(25, objpro.getPorcIGV());
            cs.setString(26, objpro.getEstado());
            cs.setString(27, objpro.getComprobanteRef());
            cs.setString(28, objpro.getRazonSocialRef());
            cs.setString(29, objpro.getFechaRef());
            cs.setString(30, objpro.getUsuCrea());
            cs.setString(31, objpro.getNroCuentaContable());
//             }
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean ActualizarComprobante(EComprobante objpro) {
        String procInsertpro = "{call [dbo].[usp_ActualizarComprobante](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);
            cs.setInt(1, objpro.getCodComprobante());
            cs.setString(2, objpro.getTipoComprobante());
            cs.setString(3, objpro.getNumComprobante());
            cs.setString(4, objpro.getFechaComprobante());
            cs.setString(5, objpro.getDocProveedor());
            cs.setString(6, objpro.getGlosaComprante());
            cs.setString(7, objpro.getComprobanteSujeto());
            cs.setString(8, objpro.getCodDetraccion());
            cs.setString(9, objpro.getFormaPago());
            cs.setString(10, objpro.getDestinoPago());
            cs.setInt(11, objpro.getCodMoneda());
            cs.setString(12, String.valueOf(objpro.getTCComprobante()));
            cs.setString(13, String.valueOf(objpro.getMontoBase()));
            cs.setString(14, String.valueOf(objpro.getMontoOtros()));
            cs.setString(15, String.valueOf(objpro.getMontoIGV()));
            cs.setString(16,String.valueOf( objpro.getMontoTotal()));
            cs.setString(17, String.valueOf(objpro.getMontoRetDet()));
            cs.setString(18, String.valueOf(objpro.getMontoPagar()));
            cs.setString(19, String.valueOf(objpro.getMontoBaseDOL()));
            cs.setString(20, String.valueOf(objpro.getMontoOtrosDOL()));
            cs.setString(21, String.valueOf(objpro.getMontoIGVDOL()));
            cs.setString(22, String.valueOf(objpro.getMontoTotalDOL()));
            cs.setString(23, String.valueOf(objpro.getMontoRetDetDOL()));
            cs.setString(24, String.valueOf(objpro.getMontoPagarDOL()));
            cs.setString(25, String.valueOf(objpro.getPorcentaje()));
            cs.setString(26, String.valueOf(objpro.getPorcIGV()));
            cs.setString(27, objpro.getEstado());
            cs.setString(28, objpro.getComprobanteRef());
            cs.setString(29, objpro.getRazonSocialRef());
            cs.setString(30, objpro.getFechaRef());
            cs.setString(31, objpro.getUsuModi());
            cs.setString(32, objpro.getNroCuentaContable());

            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarComprobante(int cod) {
        String proceli = "{call [dbo].[Esp_EliminarComprobante](?)}";
        try {
            CallableStatement cs = cn.prepareCall(proceli);
            cs.setInt(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public ArrayList<EDetraccion> listaDetra() {
        ArrayList<EDetraccion> lisdetra = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_ListaDetraccion]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDetraccion tipo = new EDetraccion();
                tipo.setCodDetraccion(rs.getString(1));
                tipo.setTipoDetra(rs.getString(2));
                tipo.setPorcentajeDetra(rs.getDouble(3));
                tipo.setDescripcion(rs.getString(8));
                lisdetra.add(tipo);
            }
            return lisdetra;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return lisdetra;
        }
    }

    @Override
    public ArrayList<EProveedor> listaProDNI() {
        ArrayList<EProveedor> lisxdni = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaProveedorDNI]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EProveedor tipo = new EProveedor();
                tipo.setRsDestino(rs.getString(1));
                lisxdni.add(tipo);
            }
            return lisxdni;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lisxdni;
        }
    }

    @Override
    public ArrayList<EParametro> ListaParametros() {
        ArrayList<EParametro> lispara = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaParametro]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EParametro tipo = new EParametro();
                System.out.println((rs.getInt(1) + "ENTRO"));

                tipo.setCodParametro(rs.getInt(1));
                tipo.setRazonSocial(rs.getString(2));
                tipo.setRucParametro(rs.getString(3));
                tipo.setDireParametro(rs.getString(4));

                tipo.setNomBanco(rs.getString(5));

                tipo.setCTASoles(rs.getString(6));
                tipo.setCTADolares(rs.getString(7));
                tipo.setCCISoles(rs.getString(8));
                tipo.setCCIDolares(rs.getString(9));
                tipo.setCTAConSol(rs.getString(10));
                tipo.setCTAConDol(rs.getString(11));

                tipo.setNomBanco2(rs.getString(12));

                tipo.setCTASoles2(rs.getString(13));
                tipo.setCTADolares2(rs.getString(14));
                tipo.setCCISoles2(rs.getString(15));
                tipo.setCCIDolares2(rs.getString(16));
                tipo.setCTAConSol2(rs.getString(17));
                tipo.setCTAConDol2(rs.getString(18));

                tipo.setIGV(rs.getDouble(19));
                tipo.setCampo1(rs.getDouble(20));
                tipo.setCampo2(rs.getDouble(21));

                tipo.setCuentaIGV(rs.getString(22));
                tipo.setCxPSolPro(rs.getString(23));
                tipo.setCxPDolPro(rs.getString(24));
                tipo.setCxPSolRxH(rs.getString(25));
                tipo.setCxPDolRxH(rs.getString(26));
                lispara.add(tipo);
            }
            return lispara;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lispara;
        }
    }

    @Override
    public ArrayList<EDetraccion> ListaxCodigo(String codigo) {
        ArrayList<EDetraccion> lstdetra = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_ListaDetraxCodigo](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDetraccion tipo = new EDetraccion();
                tipo.setPorcentajeDetra(rs.getDouble(1));
                cs.setString(1, codigo);
                lstdetra.add(tipo);
            }
            return lstdetra;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstdetra;
        }
    }

    @Override
    public ArrayList<ETipoComprobante> ListaTipoComprobante() {
        ArrayList<ETipoComprobante> listipocom = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_ListaTipoCompro]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                ETipoComprobante tipo = new ETipoComprobante();
                tipo.setCodComprobante(rs.getString(1));
                tipo.setDescComprobante(rs.getString(2));

                listipocom.add(tipo);
            }
            return listipocom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return listipocom;
        }
    }

    @Override
    public ArrayList<EFormaPago> ListaFormaPago() {
        ArrayList<EFormaPago> lisformapago = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_ListaPago]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EFormaPago tipo = new EFormaPago();
                tipo.setCodPago(rs.getInt(1));
                tipo.setDescPago(rs.getString(2));

                lisformapago.add(tipo);
            }
            return lisformapago;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lisformapago;
        }
    }

    @Override
    public ArrayList<ETipoComprobante> DescTipoComprobante(String codtipo) {
        ArrayList<ETipoComprobante> lsttipo = new ArrayList<>();
        String prolispro = "{call [dbo].[DescTipoComprobante](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, codtipo);
            ResultSet rss = cs.executeQuery();

            while (rss.next()) {

                ETipoComprobante tipo = new ETipoComprobante();
                tipo.setCodComprobante(rss.getString(1));
                tipo.setDescComprobante(rss.getString(2));

                System.out.println(tipo);
            }
            return lsttipo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("entro");
            System.out.println("entro");
            return lsttipo;
        }
    }

    @Override
    public String DescTipoComprobante2(String codtipo) {
        String var = null;
        String prolispro = "{call [dbo].[DescTipoComprobante](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, codtipo);
            ResultSet rss = cs.executeQuery();

            while (rss.next()) {

                var = (rss.getString(1));

            }
            return var;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return var;
        }
    }

    @Override
    public ArrayList<EProveedor> SeleccionarProveedor(String razonsocial) {
        ArrayList<EProveedor> lstproveedor = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_SeleccionarProveedor](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, razonsocial);
            ResultSet rss = cs.executeQuery();

            while (rss.next()) {

                EProveedor tipo = new EProveedor();
                tipo.setDocProveedor(rss.getString(1));
                tipo.setTipoDocProveedor(rss.getString(2));
                tipo.setRazonSocial(rss.getString(3));
                tipo.setEstado(rss.getInt(4));
                tipo.setDireccion(rss.getString(5));
                tipo.setTelefono(rss.getString(6));
                tipo.setContacto(rss.getString(7));
                tipo.setCorreoContacto(rss.getString(8));
                tipo.setNomBanco(rss.getString(9));
                tipo.setCTASoles(rss.getString(10));
                tipo.setCTADolares(rss.getString(11));
                tipo.setCCISoles(rss.getString(12));
                tipo.setCCIDolares(rss.getString(13));
                lstproveedor.add(tipo);
            }
            return lstproveedor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstproveedor;
        }
    }

    @Override
    public ArrayList<EComprobante> SeleccionarComprobante(String razonso) {
        ArrayList<EComprobante> liscomprobante = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_SeleccionarComprobante](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, razonso);
            ResultSet rss = cs.executeQuery();

            while (rss.next()) {
                EComprobante tipo = new EComprobante();

                tipo.setEstado(rss.getString(1));
                tipo.setTipoComprobante(rss.getString(2));
                tipo.setNumComprobante(rss.getString(3));
                tipo.setFechaComprobante(rss.getString(4));
                tipo.setDocProveedor(rss.getString(5));
                tipo.setRazonSocial(rss.getString(6));
                tipo.setGlosaComprante(rss.getString(7));
                tipo.setComprobanteSujeto(rss.getString(8));
                tipo.setTipoDetra(rss.getString(9));
                tipo.setFormaPago(rss.getString(10));
                tipo.setDestinoPago(rss.getString(11));
                tipo.setNomMoneda(rss.getString(12));
                tipo.setTCComprobante(rss.getDouble(13));
                tipo.setMontoBase(rss.getDouble(14));
                tipo.setMontoOtros(rss.getDouble(15));
                tipo.setMontoIGV(rss.getDouble(16));
                tipo.setMontoTotal(rss.getDouble(17));
                tipo.setMontoRetDet(rss.getDouble(18));
                tipo.setMontoPagar(rss.getDouble(19));
                tipo.setMontoBaseDOL(rss.getDouble(20));
                tipo.setMontoOtrosDOL(rss.getDouble(21));
                tipo.setMontoIGVDOL(rss.getDouble(22));
                tipo.setMontoTotalDOL(rss.getDouble(23));
                tipo.setMontoRetDetDOL(rss.getDouble(24));
                tipo.setMontoPagarDOL(rss.getDouble(25));
                tipo.setPorcentaje(rss.getDouble(26));
                tipo.setPorcIGV(rss.getDouble(27));
                tipo.setComprobanteRef(rss.getString(28));
                tipo.setRazonSocialRef(rss.getString(29));
                tipo.setFechaRef(rss.getString(30));
                tipo.setUsuCrea(rss.getString(31));
                tipo.setFechaCrea(rss.getString(32));
                tipo.setUsuCrea(rss.getString(33));
                tipo.setFechaCrea(rss.getString(34));
                tipo.setCodComprobante(rss.getInt(35));
                liscomprobante.add(tipo);
            }
            return liscomprobante;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscomprobante;
        }
    }

    @Override
    public ArrayList<EComprobante> listaComprobanteNCND() {
        ArrayList<EComprobante> liscomprobante = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaComprobanteNCND]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();

                tipo.setEstado(rs.getString(1));
                tipo.setTipoComprobante(rs.getString(2));
                tipo.setNumComprobante(rs.getString(3));
                tipo.setFechaComprobante(rs.getString(4));
                tipo.setDocProveedor(rs.getString(5));
                tipo.setRazonSocial(rs.getString(6));
                tipo.setGlosaComprante(rs.getString(7));
                tipo.setComprobanteSujeto(rs.getString(8));
                tipo.setTipoDetra(rs.getString(9));
                tipo.setFormaPago(rs.getString(10));
                tipo.setDestinoPago(rs.getString(11));
                tipo.setNomMoneda(rs.getString(12));
                tipo.setTCComprobante(rs.getDouble(13));
                tipo.setMontoBase(rs.getDouble(14));
                tipo.setMontoOtros(rs.getDouble(15));
                tipo.setMontoIGV(rs.getDouble(16));
                tipo.setMontoTotal(rs.getDouble(17));
                tipo.setMontoRetDet(rs.getDouble(18));
                tipo.setMontoPagar(rs.getDouble(19));
                tipo.setMontoBaseDOL(rs.getDouble(20));
                tipo.setMontoOtrosDOL(rs.getDouble(21));
                tipo.setMontoIGVDOL(rs.getDouble(22));
                tipo.setMontoTotalDOL(rs.getDouble(23));
                tipo.setMontoRetDetDOL(rs.getDouble(24));
                tipo.setMontoPagarDOL(rs.getDouble(25));
                tipo.setPorcentaje(rs.getDouble(26));
                tipo.setPorcIGV(rs.getDouble(27));
                tipo.setComprobanteRef(rs.getString(28));
                tipo.setRazonSocialRef(rs.getString(29));
                tipo.setFechaRef(rs.getString(30));
                tipo.setUsuCrea(rs.getString(31));
                tipo.setFechaCrea(rs.getString(32));
                tipo.setUsuCrea(rs.getString(33));
                tipo.setFechaCrea(rs.getString(34));
                tipo.setCodComprobante(rs.getInt(35));

                liscomprobante.add(tipo);
            }
            return liscomprobante;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscomprobante;
        }
    }

    @Override
    public boolean CambioEstadoCompro(EComprobante objpro) {
        String procInsertpro = "{call CambioEstadoCompro(?,?)";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);

            cs.setInt(1, objpro.getCodComprobante());
            cs.setString(2, objpro.getEstado());

            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EComprobante> ListaCuentaAdjunto() {
        ArrayList<EComprobante> liscomprobante = new ArrayList<>();
        String prolispro = "{call ListaCuentaAdjunto()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();

                tipo.setDocProveedor(rs.getString(1));
                tipo.setRazonSocial(rs.getString(2));
                tipo.setNomMoneda(rs.getString(3));
                tipo.setMontoPagar(rs.getDouble(4));
                tipo.setMontoPagarDOL(rs.getDouble(5));

                liscomprobante.add(tipo);
            }
            return liscomprobante;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscomprobante;
        }

    }

    @Override
    public double porcentajeretencion(String opc) {
        String procInsertpro = "{? = call [fn_cambiarcalculoretencio](?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.setString(2, opc);
            cs.executeUpdate();
            return cs.getDouble(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0.0;
        }
    }

    @Override
    public int VerificarNroComprobante(String NroComprob, String Ruc) {
        String procverifnro = "{? = call [fn_verifcomprob](?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procverifnro);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.setString(2, NroComprob);
            cs.setString(3, Ruc);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean InsertarComprobanteImport(EComprobante ecom) {
        String proceli = "{call sp_InsertarImportComprob(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";

        try {
         
            CallableStatement cs = cn.prepareCall(proceli);
            cs.setString(1, ecom.getNumComprobante());
            cs.setString(2,ecom.getFechaComprobante());
            cs.setString(3, ecom.getDocProveedor());
            cs.setString(4, ecom.getNomMoneda());
            cs.setDouble(5, ecom.getTCComprobante());
            cs.setDouble(6, ecom.getMontoTotal());
            cs.setDouble(7, ecom.getMontoIGV());
            cs.setDouble(8, ecom.getMontoTotalDOL());
            cs.setDouble(9, ecom.getMontoIGVDOL());
            cs.setString(10, SesionUsuario.misesion.getUsuario());
            cs.setString(11, ecom.getNomProveedor());
            cs.setString(12, ecom.getReferencia());
            cs.setString(13, ecom.getFormaPago());
            cs.setDouble(14, ecom.getMontoBase());
            cs.setDouble(15, ecom.getMontoBaseDOL());
            cs.setString(16, ecom.getTipoComprobanteRef());
            cs.setString(17, ecom.getComprobanteRef());
            cs.setString(18, ecom.getFechaRef());
            cs.setString(19, ecom.getTipoComprobante());
            cs.setString(20, ecom.getFechaContable());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

   private Date retornaFormatoDate(String Fecha) throws ParseException{
   SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
          Date date = formatter.parse(Fecha);
          return date;
   }

    @Override
    public ArrayList<EComprobante> ConsultaListaDetalle(int codprog) {
        ArrayList<EComprobante> liscom = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_ConsultaDetProgramacion](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setInt(1, codprog);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();
                tipo.setEstado(rs.getString(1));
                tipo.setTipoComprobante(rs.getString(2));
                tipo.setNumComprobante(rs.getString(3));
                tipo.setFechaComprobante(rs.getString(4));
                tipo.setDocProveedor(rs.getString(5));
                tipo.setRazonSocial(rs.getString(6));
                tipo.setGlosaComprante(rs.getString(7));
                tipo.setComprobanteSujeto(rs.getString(8));
                tipo.setTipoDetra(rs.getString(9));
                tipo.setFormaPago(rs.getString(10));
                tipo.setDestinoPago(rs.getString(11));
                tipo.setNomMoneda(rs.getString(12));
                tipo.setTCComprobante(rs.getDouble(13));
                tipo.setMontoBase(rs.getDouble(14));
                tipo.setMontoOtros(rs.getDouble(15));
                tipo.setMontoIGV(rs.getDouble(16));
                tipo.setMontoTotal(rs.getDouble(17));
                tipo.setMontoRetDet(rs.getDouble(18));
                tipo.setMontoPagar(rs.getDouble(19));
                tipo.setMontoBaseDOL(rs.getDouble(20));
                tipo.setMontoOtrosDOL(rs.getDouble(21));
                tipo.setMontoIGVDOL(rs.getDouble(22));
                tipo.setMontoTotalDOL(rs.getDouble(23));
                tipo.setMontoRetDetDOL(rs.getDouble(24));
                tipo.setMontoPagarDOL(rs.getDouble(25));
                tipo.setPorcentaje(rs.getDouble(26));
                tipo.setPorcIGV(rs.getDouble(27));
                tipo.setComprobanteRef(rs.getString(28));
                tipo.setRazonSocialRef(rs.getString(29));
                tipo.setFechaRef(rs.getString(30));
                tipo.setUsuCrea(rs.getString(31));
                tipo.setFechaCrea(rs.getString(32));
                tipo.setUsuCrea(rs.getString(33));
                tipo.setFechaCrea(rs.getString(34));
                tipo.setCodComprobante(rs.getInt(35));
                liscom.add(tipo);
            }
            return liscom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscom;
        }
    }

    @Override
    public ArrayList<EProveedor> SeleccionarProveedorxRuc(String ruc) {
        ArrayList<EProveedor> lstproveedor = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_SeleccionarProveedorxRuc](?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, ruc);
            ResultSet rss = cs.executeQuery();

            while (rss.next()) {

                EProveedor tipo = new EProveedor();
                tipo.setDocProveedor(rss.getString(1));
                tipo.setTipoDocProveedor(rss.getString(2));
                tipo.setRazonSocial(rss.getString(3));
                tipo.setEstado(rss.getInt(4));
                tipo.setDireccion(rss.getString(5));
                tipo.setTelefono(rss.getString(6));
                tipo.setContacto(rss.getString(7));
                tipo.setCorreoContacto(rss.getString(8));
                tipo.setNomBanco(rss.getString(9));
                tipo.setCTASoles(rss.getString(10));
                tipo.setCTADolares(rss.getString(11));
                tipo.setCCISoles(rss.getString(12));
                tipo.setCCIDolares(rss.getString(13));
                lstproveedor.add(tipo);
            }
            return lstproveedor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstproveedor;
        }
    }

    @Override
    public ArrayList<EComprobante> listaComprobante2() {
        ArrayList<EComprobante> liscom = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaBasicaComprobante2]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();

                tipo.setEstado(rs.getString(1));
                tipo.setTipoComprobante(rs.getString(2));
                tipo.setNumComprobante(rs.getString(3));
                tipo.setFechaComprobante(rs.getString(4));
                tipo.setDocProveedor(rs.getString(5));
                tipo.setRazonSocial(rs.getString(6));
                tipo.setGlosaComprante(rs.getString(7));
                tipo.setComprobanteSujeto(rs.getString(8));
                tipo.setTipoDetra(rs.getString(9));
                tipo.setFormaPago(rs.getString(10));
                tipo.setDestinoPago(rs.getString(11));
                tipo.setNomMoneda(rs.getString(12));
                tipo.setTCComprobante(rs.getDouble(13));
                tipo.setMontoBase(rs.getDouble(14));
                tipo.setMontoOtros(rs.getDouble(15));
                tipo.setMontoIGV(rs.getDouble(16));
                tipo.setMontoTotal(rs.getDouble(17));
                tipo.setMontoRetDet(rs.getDouble(18));
                tipo.setMontoPagar(rs.getDouble(19));
                tipo.setMontoBaseDOL(rs.getDouble(20));
                tipo.setMontoOtrosDOL(rs.getDouble(21));
                tipo.setMontoIGVDOL(rs.getDouble(22));
                tipo.setMontoTotalDOL(rs.getDouble(23));
                tipo.setMontoRetDetDOL(rs.getDouble(24));
                tipo.setMontoPagarDOL(rs.getDouble(25));
                tipo.setPorcentaje(rs.getDouble(26));
                tipo.setPorcIGV(rs.getDouble(27));
                tipo.setComprobanteRef(rs.getString(28));
                tipo.setRazonSocialRef(rs.getString(29));
                tipo.setFechaRef(rs.getString(30));
                tipo.setUsuCrea(rs.getString(31));
                tipo.setFechaCrea(rs.getString(32));
                tipo.setUsuCrea(rs.getString(33));
                tipo.setFechaCrea(rs.getString(34));
                tipo.setCodComprobante(rs.getInt(35));
                liscom.add(tipo);
            }
            System.out.println(liscom.size());
            return liscom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscom;
        }
    }

    @Override
    public ArrayList<EComprobante> listaComprobante3() {
        ArrayList<EComprobante> liscom = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaBasicaComprobante3]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();
                tipo.setEstado(rs.getString(1));
                tipo.setTipoComprobante(rs.getString(2));
                tipo.setNumComprobante(rs.getString(3));
                tipo.setFechaComprobante(rs.getString(4));
                tipo.setDocProveedor(rs.getString(5));
                tipo.setRazonSocial(rs.getString(6));
                tipo.setGlosaComprante(rs.getString(7));
                tipo.setComprobanteSujeto(rs.getString(8));
                tipo.setTipoDetra(rs.getString(9));
                tipo.setFormaPago(rs.getString(10));
                tipo.setDestinoPago(rs.getString(11));
                tipo.setNomMoneda(rs.getString(12));
                tipo.setTCComprobante(rs.getDouble(13));
                tipo.setMontoBase(rs.getDouble(14));
                tipo.setMontoOtros(rs.getDouble(15));
                tipo.setMontoIGV(rs.getDouble(16));
                tipo.setMontoTotal(rs.getDouble(17));
                tipo.setMontoRetDet(rs.getDouble(18));
                tipo.setMontoPagar(rs.getDouble(19));
                tipo.setMontoBaseDOL(rs.getDouble(20));
                tipo.setMontoOtrosDOL(rs.getDouble(21));
                tipo.setMontoIGVDOL(rs.getDouble(22));
                tipo.setMontoTotalDOL(rs.getDouble(23));
                tipo.setMontoRetDetDOL(rs.getDouble(24));
                tipo.setMontoPagarDOL(rs.getDouble(25));
                tipo.setPorcentaje(rs.getDouble(26));
                tipo.setPorcIGV(rs.getDouble(27));
                tipo.setComprobanteRef(rs.getString(28));
                tipo.setRazonSocialRef(rs.getString(29));
                tipo.setFechaRef(rs.getString(30));
                tipo.setUsuCrea(rs.getString(31));
                tipo.setFechaCrea(rs.getString(32));
                tipo.setUsuCrea(rs.getString(33));
                tipo.setFechaCrea(rs.getString(34));
                tipo.setCodComprobante(rs.getInt(35));
                liscom.add(tipo);
            }
            System.out.println(liscom.size());
            return liscom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscom;
        }
    }

    @Override
    public ArrayList<EComprobante> listaComprobante4() {
        ArrayList<EComprobante> liscom = new ArrayList<>();
        String prolispro = "{call [dbo].[usp_ListaBasicaComprobante4]()}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();
                tipo.setEstado(rs.getString(1));
                tipo.setTipoComprobante(rs.getString(2));
                tipo.setNumComprobante(rs.getString(3));
                tipo.setFechaComprobante(rs.getString(4));
                tipo.setDocProveedor(rs.getString(5));
                tipo.setRazonSocial(rs.getString(6));
                tipo.setGlosaComprante(rs.getString(7));
                tipo.setComprobanteSujeto(rs.getString(8));
                tipo.setTipoDetra(rs.getString(9));
                tipo.setFormaPago(rs.getString(10));
                tipo.setDestinoPago(rs.getString(11));
                tipo.setNomMoneda(rs.getString(12));
                tipo.setTCComprobante(rs.getDouble(13));
                tipo.setMontoBase(rs.getDouble(14));
                tipo.setMontoOtros(rs.getDouble(15));
                tipo.setMontoIGV(rs.getDouble(16));
                tipo.setMontoTotal(rs.getDouble(17));
                tipo.setMontoRetDet(rs.getDouble(18));
                tipo.setMontoPagar(rs.getDouble(19));
                tipo.setMontoBaseDOL(rs.getDouble(20));
                tipo.setMontoOtrosDOL(rs.getDouble(21));
                tipo.setMontoIGVDOL(rs.getDouble(22));
                tipo.setMontoTotalDOL(rs.getDouble(23));
                tipo.setMontoRetDetDOL(rs.getDouble(24));
                tipo.setMontoPagarDOL(rs.getDouble(25));
                tipo.setPorcentaje(rs.getDouble(26));
                tipo.setPorcIGV(rs.getDouble(27));
                tipo.setComprobanteRef(rs.getString(28));
                tipo.setRazonSocialRef(rs.getString(29));
                tipo.setFechaRef(rs.getString(30));
                tipo.setUsuCrea(rs.getString(31));
                tipo.setFechaCrea(rs.getString(32));
                tipo.setUsuCrea(rs.getString(33));
                tipo.setFechaCrea(rs.getString(34));
                tipo.setCodComprobante(rs.getInt(35));
                tipo.setNroCuentaContable(rs.getString(36));
                tipo.setCodDetraccion(rs.getString(37));
                liscom.add(tipo);
            }
            System.out.println(liscom.size());
            return liscom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscom;
        }
    }

    @Override
    public boolean ActualizarEstadoComprobante(String numcomprob) {
        String proceli = "{call sp_PasarAPendiente(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proceli);
            cs.setString(1, numcomprob);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EComprobante> ListaParaExportarAGP(String ini, String fin, String moneda) {
        ArrayList<EComprobante> liscom = new ArrayList<>();
        String prolispro = "{call [dbo].[sp_ListarParaExportarAGP](?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, ini);
            cs.setString(2, fin);
            cs.setString(3, moneda);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante tipo = new EComprobante();
                tipo.setFechaComprobante(rs.getString(1));
                tipo.setDocProveedor(rs.getString(2));
                tipo.setMontoPagar(rs.getDouble(3));
                tipo.setGlosaComprante(rs.getString(4));
                tipo.setNumComprobante(rs.getString(5));
                liscom.add(tipo);
            }
            System.out.println(liscom.size());
            return liscom;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscom;
        }
    }

    @Override
    public boolean InsertarComprobanteImportNew(EComprobante ecom) {

        String proceli = "{call sp_InsertarImportComprobante_nuevo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(proceli);
            cs.setString(1, ecom.getNumComprobante());
            cs.setString(2, ecom.getFechaComprobante());
            cs.setString(3, ecom.getDocProveedor());
            cs.setString(4, ecom.getNomMoneda());
            cs.setString(5, String.valueOf(ecom.getTCComprobante()));
            cs.setString(6,String.valueOf( ecom.getMontoTotal()));
            cs.setString(7, String.valueOf(ecom.getMontoIGV()));
            cs.setString(8, String.valueOf(ecom.getMontoTotalDOL()));
            cs.setString(9,String.valueOf( ecom.getMontoIGVDOL()));
            cs.setString(10, SesionUsuario.misesion.getUsuario());
            cs.setString(11, ecom.getNomProveedor());
            cs.setString(12, ecom.getReferencia());
            cs.setString(13, ecom.getFormaPago());
            cs.setString(14,String.valueOf( ecom.getMontoBase()));
            cs.setString(15,String.valueOf( ecom.getMontoBaseDOL()));
            cs.setString(16, ecom.getTipoComprobanteRef());
            cs.setString(17, ecom.getComprobanteRef().trim());
            cs.setString(18, ecom.getFechaRef());
            cs.setString(19, ecom.getTipoComprobante());
            cs.setString(20, ecom.getFechaContable());
            cs.setString(21, ecom.getCodDetraccion());
            cs.setString(22, ecom.getDescripcionBienServicio());
            cs.setString(23,String.valueOf(ecom.getPorcentaje()));
            cs.setString(24, ecom.getCuentaCorrienteBNDetraccion());
            cs.setString(25, ecom.getNroCuentaContable());
            cs.setInt(26, ecom.getFlagDetraccion());
            cs.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
