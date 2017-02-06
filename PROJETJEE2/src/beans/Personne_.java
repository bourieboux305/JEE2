package beans;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-01-20T01:14:44.626+0100")
@StaticMetamodel(Personne.class)
public class Personne_ {
	public static volatile SingularAttribute<Personne, Integer> id;
	public static volatile SingularAttribute<Personne, String> nom;
	public static volatile SingularAttribute<Personne, String> prenom;
	public static volatile SingularAttribute<Personne, String> email;
	public static volatile SingularAttribute<Personne, String> website;
	public static volatile SingularAttribute<Personne, Date> dateNaiss;
	public static volatile SingularAttribute<Personne, String> pass_word;
}
