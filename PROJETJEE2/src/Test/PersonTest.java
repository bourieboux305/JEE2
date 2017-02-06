package Test;

import static org.junit.Assert.*;

import java.sql.Date;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.Personne;
import pkg.impl.Users;
import pkg.service.IUsers;


public class PersonTest {

	private static EJBContainer ejbContainer;
	
	private static IUsers user;
	
	@BeforeClass
	public static void init() throws NamingException{
		ejbContainer = EJBContainer.createEJBContainer();
		 Object object = ejbContainer.getContext().lookup("java:global/PROJETJEE2/Users");
	     assertTrue(object instanceof IUsers);
	     user=(IUsers)object;
	}
	
	private void testAdd(){
		 
		Personne p=new Personne();
		p.setNom("rabetrena");
		p.setPrenom("anja");
		p.setEmail("anja@anja.fr");
		p.setWebsite("anja");
		p.setDateNaiss(new Date(1993,11,07));
		p.setPass_word("anja");
		user.registration(p);
	}
	
	private void testFindByName(){
		Personne p;
		p=user.findByLastName("rabetrena");
		assertNotNull(p);
		p=user.findByLastName("toto");
		assertNull(p);
	}
	
	private void testConnection(){
		user.connection("anja@anja.fr", "anja");
		assertTrue(user.isConnected());
	}
	
	private void testDisconnection(){
		user.disconnection();
		assertFalse(user.isConnected());
	}
	
	private void testModification(){
		Personne p=new Personne();
		p=user.findByLastName("rabetrena");
		p.setPrenom("anjaharijaona");
		user.modification(p);
		p=user.findByFirstName("anjaharijaona");
		assertNotNull(p);
		System.out.println(p.getPrenom());
	}
	
	@Test
	public void testAll(){
		this.testAdd();
		this.testFindByName();
		this.testConnection();
		this.testModification();
		this.testDisconnection();
	}
}
