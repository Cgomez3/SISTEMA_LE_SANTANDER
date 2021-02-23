/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica.Interfaz;

import Capa_Entidades.ECuentaBancaria;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public interface ICuentaBancaria {
    
    public ArrayList<ECuentaBancaria>listaCuentaBancaria();
    public boolean InsertarCuentaBancaria(ECuentaBancaria objpro);
    public boolean ActualizarCuentaBancaria(ECuentaBancaria objpro);
    public boolean eliminarCuenta(String cod);
    public ArrayList<ECuentaBancaria> ListarCuentaXRuc(String ruc);
    
    
}
