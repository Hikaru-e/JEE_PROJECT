package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Stagiaire;

@Local
public interface ILocal_StagiaireLocal {

	public void addStagiaire(Stagiaire a);

	public void deleteStagiaire(int id_stagr);

	public void updateStagiaire(Stagiaire a);

	public Stagiaire getStagiaire(int id_stagr);

	public List<Stagiaire> getallStagiaires();

	public List<Stagiaire> getStagiairesByNom(String nom);

	public Stagiaire getStagiaireByCIN(String cin);
}
