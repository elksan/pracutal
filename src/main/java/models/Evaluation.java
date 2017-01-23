package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Diego on 01-07-2016.
 */
@Entity
public class Evaluation {

	private int id;
	private String filename;
	private Date date;
	private Internship internship;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "internship_id")
	public Internship getInternship() {
		return internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
