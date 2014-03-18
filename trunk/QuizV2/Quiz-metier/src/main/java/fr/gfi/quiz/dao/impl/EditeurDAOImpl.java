package fr.gfi.quiz.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import fr.gfi.quiz.dao.EditeurDAO;
import fr.gfi.quiz.critere.CritereRechercheEditeur;
import fr.gfi.quiz.entite.Editeur;

@Repository("editeurDAO")
@SuppressWarnings("unchecked")
public class EditeurDAOImpl implements EditeurDAO {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public void createEditeur(Editeur editeur) {
		this.sessionFactory.getCurrentSession().save(editeur);
	}

	public void deleteEditeur(Editeur editeur) {
		this.sessionFactory.getCurrentSession().delete(editeur);
	}

	public Editeur findEditeurById(long id) {
		return (Editeur) this.sessionFactory.getCurrentSession()
				.createCriteria(Editeur.class)
				.add(Restrictions.eq("id", Long.valueOf(id)))
				.setFetchMode("ville", FetchMode.JOIN)
				.setFetchMode("ouvrages", FetchMode.JOIN).uniqueResult();
	}

	public void updateEditeur(Editeur editeur) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(editeur);
	}

	@Cacheable(value = "cache-dref-editeur", key = "#critere.nom", condition = "#critere.nom != null and !#critere.nom.isEmpty()")
	public List<Editeur> findEditeurByPagination(CritereRechercheEditeur critere) {

		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Editeur.class);

		criteria.setFetchMode("ville", FetchMode.JOIN);

		// s'il y a des criteres, alors on va ajouter les criteres a la
		// connexion
		if (critere != null) {
			String nom = critere.getNom();
			String id = critere.getId();

			// s'il y a un critere sur l'identifiant
			if (id != null && !id.equals("") ) {
				criteria.add(Restrictions.eq("id", Integer.parseInt(id)));
			}

			// s'il y a un critere sur le nom
			if (nom != null && !nom.equals("") ) {
				criteria.add(Restrictions.like("nom", "%" + nom + "%"));
			}
		}

		// on recherche d'abord le nombre d'enregistrements
		criteria.setProjection(Projections.rowCount());
		critere.setTRowCount(((Long) criteria.uniqueResult()).intValue());

		// projection par defaut pour recuperer la liste des libraires
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// ajout du tri s
		if (critere.getSort() != null)
			criteria.addOrder(Order.asc(critere.getSort()));
		else
			criteria.addOrder(Order.asc("nom")); // defaut

		// pagination : FIXME décommenter les 2 lignes suivantes pour inclure la
		// pagination jsp(taglib ou jquery datatable)
		// criteria.setMaxResults(critere.getFetchSize());
		// criteria.setFirstResult(critere.getStartRow());

		// on recupere la partie de liste des editeurs qui nous interesse
		return criteria.list();
	}

	public List<Editeur> findAllEditeurPresent() {
		Criteria criteria = this.sessionFactory.getCurrentSession()
				.createCriteria(Editeur.class);
		criteria.setProjection(Projections.rowCount());

		// projection par defaut pour recuperer la liste des libraires
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

		// ajout du tri s
		criteria.addOrder(Order.asc("nom"));
		return criteria.list();
	}
}
