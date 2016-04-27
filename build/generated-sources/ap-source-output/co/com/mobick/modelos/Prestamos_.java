package co.com.mobick.modelos;

import co.com.mobick.modelos.Empleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(Prestamos.class)
public class Prestamos_ { 

    public static volatile SingularAttribute<Prestamos, Date> fecha;
    public static volatile SingularAttribute<Prestamos, Boolean> eliminar;
    public static volatile SingularAttribute<Prestamos, Boolean> cancelado;
    public static volatile SingularAttribute<Prestamos, Empleado> empleadodocumento;
    public static volatile SingularAttribute<Prestamos, Integer> valor;
    public static volatile SingularAttribute<Prestamos, Integer> idprestamos;

}