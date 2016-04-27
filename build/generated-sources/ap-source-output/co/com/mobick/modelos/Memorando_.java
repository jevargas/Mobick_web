package co.com.mobick.modelos;

import co.com.mobick.modelos.Empleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(Memorando.class)
public class Memorando_ { 

    public static volatile SingularAttribute<Memorando, Date> fecha;
    public static volatile SingularAttribute<Memorando, Boolean> eliminar;
    public static volatile SingularAttribute<Memorando, Empleado> empleadodocumento;
    public static volatile SingularAttribute<Memorando, String> observaciones;
    public static volatile SingularAttribute<Memorando, Integer> idmemorando;

}