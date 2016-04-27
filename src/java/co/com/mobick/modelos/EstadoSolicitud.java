/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.modelos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ew
 */
@Entity
@Table(name = "estado_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoSolicitud.findAll", query = "SELECT e FROM EstadoSolicitud e"),
    @NamedQuery(name = "EstadoSolicitud.findByIdestadoSolicitud", query = "SELECT e FROM EstadoSolicitud e WHERE e.idestadoSolicitud = :idestadoSolicitud"),
    @NamedQuery(name = "EstadoSolicitud.findByDetallesEstado", query = "SELECT e FROM EstadoSolicitud e WHERE e.detallesEstado = :detallesEstado")})
public class EstadoSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idestado_solicitud")
    private Integer idestadoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "detalles_estado")
    private String detallesEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoSolicitudIdestadoSolicitud")
    private Collection<Solicitudes> solicitudesCollection;

    public EstadoSolicitud() {
    }

    public EstadoSolicitud(Integer idestadoSolicitud) {
        this.idestadoSolicitud = idestadoSolicitud;
    }

    public EstadoSolicitud(Integer idestadoSolicitud, String detallesEstado) {
        this.idestadoSolicitud = idestadoSolicitud;
        this.detallesEstado = detallesEstado;
    }

    public Integer getIdestadoSolicitud() {
        return idestadoSolicitud;
    }

    public void setIdestadoSolicitud(Integer idestadoSolicitud) {
        this.idestadoSolicitud = idestadoSolicitud;
    }

    public String getDetallesEstado() {
        return detallesEstado;
    }

    public void setDetallesEstado(String detallesEstado) {
        this.detallesEstado = detallesEstado;
    }

    @XmlTransient
    public Collection<Solicitudes> getSolicitudesCollection() {
        return solicitudesCollection;
    }

    public void setSolicitudesCollection(Collection<Solicitudes> solicitudesCollection) {
        this.solicitudesCollection = solicitudesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestadoSolicitud != null ? idestadoSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoSolicitud)) {
            return false;
        }
        EstadoSolicitud other = (EstadoSolicitud) object;
        if ((this.idestadoSolicitud == null && other.idestadoSolicitud != null) || (this.idestadoSolicitud != null && !this.idestadoSolicitud.equals(other.idestadoSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.EstadoSolicitud[ idestadoSolicitud=" + idestadoSolicitud + " ]";
    }
    
}
