package fr.gfi.quiz.json.jsonperso;

import java.io.UnsupportedEncodingException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.google.gson.Gson;

import fr.gfi.quiz.json.entite.Quiz;
import fr.gfi.quiz.json.moteur.JsonBuilder;

public class testJson extends TestCase {

	Gson gson;

	private static String jsonstatic = "{" +
			"  \"id\": 15," +
			"  \"candidat\": {" +
			"    \"id\": 1," +
			"    \"nom\": \"EVODE\"" +
			"  }," +
			"  \"examinateur\": {" +
			"    \"id\": 4," +
			"    \"nom\": \"OUAR\"" +
			"  }," +
			"  \"lTypesQuestions\": [" +
			"    {" +
			"      \"langage\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"JAVA\"" +
			"      }," +
			"      \"typeSujet\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"hibernate\"" +
			"      }," +
			"      \"difficulte\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"facile\"" +
			"      }" +
			"    }," +
			"    {" +
			"      \"langage\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"JAVA\"" +
			"      }," +
			"      \"typeSujet\": {" +
			"        \"id\": 2," +
			"        \"libelle\": \"Java/J2EE\"" +
			"      }," +
			"      \"difficulte\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"facile\"" +
			"      }" +
			"    }" +
			"  ]," +
			"  \"lQuestions\": [" +
			"    {" +
			"      \"id\": 0," +
			"      \"langage\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"JAVA\"" +
			"      }," +
			"      \"typeSujet\": {" +
			"        \"id\": 2," +
			"        \"libelle\": \"Java/J2EE\"" +
			"      }," +
			"      \"difficulte\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"facile\"" +
			"      }," +
			"      \"uniqueReponseCorrecte\": false," +
			"      \"dureeReflexionEnSec\": 120," +
			"      \"libelle\": \"qu\u0027est ce qu\u0027un war\"," +
			"      \"lReponses\": [" +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 35," +
			"            \"libelle\": \"une release pour un conteneur\"" +
			"          }" +
			"        }," +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 36," +
			"            \"libelle\": \"une image\"" +
			"          }" +
			"        }," +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 37," +
			"            \"libelle\": \"un éxécutable\"" +
			"          }" +
			"        }," +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 34," +
			"            \"libelle\": \"un zip\"" +
			"          }" +
			"        }" +
			"      ]" +
			"    }," +
			"    {" +
			"      \"id\": 0," +
			"      \"langage\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"JAVA\"" +
			"      }," +
			"      \"typeSujet\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"hibernate\"" +
			"      }," +
			"      \"difficulte\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"facile\"" +
			"      }," +
			"      \"uniqueReponseCorrecte\": true," +
			"      \"dureeReflexionEnSec\": 120," +
			"      \"libelle\": \"quel est la différence entre HQL et Criteria\"," +
			"      \"lReponses\": [" +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 32," +
			"            \"libelle\": \"l\u0027un est transactionnel, l\u0027autre non\"" +
			"          }" +
			"        }," +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 31," +
			"            \"libelle\": \"un utilise les objets, l\u0027autre le SQL\"" +
			"          }" +
			"        }," +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 33," +
			"            \"libelle\": \"aucune réponse précédement citée\"" +
			"          }" +
			"        }," +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 30," +
			"            \"libelle\": \"pas de différence\"" +
			"          }" +
			"        }" +
			"      ]" +
			"    }," +
			"    {" +
			"      \"id\": 0," +
			"      \"langage\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"JAVA\"" +
			"      }," +
			"      \"typeSujet\": {" +
			"        \"id\": 2," +
			"        \"libelle\": \"Java/J2EE\"" +
			"      }," +
			"      \"difficulte\": {" +
			"        \"id\": 1," +
			"        \"libelle\": \"facile\"" +
			"      }," +
			"      \"uniqueReponseCorrecte\": false," +
			"      \"dureeReflexionEnSec\": 222," +
			"      \"libelle\": \"test\"," +
			"      \"urlInmage\": \"http://quizzcmg-web.samourai.eu.cloudbees.net:80/imageUpload/20140216043837.jpg\"," +
			"      \"lReponses\": [" +
			"        {" +
			"          \"reponse\": {" +
			"            \"id\": 42," +
			"            \"libelle\": \"test1\"" +
			"          }" +
			"        }" +
			"      ]" +
			"    }" +
			"  ]" +
			"}";


	 /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public testJson( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return (Test) new TestSuite( testJson.class );
    }

	public void testDeserialisationQuiz() throws UnsupportedEncodingException {
		Quiz quiz = JsonBuilder.getBeanFromJson(jsonstatic);
		assertNotNull(quiz);
		String json = JsonBuilder.getJsonStringFromBean(quiz);
		System.out.println(json);
		Quiz quiz2 = JsonBuilder.getBeanFromJson(json);
		assertEquals(quiz, quiz2);
	}

}
