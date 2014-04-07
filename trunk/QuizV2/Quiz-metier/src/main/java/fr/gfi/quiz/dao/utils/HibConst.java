package fr.gfi.quiz.dao.utils;

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

		TypesSujets("quizzSujets.typeSujet"),
		DifficulteSujet("quizzSujets.typeSujet.difficulte"),
		LangageSujet("quizzSujets.typeSujet.langage"),
		Reponses("quizzQuestions.question.reponses"),
		Questions("quizzQuestions.question"),
		ReponsesCandidatAvecQuestions("quizzQuestions.question.reponses.reponseCandidats"),
		ReponsesCandidat("reponseCandidats.reponse"),
		User("user"),

		DifficulteQuestion("quizzQuestions.question.typeSujet.difficulte"),
		LangageQuestion("quizzQuestions.question.typeSujet.langage");

		private final String value;

		private QuizzEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum ReponseCandidatEnum {

		Examinateur("quizz.user"), 
		Question("question"),
		DifficulteSujet("question.typeSujet.difficulte"), 
		Reponse("reponse"), 
		TypeSujet("question.typeSujet"),
		Quizz("quizz");

		private final String value;

		private ReponseCandidatEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum LangageEnum {

		TypeSujets("typeSujets"),
		DifficulteSujets("typeSujets.difficulte"),
		Questions("typeSujets.questions"),
		Quizzs("typeSujets.sujets.quizz");

		private final String value;

		private LangageEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuestionEnum {

		Reponses("reponses"), Niveau("niveauQuestion"), sujet("typeSujet"), Langage("typeSujet.langage"), quizzQuestions("quizzQuestions"), reponseCandidats("reponseCandidats");

		private final String value;

		private QuestionEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum typeSujetEnum {

		Langage("langage"), Questions("questions"), QuizzSujets("quizzSujets");

		private final String value;

		private typeSujetEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum UserEnum {

		roles("userRoleses.role");

		private final String value;

		private UserEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

}
