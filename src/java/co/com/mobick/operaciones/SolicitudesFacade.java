/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mobick.operaciones;

import co.com.mobick.modelos.DetallesSolicitud;
import co.com.mobick.modelos.Solicitudes;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ew
 */
@Stateless
public class SolicitudesFacade extends AbstractFacade<Solicitudes> {

    @PersistenceContext(unitName = "aplicativo_mobickPU")
    private EntityManager em;
    private List<Solicitudes> lista;
    private Solicitudes solicitudes;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolicitudesFacade() {
        super(Solicitudes.class);
    }
    
    public Long calcPendientes(){
        Long pendientes = (Long) em.createQuery("SELECT COUNT(s.idSolicitudes) FROM Solicitudes s WHERE s.estadoSolicitudIdestadoSolicitud.idestadoSolicitud=1").getSingleResult();  
        return pendientes;
    }
    
    public List<Solicitudes> listarSolicitudeses() {
        lista = em.createNamedQuery("Solicitudes.findAll").getResultList();
        return lista;
    }

    public void borrar(Solicitudes solicitudesBorrado) {
        try {
            int query = em.createQuery("UPDATE Solicitudes s SET s.eliminar = 1 WHERE s.idSolicitudes = :idSol").setParameter("idSol", solicitudesBorrado.getIdSolicitudes()).executeUpdate();
        } catch (Exception e) {
            System.out.println("error al hacer borrado solicitudes");
            e.printStackTrace();
        }
    }
   /* 
    public void edit(Solicitudes SolicitudEditar){
        try {
            int query = em.createQuery("Update Solicitudes s SET s.estadoSolicitudIdestadoSolicitud = :idEstado , s.formadepagoidFormadepago , s.transportadordocumento , s.tipoSolicitudIdtipoSolicitud , s.empleadodocumento , s.fechaEntrega ");
        } catch (Exception e) {
        }
    }*/

    public List<Solicitudes> getLista() {
        return lista;
    }

    public void setLista(List<Solicitudes> lista) {
        this.lista = lista;
    }

    public Solicitudes getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(Solicitudes solicitudes) {
        this.solicitudes = solicitudes;
    }

    //detalle solicitud
    public void guardarSolicitudYDetalles(List<DetallesSolicitud> listaDetalles, Solicitudes solicitud) {


    }

}
