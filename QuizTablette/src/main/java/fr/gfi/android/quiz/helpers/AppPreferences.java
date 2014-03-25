package fr.gfi.android.quiz.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

import fr.gfi.quiz.json.entite.Quiz;

public class AppPreferences {

	private static AppPreferences _instance;

	private SharedPreferences _sharedPrefs = null;
	private Editor _prefsEditor;
	private static final String APP_SHARED_PREFS = AppPreferences.class
			.getSimpleName(); // Name of the file -.xml

	public static final Quiz KEY_PREFS_VALEUR_QUIZ = null;
	public static final String KEY_PREFS_VALEUR_DUREE_PAR_DEFAULT = "00";
	private static Gson gson = new Gson();
	 
	private AppPreferences(Context context) {
		this._sharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS,
				Activity.MODE_PRIVATE);
		this._prefsEditor = _sharedPrefs.edit();
	}

	public Quiz getQuiz() {
		
		String json = _sharedPrefs.getString("CurrentQuiz", "");
		Quiz obj = gson.fromJson(json, Quiz.class);
		return obj;
	}

	public void saveQuiz(Quiz quiz) {
		String json = gson.toJson(quiz);
		this._prefsEditor.putString("CurrentQuiz", json);
		this._prefsEditor.commit();
	}

	 public String getDureeValeurParDefault() {
         return _sharedPrefs.getString(KEY_PREFS_VALEUR_DUREE_PAR_DEFAULT, "00");
     }

     public void saveDureeChoisi(String text) {
         _prefsEditor.putString(KEY_PREFS_VALEUR_DUREE_PAR_DEFAULT, text);
         _prefsEditor.commit();
     }
     
	public static AppPreferences getInstance(Context context) {
		if (_instance == null) {
			_instance = new AppPreferences(context);
		}
		return _instance;
	}
	
//	public static saveQuizzToScreen(QuizzToScreen quizNavigation){
//		
//	}

}
