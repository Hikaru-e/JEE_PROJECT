package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Absence;

@Local
public interface ILocal_AbsenceLocal {
	public void addAbsence(Absence a);

	public void deleteAbsence(int id_abs);

	public void updateAbsence(Absence a);

	public Absence getAbsence(int id_abs);

	public List<Absence> getallAbsences();
}
