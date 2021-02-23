/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EParametro;
import Capa_Logica.Interfaz.IParametros;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDParametro extends ConexionBD implements IParametros{
    
     public BDParametro(String bd){
        super(bd);  
    }

    @Override
    public ArrayList<EParametro> ListaParametros() {
       ArrayList<EParametro> lispara=new ArrayList<>();
         String prolispro="{call [dbo].[usp_ListaParametro]()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EParametro tipo = new EParametro();
                System.out.println((rs.getInt(1)+"ENTRO"));
                
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
    public boolean ActualizarParametro(EParametro objpro) {
        String procInsertpro="{call [usp_ActualizarParametro](?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             
             cs.setString(1, objpro.getRazonSocial());
             cs.setString(2, objpro.getRucParametro());
             cs.setString(3, objpro.getDireParametro());
             cs.setInt(4, objpro.getCodBanco1());
             
             cs.setString(5, objpro.getCTASoles());
             cs.setString(6, objpro.getCTADolares());
             cs.setString(7, objpro.getCCISoles());
             cs.setString(8, objpro.getCCIDolares());
             cs.setString(9, objpro.getCTAConSol());
             cs.setString(10, objpro.getCTAConDol());
             
             cs.setInt(11, objpro.getCodBanco2());
             cs.setString(12, objpro.getCTASoles2());
             cs.setString(13, objpro.getCTADolares2());
             cs.setString(14, objpro.getCCISoles2());
             cs.setString(15, objpro.getCCIDolares2());
             cs.setString(16, objpro.getCTAConSol2());
             cs.setString(17, objpro.getCTAConDol2());
             
             cs.setDouble(18, objpro.getIGV());
             cs.setDouble(19, objpro.getCampo1());
             cs.setDouble(20, objpro.getCampo2());
             
             
             cs.setString(21, objpro.getCuentaIGV());
             cs.setString(22, objpro.getCxPSolPro());
             cs.setString(23, objpro.getCxPDolPro());
             cs.setString(24, objpro.getCxPSolRxH());
             cs.setString(25, objpro.getCxPDolRxH());
            
             
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public ArrayList<EParametro> ListaBancoPrincipal() {
         ArrayList<EParametro> lisbanpara=new ArrayList<>();
         String prolispro="{call ListaBancoPrincipal()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EParametro tipo = new EParametro();
                
                 tipo.setNomBanco(rs.getString(1));
                 tipo.setNomBanco2(rs.getString(2));
         
                 lisbanpara.add(tipo);
             }   
             return lisbanpara;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lisbanpara;
        }
    }

    @Override
    public ArrayList<EParametro> ListaCuentasPrincipal() {
           ArrayList<EParametro> liscuenpara=new ArrayList<>();
         String prolispro="{call ListaCuentasPrincipal()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 EParametro tipo = new EParametro();
                
                 tipo.setCTASoles(rs.getString(1));
                 tipo.setCTADolares(rs.getString(2));
                 tipo.setCTASoles2(rs.getString(3));
                 tipo.setCTADolares2(rs.getString(4));
                 
         
                 liscuenpara.add(tipo);
             }   
             return liscuenpara;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscuenpara;
        }
    }

    @Override
    public int ConsultarCorrelativo() {
        int numero=0;
         String prolispro="{call sp_ConsultaCorrelativo()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
         numero=rs.getInt(1);
             }   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
         return numero;
    }

    @Override
    public boolean ActualizarCorrelativo(int nuevo) {
        String procInsertpro="{call [sp_ActualizarCorrelativo](?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.setInt(1, nuevo);
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }
    
}
