package fr.gfi.quiz.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import fr.gfi.quiz.dao.VilleDAO;
import fr.gfi.quiz.entite.Ville;

@SuppressWarnings("unchecked")
@Repository("villeDAO")
public class VilleDAOImpl implements VilleDAO {

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	@Cacheable(value="cache-dref-ville", key="#codePostal",condition="#codePostal != null")
	public Ville findVilleByCodePostal(Long codePostal) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Ville.class);
				
		if(null != codePostal){
			criteria.add(Restrictions.eq("codePostal", codePostal));
			return (Ville) criteria.uniqueResult();
			
		}else{
			return null;
		}
		
		
	}

	@Override
	@Cacheable(value="cache-dref-ville", key="#nomVille", condition="#nomVille != null and !#nomVille.isEmpty()")
	public List<Ville> findVilles(String nomVille, int maxSize) {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Ville.class);
				
		if (StringUtils.isNotEmpty(nomVille)) {
			criteria.add(Restrictions.like("nom", nomVille, MatchMode.ANYWHERE));
		}
		
		criteria.setMaxResults(maxSize);
		
		return criteria.list();
	}

}
