package  fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

@Controller("AfficherListQuizzAction")
public class AfficherListQuizzAction extends AbstractMonAction {

	

	@Resource(name = "quizzBusinessService")
	QuizzBusinessService bsqz;

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		validate(session);
		if (this.isErreur()) {
			return "redirect:LoginAction";		
		}

		else {
			try {
				List<Quizz> lQuizzs = bsqz.getListQuizzByDate();
				gestionFormBean.setListQuizz(lQuizzs);
				
			} catch (BusinessServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return"Gestion/ListQuizz";
		}
	

		
		
	}





}
