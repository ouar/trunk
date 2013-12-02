package fr.gfi.cmg.QuizzCmg.persistance.service.impl.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.gfi.cmg.QuizzCmg.metier.beans.SujetDifficulteBean;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzQuestion;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.QuizzSujet;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.ReponseCandidat;
import fr.gfi.cmg.QuizzCmg.persistance.service.QuizzPersistenceService;
import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;
import fr.gfi.cmg.QuizzCmg.persistance.util.HibConst;
import fr.gfi.cmg.QuizzCmg.persistance.util.MyRequest;


@Repository("quizzPersistenceService")
public class QuizzPersistenceServiceImpl implements QuizzPersistenceService {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 * 
	 * @param lTypeSujets
	 * @param niveauQuestion
	 * @return @
	 */
	@SuppressWarnings("unchecked")
	public List<Question> getListQuestionsByListTypesSujetsAndNiveauQuestion(
			List<SujetDifficulteBean>  listNiveauTypeSujet) {
		Session hSession = null;

		List<Question> lQuestions = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Question.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ParametresEnum.type_sujet.getValue());
		lAssociations.add(HibConst.ParametresEnum.reponse.getValue());

		Disjunction orTypeSujet = Restrictions.disjunction();

		
		for (SujetDifficulteBean bean : listNiveauTypeSujet) {
			Conjunction andNiveauTypeSujet =Restrictions.conjunction();
			andNiveauTypeSujet.add(Restrictions.eq("typeSujet.id", bean.getSujet().getId())).add(Restrictions.eq("niveauQuestion.id", bean.getDifficulte().getId()));
			orTypeSujet.add(andNiveauTypeSujet);
		}	
		criteres.add(orTypeSujet);

		// On traite le r�sultat de la requ�te pour avoir une question avec
		// des r�ponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		lQuestions = (List<Question>) MyRequest.list(criteres, lAssociations);

		return lQuestions;

	}

	@SuppressWarnings("unchecked")
	public List<Quizz> getListQuizzByDate() {

		Session hSession = null;

		List<Quizz> lQuizz = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Quizz.class);

		criteres.addOrder(Order.desc("datQuizz"));

		lQuizz = criteres.list();

		return lQuizz;

	}

	public Quizz getDetailsQuizz(Integer id) {

		Session hSession = null;

		Quizz quizz = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Quizz.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.QuizzEnum.TypesSujets.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Questions_Quizz.getValue());
		lAssociations.add(HibConst.QuizzEnum.Admin.getValue());
		lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());
		lAssociations.add(HibConst.QuizzEnum.NiveauQuestion.getValue());
		lAssociations.add(HibConst.QuizzEnum.ReponsesCandidat.getValue());
		lAssociations.add(HibConst.QuizzEnum.TypesSujetsLangage.getValue());

		// On traite le r�sultat de la requ�te pour avoir une question avec
		// des r�ponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		// criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);quizzQuestions

		criteres.add(Restrictions.eq("id", id))
		// .createAlias("quizzQuestions.question", "question")
		;
		// .add(Restrictions.eq("question.quizzQuestions.quizz.id",
		// id))
		// .createAlias("quizzQuestions.question.reponses.reponseCandidats",
		// "reponseCandidats")
		// .add(Restrictions.eq("reponseCandidats.quizz.id", id));
		quizz = (Quizz) MyRequest.uniqueResult(criteres, lAssociations);

		return quizz;
	}

	public Quizz getDetailsQuizzAvecReponsesCandidats(Integer id) {
		Session hSession = null;

		Quizz quizz = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Quizz.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.QuizzEnum.TypesSujets.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Questions_Quizz.getValue());
		lAssociations.add(HibConst.QuizzEnum.Admin.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());
		lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());

		// On traite le r�sultat de la requ�te pour avoir une question avec
		// des r�ponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		// criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);quizzQuestions

		criteres.add(Restrictions.eq("id", id));
		// criteres.add(Restrictions.eq("reponsesCandidat.quizz.id", id));
		// .createAlias("quizzQuestions.question", "question")
		// ;
		// .add(Restrictions.eq("question.quizzQuestions.quizz.id",
		// id))
		// .createAlias("quizzQuestions.question.reponses.reponseCandidats","reponseCandidats")
		// .add(Restrictions.eq("reponseCandidats.quizz.id", id))

		quizz = (Quizz) MyRequest.uniqueResult(criteres, lAssociations);

		return quizz;
	}

	@SuppressWarnings({ "unchecked" })
	public List<ReponseCandidat> getListReponsesCandidatsByQuizz(Integer id) {
		// TODO Auto-generated method stub

		Session hSession = null;
		List<ReponseCandidat> lReponseCandidats = null;

		hSession = this.sessionFactory.getCurrentSession();
		final Criteria criteres = hSession
				.createCriteria(ReponseCandidat.class).createAlias("reponse",
						"reponse");

		criteres.add(Restrictions.eq("quizz.id", id));
		// lReponseCandidats = (List<ReponseCandidat>)
		// MyRequest.list(criteres, lAssociations);
		lReponseCandidats = criteres.list();

		return lReponseCandidats;

	}

	@SuppressWarnings("unchecked")
	public List<ReponseCandidat> getDetailsReponseCandidat() {
		Session hSession = null;

		List<ReponseCandidat> lReponseCandidats = null;

		hSession = this.sessionFactory.getCurrentSession();

		final Criteria criteres = hSession
				.createCriteria(ReponseCandidat.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ReponseCandidatEnum.Admin.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.Reponse.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.NiveauQuestion
				.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.TypeSujet.getValue());

		// On traite le r�sultat de la requ�te pour avoir une question avec
		// des r�ponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		lReponseCandidats = (List<ReponseCandidat>) MyRequest.list(criteres,
				lAssociations);

		return lReponseCandidats;
	}

	public QuizzSujet enregistrerQuizzSujet(QuizzSujet quizzSujet) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(quizzSujet);

		return quizzSujet;
	}

	public QuizzQuestion enregistrerQuizzQuestion(QuizzQuestion quizzQuestion) {

		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(quizzQuestion);

		return quizzQuestion;
	}

	/**
	 * 
	 * @param quizz
	 * @return @
	 */
	public Quizz enregistreQuizz(Quizz quizz) {

		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(quizz);
		// final Integer id = quizz.getId();
		return quizz;

	}

	/**
	 * Met � jour un quizz avec la dur�e pass�e par un candidat pour r�pondre
	 * aux questions.
	 * 
	 * @param idQuizz
	 * @param duree
	 *            @
	 */
	public void enrengistrerDureeQuizz(Integer idQuizz, Integer duree) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		Quizz quizz = (Quizz) hSession.get(Quizz.class, idQuizz);
		quizz.setIntDuree(duree);
		hSession.update(quizz);

	}

	/**
	 * enregistre une r�ponse coch�e par un candidat pour une question au cours
	 * d'un quizz.
	 * 
	 * @param reponseCandidat
	 * @return @
	 */
	public ReponseCandidat enregistrerReponseCandidat(
			ReponseCandidat reponseCandidat) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.save(reponseCandidat);
		return reponseCandidat;

	}

	/**
	 * 
	 * @param entity
	 *            @
	 */
	public void deleteEntity(Object entity) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		hSession.delete(entity);

	}

}
