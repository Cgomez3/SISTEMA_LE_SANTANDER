/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDAgenteRetenedores;
import Capa_Entidades.EAgenteRetenedores;
import Capa_Logica.Interfaz.IAgenteRetenedores;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LAgenteRetenedores implements  IAgenteRetenedores{
    
    BDAgenteRetenedores bdage=new BDAgenteRetenedores(SesionUsuario.bddatos.getNombd());

    @Override
    public boolean InsertarAgente(EAgenteRetenedores tipo) {
        return bdage.InsertarAgente(tipo);
    }

    @Override
    public boolean ActualizarAgente(EAgenteRetenedores tipo) {
        return bdage.ActualizarAgente(tipo);
    }

    @Override
    public ArrayList<EAgenteRetenedores> ListaAgente() {
        return bdage.ListaAgente();
    }

    @Override
    public boolean Eliminar() {
       return bdage.Eliminar();
    }

    @Override
    public int VerificarAgente(String ruc) {
        return bdage.VerificarAgente(ruc);
    }

    @Override
    public ArrayList<EAgenteRetenedores> ListaFiltro(String letras) {
        return bdage.ListaFiltro(letras);
    }

    @Override
    public int VerificarConsolidado(int codprog, String docprov) {
        return bdage.VerificarConsolidado(codprog, docprov);
    }
    
}
