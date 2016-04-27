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
@Table(name = "forma_de_pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormaDePago.findAll", query = "SELECT f FROM FormaDePago f"),
    @NamedQuery(name = "FormaDePago.findByIdFormadepago", query = "SELECT f FROM FormaDePago f WHERE f.idFormadepago = :idFormadepago"),
    @NamedQuery(name = "FormaDePago.findByDescripcionPago", query = "SELECT f FROM FormaDePago f WHERE f.descripcionPago = :descripcionPago")})
public class FormaDePago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idForma_de_pago")
    private Integer idFormadepago;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "descripcion_pago")
    private String descripcionPago;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formadepagoidFormadepago")
    private Collection<Solicitudes> solicitudesCollection;

    public FormaDePago() {
    }

    public FormaDePago(Integer idFormadepago) {
        this.idFormadepago = idFormadepago;
    }

    public FormaDePago(Integer idFormadepago, String descripcionPago) {
        this.idFormadepago = idFormadepago;
        this.descripcionPago = descripcionPago;
    }

    public Integer getIdFormadepago() {
        return idFormadepago;
    }

    public void setIdFormadepago(Integer idFormadepago) {
        this.idFormadepago = idFormadepago;
    }

    public String getDescripcionPago() {
        return descripcionPago;
    }

    public void setDescripcionPago(String descripcionPago) {
        this.descripcionPago = descripcionPago;
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
        hash += (idFormadepago != null ? idFormadepago.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormaDePago)) {
            return false;
        }
        FormaDePago other = (FormaDePago) object;
        if ((this.idFormadepago == null && other.idFormadepago != null) || (this.idFormadepago != null && !this.idFormadepago.equals(other.idFormadepago))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.FormaDePago[ idFormadepago=" + idFormadepago + " ]";
    }
    
}
