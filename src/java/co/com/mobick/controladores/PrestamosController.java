package co.com.mobick.controladores;

import co.com.mobick.modelos.Prestamos;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.operaciones.PrestamosFacade;
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

@ManagedBean(name = "prestamosController")
@SessionScoped
public class PrestamosController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.PrestamosFacade ejbFacade;
    private List<Prestamos> items = null;
    private List<Prestamos> listaPrestamos = null;
    private Prestamos selected;
    private int documentoPres;
    private long pendientesXcobrar = 0;

    public void calcularPendientes() {
        this.pendientesXcobrar = ejbFacade.calcPendientes();
    }

    public void ReportePrestamos() throws SQLException, JRException, IOException, NamingException {
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
        parametro.put("DocumentoPres", documentoPres);

        response.addHeader("Content-disposition",
                "attachment; filename=ReportePrestamos.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\Prestamos.jasper", parametro, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public PrestamosController() {
    }

    public Prestamos getSelected() {
        return selected;
    }

    public void setSelected(Prestamos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private PrestamosFacade getFacade() {
        return ejbFacade;
    }

    public Prestamos prepareCreate() {
        selected = new Prestamos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("PrestamosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("PrestamosUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("PrestamosDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Prestamos> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<Prestamos> listar() {
        listaPrestamos = ejbFacade.listarPrestamoses();
        return listaPrestamos;
    }

    public void borradoLogico() {

        ejbFacade.borrar(selected);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Prestamo eliminado"));
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

    public List<Prestamos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Prestamos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Prestamos> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(List<Prestamos> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }

    public int getDocumentoPres() {
        return documentoPres;
    }

    public void setDocumentoPres(int documentoPres) {
        this.documentoPres = documentoPres;
    }

    public long getPendientesXcobrar() {
        return pendientesXcobrar;
    }

    public void setPendientesXcobrar(long pendientesXcobrar) {
        this.pendientesXcobrar = pendientesXcobrar;
    }

    @FacesConverter(forClass = Prestamos.class)
    public static class PrestamosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PrestamosController controller = (PrestamosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "prestamosController");
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
            if (object instanceof Prestamos) {
                Prestamos o = (Prestamos) object;
                return getStringKey(o.getIdprestamos());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Prestamos.class.getName()});
                return null;
            }
        }

    }

}
