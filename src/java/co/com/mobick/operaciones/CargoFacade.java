/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.Cargo;
import java.sql.Connection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class CargoFacade extends AbstractFacade<Cargo> {
    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        Connection cnn = em.unwrap(java.sql.Connection.class);
        return em;
    }

    public CargoFacade() {
        super(Cargo.class);
    }
    
}
