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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import fr.gfi.android.quiz.R;
import fr.gfi.quiz.json.entite.Question;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * QuestionnaireSlideActivity} samples.</p>
 */
public class QuestionnaireSlidePageFragment extends Fragment {

	public static final String QUESTION 		= "question";
	public static final String NUMERO_PAGE 		= "numPage";
	MyCustomAdaptater dataAdapter = null;


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

	private Question question;
    private int nbPageAffichee;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static QuestionnaireSlidePageFragment afficheQuestion(Question question,int _nbPageAffichee) {
        QuestionnaireSlidePageFragment fragment = new QuestionnaireSlidePageFragment();
        Bundle args = new Bundle();
        args.putSerializable(QUESTION, question);
        args.putInt(NUMERO_PAGE, _nbPageAffichee);
        fragment.setArguments(args);
        return fragment;
    }

    public QuestionnaireSlidePageFragment() {
    }
    

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = (Question) getArguments().getSerializable(QUESTION);
        nbPageAffichee = getArguments().getInt(NUMERO_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_screen_slide_page, container, false);

        ((TextView) rootView.findViewById(R.id.question_titre)).setText(question.getLibelle());
        
        ListView listReponse = ((ListView)rootView.findViewById(R.id.listeReponse));
        dataAdapter = new MyCustomAdaptater(this.getActivity(), R.layout.reponse, question.getlReponses());
        listReponse.setAdapter(dataAdapter);
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return nbPageAffichee;
    }
}
