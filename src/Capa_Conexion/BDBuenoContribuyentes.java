/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EBuenosContribuyentes;
import Capa_Logica.Interfaz.IBuenosContribuyentes;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDBuenoContribuyentes extends ConexionBD implements IBuenosContribuyentes{
    
      public BDBuenoContribuyentes(String bd) {
        super(bd);
    }

    @Override
    public ArrayList<EBuenosContribuyentes> ListaBuenosContribuyentes() {
          ArrayList<EBuenosContribuyentes> lstabuen=new ArrayList<>();
        String proclage = "{call [dbo].[sp_ListaBuenosContribuyentes]()}";
        try {
            CallableStatement cs = cn.prepareCall(proclage);
            rs = cs.executeQuery();
            while (rs.next()) {
                EBuenosContribuyentes tipo=new EBuenosContribuyentes();
                tipo.setRuc(rs.getString(1));
                tipo.setRazonSocial(rs.getString(2));
                tipo.setFechaIni(rs.getString(3));
                tipo.setResolucion(rs.getString(4));
                tipo.setUsuarioCrea(rs.getString(5));
                tipo.setFechaCrea(rs.getString(6));

                lstabuen.add(tipo);
            }
            return lstabuen;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstabuen;
        }
    
    }

    @Override
    public boolean InsertarContribuyentes(EBuenosContribuyentes tipo) {
         String procInsertTC = "{call [dbo].[sp_InsertarBuenosContri](?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertTC);
            cs.setString(1, tipo.getRuc());
            cs.setString(2, tipo.getRazonSocial());
            cs.setString(3, tipo.getFechaIni());
            cs.setString(4, tipo.getResolucion());
            cs.setString(5, tipo.getUsuarioCrea());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    
    }

    @Override
    public boolean ActualizarContribuyentes(EBuenosContribuyentes tipo) {
      String proceli="{call [dbo].[Esp_EliminarComprobante]}";
        try {
            CallableStatement cs=cn.prepareCall(proceli);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public int verifRUCrep(String ruc) {
        String procInsertpro="{? = call [dbo].[usp_VeriRUCBuenosContri](?)}";
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
    public boolean Eliminar() {
           String proceli="{call [dbo].[Esp_EliminarContribuyentes]}";
        try {
            CallableStatement cs=cn.prepareCall(proceli);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public ArrayList<EBuenosContribuyentes> ListaContriFiltro(String letras) {
          ArrayList<EBuenosContribuyentes> lstabuen=new ArrayList<>();
        String proclage = "{call [dbo].[sp_ListaBuenosContribuyentesxLetra](?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclage);
            cs.setString(1, letras);
            rs = cs.executeQuery();
            while (rs.next()) {
                EBuenosContribuyentes tipo=new EBuenosContribuyentes();
                tipo.setRuc(rs.getString(1));
                tipo.setRazonSocial(rs.getString(2));
                tipo.setFechaIni(rs.getString(3));
                tipo.setResolucion(rs.getString(4));
                tipo.setUsuarioCrea(rs.getString(5));
                tipo.setFechaCrea(rs.getString(6));
                lstabuen.add(tipo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstabuen;
    }

   

   
    
}
