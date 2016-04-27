package co.com.mobick.modelos;

import co.com.mobick.modelos.Empleado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(HorasExtras.class)
public class HorasExtras_ { 

    public static volatile SingularAttribute<HorasExtras, Date> fecha;
    public static volatile SingularAttribute<HorasExtras, Integer> valorHoraExtra;
    public static volatile SingularAttribute<HorasExtras, Boolean> cancelado;
    public static volatile SingularAttribute<HorasExtras, Empleado> empleadodocumento;
    public static volatile SingularAttribute<HorasExtras, Integer> cantHoras;
    public static volatile SingularAttribute<HorasExtras, Integer> idhorasExtras;

}