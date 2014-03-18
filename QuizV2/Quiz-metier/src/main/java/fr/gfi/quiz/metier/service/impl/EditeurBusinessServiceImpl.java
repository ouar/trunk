package fr.gfi.quiz.metier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.quiz.dao.EditeurDAO;
import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;
import fr.gfi.quiz.metier.service.EditeurBusinessService;

@Service("editeurBusinessService")
public class EditeurBusinessServiceImpl implements EditeurBusinessService {
	
	@Resource(name="editeurDAO")
	private EditeurDAO editeurDAO;

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void createEditeur(Editeur editeur) {
		editeurDAO.createEditeur(editeur);
	}

	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void deleteEditeur(Editeur editeur) {		
		editeurDAO.deleteEditeur(editeur);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Editeur findEditeurById(Long id) {
		return editeurDAO.findEditeurById(id);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public void updateEditeur(Editeur editeur) {
		editeurDAO.updateEditeur(editeur);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Editeur> findAllEditeurByPagination(CritereRechercheEditeur critere) {
		return editeurDAO.findEditeurByPagination(critere);
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Editeur> findAllEditeur() {
		return editeurDAO.findAllEditeurPresent();
	}
}