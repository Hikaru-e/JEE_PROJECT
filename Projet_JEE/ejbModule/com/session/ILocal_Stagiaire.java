package com.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Stagiaire;

@Stateless
@LocalBean
public class ILocal_Stagiaire implements ILocal_StagiaireLocal {

	@PersistenceContext
	EntityManager mn;

	public ILocal_Stagiaire() {
	}

	@Override
	public void addStagiaire(Stagiaire a) {
		mn.persist(a);
	}

	@Override
	public void deleteStagiaire(int id_stagr) {
		Stagiaire s = mn.find(Stagiaire.class, id_stagr);
		mn.remove(s);
	}

	@Override
	public void updateStagiaire(Stagiaire a) {
		mn.merge(a);
	}

	@Override
	public Stagiaire getStagiaire(int id_stagr) {
		Stagiaire s = mn.find(Stagiaire.class, id_stagr);
		return s;

	}

	@Override
	public List<Stagiaire> getallStagiaires() {
		Query q = mn.createQuery("select s from Stagiaire s");

		return q.getResultList();

	}

	@Override
	public List<Stagiaire> getStagiairesByNom(String n) {
		Query query = mn.createQuery("SELECT s FROM Stagiaire s WHERE s.nom =:nom");
		query.setParameter("nom", n);

		return query.getResultList();
	}

	@Override
	public Stagiaire getStagiaireByCIN(String cin) {
		try {
			Query query = mn.createQuery("SELECT s FROM Stagiaire s WHERE s.cin =:cin");
			query.setParameter("cin", cin);
			return (Stagiaire) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
