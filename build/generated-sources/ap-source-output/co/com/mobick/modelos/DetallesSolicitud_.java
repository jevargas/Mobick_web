package co.com.mobick.modelos;

import co.com.mobick.modelos.Muebles;
import co.com.mobick.modelos.Solicitudes;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(DetallesSolicitud.class)
public class DetallesSolicitud_ { 

    public static volatile SingularAttribute<DetallesSolicitud, Integer> consecutivo;
    public static volatile SingularAttribute<DetallesSolicitud, Solicitudes> solicitudesidSolicitudes;
    public static volatile SingularAttribute<DetallesSolicitud, Muebles> mueblesidMuebles;
    public static volatile SingularAttribute<DetallesSolicitud, String> detallesSolicitud;
    public static volatile SingularAttribute<DetallesSolicitud, Integer> valorDetalle;
    public static volatile SingularAttribute<DetallesSolicitud, Integer> idDetallessolicitud;

}