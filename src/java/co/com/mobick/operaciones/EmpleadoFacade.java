/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Empleado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
    private List<Empleado> lista;
    private Empleado empleado;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }

    public Long calcActivos() {
        Long activos = (Long) em.createQuery("SELECT COUNT(s.documento) FROM Empleado s").getSingleResult();
        return activos;
    }

    public List<Empleado> listarEmpleado() {
        lista = em.createNamedQuery("Empleado.findAll").getResultList();
        return lista;
    }

    public void borrar(Empleado empleado) {
        try {
            int query = em.createQuery("UPDATE Empleado e SET e.eliminar = 1 WHERE e.documento   = :doc").setParameter("doc", empleado.getDocumento()).executeUpdate();

        } catch (Exception e) {
            System.out.println("error al hacer borrado logico");
            e.printStackTrace();
        }

    }

    public List<Empleado> getLista() {
        return lista;
    }

    public void setLista(List<Empleado> lista) {
        this.lista = lista;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

}
