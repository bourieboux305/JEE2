package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import beans.Activite;
import beans.CV;
import beans.Nature;
import beans.Personne;
import pkg.service.ICvImpl;

@ManagedBean
@RequestScoped
public class CvController {

	@EJB
	ICvImpl iCv;
	
	Activite activite;
	Personne currentPersonne;
	
	public CV getCvByPersonne(Personne p){
		CV cv;
		cv=iCv.findByPerson(p);
		return cv;
	}
	
	
	public Activite getActivite(){
		return activite;
	}
	
	public Personne getCurrentPersonne(){
		return currentPersonne;
	}
	
	@PostConstruct
	public void init(){
		System.out.println("creation cvC "+this);
		activite=new Activite();
		currentPersonne=new Personne();
	}
	
	public Nature[] getNatures(){
		return Nature.values();
	}
	
	public String addActivite(Personne p){
		CV cv;
		System.out.println("** debut d'ajout activite");
		System.out.println("nom : "+p.getNom());
	
		cv=iCv.findByPerson(p);
		if(cv==null){
			System.err.println("** cv null pas de cv");
			cv=new CV();
			cv.setPersonne(p);
			System.err.println("** "+cv.getPersonne().getNom());
			List<Activite> liste=new ArrayList<Activite>();
			liste.add(activite);
			cv.setActivite(liste);
			if(cv==null)
				System.err.println("** cv est null");
			else
				System.err.println("** cv plus null");
			System.err.println(activite.getTitre());
			iCv.add(cv);
		}
		else{
			iCv.addActivite(cv, activite);
		}
		
		System.out.println("** fin d'ajout activite");
		return "monProfile";
	}
	
	public String toModifCv(Activite act){
		activite=act;
		return "modifierCV";
	}
	
	public String modifierActivite(Personne p,Activite act){
		CV cv=iCv.findByPerson(p);
		act.setId(Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id")));
		List<Activite> liste=new ArrayList<Activite>();
		liste.add(act);
		cv.setActivite(liste);
		iCv.modification(cv);
		return "monProfile";
	}
	
	public String deleteActivite(Activite act){
		iCv.removeActivite(act);
		return "monProfile";
	}
	
	public String consultation(Personne personne){
		currentPersonne=personne;
		return "consultation";
	}
}
