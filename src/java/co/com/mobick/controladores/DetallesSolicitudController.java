package co.com.mobick.controladores;

import co.com.mobick.modelos.DetallesSolicitud;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.modelos.Solicitudes;
import co.com.mobick.operaciones.DetallesSolicitudFacade;
import static com.sun.faces.facelets.util.Path.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "detallesSolicitudController")
@SessionScoped
public class DetallesSolicitudController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.DetallesSolicitudFacade ejbFacade;
    private co.com.mobick.operaciones.SolicitudesFacade SolicitudejbFacade;

    private List<DetallesSolicitud> items = null;
    private DetallesSolicitud selected = new DetallesSolicitud();
    private double cantidad_total = 0;
    private List<DetallesSolicitud> listarDet = new ArrayList();
    private List<DetallesSolicitud> editar = new ArrayList();
    private Solicitudes sol = new Solicitudes();
    private Solicitudes soledit = new Solicitudes();
    private DetallesSolicitud detalle = new DetallesSolicitud();
    private List<DetallesSolicitud> agregarDetalles = new ArrayList();
    private int consec = 0;
    private int id_editar = 0;
    private int id_consultar = 0;
    //listas para editar la solicitud
    private List<DetallesSolicitud> agregarALaSolicitud =new ArrayList();
    private List<DetallesSolicitud> eliminarDeLaSolicitud =new ArrayList();

    public DetallesSolicitudController() {
    }

    public void agregar() {
        DetallesSolicitud det = new DetallesSolicitud();
        det.setConsecutivo(agregarDetalles.size() + 1);
        this.consec = det.getConsecutivo();
        det.setMueblesidMuebles(detalle.getMueblesidMuebles());
        det.setDetallesSolicitud(detalle.getDetallesSolicitud());
        det.setValorDetalle(detalle.getValorDetalle());
        this.agregarDetalles.add(det);
        //agrrgar det 
        this.agregarALaSolicitud.add(det);
        //----
        this.cantidad_total = 0;
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.cantidad_total += agregarDetalles.get(i).getValorDetalle();
        }
    }

    public String editarSolicitud(Solicitudes sol) {
        this.id_editar = sol.getIdSolicitudes();
        agregarDetalles = ejbFacade.consultarSolicitudesDet(id_editar);

        this.cantidad_total = 0;
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.cantidad_total += agregarDetalles.get(i).getValorDetalle();
        }
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.agregarDetalles.get(i).setConsecutivo(i + 1);
        }
        return "EditarSolicitud.xhtml?faces-redirect=true";
    }

    public String consultarSolicitud(Solicitudes sol) {
        this.id_consultar = sol.getIdSolicitudes();
        agregarDetalles = ejbFacade.consultarSolicitudesDet(id_consultar);
        this.cantidad_total = 0;
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.cantidad_total += agregarDetalles.get(i).getValorDetalle();
        }
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.agregarDetalles.get(i).setConsecutivo(i + 1);
        }
        return "ConsultarSolicitud.xhtml?faces-redirect=true";
    }

    public void guardarEditar() {
        boolean confirm = ejbFacade.guardarEditarSol(sol, agregarALaSolicitud, eliminarDeLaSolicitud);
        if (confirm) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Solicitud Actualizada!", "Correctamente"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error al Actualizar!", "Correctamente"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        }
    }

    public String limpiarDet() {
        detalle = new DetallesSolicitud();
        return "CrearSolicitud?faces-redirect=true";
    }

    public void retirarDetalle(DetallesSolicitud detRemo) {
        //agrrgar det 
        this.eliminarDeLaSolicitud.add(detRemo);
        //----
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.agregarDetalles.get(i).setConsecutivo(i + 1);
        }
        this.agregarDetalles.remove(detRemo);
        ejbFacade.remove(detRemo);
        this.cantidad_total = 0;
        for (int i = 0; i < agregarDetalles.size(); i++) {
            this.cantidad_total += agregarDetalles.get(i).getValorDetalle();
        }
    }

    public void guardarSolicitud() {
        java.util.Date fecha = new Date();
        fecha.getDate();
        Solicitudes soles = sol;
        sol.setFechaSolicitud(fecha);
        cantidad_total = 0;
        consec = 0;
        List<DetallesSolicitud> detallesoles = agregarDetalles;
        this.ejbFacade.guardarSolicitudYDetalles(detallesoles, soles);
    }

    public String limpiarS() {
        if (this.selected != null) {
            this.selected = new DetallesSolicitud();
            this.soledit = new Solicitudes();
            this.sol = new Solicitudes();
        }
        return "Solicitud.xhtml?faces-redirect=true";

    }

    public DetallesSolicitud nuevoDet() {
        detalle = new DetallesSolicitud();
        initializeEmbeddableKey();
        return detalle;
    }

    public Solicitudes getSol() {
        return sol;
    }

    public void setSol(Solicitudes sol) {
        this.sol = sol;
    }

    public DetallesSolicitud getSelected() {
        return selected;
    }

    public void setSelected(DetallesSolicitud selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private DetallesSolicitudFacade getFacade() {
        return ejbFacade;
    }

    public DetallesSolicitud prepareCreate() {
        selected = new DetallesSolicitud();
        sol = new Solicitudes();
        agregarALaSolicitud = new ArrayList();
        eliminarDeLaSolicitud = new ArrayList();
        agregarDetalles = new ArrayList();
        cantidad_total=0;
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("DetallesSolicitudCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("DetallesSolicitudUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("DetallesSolicitudDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<DetallesSolicitud> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<DetallesSolicitud> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<DetallesSolicitud> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public DetallesSolicitud getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallesSolicitud detalle) {
        this.detalle = detalle;
    }

    public List<DetallesSolicitud> getAgregarDetalles() {
        return agregarDetalles;
    }

    public void setAgregarDetalles(List<DetallesSolicitud> agregarDetalles) {
        this.agregarDetalles = agregarDetalles;
    }

    public double getCantidad_total() {
        return cantidad_total;
    }

    public void setCantidad_total(double cantidad_total) {
        this.cantidad_total = cantidad_total;
    }

    public int getConsec() {
        return consec;
    }

    public void setConsec(int consec) {
        this.consec = consec;

    }

    public List<DetallesSolicitud> getEditar() {
        return editar;
    }

    public void setEditar(List<DetallesSolicitud> editar) {
        this.editar = editar;
    }

    public Solicitudes getSoledit() {
        return soledit;
    }

    public void setSoledit(Solicitudes soledit) {
        this.soledit = soledit;
    }

    @FacesConverter(forClass = DetallesSolicitud.class)
    public static class DetallesSolicitudControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DetallesSolicitudController controller = (DetallesSolicitudController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "detallesSolicitudController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DetallesSolicitud) {
                DetallesSolicitud o = (DetallesSolicitud) object;
                return getStringKey(o.getIdDetallessolicitud());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), DetallesSolicitud.class.getName()});
                return null;
            }
        }

    }

}
