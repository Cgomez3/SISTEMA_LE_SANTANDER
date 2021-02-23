/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EDataConsultaCompras {
    private int Codigo;
    private String Periodo;
    private String Fecha;
    private int Registrados;
    private int Formateados;
    private String FechaExportacion;
    private int Exportados;

    /**
     * @return the Codigo
     */
    public int getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * @return the Periodo
     */
    public String getPeriodo() {
        return Periodo;
    }

    /**
     * @param Periodo the Periodo to set
     */
    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
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
     * @return the Registrados
     */
    public int getRegistrados() {
        return Registrados;
    }

    /**
     * @param Registrados the Registrados to set
     */
    public void setRegistrados(int Registrados) {
        this.Registrados = Registrados;
    }

    /**
     * @return the Formateados
     */
    public int getFormateados() {
        return Formateados;
    }

    /**
     * @param Formateados the Formateados to set
     */
    public void setFormateados(int Formateados) {
        this.Formateados = Formateados;
    }

    /**
     * @return the FechaExportacion
     */
    public String getFechaExportacion() {
        return FechaExportacion;
    }

    /**
     * @param FechaExportacion the FechaExportacion to set
     */
    public void setFechaExportacion(String FechaExportacion) {
        this.FechaExportacion = FechaExportacion;
    }

    /**
     * @return the Exportados
     */
    public int getExportados() {
        return Exportados;
    }

    /**
     * @param Exportados the Exportados to set
     */
    public void setExportados(int Exportados) {
        this.Exportados = Exportados;
    }
}
