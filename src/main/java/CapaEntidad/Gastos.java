
package CapaEntidad;

import java.math.BigDecimal;
import java.util.Date;
/**
 *
 * @author e-p-l
 */
public class Gastos {
    private int idgasto,idProveedor;
    private Date fechaGasto;
    private String tipoDocumento,numeroDocumento,concepto,moneda;
    private BigDecimal importe;

    public Gastos(int idProveedor, Date fechaGasto, String tipoDocumento, String numeroDocumento, String concepto, String moneda, BigDecimal importe) {
        this.idProveedor = idProveedor;
        this.fechaGasto = fechaGasto;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.concepto = concepto;
        this.moneda = moneda;
        this.importe = importe;
    }

    public Gastos() {
    }

    public Gastos(int idgasto, int idProveedor, Date fechaGasto, String tipoDocumento, String numeroDocumento, String concepto, String moneda, BigDecimal importe) {
        this.idgasto = idgasto;
        this.idProveedor = idProveedor;
        this.fechaGasto = fechaGasto;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.concepto = concepto;
        this.moneda = moneda;
        this.importe = importe;
    }

    public int getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(int idgasto) {
        this.idgasto = idgasto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaGasto() {
        return fechaGasto;
    }

    public void setFechaGasto(Date fechaGasto) {
        this.fechaGasto = fechaGasto;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
}