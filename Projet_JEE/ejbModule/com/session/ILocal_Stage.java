package com.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.entities.Stage;

@Stateless
@LocalBean
public class ILocal_Stage implements ILocal_StageLocal {

	@PersistenceContext
	EntityManager mn;

	public ILocal_Stage() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addStage(Stage a) {
		mn.persist(a);
	}

	@Override
	public void deleteStage(int id_stage) {
		Stage s = mn.find(Stage.class, id_stage);
		mn.remove(s);

	}

	@Override
	public void updateStage(Stage s) {
		mn.merge(s);
	}

	@Override
	public Stage getStage(int id_stage) {
		Stage s = mn.find(Stage.class, id_stage);
		return s;

	}

	@Override
	public List<Stage> getallStages() {
		Query q = mn.createQuery("select s from Stage s");
		return q.getResultList();

	}
}
