package fr.gfi.cmg.QuizzCmg.persistance.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;

public class MyRequest {
	// Modifie le Criteria pour ajouter les tables associ�es � la requete
	// en FetchMode = JOIN
	private static Criteria addAssocToCriteria(Criteria criteres, List<String> lAssociations) {

		// v�rification de la liste des tables associ�es, si elle est vide pas
		// de traitement sp�cifique
		if ((lAssociations != null) && !lAssociations.isEmpty()) {

			final List<String> lAssociationsSplit = new ArrayList<String>();

			// on parcourt les diff�rentes tables associ�es
			for (final String sTable : lAssociations) {

				int indiceDernierPoint = 0;
				int indiceFin = 0;
				String sTableAAjouter = "";

				// boucle pour s�parer les diff�rents niveaux de profondeur des
				// tables
				// ex: si l'association est sur
				// taClient.taTiers.taPersonneMorale
				// cette boucle a pour rôle de cr�er les associations suivantes
				// taClient
				// taClient.taTiers
				// taClient.taTiers.taPersonneMorale

				while (sTable.indexOf('.', indiceDernierPoint + 1) > 0) {

					indiceFin = sTable.indexOf('.', indiceDernierPoint + 1);

					sTableAAjouter = sTable.substring(0, indiceFin);

					if (!lAssociations.contains(sTableAAjouter) && !lAssociationsSplit.contains(sTableAAjouter)) {

						lAssociationsSplit.add(sTableAAjouter);

					}

					indiceDernierPoint = indiceFin + 1;
				}
				lAssociationsSplit.add(sTable);
			}

			// Pour toutes les tables � associer on ajoute le fetch mode � JOIN
			for (final String sTable : lAssociationsSplit) {
				criteres.setFetchMode(sTable, FetchMode.JOIN);
			}
		}
		return criteres;
	}

	@SuppressWarnings("rawtypes")
	public static List list(Criteria criteres, List<String> lAssociations) {
		final Criteria criteresAvecAssociations = MyRequest.addAssocToCriteria(criteres, lAssociations);
		return criteresAvecAssociations.list();
	}

	public static Object uniqueResult(Criteria criteres, List<String> lAssociations) {
		final Criteria criteresAvecAssociations = MyRequest.addAssocToCriteria(criteres, lAssociations);
		return criteresAvecAssociations.uniqueResult();
	}
}
