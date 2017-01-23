package vo;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertTrue;

/**
 * Created by Diego on 22-06-2016.
 */
public class ActivationFormVO {

	private String newPassword;
	private String confirmPassword;
	private String token;

	@NotEmpty
	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@NotEmpty
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}


	@AssertTrue(message="Los campos de contraseña deben ser iguales")
	private boolean isValid() {
		return this.newPassword.equals(this.confirmPassword);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
