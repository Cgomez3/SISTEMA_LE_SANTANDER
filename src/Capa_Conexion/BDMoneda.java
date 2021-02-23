/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EMoneda;
import Capa_Logica.Interfaz.IMoneda;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class BDMoneda extends  ConexionBD implements IMoneda{
    
      public BDMoneda(String bd){
        super(bd);
    }

    @Override
    public ArrayList<EMoneda> ListaMoneda() {
      
        ArrayList<EMoneda> lstmon=new ArrayList<>();
        String proclmoneda = "{call [dbo].[sp_ListaMoneda]()}";
        try {
            CallableStatement cs = cn.prepareCall(proclmoneda);
                rs = cs.executeQuery();
                 while (rs.next()) {
                EMoneda banco =new EMoneda();
                banco.setCodMoneda(rs.getInt(1));
                banco.setNomMoneda(rs.getString(2));
                lstmon.add(banco);
            }
            return lstmon;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    return lstmon;
    
    
    }
    
}
