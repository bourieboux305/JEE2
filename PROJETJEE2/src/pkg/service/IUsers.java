package pkg.service;

import beans.*;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;


public interface IUsers {

	/**
	 * this method allow to test if one people are connected
	 * @return
	 */
	public boolean isConnected();
	
	/**
	 * this method allow to get the person who is connected
	 * @return the personne connected
	 */
	public Personne getUser();
	
	/**
	 * this method allowed an user to connect 
	 * @param email the e-mail of the user
	 * @param pass_word his password
	 * @return 
	 */
	public void connection(String email,String pass_word);
	
	/**
	 * this method disconnect an user connected
	 */
	public void disconnection();
	
	/**
	 * this method allow some person to subscribe 
	 * @param personne
	 */
	public void registration(Personne personne);
	
	/**
	 * this method edit the information about the user connected
	 * @param personne the new information who will be applied
	 */
	public void modification(Personne personne);
	
	public Personne findByFirstName(String prenom);
	
	public Personne findByLastName(String nom);
	
	public Personne findByEmail(String mail);
	
	public List<Personne> allPersonne();
	
}
