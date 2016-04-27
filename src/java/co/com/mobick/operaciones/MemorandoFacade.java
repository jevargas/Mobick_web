/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Memorando;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class MemorandoFacade extends AbstractFacade<Memorando> {
    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
      private List<Memorando> lista;
    private Memorando memorando;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MemorandoFacade() {
        super(Memorando.class);
    }
    
       public List<Memorando> listarMemorandos(){
        lista=em.createNamedQuery("Memorando.findAll").getResultList();
        return lista;
    }

    public void borrar(Memorando memorandoBorrado) {
        try {
           int query = em.createQuery("UPDATE Memorando m SET m.eliminar = 1 WHERE m.idmemorando = :idmem").setParameter("idmem", memorandoBorrado.getIdmemorando()).executeUpdate();
        } catch (Exception e) {
            System.out.println("error al hacer borrado Memorando");
            e.printStackTrace();
        }
    }

    public List<Memorando> getLista() {
        return lista;
    }

    public void setLista(List<Memorando> lista) {
        this.lista = lista;
    }

    public Memorando getMemorando() {
        return memorando;
    }

    public void setMemorando(Memorando memorando) {
        this.memorando = memorando;
    }
}
