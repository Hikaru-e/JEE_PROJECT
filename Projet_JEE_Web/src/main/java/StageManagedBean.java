import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Stage;
import com.entities.Stagiaire;
import com.session.ILocal_StageLocal;
import com.session.ILocal_StagiaireLocal;

@ManagedBean(name = "StageManagedBean")
@SessionScoped
public class StageManagedBean {

	private int id_stage;
//	@NotNull(message = "Le sujet du stage est requis")
	private String sujet_stage;
	private Date date_debut;
	private Date date_fin;

	private Stage stageEnCoursDeModification;

	private List<Stage> stages;

	private List<Stage> affecteStages = new ArrayList<>();
	private List<Stage> nonAffecteStages = new ArrayList<>();

	@EJB
	ILocal_StageLocal localStage;

	@EJB
	ILocal_StagiaireLocal stagiaireLocal;

	public String preparerModification(Stage stage) {
		this.stageEnCoursDeModification = stage;

		return "/modals/modifier_stage.xhtml?faces-redirect=true";
	}

	public void addStage() {
		Stage c = new Stage();
		c.setDate_debut(date_debut);
		c.setDate_fin(date_fin);
		c.setSujet_stage(sujet_stage);
		localStage.addStage(c);

	}

	public void deleteStage(int id_stage) {
		localStage.deleteStage(id_stage);
	}

	public String updateStage() {
		Stage c = stageEnCoursDeModification;
		localStage.updateStage(c);
		return "/lists/list_stages.xhtml?faces-redirect=true";
	}

	public String getStageStatus(int idStage) {
		List<Stagiaire> stagiaires = stagiaireLocal.getallStagiaires();

		for (Stagiaire stagiaire : stagiaires) {
			if (stagiaire.getStage() != null) {
				int idStage_1 = stagiaire.getStage().getId_stage();
				if (idStage_1 == idStage) {
					// L'encadrant recherché est associé à ce stagiaire
					return "AFFECTE";
				}
			}
		}
		// Aucun stagiaire n'est associé à l'encadrant recherché
		return "NON AFFECTE";
	}

    // Experimental
    public String getBadgeClass(Stage stage) {
        String stageStatus = getStageStatus(stage.getId_stage());

        switch (stageStatus) {
            case "AFFECTE":
                return "badge bg-primary";
            case "NON AFFECTE":
                return "badge bg-warning";
            default:
                return "";
        }
    }

	// Function to initialize arrays of all types of listes stagiaires
	public void updateStageStatus() {
		// Your existing implementation...

		// Clear existing lists
		nonAffecteStages.clear();
		affecteStages.clear();

		// Populate lists based on stagiereStatus
		for (Stage stage : localStage.getallStages()) {
			String status = getStageStatus(stage.getId_stage());
			switch (status) {
			case "AFFECTE":
				affecteStages.add(stage);
				break;
			case "NON AFFECTE":
				nonAffecteStages.add(stage);
				break;
			}
		}
	}

	public List<Stage> getAffecteStages() {
		updateStageStatus();
		return affecteStages;
	}

	public void setAffecteStages(List<Stage> affecteStages) {
		this.affecteStages = affecteStages;
	}

	public List<Stage> getNonAffecteStages() {
		updateStageStatus();
		return nonAffecteStages;
	}

	public void setNonAffecteStages(List<Stage> nonAffecteStages) {
		this.nonAffecteStages = nonAffecteStages;
	}

	public Stage getStageEnCoursDeModification() {
		return stageEnCoursDeModification;
	}

	public void setStageEnCoursDeModification(Stage stageEnCoursDeModification) {
		this.stageEnCoursDeModification = stageEnCoursDeModification;
	}

	public Stage getStage(int id_stage) {

		return localStage.getStage(id_stage);
	}

	public int getId_stage() {
		return id_stage;
	}

	public void setId_stage(int id_stage) {
		this.id_stage = id_stage;
	}

	public List<Stage> getStages() {
		return localStage.getallStages();
	}

	public void setStages(List<Stage> stages) {
		this.stages = stages;
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

}
