/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
    private List<Cliente> lista;
    private Cliente cliente;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    public Long calcActivos() {
        Long activos = (Long) em.createQuery("SELECT COUNT(s.documento) FROM Cliente s").getSingleResult();
        return activos;
    }

    public List<Cliente> listarCliente() {
        lista = em.createNamedQuery("Cliente.findAll").getResultList();
        return lista;
    }

    public void borrar(Cliente clienteBorrado) {
        try {
            int query = em.createQuery("UPDATE Cliente c SET c.eliminar = 1 WHERE c.documento = :doc").setParameter("doc", clienteBorrado.getDocumento()).executeUpdate();
        } catch (Exception e) {
            System.out.println("error al hacer borrado cliente");
            e.printStackTrace();
        }
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
