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
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import fr.gfi.android.quiz.R;
import fr.gfi.android.quiz.model.QuizzToScreen;
import fr.gfi.quiz.json.entite.Question;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * QuestionnaireSlideActivity} samples.</p>
 */
public class QuestionnaireSlidePageFragment extends Fragment {

	public static final String QUIZ_TO_SCREEN 	= "quiz";
	public static final String ID_QUESTION  	= "idQuestion";

    public QuizzToScreen getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizzToScreen quiz) {
		this.quiz = quiz;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	private QuizzToScreen quiz;
    private int idQuestion;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static QuestionnaireSlidePageFragment afficheQuestion(QuizzToScreen _quiz) {
        QuestionnaireSlidePageFragment fragment = new QuestionnaireSlidePageFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUIZ_TO_SCREEN, _quiz);
        args.putInt(ID_QUESTION, _quiz.getIdQuestionAffichee());
        fragment.setArguments(args);
        fragment.setIdQuestion(_quiz.getIdQuestionAffichee());
        fragment.setQuiz(_quiz);
        return fragment;
    }

    public QuestionnaireSlidePageFragment() {
    }
    

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quiz = (QuizzToScreen) getArguments().getSerializable(QUIZ_TO_SCREEN);
        idQuestion = getArguments().getInt(ID_QUESTION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        
        Question question = null;
		if(idQuestion < quiz.getQuiz().getLQuestions().size()){
			question = quiz.getQuiz().getLQuestions().get(idQuestion);
	        ((TextView) rootView.findViewById(R.id.question_titre)).setText(question.getLibelle());
		}

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return quiz.getIdQuestionAffichee();
    }
}
