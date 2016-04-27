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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "empleado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e"),
    @NamedQuery(name = "Empleado.findByDocumento", query = "SELECT e FROM Empleado e WHERE e.documento = :documento"),
    @NamedQuery(name = "Empleado.findByNombres", query = "SELECT e FROM Empleado e WHERE e.nombres = :nombres"),
    @NamedQuery(name = "Empleado.findByApellidos", query = "SELECT e FROM Empleado e WHERE e.apellidos = :apellidos"),
    @NamedQuery(name = "Empleado.findByTelefono", query = "SELECT e FROM Empleado e WHERE e.telefono = :telefono"),
    @NamedQuery(name = "Empleado.findByDireccion", query = "SELECT e FROM Empleado e WHERE e.direccion = :direccion"),
    @NamedQuery(name = "Empleado.findByEps", query = "SELECT e FROM Empleado e WHERE e.eps = :eps"),
    @NamedQuery(name = "Empleado.findByEliminar", query = "SELECT e FROM Empleado e WHERE e.eliminar = :eliminar"),
    @NamedQuery(name = "Empleado.findByCorreo", query = "SELECT e FROM Empleado e WHERE e.correo = :correo")})
public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "documento")
    private Integer documento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "EPS")
    private String eps;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar")
    private boolean eliminar;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "correo")
    private String correo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadodocumento")
    private Collection<UsuarioLogin> usuarioLoginCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadodocumento")
    private Collection<Prestamos> prestamosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadodocumento")
    private Collection<Memorando> memorandoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadodocumento")
    private Collection<Solicitudes> solicitudesCollection;
    @JoinColumn(name = "cargo_idcargo", referencedColumnName = "idcargo")
    @ManyToOne(optional = false)
    private Cargo cargoIdcargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empleadodocumento")
    private Collection<HorasExtras> horasExtrasCollection;

    public Empleado() {
    }

    public Empleado(Integer documento) {
        this.documento = documento;
    }

    public Empleado(Integer documento, String nombres, String apellidos, String telefono, String direccion, String eps, boolean eliminar, String correo) {
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.eps = eps;
        this.eliminar = eliminar;
        this.correo = correo;
    }

    public Integer getDocumento() {
        return documento;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @XmlTransient
    public Collection<UsuarioLogin> getUsuarioLoginCollection() {
        return usuarioLoginCollection;
    }

    public void setUsuarioLoginCollection(Collection<UsuarioLogin> usuarioLoginCollection) {
        this.usuarioLoginCollection = usuarioLoginCollection;
    }

    @XmlTransient
    public Collection<Prestamos> getPrestamosCollection() {
        return prestamosCollection;
    }

    public void setPrestamosCollection(Collection<Prestamos> prestamosCollection) {
        this.prestamosCollection = prestamosCollection;
    }

    @XmlTransient
    public Collection<Memorando> getMemorandoCollection() {
        return memorandoCollection;
    }

    public void setMemorandoCollection(Collection<Memorando> memorandoCollection) {
        this.memorandoCollection = memorandoCollection;
    }

    @XmlTransient
    public Collection<Solicitudes> getSolicitudesCollection() {
        return solicitudesCollection;
    }

    public void setSolicitudesCollection(Collection<Solicitudes> solicitudesCollection) {
        this.solicitudesCollection = solicitudesCollection;
    }

    public Cargo getCargoIdcargo() {
        return cargoIdcargo;
    }

    public void setCargoIdcargo(Cargo cargoIdcargo) {
        this.cargoIdcargo = cargoIdcargo;
    }

    @XmlTransient
    public Collection<HorasExtras> getHorasExtrasCollection() {
        return horasExtrasCollection;
    }

    public void setHorasExtrasCollection(Collection<HorasExtras> horasExtrasCollection) {
        this.horasExtrasCollection = horasExtrasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (documento != null ? documento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empleado)) {
            return false;
        }
        Empleado other = (Empleado) object;
        if ((this.documento == null && other.documento != null) || (this.documento != null && !this.documento.equals(other.documento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.Empleado[ documento=" + documento + " ]";
    }
    
}

