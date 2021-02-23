/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EAgenteRetenedores;
import Capa_Logica.Interfaz.IAgenteRetenedores;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDAgenteRetenedores extends ConexionBD implements IAgenteRetenedores{
    
     public BDAgenteRetenedores(String bd) {
        super(bd);
    }

    @Override
    public boolean InsertarAgente(EAgenteRetenedores tipo) {
          String procInsertTC = "{call [dbo].[sp_InsertarAgente](?,?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertTC);
            cs.setString(1, tipo.getRucBC());
            cs.setString(2, tipo.getRazonSocialBC());
            cs.setString(3, tipo.getFechaIniBC());
            cs.setString(4, tipo.getResolucionBC());
            cs.setString(5, tipo.getUsuarioCrea());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    
    }

    @Override
    public boolean ActualizarAgente(EAgenteRetenedores tipo) {
         String procUpdateTC = "{call [dbo].[sp_FactActualizarAgente](?,?,?,?,?)}";
        
       try {
            CallableStatement cs = cn.prepareCall(procUpdateTC);
            cs.setString(1, tipo.getRucBC());
            cs.setString(2, tipo.getRazonSocialBC());
            cs.setString(3, tipo.getFechaIniBC());
            cs.setString(4, tipo.getResolucionBC());
            cs.setString(5, tipo.getUsuarioCrea());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EAgenteRetenedores> ListaAgente() {
        ArrayList<EAgenteRetenedores> lstagente=new ArrayList<>();
        String proclage = "{call [dbo].[sp_ListaAgente]()}";
        try {
            CallableStatement cs = cn.prepareCall(proclage);
            rs = cs.executeQuery();
            while (rs.next()) {
                EAgenteRetenedores tipo=new EAgenteRetenedores();
                tipo.setRucBC(rs.getString(1));
                tipo.setRazonSocialBC(rs.getString(2));
                tipo.setFechaIniBC(rs.getString(3));
                tipo.setResolucionBC(rs.getString(4));
                tipo.setUsuarioCrea(rs.getString(5));
                tipo.setFechaCrea(rs.getString(6));

                lstagente.add(tipo);
            }
            return lstagente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstagente;
        }
    }

    @Override
    public boolean Eliminar() {
           String proceli="{call [dbo].[Esp_EliminarAgente]}";
        try {
            CallableStatement cs=cn.prepareCall(proceli);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public int VerificarAgente(String ruc) {
         String procInsertpro="{? = call [fn_verificaragenteretenedor](?)}";
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
    public ArrayList<EAgenteRetenedores> ListaFiltro(String letras) {
        ArrayList<EAgenteRetenedores> lstagente=new ArrayList<>();
        String proclage = "{call [dbo].[sp_ListaAgentexLetra](?)}";
        try {
            CallableStatement cs = cn.prepareCall(proclage);
            cs.setString(1, letras);
            rs = cs.executeQuery();
            while (rs.next()) {
                EAgenteRetenedores tipo=new EAgenteRetenedores();
                tipo.setRucBC(rs.getString(1));
                tipo.setRazonSocialBC(rs.getString(2));
                tipo.setFechaIniBC(rs.getString(3));
                tipo.setResolucionBC(rs.getString(4));
                tipo.setUsuarioCrea(rs.getString(5));
                tipo.setFechaCrea(rs.getString(6));
                lstagente.add(tipo);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstagente;
    }

    @Override
    public int VerificarConsolidado(int codprog, String docprov) {
        String procInsertpro="{? = call [fn_verificarcons](?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);
             cs.registerOutParameter(1, Types.INTEGER);
             cs.setInt(2, codprog);
             cs.setString(3, docprov);
             cs.executeUpdate();
             return cs.getInt(1);
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return 0;
         }   
    }


    
    
    
    
    
}
