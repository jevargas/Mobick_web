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
@Table(name = "tipo_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoSolicitud.findAll", query = "SELECT t FROM TipoSolicitud t"),
    @NamedQuery(name = "TipoSolicitud.findByIdtipoSolicitud", query = "SELECT t FROM TipoSolicitud t WHERE t.idtipoSolicitud = :idtipoSolicitud"),
    @NamedQuery(name = "TipoSolicitud.findByDescripcionSolicitud", query = "SELECT t FROM TipoSolicitud t WHERE t.descripcionSolicitud = :descripcionSolicitud")})
public class TipoSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_solicitud")
    private Integer idtipoSolicitud;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "descripcion_solicitud")
    private String descripcionSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoSolicitudIdtipoSolicitud")
    private Collection<Solicitudes> solicitudesCollection;

    public TipoSolicitud() {
    }

    public TipoSolicitud(Integer idtipoSolicitud) {
        this.idtipoSolicitud = idtipoSolicitud;
    }

    public TipoSolicitud(Integer idtipoSolicitud, String descripcionSolicitud) {
        this.idtipoSolicitud = idtipoSolicitud;
        this.descripcionSolicitud = descripcionSolicitud;
    }

    public Integer getIdtipoSolicitud() {
        return idtipoSolicitud;
    }

    public void setIdtipoSolicitud(Integer idtipoSolicitud) {
        this.idtipoSolicitud = idtipoSolicitud;
    }

    public String getDescripcionSolicitud() {
        return descripcionSolicitud;
    }

    public void setDescripcionSolicitud(String descripcionSolicitud) {
        this.descripcionSolicitud = descripcionSolicitud;
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
        hash += (idtipoSolicitud != null ? idtipoSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoSolicitud)) {
            return false;
        }
        TipoSolicitud other = (TipoSolicitud) object;
        if ((this.idtipoSolicitud == null && other.idtipoSolicitud != null) || (this.idtipoSolicitud != null && !this.idtipoSolicitud.equals(other.idtipoSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.TipoSolicitud[ idtipoSolicitud=" + idtipoSolicitud + " ]";
    }
    
}
