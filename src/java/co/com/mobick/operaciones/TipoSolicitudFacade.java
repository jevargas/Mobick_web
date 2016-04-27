/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.TipoSolicitud;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class TipoSolicitudFacade extends AbstractFacade<TipoSolicitud> {
    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoSolicitudFacade() {
        super(TipoSolicitud.class);
    }
    
}
