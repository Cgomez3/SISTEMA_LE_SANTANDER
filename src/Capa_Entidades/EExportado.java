/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EExportado {
    private int CodProgramacion;
    private String moneda;
    private String fecha;
    private String UsuCrea;
    private String FecCrea;

    /**
     * @return the CodProgramacion
     */
    public int getCodProgramacion() {
        return CodProgramacion;
    }

    /**
     * @param CodProgramacion the CodProgramacion to set
     */
    public void setCodProgramacion(int CodProgramacion) {
        this.CodProgramacion = CodProgramacion;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
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
     * @return the FecCrea
     */
    public String getFecCrea() {
        return FecCrea;
    }

    /**
     * @param FecCrea the FecCrea to set
     */
    public void setFecCrea(String FecCrea) {
        this.FecCrea = FecCrea;
    }
}
