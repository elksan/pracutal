package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.annotations.*;
import vo.VerificationToken;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User {

    protected Integer id;
	protected String password;
	protected String name;
	protected Date createdAt;
	protected Date currentSignInAt;
	protected String currentSignInIp;
	protected String email;
	protected Date lastSignInAt;
	protected String lastSignInIp;
	protected Date rememberCreatedAt;
	protected Date resetPasswordSentAt;
	protected String resetPasswordToken;
	protected int signInCount;
	protected Date updatedAt;
	protected Boolean disabled;
	protected List<Role> roles;

	protected List<VerificationToken> tokens;

    
    
    //private boolean isAdmin;
    
    public User() {
    	
    	this.signInCount = 0;
		this.disabled = true;
    }
    
                                                                                                
    

    public User(int id, String password, Date createdAt, Date currentSignInAt,
			String currentSignInIp, String email, Date lastSignInAt, String lastSignInIp, Date rememberCreatedAt,
			Date resetPasswordSentAt, String resetPasswordToken, int signInCount, Date updatedAt) {
		super();
		this.id = id;
		this.password = password;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
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

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	public List<VerificationToken> getTokens() {
		return tokens;
	}

	@OrderBy(value = "id DESC ")
	public void setTokens(List<VerificationToken> tokens) {
		this.tokens = tokens;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}
}