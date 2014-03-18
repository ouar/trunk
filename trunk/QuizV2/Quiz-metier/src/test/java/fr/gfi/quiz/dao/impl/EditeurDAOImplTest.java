package fr.gfi.quiz.dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.quiz.dao.EditeurDAO;
import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
                "file:src/test/resources/spring/test-dao-config.xml"     
        })
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional 
public class EditeurDAOImplTest {

	@Resource(name="editeurDAO")
	private EditeurDAO editeurDAO;
	
    @Test
    public void testFindEditeur() {
    	CritereRechercheEditeur crits= new CritereRechercheEditeur();
    	crits.setNom("Au diable vauvert");
        List<Editeur> editeurs = editeurDAO.findEditeurByPagination(crits);
        
        assertNotNull(editeurs);
        assertTrue(editeurs.size() > 0);
        assertTrue(editeurs.get(0).getNom().equals("Au diable vauvert") );
    }


}
