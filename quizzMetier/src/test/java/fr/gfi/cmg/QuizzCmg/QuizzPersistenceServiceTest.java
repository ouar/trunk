package fr.gfi.cmg.QuizzCmg;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.cmg.QuizzCmg.metier.entite.hibernate.Quizz;
import fr.gfi.cmg.QuizzCmg.persistance.service.QuizzPersistenceService;

/**
 * 
 * Exemple de test JUnit de la couche DAO avec DBUnit
 * 
 * 
 * @author GDA
 * 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resources/commons-config.xml",
		"file:src/main/resources/quizz-test-config.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class QuizzPersistenceServiceTest {

	@Resource(name = "quizzDatasource")
	private DataSource Datasource;

	@Resource(name = "quizzPersistenceService")
	private QuizzPersistenceService quizzPS;

	/** dbunit - populate de la table avec un matelat de données */
//	protected IDatabaseTester dbTester;
//
//	@Before
//	public void setUp() throws Exception {
//
//		dbTester = new DataSourceDatabaseTester(Datasource);
//
//		// sauf reutilisation d'un même fichier de données
//		// mettre le fichier XML des données dans le meme repertoire que la
//		// classe de test, coté src/test/resource
//		IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass()
//				.getResource("OuvragePersistenceServiceTest.xml"));
//		// populate de la base
//		dbTester.setDataSet(dataSet);
//		//
//		dbTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
//		dbTester.onSetup();
//	}
//
//	@After
//	public void tearDown() throws Exception {
//		dbTester.onTearDown();
//	}

	@Test
	public void testgetDetailsQuizz() {

		Quizz quizz = quizzPS.getDetailsQuizz(28);
//		assert.

	}

}
