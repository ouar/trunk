package fr.gfi.quiz.presentation.admin.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class QuestionForm implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 3874891119784881055L;
	String libelle;
	int idSujet;
	int idDifficulte;
	int dureeReflexionEnSeconde;
	MultipartFile capture;
	List<Proposition> lPropositions = new ArrayList<Proposition>();

}
