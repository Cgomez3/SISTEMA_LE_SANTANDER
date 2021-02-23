/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EDetraccTxt;
import Capa_Entidades.EDetraccion;
import Capa_Logica.Interfaz.IDetraccion;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDDetraccion extends ConexionBD implements IDetraccion{
    
     public BDDetraccion(String bd) {
        super(bd);
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
    public boolean InsertarDetraccion(EDetraccion tipo) {
          String procInsertTC = "{call [dbo].[sp_InsertarDetraccion](?,?,?,?)}";
        try {
            CallableStatement cs = cn.prepareCall(procInsertTC);
            cs.setString(1, tipo.getCodDetraccion());
            cs.setString(2, tipo.getTipoDetra());
            cs.setDouble(3, tipo.getPorcentajeDetra());
            cs.setString(4, tipo.getUsuarioCrea());
            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean ActualizarDetraccion(EDetraccion tipo) {
         String procUpdateTC = "{call [dbo].[sp_ActualizarDetraccion1](?,?,?,?)}";
        
       try {
            CallableStatement cs = cn.prepareCall(procUpdateTC);
            cs.setString(1, tipo.getCodDetraccion());
            cs.setString(2, tipo.getTipoDetra());
            cs.setDouble(3, tipo.getPorcentajeDetra());
            cs.setString(4, tipo.getUsuarioCrea());

            cs.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<EDetraccTxt> ListarDetracciones(int codigo) {
        ArrayList<EDetraccTxt> lstdetra=new ArrayList<>();
        String procldetra = "{call [dbo].[sp_ListarDetraccionTxt](?)}";
        try {
            CallableStatement cs = cn.prepareCall(procldetra);
            cs.setInt(1, codigo);
            rs = cs.executeQuery();
            while (rs.next()) {
                EDetraccTxt det=new EDetraccTxt();
                det.setDocProv(rs.getString(2));
                det.setCodDetraccion(rs.getString(4));
                det.setCtaDetraccion(rs.getString(5));
                det.setImporte(rs.getDouble(6));
                det.setTipoOp(rs.getString(7));
                det.setTipoComprobante(rs.getString(8));
                det.setNroComprobante(rs.getString(9));
                det.setTotal(rs.getDouble(10));
                det.setTotalDol(rs.getDouble(11));
                det.setPeriodoTributario(rs.getString(12));
                lstdetra.add(det);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lstdetra;
    }
    
}
