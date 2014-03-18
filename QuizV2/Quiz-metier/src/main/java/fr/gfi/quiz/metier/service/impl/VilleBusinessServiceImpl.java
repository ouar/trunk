package fr.gfi.quiz.metier.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import fr.gfi.quiz.dao.VilleDAO;
import fr.gfi.quiz.entite.Ville;
import fr.gfi.quiz.metier.service.VilleBusinessService;

@Service("villeBusinessService")
public class VilleBusinessServiceImpl implements VilleBusinessService {

	@Resource(name="villeDAO")
	private VilleDAO villeDAO;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public Ville findVilleByCodePostal(Long codePostal) {
		return villeDAO.findVilleByCodePostal(codePostal);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<Ville> findVilles(String nomVille, int maxSize) {
		return villeDAO.findVilles(nomVille, maxSize);
	}

}
