package co.com.mobick.modelos;

import co.com.mobick.modelos.Cliente;
import co.com.mobick.modelos.DetallesSolicitud;
import co.com.mobick.modelos.Empleado;
import co.com.mobick.modelos.EstadoSolicitud;
import co.com.mobick.modelos.FormaDePago;
import co.com.mobick.modelos.TipoSolicitud;
import co.com.mobick.modelos.Transportador;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(Solicitudes.class)
public class Solicitudes_ { 

    public static volatile SingularAttribute<Solicitudes, EstadoSolicitud> estadoSolicitudIdestadoSolicitud;
    public static volatile SingularAttribute<Solicitudes, Date> fechaSolicitud;
    public static volatile SingularAttribute<Solicitudes, FormaDePago> formadepagoidFormadepago;
    public static volatile SingularAttribute<Solicitudes, Boolean> eliminar;
    public static volatile SingularAttribute<Solicitudes, TipoSolicitud> tipoSolicitudIdtipoSolicitud;
    public static volatile SingularAttribute<Solicitudes, Empleado> empleadodocumento;
    public static volatile SingularAttribute<Solicitudes, Cliente> clientedocumento;
    public static volatile SingularAttribute<Solicitudes, Transportador> transportadordocumento;
    public static volatile CollectionAttribute<Solicitudes, DetallesSolicitud> detallesSolicitudCollection;
    public static volatile SingularAttribute<Solicitudes, Date> fechaEntrega;
    public static volatile SingularAttribute<Solicitudes, Integer> idSolicitudes;

}