/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Logica;

import Capa_Conexion.BDComprobante;
import Capa_Conexion.BDConsultaComprobante;
import Capa_Entidades.EComprobante;
import Capa_Entidades.EEstado;
import Capa_Logica.Interfaz.IConsultaComprobante;
import java.util.ArrayList;

/**
 *
 * @author EXPERTYA
 */
public class LConsultaComprobante implements IConsultaComprobante{
    BDConsultaComprobante bdconsul=new BDConsultaComprobante(SesionUsuario.bddatos.getNombd());

    @Override
    public ArrayList<EEstado> ListaEstadoCompro() {
        return bdconsul.ListaEstadoCompro();
    }

    @Override
    public ArrayList<EComprobante> ListaxEstado(String estado) {
       return bdconsul.ListaxEstado(estado);
    }

    @Override
    public ArrayList<EComprobante> ListaxRazonSocial(String razonsocial) {
        return bdconsul.ListaxRazonSocial(razonsocial);
    }

    @Override
    public ArrayList<EComprobante> ListaxFecha(String inicio, String fin) {
        return bdconsul.ListaxFecha(inicio, fin);
    }

    @Override
    public ArrayList<EComprobante> ListaxRazonyForma(String razon, String forma) {
        return bdconsul.ListaxRazonyForma(razon, forma);
    }

    @Override
    public ArrayList<EComprobante> ListaxFechayForma(String inicio, String fin, String forma) {
        return bdconsul.ListaxFechayForma(inicio, fin, forma);
    }
}
