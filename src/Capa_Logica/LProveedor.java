/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDProveedor;
import Capa_Entidades.EProveedor;
import Capa_Logica.Interfaz.IProveedor;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LProveedor  implements IProveedor{
    
    BDProveedor bdpro=new BDProveedor(SesionUsuario.bddatos.getNombd());

 
    @Override
    public boolean InsertarProveedor(EProveedor objpro) {
        return bdpro.InsertarProveedor(objpro);
    }

    @Override
    public boolean ActualizarProveedor(EProveedor objpro) {
         return  bdpro.ActualizarProveedor(objpro);
    }

    @Override
    public int verifRUCrep(String ruc) {
        return bdpro.verifRUCrep(ruc);
    }

    @Override
    public ArrayList<EProveedor> listaProveedor() {
        return bdpro.listaProveedor();
    }

    @Override
    public ArrayList<EProveedor> ListaTipoProveedor() {
       return bdpro.ListaTipoProveedor();
    }

    @Override
    public boolean InsertarProExcel(EProveedor objpro) {
       return bdpro.InsertarProExcel(objpro);
    }

    @Override
    public ArrayList<EProveedor> listaProveedorExcel() {
        return bdpro.listaProveedorExcel();
    }

    @Override
    public boolean InsertarProveedorTemp(EProveedor objpro) {
        return bdpro.InsertarProveedorTemp(objpro);
    }

    @Override
    public boolean ActualizarProveedorTemp(EProveedor objpro) {
        return bdpro.ActualizarProveedorTemp(objpro);
    }

    @Override
    public ArrayList<EProveedor> listaPersonal() {
        return bdpro.listaPersonal();
    }

    @Override
    public boolean InsertarPersonal(EProveedor objpro) {
        return bdpro.InsertarPersonal(objpro);
    }

    @Override
    public boolean ActualizarPersonal(EProveedor objpro) {
        return bdpro.ActualizarPersonal(objpro);
    }

    @Override
    public ArrayList<EProveedor> Llenarcomboretencion() {
        return bdpro.Llenarcomboretencion();
    }

    @Override
    public ArrayList<EProveedor> filtrarProveedor(String razsocial) {
        return bdpro.filtrarProveedor(razsocial);
    }
    
}
