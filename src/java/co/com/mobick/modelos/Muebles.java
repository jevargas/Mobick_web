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
@Table(name = "muebles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Muebles.findAll", query = "SELECT m FROM Muebles m"),
    @NamedQuery(name = "Muebles.findByIdMuebles", query = "SELECT m FROM Muebles m WHERE m.idMuebles = :idMuebles"),
    @NamedQuery(name = "Muebles.findByTipoMueble", query = "SELECT m FROM Muebles m WHERE m.tipoMueble = :tipoMueble"),
    @NamedQuery(name = "Muebles.findByDetallesMueble", query = "SELECT m FROM Muebles m WHERE m.detallesMueble = :detallesMueble")})
public class Muebles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMuebles")
    private Integer idMuebles;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "tipo_mueble")
    private String tipoMueble;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "detalles_mueble")
    private String detallesMueble;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mueblesidMuebles")
    private Collection<DetallesSolicitud> detallesSolicitudCollection;

    public Muebles() {
    }

    public Muebles(Integer idMuebles) {
        this.idMuebles = idMuebles;
    }

    public Muebles(Integer idMuebles, String tipoMueble, String detallesMueble) {
        this.idMuebles = idMuebles;
        this.tipoMueble = tipoMueble;
        this.detallesMueble = detallesMueble;
    }

    public Integer getIdMuebles() {
        return idMuebles;
    }

    public void setIdMuebles(Integer idMuebles) {
        this.idMuebles = idMuebles;
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public String getDetallesMueble() {
        return detallesMueble;
    }

    public void setDetallesMueble(String detallesMueble) {
        this.detallesMueble = detallesMueble;
    }

    @XmlTransient
    public Collection<DetallesSolicitud> getDetallesSolicitudCollection() {
        return detallesSolicitudCollection;
    }

    public void setDetallesSolicitudCollection(Collection<DetallesSolicitud> detallesSolicitudCollection) {
        this.detallesSolicitudCollection = detallesSolicitudCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMuebles != null ? idMuebles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Muebles)) {
            return false;
        }
        Muebles other = (Muebles) object;
        if ((this.idMuebles == null && other.idMuebles != null) || (this.idMuebles != null && !this.idMuebles.equals(other.idMuebles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.Muebles[ idMuebles=" + idMuebles + " ]";
    }
    
}
