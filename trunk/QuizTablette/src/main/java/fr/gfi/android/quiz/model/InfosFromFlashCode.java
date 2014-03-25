package fr.gfi.android.quiz.model;

import org.apache.commons.lang.StringUtils;

public class InfosFromFlashCode {

	private final int iNbArguments = 2;
	private final String sSeparator = "|";
	
	//base de l'url du serveur, ex : http://quizz.gfi.fr/v2/Quizz
	private String sBaseUrl;
	private Integer iIdQuiz;
	
	public InfosFromFlashCode() {
		super();
	}

	public InfosFromFlashCode(String sBaseUrl, Integer iIdQuiz) {
		super();
		this.setsBaseUrl(sBaseUrl);
		this.setiIdQuiz(iIdQuiz);
	}
	
	
	//On recoit une chaine du style http://quizz.gfi.fr/v2/Quizz%47
	public InfosFromFlashCode(String sDataRaw) {
		super();
		if(StringUtils.isNotEmpty(sDataRaw) && StringUtils.containsAny(sDataRaw, sSeparator)){
			String[] sValues = StringUtils.split(sDataRaw, sSeparator);
			if(sValues.length == iNbArguments){
				this.setsBaseUrl(sValues[0]);
				this.setiIdQuiz(Integer.valueOf(sValues[1]).intValue());					
			}
		}
	}

	public String getsBaseUrl() {
		return sBaseUrl;
	}

	public void setsBaseUrl(String sBasUrl) {
		this.sBaseUrl = sBasUrl;
	}

	public Integer getiIdQuiz() {
		return iIdQuiz;
	}

	public void setiIdQuiz(Integer iIdQuiz) {
		this.iIdQuiz = iIdQuiz;
	}

	@Override
	public String toString() {
		return "InfosFromFlashCode [BaseUrl=" + sBaseUrl + ", iIdQuiz="
				+ iIdQuiz + "]";
	}
	
	
	
}
