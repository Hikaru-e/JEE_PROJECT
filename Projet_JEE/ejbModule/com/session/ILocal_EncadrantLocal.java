package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Encadrant;

@Local
public interface ILocal_EncadrantLocal {

	public void addEncadrant(Encadrant a);

	public void deleteEncadrant(int id_enc);

	public void updateEncadrant(Encadrant a);

	public Encadrant getEncadrant(int id_enc);

	public List<Encadrant> getallEncadrants();

	public List<Encadrant> getEncadrantByNom(String n);

	public Encadrant getEncadrantByCIN(String cin);

}
