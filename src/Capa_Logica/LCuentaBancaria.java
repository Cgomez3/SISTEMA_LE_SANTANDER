/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDCuentaBancaria;
import Capa_Entidades.ECuentaBancaria;
import Capa_Logica.Interfaz.ICuentaBancaria;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LCuentaBancaria implements  ICuentaBancaria{

    BDCuentaBancaria bdcuenta=new BDCuentaBancaria(SesionUsuario.bddatos.getNombd());
    
    
    @Override
    public ArrayList<ECuentaBancaria> listaCuentaBancaria() {
        return bdcuenta.listaCuentaBancaria();
    }

    @Override
    public boolean InsertarCuentaBancaria(ECuentaBancaria objpro) {
        return bdcuenta.InsertarCuentaBancaria(objpro);
    }

    @Override
    public boolean ActualizarCuentaBancaria(ECuentaBancaria objpro) {
        return bdcuenta.ActualizarCuentaBancaria(objpro);
    }

    @Override
    public boolean eliminarCuenta(String cod) {
         return bdcuenta.eliminarCuenta(cod);
    }

    @Override
    public ArrayList<ECuentaBancaria> ListarCuentaXRuc(String ruc) {
       return bdcuenta.ListarCuentaXRuc(ruc);
    }
    
    
    
}
