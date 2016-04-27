package co.com.mobick.controladores;

import co.com.mobick.modelos.UsuarioLogin;
import co.com.mobick.controladores.util.JsfUtil;
import co.com.mobick.controladores.util.JsfUtil.PersistAction;
import co.com.mobick.modelos.Email;
import co.com.mobick.modelos.Empleado;
import co.com.mobick.operaciones.UsuarioLoginFacade;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
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
import org.apache.commons.codec.digest.DigestUtils;

@ManagedBean(name = "usuarioLoginController")
@SessionScoped
public class UsuarioLoginController implements Serializable {

    @EJB
    private co.com.mobick.operaciones.UsuarioLoginFacade ejbFacade;
    private Email email = new Email();
    private List<UsuarioLogin> items = null;
    private UsuarioLogin logeo;
    private List<UsuarioLogin> listaUsuarioLogin = null;
    private UsuarioLogin selected;
    private String contrasena;
    private String verificarcorreo = null;
    private String nuevacontra = null;

    public UsuarioLoginController() {
    }
  
    public String validarIngreso() {
        selected.setContrasena(DigestUtils.sha256Hex(selected.getContrasena()));
        this.logeo = ejbFacade.validar(selected.getUsuario(), selected.getContrasena());
        if (this.logeo != null) {
            if (this.logeo.getEstadoContrasena() == true) {
                return "admin/usuarioLogin/restaurarContrasena.xhtml?faces-redirect=true";
            }
            if (this.logeo.getRolIdrol().getTipoRol().equals("Administrador")) {
                return "index?faces-redirect=true";
            }
            if (this.logeo.getRolIdrol().getTipoRol().equals("Asistente Administrativo")) {
                return "index?faces-redirect=true";
            }
        }
        JsfUtil.addErrorMessage(new Exception("Usuario inexistente"), "Usuario inválido");
        return null;
    }

    public String reestablecerContra() {
        try {

            ejbFacade.ultimaActualizacion(logeo, nuevacontra);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario Actualzado", ":D"));
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            return "/faces/index?faces-redirect=true";

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR Usuario Actualzado", ":D"));
            return "restaurarContrasena";
        }
    }

    public void actualizarSesion(UsuarioLogin us, String pw) {
        ejbFacade.actualizarElUsuarioRecuperado(us, pw);
        System.out.println("Exito");

    }

    public String getCadenaAlfanumAleatoria() {
        String cadenaAleatoria = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < 8) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                cadenaAleatoria += c;
                i++;
            }
        }
        return cadenaAleatoria;
    }

    public String cerrarSesion() {
        this.logeo = null;
        return "login.xhtml?faces-redirect=true";
    }

    public void limpiarU() {
        if (this.selected != null) {
            this.selected = new UsuarioLogin();
        }
    }

    public void limpiarC() {
        verificarcorreo = null;
    }

    public boolean validarSesion() {
        try {
            if (this.logeo == null) {

                FacesContext.getCurrentInstance().getExternalContext().redirect("/aplicativo_mobick/faces/login.xhtml");
            }
        } catch (IOException ex) {
            Logger.getLogger(UsuarioLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.logeo != null;

    }

    public void RecuperarContrasena() {

        Empleado correo = ejbFacade.correo(this.verificarcorreo);
        if (correo == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Correo no existente", "En la base de datos"));
        } else {
            String nuevaContrasenia = getCadenaAlfanumAleatoria();
            System.out.println(correo.getDocumento());
            UsuarioLogin usuario = ejbFacade.consultarUsuario(correo);
            if (email.enviarCorreo(correo, usuario, nuevaContrasenia)) {
                actualizarSesion(usuario, nuevaContrasenia);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Revisa la Bandeja de tu Correo", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Tenemos Error en el Servidor", "Intenta mas tarde"));
            }
        }

    }

    public UsuarioLogin getSelected() {
        if (selected == null) {

            selected = new UsuarioLogin();

        }
        return selected;
    }

    public void setSelected(UsuarioLogin selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuarioLoginFacade getFacade() {
        return ejbFacade;
    }

    public UsuarioLogin prepareCreate() {
        selected = new UsuarioLogin();
        initializeEmbeddableKey();
        return selected;
    }

    public void create(){
        selected.setContrasena(DigestUtils.sha256Hex(selected.getContrasena()));
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioLoginCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.setContrasena(DigestUtils.sha256Hex(contrasena));
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuarioLoginUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuarioLoginDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UsuarioLogin> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    public List<UsuarioLogin> listar() {
        listaUsuarioLogin = ejbFacade.listarUsuarioLogins();
        return listaUsuarioLogin;
    }

    public void borradoLogico() {

        ejbFacade.borrar(selected);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("usuario eliminado"));
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

    public List<UsuarioLogin> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<UsuarioLogin> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    public UsuarioLogin getLogeo() {
        return logeo;
    }

    public void setLogeo(UsuarioLogin logeo) {
        this.logeo = logeo;
    }

    public List<UsuarioLogin> getListaUsuarioLogin() {
        return listaUsuarioLogin;
    }

    public void setListaUsuarioLogin(List<UsuarioLogin> listaUsuarioLogin) {
        this.listaUsuarioLogin = listaUsuarioLogin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getVerificarcorreo() {
        return verificarcorreo;
    }

    public void setVerificarcorreo(String verificarcorreo) {
        this.verificarcorreo = verificarcorreo;
    }

    public String getNuevacontra() {
        return nuevacontra;
    }

    public void setNuevacontra(String nuevacontra) {
        this.nuevacontra = nuevacontra;
    }

    @FacesConverter(forClass = UsuarioLogin.class)
    public static class UsuarioLoginControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuarioLoginController controller = (UsuarioLoginController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuarioLoginController");
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
            if (object instanceof UsuarioLogin) {
                UsuarioLogin o = (UsuarioLogin) object;
                return getStringKey(o.getUsuarioLogin());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UsuarioLogin.class.getName()});
                return null;
            }
        }

    }

}
