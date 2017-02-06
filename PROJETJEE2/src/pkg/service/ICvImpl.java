package pkg.service;

import java.util.List;

import javax.ejb.Remote;

import beans.Activite;
import beans.CV;
import beans.Personne;

@Remote
public interface ICvImpl {

	/**
	 * this method allow to create a new CV
	 * @param cv the cv to create
	 */
	public void add(CV cv);
	
	/**
	 * this method edit the information about one CV
	 * @param cv
	 */
	public void modification(CV cv);
	
	public void addActivite(CV cv,Activite activite);
	
	/**
	 * this.method remove a CV in parameters
	 * @param cv
	 */
	public void removeActivite(Activite activite);
	
	/**
	 * this method show a cv of one person
	 * @param p
	 * @return CV found
	 */
	public CV findByPerson(Personne p);
	
	/**
	 * this method list all the CV existing
	 * @return
	 */
	public List<CV> allCV();
}
