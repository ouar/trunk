package fr.gfi.quiz.entite;

import java.io.Serializable;

public class PairInt implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8769235399571622090L;
	int int1;
	int int2;

	public PairInt() {
		// TODO Auto-generated constructor stub
	}

	public PairInt(int int1, int int2) {
		super();
		this.int1 = int1;
		this.int2 = int2;
	}

	public int getInt1() {
		return int1;
	}

	public void setInt1(int int1) {
		this.int1 = int1;
	}

	public int getInt2() {
		return int2;
	}

	public void setInt2(int int2) {
		this.int2 = int2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + int1;
		result = prime * result + int2;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PairInt other = (PairInt) obj;
		if (int1 != other.int1)
			return false;
		if (int2 != other.int2)
			return false;
		return true;
	}



}
