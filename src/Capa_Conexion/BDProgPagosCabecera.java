/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

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
import Capa_Logica.SesionUsuario;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDProgPagosCabecera extends ConexionBD implements IProgPagosCabecera{

       public BDProgPagosCabecera(String bd) {
        super(bd);
    }
    
    
    @Override
    public boolean InsertarPrgPagos(EProgPagosCabecera objpro) {
        String procInsertpro="{call [dbo].[sp_InsertarProgPagosCabecera](?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getEstado_progpagos());
             cs.setString(2, objpro.getUsucrea());
             cs.setString(3, objpro.getBanco());
             cs.setString(4, objpro.getMoneda());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean InsertarPrgPagosDeta(EProgPagosDetalle objpro) {
        String procInsertpro="{call [dbo].[sp_AgregarProgPagosDetalle](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
//             
//             cs.setInt(1, objpro.getCodComprobante());
             cs.setInt(1,objpro.getCodprogramacion());
             cs.setInt(2,objpro.getCod_comprobante());
             
             cs.setString(3, objpro.getTipoComprobante());
             cs.setString(4, objpro.getNumComprobante());
             cs.setString(5, objpro.getFechaComprobante());
             cs.setString(6, objpro.getDocProveedor());
             cs.setString(7, objpro.getGlosaComprobante());
             cs.setString(8, objpro.getComprobanteSujeto());
             cs.setString(9, objpro.getCodDetraccion());
             cs.setString(10, objpro.getFormaPago());
             cs.setString(11, objpro.getDestinoPago());
             cs.setInt(12, objpro.getCodMoneda());
             cs.setDouble(13, objpro.getTCComprobante());
             cs.setDouble(14, objpro.getMontoBase());
             cs.setDouble(15, objpro.getMontoOtros());
             cs.setDouble(16, objpro.getMontoIGV());
             cs.setDouble(17, objpro.getMontoTotal());
             cs.setDouble(18, objpro.getMontoRetDet());
             cs.setDouble(19, objpro.getMontoPagar());
             cs.setDouble(20, objpro.getMontoBaseDOL());
             cs.setDouble(21, objpro.getMontoOtrosDOL());
             cs.setDouble(22, objpro.getMontoIGVDOL());
             cs.setDouble(23, objpro.getMontoTotalDOL());
             cs.setDouble(24, objpro.getMontoRetDetDOL());
             cs.setDouble(25, objpro.getMontoPagarDOL());
             cs.setDouble(26, objpro.getPorcentaje());
             cs.setDouble(27, objpro.getPorcIGV());  
             cs.setString(28, objpro.getComprobanteRef());
             cs.setString(29, objpro.getRazonSocialRef());
             cs.setString(30, objpro.getFechaRef());
             cs.setString(31, objpro.getUsuCrea());
//             }
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public ArrayList<EProgPagosCabecera> MaxCodigo() {
           ArrayList<EProgPagosCabecera> lstMAX=new ArrayList<>();
        String procldetra = "{call [dbo].[MAXCODIGO]()}";
        try {
            System.out.println("ENTRO");
            CallableStatement cs = cn.prepareCall(procldetra);
            rs = cs.executeQuery();
            while (rs.next()) {
                EProgPagosCabecera tipo=new EProgPagosCabecera();
                tipo.setCod_programacion(rs.getInt(1));
                System.out.println(rs.getInt(1));
        
                lstMAX.add(tipo);
            }
            return lstMAX;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstMAX;
        }
    }

    @Override
    public boolean CambioEstadoCompro(EComprobante objpro) {
          String procInsertpro="{call CambioEstadoCompro(?,?)}";
         try {
              CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getEstado());
             cs.setInt(2, objpro.getCodComprobante()); 
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
         
    
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListarCabeceraComprobante() {
          ArrayList<EProgPagosCabecera> lstcabecera=new ArrayList<>();
        String procldetra = "{call ListarCabeceraComprobante()}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            rs = cs.executeQuery();
            while (rs.next()) {
                EProgPagosCabecera tipo=new EProgPagosCabecera();
                tipo.setCod_programacion(rs.getInt(1));
                tipo.setEstado_progpagos(rs.getString(2));
                tipo.setUsucrea(rs.getString(3));
                tipo.setFeccrea(rs.getString(4));
                tipo.setUsuModi(rs.getString(5));
                tipo.setFecModi(rs.getString(6));
                
        
                lstcabecera.add(tipo);
            }
            return lstcabecera;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstcabecera;
        }
    }

    @Override
    public boolean eliminarProgramacion(int cod) {
          String proceli="{call [Esp_EliminarProgramacion](?)}";
        try {
            CallableStatement cs=cn.prepareCall(proceli);
            cs.setInt(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListaxFechaCabecera(String inicio, String fin) {
          String prolisServCord = "{call [ListarxFechaCabecera](?,?)}";
        ArrayList<EProgPagosCabecera> lstcabecera = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {           
                EProgPagosCabecera tipo=new EProgPagosCabecera();
                tipo.setCod_programacion(rss.getInt(1));
                tipo.setEstado_progpagos(rss.getString(2));
                tipo.setBanco(rss.getString(3));
                tipo.setMoneda(rss.getString(4));
                tipo.setNumCuenta(rss.getString(5));
                tipo.setFecha(rss.getString(6));
                lstcabecera.add(tipo);
            }            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return lstcabecera;
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListaxRazonSocialCabecera(String estado) {
         String prolisServCord = "{call [ListarxRazonSocialCabecera](?)}";
//         pc.cod_programacion, pc.estado_progpagos, pb.NomBanco, pc.Moneda,
//pc.NroCtaOrigen, convert(varchar(15),pc.Fecha,105)
        ArrayList<EProgPagosCabecera> lstcabecera = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            cs.setString(1, estado);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {                
                EProgPagosCabecera tipo=new EProgPagosCabecera();
                tipo.setCod_programacion(rss.getInt(1));
                tipo.setEstado_progpagos(rss.getString(2));
                tipo.setBanco(rss.getString(3));
                tipo.setMoneda(rss.getString(4));
                tipo.setNumCuenta(rss.getString(5));
                tipo.setFecha(rss.getString(6));
                lstcabecera.add(tipo);
            }            
            
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return lstcabecera;
    }

    @Override
    public ArrayList<EProgPagosDetalle> ListaDetalle() {
          ArrayList<EProgPagosDetalle> lstdetalle=new ArrayList<>();
        String procldetra = "{call [ListaProgramacionDetalle]()}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            rs = cs.executeQuery();
            while (rs.next()) {
                EProgPagosDetalle tipo=new EProgPagosDetalle();
                
                tipo.setCod_programacion(rs.getInt(1));
                tipo.setCod_programacion(rs.getInt(2));
                tipo.setCod_comprobante(rs.getInt(3));
                tipo.setTipoComprobante(rs.getString(4));
                tipo.setNumComprobante(rs.getString(5));
                tipo.setFechaComprobante(rs.getString(6));
                tipo.setDocProveedor(rs.getString(6));
                tipo.setGlosaComprobante(rs.getString(6));
                tipo.setComprobanteSujeto(rs.getString(6));
                tipo.setCodDetraccion(rs.getString(6));
                tipo.setFormaPago(rs.getString(6));
                tipo.setDestinoPago(rs.getString(6));
                tipo.setCodMoneda(rs.getInt(6));
                tipo.setTCComprobante(rs.getDouble(6));
                tipo.setMontoBase(rs.getDouble(6));
                tipo.setMontoOtros(rs.getDouble(6));
                tipo.setMontoIGV(rs.getDouble(6));
                tipo.setMontoTotal(rs.getDouble(6));
                tipo.setMontoRetDet(rs.getDouble(6));
                tipo.setMontoPagar(rs.getDouble(6));
                tipo.setPorcentaje(rs.getDouble(6));
                tipo.setPorcIGV(rs.getDouble(6));
                tipo.setMontoBaseDOL(rs.getDouble(6));
                tipo.setMontoOtrosDOL(rs.getDouble(6));
                tipo.setMontoIGVDOL(rs.getDouble(6));
                tipo.setMontoTotalDOL(rs.getDouble(6));
                tipo.setMontoRetDetDOL(rs.getDouble(6));
                tipo.setMontoPagarDOL(rs.getDouble(6));
                
                tipo.setComprobanteRef(rs.getString(6)); 
                tipo.setRazonSocialRef(rs.getString(6)); 
                tipo.setFechaRef(rs.getString(6)); 
                tipo.setUsuCrea(rs.getString(6)); 
                tipo.setFechaCrea(rs.getString(6)); 
                
                
                
        
                lstdetalle.add(tipo);
            }
            return lstdetalle;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstdetalle;
        }
    }

    @Override
    public int validarctabancaria(String banco, String moneda, String DocProveedor) {
        String procValExiste = "{? = call fn_verificarctabancaria(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setString(2, moneda);
            cs.setString(3,DocProveedor);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean insertarProgDetalle(EDetProgramPagos edet, int codprog) {
           String insertdetalle="{call [sp_InsertarDetallePrg](?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs=cn.prepareCall(insertdetalle);
            cs.setInt(1, codprog);
            cs.setInt(2, edet.getCodcompr());
            cs.setString(3, edet.getEstado());
            cs.setString(4, SesionUsuario.misesion.getUsuario());
            cs.setString(5, edet.getBanco());
            cs.setString(6, edet.getMoneda());
            cs.setString(7, edet.getCtaBancaria());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<EConsolidado> lstConsolidado(int codprog) {
           ArrayList<EConsolidado> lstcons=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_CrearConsolidado](?)}";
        try {
            
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1,codprog);
            rs = cs.executeQuery();
            while (rs.next()) {
                EConsolidado econ=new EConsolidado();
                econ.setCodProgramacion(codprog);
                econ.setDocProv(rs.getString(1));
                econ.setRazonSocial(rs.getString(2));
                econ.setMontosoles(rs.getDouble(3));
                econ.setMontodolares(rs.getDouble(4));
                econ.setTipoComprob(rs.getString(5));
                lstcons.add(econ);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcons;
    }

    @Override
    public boolean insertarConsolidado(EConsolidado econ) {
           String insertdetalle="{call [sp_InsertarConsolidado](?,?,?,?,?,?)}";
        try {
            CallableStatement cs=cn.prepareCall(insertdetalle);
            cs.setInt(1, econ.getCodProgramacion());
            cs.setString(2, econ.getDocProv());
            cs.setString(3, econ.getRazonSocial());
            cs.setDouble(4, econ.getMontosoles());
            cs.setDouble(5, econ.getMontodolares());
            cs.setString(6, SesionUsuario.misesion.getUsuario());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<EProgPagosCabecera> ListaxFecha(String inicio, String fin) {
           ArrayList<EProgPagosCabecera> lstcons=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListarCabProgramacion](?,?)}";
        try {
            
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            rs = cs.executeQuery();
            while (rs.next()) {
                EProgPagosCabecera econ=new EProgPagosCabecera();
                econ.setCod_programacion(rs.getInt(1));
                econ.setEstado_progpagos(rs.getString(2));
                econ.setBanco(rs.getString(3));
                econ.setMoneda(rs.getString(4));
                econ.setNumCuenta(rs.getString(5));
                econ.setFecha(rs.getString(6));
                lstcons.add(econ);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcons;
    }

    @Override
    public ArrayList<EConsolidado> buscaConsolidado(int codprog) {
            ArrayList<EConsolidado> lstcons=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaConsolidado](?)}";
        try {
            
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1,codprog);
            rs = cs.executeQuery();
            while (rs.next()) {
                EConsolidado econ=new EConsolidado();
                econ.setCodProgramacion(codprog);
                econ.setDocProv(rs.getString(2));
                econ.setRazonSocial(rs.getString(3));
                econ.setMontosoles(rs.getDouble(4));
                econ.setMontodolares(rs.getDouble(5));
                lstcons.add(econ);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcons;
    }

    @Override
    public ArrayList<EComprobante> lstdetalleprg(int codprog) {
           ArrayList<EComprobante> lstcomp=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaDetalleProg](?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1, codprog);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante comp=new EComprobante();
                comp.setCodProgramacion(codprog);
                comp.setCodComprobante(rs.getInt(2));
                comp.setTipoComprobante(rs.getString(3));
                comp.setNumComprobante(rs.getString(4));
                comp.setFechaComprobante(rs.getString(5));
                comp.setDocProveedor(rs.getString(6));
                comp.setGlosaComprante(rs.getString(7));
                comp.setComprobanteSujeto(rs.getString(8));
                comp.setFormaPago(rs.getString(9));
                comp.setMontoPagar(rs.getDouble(10));
                comp.setMontoPagarDOL(rs.getDouble(11));
                comp.setEstado(rs.getString(12));
                comp.setUsuModi(rs.getString(13));
                comp.setNomMoneda(rs.getString(14));
                comp.setCuentasoles(rs.getString(15));
                comp.setMontoRetDet(rs.getDouble(16));
                comp.setMontoRetDetDOL(rs.getDouble(17));
                comp.setMontoPagarRefSoles(rs.getDouble(18));
                comp.setMontoPagarRefDol(rs.getDouble(19));
                lstcomp.add(comp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcomp;
    }

    @Override
    public boolean EliminarProgramacion(int codprog) {
            String deleteprog="{call [sp_EliminarProg](?)}";
        try {
            CallableStatement cs=cn.prepareCall(deleteprog);
            cs.setInt(1, codprog);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<ECuentaBancaria> CtaBancaria(String docprov, String moneda) {
           ArrayList<ECuentaBancaria> lstcons=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaCtaBancariaNro](?,?)}";
        try {
            
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setString(1, docprov);
            cs.setString(2, moneda);
            rs = cs.executeQuery();
            while (rs.next()) {
                ECuentaBancaria ecb=new ECuentaBancaria();
                ecb.setNomBanco(rs.getString(1));
                ecb.setCuenta(rs.getString(2));
                ecb.setCuentaci(rs.getString(3));
                lstcons.add(ecb);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());   
        }
           return lstcons;
    }

    @Override
    public boolean ActualizarPrgPagos(EProgPagosCabecera objpro) {
//        @banco varchar(20),@moneda varchar(20),@codprog int,@usucrea varchar(30)
        String procInsertpro="{call [dbo].[sp_ActualizarProgPagoCab](?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getBanco());
             cs.setString(2, objpro.getMoneda());
             cs.setInt(3, objpro.getCod_programacion());
             cs.setString(4, SesionUsuario.misesion.getUsuario());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean CambiarEstado(int codcompr) {
        String procInsertpro = "{call [dbo].[sp_ActualizarEstadoComprobante](?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);
            cs.setInt(1, codcompr);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean LimpiarDetConsolidado(int codprog) {
        String procInsertpro = "{call [dbo].[sp_LimpiarProgPagDetalleConsolidado](?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);
            cs.setInt(1, codprog);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EComprobante> lstdetallexprov(int codprog, String docprov) {
            ArrayList<EComprobante> lstcomp=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_BuscarDetalleProg](?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1, codprog);
            cs.setString(2, docprov);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante comp=new EComprobante();
                comp.setCodProgramacion(codprog);
                comp.setCodComprobante(rs.getInt(2));
                comp.setTipoComprobante(rs.getString(3));
                comp.setNumComprobante(rs.getString(4));
                comp.setFechaComprobante(rs.getString(5));
                comp.setDocProveedor(rs.getString(6));
                comp.setGlosaComprante(rs.getString(7));
                comp.setComprobanteSujeto(rs.getString(8));
                comp.setFormaPago(rs.getString(9));
                comp.setMontoPagar(rs.getDouble(10));
                comp.setMontoPagarDOL(rs.getDouble(11));
                comp.setEstado(rs.getString(12));
                comp.setUsuModi(rs.getString(13));
                comp.setNomMoneda(rs.getString(14));
                comp.setCuentasoles(rs.getString(15));
                lstcomp.add(comp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        System.out.println(lstcomp.size());
        return lstcomp;
    }

    @Override
    public boolean ActualizarRetDet(EComprobante econ) {
        String procInsertpro="{call [dbo].[sp_ActualizarMontosComprobantes](?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setInt(1, econ.getCodComprobante());
             cs.setDouble(2, econ.getMontoRetDet());
             cs.setDouble(3, econ.getMontoPagar());
             cs.setString(4, econ.getComprobanteSujeto());
             cs.setDouble(5, econ.getMontoRetDetDOL());
             cs.setDouble(6, econ.getMontoPagarDOL());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public String ObtChecksum(int codprog) {
         String procValExiste = "{? = call fn_obtenerChecksum(?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setInt(2, codprog);
            cs.registerOutParameter(1, Types.VARCHAR);
            cs.executeUpdate();
            return cs.getString(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    @Override
    public boolean GrabarExportados(EExportado objexportar) {
         String procInsertpro="{call sp_InsertarDataExportado(?,?,?,?)}";
         try {
             CallableStatement cs = cn.prepareCall(procInsertpro);
             cs.setInt(1, objexportar.getCodProgramacion());
             cs.setString(2, objexportar.getMoneda());
             cs.setString(3, SesionUsuario.misesion.getUsuario());
             cs.setString(4, objexportar.getFecha());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean InsertProcesados(EComprobProcesados objcomproc) {
//        @docprov char(11), @numcomprob char(15), @moneda varchar(11),
//@montototal numeric(18,2), @TipoCta varchar(15), @nrocta varchar(25), 
//@estado varchar(15), @obs varchar(100),
//@usucrea varchar(30)
        String procInsertpro="{call sp_InsertarComprobProces(?,?,?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs = cn.prepareCall(procInsertpro);
             cs.setString(1, objcomproc.getDocProveedor());
             cs.setString(2, objcomproc.getNroComprobante());
             cs.setString(3, objcomproc.getMoneda());
             cs.setDouble(4, objcomproc.getMontoTotal());
             cs.setString(5, objcomproc.getTipoCuenta());
             cs.setString(6, objcomproc.getNroCuenta());
             cs.setString(7, objcomproc.getEstado());
             cs.setString(8, objcomproc.getObservacion());
             cs.setString(9, SesionUsuario.misesion.getUsuario());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean ExtornarProcesados(EComprobProcesados objcomproc) {
        String procInsertpro="{call sp_ExtornarComprobProces(?,?)}";
         try {
             CallableStatement cs = cn.prepareCall(procInsertpro);
             cs.setString(1, objcomproc.getDocProveedor());
             cs.setString(2, objcomproc.getNroComprobante());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public int ComprobarExtornar(EComprobProcesados objcomproc) {
        String procValExiste = "{? = call fn_verificarextornar(?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.setString(2, objcomproc.getDocProveedor());
            cs.setString(3, objcomproc.getNroComprobante());
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<EDataRetencion> ComprobReten() {
            ArrayList<EDataRetencion> lstdataretencion=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaDetProgRet]()}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDataRetencion eret=new EDataRetencion();
                eret.setCodigo(rs.getInt(1));
                eret.setTipoComprobante(rs.getString(2));
                eret.setNroComprobanteReten(rs.getString(3));
                eret.setFecha(rs.getString(4));
                eret.setDoc(rs.getString(5));
                eret.setRazonSocial(rs.getString(6));
                eret.setDireccion(rs.getString(7));
                eret.setSujetoA(rs.getString(8));
                eret.setMontoSoles(rs.getDouble(9));
                eret.setMontoDolares(rs.getDouble(10));
                eret.setRetencion(rs.getString(11));
                eret.setEstado(rs.getString(12));
                eret.setMoneda(rs.getString(13));
                lstdataretencion.add(eret);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstdataretencion;
    }

    @Override
    public boolean GenerarCodigo() {
        String procInsertpro="{call [dbo].[sp_CrearCorrelativo]()}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }  
    }

    @Override
    public int ObtenerCodigoGenerado() {
        String procValExiste = "{? = call fn_correlativocreado()}";
        try {
            CallableStatement cs = cn.prepareCall(procValExiste);
            cs.registerOutParameter(1, Types.INTEGER);
            cs.executeUpdate();
            return cs.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    @Override
    public boolean InsertarComprobReten(EDataRetencion objdat) {
        String procInsertpro="{call [dbo].[sp_InsertarComprobanteRetencion](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setInt(1, objdat.getCorrelativo());
             cs.setString(2, objdat.getRazonSocial());
             cs.setString(3, objdat.getDoc());
             cs.setString(4, objdat.getFecha());
             cs.setString(5, objdat.getNroComprobanteReten());
             if(objdat.getMoneda().trim().equalsIgnoreCase("SOLES")){
             cs.setDouble(6, objdat.getMontoSoles());    
             }else{
             cs.setDouble(6, objdat.getMontoDolares());        
             }
             cs.setString(7, "3%");
             cs.setString(8, objdat.getRetencion());
             cs.setString(9, objdat.getRetencion());
             if(objdat.getMoneda().trim().equalsIgnoreCase("SOLES")){
             cs.setDouble(10, objdat.getMontoSoles());    
             }else{
             cs.setDouble(10, objdat.getMontoDolares());        
             }
             cs.setString(11, objdat.getFechaPago());
             cs.setString(12, objdat.getMoneda());
             cs.setString(13, objdat.getTipoCambio());
             cs.setString(14, objdat.getDireccion());
             cs.setInt(15, objdat.getCodigo());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public ArrayList<EProgPagosCabecera> BuscarExportacion(String inicio, String fin) {
        ArrayList<EProgPagosCabecera> lstMAX = new ArrayList<>();
        String procldetra = "{call [dbo].[sp_BuscarExportados](?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setString(1, inicio);
            cs.setString(2, fin);
            rs = cs.executeQuery();
            while (rs.next()) {
                EProgPagosCabecera tipo = new EProgPagosCabecera();
                tipo.setCod_programacion(rs.getInt(1));
                tipo.setMoneda(rs.getString(2));
                tipo.setMonto(rs.getDouble(3));
                tipo.setFecha(rs.getString(4));
                tipo.setUsucrea(rs.getString(5));
                tipo.setFeccrea(rs.getString(6));
                lstMAX.add(tipo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstMAX;
    }

    @Override
    public boolean EliminarDataExportada(int codprog) {
        String procInsertpro="{call [dbo].[sp_EliminarProgExportada](?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setInt(1, codprog);
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }  
    }

    @Override
    public ArrayList<EExportarRetencion> ListarRetencionxExport(int mes, int año) {
        ArrayList<EExportarRetencion> lstretexp = new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListarCRetencionxExport](?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1, mes);
            cs.setInt(2, año);
            rs = cs.executeQuery();
            while (rs.next()) {
                EExportarRetencion exp=new EExportarRetencion();
                exp.setRuc(rs.getString(1));
                exp.setRazonSocial(rs.getString(2));
                exp.setTipoComprobante(rs.getString(3));
                exp.setFechaComprobante(rs.getString(4));
                exp.setFechaReten(rs.getString(5));
                exp.setNroComprobante(rs.getString(6));
                exp.setMoneda(rs.getString(7));
                exp.setNeto(rs.getDouble(9));
                exp.setNroReten(rs.getString(10));
                exp.setMontoBruto(rs.getDouble(11));
                exp.setTipoCambio(rs.getDouble(12));
                lstretexp.add(exp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstretexp;
    }

    @Override
    public ArrayList<EProgPagosCabecera> listacabparagp() {
        String prolisServCord = "{call sp_ListaCabProgramacionGP()}";
        ArrayList<EProgPagosCabecera> lstcabecera = new ArrayList<>();
        try {
            CallableStatement cs = cn.prepareCall(prolisServCord);
            ResultSet rss = cs.executeQuery();
            while (rss.next()) {
                EProgPagosCabecera tipo = new EProgPagosCabecera();
                tipo.setCod_programacion(rss.getInt(1));
                tipo.setEstado_progpagos(rss.getString(2));
                tipo.setBanco(rss.getString(3));
                tipo.setMoneda(rss.getString(4));
                tipo.setNumCuenta(rss.getString(5));
                tipo.setFecha(rss.getString(6));
                lstcabecera.add(tipo);
            }
        } catch (Exception e) {
            System.out.println(e + ": " + e.getMessage());
        }
        return lstcabecera;
    }

    @Override
    public ArrayList<EComprobante> lstdetalleagp(int cod, String sujeto) {
          ArrayList<EComprobante> lstcomp=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaDetalleProgGP](?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1, cod);
            cs.setString(2, sujeto);
            rs = cs.executeQuery();
            while (rs.next()) {
                EComprobante comp=new EComprobante();
                comp.setCodProgramacion(cod);
                comp.setCodComprobante(rs.getInt(2));
                comp.setTipoComprobante(rs.getString(3));
                comp.setNumComprobante(rs.getString(4));
                comp.setFechaComprobante(rs.getString(5));
                comp.setDocProveedor(rs.getString(6));
                comp.setGlosaComprante(rs.getString(7));
                comp.setComprobanteSujeto(rs.getString(8));
                comp.setFormaPago(rs.getString(9));
                comp.setMontoPagar(rs.getDouble(10));
                comp.setMontoPagarDOL(rs.getDouble(11));
                comp.setEstado(rs.getString(12));
                comp.setUsuModi(rs.getString(13));
                comp.setNomMoneda(rs.getString(14));
                comp.setCuentasoles(rs.getString(15));
                comp.setMontoRetDet(rs.getDouble(16));
                comp.setNroCuentaContable(rs.getString(17));
                lstcomp.add(comp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcomp;
    }

    @Override
    public boolean ActualizarAContabilizado(String numcomprobante) {
        String procInsertpro="{call [dbo].[sp_ActualizarAContabilizado](?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, numcomprobante.trim());
             cs.executeUpdate();
             System.out.println("ACTUALIZADO: "+numcomprobante);
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }  
    }

    @Override
    public ArrayList<ExportacionInterbank> ListarExportacionInterbank(int idProgramacion) {
        ArrayList<ExportacionInterbank> lstcomp=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListaDetalleProgNuevo](?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1, idProgramacion);
            rs = cs.executeQuery();
            while (rs.next()) {
                ExportacionInterbank comp=new ExportacionInterbank();
                comp.setCodReg(rs.getString(1));
                comp.setDocProveedor(rs.getString(2));
                comp.setCodComprobante(rs.getString(3));
                comp.setNumComprobante(rs.getString(4));
                comp.setFecha(rs.getString(5));
                comp.setMoneda(rs.getString(6));
                comp.setMontoAPagar(rs.getString(7));
                comp.setIndicadorInterbancario(rs.getString(8));
                comp.setTipoabono(rs.getString(9));
                comp.setTipoCuenta(rs.getString(10));
                comp.setMonedaProgramacion(rs.getString(11));
                comp.setCodTienda(rs.getString(12));
                comp.setNumeroCuenta(rs.getString(13));
                comp.setTipoPersona(rs.getString(14));
                comp.setTipoDocumentoIdentidad(rs.getString(15));
                comp.setDocumentoIdentidad(rs.getString(16));
                comp.setRazonSocial(rs.getString(17));
                comp.setEnblanco(rs.getString(18));
                comp.setRetencion(rs.getString(19));
                comp.setEspacioBlanco(rs.getString(20));
                lstcomp.add(comp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcomp;
    }

    @Override
    public ArrayList<ECuentaBancaria> validarCuentaBancaria(String docProveedor, String banco, String monedaPrincipal) {
       
        ArrayList<ECuentaBancaria> lstcomp=new ArrayList<>();
        String procldetra = "{call [dbo].[VALIDARCUENTA](?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setString(1, docProveedor);
            cs.setString(2, banco);
            cs.setString(3, monedaPrincipal);
            rs = cs.executeQuery();
            while (rs.next()) {
                ECuentaBancaria comp=new ECuentaBancaria();
                comp.setCuenta(rs.getString(1));
                comp.setDocProveedor(rs.getString(2));
                comp.setnomProveedor(rs.getString(3));
                comp.setCodbanco(rs.getInt(4));
                comp.setMoneda(rs.getString(5));  
                comp.setDescripcion(rs.getString(6));
                lstcomp.add(comp);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
        return lstcomp;
    }

    @Override
    public boolean insertarProgDetalleAutomatico(EDetProgramPagos edet) {
        String insertdetalle="{call [sp_InsertarDetallePrgoAutomatico](?,?,?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs=cn.prepareCall(insertdetalle);
            cs.setInt(1, edet.getCodprog());
            cs.setString(2, edet.getNroDocumento());
            cs.setString(3, edet.getEstado());
            cs.setString(4, edet.getUsuCrea());
            cs.setString(5, edet.getBanco());
            cs.setString(6, edet.getMoneda());
            cs.setString(7, edet.getCtaBancaria());
            cs.setString(8, edet.getTccomprobante());
            cs.setString(9, edet.getDocProveedor());
            cs.executeUpdate();
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<ECuentaBancaria> eliminarProgramacionDetalle(String cod, int option, String docProv, String banco,String Moneda) {
    
        String proceli="{call [sp_eliminarDetalle](?,?,?,?,?)}";
         ArrayList<ECuentaBancaria> listass=null;
        try {
            listass= new ArrayList<>();
            CallableStatement cs=cn.prepareCall(proceli);
            cs.setString(1, cod);
            cs.setInt(2, option);
            cs.setString(3, docProv);
            cs.setString(4, banco);
            cs.setString(5, Moneda);
             rs = cs.executeQuery();
            while (rs.next()) {
              ECuentaBancaria objBancaria=new ECuentaBancaria();
               objBancaria.setDocProveedor(rs.getString(1));
               objBancaria.setnomProveedor(rs.getString(2));
               objBancaria.setMoneda(rs.getString(3));
               objBancaria.setCodbanco(rs.getInt(4));
               objBancaria.setNomBanco(rs.getString(5));
               objBancaria.setCuenta(rs.getString(6));
               objBancaria.setCuentaci(rs.getString(7));
               listass.add(objBancaria);
            }
            return listass;
        } catch (SQLException e) {
            System.out.println("erooror;" + e.getMessage());
            return listass;
        }
    }

    @Override
    public ECuentaBancaria validarDocumentosProgramados(String numeroDocu,String DocProveedor,String TipoComprobante) {
          String proceli="{call [dbo].[sp_validarDocumentosProgramados](?,?,?)}";
         ECuentaBancaria objBancaria=null;
        try {
            CallableStatement cs=cn.prepareCall(proceli);
            cs.setString(1, numeroDocu);
            cs.setString(2, DocProveedor);
            cs.setString(3, TipoComprobante);
            rs = cs.executeQuery();
            while (rs.next()) {
              objBancaria=new ECuentaBancaria();
               objBancaria.setDocProveedor(rs.getString(1));
               objBancaria.setnomProveedor(rs.getString(2));
               objBancaria.setMoneda(rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("erooror 2;" + e.getMessage());
        }
        return objBancaria;
    }

    @Override
    public ArrayList<ETipoComprobante> BuscarTipoComprobante(String codigo) {
         String proceli="{call [BuscarTipoComprobante](?)}";
         ArrayList<ETipoComprobante> listass=null;
        try {
            listass= new ArrayList<>();
            CallableStatement cs=cn.prepareCall(proceli);
            cs.setString(1, codigo);
             rs = cs.executeQuery();
            while (rs.next()) {
              ETipoComprobante obj=new ETipoComprobante();
               obj.setCodComprobante(rs.getString(1));
               obj.setDescComprobante(rs.getString(2));
               listass.add(obj);
            }
            return listass;
        } catch (SQLException e) {
            System.out.println("erooror;" + e.getMessage());
            return listass;
        }
    }

    

}
