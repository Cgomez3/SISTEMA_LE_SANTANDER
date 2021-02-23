/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EBanco;
import Capa_Entidades.ETipoCambio;
import Capa_Logica.Interfaz.ITipoCambio;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
/**
 *
 * @author EXPERTYA
 */
public class BDTipoCambio extends ConexionBD implements ITipoCambio{
    
    ArrayList<EBanco> lsttipocambio = new ArrayList<>();
    
    public BDTipoCambio(String bd) {
        super(bd);
    }
    
     
    public ArrayList<ETipoCambio> ListaTipoCambio() {
        ArrayList<ETipoCambio> lsttc=new ArrayList<>();
        String proclperiodo = "{call sp_FactListaTipoCambioExp()}";
        try {
            CallableStatement cs = cn.prepareCall(proclperiodo);
            rs = cs.executeQuery();
            while (rs.next()) {
                ETipoCambio tipo=new ETipoCambio();
                tipo.setCodTipoCambio(rs.getInt(1));
                tipo.setFecha(rs.getString(2));
                tipo.setPrecioCompra(rs.getDouble(3));
                tipo.setPrecioVenta(rs.getDouble(4));
                tipo.setUsuCrea(rs.getString(5));
                tipo.setFecCrea(rs.getString(6));
                tipo.setUsuModi(rs.getString(7));
                tipo.setFecModi(rs.getString(8));
                lsttc.add(tipo);
            }
            return lsttc;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lsttc;
        }
    }

    @Override
    public boolean InsertarTipoCambio(ETipoCambio tipo) {
         String procInsertTC = "{call sp_FactInsertarTipoCambioExp(?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertTC);
            cs.setString(1, tipo.getFecha());
            cs.setDouble(2, tipo.getPrecioCompra());
            cs.setDouble(3, tipo.getPrecioVenta());
            cs.setString(4, tipo.getUsuCrea());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean ActualizarTipoCambio(ETipoCambio tipo) {
         String procUpdateTC = "{call [dbo].[sp_FactActualizarTipoCambioExp](?,?,?,?)}";
        
       try {
           System.out.println("aki");
            CallableStatement cs = cn.prepareCall(procUpdateTC);
//            cs.setInt(1, tipo.getCodTipoCambio());
            cs.setString(1, tipo.getFecha());
            cs.setDouble(2, tipo.getPrecioCompra());
            cs.setDouble(3, tipo.getPrecioVenta());
            cs.setString(4, tipo.getUsuCrea());
            System.out.println("aki");
           
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Double RecuperarTipoCambio(String fecha) {
        
        String FuncRecuperarTC = "{? = call fn_FactTipoCambioExp(?)}";
          try {
             CallableStatement cs=cn.prepareCall(FuncRecuperarTC);
             cs.registerOutParameter(1, Types.DOUBLE);
             cs.setString(2, fecha);
             cs.executeUpdate();
             return cs.getDouble(1);
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return 0.0;
         } 
    }
    
}
