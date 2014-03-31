package fr.gfi.android.quiz.helpers;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.activities.MainActivity;
import fr.gfi.android.quiz.activities.QuestionnaireProcessActivity;
import fr.gfi.android.quiz.activities.quiz.QuestionnaireActivity;
import fr.gfi.android.quiz.constants.MagicValues;
import fr.gfi.quiz.json.entite.Quiz;

public class Messages {

	private static AlertDialog.Builder builder = null;

	public static void flashcodeProcessMessage(final Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(MagicValues.QUIZ_SYSTEM_MESSAGE);
		builder.setMessage(MagicValues.FLASCODE_SUCCESS_MESSAGE + MagicValues.FLASCODE_RESUME);
		builder.setInverseBackgroundForced(true);
		builder.setPositiveButton(MagicValues.PARAMS_DEMARRER,
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {

						// open questionnaire view 
					}
				});

		builder.setNegativeButton(MagicValues.PARAMS_ABANDONER,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						ReusablesMethodes.gotoNextActivity(context, MainActivity.class);
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}

	
	public static void internetConnectionFaliedMessage(final Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(context.getResources().getString(R.string.app_message));
		builder.setMessage(context.getResources().getString(R.string.pas_de_connection_encours));
		builder.setPositiveButton(context.getResources().getString(R.string.ok_parametre),
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
					ReusablesMethodes.gotoNextActivity(context,
            			MainActivity.class);
						dialog.dismiss();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	public static void flashcodeSuccessAndJsonRetrieve(final Context context, Quiz quiz) {
		Log.i("flashcodeSuccessAndJsonRetrieve","Quizz récupérer depuis les webservices" + quiz.toString());
		
		AppPreferences.getInstance(context).saveQuiz(quiz);
		AppPreferences.getInstance(context).saveDureeChoisi(""+ReusablesMethodes.getQuizTime(quiz));
		
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(context.getResources().getString(R.string.app_message));
		builder.setMessage(context.getResources().getString(R.string.resume_quiz) 
				+ "\nNombre de questions : " + quiz.getLQuestions().size() + "."
				+ "\nDurée estimée : " +ReusablesMethodes.getQuizTime(quiz)  +" secondes." + "\n\nVeuillez cliquer sur le bouton \"Démarrer\" pour commencer le quiz. \nBonne chance " +  
				quiz.getCandidat().getPrenom() + " " + quiz.getCandidat().getNom() + ".");
		
		builder.setPositiveButton(context.getResources().getString(R.string.demarrer_parametre),
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						QuestionnaireActivity._context = context;
						ReusablesMethodes.gotoNextActivity(context, QuestionnaireActivity.class);
					}
				});

		builder.setNegativeButton(context.getResources().getString(R.string.abandonner_parametre),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						ReusablesMethodes.gotoNextActivity(context, MainActivity.class);
					}
				});

		
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public static void parsingFailedCauseByTimeout(final Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(context.getResources().getString(R.string.app_message));
		builder.setMessage(context.getResources().getString(R.string.timeout_atteint));
		builder.setPositiveButton(context.getResources().getString(R.string.ok_parametre),
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						ReusablesMethodes.gotoNextActivity(context, MainActivity.class);
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public static void unknownHost(final Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(context.getResources().getString(R.string.app_message));
		builder.setMessage(context.getResources().getString(R.string.hote_inatteignable));
		builder.setPositiveButton(context.getResources().getString(R.string.ok_parametre),
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						ReusablesMethodes.gotoNextActivity(context, MainActivity.class);
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	
	public static void noQuizObject(final Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(context.getResources().getString(R.string.app_message));
		builder.setMessage(context.getResources().getString(R.string.noquizz));
		builder.setPositiveButton(context.getResources().getString(R.string.ok_parametre),
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						ReusablesMethodes.gotoNextActivity(context, MainActivity.class);
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}
	
	
	public static void goToHome (final Context context) {
		builder = new AlertDialog.Builder(context);
		builder.setCancelable(true);
		builder.setTitle(context.getResources().getString(R.string.app_message));
		builder.setMessage(context.getResources().getString(R.string.goHomeInQuiz));
		builder.setPositiveButton(context.getResources().getString(R.string.abandonner_parametre),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						ReusablesMethodes.gotoNextActivity(context, MainActivity.class);
					}
				});
		builder.setNegativeButton(context.getResources().getString(R.string.non_parametre),
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});

		AlertDialog alert = builder.create();
		alert.show();
	}
	
}
