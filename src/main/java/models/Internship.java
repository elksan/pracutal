package models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@NamedEntityGraphs({
		@NamedEntityGraph(
				name = "graph.Internship.entries",
				attributeNodes = {
						@NamedAttributeNode(value = "logbookEntries")
				}),

		@NamedEntityGraph(
				name = "graph.Internship.offer.organization",
				attributeNodes = {
						@NamedAttributeNode(value = "offer", subgraph = "organizationSubgraph"),
						@NamedAttributeNode(value = "organization")
				},
				subgraphs = {
						@NamedSubgraph(
								name = "organizationSubgraph",
								attributeNodes = @NamedAttributeNode(value = "organization")
						)
				}),

		@NamedEntityGraph(
				name = "graph.Internship.student+organization",
				attributeNodes = {
						@NamedAttributeNode(value = "student"),
						@NamedAttributeNode(value = "organization")
				}
		),

		@NamedEntityGraph(
				name = "graph.internshipWithEvaluations",
				attributeNodes = {
						@NamedAttributeNode(value = "evaluations")
				}
		)
})
public class Internship {
	
	private boolean approved;
	private boolean closed;
	private Date createdAt;
	private String description;
	private boolean disabled;
	private String duration;
	private Integer id;
	private Date startDate;
	private Date endDate;
	private Date updatedAt;
	private String title;

	private Student student;
	private Offer offer;
	private List<LogbookEntry> logbookEntries;
	private Organization organization;
	private List<Evaluation> evaluations;

	
	
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	@Column(name="created_at")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDisabled() {
		return disabled;
	}
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
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
	
	@Column(name="start_date")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@Column(name="updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "offer_id")
	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	@Column(name="end_date")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy = "internship")
	@OrderBy(value = "createdAt DESC, updatedAt DESC ")
	public List<LogbookEntry> getLogbookEntries() {
		return logbookEntries;
	}

	public void setLogbookEntries(List<LogbookEntry> logbookEntries) {
		this.logbookEntries = logbookEntries;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "internship", cascade = CascadeType.ALL)
	public List<Evaluation> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<Evaluation> evaluations) {
		this.evaluations = evaluations;
	}
}
