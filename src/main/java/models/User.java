package models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class User {
      
    private Integer id;
    
    private String password;
    
    private String firstName;
    
    private String lastName;
    
    private String motherLastName;
    
    private Date createdAt;
    
    private Date currentSignInAt;
    
    private String currentSignInIp;
    
    private String email;
    
    private Date lastSignInAt;
    
    private String lastSignInIp;
    
    private Date rememberCreatedAt;
    
    private Date resetPasswordSentAt;
    
    private String resetPasswordToken;
    
    private int signInCount;
    
    private Date updatedAt;
    
    private Student student;

	private Organization organization;
    
    private List<Role> roles;

    
    
    //private boolean isAdmin;
    
    public User() {
    	
    	this.signInCount = 0;
    }
    
                                                                                                
    

    public User(int id, String password, String firstName, String lastName, Date createdAt, Date currentSignInAt,
			String currentSignInIp, String email, Date lastSignInAt, String lastSignInIp, Date rememberCreatedAt,
			Date resetPasswordSentAt, String resetPasswordToken, int signInCount, Date updatedAt) {
		super();
		this.id = id;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.createdAt = createdAt;
		this.currentSignInAt = currentSignInAt;
		this.currentSignInIp = currentSignInIp;
		this.email = email;
		this.lastSignInAt = lastSignInAt;
		this.lastSignInIp = lastSignInIp;
		this.rememberCreatedAt = rememberCreatedAt;
		this.resetPasswordSentAt = resetPasswordSentAt;
		this.resetPasswordToken = resetPasswordToken;
		this.signInCount = signInCount;
		this.updatedAt = updatedAt;
	}

	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name="first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name="created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name="current_sign_in_at")
	public Date getCurrentSignInAt() {
		return currentSignInAt;
	}

	public void setCurrentSignInAt(Date currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}
    
	@Column(name="current_sign_in_ip")
	public String getCurrentSignInIp() {
		return currentSignInIp;
	}

	public void setCurrentSignInIp(String currentSignInIp) {
		this.currentSignInIp = currentSignInIp;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="last_sign_in_at")
	public Date getLastSignInAt() {
		return lastSignInAt;
	}

	public void setLastSignInAt(Date lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}

	@Column(name="last_sign_in_ip")
	public String getLastSignInIp() {
		return lastSignInIp;
	}

	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}

	@Column(name="remember_created_at")
	public Date getRememberCreatedAt() {
		return rememberCreatedAt;
	}

	public void setRememberCreatedAt(Date rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	@Column(name="reset_password_sent_at")
	public Date getResetPasswordSentAt() {
		return resetPasswordSentAt;
	}

	public void setResetPasswordSentAt(Date resetPasswordSentAt) {
		this.resetPasswordSentAt = resetPasswordSentAt;
	}

	@Column(name="reset_password_token")
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	@Column(name="sign_in_count")
	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	@Column(name="updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name="mother_last_name")
	public String getMotherLastName() {
		return motherLastName;
	}

	public void setMotherLastName(String motherLastName) {
		this.motherLastName = motherLastName;
	}

	@OneToOne(fetch=FetchType.LAZY, mappedBy="user")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	@OneToOne(fetch=FetchType.LAZY, mappedBy="user")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name = "role_user", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}



}