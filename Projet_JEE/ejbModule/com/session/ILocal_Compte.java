package com.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Compte;

/**
 * Session Bean implementation class ILocal_Compte
 */
@Stateless
@LocalBean
public class ILocal_Compte implements ILocal_CompteLocal {

	@PersistenceContext
	EntityManager mn;

	public ILocal_Compte() {
	}

	@Override
	public void addCompte(Compte c) {
		mn.persist(c);

	}

	@Override
	public void deleteCompte(int id_compte) {
		Compte c = mn.find(Compte.class, id_compte);
		mn.remove(c);

	}

	@Override
	public void updateCompte(Compte c) {
		mn.merge(c);

	}

	@Override
	public Compte getCompte(int id_compte) {
		Compte c = mn.find(Compte.class, id_compte);
		return c;
	}

	@Override
	public List<Compte> getallComptes() {
		Query q = mn.createQuery("select c from Compte c");
		return q.getResultList();
	}

	@Override
	public boolean connexion(String email, String pwd) {
		Query query = mn.createQuery("SELECT c FROM Compte c WHERE c.email =:email AND c.password =:password");

		// Définit les paramètres de la requête avec les valeurs fournies
		query.setParameter("email", email);
		query.setParameter("password", pwd);

		List<Compte> result = query.getResultList();

		// Vérifie si la liste résultante est vide (pas de correspondance trouvée)
		return !result.isEmpty();
	}

	@Override
	public Compte getUserByUsername(String email) {
		Query query = mn.createQuery("SELECT c FROM Compte c WHERE c.email =:email");
		query.setParameter("email", email);

		List<Compte> result = query.getResultList();

		if (!result.isEmpty()) {
			return result.get(0);
		}

		return null;
	}

}
