/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Conexion;

import Capa_Entidades.EBanco;
import Capa_Logica.Interfaz.IBanco;
import java.sql.CallableStatement;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYAlstbanco
 */
public class BDBanco extends ConexionBD implements IBanco{
    
    
    public BDBanco(String bd){
        super(bd);
    }

    @Override
    public ArrayList<EBanco> ListaBanco() {
        ArrayList<EBanco> lstban=new ArrayList<>();
        String proclBanco = "{call sp_ListaBanco()}";
        try {
            CallableStatement cs = cn.prepareCall(proclBanco);
                rs = cs.executeQuery();
                 while (rs.next()) {
                EBanco banco =new EBanco();
                banco.setCodBanco(rs.getInt(1));
                banco.setNomBanco(rs.getString(2));
                lstban.add(banco);
            }
            return lstban;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }
    return lstban;
    
    
    }

    
    
    
    
}