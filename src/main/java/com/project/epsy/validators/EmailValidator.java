/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.project.epsy.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.faces.application.FacesMessage;
import javax.faces.validator.FacesValidator;

/**
 *
 * @author Cuneyt
 */
@FacesValidator("com.project.epsy.validators.EmailValidator")
public class EmailValidator implements Validator {
    
    private static final String mailPattern = "^\\S+@\\S+\\.\\S+$";
    
    private final Pattern pattern;
	private Matcher matcher;
 
	public EmailValidator(){
		  pattern = Pattern.compile(mailPattern);
	}

    @Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
               String mail=value.toString();
		matcher = pattern.matcher(mail);
/* || !mail.contains("@campus.lmu.de") || !mail.endsWith("lmu.de")
                       || mail.indexOf("@")!=mail.lastIndexOf("@") || mail.indexOf("campus.lmu.de")!=mail.lastIndexOf("campus.lmu.de")*/

		if(!matcher.matches()){
 
			FacesMessage msg = 
				new FacesMessage("E-mail Prüfung ist fehlgeschlagen. Bitte geben Sie eine gültige Email Adresse ein..", 
						"Ungültiges Email Format."); //E-mail validation failed.Enter a correct rfc compliant email.. Invalid E-mail format.
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
		}
 
	
    
}
}
