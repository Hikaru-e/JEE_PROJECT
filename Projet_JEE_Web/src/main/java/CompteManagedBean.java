import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.entities.Compte;
import com.session.ILocal_CompteLocal;

@ManagedBean(name = "CompteManagedBean")
@SessionScoped
public class CompteManagedBean {

	private int id;
	private String email;
	private String password;
	private String role;

	private Compte compteEnCoursDeModification;

	private List<Compte> comptes;

	@EJB
	ILocal_CompteLocal localCompte;

	public void createCompte() {
		Compte c = new Compte();
		c.setEmail(email);
		c.setPassword(password);
		c.setRole(role);
		localCompte.addCompte(c);
	}

	public void deleteCompte(int id) {
		localCompte.deleteCompte(id);
	}

	public String updateCompte() {
		Compte c = compteEnCoursDeModification;
		localCompte.updateCompte(c);
		return "/lists/list_comptes.xhtml?faces-redirect=true";
	}

	public Compte getCompte(int id) {
		return localCompte.getCompte(id);
	}



	public String login() {
	    // Check if form data is provided
	    if (email == null || email.trim().isEmpty() || password == null || password.trim().isEmpty()) {
	        return "";  // Return an empty string if form data is not provided
	    }

	    boolean response = localCompte.connexion(email, password);

	    if (response) {
	        // Get the user by username to access the role
	        Compte user = localCompte.getUserByUsername(email);

	        if (user != null) {
	            // Set the role in the session
	            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userRole", user.getRole());

	            if ("chef".equals(user.getRole())) {
//	                System.out.println("User Role: " + user.getRole());
	                return "/chefPage.xhtml?faces-redirect=true";
	            } else if ("admin".equals(user.getRole())) {
//	                System.out.println("User Role: " + user.getRole());
	                return "/adminPage.xhtml?faces-redirect=true";
	            }
	        }
	    }

	    // Return an outcome for failed login
	    return "false";
	}



	public String logout() {
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	    return "/login.xhtml?faces-redirect=true";
	}


	public String preparerModification(Compte compte) {
		compteEnCoursDeModification = compte;

		return "/modals/modifier_compte.xhtml?faces-redirect=true";
	}

	// Getters and setters
	public List<Compte> getComptes() {
		return localCompte.getallComptes();
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	public Compte getCompteEnCoursDeModification() {
		return compteEnCoursDeModification;
	}

	public void setCompteEnCoursDeModification(Compte compte) {
		this.compteEnCoursDeModification = compte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
