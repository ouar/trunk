package fr.gfi.quiz.json.jsonperso;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.gson.Gson;

import fr.gfi.quiz.json.entite.ChoixQuiz;
import fr.gfi.quiz.json.entite.IdLibelle;
import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;

public class TestJsonServeur extends TestCase {

	Gson gson;

	private static String jsonstatic = "["+
  "{"+
    "\"langage\": {"+
      "\"id\": 3,"+
      "\"libelle\": \"JAVA\""+
    "},"+
    "\"sujet\": {"+
      "\"id\": 4,"+
      "\"libelle\": \"Hibernate\""+
    "},"+
    "\"difficulte\": {"+
      "\"id\": 1,"+
      "\"libelle\": \"facile\""+
    "}"+
  "}"+
"]";


	 /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestJsonServeur( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( TestJsonServeur.class );
    }

	
	public void testSerialisationQuiz() throws UnsupportedEncodingException {
		IdLibelle langage = new IdLibelle(3, "JAVA");
		IdLibelle sujet = new IdLibelle(4, "Hibernate");
		IdLibelle difficulte = new IdLibelle(1, "facile"); 
		
		ChoixQuiz choix = new ChoixQuiz(langage, sujet, difficulte);
		List<ChoixQuiz> lChoix = new ArrayList<ChoixQuiz>();
		lChoix.add(choix);
		
		String json = JsonBuilder.getJsonStringFromBean(lChoix);
		System.out.println(json);
		
	}
	
	public void testDeSerialisationQuiz() throws UnsupportedEncodingException {
		List<ChoixQuiz> lChoix = JsonBuilder.getChoixQuizBeanFromJson(jsonstatic);
		assertEquals(true, lChoix.size()==1);
		String sJson = JsonBuilder.getJsonStringFromBean(lChoix);
		List<ChoixQuiz> lChoix2 = JsonBuilder.getChoixQuizBeanFromJson(sJson);
		assertEquals(true, lChoix2.size()==1);
	}

}
