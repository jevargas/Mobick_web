/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.modelos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ew
 */
@Entity
@Table(name = "solicitudes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitudes.findAll", query = "SELECT s FROM Solicitudes s WHERE  S.eliminar=0"),
    @NamedQuery(name = "Solicitudes.findByIdSolicitudes", query = "SELECT s FROM Solicitudes s WHERE s.idSolicitudes = :idSolicitudes"),
    @NamedQuery(name = "Solicitudes.findByFechaSolicitud", query = "SELECT s FROM Solicitudes s WHERE s.fechaSolicitud = :fechaSolicitud"),
    @NamedQuery(name = "Solicitudes.findByFechaEntrega", query = "SELECT s FROM Solicitudes s WHERE s.fechaEntrega = :fechaEntrega"),
    @NamedQuery(name = "Solicitudes.findByEliminar", query = "SELECT s FROM Solicitudes s WHERE s.eliminar = :eliminar")})
public class Solicitudes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSolicitudes")
    private Integer idSolicitudes;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.DATE)
    private Date fechaSolicitud;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar")
    private boolean eliminar;
    @JoinColumn(name = "Empleado_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Empleado empleadodocumento;
    @JoinColumn(name = "Cliente_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Cliente clientedocumento;
    @JoinColumn(name = "tipo_solicitud_idtipo_solicitud", referencedColumnName = "idtipo_solicitud")
    @ManyToOne(optional = false)
    private TipoSolicitud tipoSolicitudIdtipoSolicitud;
    @JoinColumn(name = "Transportador_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Transportador transportadordocumento;
    @JoinColumn(name = "Forma_de_pago_idForma_de_pago", referencedColumnName = "idForma_de_pago")
    @ManyToOne(optional = false)
    private FormaDePago formadepagoidFormadepago;
    @JoinColumn(name = "estado_solicitud_idestado_solicitud", referencedColumnName = "idestado_solicitud")
    @ManyToOne(optional = false)
    private EstadoSolicitud estadoSolicitudIdestadoSolicitud;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudesidSolicitudes")
    private Collection<DetallesSolicitud> detallesSolicitudCollection;

    public Solicitudes() {
    }

    public Solicitudes(Integer idSolicitudes) {
        this.idSolicitudes = idSolicitudes;
    }

    public Solicitudes(Integer idSolicitudes, Date fechaSolicitud, Date fechaEntrega, boolean eliminar) {
        this.idSolicitudes = idSolicitudes;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.eliminar = eliminar;
    }

    public Integer getIdSolicitudes() {
        return idSolicitudes;
    }

    public void setIdSolicitudes(Integer idSolicitudes) {
        this.idSolicitudes = idSolicitudes;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    public Cliente getClientedocumento() {
        return clientedocumento;
    }

    public void setClientedocumento(Cliente clientedocumento) {
        this.clientedocumento = clientedocumento;
    }

    public TipoSolicitud getTipoSolicitudIdtipoSolicitud() {
        return tipoSolicitudIdtipoSolicitud;
    }

    public void setTipoSolicitudIdtipoSolicitud(TipoSolicitud tipoSolicitudIdtipoSolicitud) {
        this.tipoSolicitudIdtipoSolicitud = tipoSolicitudIdtipoSolicitud;
    }

    public Transportador getTransportadordocumento() {
        return transportadordocumento;
    }

    public void setTransportadordocumento(Transportador transportadordocumento) {
        this.transportadordocumento = transportadordocumento;
    }

    public FormaDePago getFormadepagoidFormadepago() {
        return formadepagoidFormadepago;
    }

    public void setFormadepagoidFormadepago(FormaDePago formadepagoidFormadepago) {
        this.formadepagoidFormadepago = formadepagoidFormadepago;
    }

    public EstadoSolicitud getEstadoSolicitudIdestadoSolicitud() {
        return estadoSolicitudIdestadoSolicitud;
    }

    public void setEstadoSolicitudIdestadoSolicitud(EstadoSolicitud estadoSolicitudIdestadoSolicitud) {
        this.estadoSolicitudIdestadoSolicitud = estadoSolicitudIdestadoSolicitud;
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
        hash += (idSolicitudes != null ? idSolicitudes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitudes)) {
            return false;
        }
        Solicitudes other = (Solicitudes) object;
        if ((this.idSolicitudes == null && other.idSolicitudes != null) || (this.idSolicitudes != null && !this.idSolicitudes.equals(other.idSolicitudes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.Solicitudes[ idSolicitudes=" + idSolicitudes + " ]";
    }
    
}
