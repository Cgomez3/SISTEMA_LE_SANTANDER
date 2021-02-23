/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class ECuentaBancaria {
    
    private String DocProveedor;
    private int Codbanco;
    private String NomBanco;
    private String Moneda;
    private String Cuenta;
    private String Cuentaci;
    private Integer TipoCuenta;
    private String nomProveedor;
    private String Descripcion;

    
      public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String pDescripcion) {
        this.Descripcion = pDescripcion;
    }
    
    
     public String getnomProveedor() {
        return nomProveedor;
    }

    public void setnomProveedor(String pnomProveedor) {
        this.nomProveedor = pnomProveedor;
    }
    
  
    public Integer getTipoCuenta() {
        return TipoCuenta;
    }

    public void setTipoCuenta(Integer pTipoCuenta) {
        this.TipoCuenta = pTipoCuenta;
    }
    
    public String getNomBanco() {
        return NomBanco;
    }

    public void setNomBanco(String NomBanco) {
        this.NomBanco = NomBanco;
    }

    
    public String getDocProveedor() {
        return DocProveedor;
    }

    public void setDocProveedor(String DocProveedor) {
        this.DocProveedor = DocProveedor;
    }

    public int getCodbanco() {
        return Codbanco;
    }

    public void setCodbanco(int Codbanco) {
        this.Codbanco = Codbanco;
    }

    public String getmoneda() {
        return Moneda;
    }

    public void setMoneda(String Moneda) {
        this.Moneda = Moneda;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public String getCuentaci() {
        return Cuentaci;
    }

    public void setCuentaci(String Cuentaci) {
        this.Cuentaci = Cuentaci;
    }
    
    
    
    
}
