package fr.gfi.android.quiz.activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.composants.BarreNavigation;
import fr.gfi.android.quiz.helpers.AppPreferences;
import fr.gfi.android.quiz.helpers.ReusablesMethodes;
import fr.gfi.android.quiz.helpers.SimpleGestureFilter;
import fr.gfi.android.quiz.helpers.SimpleGestureListener;
import fr.gfi.android.quiz.model.QuizzToScreen;
import fr.gfi.android.quiz.webservices.MyRestClient;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.entite.Reponse;

public class QuestionnaireProcessActivity extends  AbstractActivityOrientationPortrait implements OnClickListener, OnTouchListener, SimpleGestureListener {
	
	
	private SimpleGestureFilter detector;
	
	public static Context _context = null;

	private RelativeLayout layoutBarreNavigation = null;
	private LinearLayout layoutOfDynamicContent = null;
	private QuizzToScreen quiz = null;
	private TextView candidateName = null;
	private TextView question_titre  = null;
	private TextView question_theme  = null;
	private TextView question_numero  = null;
	private Button bsubmit = null;
	
	
//	private TextView chronometre  = null;	
//	private Button next  = null;	
//	static CountDownTimer countdownDuree;

	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questionnaire_process_activity);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
		
		layoutOfDynamicContent = (LinearLayout) findViewById(R.id.layoutOfDynamicContent);
		layoutOfDynamicContent.setOnTouchListener(this);
		
		layoutBarreNavigation = (RelativeLayout) findViewById(R.id.layoutBarre);
		layoutBarreNavigation.setOnTouchListener(this);
		
		Quiz quizTemp = AppPreferences.getInstance(_context).getQuiz();
		quiz = new QuizzToScreen(QuizzToScreen.ETAT_EN_COURS, 0, quizTemp);

		candidateName = (TextView) findViewById(R.id.candidate_name);
		question_numero = (TextView) findViewById(R.id.question_numero);
		question_titre = (TextView) findViewById(R.id.question_titre);
		question_theme = (TextView) findViewById(R.id.question_theme);
		
		bsubmit = (Button)findViewById(R.id.submit);
		bsubmit.setOnClickListener(this);
		
		candidateName.setText(quiz.getQuiz().getCandidat().getPrenom() + " " + quiz.getQuiz().getCandidat().getNom());

		
		detector = new SimpleGestureFilter(this,this);
				
				
		//chronometre = (TextView) v.findViewById(R.id.chronometre);
		//dureeQuiz(QuizTemp);
		

// 		
//		next =(Button) v.findViewById(R.id.next);
//		next.setOnClickListener(this);

		
		refresh();
	}
	

//	private void dureeQuiz(Quiz quiz) {
//		countdownDuree = new CountDownTimer(Integer.parseInt(AppPreferences
//				.getInstance(_context).getDureeValeurParDefault()) * 1000, 1000) {
//			public void onTick(long millisUntilFinished) {
//				int seconds = (int) (millisUntilFinished / 1000);
//				int minutes = seconds / 60;
//				seconds = seconds % 60;
//				chronometre.setText(getString(R.string.chronometre) + " " +String.format("%d mn et %02d s", minutes, seconds));
//			}
//			public void onFinish() {
//				chronometre.setText(getString(R.string.chronometre) + "00 mn et 00 s");
//			}
//		};
//		countdownDuree.start();
//	}
	
	
	private void refresh(){
		layoutOfDynamicContent.removeAllViewsInLayout();
		
		Question question = null;
		if(quiz.getIdQuestionAffichee() < quiz.getQuiz().getLQuestions().size()){
			question = quiz.getQuiz().getLQuestions().get(quiz.getIdQuestionAffichee());
			
	
			question_numero.setText(getString(R.string.question_numero) + " " + String.valueOf(quiz.getIdQuestionAffichee() +1) + " sur " + quiz.getQuiz().getLQuestions().size());
			question_titre.setText(question.getLibelle());
			question_theme.setText(question.getLangage().getLibelle() + " > " + question.getTypeSujet().getLibelle());
			
			
			if(question.isUniqueReponseCorrecte()){
				RadioGroup group = new RadioGroup(_context);
				for(Reponse reponse : question.getlReponses()){
					RadioButton button = new RadioButton(_context);
					button.setId(reponse.getReponse().getId());
					button.setText(reponse.getReponse().getLibelle());
					
					if(reponse.isChoisie() != null && reponse.isChoisie()){
						button.setChecked(true);
					}else{
						button.setChecked(false);
					}	
					if(quiz.getEtatQuizz() == QuizzToScreen.ETAT_EN_CORRECTION){
						button.setEnabled(false);
						if(reponse.isChoisie() != null && reponse.isChoisie() && !reponse.isCorrecte()){
							button.setTextColor(Color.rgb(255, 0, 0));
						}else if(reponse.isCorrecte()){
							button.setTextColor(Color.rgb(0, 255, 0));
						}
					}
					
					
					group.addView(button,
					    new RadioGroup.LayoutParams(
					        RadioGroup.LayoutParams.WRAP_CONTENT,    
					        RadioGroup.LayoutParams.WRAP_CONTENT));
				}
				layoutOfDynamicContent.addView(group,
					    new LinearLayout.LayoutParams(
					        LinearLayout.LayoutParams.MATCH_PARENT,    
					        LinearLayout.LayoutParams.WRAP_CONTENT));
			}else{
				for(Reponse reponse : question.getlReponses()){
					CheckBox cb = new CheckBox(this);
			        cb.setText(reponse.getReponse().getLibelle());
			        cb.setId(reponse.getReponse().getId());
			        
			        if(reponse.isChoisie() != null && reponse.isChoisie()){
			        	cb.setChecked(true);	
			        }else{
			        	cb.setChecked(false);
			        }
			        
			        if(quiz.getEtatQuizz() == QuizzToScreen.ETAT_EN_CORRECTION){
			        	cb.setEnabled(false);
						if(reponse.isChoisie() != null && reponse.isChoisie() && !reponse.isCorrecte()){
							cb.setTextColor(Color.rgb(255, 0, 0));
						}else if(reponse.isCorrecte()){
							cb.setTextColor(Color.rgb(0, 255, 0));
						}
					}
			        
			        cb.setTag(reponse);
			        layoutOfDynamicContent.addView(cb,
						    new LinearLayout.LayoutParams(
						        LinearLayout.LayoutParams.MATCH_PARENT,    
						        LinearLayout.LayoutParams.WRAP_CONTENT));
				}
			}
		}
		
		layoutBarreNavigation.removeAllViewsInLayout();
		BarreNavigation barre = new BarreNavigation(_context,quiz);
		barre.setOnTouchListener(this);
		layoutBarreNavigation.addView(barre,
			    new LinearLayout.LayoutParams(
				        LinearLayout.LayoutParams.WRAP_CONTENT,    
				        LinearLayout.LayoutParams.WRAP_CONTENT));
	}


	@Override
	public void onClick(View v) {
		save();
		if(v instanceof Button){
			if((Button)v == this.bsubmit){
				post();
			}	
		}
	}


	/**
	 * Enregistre les réponses choisies par le candidat sur la page en cours
	 */
	private void save() {
		Question question = quiz.getQuiz().getLQuestions().get(quiz.getIdQuestionAffichee());
		if(question.isUniqueReponseCorrecte()){
			RadioGroup radioGroup = null;
			for(int i = 0 ; i < layoutOfDynamicContent.getChildCount() ; i++){
				if(layoutOfDynamicContent.getChildAt(i) instanceof RadioGroup){
					radioGroup = (RadioGroup) layoutOfDynamicContent.getChildAt(i);
					break;
				}
			}
			if(radioGroup != null){
				if(radioGroup.getCheckedRadioButtonId() != -1){
					question.setRepondue(true);
					for(Reponse reponse : question.getlReponses()){
						int idReponse = reponse.getReponse().getId();
						reponse.setChoisie(radioGroup.getCheckedRadioButtonId() == idReponse);
					}
				}else{
					question.setRepondue(false);
					for(Reponse reponse : question.getlReponses()){
						reponse.setChoisie(false);
					}
				}
			}
			
		}else{
			boolean questionRepondue = false;
			for(int i = 0; i < layoutOfDynamicContent.getChildCount(); i++) {
				if(layoutOfDynamicContent.getChildAt(i) instanceof CheckBox){
					CheckBox child = (CheckBox) layoutOfDynamicContent.getChildAt(i);
					Reponse reponse = (Reponse) child.getTag();
					if(child.isChecked()){
						questionRepondue = true;
						reponse.setChoisie(true);
					}else{
						reponse.setChoisie(false);
					}
				}
			}
			question.setRepondue(questionRepondue);
		}
	}
	
	
	/**
	 * Post les réponses au quiz sur le serveur
	 * @return
	 */
	private boolean post(){
		String sUrl = "http://192.168.0.10:8080/Quiz-web/web/quiz/save/"+quiz.getQuiz().getId();
		MyRestClient client = new MyRestClient(sUrl);
		if(client.postJson(quiz.getQuiz())){
			quiz.setEtatQuizz(QuizzToScreen.ETAT_EN_CORRECTION);
			quiz.setIdQuestionAffichee(0);
			refresh();
		}
		return true;
	}


	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.BUTTON_BACK){
			ReusablesMethodes.gotoNextActivity(this, MainActivity.class);
			this.finish();
		}
		if (event.getAction() == MotionEvent.ACTION_DOWN && v instanceof BarreNavigation){
			 int idQuestion = ((BarreNavigation)v).getIdSelectionne(event.getX());
			 if(idQuestion!=BarreNavigation.NOT_FOUND){
				 save();
				 quiz.setIdQuestionAffichee(idQuestion);
				 refresh();
			 }
			 return true;
		}
		if(v instanceof LinearLayout){
			quiz.setIdQuestionAffichee(0);
			refresh();
			System.out.println();
		}
		
		return true;
	}


	@Override
	public void onSwipe(int direction) {
		String str = "";
	      
	      switch (direction) {
	      
	      case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
	                                               break;
	      case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
	                                                     break;
	      case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
	                                                     break;
	      
	      }
	       Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
	}


	@Override
	public void onDoubleTap() {
		Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
		
	}
	
	  @Override
	    public boolean dispatchTouchEvent(MotionEvent me){
	        // Call onTouchEvent of SimpleGestureFilter class
	         this.detector.onTouchEvent(me);
	       return super.dispatchTouchEvent(me);
	    }
	
	
}
