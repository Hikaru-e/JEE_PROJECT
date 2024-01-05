import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.entities.Absence;
import com.entities.Stagiaire;
import com.session.ILocal_AbsenceLocal;
import com.session.ILocal_StagiaireLocal;

@ManagedBean(name="AbsenceManagedBean")
@SessionScoped
public class AbsenceManagedBean {

	private int id;
	private Date date_debut;
	private Date date_fin;
	private String justification;

	private int selectedIdStagiaire;

	private Stagiaire stagiaire;

	private List<Absence> absences;

	@EJB
	ILocal_StagiaireLocal localStagiaire;

	@EJB
	ILocal_AbsenceLocal localAbsence;

	public void addAbsence() {
		Absence a = new Absence();
		a.setDate_debut(date_debut);
		a.setDate_fin(date_fin);
		a.setJustification(justification);

		Stagiaire s = localStagiaire.getStagiaire(selectedIdStagiaire);

		// Update nbAbsence field
		long absenceDays = calculateAbsenceDays(a);
		s.setNbAbsence(s.getNbAbsence() + absenceDays);
		localStagiaire.updateStagiaire(s);
		a.setStagiaire(s);

		for (Absence abs : localAbsence.getallAbsences()) {
			if (abs.getStagiaire().getId() == selectedIdStagiaire) {
				a.setId(abs.getId());
				localAbsence.updateAbsence(a);
				return;
			}
		}
		localAbsence.addAbsence(a);
	}

	public int calculateAbsenceDays(Absence absence) {
		Date startDate = absence.getDate_debut();
		Date endDate = absence.getDate_fin();
		if (startDate != null && endDate != null) {
			long diffMilliseconds = endDate.getTime() - startDate.getTime();
			return (int) (diffMilliseconds / (24 * 60 * 60 * 1000));
		} else {
			// Handle the case where either startDate or endDate is null
			return 0; // or throw an exception, return a special value, etc.
		}
	}


//      private long calculateAbsenceDays(Absence absence) {
//        Date startDate = absence.getDate_debut();
//        Date endDate = absence.getDate_fin();
//
//        // Convert Date to Instant and then to LocalDate
//        LocalDate localStartDate = ZInstant.of(startDate.toInstant()).toLocalDate();
//        LocalDate localEndDate = ZInstant.of(endDate.toInstant()).toLocalDate();
//
//        // Calculate the difference in days
//        long daysBetween = ChronoUnit.DAYS.between(localStartDate, localEndDate);
//
//        // Add 1 to include both the start and end dates
//        return daysBetween + 1;
//    }

	public void deleteAbsence(int id_absence) {
		localAbsence.deleteAbsence(id_absence);
	}

	/*
	 * public void updateAbsence() {
	 *
	 *
	 *
	 * localAbsence.updateAbsence(null); }
	 */

	public int getSelectedIdStagiaire() {
		return selectedIdStagiaire;
	}

	public List<Absence> getAbsences() {
		return localAbsence.getallAbsences();
	}

	public void setAbsences(List<Absence> absences) {
		this.absences = absences;
	}

	public void setSelectedIdStagiaire(int selectedIdStagiaire) {
		this.selectedIdStagiaire = selectedIdStagiaire;
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
