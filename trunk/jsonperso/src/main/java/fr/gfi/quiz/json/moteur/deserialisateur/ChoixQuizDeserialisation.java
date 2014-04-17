package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.ChoixQuiz;
import fr.gfi.quiz.json.entite.IdLibelle;

public class ChoixQuizDeserialisation implements JsonDeserializer<ChoixQuiz> {

	@Override
	public ChoixQuiz deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		ChoixQuiz returnValue = new ChoixQuiz();
		JsonObject jsonObject = json.getAsJsonObject();

		IdLibelle theme= context.deserialize(jsonObject.get("theme"), IdLibelle.class);
		returnValue.setTheme(theme);
		IdLibelle sujet= context.deserialize(jsonObject.get("sujet"), IdLibelle.class);
		returnValue.setSujet(sujet);
		IdLibelle difficulte= context.deserialize(jsonObject.get("difficulte"), IdLibelle.class);
		returnValue.setDifficulte(difficulte);

		return returnValue;
	}

}
