package vo;

import models.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Diego on 21-06-2016.
 */
@Entity
@Table(name = "verification_token")
public class VerificationToken {


	public enum VeriticationTokenType { LOST_PASSWORD, EMAIL_VERIFICATION, EMAIL_REGISTRATION };
	private Integer id;
	private User user;
	private String token;
	private Date expiryDate;
	private VeriticationTokenType tokenType;
	private boolean verified;

	public VerificationToken() {
	}

	public VerificationToken(User user, VeriticationTokenType tokenType, String token, int expiryTimeInMinutes) {
		this.user = user;
		this.tokenType = tokenType;
		this.token = token;
		this.expiryDate = calculateExpiryDate(expiryTimeInMinutes);
		this.verified = false;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "token_type")
	@Enumerated(EnumType.STRING)
	public VeriticationTokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(VeriticationTokenType tokenType) {
		this.tokenType = tokenType;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Column(name = "expiry_date")
	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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


	private Date calculateExpiryDate(int expiryTimeInMinutes){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Timestamp(calendar.getTime().getTime()));
		calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
		return new Date(calendar.getTime().getTime());
	}
}
