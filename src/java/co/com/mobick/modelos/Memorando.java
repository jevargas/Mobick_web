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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ew
 */
@Entity
@Table(name = "memorando")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Memorando.findAll", query = "SELECT m FROM Memorando m WHERE m.eliminar=0"),
    @NamedQuery(name = "Memorando.findByIdmemorando", query = "SELECT m FROM Memorando m WHERE m.idmemorando = :idmemorando"),
    @NamedQuery(name = "Memorando.findByFecha", query = "SELECT m FROM Memorando m WHERE m.fecha = :fecha"),
    @NamedQuery(name = "Memorando.findByObservaciones", query = "SELECT m FROM Memorando m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "Memorando.findByEliminar", query = "SELECT m FROM Memorando m WHERE m.eliminar = :eliminar")})
public class Memorando implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmemorando")
    private Integer idmemorando;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "observaciones")
    private String observaciones;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar")
    private boolean eliminar;
    @JoinColumn(name = "Empleado_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Empleado empleadodocumento;

    public Memorando() {
    }

    public Memorando(Integer idmemorando) {
        this.idmemorando = idmemorando;
    }

    public Memorando(Integer idmemorando, Date fecha, String observaciones, boolean eliminar) {
        this.idmemorando = idmemorando;
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.eliminar = eliminar;
    }

    public Integer getIdmemorando() {
        return idmemorando;
    }

    public void setIdmemorando(Integer idmemorando) {
        this.idmemorando = idmemorando;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
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
        hash += (idmemorando != null ? idmemorando.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memorando)) {
            return false;
        }
        Memorando other = (Memorando) object;
        if ((this.idmemorando == null && other.idmemorando != null) || (this.idmemorando != null && !this.idmemorando.equals(other.idmemorando))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.Memorando[ idmemorando=" + idmemorando + " ]";
    }
    
}
