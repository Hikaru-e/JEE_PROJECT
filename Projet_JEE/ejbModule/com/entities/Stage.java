package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "STAGE")
public class Stage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_stage", nullable = false)
	private int id_stage;

	@Column(name = "sujet_stage", nullable = false)
	private String sujet_stage;

	@Column(name = "date_debut_stage", nullable = false)
	private Date date_debut;

	@Column(name = "date_fin_stage", nullable = false)
	private Date date_fin;

	@OneToMany(mappedBy = "stage")
	private List<Stagiaire> stagiaires;

	public Stage() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructeur sans specifier l'id
	public Stage(String sujet_stage, Date date_debut, Date date_fin, List<Stagiaire> stagiaires) {
		super();
		this.sujet_stage = sujet_stage;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.stagiaires = stagiaires;
	}

	public Stage(int id_stage, String sujet_stage, Date date_debut, Date date_fin) {
		super();
		this.id_stage = id_stage;
		this.sujet_stage = sujet_stage;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}

	public int getId_stage() {
		return id_stage;
	}

	public void setId_stage(int id_stage) {
		this.id_stage = id_stage;
	}

	public String getSujet_stage() {
		return sujet_stage;
	}

	public void setSujet_stage(String sujet_stage) {
		this.sujet_stage = sujet_stage;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}

}
