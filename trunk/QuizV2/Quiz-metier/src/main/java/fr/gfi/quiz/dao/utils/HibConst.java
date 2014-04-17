package fr.gfi.quiz.dao.utils;

public class HibConst {

	public enum ParametresEnum {

		reponse("reponses"), sujet("sujet");

		private final String value;

		private ParametresEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuizzEnum {

		Sujets("quizzSujets.sujet"),
		DifficulteSujet("quizzSujets.sujet.difficulte"),
		ThemeSujet("quizzSujets.sujet.theme"),
		Reponses("quizzQuestions.question.reponses"),
		Questions("quizzQuestions.question"),
		ReponsesCandidatAvecQuestions("quizzQuestions.question.reponses.reponseCandidats"),
		ReponsesCandidat("reponseCandidats.reponse"),
		User("user"),

		DifficulteQuestion("quizzQuestions.question.sujet.difficulte"),
		ThemeQuestion("quizzQuestions.question.sujet.theme");

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
		DifficulteSujet("question.sujet.difficulte"),
		Reponse("reponse"),
		Sujet("question.sujet"),
		Quizz("quizz");

		private final String value;

		private ReponseCandidatEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum ThemeEnum {

		Sujets("sujets"),
		DifficulteSujets("sujets.difficulte"),
		Questions("sujets.questions"),
		Quizzs("sujets.sujets.quizz");

		private final String value;

		private ThemeEnum(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum QuestionEnum {

		Reponses("reponses"),
		Niveau("niveauQuestion"),
		Sujet("sujet"),
		Theme("sujet.theme"),
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
