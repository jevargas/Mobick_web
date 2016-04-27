/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.modelos;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ew
 */
@Entity
@Table(name = "detalles_solicitud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetallesSolicitud.findAll", query = "SELECT d FROM DetallesSolicitud d"),
    @NamedQuery(name = "DetallesSolicitud.findByIdDetallessolicitud", query = "SELECT d FROM DetallesSolicitud d WHERE d.idDetallessolicitud = :idDetallessolicitud"),
    @NamedQuery(name = "DetallesSolicitud.findByConsecutivo", query = "SELECT d FROM DetallesSolicitud d WHERE d.consecutivo = :consecutivo"),
    @NamedQuery(name = "DetallesSolicitud.findByDetallesSolicitud", query = "SELECT d FROM DetallesSolicitud d WHERE d.detallesSolicitud = :detallesSolicitud"),
    @NamedQuery(name = "DetallesSolicitud.findByValorDetalle", query = "SELECT d FROM DetallesSolicitud d WHERE d.valorDetalle = :valorDetalle")})
public class DetallesSolicitud implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetalles_solicitud")
    private Integer idDetallessolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "consecutivo")
    private int consecutivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "detalles_solicitud")
    private String detallesSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valor_detalle")
    private int valorDetalle;
    @JoinColumn(name = "Solicitudes_idSolicitudes", referencedColumnName = "idSolicitudes")
    @ManyToOne(optional = false)
    private Solicitudes solicitudesidSolicitudes;
    @JoinColumn(name = "Muebles_idMuebles", referencedColumnName = "idMuebles")
    @ManyToOne(optional = false)
    private Muebles mueblesidMuebles;

    public DetallesSolicitud() {
    }

    public DetallesSolicitud(Integer idDetallessolicitud) {
        this.idDetallessolicitud = idDetallessolicitud;
    }

    public DetallesSolicitud(Integer idDetallessolicitud, int consecutivo, String detallesSolicitud, int valorDetalle) {
        this.idDetallessolicitud = idDetallessolicitud;
        this.consecutivo = consecutivo;
        this.detallesSolicitud = detallesSolicitud;
        this.valorDetalle = valorDetalle;
    }

    public Integer getIdDetallessolicitud() {
        return idDetallessolicitud;
    }

    public void setIdDetallessolicitud(Integer idDetallessolicitud) {
        this.idDetallessolicitud = idDetallessolicitud;
    }

    public int getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(int consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getDetallesSolicitud() {
        return detallesSolicitud;
    }

    public void setDetallesSolicitud(String detallesSolicitud) {
        this.detallesSolicitud = detallesSolicitud;
    }

    public int getValorDetalle() {
        return valorDetalle;
    }

    public void setValorDetalle(int valorDetalle) {
        this.valorDetalle = valorDetalle;
    }

    public Solicitudes getSolicitudesidSolicitudes() {
        return solicitudesidSolicitudes;
    }

    public void setSolicitudesidSolicitudes(Solicitudes solicitudesidSolicitudes) {
        this.solicitudesidSolicitudes = solicitudesidSolicitudes;
    }

    public Muebles getMueblesidMuebles() {
        return mueblesidMuebles;
    }

    public void setMueblesidMuebles(Muebles mueblesidMuebles) {
        this.mueblesidMuebles = mueblesidMuebles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetallessolicitud != null ? idDetallessolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetallesSolicitud)) {
            return false;
        }
        DetallesSolicitud other = (DetallesSolicitud) object;
        if ((this.idDetallessolicitud == null && other.idDetallessolicitud != null) || (this.idDetallessolicitud != null && !this.idDetallessolicitud.equals(other.idDetallessolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.DetallesSolicitud[ idDetallessolicitud=" + idDetallessolicitud + " ]";
    }
    
}
