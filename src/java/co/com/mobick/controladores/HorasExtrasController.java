package co.com.mobick.controladores;

import co.com.mobick.modelos.HorasExtras;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.operaciones.HorasExtrasFacade;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

@ManagedBean(name = "horasExtrasController")
@SessionScoped
public class HorasExtrasController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.HorasExtrasFacade ejbFacade;
    private List<HorasExtras> items = null;
    private HorasExtras selected;
    private int docuemento;
    private long pendientes = 0;
    
    public void calcularPendientes() {
        this.pendientes = ejbFacade.calcPendientes();
    }

    public void ReporteHorasExtras() throws SQLException, JRException, IOException, NamingException {
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
        parametro.put("documento", docuemento);

        response.addHeader("Content-disposition",
                "attachment; filename=ReporteHorasExtras.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\HorasExtras.jasper", parametro, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();

        System.out.println("cosa");

        FacesContext.getCurrentInstance().responseComplete();

    }

    public HorasExtrasController() {
    }

    public HorasExtras getSelected() {
        return selected;
    }

    public void setSelected(HorasExtras selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private HorasExtrasFacade getFacade() {
        return ejbFacade;
    }

    public HorasExtras prepareCreate() {
        selected = new HorasExtras();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("HorasExtrasCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("HorasExtrasUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("HorasExtrasDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<HorasExtras> getItems() {
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

    public List<HorasExtras> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<HorasExtras> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public int getDocuemento() {
        return docuemento;
    }

    public void setDocuemento(int docuemento) {
        this.docuemento = docuemento;
    }

    public long getPendientes() {
        return pendientes;
    }

    public void setPendientes(long pendientes) {
        this.pendientes = pendientes;
    }

    @FacesConverter(forClass = HorasExtras.class)
    public static class HorasExtrasControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            HorasExtrasController controller = (HorasExtrasController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "horasExtrasController");
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
            if (object instanceof HorasExtras) {
                HorasExtras o = (HorasExtras) object;
                return getStringKey(o.getIdhorasExtras());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), HorasExtras.class.getName()});
                return null;
            }
        }

    }

}
