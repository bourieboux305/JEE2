package validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


import beans.Personne;
import pkg.service.ICvImpl;
import pkg.service.IUsers;

@ManagedBean
@RequestScoped
public class UnicityMail implements Validator {

	@EJB
	IUsers user;
	
    @Override
    public void validate(FacesContext ct, UIComponent comp, Object obj)
            throws ValidatorException {
        System.out.println("VALIDADOR UNICITY MAIL running ");
        String value = obj.toString();
        System.out.println(user);
        
        Pattern pattern;
        Matcher matcher;
        String exp="(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)";
        pattern=Pattern.compile(exp);
        matcher=pattern.matcher(value);
        if(!matcher.matches()){
        	FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "email incorrect", "format");
            throw new ValidatorException(msg);
        }
        
        Personne personne=user.findByEmail(value);
        if (personne!=null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "email déjà utilisé", "email dejà utilisé 2");
            throw new ValidatorException(msg);
        }
    }

}