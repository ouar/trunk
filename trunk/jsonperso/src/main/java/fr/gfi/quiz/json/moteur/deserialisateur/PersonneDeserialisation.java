package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.Personne;

public class PersonneDeserialisation implements JsonDeserializer<Personne> {

	@Override
	public Personne deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		Personne returnValue = new Personne();
		JsonObject jsonObject = json.getAsJsonObject();
		if(jsonObject.has("id")){
			returnValue.setId(jsonObject.get("id").getAsInt());
		}
		if(jsonObject.has("prenom")){
			returnValue.setPrenom(jsonObject.get("prenom").getAsString());
		}
		if(jsonObject.has("nom")){
			returnValue.setNom(jsonObject.get("nom").getAsString());
		}
		return returnValue;
	}
}
