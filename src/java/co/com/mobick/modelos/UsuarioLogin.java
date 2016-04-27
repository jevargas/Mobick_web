/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.modelos;

import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
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
@Table(name = "usuario_login")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioLogin.findAll", query = "SELECT u FROM UsuarioLogin u"),
    @NamedQuery(name = "UsuarioLogin.findByUsuarioLogin", query = "SELECT u FROM UsuarioLogin u WHERE u.usuarioLogin = :usuarioLogin"),
    @NamedQuery(name = "UsuarioLogin.findByUsuario", query = "SELECT u FROM UsuarioLogin u WHERE u.usuario = :usuario"),
    @NamedQuery(name = "UsuarioLogin.findByContrasena", query = "SELECT u FROM UsuarioLogin u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "UsuarioLogin.findByEliminar", query = "SELECT u FROM UsuarioLogin u WHERE u.eliminar = :eliminar"),
    @NamedQuery(name = "UsuarioLogin.findByEstadoContrasena", query = "SELECT u FROM UsuarioLogin u WHERE u.estadoContrasena = :estadoContrasena")})
public class UsuarioLogin implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_login")
    private Integer usuarioLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "usuario")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "contrasena")
    private String contrasena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "eliminar")
    private boolean eliminar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadoContrasena")
    private boolean estadoContrasena;
    @JoinColumn(name = "Empleado_documento", referencedColumnName = "documento")
    @ManyToOne(optional = false)
    private Empleado empleadodocumento;
    @JoinColumn(name = "rol_idrol", referencedColumnName = "idrol")
    @ManyToOne(optional = false)
    private Rol rolIdrol;

    public UsuarioLogin() {
    }

    public UsuarioLogin(Integer usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public UsuarioLogin(Integer usuarioLogin, String usuario, String contrasena, boolean eliminar, boolean estadoContrasena) {
        this.usuarioLogin = usuarioLogin;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.eliminar = eliminar;
        this.estadoContrasena = estadoContrasena;
    }

    public Integer getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(Integer usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean getEliminar() {
        return eliminar;
    }

    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    public boolean getEstadoContrasena() {
        return estadoContrasena;
    }

    public void setEstadoContrasena(boolean estadoContrasena) {
        this.estadoContrasena = estadoContrasena;
    }

    public Empleado getEmpleadodocumento() {
        return empleadodocumento;
    }

    public void setEmpleadodocumento(Empleado empleadodocumento) {
        this.empleadodocumento = empleadodocumento;
    }

    public Rol getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(Rol rolIdrol) {
        this.rolIdrol = rolIdrol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioLogin != null ? usuarioLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioLogin)) {
            return false;
        }
        UsuarioLogin other = (UsuarioLogin) object;
        if ((this.usuarioLogin == null && other.usuarioLogin != null) || (this.usuarioLogin != null && !this.usuarioLogin.equals(other.usuarioLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.mobick.modelos.UsuarioLogin[ usuarioLogin=" + usuarioLogin + " ]";
    }

    public void setContrasena(string pw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}