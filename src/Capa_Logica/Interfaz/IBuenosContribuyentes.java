/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.EBuenosContribuyentes;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface IBuenosContribuyentes {

    public ArrayList<EBuenosContribuyentes> ListaBuenosContribuyentes();

    public boolean InsertarContribuyentes(EBuenosContribuyentes tipo);

    public boolean ActualizarContribuyentes(EBuenosContribuyentes tipo);

    public int verifRUCrep(String ruc);

    public boolean Eliminar();

    public ArrayList<EBuenosContribuyentes> ListaContriFiltro(String letras);
}
