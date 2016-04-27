package co.com.mobick.modelos;

import co.com.mobick.modelos.DetallesSolicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(Muebles.class)
public class Muebles_ { 

    public static volatile SingularAttribute<Muebles, Integer> idMuebles;
    public static volatile SingularAttribute<Muebles, String> detallesMueble;
    public static volatile CollectionAttribute<Muebles, DetallesSolicitud> detallesSolicitudCollection;
    public static volatile SingularAttribute<Muebles, String> tipoMueble;

}