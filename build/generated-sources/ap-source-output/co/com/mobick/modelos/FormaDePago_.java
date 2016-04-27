package co.com.mobick.modelos;

import co.com.mobick.modelos.Solicitudes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:54")
@StaticMetamodel(FormaDePago.class)
public class FormaDePago_ { 

    public static volatile SingularAttribute<FormaDePago, String> descripcionPago;
    public static volatile SingularAttribute<FormaDePago, Integer> idFormadepago;
    public static volatile CollectionAttribute<FormaDePago, Solicitudes> solicitudesCollection;

}