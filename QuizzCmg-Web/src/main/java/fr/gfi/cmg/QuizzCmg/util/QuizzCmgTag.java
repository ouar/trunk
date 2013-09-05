package fr.gfi.cmg.QuizzCmg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import fr.gfi.cmg.QuizzCmg.persistance.util.BeanNiveauTypeSujet;
import fr.gfi.cmg.QuizzCmg.presentation.beans.DifficulteBean;
import fr.gfi.cmg.QuizzCmg.presentation.beans.SujetBean;
import fr.gfi.cmg.QuizzCmg.presentation.beans.langageBean;

public class QuizzCmgTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7404793818140184505L;

	private StringBuffer contentBuffer = new StringBuffer();
	private List<langageBean> lLangageBeans;

	List<BeanNiveauTypeSujet> listPanier;

	List<BeanNiveauTypeSujet> listAselectionner;

	@Override
	public int doStartTag() throws JspException {
		contentBuffer.append("<script type=\"text/javascript\" charset=\"utf-8\">\n\n");

		if (lLangageBeans != null && lLangageBeans.size() > 0) {

			contentBuffer.append("var tableauListLangage = [];\n");
			contentBuffer.append("var aSelectionner;\n");
			contentBuffer.append("var panier;\n");
			contentBuffer.append("$(document).ready(function() {\n");

			contentBuffer.append("var tableauListTypeSujet;\n");
			contentBuffer.append("var tableauListeNiveau;\n");
			contentBuffer.append("var TypeSujet;\n");

			contentBuffer.append("var nouveauTableau = [];aSelectionner = new $.fn.Quizz(nouveauTableau);\n");
			contentBuffer.append("var nouveauTableauPanier = [];panier = new $.fn.Quizz(nouveauTableauPanier);\n");

			for (langageBean langageBean : lLangageBeans) {
				contentBuffer.append("tableauListTypeSujet = [];\n");
				List<SujetBean> listTypeSujet = langageBean.getlSujet();

				for (SujetBean sujetBean : listTypeSujet) {
					contentBuffer.append("tableauListeNiveau= [];\n");
					List<DifficulteBean> listNiveau = sujetBean.getlDifficultes();
					for (DifficulteBean difficulteBean : listNiveau) {

						contentBuffer.append("var NiveauQuestion = new $.fn.NiveauQuestion(" + difficulteBean.getId() + ",'" + difficulteBean.getLibelle() + "');\n");
						contentBuffer.append("tableauListeNiveau.push( NiveauQuestion );\n");
						if (listPanier.size() == 0) {
							contentBuffer.append("var ObjetPanier = new $.fn.ObjetPanier('" + langageBean.getId() + "','" + langageBean.getLibelle() + "','" + sujetBean.getId() + "','" + sujetBean.getLibelle() + "','" + difficulteBean.getId() + "','" + difficulteBean.getLibelle() + "');\n");
							contentBuffer.append("aSelectionner.elements.listObjetPanier.push( ObjetPanier );\n");
						}
					}

					contentBuffer.append("TypeSujet = new $.fn.TypeSujet(" + sujetBean.getId() + ",'" + sujetBean.getLibelle() + "',tableauListeNiveau);\n");
					contentBuffer.append("tableauListTypeSujet.push( TypeSujet );\n");

				}

				contentBuffer.append("var langage = new $.fn.Langage(" + langageBean.getId() + ",'" + langageBean.getLibelle() + "'," + "tableauListTypeSujet);\n");
				contentBuffer.append("tableauListLangage.push( langage );\n");

			}

			contentBuffer.append("var options = '';\n");
			// contentBuffer.append("options += '<option value=\"\" >------</option>';\n");
			contentBuffer.append("for (var i = 0; i < tableauListLangage.length; i++) {\n");
			contentBuffer.append("options += '<option value=\"' + tableauListLangage[i].elements.idLangage + '\">' + tableauListLangage[i].elements.libelleLangage + '</option>';\n");
			contentBuffer.append(" }\n");
			contentBuffer.append(" $(\"select#IdLangage\").html(options);\n");

			if (listPanier != null && listPanier.size() > 0) {

				for (BeanNiveauTypeSujet bean : listPanier) {
					contentBuffer.append("var ObjetPanier = new $.fn.ObjetPanier('" + bean.getTypeSujet().getId() + "','" + bean.getTypeSujet().getLibelle() + "','" + bean.getTypeSujet().getId() + "','" + bean.getTypeSujet().getLibelle() + "','" + bean.getNiveauQuestion().getId() + "','" + bean.getNiveauQuestion().getLibNiveau() + "');\n");
					contentBuffer.append("panier.elements.listObjetPanier.push( ObjetPanier );\n");
				}
			}
			if (listAselectionner != null && listAselectionner.size() > 0) {

				for (BeanNiveauTypeSujet bean : listAselectionner) {
					contentBuffer.append("var ObjetPanier = new $.fn.ObjetPanier('" + bean.getTypeSujet().getId() + "','" + bean.getTypeSujet().getLibelle() + "','" + bean.getTypeSujet().getId() + "','" + bean.getTypeSujet().getLibelle() + "','" + bean.getNiveauQuestion().getId() + "','" + bean.getNiveauQuestion().getLibNiveau() + "');\n");
					contentBuffer.append("aSelectionner.elements.listObjetPanier.push( ObjetPanier );\n");
				}
			}

			
			contentBuffer.append("raffraichirPanier();\n");
			contentBuffer.append("});\n");
		}

		contentBuffer.append("</script>\n\n");

		JspWriter localJspWriter = pageContext.getOut();

		try {
			localJspWriter.print(contentBuffer.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}
		contentBuffer.delete(0, contentBuffer.length());
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		JspWriter localJspWriter = pageContext.getOut();

		try {
			localJspWriter.print(contentBuffer.toString());
		} catch (IOException e) {
			throw new JspException(e);
		}

		dropData();
		return SKIP_BODY;
	}

	@Override
	public int doAfterBody() throws JspException {
		return SKIP_BODY;
	}

	/**
	 * Méthode dropData()
	 */
	private void dropData() {
		contentBuffer = new StringBuffer();
		lLangageBeans = new ArrayList<langageBean>();
		listPanier = new ArrayList<BeanNiveauTypeSujet>();
		listAselectionner = new ArrayList<BeanNiveauTypeSujet>();

	}

	public List<langageBean> getlLangageBeans() {
		return lLangageBeans;
	}

	public void setlLangageBeans(List<langageBean> lLangageBeans) {
		this.lLangageBeans = lLangageBeans;
	}

	public List<BeanNiveauTypeSujet> getListPanier() {
		return listPanier;
	}

	public void setListPanier(List<BeanNiveauTypeSujet> listPanier) {
		this.listPanier = listPanier;
	}

	public List<BeanNiveauTypeSujet> getListAselectionner() {
		return listAselectionner;
	}

	public void setListAselectionner(List<BeanNiveauTypeSujet> listAselectionner) {
		this.listAselectionner = listAselectionner;
	}

}
