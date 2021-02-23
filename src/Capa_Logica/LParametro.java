/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDParametro;
import Capa_Entidades.EParametro;
import Capa_Logica.Interfaz.IParametros;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LParametro implements IParametros{

     BDParametro bdpara=new BDParametro(SesionUsuario.bddatos.getNombd());
    
    
    @Override
    public ArrayList<EParametro> ListaParametros() {
        return bdpara.ListaParametros();
    }

    @Override
    public boolean ActualizarParametro(EParametro objpro) {
       return bdpara.ActualizarParametro(objpro);
    }

    @Override
    public ArrayList<EParametro> ListaBancoPrincipal() {
        return bdpara.ListaBancoPrincipal();
    }

    @Override
    public ArrayList<EParametro> ListaCuentasPrincipal() {
        return bdpara.ListaCuentasPrincipal();
    }

    @Override
    public int ConsultarCorrelativo() {
        return bdpara.ConsultarCorrelativo();
    }

    @Override
    public boolean ActualizarCorrelativo(int nuevo) {
        return bdpara.ActualizarCorrelativo(nuevo);
    }
    
    
    
}
