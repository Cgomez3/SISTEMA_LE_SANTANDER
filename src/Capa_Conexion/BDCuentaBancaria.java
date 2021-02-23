/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.ECuentaBancaria;
import Capa_Logica.Interfaz.ICuentaBancaria;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDCuentaBancaria extends ConexionBD implements ICuentaBancaria{
    
      public BDCuentaBancaria(String bd) {
        super(bd);
    }

    @Override
    public ArrayList<ECuentaBancaria> listaCuentaBancaria() {
         ArrayList<ECuentaBancaria> liscuenta=new ArrayList<>();
         String prolispro="{call [dbo].[ListarCuentaBancaria]()}";
         try {
              CallableStatement cs=cn.prepareCall(prolispro);
             rs=cs.executeQuery();
             while (rs.next()) {                 
                 ECuentaBancaria tipo = new ECuentaBancaria();
                
                 tipo.setDocProveedor(rs.getString(1));
                 tipo.setNomBanco(rs.getString(2));
                 tipo.setMoneda(rs.getString(3));
                 tipo.setCuenta(rs.getString(4));
                 tipo.setCuentaci(rs.getString(5));
                 
                 liscuenta.add(tipo);
             }   
             return liscuenta;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return liscuenta;
        }
         

    }

    @Override
    public boolean InsertarCuentaBancaria(ECuentaBancaria objpro) {
          String procInsertpro="{call AgregarCuentaBancaria(?,?,?,?,?,?)}";
         try {
             CallableStatement cs=cn.prepareCall(procInsertpro);


             cs.setString(1, objpro.getDocProveedor());
             cs.setInt(2, objpro.getCodbanco());
             cs.setString(3, objpro.getmoneda());
             cs.setString(4, objpro.getCuenta());
             cs.setString(5, objpro.getCuentaci());
             cs.setInt(6, objpro.getTipoCuenta());


             cs.executeUpdate();
             return true;
         } catch (Exception e) {
             System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean ActualizarCuentaBancaria(ECuentaBancaria objpro) {
       String procInsertpro="{call [dbo].[usp_ActualizarComprobante](?,?,?,?,?)}";
         try {
              CallableStatement cs=cn.prepareCall(procInsertpro);
             

             cs.setString(1, objpro.getDocProveedor());
             cs.setInt(2, objpro.getCodbanco());
             cs.setString(3, objpro.getmoneda());
             cs.setString(4, objpro.getCuenta());
             cs.setString(5, objpro.getCuentaci());
       
             
             cs.executeUpdate();
             return true;
         } catch (Exception e) {
              System.out.println(e.getMessage());
             return false;
         }   
    }

    @Override
    public boolean eliminarCuenta(String cod) {
         String proceli="{call EliminarCuentaBancaria(?)}";
        try {
            CallableStatement cs=cn.prepareCall(proceli);
            cs.setString(1, cod);
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public ArrayList<ECuentaBancaria> ListarCuentaXRuc(String ruc) {
          ArrayList<ECuentaBancaria> lstcuenta=new ArrayList<>();
          String prolispro="{call ListarCuentaXRuc(?)}";
         try {
              CallableStatement cs = cn.prepareCall(prolispro);
            cs.setString(1, ruc);
            ResultSet rss = cs.executeQuery();
                               
             while (rss.next()) {   
                 ECuentaBancaria tipo = new ECuentaBancaria();
                

                 tipo.setCodbanco(rss.getInt(1));
                 tipo.setMoneda(rss.getString(2));
                 tipo.setCuenta(rss.getString(3));
                 tipo.setCuentaci(rss.getString(4));
                 

                 lstcuenta.add(tipo);
             }   
             return lstcuenta;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return lstcuenta;
        }
    }

    }

