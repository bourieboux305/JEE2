package Test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.BeforeClass;
import org.junit.Test;

import beans.Activite;
import beans.CV;
import beans.Nature;
import beans.Personne;
import pkg.service.ICvImpl;
import pkg.service.IUsers;

public class CvTest {

	private static EJBContainer ejbContainer;
	
	private static ICvImpl Icv;
	private static IUsers user;
	
	@BeforeClass
	public static void init() throws NamingException{
		ejbContainer = EJBContainer.createEJBContainer();
		 Object object = ejbContainer.getContext().lookup("java:global/PROJETJEE2/CvImpl");
	     assertTrue(object instanceof ICvImpl);
	     Icv=(ICvImpl)object;
	     object = ejbContainer.getContext().lookup("java:global/PROJETJEE2/Users");
	     assertTrue(object instanceof IUsers);
	     user=(IUsers)object;
	}
	
	public void testAddCv(){
		CV cv=new CV();
		Personne p=new Personne();
		p.setNom("rabetrena");
		p.setPrenom("anja");
		p.setEmail("anja@anja.fr");
		p.setWebsite("anja");
		p.setDateNaiss(new Date(1993,11,07));
		p.setPass_word("anja");
		user.registration(p);
		
		p=user.getUser();
		
		cv.setPersonne(p);
		
		Activite activite=new Activite();
		activite.setDescription("description");
		activite.setTitre("TOTO");
		activite.setNature(Nature.AUTRE);
		
		List<Activite> liste = new ArrayList<Activite>();
		liste.add(activite);
		
		cv.setActivite(liste);
		
		Icv.add(cv);
	}
	
	public void findByPesonneTest(){
		CV cv=new CV();
		Personne p=new Personne();
		p.setNom("rabetrena");
		p.setPrenom("anja");
		p.setEmail("anja@anja.fr");
		p.setWebsite("anja");
		p.setDateNaiss(new Date(1993,11,07));
		p.setPass_word("anja");
		
		cv=Icv.findByPerson(p);
		assertNotNull(cv);
		assertNotNull(cv.getActivite());
	}
	
	public void allCvTest(){
		List<CV> listeCv=null;
		listeCv=Icv.allCV();
		assertNotNull(listeCv);
		assertNotNull(listeCv.get(0).getActivite());
		assertEquals(1,listeCv.size());
	}
	
	public void modificationTest(){
		CV cv=new CV();
		Personne p=new Personne();
		p.setNom("rabetrena");
		p.setPrenom("anja");
		p.setEmail("anja@anja.fr");
		p.setWebsite("anja");
		p.setDateNaiss(new Date(1993,11,07));
		p.setPass_word("anja");
		
		cv.setPersonne(p);
		
		Activite activite=new Activite();
		activite.setDescription("description modifié");
		activite.setTitre("TITI");
		activite.setNature(Nature.AUTRE);
		activite.setId(101);
		
		List<Activite> liste = new ArrayList<Activite>();
		liste.add(activite);
		
		cv.setActivite(liste);
		
		Icv.modification(cv);
	}
	
	public void addActiviteTest(){
		CV cv=new CV();
		Personne p=new Personne();
		p.setNom("rabetrena");
		p.setPrenom("anja");
		p.setEmail("anja@anja.fr");
		p.setWebsite("anja");
		p.setDateNaiss(new Date(1993,11,07));
		p.setPass_word("anja");
		
		cv.setPersonne(p);
		
		Activite activite=new Activite();
		activite.setDescription("nouveau");
		activite.setTitre("NEW");
		activite.setNature(Nature.AUTRE);
		
		Icv.addActivite(cv, activite);
		
		cv=Icv.findByPerson(p);
		assertEquals(2,cv.getActivite().size());
	}
	
	@Test
	public void allTest(){
		this.testAddCv();
		this.findByPesonneTest();
		this.allCvTest();
		this.modificationTest();
		this.addActiviteTest();
	}
}
