/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDBuenoContribuyentes;
import Capa_Entidades.EBuenosContribuyentes;
import Capa_Logica.Interfaz.IBuenosContribuyentes;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LBuenosContribuyentes implements  IBuenosContribuyentes{
    
      BDBuenoContribuyentes bdcontri=new BDBuenoContribuyentes(SesionUsuario.bddatos.getNombd());

    @Override
    public ArrayList<EBuenosContribuyentes> ListaBuenosContribuyentes() {
       return bdcontri.ListaBuenosContribuyentes();
    }

    @Override
    public boolean InsertarContribuyentes(EBuenosContribuyentes tipo) {
        return bdcontri.InsertarContribuyentes(tipo);
    }

    @Override
    public boolean ActualizarContribuyentes(EBuenosContribuyentes tipo) {
        return bdcontri.ActualizarContribuyentes(tipo);
    }

    @Override
    public int verifRUCrep(String ruc) {
       return bdcontri.verifRUCrep(ruc);
    }



    @Override
    public boolean Eliminar() {
       return bdcontri.Eliminar();
    }

    @Override
    public ArrayList<EBuenosContribuyentes> ListaContriFiltro(String letras) {
       return bdcontri.ListaContriFiltro(letras);
    }

   
}
