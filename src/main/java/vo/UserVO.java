package vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import models.Organization;
import models.Role;
import models.Student;

import java.util.Date;
import java.util.List;

/**
 * Created by Diego on 12-06-2016.
 */

/*@JsonTypeInfo(
		use = JsonTypeInfo.Id.NAME,
		include = JsonTypeInfo.As.PROPERTY,
		property = "type")
@JsonSubTypes({
		@Type(value = OrganizationVO.class, name = "organization"),
		@Type(value = Student.class, name = "student") })*/
public class UserVO {

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
	public String type;
	protected List<Role> roles;
	protected String role;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getCurrentSignInAt() {
		return currentSignInAt;
	}

	public void setCurrentSignInAt(Date currentSignInAt) {
		this.currentSignInAt = currentSignInAt;
	}

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

	public Date getLastSignInAt() {
		return lastSignInAt;
	}

	public void setLastSignInAt(Date lastSignInAt) {
		this.lastSignInAt = lastSignInAt;
	}

	public String getLastSignInIp() {
		return lastSignInIp;
	}

	public void setLastSignInIp(String lastSignInIp) {
		this.lastSignInIp = lastSignInIp;
	}

	public Date getRememberCreatedAt() {
		return rememberCreatedAt;
	}

	public void setRememberCreatedAt(Date rememberCreatedAt) {
		this.rememberCreatedAt = rememberCreatedAt;
	}

	public Date getResetPasswordSentAt() {
		return resetPasswordSentAt;
	}

	public void setResetPasswordSentAt(Date resetPasswordSentAt) {
		this.resetPasswordSentAt = resetPasswordSentAt;
	}

	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	public int getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(int signInCount) {
		this.signInCount = signInCount;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
