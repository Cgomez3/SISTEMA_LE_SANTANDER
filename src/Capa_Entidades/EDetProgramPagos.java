/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author HOME
 */
public class EDetProgramPagos {
   private int codprog;
   private int codcompr;
   private String estado;
   private String Banco;
   private String Moneda;
   private String CtaBancaria;
   private String UsuCrea;
   private String NroDocumento;
   private String tccomprobante;
   private String docProveedor;

    /**
     * @return the codprog
     */
    public int getCodprog() {
        return codprog;
    }

    /**
     * @param codprog the codprog to set
     */
    public void setCodprog(int codprog) {
        this.codprog = codprog;
    }

    /**
     * @return the codcompr
     */
    public int getCodcompr() {
        return codcompr;
    }

    /**
     * @param codcompr the codcompr to set
     */
    public void setCodcompr(int codcompr) {
        this.codcompr = codcompr;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the UsuCrea
     */
    public String getUsuCrea() {
        return UsuCrea;
    }

    /**
     * @param UsuCrea the UsuCrea to set
     */
    public void setUsuCrea(String UsuCrea) {
        this.UsuCrea = UsuCrea;
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
     * @return the CtaBancaria
     */
    public String getCtaBancaria() {
        return CtaBancaria;
    }

    /**
     * @param CtaBancaria the CtaBancaria to set
     */
    public void setCtaBancaria(String CtaBancaria) {
        this.CtaBancaria = CtaBancaria;
    }

    /**
     * @return the NroDocumento
     */
    public String getNroDocumento() {
        return NroDocumento;
    }

    /**
     * @param NroDocumento the NroDocumento to set
     */
    public void setNroDocumento(String NroDocumento) {
        this.NroDocumento = NroDocumento;
    }

    /**
     * @return the tccomprobante
     */
    public String getTccomprobante() {
        return tccomprobante;
    }

    /**
     * @param tccomprobante the tccomprobante to set
     */
    public void setTccomprobante(String tccomprobante) {
        this.tccomprobante = tccomprobante;
    }

    /**
     * @return the docProveedor
     */
    public String getDocProveedor() {
        return docProveedor;
    }

    /**
     * @param docProveedor the docProveedor to set
     */
    public void setDocProveedor(String docProveedor) {
        this.docProveedor = docProveedor;
    }
}
