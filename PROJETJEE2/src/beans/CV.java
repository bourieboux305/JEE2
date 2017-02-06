package beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="CV")
public class CV implements Serializable {

	@Id
	@GeneratedValue
	private int id_cv;
	
	@OneToOne
	@JoinColumn(name="id_personne")
	private Personne personne;
	
	@OneToMany(mappedBy="cv",cascade=CascadeType.PERSIST)
	private List<Activite> activite;

	public int getId_cv() {
		return id_cv;
	}

	public void setId_cv(int id_cv) {
		this.id_cv = id_cv;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public List<Activite> getActivite() {
		return activite;
	}

	public void setActivite(List<Activite> activite) {
		this.activite = activite;
	}
	
	
}
