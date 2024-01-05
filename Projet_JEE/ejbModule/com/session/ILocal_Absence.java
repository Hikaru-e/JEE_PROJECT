package com.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Absence;

/**
 * Session Bean implementation class ILocal_Absence
 */
@Stateless
@LocalBean
public class ILocal_Absence implements ILocal_AbsenceLocal {

	@PersistenceContext
	EntityManager mn;

	public ILocal_Absence() {
	}

	@Override
	public void addAbsence(Absence a) {
		mn.persist(a);

	}

	@Override
	public void deleteAbsence(int id_abs) {
		Absence s = mn.find(Absence.class, id_abs);
		mn.remove(s);

	}

	@Override
	public void updateAbsence(Absence a) {
		mn.merge(a);
	}

	@Override
	public Absence getAbsence(int id_abs) {
		Absence e = mn.find(Absence.class, id_abs);
		return e;
	}

	@Override
	public List<Absence> getallAbsences() {
		Query q = mn.createQuery("select a from Absence a");
		return q.getResultList();
	}

}
