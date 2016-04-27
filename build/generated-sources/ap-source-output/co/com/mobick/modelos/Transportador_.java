package co.com.mobick.modelos;

import co.com.mobick.modelos.Solicitudes;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-04-26T22:40:53")
@StaticMetamodel(Transportador.class)
public class Transportador_ { 

    public static volatile SingularAttribute<Transportador, String> apellidos;
    public static volatile SingularAttribute<Transportador, String> direccion;
    public static volatile SingularAttribute<Transportador, Integer> documento;
    public static volatile SingularAttribute<Transportador, String> telefono;
    public static volatile SingularAttribute<Transportador, String> nombres;
    public static volatile CollectionAttribute<Transportador, Solicitudes> solicitudesCollection;

}