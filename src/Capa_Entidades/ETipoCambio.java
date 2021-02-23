/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class ETipoCambio {
    private int CodTipoCambio;
    private String Fecha;
    private Double PrecioCompra;
    private Double PrecioVenta;
    private String UsuCrea;
    private String FecCrea;
    private String UsuModi;
    private String FecModi;
    
    

    public void setCodTipoCambio(int CodTipoCambio) {
        this.CodTipoCambio = CodTipoCambio;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public void setPrecioCompra(Double PrecioCompra) {
        this.PrecioCompra = PrecioCompra;
    }

    public void setPrecioVenta(Double PrecioVenta) {
        this.PrecioVenta = PrecioVenta;
    }

    public void setUsuCrea(String UsuCrea) {
        this.UsuCrea = UsuCrea;
    }

    public void setFecCrea(String FecCrea) {
        this.FecCrea = FecCrea;
    }

    public void setUsuModi(String UsuModi) {
        this.UsuModi = UsuModi;
    }

    public void setFecModi(String FecModi) {
        this.FecModi = FecModi;
    }

    
    
    public int getCodTipoCambio() {
        return CodTipoCambio;
    }

    public String getFecha() {
        return Fecha;
    }

    public Double getPrecioCompra() {
        return PrecioCompra;
    }

    public Double getPrecioVenta() {
        return PrecioVenta;
    }

    public String getUsuCrea() {
        return UsuCrea;
    }

    public String getFecCrea() {
        return FecCrea;
    }

    public String getUsuModi() {
        return UsuModi;
    }

    public String getFecModi() {
        return FecModi;
    }
    
    
}
