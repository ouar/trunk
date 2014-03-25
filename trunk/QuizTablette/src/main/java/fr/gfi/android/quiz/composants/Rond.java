package fr.gfi.android.quiz.composants;

public class Rond {
	private int idQuestion;
	private float x;
	private float y;
	private int r;


	public Rond(int idQuestion, float x, float y, int r) {
		super();
		this.idQuestion = idQuestion;
		this.x = x;
		this.y = y;
		this.r = r;
	}

	public int getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}


}
