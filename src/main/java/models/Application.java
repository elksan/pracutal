package models;

import etc.ApplicationStatus;

import java.util.Date;

import javax.persistence.*;


@Entity
@NamedEntityGraphs({
		@NamedEntityGraph(name = "applicationWithOfferAndStudent",
				attributeNodes = {
						@NamedAttributeNode(value = "student", subgraph = "career"),
						@NamedAttributeNode(value = "offer"),

				},
				subgraphs = {
						@NamedSubgraph(name = "career", attributeNodes = {
								@NamedAttributeNode(value = "career")
						})
				}
		),
		@NamedEntityGraph(name = "applicationWithStudentAndCareer",
			attributeNodes = {
					@NamedAttributeNode(value = "student", subgraph = "career")
			},
			subgraphs = {
					@NamedSubgraph(name = "career", attributeNodes = {
							@NamedAttributeNode(value = "career")
					})
			}
		)
})

public class Application {


	private Integer id;
	private Integer priorityScore;
	private boolean approved;
	private ApplicationStatus status;
	private boolean candidate;
	private Date createdAt;
	private Date updatedAt;
	private Student student;
	private Offer offer;

	
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	@Column(name="created_at")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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
	
	@Column(name="priority_score")
	public Integer getPriorityScore() {
		return priorityScore;
	}
	public void setPriorityScore(Integer priorityScore) {
		this.priorityScore = priorityScore;
	}
	
	@Column(name="updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Enumerated(EnumType.STRING)
	public ApplicationStatus getStatus() {
		return status;
	}
	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "offer_id")
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "student_id")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		status = ApplicationStatus.EN_PROCESO;
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}


	public boolean isCandidate() {
		return candidate;
	}

	public void setCandidate(boolean candidate) {
		this.candidate = candidate;
	}
}
