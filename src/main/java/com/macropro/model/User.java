package com.macropro.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.macropro.annotation.PasswordMatches;
import com.macropro.annotation.ValidEmail;

@Entity
@PasswordMatches
@Table(name="user")
//@IdClass(UserPK.class)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="username")
	@NotNull
	@Size(min=1, max=20, message="must be between 1 and 20 characters")
	private String username;
	
	@Column(name="password")
	@NotNull
	@Size(min=1, max=100, message="must be between 1 and 100 characters")
	private String password;
	
	@Column(name="matching_password")
	@NotNull
	@Size(min=1, max=100, message="must be between 1 and 100 characters")
	private String matchingPassword;
	
	@Column(name="first_name")
	@NotNull
	@Size(min=1, max=50, message="must be between 1 and 50 characters")
	private String firstName;
	
	@Column(name="last_name")
	@NotNull
	@Size(min=1, max=50, message="must be between 1 and 50 characters")
	private String lastName;
	
	@Column(name="email")
	@ValidEmail
	@NotNull
	@Size(min=1, max=65, message="must be between 1 and 65 characters")
	private String email;

	@Size(min=10, max=10, message="must be 10 characters")
	@Column(name="phone_number")
	private String phoneNumber;

	@Size(min=1, max=31, message="must be between 1 and 31 characters")
	@Column(name="country")
	private String country;
	
	@NotNull
	@Column(name="enabled")
	private boolean enabled;

	@Column(name="submitted_goal")
	private boolean submittedGoal;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="goal_id")
	private UserGoal goal;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="macro_plan_id")
	private UserMacroPlan macroPlan;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade = {CascadeType.MERGE})
	@JoinTable(name="user_userauthority",
					joinColumns= { @JoinColumn(name="user_id") },
					inverseJoinColumns= {@JoinColumn(name="authority_id")})
	private Set<UserAuthority> userAuthorities = new HashSet<>();

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST },
					fetch = FetchType.EAGER, mappedBy = "creator")
	private Set<Food> myFoods = new HashSet<>();
	
	public User() {
	}
	
	public User(String username, String password, String matchingPassword, String firstName, String lastName, String email, String phoneNumber,
			String country) {
		this.username = username;
		this.password = password;
		this.matchingPassword = matchingPassword;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.country = country;
	}
	
	/*public User(String username, String password, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}*/

	/*public User(User user) {
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.phoneNumber = user.getPhoneNumber();
		this.country = user.getCountry();
	}*/


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isSubmittedGoal() {
		return submittedGoal;
	}

	public void setSubmittedGoal(boolean submittedGoal) {
		this.submittedGoal = submittedGoal;
	}

	public UserGoal getGoal() {
		return goal;
	}

	public void setGoal(UserGoal goal) {
		this.goal = goal;
	}

	public UserMacroPlan getMacroPlan() {
		return macroPlan;
	}

	public void setMacroPlan(UserMacroPlan macroPlan) {
		this.macroPlan = macroPlan;
	}

	public Set<UserAuthority> getUserAuthorities() {
		return userAuthorities;
	}

	public void setUserAuthorities(Set<UserAuthority> authorities) {
		this.userAuthorities = authorities;
	}

	public static UserMacroPlan generateMacroPlan(UserGoal u) {
		double genderConstant = u.getGender().charAt(0) == 'M' ? 5 : -161;
		double weightKg = u.getWeightPounds() * 0.453592;
		double heightCm = (((double) u.getHeightFeet() * 12.00) + (double) u.getHeightInches()) * 2.54;
		double bmr = (weightKg * 10.00) + (heightCm * 6.25) - ((double) u.getAge() * 5.00) + genderConstant;
		double tdee;
		
		if (u.getActivityLevel() == 1)
			tdee = bmr * 1.375; 
		else if (u.getActivityLevel() == 2)
			tdee = bmr * 1.55;
		else
			tdee = bmr * 1.725;
		
		int targetCalories = u.getGoal().equals("BULKING") ? (int) Math.rint(tdee + 500.00) : (int) Math.rint(tdee - 500.00);
		int proteinGrams = (int) Math.round(u.getWeightPounds());
		int fatGrams = (int) Math.round(((double) targetCalories * ((double) u.getFatPercentagePreferrence() / 100.00)) / 9.00);
		int carbsGrams = (int) Math.round((targetCalories - (proteinGrams * 4.00) - (fatGrams * 9.00)) / 4.00);
		targetCalories = (proteinGrams * 4) + (fatGrams * 9) + (carbsGrams * 4); // update targetCalories to account for rounding
		
		return new UserMacroPlan(targetCalories, carbsGrams, fatGrams, proteinGrams);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> l = new ArrayList<GrantedAuthority>();
		for (UserAuthority ua : getUserAuthorities()) {
			l.add(new SimpleGrantedAuthority(ua.getAuthority()));
		}
		
		return l;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", country=" + country + ", enabled=" + enabled + ", submittedGoal="
				+ submittedGoal + ", goal=" + goal + ", macroPlan=" + macroPlan + ", authorities=" + userAuthorities + "]";
	}

	public void addUserAuthority(UserAuthority ua) {
		this.userAuthorities.add(ua);
		ua.getUsers().add(this);
	}

	public void removeUserAuthority(UserAuthority ua) {
		this.userAuthorities.remove(ua);
		ua.getUsers().remove(this);
	}

	public boolean containsUserAuthority(UserAuthority ua) {
		return userAuthorities.contains(ua);
	}

	public Set<Food> getMyFoods() {
		return myFoods;
	}

	public void setMyFoods(Set<Food> myFoods) {
		this.myFoods = myFoods;
	}
	
	public void addFood(Food f) {
		this.myFoods.add(f);
		f.setCreator(this);
	}
	
	public void deleteFood(Food f) {
		this.myFoods.remove(f);
		f.setCreatorNull();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
