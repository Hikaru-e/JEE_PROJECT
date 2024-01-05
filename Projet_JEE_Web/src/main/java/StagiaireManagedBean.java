import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import com.entities.Encadrant;
import com.entities.Stage;
import com.entities.Stagiaire;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.session.ILocal_EncadrantLocal;
import com.session.ILocal_StageLocal;
import com.session.ILocal_StagiaireLocal;

@ManagedBean(name = "StagiaireManagedBean")
@SessionScoped
public class StagiaireManagedBean {

	@EJB
	ILocal_StagiaireLocal stagiaireLocal;

	@EJB
	ILocal_StageLocal stageLocal;

	@EJB
	ILocal_EncadrantLocal encadrantLocal;

	private int id;
	private String cin;
	private String nom;
	private String prenom;
	private String email;
	private Date dateN;
	private String ecole;
	private Stagiaire stagiaire;

	private int selectedStageID;
	private int selectedEncadrantID;
	private Stagiaire stagiaireEnCoursDeModification;
	private String choice;
	private String searchTerm;
	private String validationFailed;

	private Stagiaire stagiaire1;
	private List<Stagiaire> listStagiaire1 = new ArrayList<Stagiaire>();

	private List<Stagiaire> nouveauStagiaires = new ArrayList<>();
	private List<Stagiaire> enCoursStagiaires = new ArrayList<>();
	private List<Stagiaire> archiveStagiaires = new ArrayList<>();

	private List<Stagiaire> listStagiaire = new ArrayList<Stagiaire>();

	public void addStagiaire() {

		if (cin.isEmpty() || cin==null ||
				dateN == null ||
				ecole == null || ecole.isEmpty()||
				email == null || email.isEmpty()||
				nom == null   || nom.isEmpty()||
				prenom == null || prenom.isEmpty()
				) {
			 this.validationFailed = "true";

		}

		Stagiaire a = new Stagiaire();
		a.setCin(cin);
		a.setDateN(dateN);
		a.setEcole(ecole);
		a.setEmail(email);
		a.setNom(nom);
		a.setPrenom(prenom);
		Stage s = stageLocal.getStage(selectedStageID);
		a.setStage(s);

		Encadrant e = encadrantLocal.getEncadrant(selectedEncadrantID);
		a.setEncadrant(e);
		stagiaireLocal.addStagiaire(a);
		 this.validationFailed = "false";
	}



	public void deleteStagiaire(int id_stagr) {
		stagiaireLocal.deleteStagiaire(id_stagr);
	}

	public String updateStagiaire() {
//		Stagiaire a = stagiaireEnCoursDeModification;
		Stagiaire a = new Stagiaire();
		a.setId(stagiaireEnCoursDeModification.getId());
		a.setCin(stagiaireEnCoursDeModification.getCin());
		a.setDateN(stagiaireEnCoursDeModification.getDateN());
		a.setEcole(stagiaireEnCoursDeModification.getEcole());
		a.setEmail(stagiaireEnCoursDeModification.getEmail());
	    a.setEncadrant(stagiaireEnCoursDeModification.getEncadrant());
	    a.setNbAbsence(stagiaireEnCoursDeModification.getNbAbsence());
	    a.setNom(stagiaireEnCoursDeModification.getNom());
	    a.setPrenom(stagiaireEnCoursDeModification.getPrenom());

	    if(stagiaireEnCoursDeModification.getStage()!=null)
	    	a.setStage(stagiaireEnCoursDeModification.getStage());
	    else if(stagiaireEnCoursDeModification.getStage()==null)
	    	a.setStage(new Stage());

		stagiaireLocal.updateStagiaire(a);
		return "/lists/list_stagiaires.xhtml?faces-redirect=true";
	}
	public String preparerModification(Stagiaire stagiaire) {
		this.stagiaireEnCoursDeModification = stagiaire;
		// Ensure stagiaireEnCoursDeModification has a non-null stage property
		if (this.stagiaireEnCoursDeModification.getStage() == null) {
			this.stagiaireEnCoursDeModification.setStage(new Stage());
		}
		// Ensure stagiaireEnCoursDeModification has a non-null encadrant property
		if (this.stagiaireEnCoursDeModification.getEncadrant() == null) {
			this.stagiaireEnCoursDeModification.setEncadrant(new Encadrant());
		}
		return "/modals/modifier_stagiaire.xhtml?faces-redirect=true";
	}

	public String getValidationFailed() {
		return validationFailed;
	}


	public String isValidationFailed() {
		return validationFailed;
	}

	public void setValidationFailed(String validationFailed) {
		this.validationFailed = validationFailed;
	}

	public List<Stagiaire> getListStagiaire() {
		this.listStagiaire = stagiaireLocal.getallStagiaires();
		return this.listStagiaire;
	}

	public void setListStagiaire(List<Stagiaire> listStagiaire) {
		this.listStagiaire = listStagiaire;
	}

	// Experimental
	public String getStagiereStatus(Stagiaire stagiaire) {
		if (stagiaire == null) {
			return "NOUVEAU";
		} else if (stagiaire.getStage() == null) {
			return "NOUVEAU";
		} else {
			Date currentDate = new Date();

			if (stagiaire.getStage().getDate_debut() != null && currentDate.before(stagiaire.getStage().getDate_debut())) {
				return "NOUVEAU";
			} else if (stagiaire.getStage().getDate_fin() != null && currentDate.after(stagiaire.getStage().getDate_fin())) {
				return "EN ARCHIVE";
			} else {
				return "EN COURS";
			}
		}
	}

	// Experimental
	public String getBadgeClass(Stagiaire stagiaire) {
		String stagiereStatus = getStagiereStatus(stagiaire);

		switch (stagiereStatus) {
		case "NOUVEAU":
			return "badge bg-primary";
		case "EN ARCHIVE":
			return "badge bg-warning";
		case "EN COURS":
			return "badge bg-success";
		default:
			return "";
		}
	}

	// Function to initialize arrays of all types of listes stagiaires
	public void updateStagiereStatus() {
		// Your existing implementation...

		// Clear existing lists
		nouveauStagiaires.clear();
		enCoursStagiaires.clear();
		archiveStagiaires.clear();

		// Populate lists based on stagiereStatus
		for (Stagiaire stagiaire : stagiaireLocal.getallStagiaires()) {
			String status = getStagiereStatus(stagiaire);
			switch (status) {
			case "NOUVEAU":
				nouveauStagiaires.add(stagiaire);
				break;
			case "EN COURS":
				enCoursStagiaires.add(stagiaire);
				break;
			case "EN ARCHIVE":
				archiveStagiaires.add(stagiaire);
				break;
			}
		}
	}

	public void performSearch() {
		if ("nom".equals(choice)) {
			this.listStagiaire1.clear();
			this.listStagiaire1 = stagiaireLocal.getStagiairesByNom(searchTerm);
		} else if ("cin".equals(choice)) {
			this.stagiaire1 = stagiaireLocal.getStagiaireByCIN(searchTerm);
		}
	}

	// Experimental : pdf Certificate export
	public void generateCertificate(Stagiaire stagiaire) {
	    try {
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

	        String cin = stagiaire.getCin(); // Replace with your actual method to get cin

	        response.setContentType("application/pdf");
	        response.setHeader("Content-disposition", "attachment;filename=" + cin + "_certificat_de_stage.pdf");

	        Document document = new Document();
	        PdfWriter.getInstance(document, response.getOutputStream());

	        document.open();

	        // Add title
	        Font titleFont = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
	        Paragraph title = new Paragraph("Certificat de Fin de Stage", titleFont);
	        title.setAlignment(Element.ALIGN_CENTER);
	        document.add(title);

	        // Add a blank line
	        document.add(new Paragraph(" "));

	        // Add Stagiaire name, CIN, date_debut, date_fin, and duration
	        Font contentFont = new Font(Font.FontFamily.HELVETICA, 14);
	        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");

	        Paragraph content = new Paragraph();
	        content.add(new Phrase("Il est certifié que", contentFont));
	        content.add(new Phrase("\n\n"));

	        // Concatenate first name and last name
	        String fullName = stagiaire.getPrenom() + " " + stagiaire.getNom();

	        content.add(new Phrase(fullName, titleFont)); // Replace with concatenated name
	        content.add(new Phrase("\n\n"));
	        content.add(new Phrase("CIN: " + stagiaire.getCin(), contentFont)); // Add CIN
	        content.add(new Phrase("\n\n"));
	        content.add(new Phrase("a commencé son stage le", contentFont));
	        content.add(new Phrase("\n\n"));
	        content.add(new Phrase(dateFormat.format(stagiaire.getStage().getDate_debut()), titleFont)); // Add date_debut
	        content.add(new Phrase("\n\n"));
	        content.add(new Phrase("et l'a terminé avec succès le", contentFont));
	        content.add(new Phrase("\n\n"));
	        content.add(new Phrase(dateFormat.format(stagiaire.getStage().getDate_fin()), titleFont)); // Add date_fin
	        content.add(new Phrase("\n\n"));
	        content.add(new Phrase("pour une durée totale de", contentFont));
	        content.add(new Phrase("\n\n"));

	        // Calculate and add the duration
	        long durationInMilliseconds = stagiaire.getStage().getDate_fin().getTime() - stagiaire.getStage().getDate_debut().getTime();
	        long durationInDays = TimeUnit.MILLISECONDS.toDays(durationInMilliseconds);
	        content.add(new Phrase(durationInDays + " jours", titleFont)); // Add duration in days

	        content.setAlignment(Element.ALIGN_CENTER);
	        document.add(content);

	        document.close();

	        facesContext.responseComplete();
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle the exception appropriately (log it, show an error message, etc.)
	    }
	}


	public Stagiaire getStagiaire1() {
		return stagiaire1;
	}

	public void setStagiaire1(Stagiaire stagiaire1) {
		this.stagiaire1 = stagiaire1;
	}

	public List<Stagiaire> getListStagiaire1() {
		return listStagiaire1;
	}

	public void setListStagiaire1(List<Stagiaire> listStagiaire1) {
		this.listStagiaire1 = listStagiaire1;
	}

	public Stagiaire getStagiaire() {
		this.stagiaire = stagiaireLocal.getStagiaire(id);
		return this.stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
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

	public int getSelectedStageID() {
		return selectedStageID;
	}

	public Stagiaire getStagiaireEnCoursDeModification() {
		return stagiaireEnCoursDeModification;
	}

	public void setStagiaireEnCoursDeModification(Stagiaire stagiaireEnCoursDeModification) {
		this.stagiaireEnCoursDeModification = stagiaireEnCoursDeModification;
	}

	public void setSelectedStageID(int selectedStageID) {
		this.selectedStageID = selectedStageID;
	}

	public int getSelectedEncadrantID() {
		return selectedEncadrantID;
	}

	public void setSelectedEncadrantID(int selectedEncadrantID) {
		this.selectedEncadrantID = selectedEncadrantID;
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

	public List<Stagiaire> getNouveauStagiaires() {
		updateStagiereStatus();
		return nouveauStagiaires;
	}

	public void setNouveauStagiaires(List<Stagiaire> nouveauStagiaires) {
		this.nouveauStagiaires = nouveauStagiaires;
	}

	public List<Stagiaire> getEnCoursStagiaires() {
		updateStagiereStatus();
		return enCoursStagiaires;
	}

	public void setEnCoursStagiaires(List<Stagiaire> enCoursStagiaires) {
		this.enCoursStagiaires = enCoursStagiaires;
	}

	public List<Stagiaire> getArchiveStagiaires() {
		updateStagiereStatus();
		return archiveStagiaires;
	}

	public void setArchiveStagiaires(List<Stagiaire> archiveStagiaires) {
		this.archiveStagiaires = archiveStagiaires;
	}

}
