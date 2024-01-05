package com.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Encadrant;

@Stateless
@LocalBean
public class ILocal_Encadrant implements ILocal_EncadrantLocal {

	@PersistenceContext
	EntityManager mn;

	public ILocal_Encadrant() {
		super();
	}

	@Override
	public void addEncadrant(Encadrant e) {
		mn.persist(e);
	}

	@Override
	public void deleteEncadrant(int id_enc) {
		Encadrant e = mn.find(Encadrant.class, id_enc);
		mn.remove(e);

	}

	@Override
	public void updateEncadrant(Encadrant e) {
		mn.merge(e);

	}

	@Override
	public Encadrant getEncadrant(int id_enc) {
		Encadrant e = mn.find(Encadrant.class, id_enc);
		return e;
	}

	@Override
	public List<Encadrant> getallEncadrants() {
		Query q = mn.createQuery("select e from Encadrant e");
		return q.getResultList();
	}

	@Override
	public List<Encadrant> getEncadrantByNom(String n) {
		Query query = mn.createQuery("SELECT s FROM Encadrant s WHERE s.nom =:nom");
		query.setParameter("nom", n);

		return query.getResultList();
	}

	@Override
	public Encadrant getEncadrantByCIN(String cin) {
		try {
			Query query = mn.createQuery("SELECT s FROM Encadrant s WHERE s.cin =:cin");
			query.setParameter("cin", cin);
			return (Encadrant) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
