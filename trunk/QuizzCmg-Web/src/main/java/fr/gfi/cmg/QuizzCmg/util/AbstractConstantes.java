package fr.gfi.cmg.QuizzCmg.util;

public abstract class AbstractConstantes {

	public static enum BS {
		Admin("AdminBusinessService"), Quizz("QuizzBusinessService");

		private final String value;

		private BS(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	};

	public static enum PS {
		Admin("AdminHibernatePersistenceService"), Quizz("QuizzPersistenceService");

		private final String value;

		private PS(String sValue) {
			this.value = sValue;
		}

		public String getValue() {
			return this.value;
		}
	};

	public static String LISTE_ADMINS = "ListeAdmins";
	public static String LISTE_LANGAGES = "ListeLangages";
	public static String LISTE_TYPE_SUJETS = "ListeTypeSujets";
	public static String LISTE_NIVEAU_DIFFICULTES = "ListeNiveauxDifficultes";
	public static String LISTE_QUESTIONS = "ListeQuestionss";
	

}
