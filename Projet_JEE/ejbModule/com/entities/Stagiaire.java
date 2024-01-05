package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STAGIAIRE")
public class Stagiaire implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stagr")
	private int id;

	@Column(name = "cin_stagr")
	private String cin;

	@Column(name = "nom_stagr")
	private String nom;

	@Column(name = "prenom_stagr")
	private String prenom;

	@Column(name = "email_stagr")
	private String email;

	@Column(name = "date_n_stagr")
	private Date dateN;

	@Column(name = "etab_stagr")
	private String ecole;

	@ManyToOne
	@JoinColumn(name = "id_enc")
	private Encadrant encadrant;

	@ManyToOne
	@JoinColumn(name = "id_stage")
	private Stage stage;

	@OneToMany(mappedBy = "stagiaire")
	private List<Absence> absences;

	@Column(name = "nb_absence")
	private long nbAbsence;

	public Stagiaire() {
		super();
	}

	// Constructeur sans specifier l'id
	public Stagiaire(String cin, String nom, String prenom, String email, Date dateN, String ecole, int nbAbsence) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateN = dateN;
		this.ecole = ecole;
		this.nbAbsence = nbAbsence;
	}

	public Stagiaire(String cin, String nom, String prenom, String email, Date dateN, String ecole, Encadrant encadrant,
			Stage stage, int nbAbsence) {
		super();
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateN = dateN;
		this.ecole = ecole;
		this.encadrant = encadrant;
		this.stage = stage;
		this.nbAbsence = nbAbsence;
	}

	public Stagiaire(int id, String cin, String nom, String prenom, String email, Date dateN, String ecole,
			Encadrant encadrant, Stage stage, int nbAbsence) {
		super();
		this.id = id;
		this.cin = cin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateN = dateN;
		this.ecole = ecole;
		this.encadrant = encadrant;
		this.stage = stage;
		this.nbAbsence = nbAbsence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Encadrant getEncadrant() {
		return encadrant;
	}

	public void setEncadrant(Encadrant encadrant) {
		this.encadrant = encadrant;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<Absence> getAbsences() {
		return absences;
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
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

	public Date getDateN() {
		return dateN;
	}

	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}

	public String getEcole() {
		return ecole;
	}

	public void setEcole(String ecole) {
		this.ecole = ecole;
	}

	public long getNbAbsence() {
		return nbAbsence;
	}

	public void setNbAbsence(long nbAbsence) {
		this.nbAbsence = nbAbsence;
	}

}