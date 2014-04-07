package fr.gfi.quiz.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import fr.gfi.quiz.dao.QuizDAO;
import fr.gfi.quiz.dao.utils.HibConst;
import fr.gfi.quiz.dao.utils.MyRequest;
import fr.gfi.quiz.entite.hibernate.Langage;
import fr.gfi.quiz.entite.hibernate.Question;
import fr.gfi.quiz.entite.hibernate.Quizz;
import fr.gfi.quiz.entite.hibernate.QuizzQuestion;
import fr.gfi.quiz.entite.hibernate.QuizzSujet;
import fr.gfi.quiz.entite.hibernate.ReponseCandidat;
import fr.gfi.quiz.entite.hibernate.TypeSujetId;
import fr.gfi.quiz.json.entite.ChoixQuiz;


@Repository("quizDAO")
public class QuizDAOImpl extends AbstractDAOImpl implements QuizDAO {


	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	/**
	 *
	 * @param lTypeSujets
	 * @param niveauQuestion
	 * @return @
	 */
	@SuppressWarnings("unchecked")
	public List<Question> getListQuestionsByListTypesSujetsAndNiveauQuestion(List<ChoixQuiz>  lChoix) {
		Session hSession = null;

		List<Question> lQuestions = null;

		hSession = sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Question.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ParametresEnum.type_sujet.getValue());
		lAssociations.add(HibConst.ParametresEnum.reponse.getValue());

		Disjunction orTypeSujet = Restrictions.disjunction();


		for (ChoixQuiz choix : lChoix) {
			Conjunction andNiveauTypeSujet =Restrictions.conjunction();
			TypeSujetId sujetId = new TypeSujetId();
			sujetId.setId(choix.getSujet().getId());
			sujetId.setRefDifficulte(choix.getDifficulte().getId());
			andNiveauTypeSujet.add(Restrictions.eq("typeSujet.id", sujetId));
			orTypeSujet.add(andNiveauTypeSujet);
		}
		criteres.add(orTypeSujet);

		// On traite le résultat de la requête pour avoir une question avec
		// des réponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		lQuestions = (List<Question>) MyRequest.list(criteres, lAssociations);

		return lQuestions;

	}

	@SuppressWarnings("unchecked")
	public List<Quizz> getListQuizzByDate() {
		Session hSession = null;
		List<Quizz> lQuizz = null;
		hSession = sessionFactory.getCurrentSession();
		final Criteria criteres = hSession.createCriteria(Quizz.class);
		criteres.addOrder(Order.desc("datQuizz"));
		lQuizz = criteres.list();

		return lQuizz;
	}

	public Quizz getDetailsQuizz(Integer id,List<String> lAssociations) {
		Session hSession = null;
		Quizz quizz = null;

		hSession = sessionFactory.getCurrentSession();
		final Criteria criteres = hSession.createCriteria(Quizz.class);
		criteres.add(Restrictions.eq("id", id));
		quizz = (Quizz) MyRequest.uniqueResult(criteres, lAssociations);

		return quizz;
	}

	public Quizz getDetailsQuizzAvecReponsesCandidats(Integer id) {
		Session hSession = null;

		Quizz quizz = null;

		hSession = sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Quizz.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.QuizzEnum.Questions.getValue());
		lAssociations.add(HibConst.QuizzEnum.ReponsesCandidat.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Questions_Quizz.getValue());
		lAssociations.add(HibConst.QuizzEnum.User.getValue());
		// lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());
		lAssociations.add(HibConst.QuizzEnum.Reponses.getValue());

		// On traite le résultat de la requête pour avoir une question avec
		// des réponses correspondantes et non pas autant de questions qu'il
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
		Session hSession = null;
		List<ReponseCandidat> lReponseCandidats = null;


		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ReponseCandidatEnum.Question.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.Reponse.getValue());
		
		hSession = sessionFactory.getCurrentSession();
		final Criteria criteres = hSession.createCriteria(ReponseCandidat.class).createAlias("quizz", "quizz");

		criteres.add(Restrictions.eq("quizz.id", id));
		lReponseCandidats = MyRequest.list(criteres,lAssociations);

		return lReponseCandidats;

	}

	@SuppressWarnings("unchecked")
	public List<ReponseCandidat> getDetailsReponseCandidat() {
		Session hSession = null;

		List<ReponseCandidat> lReponseCandidats = null;

		hSession = sessionFactory.getCurrentSession();

		final Criteria criteres = hSession
				.createCriteria(ReponseCandidat.class);

		// Chargement des associations
		final List<String> lAssociations = new ArrayList<String>();
		lAssociations.add(HibConst.ReponseCandidatEnum.Examinateur.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.Reponse.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.DifficulteSujet.getValue());
		lAssociations.add(HibConst.ReponseCandidatEnum.TypeSujet.getValue());

		// On traite le résultat de la requête pour avoir une question avec
		// des réponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		lReponseCandidats = (List<ReponseCandidat>) MyRequest.list(criteres,
				lAssociations);

		return lReponseCandidats;
	}

	public QuizzSujet enregistrerQuizzSujet(QuizzSujet quizzSujet) {
		Session hSession = null;

		hSession = sessionFactory.getCurrentSession();
		hSession.save(quizzSujet);

		return quizzSujet;
	}

	public QuizzQuestion enregistrerQuizzQuestion(QuizzQuestion quizzQuestion) {

		Session hSession = null;

		hSession = sessionFactory.getCurrentSession();
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

		hSession = sessionFactory.getCurrentSession();
		hSession.save(quizz);
		// final Integer id = quizz.getId();
		return quizz;

	}

	/**
	 * Met Ã  jour un quizz avec la durée passée par un candidat pour répondre
	 * aux questions.
	 *
	 * @param idQuizz
	 * @param duree
	 *            @
	 */
	public void enrengistrerDureeQuizz(Integer idQuizz, Integer duree) {
		Session hSession = null;

		hSession = sessionFactory.getCurrentSession();
		Quizz quizz = (Quizz) hSession.get(Quizz.class, idQuizz);
		quizz.setIntDuree(duree);
		hSession.update(quizz);

	}

	/**
	 * enregistre une réponse cochée par un candidat pour une question au cours
	 * d'un quizz.
	 *
	 * @param reponseCandidat
	 * @return @
	 */
	public ReponseCandidat enregistrerReponseCandidat(
			ReponseCandidat reponseCandidat) {
		Session hSession = null;

		hSession = sessionFactory.getCurrentSession();
		hSession.save(reponseCandidat);
		return reponseCandidat;

	}

	public List<Langage> getListLangage(List<String> lAssociations) {
		Session hSession = null;

		hSession = this.sessionFactory.getCurrentSession();
		Criteria criteria = hSession.createCriteria(Langage.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		final List<Langage> listLangages = MyRequest.list(criteria, lAssociations);
		return listLangages;

	}

	@Override
	public void create(Object objet) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public Quizz getDetailsQuizzFini(int idQuiz, List<String> lAssociations) {
		Session hSession = null;

		Quizz quizz = null;

		hSession = sessionFactory.getCurrentSession();

		final Criteria criteres = hSession.createCriteria(Quizz.class)
				.createAlias("quizzQuestions.question.reponseCandidats", "reponseCandidat");

		// Chargement des associations


		// On traite le résultat de la requête pour avoir une question avec
		// des réponses correspondantes et non pas autant de questions qu'il
		// ya de reponses.
		// criteres.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);quizzQuestions

		criteres.add(Restrictions.eq("id", idQuiz));
		criteres.add(Restrictions.eq("reponseCandidat.quizz.id", idQuiz));
		// .createAlias("quizzQuestions.question", "question")

		// .add(Restrictions.eq("question.quizzQuestions.quizz.id",
		// id))
		// .createAlias("quizzQuestions.question.reponses.reponseCandidats",
		// "reponseCandidats")
		// .add(Restrictions.eq("reponseCandidats.quizz.id", id));
		quizz = (Quizz) MyRequest.uniqueResult(criteres, lAssociations);

		return quizz;
	}

}
