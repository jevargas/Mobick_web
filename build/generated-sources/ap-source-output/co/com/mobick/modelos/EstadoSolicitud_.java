package co.com.mobick.modelos;

import co.com.mobick.modelos.Solicitudes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(EstadoSolicitud.class)
public class EstadoSolicitud_ { 

    public static volatile SingularAttribute<EstadoSolicitud, String> detallesEstado;
    public static volatile SingularAttribute<EstadoSolicitud, Integer> idestadoSolicitud;
    public static volatile CollectionAttribute<EstadoSolicitud, Solicitudes> solicitudesCollection;

}