package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Stage;

@Local
public interface ILocal_StageLocal {

	public void addStage(Stage c);

	public void deleteStage(int id_stage);

	public void updateStage(Stage c);

	public Stage getStage(int id_stage);

	public List<Stage> getallStages();

}
