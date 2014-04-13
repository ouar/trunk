package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Reponse;

public class QuestionDeserialisation implements JsonDeserializer<Question> {

	@Override
	public Question deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
		      throws JsonParseException {

		final Question question = new Question();
		
		final JsonObject jsonObject = json.getAsJsonObject();
		
		
		
		IdLibelle langage = context.deserialize(jsonObject.get("langage"), IdLibelle.class);
		IdLibelle typeSujet = context.deserialize(jsonObject.get("typeSujet"), IdLibelle.class);
		IdLibelle difficulte = context.deserialize(jsonObject.get("difficulte"), IdLibelle.class);
		
		int idQuestion = jsonObject.get("id").getAsInt();
		String libEnonce = jsonObject.get("libelle").getAsString();
		boolean isUniqueReponseCorrecte = jsonObject.get("uniqueReponseCorrecte").getAsBoolean();
		int dureeReflexionEnSec = jsonObject.get("dureeReflexionEnSec").getAsInt();

			
		Reponse[] tabTypeReponses = context.deserialize(jsonObject.get("lReponses"), Reponse[].class);
		List<Reponse> lReponses = Arrays.asList(tabTypeReponses);
		
		question.setId(idQuestion);
		question.setLangage(langage);
		question.setTypeSujet(typeSujet);
		question.setDifficulte(difficulte);
		question.setUniqueReponseCorrecte(isUniqueReponseCorrecte);
		question.setDureeReflexionEnSec(dureeReflexionEnSec);
		question.setLibelle(libEnonce);
		question.setlReponses(lReponses);
		if(jsonObject.has("image")){
			question.setImage(jsonObject.get("image").getAsBoolean());
		}
		if(jsonObject.has("repondue")){
			question.setRepondue(jsonObject.get("repondue").getAsBoolean());
		}
		if(jsonObject.has("correcte")){
			question.setCorrecte(jsonObject.get("correcte").getAsBoolean());
		}
	
		return question;
	}

}
