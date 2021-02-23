/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EComprobProcesados {
//    "DocProveedor","Proveedor","Tipo de Comprobante", "NroComprobante", "Moneda", 
//        "MontoTotal", "TipoCuenta","NroCuenta", "Estado", "Observacion"
    private String DocProveedor;
    private String Proveedor;
    private String TipoComprobante;
    private String NroComprobante;
    private String Moneda;
    private double MontoTotal;
    private String TipoCuenta;
    private String NroCuenta;
    private String Estado;
    private String Observacion;

    /**
     * @return the DocProveedor
     */
    public String getDocProveedor() {
        return DocProveedor;
    }

    /**
     * @param DocProveedor the DocProveedor to set
     */
    public void setDocProveedor(String DocProveedor) {
        this.DocProveedor = DocProveedor;
    }

    /**
     * @return the Proveedor
     */
    public String getProveedor() {
        return Proveedor;
    }

    /**
     * @param Proveedor the Proveedor to set
     */
    public void setProveedor(String Proveedor) {
        this.Proveedor = Proveedor;
    }

    /**
     * @return the TipoComprobante
     */
    public String getTipoComprobante() {
        return TipoComprobante;
    }

    /**
     * @param TipoComprobante the TipoComprobante to set
     */
    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    /**
     * @return the NroComprobante
     */
    public String getNroComprobante() {
        return NroComprobante;
    }

    /**
     * @param NroComprobante the NroComprobante to set
     */
    public void setNroComprobante(String NroComprobante) {
        this.NroComprobante = NroComprobante;
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
     * @return the MontoTotal
     */
    public double getMontoTotal() {
        return MontoTotal;
    }

    /**
     * @param MontoTotal the MontoTotal to set
     */
    public void setMontoTotal(double MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    /**
     * @return the TipoCuenta
     */
    public String getTipoCuenta() {
        return TipoCuenta;
    }

    /**
     * @param TipoCuenta the TipoCuenta to set
     */
    public void setTipoCuenta(String TipoCuenta) {
        this.TipoCuenta = TipoCuenta;
    }

    /**
     * @return the NroCuenta
     */
    public String getNroCuenta() {
        return NroCuenta;
    }

    /**
     * @param NroCuenta the NroCuenta to set
     */
    public void setNroCuenta(String NroCuenta) {
        this.NroCuenta = NroCuenta;
    }

    /**
     * @return the Estado
     */
    public String getEstado() {
        return Estado;
    }

    /**
     * @param Estado the Estado to set
     */
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    /**
     * @return the Observacion
     */
    public String getObservacion() {
        return Observacion;
    }

    /**
     * @param Observacion the Observacion to set
     */
    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }
}
