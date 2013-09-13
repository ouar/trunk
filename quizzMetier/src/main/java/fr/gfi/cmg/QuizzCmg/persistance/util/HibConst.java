package fr.gfi.cmg.QuizzCmg.persistance.util;



public class HibConst {

	public enum ParametresEnum {

		reponse("reponses"), type_sujet("typeSujet");

		private final String value;

		private ParametresEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuizzEnum {

		TypesSujets("quizzSujets.typeSujet"), TypesSujetsLangage("quizzSujets.typeSujet.langage"), Reponses("quizzQuestions.question.reponses"), Questions(
				"quizzQuestions.question"), QuestionsReponsesCandidat("quizzQuestions.question.reponses.reponseCandidats"), ReponsesCandidat("reponseCandidats"), Admin(
				"admin"), NiveauQuestion("quizzQuestions.question.niveauQuestion");

		private final String value;

		private QuizzEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum ReponseCandidatEnum {

		Admin("quizz.admin"), NiveauQuestion("question.niveauQuestion"), Reponse("reponse"), TypeSujet("question.typeSujet"), Quizz("quizz");

		private final String value;

		private ReponseCandidatEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum LangageEnum {

		TypeSujets("typeSujets"), Questions("typeSujets.questions"), Quizzs("typeSujets.sujets.quizz");

		private final String value;

		private LangageEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuestionEnum {

		Reponses("reponses"), Niveau("niveauQuestion"), sujet("typeSujet"), Langage("typeSujet.langage");

		private final String value;

		private QuestionEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}
	
	public enum typeSujetEnum {

		Langage("langage"),	Questions("questions"),	QuizzSujets("quizzSujets");	
	

		private final String value;

		private typeSujetEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}
}
