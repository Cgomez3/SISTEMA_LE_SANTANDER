/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Capa_Entidades;

/**
 *
 * @author EXPERTYA
 */
public class EProgPagosDetalle {
    
    private int cod_prodeta;
    private int codprogramacion;
    private int cod_comprobante;
    private String TipoComprobante;
    private String NumComprobante;
    private String FechaComprobante;
    private String DocProveedor;
        private String RazonSocial;
    private String GlosaComprobante;
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
    private String ComprobanteRef;
    private String RazonSocialRef;
    private String FechaRef;
    private String UsuCrea;
    private String FechaCrea;
    private String UsuModi;
    private String FechaModi;

    
    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getTipoDetra() {
        return TipoDetra;
    }

    public void setTipoDetra(String TipoDetra) {
        this.TipoDetra = TipoDetra;
    }

    public String getNomMoneda() {
        return NomMoneda;
    }

    public void setNomMoneda(String NomMoneda) {
        this.NomMoneda = NomMoneda;
    }

    public int getCod_prodeta() {
        return cod_prodeta;
    }

    public void setCod_prodeta(int cod_prodeta) {
        this.cod_prodeta = cod_prodeta;
    }

    public int getCodprogramacion() {
        return codprogramacion;
    }

    public void setCod_programacion(int codprogramacion) {
        this.codprogramacion = codprogramacion;
    }

    public int getCod_comprobante() {
        return cod_comprobante;
    }

    public void setCod_comprobante(int cod_comprobante) {
        this.cod_comprobante = cod_comprobante;
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

    public String getGlosaComprobante() {
        return GlosaComprobante;
    }

    public void setGlosaComprobante(String GlosaComprobante) {
        this.GlosaComprobante = GlosaComprobante;
    }

    public String getComprobanteSujeto() {
        return ComprobanteSujeto;
    }

    public void setComprobanteSujeto(String ComprobanteSujeto) {
        this.ComprobanteSujeto = ComprobanteSujeto;
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

    public double getMontoIGV() {
        return MontoIGV;
    }

    public void setMontoIGV(double MontoIGV) {
        this.MontoIGV = MontoIGV;
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
            
    
}
