package fr.gfi.quiz.json.moteur.deserialisateur;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import fr.gfi.quiz.json.entite.Personne;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.TypeQuestion;

public class QuizDeserialisation implements JsonDeserializer<Quiz> {

	@Override
	public Quiz deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
			throws JsonParseException {

		JsonObject jsonQuiz = json.getAsJsonObject();
		
		final Quiz quiz = new Quiz();
		int idQuiz = jsonQuiz.get("id").getAsInt();
		Personne candidat = context.deserialize(jsonQuiz.get("candidat"), Personne.class);
		Personne examinateur = context.deserialize(jsonQuiz.get("examinateur"), Personne.class);


		TypeQuestion[] tabTypeQuestions = context.deserialize(jsonQuiz.get("lTypesQuestions"), TypeQuestion[].class);
		Question[] tabQuestions = context.deserialize(jsonQuiz.get("lQuestions"), Question[].class);

		List<TypeQuestion> lTypesQuestions = Arrays.asList(tabTypeQuestions);
		List<Question> lQuestions = Arrays.asList(tabQuestions);

		quiz.setId(idQuiz);
		quiz.setCandidat(candidat);
		quiz.setExaminateur(examinateur);
		quiz.setLTypesQuestions(lTypesQuestions);
		quiz.setLQuestions(lQuestions);

		return quiz;
	}
}