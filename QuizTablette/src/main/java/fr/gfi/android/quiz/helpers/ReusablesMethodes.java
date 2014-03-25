package fr.gfi.android.quiz.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;

public class ReusablesMethodes {

	public static void gotoNextActivity(Context _context, Class<? extends Activity> activiyClass)
	{
		Intent i = new Intent(_context, activiyClass);
		_context.startActivity(i);
	}
	
	public static int getQuizTime(Quiz givenQuiz)
	{
		int somme = 0;
		
		for(Question questions : givenQuiz.getLQuestions())
		{
			somme = somme + questions.getDureeReflexionEnSec(); 
		}
		return somme;
	}
	
}
