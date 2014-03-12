package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Reponse;

public class ReponseDeserialisation implements JsonDeserializer<Reponse> {

	@Override
	  public Reponse deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
	      throws JsonParseException {
		  
		  Reponse reponse = new Reponse();
		  JsonObject jsonObject = json.getAsJsonObject();
		 
		  IdLibelle reponseidlib = context.deserialize(jsonObject.get("reponse"), IdLibelle.class);
		  reponse.setReponse(reponseidlib);
		  
		  if(jsonObject.has("correcte")){
			  Boolean correcte = jsonObject.get("correcte").getAsBoolean();
			  reponse.setCorrecte(correcte.booleanValue());
		  }
		  if(jsonObject.has("choisie")){
			  Boolean choisie = jsonObject.get("correcte").getAsBoolean();
			  reponse.setChoisie(choisie.booleanValue());
		  }
		  return reponse;
	  }

}
