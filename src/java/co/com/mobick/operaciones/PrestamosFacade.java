/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Prestamos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class PrestamosFacade extends AbstractFacade<Prestamos> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
    private List<Prestamos> lista;
    private Prestamos prestamos;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrestamosFacade() {
        super(Prestamos.class);
    }

    public Long calcPendientes() {
        Long pendientes = (Long) em.createQuery("SELECT COUNT(s.idprestamos) FROM Prestamos s WHERE s.cancelado=0").getSingleResult();
        return pendientes;
    }

    public List<Prestamos> listarPrestamoses() {
        lista = em.createNamedQuery("Prestamos.findAll").getResultList();
        return lista;
    }

    public void borrar(Prestamos prestamosBorrado) {
        try {
            int query = em.createQuery("UPDATE Prestamos p SET p.eliminar = 1 WHERE p.idprestamos = :idpres").setParameter("idpres", prestamosBorrado.getIdprestamos()).executeUpdate();
        } catch (Exception e) {
            System.out.println("error al hacer borrado prestamos");
            e.printStackTrace();
        }
    }

    public List<Prestamos> getLista() {
        return lista;
    }

    public void setLista(List<Prestamos> lista) {
        this.lista = lista;
    }

    public Prestamos getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamos prestamos) {
        this.prestamos = prestamos;
    }

}
