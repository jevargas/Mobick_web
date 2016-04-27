package co.com.mobick.modelos;

import co.com.mobick.modelos.Empleado;
import co.com.mobick.modelos.Rol;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(UsuarioLogin.class)
public class UsuarioLogin_ { 

    public static volatile SingularAttribute<UsuarioLogin, Boolean> estadoContrasena;
    public static volatile SingularAttribute<UsuarioLogin, Boolean> eliminar;
    public static volatile SingularAttribute<UsuarioLogin, Empleado> empleadodocumento;
    public static volatile SingularAttribute<UsuarioLogin, Integer> usuarioLogin;
    public static volatile SingularAttribute<UsuarioLogin, String> usuario;
    public static volatile SingularAttribute<UsuarioLogin, String> contrasena;
    public static volatile SingularAttribute<UsuarioLogin, Rol> rolIdrol;

}