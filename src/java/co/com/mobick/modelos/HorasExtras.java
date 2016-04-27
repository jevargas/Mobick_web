/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ew
 */
@Entity
@Table(name = "horas_extras")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HorasExtras.findAll", query = "SELECT h FROM HorasExtras h"),
    @NamedQuery(name = "HorasExtras.findByIdhorasExtras", query = "SELECT h FROM HorasExtras h WHERE h.idhorasExtras = :idhorasExtras"),
    @NamedQuery(name = "HorasExtras.findByFecha", query = "SELECT h FROM HorasExtras h WHERE h.fecha = :fecha"),
    @NamedQuery(name = "HorasExtras.findByCantHoras", query = "SELECT h FROM HorasExtras h WHERE h.cantHoras = :cantHoras"),
    @NamedQuery(name = "HorasExtras.findByValorHoraExtra", query = "SELECT h FROM HorasExtras h WHERE h.valorHoraExtra = :valorHoraExtra"),
    @NamedQuery(name = "HorasExtras.findByCancelado", query = "SELECT h FROM HorasExtras h WHERE h.cancelado = :cancelado")})
public class HorasExtras implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhoras_extras")
    private Integer idhorasExtras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cant_horas")
    private int cantHoras;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_hora_extra")
    private int valorHoraExtra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancelado")
    private boolean cancelado;
    @JoinColumn(name = "Empleado_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Empleado empleadodocumento;

    public HorasExtras() {
    }

    public HorasExtras(Integer idhorasExtras) {
        this.idhorasExtras = idhorasExtras;
    }

    public HorasExtras(Integer idhorasExtras, Date fecha, int cantHoras, int valorHoraExtra, boolean cancelado) {
        this.idhorasExtras = idhorasExtras;
        this.fecha = fecha;
        this.cantHoras = cantHoras;
        this.valorHoraExtra = valorHoraExtra;
        this.cancelado = cancelado;
    }

    public Integer getIdhorasExtras() {
        return idhorasExtras;
    }

    public void setIdhorasExtras(Integer idhorasExtras) {
        this.idhorasExtras = idhorasExtras;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCantHoras() {
        return cantHoras;
    }

    public void setCantHoras(int cantHoras) {
        this.cantHoras = cantHoras;
    }

    public int getValorHoraExtra() {
        return valorHoraExtra;
    }

    public void setValorHoraExtra(int valorHoraExtra) {
        this.valorHoraExtra = valorHoraExtra;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public Empleado getEmpleadodocumento() {
        return empleadodocumento;
    }

    public void setEmpleadodocumento(Empleado empleadodocumento) {
        this.empleadodocumento = empleadodocumento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idhorasExtras != null ? idhorasExtras.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HorasExtras)) {
            return false;
        }
        HorasExtras other = (HorasExtras) object;
        if ((this.idhorasExtras == null && other.idhorasExtras != null) || (this.idhorasExtras != null && !this.idhorasExtras.equals(other.idhorasExtras))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.HorasExtras[ idhorasExtras=" + idhorasExtras + " ]";
    }
    
}
