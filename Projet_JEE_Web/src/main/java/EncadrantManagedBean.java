import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Encadrant;
import com.entities.Stagiaire;
import com.session.ILocal_EncadrantLocal;
import com.session.ILocal_StagiaireLocal;

@ManagedBean(name = "EncadrantManagedBean")
@SessionScoped
public class EncadrantManagedBean {

	private int id;
	private String cin;
	private String nom;
	private String prenom;
	private String email;
	private String division;

	private Encadrant encadrantEnCoursDeModification;

	private List<Encadrant> encadrants;
	private List<Encadrant> affecteEncadrants = new ArrayList<>();
	private List<Encadrant> nonAffecteEncadrants = new ArrayList<>();

	private String choice;
	private String searchTerm;

	private Encadrant encadrant1;
	private List<Encadrant> listEncadrant1 = new ArrayList<Encadrant>();

	@EJB
	ILocal_EncadrantLocal localEncadrant;

	@EJB
	ILocal_StagiaireLocal stagiaireLocal;

	public void addEncadrant() {
		Encadrant e = new Encadrant();
		e.setCin(cin);
		e.setDivision(division);
		e.setEmail(email);
		e.setNom(nom);
		e.setPrenom(prenom);
		localEncadrant.addEncadrant(e);
	}

	public void deleteEncadrant(int id_enc) {
		localEncadrant.deleteEncadrant(id_enc);
	}

	public String updatEncadrant() {
		Encadrant e = encadrantEnCoursDeModification;
		localEncadrant.updateEncadrant(e);
		return "/lists/list_encadrants.xhtml?faces-redirect=true";
	}

	public String getEncadrantStatus(int idEncadrantRecherche) {
		List<Stagiaire> stagiaires = stagiaireLocal.getallStagiaires();

		for (Stagiaire stagiaire : stagiaires) {
			if (stagiaire.getEncadrant() != null) {
				int idEncadrantDuStagiaire = stagiaire.getEncadrant().getId();

				if (idEncadrantDuStagiaire == idEncadrantRecherche) {
					// L'encadrant recherché est associé à ce stagiaire
					return "AFFECTE";
				}
			}
		}

		// Aucun stagiaire n'est associé à l'encadrant recherché
		return "NON AFFECTE";
	}

	public String getBadgeClass(Encadrant encadrant) {
		String encadrantStatus = getEncadrantStatus(encadrant.getId());

		switch (encadrantStatus) {
		case "NON AFFECTE":

			return "badge bg-warning";
		case "AFFECTE":
			return "badge bg-success";
		default:
			return "";
		}
	}

	public void updateEncadrantStatus() {
		// Your existing implementation...

		// Clear existing lists
		affecteEncadrants.clear();
		nonAffecteEncadrants.clear();

		// Populate lists based on stagiereStatus
		for (Encadrant encadrant : localEncadrant.getallEncadrants()) {
			String status = getEncadrantStatus(encadrant.getId());
			switch (status) {
			case "AFFECTE":
				affecteEncadrants.add(encadrant);
				break;
			case "NON AFFECTE":
				nonAffecteEncadrants.add(encadrant);
				break;

			}
		}
	}

	public void performSearch() {
		if ("nom".equals(choice)) {
			this.listEncadrant1.clear();
			this.listEncadrant1 = localEncadrant.getEncadrantByNom(searchTerm);
		} else if ("cin".equals(choice)) {
			this.encadrant1 = localEncadrant.getEncadrantByCIN(searchTerm);
		}
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public Encadrant getEncadrant1() {
		return encadrant1;
	}

	public void setEncadrant1(Encadrant encadrant1) {
		this.encadrant1 = encadrant1;
	}

	public List<Encadrant> getListEncadrant1() {
		return listEncadrant1;
	}

	public void setListEncadrant1(List<Encadrant> listEncadrant1) {
		this.listEncadrant1 = listEncadrant1;
	}

	public String preparerModification(Encadrant encadrant) {
		this.encadrantEnCoursDeModification = encadrant;
		return "/modals/modifier_encadrant.xhtml?faces-redirect=true";
	}

	public List<Encadrant> getEncadrants() {
		return localEncadrant.getallEncadrants();
	}

	public void setEncadrants(List<Encadrant> encadrants) {
		this.encadrants = encadrants;
	}

	public int getId() {
		return id;
	}

	public Encadrant getEncadrantEnCoursDeModification() {
		return encadrantEnCoursDeModification;
	}

	public void setEncadrantEnCoursDeModification(Encadrant encadrantEnCoursDeModification) {
		this.encadrantEnCoursDeModification = encadrantEnCoursDeModification;
	}

	public List<Encadrant> getAffecteEncadrants() {
		updateEncadrantStatus();
		return affecteEncadrants;
	}

	public void setAffecteEncadrants(List<Encadrant> affecteEncadrants) {
		this.affecteEncadrants = affecteEncadrants;
	}

	public List<Encadrant> getNonAffecteEncadrants() {
		updateEncadrantStatus();
		return nonAffecteEncadrants;
	}

	public void setNonAffecteEncadrants(List<Encadrant> nonAffecteEncadrants) {
		this.nonAffecteEncadrants = nonAffecteEncadrants;
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

}
