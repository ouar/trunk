package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.TypeQuestion;

public class TypeQuestionDeserialisation implements JsonDeserializer<TypeQuestion> {

	@Override
	  public TypeQuestion deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
	      throws JsonParseException {

		  final TypeQuestion typeQuestion = new TypeQuestion();

			final JsonObject jsonObject = json.getAsJsonObject();

			IdLibelle theme = context.deserialize(jsonObject.get("theme"), IdLibelle.class);
			IdLibelle sujet = context.deserialize(jsonObject.get("sujet"), IdLibelle.class);
			IdLibelle difficulte = context.deserialize(jsonObject.get("difficulte"), IdLibelle.class);

			typeQuestion.setTheme(theme);
			typeQuestion.setSujet(sujet);
			typeQuestion.setDifficulte(difficulte);

			return typeQuestion;
	  }
}
