package co.com.mobick.modelos;

import co.com.mobick.modelos.UsuarioLogin;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:54")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Integer> idrol;
    public static volatile CollectionAttribute<Rol, UsuarioLogin> usuarioLoginCollection;
    public static volatile SingularAttribute<Rol, String> tipoRol;

}