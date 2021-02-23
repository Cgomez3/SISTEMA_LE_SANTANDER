/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EDetraccion {
    private String CodDetraccion;
    private String TipoDetra;
    private Double PorcentajeDetra;
    private String UsuarioCrea;
    private String FechaCrea;
    private String UsuarioModi;
    private String FechaModi;
    private String Descripcion;

    public String getCodDetraccion() {
        return CodDetraccion;
    }

    public void setCodDetraccion(String CodDetraccion) {
        this.CodDetraccion = CodDetraccion;
    }
    

    public String getTipoDetra() {
        return TipoDetra;
    }

    public void setTipoDetra(String TipoDetra) {
        this.TipoDetra = TipoDetra;
    }

    public Double getPorcentajeDetra() {
        return PorcentajeDetra;
    }

    public void setPorcentajeDetra(Double PorcentajeDetra) {
        this.PorcentajeDetra = PorcentajeDetra;
    }

    public String getUsuarioCrea() {
        return UsuarioCrea;
    }

    public void setUsuarioCrea(String UsuarioCrea) {
        this.UsuarioCrea = UsuarioCrea;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String FechaCrea) {
        this.FechaCrea = FechaCrea;
    }

    public String getUsuarioModi() {
        return UsuarioModi;
    }

    public void setUsuarioModi(String UsuarioModi) {
        this.UsuarioModi = UsuarioModi;
    }

    public String getFechaModi() {
        return FechaModi;
    }

    public void setFechaModi(String FechaModi) {
        this.FechaModi = FechaModi;
    }

    /**
     * @return the Descripcion
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * @param Descripcion the Descripcion to set
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    
    
    
}
