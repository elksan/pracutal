package models;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	private Integer id;
	private String name;
	private Date updatedAt;
	private Date createdAt;
	private Set<User> users;
	
	public Role(){
		
		
	}
	
	public Role(Integer id, String name, Date updatedAt, Date createdAt, Set<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.users = users;
	}
	
	
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Column(name="created_at")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
