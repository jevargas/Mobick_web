package co.com.mobick.controladores;

import co.com.mobick.modelos.Empleado;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.operaciones.EmpleadoFacade;
import java.io.IOException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
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

@ManagedBean(name = "empleadoController")
@SessionScoped
public class EmpleadoController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.EmpleadoFacade ejbFacade;
    private List<Empleado> items = null;
    private List<Empleado> listaEmpleado = null;
    private Empleado selected = new Empleado();
    private long empleadosActivos=0;

    public EmpleadoController() {
    }
    
    public void calcularActivos(){
        this.empleadosActivos=ejbFacade.calcActivos();
    }

    public void ReporteEmpleadoHorasExtras() throws SQLException, JRException, IOException, NamingException {
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
//        JasperReport reporte = null;
//        reporte = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\alber\\Documents\\NetBeansProjects\\UltimatePrueba\\ultimate.1\\web\\WEB-INF\\StockProducto.jasper");
//        
        response.addHeader("Content-disposition",
                "attachment; filename=Reporte empleadoHorasExtras.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\horaExtras.jasper", null, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();
        
        System.out.println("cosa");
     
        
        FacesContext.getCurrentInstance().responseComplete();
   
    }
    
     public void EmpleadoPrestamos() throws SQLException, JRException, IOException, NamingException {
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
//        JasperReport reporte = null;
//        reporte = (JasperReport) JRLoader.loadObjectFromFile("C:\\Users\\alber\\Documents\\NetBeansProjects\\UltimatePrueba\\ultimate.1\\web\\WEB-INF\\StockProducto.jasper");
//        
        response.addHeader("Content-disposition",
                "attachment; filename=Reporte empleadoPrestamos.pdf");
        response.setContentType("application/pdf");

        JasperPrint jasperPrint = JasperFillManager.fillReport("C:\\Users\\ew\\Documents\\NetBeansProjects\\aplicativo_mobick\\src\\java\\reportes\\empleadoPretamos.jasper", null, conexion);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        exporter.exportReport();
        
        System.out.println("cosa");
     
        
        FacesContext.getCurrentInstance().responseComplete();
   
    }
    public Empleado getSelected() {
        return selected;
    }

    public void setSelected(Empleado selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private EmpleadoFacade getFacade() {
        return ejbFacade;
    }

    public Empleado prepareCreate() {
        selected = new Empleado();
        initializeEmbeddableKey();
        return selected;
    }
     public void limpiarE() {
        if (this.selected!=null) {
            this.selected = new Empleado();
        }
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("EmpleadoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("EmpleadoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("EmpleadoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Empleado> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }
    public List<Empleado> listar(){
        listaEmpleado= ejbFacade.listarEmpleado();
        
                return listaEmpleado;
    }
    
    public void borradoLogico(){
        
        ejbFacade.borrar(selected);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null,new FacesMessage("Empleado eliminado"));
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

    public List<Empleado> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Empleado> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public List<Empleado> getListaEmpleado() {
        return listaEmpleado;
    }

    public void setListaEmpleado(List<Empleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
    }

    public long getEmpleadosActivos() {
        return empleadosActivos;
    }

    public void setEmpleadosActivos(long empleadosActivos) {
        this.empleadosActivos = empleadosActivos;
    }

    @FacesConverter(forClass = Empleado.class)
    public static class EmpleadoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EmpleadoController controller = (EmpleadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "empleadoController");
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
            if (object instanceof Empleado) {
                Empleado o = (Empleado) object;
                return getStringKey(o.getDocumento());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Empleado.class.getName()});
                return null;
            }
        }

    }

}
