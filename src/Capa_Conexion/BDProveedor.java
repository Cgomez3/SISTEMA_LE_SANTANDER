/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EProveedor;
import Capa_Entidades.ETipoCambio;
import Capa_Logica.Interfaz.IProveedor;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EXPERTYA
 */
public class BDProveedor extends ConexionBD implements IProveedor{
    
     ArrayList<EProveedor> lstproveedor = new ArrayList<>();
    
    
     public BDProveedor(String bd){
        super(bd);  
    }

 

    @Override
    public boolean InsertarProveedor(EProveedor objpro) {
       String procInsertpro="{call [dbo].[sp_AgregarProveedor1](?,?,?,?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getDocProveedor());
             cs.setString(2, objpro.getTipoDocProveedor());
             cs.setString(3, objpro.getRazonSocial());
             cs.setInt(4, objpro.getEstado());
             cs.setString(5, objpro.getDireccion());
             cs.setString(6, objpro.getTelefono());
             cs.setString(7, objpro.getContacto());
             cs.setString(8, objpro.getCorreoContacto());
             cs.setString(9, objpro.getUsuCrea());
             cs.setString(10, objpro.getCuentaDetracciones());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean ActualizarProveedor(EProveedor objpro) {
         String procInsertpro="{call [dbo].[usp_ActualizarProveedor1](?,?,?,?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getDocProveedor());
             cs.setString(2, objpro.getTipoDocProveedor());
             cs.setString(3, objpro.getRazonSocial());
             cs.setInt(4, objpro.getEstado());
             cs.setString(5, objpro.getDireccion());
             cs.setString(6, objpro.getTelefono());
             cs.setString(7, objpro.getContacto());
             cs.setString(8, objpro.getCorreoContacto());
             cs.setString(9, objpro.getUsumodi());
             cs.setString(10, objpro.getCuentaDetracciones());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }
     
     public int verifRUCrep(String ruc) {
        String procInsertpro="{? = call [usp_VeriRUCProveedor](?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.registerOutParameter(1, Types.INTEGER);
             cs.setString(2, ruc);
             cs.executeUpdate();
             return cs.getInt(1);
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return 0;
         }   
    }

    @Override
    public ArrayList<EProveedor> listaProveedor() {
         ArrayList<EProveedor> lispro=new ArrayList<>();
         String prolispro="{call [dbo].[usp_ListaProveedor]()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EProveedor tipo = new EProveedor();
                 tipo.setDocProveedor(rs.getString(1));
                 tipo.setTipoDocProveedor(rs.getString(2));
                 tipo.setRazonSocial(rs.getString(3));
                 tipo.setEstado(rs.getInt(4));
                 tipo.setDireccion(rs.getString(5));
                 tipo.setTelefono(rs.getString(6));
                 tipo.setContacto(rs.getString(7));
                 tipo.setCorreoContacto(rs.getString(8));
                 tipo.setNomBanco(rs.getString(9));
                 tipo.setTipoCuenta(rs.getInt(10));
                 tipo.setCTASoles(rs.getString(11));
                 tipo.setCTADolares(rs.getString(12));
                 tipo.setCCISoles(rs.getString(13));
                 tipo.setCCIDolares(rs.getString(14));
                 tipo.setCuentaDetracciones(rs.getString(15));
                 lispro.add(tipo);
             }   
             return lispro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lispro;
        }
         
    }

    @Override
    public ArrayList<EProveedor> ListaTipoProveedor() {
          ArrayList<EProveedor> lisp=new ArrayList<>();
        String proclp = "{call [sp_ListaTipoPro]()}";
        try {
            CallableStatement cs = cn.prepareCall(proclp);
                rs = cs.executeQuery();
                 while (rs.next()){
                EProveedor proo =new EProveedor();
                proo.setTipoDocProveedor(rs.getString(1));
                lisp.add(proo);
            }
            return lisp;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    return lisp;
    }

    @Override
    public boolean InsertarProExcel(EProveedor objpro) {
        String procInsertpro="{call [dbo].[sp_AgregarProveedorExcel](?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getDocProveedor());
             cs.setString(2, objpro.getRazonSocial());
             cs.setString(3, objpro.getDireccion());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public ArrayList<EProveedor> listaProveedorExcel() {
            ArrayList<EProveedor> lispexcel=new ArrayList<>();
        String proclp = "{call [dbo].[usp_ListaProveedorExcel]()}";
        try {
            CallableStatement cs = cn.prepareCall(proclp);
                rs = cs.executeQuery();
                 while (rs.next()){
                EProveedor proo =new EProveedor();
                proo.setDocProveedor(rs.getString(1));
                proo.setRazonSocial(rs.getString(2));
                proo.setDireccion(rs.getString(3));
                
                lispexcel.add(proo);
            }
            return lispexcel;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    return lispexcel;
    }

    @Override
    public boolean InsertarProveedorTemp(EProveedor objpro) {
        String procInsertpro="{call [dbo].[SP_INSERTAPROVEEDOR](?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getDocProveedor());
             cs.setString(2, objpro.getRazonSocial().toUpperCase());
             cs.setString(3, objpro.getDireccion().toUpperCase());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean ActualizarProveedorTemp(EProveedor objpro) {
       String procInsertpro="{call [dbo].[SP_ACTUALIZAPROVEEDOR](?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getDocProveedor());
             cs.setString(2, objpro.getRazonSocial().toUpperCase());
             cs.setString(3, objpro.getDireccion().toUpperCase());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public ArrayList<EProveedor> listaPersonal() {
        ArrayList<EProveedor> lispro=new ArrayList<>();
         String prolispro="{call [dbo].[usp_ListaProveedor2]()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EProveedor tipo = new EProveedor();
                 tipo.setDocProveedor(rs.getString(1));
                 tipo.setTipoDocProveedor(rs.getString(2));
                 tipo.setRazonSocial(rs.getString(3));
                 tipo.setEstado(rs.getInt(4));
                 tipo.setDireccion(rs.getString(5));
                 tipo.setTelefono(rs.getString(6));
                 tipo.setNomBanco(rs.getString(7));
                 tipo.setCTASoles(rs.getString(8));
                 tipo.setCTADolares(rs.getString(9));
                 tipo.setCCISoles(rs.getString(10));
                 tipo.setCCIDolares(rs.getString(11));
                 lispro.add(tipo);
             }   
             return lispro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lispro;
        }
         
    }

    @Override
    public boolean InsertarPersonal(EProveedor objpro) {
        String procInsertpro = "{call [dbo].[sp_AgregarPersonal](?,?,?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertpro);
            cs.setString(1, objpro.getDocProveedor());
            cs.setString(2, objpro.getTipoDocProveedor());
            cs.setString(3, objpro.getRazonSocial());
            cs.setInt(4, objpro.getEstado());
            cs.setString(5, objpro.getDireccion());
            cs.setString(6, objpro.getTelefono());
            cs.setString(7, objpro.getUsuCrea());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean ActualizarPersonal(EProveedor objpro) {
         String procInsertpro="{call [dbo].[sp_ActualizarPersonal](?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setString(1, objpro.getDocProveedor());
             cs.setString(2, objpro.getTipoDocProveedor());
             cs.setString(3, objpro.getRazonSocial());
             cs.setInt(4, objpro.getEstado());
             cs.setString(5, objpro.getDireccion());
             cs.setString(6, objpro.getTelefono());
             cs.setString(7, objpro.getUsumodi());
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public ArrayList<EProveedor> Llenarcomboretencion() {
        ArrayList<EProveedor> lispro=new ArrayList<>();
         String prolispro="{call [dbo].[sp_LCProveedoresReten]()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EProveedor tipo = new EProveedor();
                 tipo.setDocProveedor(rs.getString(1));
                 tipo.setRazonSocial(rs.getString(2));
                 lispro.add(tipo);
             }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         return lispro;
    }

    @Override
    public ArrayList<EProveedor> filtrarProveedor(String razsocial) {
         ArrayList<EProveedor> lispro=new ArrayList<>();
         String prolispro="{call [dbo].[usp_FiltrarProveedor](?)}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
              cs.setString(1, razsocial);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EProveedor tipo = new EProveedor();
                 tipo.setDocProveedor(rs.getString(1));
                 tipo.setTipoDocProveedor(rs.getString(2));
                 tipo.setRazonSocial(rs.getString(3));
                 tipo.setEstado(rs.getInt(4));
                 tipo.setDireccion(rs.getString(5));
                 tipo.setTelefono(rs.getString(6));
                 tipo.setContacto(rs.getString(7));
                 tipo.setCorreoContacto(rs.getString(8));
                 tipo.setNomBanco(rs.getString(9));
                 tipo.setTipoCuenta(rs.getInt(10));
                 tipo.setCTASoles(rs.getString(11));
                 tipo.setCTADolares(rs.getString(12));
                 tipo.setCCISoles(rs.getString(13));
                 tipo.setCCIDolares(rs.getString(14));
                 tipo.setCuentaDetracciones(rs.getString(15));
                 lispro.add(tipo);
             }   
             return lispro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lispro;
        }
    }
}
     
     
     
    

