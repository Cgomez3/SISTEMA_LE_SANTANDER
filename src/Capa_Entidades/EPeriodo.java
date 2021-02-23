/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EPeriodo {
    private int Codigo;
    private int Mes;
    private int Año;
    private String MesChar;

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
     * @return the Mes
     */
    public int getMes() {
        return Mes;
    }

    /**
     * @param Mes the Mes to set
     */
    public void setMes(int Mes) {
        this.Mes = Mes;
    }

    /**
     * @return the Año
     */
    public int getAño() {
        return Año;
    }

    /**
     * @param Año the Año to set
     */
    public void setAño(int Año) {
        this.Año = Año;
    }

    /**
     * @return the MesChar
     */
    public String getMesChar() {
        return MesChar;
    }

    /**
     * @param MesChar the MesChar to set
     */
    public void setMesChar(String MesChar) {
        this.MesChar = MesChar;
    }
}
