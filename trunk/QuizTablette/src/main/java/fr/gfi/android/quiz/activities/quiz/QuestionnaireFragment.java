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
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.model.QuizzToScreen;
import fr.gfi.quiz.json.entite.Question;
import fr.gfi.quiz.json.entite.Reponse;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * QuestionnaireActivity} samples.</p>
 */
public class QuestionnaireFragment extends Fragment {

	public static final String QUESTION 		= "question";
	public static final String NUMERO_PAGE 		= "numPage";
	public static final String ETAT_QUIZ 		= "etatQuiz";

	private LinearLayout layoutOfDynamicContent = null;

	private Question question;
	private int nbPageAffichee;
	private int etatQuiz;

	/**
	 * Factory method for this fragment class. Constructs a new fragment for the given page number.
	 */
	public static QuestionnaireFragment afficheQuestion(Question question,int idPageAAfficher,int etatQuiz) {
		QuestionnaireFragment fragment = new QuestionnaireFragment();
		Bundle args = new Bundle();
		args.putSerializable(QUESTION, question);
		args.putInt(NUMERO_PAGE, idPageAAfficher);
		args.putInt(ETAT_QUIZ, etatQuiz);
		fragment.setArguments(args);
		return fragment;
	}

	public QuestionnaireFragment() {
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		question = (Question) getArguments().getSerializable(QUESTION);
		nbPageAffichee = getArguments().getInt(NUMERO_PAGE);
		etatQuiz = getArguments().getInt(ETAT_QUIZ);
	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
		((TextView) rootView.findViewById(R.id.question_titre)).setText(question.getLibelle());

		layoutOfDynamicContent = (LinearLayout) rootView.findViewById(R.id.layoutOfDynamicContent);
		layoutOfDynamicContent.setTag(question);
		
		
		
		if(question.isUniqueReponseCorrecte()){
			RadioGroup group = new RadioGroup(getActivity());
			for(Reponse reponse : question.getlReponses()){
				RadioButton button = new RadioButton(getActivity());
				button.setId(reponse.getReponse().getId());
				button.setText(reponse.getReponse().getLibelle());
				button.setChecked(reponse.isChoisie() != null && reponse.isChoisie());
				
				if(etatQuiz == QuizzToScreen.ETAT_EN_CORRECTION){
					button.setEnabled(false);
					if(reponse.isChoisie() != null && reponse.isChoisie() && !reponse.isCorrecte()){
						button.setTextColor(Color.rgb(255, 0, 0));
					}else if(reponse.isCorrecte()){
						button.setTextColor(Color.rgb(0, 255, 0));
					}
				}
				button.setTag(reponse);
				
				group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						question.setRepondue(true);
						for(int i = 0 ; i < group.getChildCount() ; i++){
							RadioButton temp = (RadioButton)group.getChildAt(i);
							Reponse reponseTemp = (Reponse) temp.getTag();
							reponseTemp.setChoisie(reponseTemp.getReponse().getId() == checkedId);
						}
					}
				});

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
				CheckBox cb = new CheckBox(getActivity());
				cb.setText(reponse.getReponse().getLibelle());
				cb.setId(reponse.getReponse().getId());
				cb.setChecked(reponse.isChoisie() != null && reponse.isChoisie());
				cb.setTag(reponse);
				if(etatQuiz == QuizzToScreen.ETAT_EN_CORRECTION){
					cb.setEnabled(false);
					if(reponse.isChoisie() != null && reponse.isChoisie() && !reponse.isCorrecte()){
						cb.setTextColor(Color.rgb(255, 0, 0));
					}else if(reponse.isCorrecte()){
						cb.setTextColor(Color.rgb(0, 255, 0));
					}
				}
				cb.setOnClickListener(new OnClickListener() {

				      @Override
				      public void onClick(View v) {
				    	 if(v instanceof CheckBox){
				    		 question.setRepondue(true);
				    		 CheckBox checkTemp = (CheckBox) v;
				    		 Reponse reponse = (Reponse) checkTemp.getTag();
				    			 reponse.setChoisie(checkTemp.isChecked());
				    	 }
				      }
				});
				


				layoutOfDynamicContent.addView(cb,
						new LinearLayout.LayoutParams(
								LinearLayout.LayoutParams.MATCH_PARENT,    
								LinearLayout.LayoutParams.WRAP_CONTENT));
			}
			
			
		}
		return rootView;
	}

	/**
	 * Returns the page number represented by this fragment object.
	 */
	public int getPageNumber() {
		return nbPageAffichee;
	}
	
	public int getNbPageAffichee() {
		return nbPageAffichee;
	}

	public void setNbPageAffichee(int nbPageAffichee) {
		this.nbPageAffichee = nbPageAffichee;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}

