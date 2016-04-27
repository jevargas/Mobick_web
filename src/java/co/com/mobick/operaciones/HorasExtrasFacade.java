/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.HorasExtras;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class HorasExtrasFacade extends AbstractFacade<HorasExtras> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HorasExtrasFacade() {
        super(HorasExtras.class);
    }

    public Long calcPendientes() {
        Long pendientes = (Long) em.createQuery("SELECT COUNT(s.idhorasExtras) FROM HorasExtras s WHERE s.cancelado=0").getSingleResult();
        return pendientes;
    }

}
