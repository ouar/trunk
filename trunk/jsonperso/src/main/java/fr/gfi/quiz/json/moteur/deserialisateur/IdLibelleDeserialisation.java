package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.IdLibelle;

public class IdLibelleDeserialisation implements JsonDeserializer<IdLibelle> {

	@Override
	public IdLibelle deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {
		IdLibelle returnValue = new IdLibelle();
		JsonObject jsonObject = json.getAsJsonObject();
		if(jsonObject.has("id")){
			returnValue.setId(jsonObject.get("id").getAsInt());
		}
		if(jsonObject.has("libelle")){
			returnValue.setLibelle(jsonObject.get("libelle").getAsString());
		}
		return returnValue;
	}
}
