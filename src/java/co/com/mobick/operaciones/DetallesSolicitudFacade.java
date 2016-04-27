/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.DetallesSolicitud;
import co.com.mobick.modelos.Solicitudes;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ew
 */
@Stateless
public class DetallesSolicitudFacade extends AbstractFacade<DetallesSolicitud> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
    @EJB
    private SolicitudesFacade solicitudesFacade;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetallesSolicitudFacade() {
        super(DetallesSolicitud.class);
    }

    public boolean guardarEditarSol(Solicitudes sol, List<DetallesSolicitud> agregar, List<DetallesSolicitud> eliminar) {
        try {
            System.out.println(sol.getEstadoSolicitudIdestadoSolicitud().getDetallesEstado());
            solicitudesFacade.edit(sol);
            
            for (DetallesSolicitud el : eliminar) {
                
                em.createQuery("DELETE FROM DetallesSolicitud d WHERE d.idDetallessolicitud=:id ").setParameter("id", el.getIdDetallessolicitud()).executeUpdate();
                
            }
            for (DetallesSolicitud agr : agregar) {
                
                DetallesSolicitud detalle = new DetallesSolicitud();
                detalle = agr;
                detalle.setSolicitudesidSolicitudes(sol);
                
                create(detalle);
            }
            
            return true;
        } catch (Exception e) {
            
            System.out.println("errores al editar la solicitud y el detalle");
            
        }
        return false;
    }

    //detalle solicitud
    public void guardarSolicitudYDetalles(List<DetallesSolicitud> listaDetalles, Solicitudes solicitud) {
        try {
            solicitudesFacade.create(solicitud);
            int ultimaSolicitud = (int) em.createQuery("SELECT MAX(s.idSolicitudes) FROM Solicitudes s").getSingleResult();
            Solicitudes soluc = (Solicitudes) em.createQuery("SELECT s FROM Solicitudes s WHERE s.idSolicitudes = :id").setParameter("id", ultimaSolicitud).getSingleResult();
            for (int i = 0; i < listaDetalles.size(); i++) {
                listaDetalles.get(i).setSolicitudesidSolicitudes(soluc);
                create(listaDetalles.get(i));
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Cread", "exitosamente"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

        } catch (Exception e) {
            System.out.println("error en el query de detalle");
            e.printStackTrace();
        }
    }

    public List<DetallesSolicitud> consultarSolicitudesDet(int select) {
        try {
            List<DetallesSolicitud> listar = em.createQuery("SELECT d FROM DetallesSolicitud d WHERE d.solicitudesidSolicitudes.idSolicitudes = :id ").setParameter("id", select).getResultList();
            return listar;
        } catch (Exception e) {
            System.out.println("Error consultarSolicitudesDet");
            e.printStackTrace();
            return null;
        }
    }

}
