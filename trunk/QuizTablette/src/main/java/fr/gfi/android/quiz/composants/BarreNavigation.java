package fr.gfi.android.quiz.composants;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import fr.gfi.android.quiz.model.QuizzToScreen;
import fr.gfi.quiz.json.entite.Question;

public class BarreNavigation extends View{

	
	public static final int NOT_FOUND = -1;
	private QuizzToScreen quiz = null;
	private final Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int y = 30;
	private final int xMargeInit = 10;
	private int xMarge = 10;
	private int rayonNormal = 15;
	private int rayonSelection = 20;
	private Canvas _canvas;
	
	 

	public BarreNavigation(Context context) {
		super(context);
	}
	
	
	public BarreNavigation(Context context,QuizzToScreen quiz) {
		this(context);
		this.quiz = quiz;
	}


	@Override
	protected void onDraw(Canvas canvas) {
		_canvas = canvas;
		super.onDraw(canvas);
		mPaint.setColor(Color.GRAY);
		mPaint.setStrokeWidth(2);
		int xMargeTemp = xMargeInit;
		for(int i = 0 ; i < quiz.getQuiz().getLQuestions().size() ; i++){
			Question question =  quiz.getQuiz().getLQuestions().get(i);
			if(question.isRepondue()){
				mPaint.setStyle(Paint.Style.FILL);
			}else{
				mPaint.setStyle(Paint.Style.STROKE);
			}
			
			if(quiz.getIdQuestionAffichee() == i){
				canvas.drawCircle(xMargeTemp+rayonSelection, y, rayonSelection, mPaint);
				xMargeTemp +=(xMarge+(rayonSelection*2));
			}else{
				canvas.drawCircle(xMargeTemp+rayonNormal, y, rayonNormal, mPaint);
				xMargeTemp +=(xMarge+(rayonNormal*2));
			}
			
				
		}
	}


	public void refresh(){
		this.invalidate();
	}


	public int getIdSelectionne(float x) {
		Paint paintDebug = new Paint(Paint.ANTI_ALIAS_FLAG);
		paintDebug.setColor(Color.BLACK);
		int xLargeurQuestion = (2 * rayonNormal) + xMarge; 
		int xMax = xMargeInit + (xLargeurQuestion * (quiz.getQuiz().getLQuestions().size() - 1)) + (2*rayonSelection);
		//vérification des coordonnées X dans les bornes valides
		if(x > xMargeInit && x < xMax){
			int xTemp = (int) (x-xMargeInit);
			int idQuestion = (int) Math.floor(xTemp/xLargeurQuestion); 
			return idQuestion;
		}else{
			return NOT_FOUND;		
		}
	}
	
}
