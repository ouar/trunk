package fr.gfi.quiz.dao.utils;

public class HibConst {

	public enum ParametresEnum {

		reponse("reponses"), sujet("Sujet");

		private final String value;

		private ParametresEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuizzEnum {

		Sujets("quizzSujets.Sujet"),
		DifficulteSujet("quizzSujets.Sujet.difficulte"),
		ThemeSujet("quizzSujets.Sujet.theme"),
		Reponses("quizzQuestions.question.reponses"),
		Questions("quizzQuestions.question"),
		ReponsesCandidatAvecQuestions("quizzQuestions.question.reponses.reponseCandidats"),
		ReponsesCandidat("reponseCandidats.reponse"),
		User("user"),

		DifficulteQuestion("quizzQuestions.question.Sujet.difficulte"),
		ThemeQuestion("quizzQuestions.question.Sujet.theme");

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
		DifficulteSujet("question.Sujet.difficulte"),
		Reponse("reponse"),
		Sujet("question.Sujet"),
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

		Sujets("Sujets"),
		DifficulteSujets("Sujets.difficulte"),
		Questions("Sujets.questions"),
		Quizzs("Sujets.sujets.quizz");

		private final String value;

		private LangageEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuestionEnum {

		Reponses("reponses"),
		Niveau("niveauQuestion"),
		Sujet("Sujet"),
		Theme("Sujet.theme"),
		quizzQuestions("quizzQuestions"),
		reponseCandidats("reponseCandidats");

		private final String value;

		private QuestionEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum SujetEnum {

		Theme("theme"),
		Questions("questions"),
		QuizzSujets("quizzSujets");

		private final String value;

		private SujetEnum(String sValue) {
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
