package co.com.mobick.controladores;

import co.com.mobick.modelos.Muebles;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.operaciones.MueblesFacade;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
//import javax.faces.view.ViewScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "mueblesController")
@SessionScoped
public class MueblesController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.MueblesFacade ejbFacade;
    private List<Muebles> items = null;
    private Muebles selected = new Muebles();
    private String tipoMueble;
    private long mueblesActivos=0;

    public MueblesController() {
    }

    public void calcularActivos() {
        this.mueblesActivos= ejbFacade.calcActivos();
    }

    public Muebles getSelected() {
        return selected;
    }

    public void setSelected(Muebles selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private MueblesFacade getFacade() {
        return ejbFacade;
    }

    public Muebles prepareCreate() {
        selected = new Muebles();
        initializeEmbeddableKey();
        return selected;
    }

    public void limpiarM() {
        if (this.selected != null) {
            this.selected = new Muebles();
        }
    }

    public void ReporteMuebles() throws SQLException, JRException, IOException, NamingException {
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
        parametro.put("TpoMueble", tipoMueble);

        response.addHeader("Content-disposition",
                "attachment; filename=ReporteMuebles.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\Muebles.jasper", parametro, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MueblesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MueblesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MueblesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = new Muebles(); // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Muebles> getItems() {
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

    public List<Muebles> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Muebles> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public long getMueblesActivos() {
        return mueblesActivos;
    }

    public void setMueblesActivos(long mueblesActivos) {
        this.mueblesActivos = mueblesActivos;
    }

    @FacesConverter(forClass = Muebles.class)
    public static class MueblesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MueblesController controller = (MueblesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "mueblesController");
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
            if (object instanceof Muebles) {
                Muebles o = (Muebles) object;
                return getStringKey(o.getIdMuebles());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Muebles.class.getName()});
                return null;
            }
        }

    }

}
