/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

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
import Capa_Logica.SesionUsuario;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDImportar extends ConexionBD implements IImportar{

    public BDImportar(String bd) {
        super(bd);
    }
    
    @Override
    public boolean InsertarRegistroCompraCab(int mes, int año) {
        String procInsertUsu = "{call sp_InsertarDataCompraCab(?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, mes);
            cs.setInt(2, año);
            cs.setString(3, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public int ValExiste(int mes, int año) {
      String procValExiste = "{? = call fn_verifperiodoexiste(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, mes);
            cs.setInt(3, año);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int CodRegistroCompra(int mes, int año) {
        String procValExiste = "{? = call fn_recuperarcodperiodo(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, mes);
            cs.setInt(3, año);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean InsertarRegistroCompraDet(EDataCompra objdata) {
        String procInsertUsu = "{call sp_InsertarDataCompraDet(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, objdata.getCodigo());
            cs.setString(2, objdata.getFechaEmision().replace("/", "-"));
            cs.setString(3, objdata.getFechaVencimiento().replace("/", "-"));
            cs.setString(4, objdata.getTipoComprobantePago());
            cs.setString(5, objdata.getSerieCodDepenAduanera());
            cs.setString(6, objdata.getAñoEmisionDua());
            cs.setString(7, objdata.getDocumentosEmitidos());
            cs.setString(8, objdata.getTipoDocuIdenProv());
            cs.setString(9, objdata.getNroDocIdentProv());
            cs.setString(10, objdata.getRazonSocialProv());
            if(objdata.getFila()==367){
                System.out.println("BASE: "+objdata.getGOBaseDisponible().replace(".","").replaceAll(",", "."));
                System.out.println("IGV: "+objdata.getGOIGV().replace(".","").replaceAll(",", "."));
                System.out.println("TOTAL: "+objdata.getImporteTotal().replace(".","").replaceAll(",", "."));
                
                System.out.println("GYGBD: "+objdata.getGYGBaseDisponible().replace(".","").replaceAll(",", "."));
                System.out.println("GYGIGV: "+objdata.getGYGIGV().replace(".","").replaceAll(",", "."));
                System.out.println("NGBD: "+objdata.getNGBaseDisponible().replace(".","").replaceAll(",", "."));
                System.out.println("NGIGV: "+objdata.getNGIGV().replace(".","").replaceAll(",", "."));
                System.out.println("ANG: "+objdata.getAdquisionesNG().replace(".","").replaceAll(",", "."));
                System.out.println("OTYC: "+objdata.getOtrosTribyCarg().replace(".","").replaceAll(",", "."));
                System.out.println("TIPOCAMBIO: "+objdata.getTipoDeCambio().replace(",",""));
            }
            cs.setDouble(11, Double.parseDouble(objdata.getGOBaseDisponible().replace(".","").replaceAll(",", ".")));
            cs.setDouble(12, Double.parseDouble(objdata.getGOIGV().replace(".","").replaceAll(",", ".")));
            cs.setDouble(13, Double.parseDouble(objdata.getGYGBaseDisponible().replace(".","").replaceAll(",", ".")));
            cs.setDouble(14, Double.parseDouble(objdata.getGYGIGV().replace(".","").replaceAll(",", ".")));
            cs.setDouble(15, Double.parseDouble(objdata.getNGBaseDisponible().replace(".","").replaceAll(",", ".")));
            cs.setDouble(16, Double.parseDouble(objdata.getNGIGV().replace(".","").replaceAll(",", ".")));
            cs.setDouble(17, Double.parseDouble(objdata.getAdquisionesNG().replace(".","").replaceAll(",", ".")));
            cs.setString(18, objdata.getISC());
            cs.setDouble(19, Double.parseDouble(objdata.getOtrosTribyCarg().replace(".","").replaceAll(",", ".")));
            cs.setDouble(20, Double.parseDouble(objdata.getImporteTotal().replace(".","").replaceAll(",", ".")));
            cs.setString(21, objdata.getNComprNoDomic());
            cs.setString(22, objdata.getNroConstanciaDetraccion());
            cs.setString(23, objdata.getFechaEmisionConsDetracc());
            cs.setDouble(24, Double.parseDouble(objdata.getTipoDeCambio().replace(",","")));
            cs.setString(25, objdata.getCMFecha());
            cs.setString(26, objdata.getCMTipo());
            cs.setString(27, objdata.getCMSerie());
            cs.setString(28, objdata.getCMNCPagoDoc());
            cs.setInt(29,objdata.getFila());
            cs.executeUpdate();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e+ " : "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean LimpiarRegistroCompraDet(int cod) {
        String procInsertUsu = "{call sp_LimpiarDataCompraDet(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean InsertarRegistroVentaCab(int mes, int año) {
        String procInsertUsu = "{call sp_InsertarDataVentaCab(?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, mes);
            cs.setInt(2, año);
            cs.setString(3, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public int ValVenExiste(int mes, int año) {
        String procValExiste = "{? = call fn_verifperiodovenexiste(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, mes);
            cs.setInt(3, año);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public int CodRegistroVenta(int mes, int año) {
        String procValExiste = "{? = call fn_recuperarcodperiodoven(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, mes);
            cs.setInt(3, año);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean InsertarRegistroVentaDet(EDataVenta objdata) {
        String procInsertUsu = "{call sp_InsertarDataVentaDet(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, objdata.getCodigo());
            cs.setString(2, objdata.getFechaEmision().replace("/", "-"));
            cs.setString(3, objdata.getFechaVencimiento().replace("/", "-"));
            cs.setString(4, objdata.getTipoCompPago());
            cs.setString(5, objdata.getNroSerieMaqReg());
            cs.setString(6, objdata.getNroComproPago());
            cs.setString(7, objdata.getTipoDocIdenClientt());
            cs.setString(8, objdata.getNroDocIdentCliente());
            cs.setString(9, objdata.getRazSocialCliente());
            cs.setDouble(10, Double.parseDouble(objdata.getValorFactExport().replace(".","").replace(",", ".")));
            cs.setDouble(11, Double.parseDouble(objdata.getBaseImpOpeGravada().replace(".","").replace(",", ".")));
            if (objdata.getImportTotalOpeExon().trim().length() > 0) {
                cs.setDouble(12, Double.parseDouble(objdata.getImportTotalOpeExon().replace(".","").replace(",", ".")));
            } else {
                cs.setDouble(12, Double.parseDouble("0.00"));
            }
            if (objdata.getImportTotalOpeInaf().trim().length() > 0) {
                cs.setDouble(13, Double.parseDouble(objdata.getImportTotalOpeInaf().replace(".","").replace(",", ".")));
            } else {
                cs.setDouble(13, Double.parseDouble("0.00"));
            }
            cs.setDouble(14, Double.parseDouble(objdata.getISC().replace(".","").replace(",", ".")));
            cs.setDouble(15, Double.parseDouble(objdata.getIGVyIPM().replace(".","").replace(",", ".")));
            cs.setDouble(16, Double.parseDouble(objdata.getOtrosTributos().replace(".","").replace(",", ".")));
            cs.setDouble(17, Double.parseDouble(objdata.getImporteTotal().replace(".","").replace(",", ".")));
            if (objdata.getTipoCambio().trim().length() > 0) {
                cs.setDouble(18, Double.parseDouble(objdata.getTipoCambio().replace(",", ".")));
            } else {
                cs.setDouble(18, Double.parseDouble("0.00"));
            }
            cs.setString(19, objdata.getFechaDocMod());
            cs.setString(20, objdata.getTipoDocMod());
            cs.setString(21, objdata.getSerieDocMod());
            cs.setString(22, objdata.getNroDocMod());
            cs.setInt(23, objdata.getCorrelativo());
            cs.executeUpdate();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e+ " : "+e.getMessage());
            return false;
        }
    }

    @Override
    public boolean LimpiarRegistroVentaDet(int cod) {
        String procInsertUsu = "{call sp_LimpiarDataVentaDet(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EPeriodo> ListaPeriodoCompra() {
        ArrayList<EPeriodo> lstperiodo = new ArrayList<>();
        String proclperiodo = "{call sp_LCPeriodoCompra()}";
        try {
            CallableStatement cs = cn.prepareCall(proclperiodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                EPeriodo per = new EPeriodo();
                per.setCodigo(rs.getInt(1));
                per.setMes(rs.getInt(2));
                per.setAño(rs.getInt(3));
                per.setMesChar(rs.getString(4));
                lstperiodo.add(per);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstperiodo;
    }

  
    
    
    
    @Override
    public ArrayList<EPeriodo> ListaPeriodoVenta() {
         ArrayList<EPeriodo> lstperiodo = new ArrayList<>();
        String proclperiodo = "{call sp_LCPeriodoVenta()}";
        try {
            CallableStatement cs = cn.prepareCall(proclperiodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                EPeriodo per = new EPeriodo();
                per.setCodigo(rs.getInt(1));
                per.setMes(rs.getInt(2));
                per.setAño(rs.getInt(3));
                per.setMesChar(rs.getString(4));
                lstperiodo.add(per);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstperiodo;
    }

    @Override
    public ArrayList<EDataCompra> ListaCompra(int cod) {
        ArrayList<EDataCompra> lstcompra = new ArrayList<>();
        String proclcompra = "{call sp_ListarDetalleCompras(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclcompra);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataCompra data=new EDataCompra();
                data.setFila(rs.getInt(1));
                data.setFechaEmision(rs.getString(2));
                data.setFechaVencimiento(rs.getString(3));
                data.setTipoComprobantePago(rs.getString(4));
                data.setSerieCodDepenAduanera(rs.getString(5));
                data.setAñoEmisionDua(rs.getString(6));
                data.setDocumentosEmitidos(rs.getString(7));
                data.setTipoDocuIdenProv(rs.getString(8));
                data.setNroDocIdentProv(rs.getString(9));
                data.setRazonSocialProv(rs.getString(10));
                data.setGOBaseDisponible(rs.getString(11));
                data.setGOIGV(rs.getString(12));
                data.setGYGBaseDisponible(rs.getString(13));
                data.setGYGIGV(rs.getString(14));
                data.setNGBaseDisponible(rs.getString(15));
                data.setNGIGV(rs.getString(16));
                data.setAdquisionesNG(rs.getString(17));
                data.setISC(rs.getString(18));
                data.setOtrosTribyCarg(rs.getString(19));
                data.setImporteTotal(rs.getString(20));
                data.setNComprNoDomic(rs.getString(21));
                data.setNroConstanciaDetraccion(rs.getString(22));
                data.setFechaEmisionConsDetracc(rs.getString(23));
                data.setTipoDeCambio(rs.getString(24));
                data.setCMFecha(rs.getString(25));
                data.setCMTipo(rs.getString(26));
                data.setCMSerie(rs.getString(27));
                data.setCMNCPagoDoc(rs.getString(28));
                lstcompra.add(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstcompra;
    }

    @Override
    public ArrayList<EDataVenta> ListaVenta(int cod) {
        ArrayList<EDataVenta> lstventa = new ArrayList<>();
        String proclventa = "{call sp_ListarDetalleVentas(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclventa);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataVenta data = new EDataVenta();
                data.setCorrelativo(rs.getInt(1));
                data.setFechaEmision(rs.getString(2));
                data.setFechaVencimiento(rs.getString(3));
                data.setTipoCompPago(rs.getString(4));
                data.setNroSerieMaqReg(rs.getString(5));
                data.setNroComproPago(rs.getString(6));
                data.setTipoDocIdenClientt(rs.getString(7));
                data.setNroDocIdentCliente(rs.getString(8));
                data.setRazSocialCliente(rs.getString(9));
                data.setValorFactExport(rs.getString(10));
                data.setBaseImpOpeGravada(rs.getString(11));
                data.setImportTotalOpeExon(rs.getString(12));
                data.setImportTotalOpeInaf(rs.getString(13));
                data.setISC(rs.getString(14));
                data.setIGVyIPM(rs.getString(15));
                data.setOtrosTributos(rs.getString(16));
                data.setImporteTotal(rs.getString(17));
                data.setTipoCambio(rs.getString(18));
                data.setTipoDocMod(rs.getString(19));
                data.setFechaDocMod(rs.getString(20));
                data.setSerieDocMod(rs.getString(21));
                data.setNroDocMod(rs.getString(22));
                lstventa.add(data);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstventa;
    }

    @Override
    public boolean InsertarRegCompraFormat(EDataFormatCompras data) {
        String procInsertCF = "{call sp_InsertarDataComprasFormat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertCF);
            cs.setInt(1, data.getCodigo());
            cs.setString(2, data.getCampo1());
            cs.setString(3, data.getCampo2());
            cs.setString(4, data.getCampo3());
            cs.setString(5, data.getCampo4());
            cs.setString(6, data.getCampo5());
            cs.setString(7, data.getCampo6());
            cs.setString(8, data.getCampo7());
            cs.setString(9, data.getCampo8());
            cs.setString(10, data.getCampo9());
            cs.setString(11, data.getCampo10());
            cs.setString(12, data.getCampo11());
            cs.setString(13, data.getCampo12());
            cs.setString(14, data.getCampo13());
            cs.setString(15, data.getCampo14());
            cs.setString(16, data.getCampo15());
            cs.setString(17, data.getCampo16());
            cs.setString(18, data.getCampo17());
            cs.setString(19, data.getCampo18());
            cs.setString(20, data.getCampo19());
            cs.setString(21, data.getCampo20());
            cs.setString(22, data.getCampo21());
            cs.setString(23, data.getCampo22());
            cs.setString(24, data.getCampo23());
            cs.setString(25, data.getCampo24());
            cs.setString(26, data.getCampo25());
            cs.setString(27, data.getCampo26());
            cs.setString(28, data.getCampo27());
            cs.setString(29, data.getCampo28());
            cs.setString(30, data.getCampo29());
            cs.setString(31, data.getCampo30());
            cs.setString(32, data.getCampo31());
            cs.setString(33, data.getCampo32());
            cs.setString(34, data.getCampo33());
            cs.setString(35, data.getCampo34());
            cs.setString(36, data.getCampo35());
            cs.setString(37, data.getCampo36());
            cs.setString(38, data.getCampo37());
            cs.setString(39, data.getCampo38());
            cs.setString(40, data.getCampo39());
            cs.setString(41, data.getCampo40());
            cs.setString(42, data.getCampo41());
            cs.setString(43, data.getCampoOtros());
            cs.setString(44, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public int DataAnteriorFormatoCompras(int cod) {
        String procValExiste = "{? = call fn_dataanteriorformateadacompras(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, cod);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean LimpiarRegCompraFormat(int cod) {
        String procInsertUsu = "{call sp_LimpiarComprasFormateada(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EDataFormatCompras> ListaDataFormatCompras(int cod) {
        ArrayList<EDataFormatCompras> lstcompras = new ArrayList<>();
        String proclcompras = "{call sp_BuscarDataFormatCompras(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclcompras);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
               EDataFormatCompras fcompras=new EDataFormatCompras();
               fcompras.setCodigo(rs.getInt(1));
               fcompras.setCampo1(rs.getString(2));
               fcompras.setCampo2(rs.getString(3));
               fcompras.setCampo3(rs.getString(4));
               fcompras.setCampo4(rs.getString(5));
               fcompras.setCampo5(rs.getString(6));
               fcompras.setCampo6(rs.getString(7));
               fcompras.setCampo7(rs.getString(8));
               fcompras.setCampo8(rs.getString(9));
               fcompras.setCampo9(rs.getString(10));
               fcompras.setCampo10(rs.getString(11));
               fcompras.setCampo11(rs.getString(12));
               fcompras.setCampo12(rs.getString(13));
               fcompras.setCampo13(rs.getString(14));
               fcompras.setCampo14(rs.getString(15));
               fcompras.setCampo15(rs.getString(16));
               fcompras.setCampo16(rs.getString(17));
               fcompras.setCampo17(rs.getString(18));
               fcompras.setCampo18(rs.getString(19));
               fcompras.setCampo19(rs.getString(20));
               fcompras.setCampo20(rs.getString(21));
               fcompras.setCampo21(rs.getString(22));
               fcompras.setCampo22(rs.getString(23));
               fcompras.setCampo23(rs.getString(24));
               fcompras.setCampo24(rs.getString(25));
               fcompras.setCampo25(rs.getString(26));
               fcompras.setCampo26(rs.getString(27));
               fcompras.setCampo27(rs.getString(28));
               fcompras.setCampo28(rs.getString(29));
               fcompras.setCampo29(rs.getString(30));
               fcompras.setCampo30(rs.getString(31));
               fcompras.setCampo31(rs.getString(32));
               fcompras.setCampo32(rs.getString(33));
               fcompras.setCampo33(rs.getString(34));
               fcompras.setCampo34(rs.getString(39));
               fcompras.setCampo35(rs.getString(40));
               fcompras.setCampo36(rs.getString(41));
               fcompras.setCampo37(rs.getString(42));
               fcompras.setCampo38(rs.getString(42));
               fcompras.setCampo39(rs.getString(43));
               fcompras.setCampo40(rs.getString(44));
               fcompras.setCampo41(rs.getString(45));
               fcompras.setCampoOtros(rs.getString(35));
               lstcompras.add(fcompras);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstcompras;
    }

    @Override
    public boolean InsertarRegVentaFormat(EDataFormatVentas data) {
        String procInsertCF = "{call sp_InsertarDataVentasFormat(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                + "?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertCF);
            cs.setInt(1, data.getCodigo());
            cs.setString(2, data.getCampo1());
            cs.setString(3, data.getCampo2());
            cs.setString(4, data.getCampo3());
            cs.setString(5, data.getCampo4());
            cs.setString(6, data.getCampo5());
            cs.setString(7, data.getCampo6());
            cs.setString(8, data.getCampo7());
            cs.setString(9, data.getCampo8());
            cs.setString(10, data.getCampo9());
            cs.setString(11, data.getCampo10());
            cs.setString(12, data.getCampo11());
            cs.setString(13, data.getCampo12());
            cs.setString(14, data.getCampo13());
            cs.setString(15, data.getCampo14());
            cs.setString(16, data.getCampo15());
            cs.setString(17, data.getCampo16());
            cs.setString(18, data.getCampo17());
            cs.setString(19, data.getCampo18());
            cs.setString(20, data.getCampo19());
            cs.setString(21, data.getCampo20());
            cs.setString(22, data.getCampo21());
            cs.setString(23, data.getCampo22());
            cs.setString(24, data.getCampo23());
            cs.setString(25, data.getCampo24());
            cs.setString(26, data.getCampo25());
            cs.setString(27, data.getCampo26());
            cs.setString(28, data.getCampo27());
            cs.setString(29, data.getCampo28());
            cs.setString(30, data.getCampo29());
            cs.setString(31, data.getCampoOtros());
            cs.setString(32, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public int DataAnteriorFormatoVentas(int cod) {
        String procValExiste = "{? = call fn_dataanteriorformateadaventas(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, cod);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean LimpiarRegVentaFormat(int cod) {
        String procInsertUsu = "{call sp_LimpiarVentasFormateada(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setInt(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EDataFormatVentas> ListaDataFormatVentas(int cod) {
        ArrayList<EDataFormatVentas> lstventas = new ArrayList<>();
        String proclventas = "{call sp_BuscarDataFormatVentas(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclventas);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
               EDataFormatVentas fcompras=new EDataFormatVentas();
               fcompras.setCodigo(rs.getInt(1));
               fcompras.setCampo1(rs.getString(2));
               fcompras.setCampo2(rs.getString(3));
               fcompras.setCampo3(rs.getString(4));
               fcompras.setCampo4(rs.getString(5));
               fcompras.setCampo5(rs.getString(6));
               fcompras.setCampo6(rs.getString(7));
               fcompras.setCampo7(rs.getString(8));
               fcompras.setCampo8(rs.getString(9));
               fcompras.setCampo9(rs.getString(10));
               fcompras.setCampo10(rs.getString(11));
               fcompras.setCampo11(rs.getString(12));
               fcompras.setCampo12(rs.getString(13));
               fcompras.setCampo13(rs.getString(14));
               fcompras.setCampo14(rs.getString(15));
               fcompras.setCampo15(rs.getString(16));
               fcompras.setCampo16(rs.getString(17));
               fcompras.setCampo17(rs.getString(18));
               fcompras.setCampo18(rs.getString(19));
               fcompras.setCampo19(rs.getString(20));
               fcompras.setCampo20(rs.getString(21));
               fcompras.setCampo21(rs.getString(22));
               fcompras.setCampo22(rs.getString(23));
               fcompras.setCampo23(rs.getString(24));
               fcompras.setCampo24(rs.getString(25));
               fcompras.setCampo25(rs.getString(26));
               fcompras.setCampo26(rs.getString(27));
               fcompras.setCampo27(rs.getString(28));
               fcompras.setCampo28(rs.getString(29));
               fcompras.setCampo29(rs.getString(30));
               fcompras.setCampoOtros(rs.getString(31));
               lstventas.add(fcompras);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstventas;
    }

    @Override
    public ArrayList<EPeriodo> ListaPeriodoAmbos() {
            ArrayList<EPeriodo> lstperiodo = new ArrayList<>();
        String proclperiodo = "{call sp_ListaPeriodosAmbos()}";
        try {
            CallableStatement cs = cn.prepareCall(proclperiodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                EPeriodo per = new EPeriodo();
                per.setMes(rs.getInt(1));
                per.setAño(rs.getInt(2));
                per.setMesChar(rs.getString(3));
                lstperiodo.add(per);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstperiodo;
    }

    @Override
    public boolean InsertarDataExportCompras(int cod, int nroreg) {
         String procInsertExp = "{call sp_InsertarExpCompras(?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertExp);
            cs.setInt(1, cod);
            cs.setInt(2, nroreg);
            cs.setString(3, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean InsertarDataExportVentas(int cod, int nroreg) {
         String procInsertExp = "{call sp_InsertarExpVentas(?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertExp);
            cs.setInt(1, cod);
            cs.setInt(2, nroreg);
            cs.setString(3, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaCompras(int cod) {
        ArrayList<EDataConsultaCompras> lstconscompras = new ArrayList<>();
        String procconscompras = "{call sp_ConsultaComprasCab(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procconscompras);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataConsultaCompras cons = new EDataConsultaCompras();
                cons.setCodigo(rs.getInt(1));
                cons.setPeriodo(rs.getString(2));
                cons.setFecha(rs.getString(3));
                cons.setRegistrados(rs.getInt(4));
                cons.setFormateados(rs.getInt(5));
                cons.setFechaExportacion(rs.getString(6));
                cons.setExportados(rs.getInt(7));
                lstconscompras.add(cons);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstconscompras;
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaVentas(int cod) {
        ArrayList<EDataConsultaCompras> lstconsventas = new ArrayList<>();
        String procconsventas = "{call sp_ConsultaVentasCab(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procconsventas);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataConsultaCompras cons = new EDataConsultaCompras();
                cons.setCodigo(rs.getInt(1));
                cons.setPeriodo(rs.getString(2));
                cons.setFecha(rs.getString(3));
                cons.setRegistrados(rs.getInt(4));
                cons.setFormateados(rs.getInt(5));
                cons.setFechaExportacion(rs.getString(6));
                cons.setExportados(rs.getInt(7));
                lstconsventas.add(cons);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstconsventas;
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaRangCompras(String inicio, String fin) {
        ArrayList<EDataConsultaCompras> lstconscompras = new ArrayList<>();
        String procconscompras = "{call sp_ConsultaComprasCabRang(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procconscompras);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataConsultaCompras cons = new EDataConsultaCompras();
                cons.setCodigo(rs.getInt(2));
                cons.setPeriodo(rs.getString(3));
                cons.setFecha(rs.getString(4));
                cons.setRegistrados(rs.getInt(5));
                cons.setFormateados(rs.getInt(6));
                cons.setFechaExportacion(rs.getString(7));
                cons.setExportados(rs.getInt(8));
                lstconscompras.add(cons);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstconscompras;
    }

    @Override
    public ArrayList<EDataConsultaCompras> ConsultaRangVentas(String inicio, String fin) {
        ArrayList<EDataConsultaCompras> lstconsventas = new ArrayList<>();
        String procconsventas = "{call sp_ConsultaVentasCabRang(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procconsventas);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataConsultaCompras cons = new EDataConsultaCompras();
                cons.setCodigo(rs.getInt(2));
                cons.setPeriodo(rs.getString(3));
                cons.setFecha(rs.getString(4));
                cons.setRegistrados(rs.getInt(5));
                cons.setFormateados(rs.getInt(6));
                cons.setFechaExportacion(rs.getString(7));
                cons.setExportados(rs.getInt(8));
                lstconsventas.add(cons);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstconsventas;
    }

    @Override
    public boolean InsertarDataRetencionDetalle(EDataRetencion det) {
        String procInsertRetenc = "{call sp_InsertarDataRetencionDetalle(?,?,?,?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertRetenc);
            cs.setInt(1, 1);
            cs.setInt(2, det.getCorrelativo());
            cs.setString(3, det.getRazonSocial().trim());
            cs.setString(4, det.getRUC().trim());
            cs.setString(5, det.getFecha().trim());
            cs.setString(6, det.getDoc().trim());
            cs.setDouble(7, Double.valueOf(det.getImporte()));
            cs.setString(8, det.getPorcentRetenc());
            cs.setDouble(9, Double.valueOf(det.getRetencion()));
            cs.setDouble(10, Double.valueOf(det.getSUNAT()));
            cs.setDouble(11, Double.valueOf(det.getNeto()));
            cs.setString(12, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EDataRetencion> ListarRetencionDetalle(int cod) {
        ArrayList<EDataRetencion> lstdetret = new ArrayList<>();
        String proclreten = "{call sp_ListarDetalleRetencion(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclreten);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataRetencion dr = new EDataRetencion();
                dr.setCodigo(rs.getInt(1));
                dr.setCorrelativo(rs.getInt(2));
                dr.setRazonSocial(rs.getString(3));
                dr.setRUC(rs.getString(4));
                dr.setFecha(rs.getString(5));
                dr.setDoc(rs.getString(6));
                dr.setImporte(rs.getString(7));
                dr.setPorcentRetenc(rs.getString(8));
                dr.setRetencion(rs.getString(9));
                dr.setSUNAT(rs.getString(10));
                dr.setNeto(rs.getString(11));
                dr.setNroComprobanteReten(rs.getString(12));
                lstdetret.add(dr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstdetret;
    }

    @Override
    public ArrayList<EDetraccion> ListaDetraccion() {
          ArrayList<EDetraccion> lstdetra=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaDetraccion]()}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDetraccion tipo=new EDetraccion();
                tipo.setCodDetraccion(rs.getString(1));
                tipo.setTipoDetra(rs.getString(2));
                tipo.setPorcentajeDetra(rs.getDouble(3));
                tipo.setUsuarioCrea(rs.getString(4));
                tipo.setFechaCrea(rs.getString(5));
                tipo.setUsuarioModi(rs.getString(6));
                tipo.setFechaModi(rs.getString(7));
                lstdetra.add(tipo);
            }
            return lstdetra;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstdetra;
        }
    }

    @Override
    public ArrayList<EDataRetencion> ListaDetalle(int cod) {
        ArrayList<EDataRetencion> lstdetret = new ArrayList<>();
        String proclreten = "{call sp_ListaDetalleRetencion(?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclreten);
            cs.setInt(1, cod);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataRetencion dr = new EDataRetencion();
                dr.setRazonSocial(rs.getString(1));
                dr.setRUC(rs.getString(2));
                dr.setFecha(rs.getString(3));
                dr.setDoc(rs.getString(4));
                dr.setImporte(rs.getString(5));
                dr.setPorcentRetenc(rs.getString(8));
                dr.setSUNAT(rs.getString(7));
                dr.setNeto(rs.getString(6));
                dr.setTipoComprobante(rs.getString(9));
                lstdetret.add(dr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstdetret;
    }

    @Override
    public ArrayList<EDataRetencion> ConsultaRetencionDetalle(String ruc, String fecini, String fecfin) {
        ArrayList<EDataRetencion> lstdetret = new ArrayList<>();
        String proclreten = "{call sp_ConsultarDetalleRetencion(?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclreten);
            cs.setString(1, ruc);
            cs.setString(2, fecini);
            cs.setString(3, fecfin);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataRetencion dr = new EDataRetencion();
                dr.setCodigo(rs.getInt(1));
                dr.setCorrelativo(rs.getInt(2));
                dr.setRazonSocial(rs.getString(3));
                dr.setRUC(rs.getString(4));
                dr.setFecha(rs.getString(5));
                dr.setDoc(rs.getString(6));
                dr.setImporte(rs.getString(7));
                dr.setPorcentRetenc(rs.getString(8));
                dr.setRetencion(rs.getString(9));
                dr.setSUNAT(rs.getString(10));
                dr.setNeto(rs.getString(11));
                dr.setNroComprobanteReten(rs.getString(12));
                dr.setFechaRetencion(rs.getString(13));
                lstdetret.add(dr);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstdetret;
    }

    @Override
    public ArrayList<EFormatoDAOTVentas> ListarDaotVentas() {
         ArrayList<EFormatoDAOTVentas> lstdaot = new ArrayList<>();
         //NroDocIdentCliente, RazonSocial, TipoRuc, Monto
        String proclperiodo = "{call sp_ListarDAOTVentas()}";
        try {
            CallableStatement cs = cn.prepareCall(proclperiodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                EFormatoDAOTVentas objdaot=new EFormatoDAOTVentas();
                objdaot.setFila(rs.getInt(2));
                objdaot.setTipoDocSantander("6");
                objdaot.setRucSantander("20550226589");
                objdaot.setAño(2015);
                objdaot.setRucCliente(rs.getString(1).trim());
                objdaot.setTipoPersona(rs.getString(4).trim());
                if(objdaot.getTipoPersona().trim().equalsIgnoreCase("06")){
                    objdaot.setRazonSocial(rs.getString(3).trim().toUpperCase());
                    objdaot.setNombresSinSeparar("|||");
                }else{
                    objdaot.setRazonSocial("");
                    objdaot.setNombresSinSeparar(rs.getString(3).toUpperCase().trim().replace(" ", "|"));
                }
                objdaot.setMonto(rs.getInt(5));
                lstdaot.add(objdaot);
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstdaot;
    }

    @Override
    public boolean GrabarVentaInafecto(EDataVenta objvent) {
        String procInsertUsu = "{call sp_AgregarInafectos(?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertUsu);
            cs.setString(1, objvent.getRazSocialCliente());
            cs.setString(2, objvent.getNroDocIdentCliente());
            cs.setDouble(3, Double.valueOf(objvent.getBaseImpOpeGravada()));
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e + " : " + e.getMessage());
            return false;
        }
    }

   
    
}

