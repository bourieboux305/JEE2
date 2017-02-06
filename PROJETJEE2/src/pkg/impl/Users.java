package pkg.impl;

import javax.ejb.Stateful;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import java.util.List;

import javax.ejb.*;

import beans.Personne;
import pkg.service.IUsers;

@Stateful
public class Users implements IUsers{

	@PersistenceContext(unitName = "myData")
    EntityManager em;
	
	private Personne user=null;
	private boolean isConnected=false;
	
	
	public Personne getUser() {
		return user;
	}

	public void setUser(Personne user) {
		this.user = user;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public void connection(String email, String pass_word) {
		Personne p=new Personne();
		System.out.println("** debut connection");
		try{
			p=(Personne) em.createQuery("select p from Personne p where p.email LIKE :email")
					.setParameter("email", email)
					.getSingleResult();
			if(p.getPass_word().compareTo(pass_word)==0){
				this.setUser(p);
				this.setConnected(true);
				System.out.println("connection réussi");
			}
			else{
				this.setConnected(false);
				this.setUser(null);
				System.out.println("connection échoué");
			}
		}
		catch(Exception e){
			this.setConnected(false);
			this.setUser(null);
			System.out.println(e.getMessage());
		}
		System.out.println("** fin connection");
	}

	public void disconnection() {
		this.setUser(null);
		this.setConnected(false);
	}
	
	public void registration(Personne personne) {
		System.out.println("debut registration");
		try{
			em.persist(personne);
			em.flush();
			this.connection(personne.getEmail(), personne.getPass_word());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("fin registration");
	}

	public void modification(Personne personne) {
		try{
			em.merge(personne);
			em.flush();
			this.setUser(personne);
		}
		catch(Exception e){}
	}

	@Override
	public Personne findByFirstName(String prenom) {
		Personne p=null;
		try{
			p=(Personne) em.createQuery("select p from Personne p where p.prenom LIKE :prenom")
					.setParameter("prenom", prenom)
					.getSingleResult();
		}
		catch(Exception e){}
		return p;
	}

	@Override
	public Personne findByLastName(String nom) {
		Personne p=null;
		try{
			p=(Personne) em.createQuery("select p from Personne p where p.nom LIKE :nom")
					.setParameter("nom", nom)
					.getSingleResult();
		}
		catch(Exception e){}
		return p;
	}

	@Override
	public Personne findByEmail(String mail) {
		Personne p=null;
		try{
			p=(Personne) em.createQuery("select p from Personne p where p.email LIKE :email")
					.setParameter("email", mail)
					.getSingleResult();
		}
		catch(Exception e){}
		return p;
	}

	@Override
	public List<Personne> allPersonne() {
		List<Personne> liste=null;
		try{
			liste=(List<Personne>) em.createQuery("select p from Personne p ")
					.getResultList();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return liste;
	}
	

}
