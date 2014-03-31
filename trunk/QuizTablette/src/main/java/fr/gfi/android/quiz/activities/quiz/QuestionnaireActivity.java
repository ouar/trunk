package fr.gfi.android.quiz.activities.quiz;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.activities.effects.ZoomOutPageTransformer;
import fr.gfi.android.quiz.helpers.AppPreferences;
import fr.gfi.android.quiz.model.QuizzToScreen;
import fr.gfi.android.quiz.webservices.MyRestClient;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;

public class QuestionnaireActivity extends FragmentActivity implements OnClickListener{

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    
    public static Context _context = null;
    private QuizzToScreen quiz = null;
    private int nbMaxQuestion = 0;
	private TextView candidateName = null;
	private TextView question_numero  = null;
	private TextView type_sujet  = null;
	private Button bSoumettre = null;
    
    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_slide);
    	
        if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
        
        //récupération du quiz dans les 
        Quiz quizTemp = AppPreferences.getInstance(_context).getQuiz();
        quiz = new QuizzToScreen(QuizzToScreen.ETAT_EN_COURS, 0, quizTemp);
        nbMaxQuestion = quiz.getQuiz().getLQuestions().size();
        
        candidateName = (TextView) findViewById(R.id.candidate_name);
        question_numero  = (TextView) findViewById(R.id.question_numero);
        type_sujet = (TextView) findViewById(R.id.type_sujet);
    	bSoumettre = (Button) findViewById(R.id.soumettre);
        bSoumettre.setOnClickListener(this);
        
		candidateName.setText(quiz.getQuiz().getCandidat().getPrenom() + " " + quiz.getQuiz().getCandidat().getNom());
		question_numero.setText(getString(R.string.question_numero, 1,nbMaxQuestion));
		Question premiereQuestionAAfficher = quiz.getQuiz().getLQuestions().get(0);
		type_sujet.setText(
				getString(R.string.type_sujet, 
						premiereQuestionAAfficher.getLangage().getLibelle(),
						premiereQuestionAAfficher.getTypeSujet().getLibelle(),
						premiereQuestionAAfficher.getDifficulte().getLibelle()));
		
		
		
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new QuestionnaireSlidePagerAdapter(getFragmentManager(), quiz);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        
        
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	Question questionAAfficher = quiz.getQuiz().getLQuestions().get(position);
            	question_numero.setText(getString(R.string.question_numero, position+1,nbMaxQuestion));
            	type_sujet.setText(getString(R.string.type_sujet, 
            			questionAAfficher.getLangage().getLibelle(),
            			questionAAfficher.getTypeSujet().getLibelle(),
            			questionAAfficher.getDifficulte().getLibelle()));
            }
        });
    }

//    public void refresh(){
//    	for(int i = 0 ; i < mPager.getChildCount() ; i++){
//    		View page = mPager.getChildAt(i);
//    		if(page instanceof ScrollView){
//    			ScrollView scrollTemp = (ScrollView) page;
//    			for(int j = 0 ; j < scrollTemp.getChildCount() ; j++){
//    				if(scrollTemp.getChildAt(j) instanceof LinearLayout){
//    					LinearLayout layoutTemp = (LinearLayout) scrollTemp.getChildAt(j);
//    					for(int k = 0 ; k < layoutTemp.getChildCount() ; k++){
//							if(layoutTemp.getChildAt(k) instanceof LinearLayout){
//								LinearLayout layoutTemp2 = (LinearLayout) layoutTemp.getChildAt(k);
//								layoutTemp2.getTag();
//								for(int l = 0 ; l < layoutTemp2.getChildCount() ; l++){
//									if(layoutTemp2.getChildAt(l) instanceof RadioGroup){
//										RadioGroup radioTemp = (RadioGroup) layoutTemp2.getChildAt(l);
//										for(int m = 0; m<radioTemp.getChildCount(); m++){
//											RadioButton radioButtonTemp = (RadioButton) radioTemp.getChildAt(m);
//											radioButtonTemp.getTag();
//										}
//									    
//									    radioTemp.getTag();
//									}else if(layoutTemp2.getChildAt(l) instanceof CheckBox){
//										CheckBox checkTemp = (CheckBox) layoutTemp2.getChildAt(l);
//										checkTemp.getTag();
//									}	
//								}
//							}
//								
//								
//    					}
//    						
//    					
//    				}
//    				Toast toast = Toast.makeText(_context, 
//    						String.valueOf(temp.getChildAt(j).getTag()), 
//    						android.widget.Toast.LENGTH_LONG);
//    				toast.show();
//    			}
//    		}
//    	}
//    }
    
    
	@Override
	public void onClick(View v) {
		String sUrl = "http://192.168.0.10:8080/Quiz-web/web/quiz/save/"+quiz.getQuiz().getId();
		MyRestClient client = new MyRestClient(sUrl);
		String sResutltat = "";
		sResutltat=(client.postJson(quiz.getQuiz()))?"Quiz envoyé avec Succès":"Erreur à l'envoi du Quiz";
		Toast toast = Toast.makeText(_context, sResutltat, android.widget.Toast.LENGTH_SHORT);
		toast.show();
					
		quiz.setEtatQuizz(QuizzToScreen.ETAT_EN_CORRECTION);
		mPagerAdapter = new QuestionnaireSlidePagerAdapter(getFragmentManager(), quiz);
		mPager.getChildAt(0).invalidate();
		mPager.setCurrentItem(0,true);
	}
	
    
    

    private class QuestionnaireSlidePagerAdapter extends FragmentStatePagerAdapter {
    	
    	private QuizzToScreen quiz = null;
    	
        public QuestionnaireSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }
        
        public QuestionnaireSlidePagerAdapter(FragmentManager fm,QuizzToScreen _quiz) {
            this(fm);
            quiz = _quiz;
        }

		@Override
        public Fragment getItem(int _position) {
			quiz.setIdQuestionAffichee(_position);
			Question questionAAfficher = quiz.getQuiz().getLQuestions().get(_position);
            return QuestionnaireFragment.afficheQuestion(questionAAfficher,_position,quiz.getEtatQuizz());
        }
        
        @Override
        public int getCount() {
            return quiz.getQuiz().getLQuestions().size();
        }
    }
}
