/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EProgPagosCabecera {
    
    private int cod_programacion;
    private String estado_progpagos;
    private String Banco;
    private String Moneda;
    private String NumCuenta;
    private String Usucrea;
    private String Feccrea;
    private String UsuModi;
    private String FecModi;
    private String Fecha;
    private double Monto;

    public int getCod_programacion() {
        return cod_programacion;
    }

    public void setCod_programacion(int cod_programacion) {
        this.cod_programacion = cod_programacion;
    }

    public String getEstado_progpagos() {
        return estado_progpagos;
    }

    public void setEstado_progpagos(String estado_progpagos) {
        this.estado_progpagos = estado_progpagos;
    }

    public String getUsucrea() {
        return Usucrea;
    }

    public void setUsucrea(String Usucrea) {
        this.Usucrea = Usucrea;
    }

    public String getFeccrea() {
        return Feccrea;
    }

    public void setFeccrea(String Feccrea) {
        this.Feccrea = Feccrea;
    }

    public String getUsuModi() {
        return UsuModi;
    }

    public void setUsuModi(String UsuModi) {
        this.UsuModi = UsuModi;
    }

    public String getFecModi() {
        return FecModi;
    }

    public void setFecModi(String FecModi) {
        this.FecModi = FecModi;
    }

    /**
     * @return the Banco
     */
    public String getBanco() {
        return Banco;
    }

    /**
     * @param Banco the Banco to set
     */
    public void setBanco(String Banco) {
        this.Banco = Banco;
    }

    /**
     * @return the Moneda
     */
    public String getMoneda() {
        return Moneda;
    }

    /**
     * @param Moneda the Moneda to set
     */
    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    /**
     * @return the NumCuenta
     */
    public String getNumCuenta() {
        return NumCuenta;
    }

    /**
     * @param NumCuenta the NumCuenta to set
     */
    public void setNumCuenta(String NumCuenta) {
        this.NumCuenta = NumCuenta;
    }

    /**
     * @return the Fecha
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * @param Fecha the Fecha to set
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    /**
     * @return the Monto
     */
    public double getMonto() {
        return Monto;
    }

    /**
     * @param Monto the Monto to set
     */
    public void setMonto(double Monto) {
        this.Monto = Monto;
    }


    
    
}
