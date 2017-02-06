package beans;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-20T01:14:44.604+0100")
@StaticMetamodel(Activite.class)
public class Activite_ {
	public static volatile SingularAttribute<Activite, String> titre;
	public static volatile SingularAttribute<Activite, String> description;
	public static volatile SingularAttribute<Activite, String> website;
	public static volatile SingularAttribute<Activite, Nature> nature;
	public static volatile SingularAttribute<Activite, CV> cv;
	public static volatile SingularAttribute<Activite, Integer> id;
}
