/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Empleado;
import co.com.mobick.modelos.UsuarioLogin;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ew
 */
@Stateless
public class UsuarioLoginFacade extends AbstractFacade<UsuarioLogin> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
    private List<UsuarioLogin> lista;
    private UsuarioLogin usuarioLogin;
    @EJB
    co.com.mobick.operaciones.UsuarioLoginFacade usuarioLoginFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioLoginFacade() {
        super(UsuarioLogin.class);
    }

    public List<UsuarioLogin> listarUsuarioLogins() {
        lista = em.createNamedQuery("UsuarioLogin.findAll").getResultList();
        return lista;
    }

    public void ultimaActualizacion(UsuarioLogin us, String pw) {

        try {
            UsuarioLogin actualizado = new UsuarioLogin();
            actualizado.setUsuarioLogin(us.getUsuarioLogin());
            actualizado.setUsuario(us.getUsuario());
            actualizado.setRolIdrol(us.getRolIdrol());
            actualizado.setContrasena(DigestUtils.sha256Hex(pw));
            actualizado.setEstadoContrasena(true);

            int query = em.createQuery("UPDATE UsuarioLogin u SET u.contrasena=:contrasena, u.estadoContrasena=FALSE WHERE u.usuarioLogin=:id").setParameter("contrasena", actualizado.getContrasena()).setParameter("id", actualizado.getUsuarioLogin()).executeUpdate();

        } catch (Exception e) {
            System.out.println("error en actulizar");
            e.printStackTrace();
        }
    }

    public void actualizarElUsuarioRecuperado(UsuarioLogin us, String pw) {

        try {
            UsuarioLogin actualizado = new UsuarioLogin();
            actualizado.setUsuarioLogin(us.getUsuarioLogin());
            actualizado.setUsuario(us.getUsuario());
            actualizado.setRolIdrol(us.getRolIdrol());
            actualizado.setContrasena(DigestUtils.sha256Hex(pw));
            actualizado.setEstadoContrasena(false);

            int query = em.createQuery("UPDATE UsuarioLogin u SET u.contrasena=:contrasena, u.estadoContrasena=TRUE WHERE u.usuarioLogin=:id").setParameter("contrasena", actualizado.getContrasena()).setParameter("id", actualizado.getUsuarioLogin()).executeUpdate();

        } catch (Exception e) {
            System.out.println("error en actulizar");
            e.printStackTrace();
        }
    }

    public void borrar(UsuarioLogin usuarioLoginBorrado) {
        try {
            int query = em.createQuery("UPDATE UsuarioLogin u SET u.eliminar = 1 WHERE u.empleadodocumento = :doc").setParameter("doc", usuarioLoginBorrado.getEmpleadodocumento()).executeUpdate();
        } catch (Exception e) {
            System.out.println("error al hacer borrado usuario");
            e.printStackTrace();
        }
    }

    public UsuarioLogin validar(String usuario, String pw) {
        try {
            UsuarioLogin u = (UsuarioLogin) em.createQuery("select u from UsuarioLogin u WHERE u.usuario = :usuario and u.contrasena=:contrasena")
                    .setParameter("usuario", usuario)
                    .setParameter("contrasena", pw).getSingleResult();
            if (u != null) {
                return u;
            }

        } catch (Exception e) {
            System.out.println("Error en el query");
            e.printStackTrace();
        }
        return null;
    }

    public Empleado correo(String correoverificar) {

        try {
            return (Empleado) em.createQuery("SELECT e FROM Empleado e WHERE e.correo= :correo").setParameter("correo", correoverificar).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error en verificar correo");
            e.printStackTrace();
        }
        return null;
    }

    public UsuarioLogin consultarUsuario(Empleado correo) {
        try {
            return (UsuarioLogin) em.createQuery("SELECT u FROM UsuarioLogin u WHERE u.empleadodocumento.documento=:doc").setParameter("doc", correo.getDocumento()).getSingleResult();
        } catch (Exception e) {
            System.out.println("Error al consultar usuario");
            e.printStackTrace();
        }
        return null;
    }

    public List<UsuarioLogin> getLista() {
        return lista;
    }

    public void setLista(List<UsuarioLogin> lista) {
        this.lista = lista;
    }

    public UsuarioLogin getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(UsuarioLogin usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }
}
