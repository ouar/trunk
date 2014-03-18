package  fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;
import fr.gfi.cmg.QuizzCmg.presentation.beans.GestionFormBean;

@Controller("AfficherListQuizzAction")
public class AfficherListQuizzAction extends AbstractMonAction {

	@RolesAllowed("ROLE_USER")
	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		
		
			try {
				List<Quizz> lQuizzs = bsqz.getListQuizzByDate();
				gestionFormBean.setListQuizz(lQuizzs);
				
			} catch (BusinessServiceException e) {
				System.err.println(e);
			}

			return"Gestion/ListQuizz";
	}

}
