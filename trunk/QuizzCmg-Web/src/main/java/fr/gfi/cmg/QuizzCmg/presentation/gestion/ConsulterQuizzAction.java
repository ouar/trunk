package fr.gfi.cmg.QuizzCmg.presentation.gestion;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.gfi.cmg.QuizzCmg.metier.beans.WrapperReponses;
import fr.gfi.cmg.QuizzCmg.metier.beans.WrapperResultatsQuizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Reponse;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.ReponseCandidat;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;
import fr.gfi.cmg.QuizzCmg.presentation.AbstractMonAction;

@Controller("ConsulterQuizzAction")
public class ConsulterQuizzAction extends AbstractMonAction {

	@Resource(name = "quizzBusinessService")
	QuizzBusinessService bsqz;

	@RequestMapping(method = RequestMethod.POST)
	public String execute(@ModelAttribute("gestionFormBean") GestionFormBean gestionFormBean, HttpServletRequest request) {

		if (this.isErreur()) {
			return "redirect:LoginAction";
		}

		Quizz quizz = null;
		try {
			quizz = this.bsqz.getDetailsQuizz(gestionFormBean.getIdQuizzAConsulter());
			gestionFormBean.setQuizz(quizz);
			getListReponsesCandidatsByQuizz(gestionFormBean);
		} catch (BusinessServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "Gestion/ResultatQuizz";

	}

	public void getListReponsesCandidatsByQuizz(GestionFormBean gestionFormBean) throws BusinessServiceException {

		final List<ReponseCandidat> lReponseCandidats = bsqz.getListReponsesCandidatsByQuizz(gestionFormBean.getIdQuizzAConsulter());
		List<WrapperResultatsQuizz> listWrapperResultatsQuizz = populateListWrapperResultatsQuizz(gestionFormBean.getQuizz(), lReponseCandidats);
		gestionFormBean.setListWrapperResultatsQuizz(listWrapperResultatsQuizz);
	}

	/**
	 * Construction d'une liste d'affichage des résultats à un quizz.
	 * 
	 * @param pQuizz
	 * @param pListReponseCandidats
	 * @return
	 */
	private List<WrapperResultatsQuizz> populateListWrapperResultatsQuizz(Quizz pQuizz, List<ReponseCandidat> pListReponseCandidats) {

		List<WrapperResultatsQuizz> lWrapperResultatsQuizz = new ArrayList<WrapperResultatsQuizz>();
		List<Integer> lIdentifiantsReponsesChoisies = new ArrayList<Integer>();
		// construction d'une liste des identifiants des réponses répondues par
		// le candidat
		for (ReponseCandidat reponseCandidat : pListReponseCandidats) {
			lIdentifiantsReponsesChoisies.add(reponseCandidat.getReponse().getId());
		}

		for (QuizzQuestion quizzQuestion : pQuizz.getQuizzQuestions()) {
			List<WrapperReponses> lWrapperReponses = new ArrayList<WrapperReponses>();
			Question question = quizzQuestion.getQuestion();
			String libQuestion = question.getLibQuestion();
			String libNiveau = question.getNiveauQuestion().getLibNiveau();

			List<Reponse> lReponses = new ArrayList<Reponse>(question.getReponses());

			// enrichissement du bean d'affichage avec les informations
			// concernant chaque réponse posée.
			for (Reponse reponse : lReponses) {
				String sLibReponse = reponse.getLibReponse();
				Integer idReponseParametre = reponse.getId();
				boolean hasReponseCandidat = false;
				boolean isReponseCandidatCorrecte = reponse.getBolTypeReponse();
				if (lIdentifiantsReponsesChoisies.contains(idReponseParametre)) {
					hasReponseCandidat = true;
				}

				WrapperReponses wrapperReponses = new WrapperReponses(sLibReponse, idReponseParametre, hasReponseCandidat, isReponseCandidatCorrecte);
				lWrapperReponses.add(wrapperReponses);
			}

			WrapperResultatsQuizz wrapperResultatsQuizz = new WrapperResultatsQuizz(libQuestion, libNiveau, lWrapperReponses);
			lWrapperResultatsQuizz.add(wrapperResultatsQuizz);
		}
		return lWrapperResultatsQuizz;

	}

}
