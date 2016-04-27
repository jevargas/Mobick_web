package co.com.mobick.controladores;

import co.com.mobick.modelos.Solicitudes;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.modelos.DetallesSolicitud;
import co.com.mobick.operaciones.SolicitudesFacade;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

@ManagedBean(name = "solicitudesController")
@SessionScoped
public class SolicitudesController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.SolicitudesFacade ejbFacade;
    private DetallesSolicitudController detallesSolicitudController;
    private List<Solicitudes> items = null;
    private List<Solicitudes> listaSolicitudes = null;
    private Solicitudes selected = new Solicitudes();
    private Date fechaVentas;
    private Date fechaVentaFin;
    private int numeroSolicitud;
    private int docuemntoCliente;
    private long cantPendientes;

    public void calcularPendientes() {
        cantPendientes = ejbFacade.calcPendientes();
    }

    public void guardarSolicitud() {
        System.out.println("entre al solicitud controller");
        System.out.println(selected.getTipoSolicitudIdtipoSolicitud());
        List<DetallesSolicitud> detalleLista = detallesSolicitudController.getAgregarDetalles();

        for (DetallesSolicitud detalleLista1 : detalleLista) {
            System.out.println(detalleLista1.getDetallesSolicitud());
        }

    }

    public void ReporteVentas() throws SQLException, JRException, IOException, NamingException {
        //Fill Map with params values
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        ServletOutputStream out = response.getOutputStream();

        //Connect with local datasource
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jndi_mobick");
        Connection conexion = null;
        conexion = ds.getConnection();
        conexion.setAutoCommit(true);

        Map<String, Object> parametro = new HashMap<String, Object>();

        parametro.put("fechaVenta", format.format(fechaVentas));
        parametro.put("fechaVentaFin", format.format(fechaVentaFin));
        response.addHeader("Content-disposition",
                "attachment; filename=reporteVentas.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\Ventass.jasper", parametro, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        System.out.println("cosa");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void ReporteSolicitudes() throws SQLException, JRException, IOException, NamingException {
        //Fill Map with params values
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
        ServletOutputStream out = response.getOutputStream();

        //Connect with local datasource
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jndi_mobick");
        Connection conexion = null;
        conexion = ds.getConnection();
        conexion.setAutoCommit(true);

        Map<String, Object> parametro = new HashMap<String, Object>();
        parametro.put("docuementoCliente", docuemntoCliente);
        parametro.put("idSolicitud", numeroSolicitud);

        response.addHeader("Content-disposition",
                "attachment; filename=ReporteSolicitud.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\Solicitud.jasper", parametro, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        System.out.println("cosa");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public Solicitudes getSelected() {
        return selected;
    }

    public void setSelected(Solicitudes selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SolicitudesFacade getFacade() {
        return ejbFacade;
    }

    public Solicitudes prepareCreate() {
        selected = new Solicitudes();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SolicitudesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Solicitudes> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Solicitudes> listar() {
        listaSolicitudes = ejbFacade.listarSolicitudeses();
        return listaSolicitudes;
    }

    public void borradoLogico() {

        ejbFacade.borrar(selected);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Solicitud eliminado"));
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

    public List<Solicitudes> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Solicitudes> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Solicitudes> getListaSolicitudes() {
        return listaSolicitudes;
    }

    public void setListaSolicitudes(List<Solicitudes> listaSolicitudes) {
        this.listaSolicitudes = listaSolicitudes;
    }

    public Date getFechaVentas() {
        return fechaVentas;
    }

    public void setFechaVentas(Date fechaVentas) {
        this.fechaVentas = fechaVentas;
    }

    public Date getFechaVentaFin() {
        return fechaVentaFin;
    }

    public void setFechaVentaFin(Date fechaVentaFin) {
        this.fechaVentaFin = fechaVentaFin;
    }

    public int getNumeroSolicitud() {
        return numeroSolicitud;
    }

    public void setNumeroSolicitud(int numeroSolicitud) {
        this.numeroSolicitud = numeroSolicitud;
    }

    public int getDocuemntoCliente() {
        return docuemntoCliente;
    }

    public void setDocuemntoCliente(int docuemntoCliente) {
        this.docuemntoCliente = docuemntoCliente;
    }

    public long getCantPendientes() {
        return cantPendientes;
    }

    public void setCantPendientes(long cantPendientes) {
        this.cantPendientes = cantPendientes;
    }

    @FacesConverter(forClass = Solicitudes.class)
    public static class SolicitudesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SolicitudesController controller = (SolicitudesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "solicitudesController");
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
            if (object instanceof Solicitudes) {
                Solicitudes o = (Solicitudes) object;
                return getStringKey(o.getIdSolicitudes());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Solicitudes.class.getName()});
                return null;
            }
        }

    }

}
