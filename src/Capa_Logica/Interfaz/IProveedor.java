/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EProveedor;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author EXPERTYA
 */
public interface IProveedor {
    public ArrayList<EProveedor>listaProveedor();
    public boolean InsertarProveedor(EProveedor objpro);
    public boolean ActualizarProveedor(EProveedor objpro);
    public int verifRUCrep (String ruc);
    public ArrayList<EProveedor> ListaTipoProveedor();
    public boolean InsertarProExcel(EProveedor objpro);
    public ArrayList<EProveedor>listaProveedorExcel();
    public boolean InsertarProveedorTemp(EProveedor objpro);
    public boolean ActualizarProveedorTemp(EProveedor objpro);
    public ArrayList<EProveedor>listaPersonal();
    public boolean InsertarPersonal(EProveedor objpro);
    public boolean ActualizarPersonal(EProveedor objpro);
    public ArrayList<EProveedor>Llenarcomboretencion();
    public ArrayList<EProveedor> filtrarProveedor(String razsocial);
}
