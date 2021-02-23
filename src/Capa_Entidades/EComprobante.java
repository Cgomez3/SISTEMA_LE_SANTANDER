/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author EXPERTYA
 */
public class EComprobante {
    
    private int CodComprobante;
    private String TipoComprobante;
    private String NumComprobante;
    private String FechaComprobante;
    private String DocProveedor;
    private String RazonSocial;
    private String GlosaComprante;
    private String ComprobanteSujeto;
    private String CodDetraccion;
    private String TipoDetra;
    private String FormaPago;
    private String DestinoPago;
    private int CodMoneda;
    private String NomMoneda;
    private double TCComprobante;
 
    private double MontoBase;
    private double MontoOtros;
    private double MontoIGV;
    private double MontoTotal;
    private double MontoRetDet;
    private double MontoPagar;
    
    private double MontoBaseDOL;
    private double MontoOtrosDOL;
    private double MontoIGVDOL;
    private double MontoTotalDOL;
    private double MontoRetDetDOL;
    private double MontoPagarDOL;
    
    private double Porcentaje;
    private double PorcIGV;
    
    private String TipoComprobanteRef;
    private String ComprobanteRef;
    private String RazonSocialRef;
    private String FechaRef;
    
    private String UsuCrea;
    private String FechaCrea;
    private String UsuModi;
    private String FechaModi;
    private String estado;

    private int CodProgramacion;
    private String cuentasoles;
    private String cuentadolares;
    private String ccisoles;
    private String ccidolares;
    private String referencia;
    private String NomProveedor;
    
    private String FechaContable;
    private String descripcionBienServicio;
    private String cuentaCorrienteBNDetraccion;
    private String Banco;
    private String NroFacturaRelacionada;
    private String NroCuentaContable;
    private Integer flagDetraccion;
    private double montoPagarRefSoles;
    private double montoPagarRefDol;
    
    
    public String getComprobanteRef() {
        return ComprobanteRef;
    }

    public void setComprobanteRef(String ComprobanteRef) {
        this.ComprobanteRef = ComprobanteRef;
    }

    public String getRazonSocialRef() {
        return RazonSocialRef;
    }

    public void setRazonSocialRef(String RazonSocialRef) {
        this.RazonSocialRef = RazonSocialRef;
    }

    public String getFechaRef() {
        return FechaRef;
    }

    public void setFechaRef(String FechaRef) {
        this.FechaRef = FechaRef;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    public double getMontoBaseDOL() {
        return MontoBaseDOL;
    }

    public void setMontoBaseDOL(double MontoBaseDOL) {
        this.MontoBaseDOL = MontoBaseDOL;
    }

    public double getMontoOtrosDOL() {
        return MontoOtrosDOL;
    }

    public void setMontoOtrosDOL(double MontoOtrosDOL) {
        this.MontoOtrosDOL = MontoOtrosDOL;
    }

    public double getMontoIGVDOL() {
        return MontoIGVDOL;
    }

    public void setMontoIGVDOL(double MontoIGVDOL) {
        this.MontoIGVDOL = MontoIGVDOL;
    }

    public double getMontoTotalDOL() {
        return MontoTotalDOL;
    }

    public void setMontoTotalDOL(double MontoTotalDOL) {
        this.MontoTotalDOL = MontoTotalDOL;
    }

    public double getMontoRetDetDOL() {
        return MontoRetDetDOL;
    }

    public void setMontoRetDetDOL(double MontoRetDetDOL) {
        this.MontoRetDetDOL = MontoRetDetDOL;
    }

    public double getMontoPagarDOL() {
        return MontoPagarDOL;
    }

    public void setMontoPagarDOL(double MontoPagarDOL) {
        this.MontoPagarDOL = MontoPagarDOL;
    }

    public double getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(double Porcentaje) {
        this.Porcentaje = Porcentaje;
    }

    public double getPorcIGV() {
        return PorcIGV;
    }

    public void setPorcIGV(double PorcIGV) {
        this.PorcIGV = PorcIGV;
    }

    public double getMontoIGV() {
        return MontoIGV;
    }

    public void setMontoIGV(double MontoIGV) {
        this.MontoIGV = MontoIGV;
    }

    public String getTipoDetra() {
        return TipoDetra;
    }

    public void setTipoDetra(String TipoDetra) {
        this.TipoDetra = TipoDetra;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }
    
    public String getNomMoneda() {
        return NomMoneda;
    }

    public void setNomMoneda(String NomMoneda) {
        this.NomMoneda = NomMoneda;
    }
    
    public String getComprobanteSujeto() {
        return ComprobanteSujeto;
    }

    public void setComprobanteSujeto(String ComprobanteSujeto) {
        this.ComprobanteSujeto = ComprobanteSujeto;
    }
       
    public int getCodComprobante() {
        return CodComprobante;
    }

    public void setCodComprobante(int CodComprobante) {
        this.CodComprobante = CodComprobante;
    }

    public String getTipoComprobante() {
        return TipoComprobante;
    }

    public void setTipoComprobante(String TipoComprobante) {
        this.TipoComprobante = TipoComprobante;
    }

    public String getNumComprobante() {
        return NumComprobante;
    }

    public void setNumComprobante(String NumComprobante) {
        this.NumComprobante = NumComprobante;
    }

    public String getFechaComprobante() {
        return FechaComprobante;
    }

    public void setFechaComprobante(String FechaComprobante) {
        this.FechaComprobante = FechaComprobante;
    }

    public String getDocProveedor() {
        return DocProveedor;
    }

    public void setDocProveedor(String DocProveedor) {
        this.DocProveedor = DocProveedor;
    }

    public String getGlosaComprante() {
        return GlosaComprante;
    }

    public void setGlosaComprante(String GlosaComprante) {
        this.GlosaComprante = GlosaComprante;
    }

    public String getCodDetraccion() {
        return CodDetraccion;
    }

    public void setCodDetraccion(String CodDetraccion) {
        this.CodDetraccion = CodDetraccion;
    }

    public String getFormaPago() {
        return FormaPago;
    }

    public void setFormaPago(String FormaPago) {
        this.FormaPago = FormaPago;
    }

    public String getDestinoPago() {
        return DestinoPago;
    }

    public void setDestinoPago(String DestinoPago) {
        this.DestinoPago = DestinoPago;
    }

    public int getCodMoneda() {
        return CodMoneda;
    }

    public void setCodMoneda(int CodMoneda) {
        this.CodMoneda = CodMoneda;
    }

    public double getTCComprobante() {
        return TCComprobante;
    }

    public void setTCComprobante(double TCComprobante) {
        this.TCComprobante = TCComprobante;
    }

    public double getMontoBase() {
        return MontoBase;
    }

    public void setMontoBase(double MontoBase) {
        this.MontoBase = MontoBase;
    }

    public double getMontoOtros() {
        return MontoOtros;
    }

    public void setMontoOtros(double MontoOtros) {
        this.MontoOtros = MontoOtros;
    }

    public double getMontoTotal() {
        return MontoTotal;
    }

    public void setMontoTotal(double MontoTotal) {
        this.MontoTotal = MontoTotal;
    }

    public double getMontoRetDet() {
        return MontoRetDet;
    }

    public void setMontoRetDet(double MontoRetDet) {
        this.MontoRetDet = MontoRetDet;
    }

    public double getMontoPagar() {
        return MontoPagar;
    }

    public void setMontoPagar(double MontoPagar) {
        this.MontoPagar = MontoPagar;
    }

    public String getUsuCrea() {
        return UsuCrea;
    }

    public void setUsuCrea(String UsuCrea) {
        this.UsuCrea = UsuCrea;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String FechaCrea) {
        this.FechaCrea = FechaCrea;
    }

    public String getUsuModi() {
        return UsuModi;
    }

    public void setUsuModi(String UsuModi) {
        this.UsuModi = UsuModi;
    }

    public String getFechaModi() {
        return FechaModi;
    }

    public void setFechaModi(String FechaModi) {
        this.FechaModi = FechaModi;
    }

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
     * @return the cuentasoles
     */
    public String getCuentasoles() {
        return cuentasoles;
    }

    /**
     * @param cuentasoles the cuentasoles to set
     */
    public void setCuentasoles(String cuentasoles) {
        this.cuentasoles = cuentasoles;
    }

    /**
     * @return the cuentadolares
     */
    public String getCuentadolares() {
        return cuentadolares;
    }

    /**
     * @param cuentadolares the cuentadolares to set
     */
    public void setCuentadolares(String cuentadolares) {
        this.cuentadolares = cuentadolares;
    }

    /**
     * @return the ccisoles
     */
    public String getCcisoles() {
        return ccisoles;
    }

    /**
     * @param ccisoles the ccisoles to set
     */
    public void setCcisoles(String ccisoles) {
        this.ccisoles = ccisoles;
    }

    /**
     * @return the ccidolares
     */
    public String getCcidolares() {
        return ccidolares;
    }

    /**
     * @param ccidolares the ccidolares to set
     */
    public void setCcidolares(String ccidolares) {
        this.ccidolares = ccidolares;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the NomProveedor
     */
    public String getNomProveedor() {
        return NomProveedor;
    }

    /**
     * @param NomProveedor the NomProveedor to set
     */
    public void setNomProveedor(String NomProveedor) {
        this.NomProveedor = NomProveedor;
    }

    /**
     * @return the TipoComprobanteRef
     */
    public String getTipoComprobanteRef() {
        return TipoComprobanteRef;
    }

    /**
     * @param TipoComprobanteRef the TipoComprobanteRef to set
     */
    public void setTipoComprobanteRef(String TipoComprobanteRef) {
        this.TipoComprobanteRef = TipoComprobanteRef;
    }

    /**
     * @return the FechaContable
     */
    public String getFechaContable() {
        return FechaContable;
    }

    /**
     * @param FechaContable the FechaContable to set
     */
    public void setFechaContable(String FechaContable) {
        this.FechaContable = FechaContable;
    }

    /**
     * @return the descripcionBienServicio
     */
    public String getDescripcionBienServicio() {
        return descripcionBienServicio;
    }

    /**
     * @param descripcionBienServicio the descripcionBienServicio to set
     */
    public void setDescripcionBienServicio(String descripcionBienServicio) {
        this.descripcionBienServicio = descripcionBienServicio;
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
     * @return the cuentaCorrienteProveedor
     */
    public String getCuentaCorrienteBNDetraccion() {
        return cuentaCorrienteBNDetraccion;
    }

    /**
     * @param cuentaCorrienteProveedor the cuentaCorrienteProveedor to set
     */
    public void setCuentaCorrienteBNDetraccion(String cuentaCorrienteProveedor) {
        this.cuentaCorrienteBNDetraccion = cuentaCorrienteProveedor;
    }

    /**
     * @return the NroFacturaRelacionada
     */
    public String getNroFacturaRelacionada() {
        return NroFacturaRelacionada;
    }

    /**
     * @param NroFacturaRelacionada the NroFacturaRelacionada to set
     */
    public void setNroFacturaRelacionada(String NroFacturaRelacionada) {
        this.NroFacturaRelacionada = NroFacturaRelacionada;
    }

    /**
     * @return the NroCuentaContable
     */
    public String getNroCuentaContable() {
        return NroCuentaContable;
    }

    /**
     * @param NroCuentaContable the NroCuentaContable to set
     */
    public void setNroCuentaContable(String NroCuentaContable) {
        this.NroCuentaContable = NroCuentaContable;
    }

    /**
     * @return the flagDetraccion
     */
    public Integer getFlagDetraccion() {
        return flagDetraccion;
    }

    /**
     * @param flagDetraccion the flagDetraccion to set
     */
    public void setFlagDetraccion(Integer flagDetraccion) {
        this.flagDetraccion = flagDetraccion;
    }

    /**
     * @return the montoPagarRefSoles
     */
    public double getMontoPagarRefSoles() {
        return montoPagarRefSoles;
    }

    /**
     * @param montoPagarRefSoles the montoPagarRefSoles to set
     */
    public void setMontoPagarRefSoles(double montoPagarRefSoles) {
        this.montoPagarRefSoles = montoPagarRefSoles;
    }

    /**
     * @return the montoPagarRefDol
     */
    public double getMontoPagarRefDol() {
        return montoPagarRefDol;
    }

    /**
     * @param montoPagarRefDol the montoPagarRefDol to set
     */
    public void setMontoPagarRefDol(double montoPagarRefDol) {
        this.montoPagarRefDol = montoPagarRefDol;
    }

  
    
    
    
    
    
    
    
    
    
        
    
    
}
