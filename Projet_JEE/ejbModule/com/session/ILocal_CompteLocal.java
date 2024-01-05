package com.session;

import java.util.List;

import javax.ejb.Local;

import com.entities.Compte;

@Local
public interface ILocal_CompteLocal {

	public void addCompte(Compte c);

	public void deleteCompte(int id_compte);

	public void updateCompte(Compte c);

	public Compte getCompte(int id_compte);

	public List<Compte> getallComptes();

	public boolean connexion(String email, String pwd);

	public Compte getUserByUsername(String email);

}
