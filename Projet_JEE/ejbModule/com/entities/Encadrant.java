package com.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ENCADRANT")
public class Encadrant implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_enc")
	private int id;

	@Column(name = "cin_enc")
	private String cin;

	@Column(name = "nom_enc")
	private String nom;

	@Column(name = "prenom_enc")
	private String prenom;

	@Column(name = "email_enc")
	private String email;

	@Column(name = "division_enc")
	private String division;

	@OneToMany(mappedBy = "encadrant")
	List<Stagiaire> stagiaires;

	public Encadrant() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructeur sans specifier l'id
	public Encadrant(String cin, String nom, String prenom, String email, String division) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.division = division;
	}

	public Encadrant(int id, String cin, String nom, String prenom, String email, String division) {
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.division = division;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

}
