package co.com.mobick.modelos;

import co.com.mobick.modelos.Cargo;
import co.com.mobick.modelos.HorasExtras;
import co.com.mobick.modelos.Memorando;
import co.com.mobick.modelos.Prestamos;
import co.com.mobick.modelos.Solicitudes;
import co.com.mobick.modelos.UsuarioLogin;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> apellidos;
    public static volatile CollectionAttribute<Empleado, Memorando> memorandoCollection;
    public static volatile SingularAttribute<Empleado, Cargo> cargoIdcargo;
    public static volatile CollectionAttribute<Empleado, Prestamos> prestamosCollection;
    public static volatile CollectionAttribute<Empleado, HorasExtras> horasExtrasCollection;
    public static volatile SingularAttribute<Empleado, String> direccion;
    public static volatile SingularAttribute<Empleado, Integer> documento;
    public static volatile SingularAttribute<Empleado, String> eps;
    public static volatile SingularAttribute<Empleado, String> nombres;
    public static volatile CollectionAttribute<Empleado, UsuarioLogin> usuarioLoginCollection;
    public static volatile SingularAttribute<Empleado, Boolean> eliminar;
    public static volatile SingularAttribute<Empleado, String> correo;
    public static volatile SingularAttribute<Empleado, String> telefono;
    public static volatile CollectionAttribute<Empleado, Solicitudes> solicitudesCollection;

}