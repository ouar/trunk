/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
import android.widget.TextView;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.activities.effects.ZoomOutPageTransformer;
import fr.gfi.android.quiz.helpers.AppPreferences;
import fr.gfi.android.quiz.model.QuizzToScreen;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Quiz;

/**
 * Demonstrates a "screen-slide" animation using a {@link ViewPager}. Because {@link ViewPager}
 * automatically plays such an animation when calling {@link ViewPager#setCurrentItem(int)}, there
 * isn't any animation-specific code in this sample.
 *
 * <p>This sample shows a "next" button that advances the user to the next step in a wizard,
 * animating the current screen out (to the left) and the next screen in (from the right). The
 * reverse animation is played when the user presses the "previous" button.</p>
 *
 * @see QuestionnaireSlidePageFragment
 */
public class QuestionnaireSlideActivity extends FragmentActivity {

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
        
        
        
        
		candidateName.setText(quiz.getQuiz().getCandidat().getPrenom() + " " + quiz.getQuiz().getCandidat().getNom());
		question_numero.setText(getString(R.string.question_numero, 1,nbMaxQuestion));
		Question premiereQuestionAAfficher = quiz.getQuiz().getLQuestions().get(0);
		type_sujet.setText(getString(R.string.type_sujet, premiereQuestionAAfficher.getLangage().getLibelle(),premiereQuestionAAfficher.getTypeSujet().getLibelle()));
		
		
		
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new QuestionnaireSlidePagerAdapter(getFragmentManager(), quiz);
        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
            	Question questionAAfficher = quiz.getQuiz().getLQuestions().get(position);
            	question_numero.setText(getString(R.string.question_numero, position+1,nbMaxQuestion));
            	type_sujet.setText(getString(R.string.type_sujet, questionAAfficher.getLangage().getLibelle(),questionAAfficher.getTypeSujet().getLibelle()));
            }
        });
    }


    /**
     * A simple pager adapter that represents 5 {@link QuestionnaireSlidePageFragment} objects, in
     * sequence.
     */
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
            return QuestionnaireSlidePageFragment.afficheQuestion(questionAAfficher,_position);
        }
        
        @Override
        public int getCount() {
            return quiz.getQuiz().getLQuestions().size();
        }
    }
}
