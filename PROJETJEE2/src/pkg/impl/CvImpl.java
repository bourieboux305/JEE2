package pkg.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import beans.*;
import pkg.service.ICvImpl;
import pkg.service.IUsers;

@Stateless
public class CvImpl implements ICvImpl{

	@PersistenceContext(unitName = "myData")
    EntityManager em;
	@EJB
	IUsers user;
	
	@Override
	public void add(CV cv) {
		try{
			cv.setPersonne(user.findByEmail(cv.getPersonne().getEmail()));
			int i;
			for(i=0;i<cv.getActivite().size();i++)
				cv.getActivite().get(i).setCv(cv);
			em.persist(cv.getPersonne());
			em.persist(cv);
			em.flush();
		}
		catch(Exception e){
			System.out.println("add cv :"+e.getMessage());
		}
	}

	@Override
	public void modification(CV cv) {
		System.out.println("modification cv");
		CV currentCv=this.findByPerson(cv.getPersonne());
		int i,j;
		System.out.println("------------"+currentCv.getActivite().get(0).getId());
		for(i=0;i<currentCv.getActivite().size();i++){
			for(j=0;j<cv.getActivite().size();j++){
				if(currentCv.getActivite().get(i).getId()==cv.getActivite().get(j).getId()){
					currentCv.getActivite().get(i).setDescription(cv.getActivite().get(j).getDescription());
					currentCv.getActivite().get(i).setNature(cv.getActivite().get(j).getNature());
					currentCv.getActivite().get(i).setTitre(cv.getActivite().get(j).getTitre());
					currentCv.getActivite().get(i).setWebsite(cv.getActivite().get(j).getWebsite());
					System.out.println("----- modifcation act");
				}
			}
		}
		em.merge(currentCv);
		em.flush();
		System.out.println("fin modification");
	}

	@Override
	public void removeActivite(Activite activite) {
		// TODO Auto-generated method stub
		System.out.println("** debut de suppression activite");
		try{
		Activite act=(Activite) em.createQuery("select act from Activite act where act.id = :id")
				.setParameter("id", activite.getId())
				.getSingleResult();
			em.remove(act);
			em.flush();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		System.out.println("** fin de suppression activite");
	}

	@Override
	public CV findByPerson(Personne p) {
		CV cv = new CV();
		Personne pers=new Personne();
		List<Activite> liste=null;
		pers=user.findByEmail(p.getEmail());
		try{
			cv=(CV) em.createQuery("select cv from CV cv where cv.personne.id = :id_pers")
				.setParameter("id_pers", pers.getId())
				.getSingleResult();
			try{
				liste=(List<Activite>) em.createQuery("select Act from Activite Act where Act.cv.id_cv = :id_cv")
						.setParameter("id_cv", cv.getId_cv())
						.getResultList();
				cv.setActivite(liste);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		catch(Exception e){
			cv=null;
			System.out.println(e.getMessage());
		}
		return cv;
	}

	@Override
	public List<CV> allCV() {
		List<CV> listeCv=null;
		Personne pers=new Personne();
		List<Activite> liste=null;
		try{
			listeCv=(List<CV>) em.createQuery("select cv from CV cv").getResultList();
			int i;
			for(i=0;i<listeCv.size();i++){
				liste=new ArrayList<Activite>();
				liste=(List<Activite>) em.createQuery("select Act from Activite Act where Act.cv.id_cv = :id_cv")
						.setParameter("id_cv", listeCv.get(i).getId_cv())
						.getResultList();
				listeCv.get(i).setActivite(liste);
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return listeCv;
	}

	@Override
	public void addActivite(CV cv, Activite activite) {
		System.out.println("debut add activity");
		CV currentCv=this.findByPerson(cv.getPersonne());
		activite.setCv(currentCv);
		em.persist(activite);
		em.flush();
		System.out.println("fin add activity");
	}

}
