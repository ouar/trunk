package fr.gfi.quiz.presentation.exception;

@SuppressWarnings("serial")
/**
 * Exception uniquement de la couche présentation pour illuster une utilisation du Exception Handler
 * et l'externalisation des messages
 * 
 * @author CCMT Team
 */
public class VilleInconnueException extends AbstractPresentationException {
	
	private Long codePostal;
	private String labelErreur;

	public VilleInconnueException(String labelErreur, Long codePostal) {
		this.labelErreur= labelErreur;
		this.codePostal = codePostal;
	}
	
	public Long getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Long codePostal) {
		this.codePostal = codePostal;
	}

	@Override
	public String getMessage() {
		if(null != codePostal){
			return labelErreur + " : code postale saisi = " + String.valueOf(codePostal);
		}else{
			return labelErreur;
		}
		
	}
	
}
