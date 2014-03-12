package fr.gfi.quiz.json.moteur;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Personne;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.Reponse;
import fr.gfi.quiz.json.entite.TypeQuestion;
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
	    gsonBuilder.registerTypeAdapter(Quiz.class, new QuizDeserialisation());
	    gsonBuilder.registerTypeAdapter(Question.class, new QuestionDeserialisation());
	    gsonBuilder.registerTypeAdapter(Reponse.class, new ReponseDeserialisation());
	    gsonBuilder.registerTypeAdapter(TypeQuestion.class, new TypeQuestionDeserialisation());
	    gsonBuilder.registerTypeAdapter(IdLibelle.class, new IdLibelleDeserialisation());
	    gsonBuilder.registerTypeAdapter(Personne.class, new PersonneDeserialisation());
	    gson = gsonBuilder.setPrettyPrinting().create();
	}
	
	static public String getJsonStringFromBean(Quiz quiz){
		String json = gson.toJson(quiz,quiz.getClass().getGenericSuperclass());
		return json;
	}

	static public JsonElement getJsonFromBean(Quiz quiz){
		JsonElement json = gson.toJsonTree(quiz,quiz.getClass().getGenericSuperclass());
		return json;
	}
	
	static public Quiz getBeanFromJson(String sJson){
		Quiz quiz = gson.fromJson(sJson, Quiz.class);
		return quiz;
	}
	
}
