package fr.gfi.quiz.metier.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.gfi.quiz.dao.EditeurDAO;
import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;
import fr.gfi.quiz.metier.service.EditeurBusinessService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/spring/test-metier-config.xml" })
public class EditeurBusinessServiceImplTest {

	// mock du service de persistance sous-jacent
	@Mock
	private EditeurDAO editeurDAO;

	@Resource(name = "editeurBusinessService")
	@InjectMocks
	private EditeurBusinessService editeurBusinessService;

	@Before
	public void setUp() throws Exception {
		// init des annotations Mock car le runner reste en
		// SpringJUnit4ClassRunner (et non MockitoJUnitRunner)
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindEditeur() throws Exception {
		String nom = "Denoél";

		// resultat attendu du mock
		Editeur editeur = new Editeur();
		editeur.setId(Long.valueOf(26));
		editeur.setNom(nom);

		List<Editeur> expectedResult = new ArrayList<Editeur>();
		expectedResult.add(editeur);

		// mock des appels au service DAO sous-jacent
		// ATTENTION - s'il n'est pas primitif, le param�tre de la methode mock
		// doit avoir une methode "equals"
		CritereRechercheEditeur critmocks = new CritereRechercheEditeur();
		critmocks.setNom(nom);
		when(editeurDAO.findEditeurByPagination(critmocks)).thenReturn(
				expectedResult);

		// appel de la surcouche

		CritereRechercheEditeur crits = new CritereRechercheEditeur();
		crits.setNom(nom);

		List<Editeur> allEditeurs = editeurBusinessService
				.findAllEditeurByPagination(crits);

		assertNotNull(allEditeurs);
		assertTrue(allEditeurs.size() > 0);
		assertTrue(nom.equals(allEditeurs.get(0).getNom()));
	}

}
