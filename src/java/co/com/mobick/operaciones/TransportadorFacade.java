/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Transportador;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class TransportadorFacade extends AbstractFacade<Transportador> {
    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransportadorFacade() {
        super(Transportador.class);
    }
    
}
