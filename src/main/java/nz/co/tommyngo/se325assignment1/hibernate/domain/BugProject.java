package nz.co.tommyngo.se325assignment1.hibernate.domain;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="PROJECT")
@NamedQueries({@NamedQuery(name="BugProject.findProjectById", 
				query="select distinct p from BugProject p where p.id = :id"), 
				@NamedQuery(name="BugProject.findAllWithDetail", 
				query="select distinct p from BugProject p left join fetch p.bugs b")})
public class BugProject implements Serializable{
	private long _id;
	private String _name;
	private String _description;
	private Set<BugTracking> _bugs = new HashSet<BugTracking>();
	
	public BugProject(){
	}
	
	public BugProject(String name){
		_name = name;
	}
	public BugProject(String name, String description){
		_name = name;
		_description = description;
	}
	public BugProject(String name, String description, Set<BugTracking> bugs){
		_name = name;
		_description = description;
		_bugs = bugs;
	}
	@Id
	@GeneratedValue(strategy=IDENTITY)
	@Column(name="ID")
	public long getId(){
		return _id;
	}
	
	public void setId(long id){
		this._id = id;
	}
	
	@Column(name="PROJECT_NAME")
	public String getProjectName(){
		return _name;
	}
	
	public void setProjectName(String name){
		this._name = name;
	}
	
	@Column(name="PROJECT_DESCRIPTION")
	public String getProjectDescription(){
		return _description;
	}
	
	public void setProjectDescription(String description){
		this._description = description;
	}
	
	@OneToMany(mappedBy="project",
			   cascade=CascadeType.ALL,
			   orphanRemoval=true,
			   targetEntity=BugTracking.class,
			   fetch=FetchType.EAGER)	
	public Set<BugTracking> getBugs(){
		return _bugs;
	}
	
	public void setBugs(Set<BugTracking> bugs){
		this._bugs = bugs;
	}
	public void addBug(BugTracking bug){
		bug.setProject(this);
		this._bugs.add(bug);
	}
	public void removeBug(BugTracking bug){
		getBugs().remove(bug);
	}
	
	public String toString(){
		return "Project id: " + this._id + "Project Name: " + this._name;
	}
}
