package controller;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import beans.Personne;
import pkg.service.IUsers;

@ManagedBean
@SessionScoped
public class UserController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	IUsers iUser;
	
	Personne user;
	boolean connected;
	public Personne getUser() {
		return user;
	}
	
	public boolean isConnected() {
		return connected;
	}
	
	public String signUp(){
		iUser.registration(user);
		System.out.println(iUser.isConnected());
		System.out.println(user.getId());
		connected=iUser.isConnected();
		return "hello";
	}
	
	public List<Personne> listePersonne(){
		List<Personne> liste;
		liste=iUser.allPersonne();
		return liste;	
	}
	
	public String logIn(){
		iUser.connection(user.getEmail(), user.getPass_word());
		if(iUser.isConnected())
			user=iUser.getUser();
		connected=iUser.isConnected();
		System.out.println("__login "+connected);
		return "hello";
	}
	
	public String logOut(){
		System.out.println("**log out");
		iUser.disconnection();
		user=new Personne();
		connected=iUser.isConnected();
		return "hello";
	}
	
	public String profile(){
		System.out.println("____"+connected);
		System.out.println(user.getNom());
		return "monProfile";
	}
	
	public String modifProfile(){
		System.out.println("** debut modification profile");
		iUser.modification(user);
		System.out.println("** fin modification profile");
		return "monProfile";
	}
	
	@PostConstruct
    void init() {
		user=new Personne();
		connected=false;
        System.err.println("Create " + this);
    }
	
	@PreDestroy
	public void affiche(){
		 System.err.println("Create " + this);
	}
}
