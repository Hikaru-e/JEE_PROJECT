package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ABSENCE")
public class Absence implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_abs")
	private int id;

	@Column(name = "date_debut_abs")
	private Date date_debut;
	@Column(name = "date_fin_abs")
	private Date date_fin;
	@Column(name = "justification_abs")
	private String justification;

	@ManyToOne
	@JoinColumn(name = "id_stagr")
	private Stagiaire stagiaire;

	public Absence() {
		super();
	}

	// Constructeur sans specifier l'id
	public Absence(Date date_debut, Date date_fin, String justification, Stagiaire stagiaire) {
		super();
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.justification = justification;
		this.stagiaire = stagiaire;
	}

	public Absence(int id, Date date_debut, Date date_fin, String justification) {
		super();
		this.id = id;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.justification = justification;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

}
