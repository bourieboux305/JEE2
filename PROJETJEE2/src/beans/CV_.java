package beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-20T01:14:44.625+0100")
@StaticMetamodel(CV.class)
public class CV_ {
	public static volatile SingularAttribute<CV, Integer> id_cv;
	public static volatile SingularAttribute<CV, Personne> personne;
	public static volatile ListAttribute<CV, Activite> activite;
}
