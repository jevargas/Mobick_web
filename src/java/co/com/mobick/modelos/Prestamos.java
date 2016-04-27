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
@Table(name = "prestamos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamos.findAll", query = "SELECT p FROM Prestamos p WHERE p.eliminar = 0"),
    @NamedQuery(name = "Prestamos.findByIdprestamos", query = "SELECT p FROM Prestamos p WHERE p.idprestamos = :idprestamos"),
    @NamedQuery(name = "Prestamos.findByFecha", query = "SELECT p FROM Prestamos p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Prestamos.findByValor", query = "SELECT p FROM Prestamos p WHERE p.valor = :valor"),
    @NamedQuery(name = "Prestamos.findByCancelado", query = "SELECT p FROM Prestamos p WHERE p.cancelado = :cancelado"),
    @NamedQuery(name = "Prestamos.findByEliminar", query = "SELECT p FROM Prestamos p WHERE p.eliminar = :eliminar")})
public class Prestamos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idprestamos")
    private Integer idprestamos;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor")
    private int valor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cancelado")
    private boolean cancelado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar")
    private boolean eliminar;
    @JoinColumn(name = "Empleado_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Empleado empleadodocumento;

    public Prestamos() {
    }

    public Prestamos(Integer idprestamos) {
        this.idprestamos = idprestamos;
    }

    public Prestamos(Integer idprestamos, Date fecha, int valor, boolean cancelado, boolean eliminar) {
        this.idprestamos = idprestamos;
        this.fecha = fecha;
        this.valor = valor;
        this.cancelado = cancelado;
        this.eliminar = eliminar;
    }

    public Integer getIdprestamos() {
        return idprestamos;
    }

    public void setIdprestamos(Integer idprestamos) {
        this.idprestamos = idprestamos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
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
        hash += (idprestamos != null ? idprestamos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamos)) {
            return false;
        }
        Prestamos other = (Prestamos) object;
        if ((this.idprestamos == null && other.idprestamos != null) || (this.idprestamos != null && !this.idprestamos.equals(other.idprestamos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.Prestamos[ idprestamos=" + idprestamos + " ]";
    }
    
}
