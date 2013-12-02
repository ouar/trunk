package fr.gfi.cmg.QuizzCmg;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Question;
import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.metier.exceptions.BusinessServiceException;
import fr.gfi.cmg.QuizzCmg.metier.service.AdminBusinessService;
import fr.gfi.cmg.QuizzCmg.metier.service.QuizzBusinessService;

/**
 * 
 * Exemple de test JUnit de la couche service avec DBUnit
 * 
 * 
 * @author GDA
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/commons-config.xml", "file:src/main/resources/quizz-test-config.xml" })
public class QuizzBusinessServiceTest {

	private Logger logger = LoggerFactory.getLogger(QuizzBusinessServiceTest.class);

	@Resource(name = "quizzDatasource")
	private DataSource quizzDatasource;

	@Resource(name = "adminBusinessService")
	AdminBusinessService bsAdmin;

	@Resource(name = "quizzBusinessService")
	private QuizzBusinessService quizzBS;

	/** dbunit - populate de la table avec un matelat de données */
	// protected IDatabaseTester dbTester;
	//
	// @Before
	// public void setUp() throws Exception {
	//
	// // creation de la classe utilitaire dbunit
	// dbTester = new DataSourceDatabaseTester(quizzDatasource);
	//
	// // sauf reutilisation d'un même fichier de données
	// // mettre le fichier XML des données dans le meme repertoire que la
	// // classe de test, coté src/test/resource
	// IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass()
	// .getResource("OuvragePersistenceServiceTest.xml"));
	//
	// // populate de la base
	// dbTester.setDataSet(dataSet);
	// //
	// dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
	// dbTester.onSetup();
	// }
	//
	// @After
	// public void tearDown() throws Exception {
	// // purge dbunit
	// dbTester.onTearDown();
	// }

//	@Test
//	public void testgetDetailsQuizz() throws BusinessServiceException {
//
//		logger.debug("recherche d'ouvrage par titre");
//
//		Quizz quizz = quizzBS.getDetailsQuizz(28);
//
//		ass(quizz);
//		// assertTrue(ouvrages.get(0).getTitre().equals("Le petit Nicholas") );
//	}

	@Test
	public void testgetAllQuestions() throws BusinessServiceException {

		logger.debug("recherche d'ouvrage par titre");

		List<Question> list = bsAdmin.getAllQuestionsResponses();

		assertNotNull(list);
		// assertTrue(ouvrages.get(0).getTitre().equals("Le petit Nicholas") );
	}

}
