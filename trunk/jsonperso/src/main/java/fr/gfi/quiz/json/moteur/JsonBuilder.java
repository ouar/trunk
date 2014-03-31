package fr.gfi.quiz.json.moteur;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import fr.gfi.quiz.json.entite.ChoixQuiz;
import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Personne;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.Reponse;
import fr.gfi.quiz.json.entite.TypeQuestion;
import fr.gfi.quiz.json.moteur.deserialisateur.ChoixQuizDeserialisation;
import fr.gfi.quiz.json.moteur.deserialisateur.IdLibelleDeserialisation;
import fr.gfi.quiz.json.moteur.deserialisateur.PersonneDeserialisation;
import fr.gfi.quiz.json.moteur.deserialisateur.QuestionDeserialisation;
import fr.gfi.quiz.json.moteur.deserialisateur.QuizDeserialisation;
import fr.gfi.quiz.json.moteur.deserialisateur.ReponseDeserialisation;
import fr.gfi.quiz.json.moteur.deserialisateur.TypeQuestionDeserialisation;

public class JsonBuilder {

	static Gson gson;
	
	static{
		GsonBuilder gsonBuilder = new GsonBuilder();

		//pour les échanges avec la tablette
	    gsonBuilder.registerTypeAdapter(Quiz.class, new QuizDeserialisation());
	    gsonBuilder.registerTypeAdapter(Question.class, new QuestionDeserialisation());
	    gsonBuilder.registerTypeAdapter(Reponse.class, new ReponseDeserialisation());
	    gsonBuilder.registerTypeAdapter(TypeQuestion.class, new TypeQuestionDeserialisation());
	    gsonBuilder.registerTypeAdapter(IdLibelle.class, new IdLibelleDeserialisation());
	    gsonBuilder.registerTypeAdapter(Personne.class, new PersonneDeserialisation());
	    
		//pour les échanges avec le serveur
		gsonBuilder.registerTypeAdapter(ChoixQuiz.class, new ChoixQuizDeserialisation());

	    gson = gsonBuilder.setPrettyPrinting().create();
	}
	
	static public String getJsonStringFromBean(Quiz quiz){
		String json = gson.toJson(quiz,quiz.getClass().getGenericSuperclass());
		return json;
	}
	
	static public String getJsonStringFromBean(List<ChoixQuiz> lChoix){
		ChoixQuiz[] tabChoix = (ChoixQuiz[]) lChoix.toArray(new ChoixQuiz[lChoix.size()]);
		String json = gson.toJson(tabChoix,ChoixQuiz[].class);
		return json;
	}

	static public JsonElement getJsonFromBean(Quiz quiz){
		JsonElement json = gson.toJsonTree(quiz,quiz.getClass().getGenericSuperclass());
		return json;
	}

	static public Quiz getQuizBeanFromJson(String sJson){
		Quiz quiz = gson.fromJson(sJson, Quiz.class);
		return quiz;
	}

	static public List<ChoixQuiz> getChoixQuizBeanFromJson(String sJson){
		ChoixQuiz[] tabChoix = gson.fromJson(sJson, ChoixQuiz[].class);
		List<ChoixQuiz> lChoix = Arrays.asList(tabChoix);
		return lChoix;
	}

}
